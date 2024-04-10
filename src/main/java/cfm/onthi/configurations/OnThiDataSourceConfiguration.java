package cfm.onthi.configurations;

import cfm.onthi.exceptions.ExceptionTranslator;
import cfm.onthi.utils.DefineProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@ConfigurationProperties("spring.datasource-onthi")
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = DefineProperties.entityManagerOnThi, transactionManagerRef = DefineProperties.dbTransactionOnThi, basePackages = {
        DefineProperties.repositoryPackageOnThi, DefineProperties.entitiesPackageOnThiExtend})
public class OnThiDataSourceConfiguration extends HikariConfig {
    protected final OnThiHikariConfiguration scanWalletHikariConfiguration;
    protected Properties jpaProperty;

    public OnThiDataSourceConfiguration(OnThiHikariConfiguration scanWalletHikariConfiguration, HibernateConfiguration hibernateConfiguration) {
        this.scanWalletHikariConfiguration = scanWalletHikariConfiguration;
        setPoolName(this.scanWalletHikariConfiguration.getPoolName());
        setMinimumIdle(this.scanWalletHikariConfiguration.getMinimumIdle());
        setMaximumPoolSize(this.scanWalletHikariConfiguration.getMaximumPoolSize());
        setIdleTimeout(this.scanWalletHikariConfiguration.getIdleTimeout());
        setSchema(DefineProperties.schemaOnThi);
        //setDataSourceJNDI(DefineProperties.dataSourceOnThi);

        jpaProperty = new Properties() {
            private static final long serialVersionUID = 4L;

            {
                put("hibernate.dialect", hibernateConfiguration.getDialect());
                put("hibernate.show_sql", hibernateConfiguration.isShowSql());
                put("hibernate.format_sql", hibernateConfiguration.isFormatSql());
            }
        };
    }

    @Bean
    public HikariDataSource scanWalletDataSource() {
        return new HikariDataSource(this);
    }

    @Bean(name = DefineProperties.entityManagerOnThi)
    public LocalContainerEntityManagerFactoryBean scanWalletEntityManager() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactoryBean.setDataSource(scanWalletDataSource());
        entityManagerFactoryBean.setPackagesToScan(DefineProperties.entitiesPackageOnThi, DefineProperties.entitiesPackageOnThiExtend);
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(jpaProperty);
        entityManagerFactoryBean.setPersistenceUnitName(DefineProperties.persistenceUnitNameOnThi);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        return entityManagerFactoryBean;
    }

    @Bean(name = DefineProperties.dbTransactionOnThi)
    public PlatformTransactionManager scanWalletTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(scanWalletEntityManager().getObject());
        return transactionManager;
    }

    @Bean(name = DefineProperties.jdbcTemplateOnThi)
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(scanWalletDataSource());
        return template;
    }

    @Bean(name = DefineProperties.connectionProviderOnThi)
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(scanWalletDataSource());
    }

    @Bean(name = DefineProperties.DSLConnectionOnThi)
    public org.jooq.Configuration dslConnectionOnThi() {
        return new DefaultConfiguration()
                .derive(connectionProvider())
                .derive(new DefaultExecuteListenerProvider(new ExceptionTranslator()))
                .derive(SQLDialect.MARIADB);
    }

    @Bean(name = DefineProperties.DSLContextOnThi)
    public DSLContext dslContextOnThi() {
        // xem thêm : https://www.jooq.org/javadoc/latest/org.jooq/org/jooq/DSLContext.html
        return new DefaultDSLContext(dslConnectionOnThi());
    }
}

