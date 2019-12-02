package com.bestvike.portal.security.oauth2;

import com.bestvike.commons.redis.Cache;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by lihua on 2017/9/12.
 */
@Configurable(autowire = Autowire.BY_TYPE)
@Component
public class CustomTokenStore implements TokenStore {
    @Value("${app.token.access.expire:60*24*24}")
    private Integer ACCESS_TOKEN_VALIDITY_SECONDS;
    @Value("${app.token.refresh.expire:60*24*24*30}")
    private Integer REFRESH_TOKEN_VALIDITY_SECONDS;

    @Autowired
    private Cache cache;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        return cache.get("authentication:" + token, OAuth2Authentication.class);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        // String clientId = authentication.getOAuth2Request().getClientId();
        cache.setExpire("access_token:" + token.getValue(), token, token.getExpiresIn());
        cache.setExpire("authentication:" + token.getValue(), authentication, token.getExpiresIn());
        String clientId = authentication.getOAuth2Request().getClientId();
        if (!StringUtils.isEmpty(clientId)) {
            String oldAccessTokenValue = cache.get("clients:" + clientId);
            if (!StringUtils.isEmpty(oldAccessTokenValue)) {
                OAuth2AccessToken oldOAuth2AccessToken = cache.get("access_token:" + oldAccessTokenValue, OAuth2AccessToken.class);
                if (oldOAuth2AccessToken != null) {
                    removeAccessToken(oldOAuth2AccessToken);
                    removeRefreshToken(oldOAuth2AccessToken.getRefreshToken());
                }
            }
            cache.setExpire("clients:" + clientId, token.getValue(), token.getExpiresIn());
        }
        if (token.getRefreshToken() != null && token.getRefreshToken().getValue() != null) {
            int refreshTokenExpireIn = token.getExpiresIn() + REFRESH_TOKEN_VALIDITY_SECONDS - ACCESS_TOKEN_VALIDITY_SECONDS;
            cache.setExpire("refresh_token:" + token.getRefreshToken().getValue(), token.getRefreshToken(), refreshTokenExpireIn);
            cache.setExpire("access_to_refresh:" + token.getValue(), token.getRefreshToken().getValue(), token.getExpiresIn());
            cache.setExpire("refresh_to_access:" + token.getRefreshToken().getValue(), token.getValue(), refreshTokenExpireIn);
        }
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return cache.get("access_token:" + tokenValue, OAuth2AccessToken.class);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        cache.del("access_token:" + token.getValue());
        cache.del("authentication:" + token.getValue());
        if (token.getRefreshToken() != null && token.getRefreshToken().getValue() != null) {
            cache.del("access_to_refresh:" + token.getValue());
            cache.del("refresh_to_access:" + token.getRefreshToken().getValue());
        }
        /// cache.del(token.getValue());
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken token, OAuth2Authentication authentication) {
        /// cache.hset("refresh_token:" + token.getValue(), token);
        /// cache.hset("authentication:" + token.getValue(), authentication);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        return cache.get("refresh_token:" + tokenValue, OAuth2RefreshToken.class);
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        cache.del("refresh_token:" + token.getValue());
        // cache.del("authentication:" + token.getValue());
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken token) {
        String accessTokenValue = cache.get("refresh_to_access:" + token.getValue());
        if (!StringUtils.isEmpty(accessTokenValue)) {
            OAuth2AccessToken accessToken = cache.get("access_token:" + accessTokenValue, OAuth2AccessToken.class);
            if (accessToken != null) {
                removeAccessToken(accessToken);
            }
        }
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String key = authenticationKeyGenerator.extractKey(authentication);
        OAuth2AccessToken accessToken = cache.get("access_token:" + key, DefaultOAuth2AccessToken.class);
        if (accessToken != null && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            storeAccessToken(accessToken, authentication);
        }
        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        // TODO: 未用到userName
        String tokenValue = cache.get("clients:" + clientId);
        if (!StringUtils.isEmpty(tokenValue)) {
            OAuth2AccessToken token = cache.get("refresh_token:" + tokenValue, OAuth2AccessToken.class);
            if (token != null) {
                Collection<OAuth2AccessToken> collection = new HashSet<>();
                collection.add(token);
                return collection;
            }
        }
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        return findTokensByClientIdAndUserName(clientId, null);
    }
}
