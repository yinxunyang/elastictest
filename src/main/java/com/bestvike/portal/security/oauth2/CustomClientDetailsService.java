package com.bestvike.portal.security.oauth2;

import com.bestvike.portal.dao.SysUserTokenDao;
import com.bestvike.portal.data.SysUserToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by lihua on 2017/9/12.
 */
@Component
public class CustomClientDetailsService implements ClientDetailsService {
    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private SysUserTokenDao sysUserTokenDao;

    @Value("${app.token.access.expire:60*24*24}")
    private Integer ACCESS_TOKEN_VALIDITY_SECONDS;
    @Value("${app.token.refresh.expire:60*24*24*30}")
    private Integer REFRESH_TOKEN_VALIDITY_SECONDS;

    private String RESOURCE_ID = "bestvike";
    // private Integer ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24;// 60 * 10

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (StringUtils.isEmpty(clientId)) {
            throw new ClientRegistrationException("clientId不能为空");
        }
        SysUserToken sysUserToken = sysUserTokenDao.selectByPrimaryKey(clientId);
        if (sysUserToken == null) {
            throw new ClientRegistrationException("clientId[" + clientId + "]未注册");
        }
        String scopes = "read,write";// ,trust
        String grantTypes = "client_credentials,refresh_token";// ,authorization_code,implicit,password
        String authorities = "ROLE_CLIENT";// ,ROLE_USER
        BaseClientDetails clientDetails = new BaseClientDetails(clientId, RESOURCE_ID, scopes, grantTypes, authorities);
        clientDetails.setClientSecret(sysUserToken.getClientSecret());
        clientDetails.setAccessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
        clientDetails.setRefreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
        return clientDetails;
    }
}
