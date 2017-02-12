package holidays.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/**
 * Created by tomek on 10.02.17.
 */
public class ResponseHoliday {

    @JsonInclude(Include.NON_EMPTY)
    private String date;
    @JsonInclude(Include.NON_EMPTY)
    private String name1;
    @JsonInclude(Include.NON_EMPTY)
    private String name2;
    @JsonInclude(Include.NON_EMPTY)
    private String error;

    public ResponseHoliday(String date, String name1, String name2) {
        this.date = date;
        this.name1 = name1;
        this.name2 = name2;
    }

    public ResponseHoliday(String error) {
        this.error = error;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
