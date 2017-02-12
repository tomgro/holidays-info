package holidays.controller;

import holidays.Application;
import holidays.configuration.AppConfig;
import holidays.model.Holiday;
import holidays.model.Holidays;
import holidays.response.ResponseHoliday;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by tomek on 10.02.17.
 */

@RestController
public class HolidaysController {

    @Autowired
    private AppConfig appConfig;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping("/holidays")
    public ResponseHoliday holidays(@RequestParam(value = "date") String date,
                                    @RequestParam(value = "countryCode1") String countryCode1,
                                    @RequestParam(value = "countryCode2") String countryCode2,
                                    RestTemplate restTemplate) {
        LocalDate dateTime = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(appConfig.getDatePattern());
            dateTime = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return new ResponseHoliday("Date format is not valid.");
        }

        ResponseHoliday responseHoliday = getNextHolidayForCountries(countryCode1, countryCode2, dateTime, dateTime, restTemplate);

        return responseHoliday;
    }

    private ResponseHoliday getNextHolidayForCountries(String countryCode1, String countryCode2, final LocalDate dateTime,
                                                       final LocalDate orgDateTime,
                                                       RestTemplate restTemplate) {
        Holidays holidaysForCountry1 = getHolidaysForCountry(countryCode1, dateTime, restTemplate);
        Holidays holidaysForCountry2 = getHolidaysForCountry(countryCode2, dateTime, restTemplate);

        ResponseHoliday response = null;

        if (holidaysForCountry1.getError() != null) {
            return new ResponseHoliday(holidaysForCountry1.getError());
        }
        if (holidaysForCountry2.getError() != null) {
            return new ResponseHoliday(holidaysForCountry2.getError());
        }

        for (Holiday hc1 : holidaysForCountry1.getHolidays()) {
            for (Holiday hc2 : holidaysForCountry2.getHolidays()) {
                if (hc1.getDate().isAfter(orgDateTime) && hc2.getDate().isAfter(orgDateTime) &&
                        hc1.getDate().isEqual(hc2.getDate())) {

                    response = new ResponseHoliday(hc1.getDate().toString(), hc1.getName(), hc2.getName());
                    return response;
                }
            }
        }

        LocalDate nextMont = dateTime.plusMonths(1);
        return getNextHolidayForCountries(countryCode1, countryCode2, nextMont, orgDateTime, restTemplate);
    }

    private Holidays getHolidaysForCountry(String countryCode, LocalDate dateTime, RestTemplate restTemplate) {
        String url = buildUrl(countryCode, dateTime.getYear(), dateTime.getMonthValue());
        Holidays holidays = null;
        try {
            holidays = restTemplate.getForObject(
                    url, Holidays.class);

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() != HttpStatus.OK) {
                holidays = new Holidays(ex.getResponseBodyAsString());
            }
        }
        return holidays;
    }

    private String buildUrl(String country, int year, int month) {
        String baseUrl = appConfig.getBaseUrl() + appConfig.getUrlParams() + appConfig.getKey();
        String url = baseUrl.replace(appConfig.getParamCountry(), country);
        url = url.replace(appConfig.getParamYear(), String.valueOf(year));
        url = url.replace(appConfig.getParamMonth(), String.valueOf(month));
        log.info(url);
        return url;
    }
}
