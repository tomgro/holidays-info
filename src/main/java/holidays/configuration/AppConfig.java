package holidays.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

/**
 * Created by tomek on 10.02.17.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class AppConfig {

    public AppConfig() {}

    @Value("${api.key}")
    private String key;

    @Value("${base.url}")
    private String baseUrl;

    @Value("${url.params}")
    private String urlParams;

    @Value("${param.country}")
    private String paramCountry;

    @Value("${param.year}")
    private String paramYear;

    @Value("${param.month}")
    private String paramMonth;

    @Value("${date.pattern}")
    private String datePattern;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public String getParamCountry() {
        return paramCountry;
    }

    public void setParamCountry(String paramCountry) {
        this.paramCountry = paramCountry;
    }

    public String getParamYear() {
        return paramYear;
    }

    public void setParamYear(String paramYear) {
        this.paramYear = paramYear;
    }

    public String getParamMonth() {
        return paramMonth;
    }

    public void setParamMonth(String paramMonth) {
        this.paramMonth = paramMonth;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
