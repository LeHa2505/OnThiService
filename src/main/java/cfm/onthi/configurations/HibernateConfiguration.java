package cfm.onthi.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties("spring.hibernate")
public class HibernateConfiguration {
    private String dialect;
    private Boolean showSql;
    private Boolean formatSql;

    public HibernateConfiguration() {
    }

    public HibernateConfiguration(String dialect, Boolean showSql) {
        this.dialect = dialect;
        this.showSql = showSql;
    }

    public String getDialect() {
        return this.dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public Boolean isShowSql() {
        return this.showSql;
    }

    public Boolean getShowSql() {
        return this.showSql;
    }

    public void setShowSql(Boolean showSql) {
        this.showSql = showSql;
    }

    public Boolean isFormatSql() {
        return formatSql;
    }

    public Boolean getFormatSql() {
        return formatSql;
    }

    public void setFormatSql(Boolean formatSql) {
        this.formatSql = formatSql;
    }
}
