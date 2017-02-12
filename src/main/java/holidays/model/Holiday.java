package holidays.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import holidays.json.JsonDateDeserializer;

import java.time.LocalDate;

/**
 * Created by tomek on 10.02.17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Holiday {

    private String name;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    private LocalDate date;

    public Holiday() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
