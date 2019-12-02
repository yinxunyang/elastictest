package com.bestvike.frame.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.mapper.MapperScannerConfigurer;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Comparator;

@Configuration
public class FrameMybatisConfiguration implements ApplicationContextAware {
    // @Autowired
    // DataSource dataSource;
    protected Log logger = LogFactory.getLog(this.getClass());

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

    @Bean(name="frameDataSource")
    @ConfigurationProperties(prefix = "datasources.frame.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build(); // .type(DataSource.class).build();
    }

    @Value("${datasources.frame.mybatis.dialect}")
    private String dialect;
    @Value("${datasources.frame.mybatis.aliasesPackage}")
    private String aliasesPackage;
    @Value("${datasources.frame.mybatis.mapperLocations}")
    private String mapperLocations;
    @Value("${datasources.frame.mybatis.underscoreToCamelCase}")
    private Boolean underscoreToCamelCase;
    @Bean(name="frameSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setTypeAliasesPackage(aliasesPackage);
        sqlSessionFactoryBean.setDialect(dialect);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            if (!StringUtils.isEmpty(mapperLocations)) {
                String[] paths = mapperLocations.split(",");
                Resource[] resources = null;
                for (String path : paths) {
                    resources = (Resource[]) ArrayUtils.addAll(resources, resolver.getResources(path));
                }
                Arrays.sort(resources, new ResourceComparator());
                sqlSessionFactoryBean.setMapperLocations(resources);
            }
            // sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
            sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(underscoreToCamelCase);
            sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCaseForMap(underscoreToCamelCase);
//            sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean(name="frameSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean(name = "frameTransactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    public class ResourceComparator implements Comparator<Resource> {
        @Override
        public int compare(Resource r1, Resource r2) {
            if (r1.getFilename().startsWith("AbstractMapper.xml")) {
                return -1;
            } else if (r2.getFilename().startsWith("AbstractMapper.xml")) {
                return 1;
            }
            return 0;
        }
    }

    @Configuration
    @AutoConfigureAfter(FrameMybatisConfiguration.class)
    public static class MyBatisMapperScannerConfig implements ApplicationContextAware {
        private String basePackage;
        private Boolean underscoreToCamelCase;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            basePackage = applicationContext.getEnvironment().getProperty("datasources.frame.mybatis.basePackage");
            underscoreToCamelCase = Boolean.parseBoolean(applicationContext.getEnvironment().getProperty("datasources.frame.mybatis.underscoreToCamelCase"));
        }
        @Bean(name = "frameMapperScannerConfigurer")
        public MapperScannerConfigurer mapperScannerConfigurer() {
            MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
            mapperScannerConfigurer.setCamelhumpToUnderline(underscoreToCamelCase);
            mapperScannerConfigurer.setBasePackage(basePackage);
            mapperScannerConfigurer.setSqlSessionFactoryBeanName("frameSqlSessionFactory");
            return mapperScannerConfigurer;
        }
    }
}