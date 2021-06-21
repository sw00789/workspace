import java.text.SimpleDateFormat;
import java.util.Date;

public class Work {
    public String work;
    public Date date;
    public int time;

    public String inf(){
        //SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //date = new Date(date.getTime()+time);


        return work;
    }

    public String dat(){
        SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date(date.getTime()+time);
        return a.format(date);
    }

}
