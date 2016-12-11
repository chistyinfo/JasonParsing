package greendustbd.jasonparsing.Model;

/**
 * Created by chisty on 12/4/2016.
 */

public class JSonParse {

    /*
    {
        "title": "Bangladesh Premier League",
        "time": "07:30 GMT | 01:30 BDT",
        "date": "04/12/2016"
    }
     */

    private String title;
    private String date;
    private String time;





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
