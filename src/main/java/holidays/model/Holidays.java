package holidays.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Arrays;

/**
 * Created by tomek on 10.02.17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Holidays {

    @JsonInclude(Include.NON_EMPTY)
    private Holiday[] holidays;
    @JsonInclude(Include.NON_EMPTY)
    private String error;

    public Holidays() {
    }

    public Holidays(String error) {

        this.error = error;
    }

    public Holiday[] getHolidays() {
        return holidays;
    }

    public void setHolidays(Holiday[] holidays) {
        this.holidays = holidays;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Holidays{" +
                "holidays=" + Arrays.toString(holidays) +
                ", error='" + error + '\'' +
                '}';
    }
}
