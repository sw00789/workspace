import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Memory {
    public static void denglu(String user,String password,String a,String b){
            System.out.println("请输入用户名");
            a = new Scanner(System.in).next();
            System.out.println("请输入密码");
            b = new Scanner(System.in).next();

            if(a.equals(user)&&b.equals(password)){
                System.out.println("登陆成功");
            }
            else {
                System.out.println("账户或者密码错误,请重新输入");
                denglu(user,password,a,b);
            }


    }

    public static void read(File file) throws IOException {
        FileReader a = new FileReader(file);
        BufferedReader b = new BufferedReader(a);
        String s;
        int i=1;
        while((s = b.readLine())!=null){
            System.out.println(s);
            i=0;
        }
        if(i==1){
            System.out.println("你已经完成了全部今天应完成的任务");
        }
        b.close();
        a.close();

    }

    public static void writework(File work,File all,File nexttime) throws IOException {
        FileReader a = new FileReader(nexttime);
        BufferedReader b = new BufferedReader(a);
        FileReader c = new FileReader(all);
        BufferedReader d = new BufferedReader(c);
        FileWriter e = new FileWriter(work,false);
        BufferedWriter g = new BufferedWriter(e);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String s,f;
        LocalDateTime starttime = LocalDate.now().atTime(0,0,0);
        LocalDateTime endtime = LocalDate.now().atTime(23,59,59);
        while((s = b.readLine())!=null) {
            if((f = d.readLine())!=null) {
                LocalDateTime localtime = LocalDateTime.parse(s, df);
                if (localtime.isAfter(starttime) && localtime.isBefore(endtime)) {
                    g.write(f);
                    g.newLine();


                }
            }
        }
        b.close();
        d.close();
        g.close();
        a.close();
        c.close();
        e.close();

    }

    public static void writeall(File all,Work a) throws IOException {
        FileWriter b = new FileWriter(all,true);
        BufferedWriter c = new BufferedWriter(b);
        c.write(a.inf());
        c.newLine();
        c.close();
        b.close();

    }

    public static void writeTime(File time,Work a) throws IOException {
        FileWriter b = new FileWriter(time,true);
        BufferedWriter c = new BufferedWriter(b);
        String time1 = String.valueOf(a.time);
        c.write(time1);
        c.newLine();
        c.close();
        b.close();
    }

    public static void writenTime(File nexttime,Work a) throws IOException {
        FileWriter b = new FileWriter(nexttime,true);
        BufferedWriter c = new BufferedWriter(b);
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        c.write(d.format(a.date));
        c.newLine();
        c.close();
        b.close();
    }

    public static void finish(File work,File all,File time,File nexttime) throws IOException, ParseException {
        if(check1(work)) {
            System.out.println("请输入完成的记忆任务");
            String b = new Scanner(System.in).next();
            FileReader c = new FileReader(work);
            BufferedReader d = new BufferedReader(c);
            FileReader e = new FileReader(all);
            BufferedReader f = new BufferedReader(e);
            String s;
            int i = 0;
            while (i == 0) {
                while ((s = d.readLine()) != null) {
                    if (s.equals(b)) {
                        i = 1;
                        System.out.println("恭喜" + s + "已完成");
                        break;
                    }

                }
                if (i == 0) {
                    System.out.println("未找到该记忆任务，请重新输入");
                    b = new Scanner(System.in).next();
                }
            }
            int count = 1;
            while ((s = f.readLine()) != null) {
                if (s.equals(b)) {
                    break;
                }
                count++;
            }
            addtime(time, nexttime, count);
            f.close();
            e.close();
            d.close();
            c.close();
        }


    }

    public static boolean check1(File work) throws IOException {
        FileReader a = new FileReader(work);
        BufferedReader b = new BufferedReader(a);
        int i=0;
        while (b.readLine()!=null){
            i=1;
        }
        if(i==0){
            System.out.println("今天任务已经全部完成");
            return false;
        }
        else
            return true;

    }

    public static void addtime(File time,File nexttime,int count) throws IOException, ParseException {
        FileReader c = new FileReader(time);
        BufferedReader d = new BufferedReader(c);
        FileReader e = new FileReader(nexttime);
        BufferedReader f = new BufferedReader(e);
        int i = 0;

        String s="null";
        String a="null";
        while (i!=count){
            s=d.readLine();
            a=f.readLine();
            i++;

        }
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int g = Integer.valueOf(s).intValue();
        Date w = df.parse(a);
        a=df.format(new Date(w.getTime()+g));

        f.close();
        e.close();
        d.close();
        c.close();


        rewritetime(nexttime,a,count);

    }

    public static void rewritetime(File nexttime,String a,int count) throws IOException {
        FileReader c = new FileReader(nexttime);
        BufferedReader d = new BufferedReader(c);
        d.mark((int)nexttime.length()+1);
        String b;
        String[] e = new String[30];
        int i = 0;
        int k = 0;
        while (d.readLine()!=null){
            k++;
        }
        while (i<k){
            e[i]="null";
            i++;
        }
        i=0;
        int m = 1;
        d.reset();
        while ((b = d.readLine())!=null){
            if(m==count) {
                e[i] = a;
            }
            else {
                e[i] = b;
            }
            i++;
            m++;
        }
        d.close();
        c.close();
        FileWriter f = new FileWriter(nexttime,false);
        BufferedWriter g = new BufferedWriter(f);
        i=0;
        while (i<k){
            g.write(e[i]);
            g.newLine();
            i++;
        }
        g.close();
        f.close();
    }

    public static boolean check(File all,String b) throws IOException {
        FileReader c = new FileReader(all);
        BufferedReader d = new BufferedReader(c);
        String s;
        while ((s = d.readLine())!=null){
            if(s.equals(b)){
                System.out.println("该记忆任务已存在,请重新输入");
                return false;
            }
        }
        return true;


    }

    public static void add(File all,File time,File nexttime) throws IOException {
        Work a = new Work();
        System.out.println("请输入记忆任务的内容:");
        a.work = new Scanner(System.in).next();
        while(!check(all,a.work)){
            a.work = new Scanner(System.in).next();
        }

        System.out.println("请选择该任务的记忆周期:1.1天 2.3天 3.7天");
        int chioce = new Scanner(System.in).nextInt();
        int i=1;
        while(i==1) {
            switch (chioce) {
                case 1:
                    a.time = 24 * 60 * 60 * 1000;
                    i=0;
                    break;
                case 2:
                    a.time = 3*24 * 60 * 60 * 1000;
                    i=0;
                    break;
                case 3:
                    a.time = 7 * 24 * 60 * 60 * 1000;
                    i=0;
                    break;
                default:
                    System.out.println("请输入1-3的整数");
            }
        }
        a.date = new Date();
       /* Calendar b = Calendar.getInstance();
        b.setTime(a.date);
        SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(c.format(a.date));*/
        writeall(all,a);
        writeTime(time,a);
        writenTime(nexttime,a);



    }

    public static void readplus(File all,File time,File nexttime) throws IOException {
        FileReader a = new FileReader(nexttime);
        BufferedReader b = new BufferedReader(a);
        FileReader c = new FileReader(all);
        BufferedReader d = new BufferedReader(c);
        FileReader e = new FileReader(time);
        BufferedReader g = new BufferedReader(e);
        String s,k,m;
        int n;
        int i=0;
        while ((s = b.readLine())!=null){
            k=d.readLine();
            m=g.readLine();
            n = Integer.valueOf(m).intValue();
            n=n/(24 * 60 * 60 * 1000);
            System.out.println("任务:"+k+"  记忆周期为:"+n+"天   下一次截至完成时间为:"+s);
            i=1;
        }
        if(i==0){
            System.out.println("你还未添加任务，请先输入数字2添加任务");
        }
        b.close();
        d.close();
        g.close();
        a.close();
        c.close();
        e.close();

    }

    public static void delete1(File all,File time,File nexttime) throws IOException {
        if(check2(all)) {
            System.out.println("请输入你要删除的任务:");
            String b = new Scanner(System.in).next();
            FileReader e = new FileReader(all);
            BufferedReader f = new BufferedReader(e);
            String s;
            int i = 0;
            int count = 1;
            while (i == 0) {
                while ((s = f.readLine()) != null) {
                    if (s.equals(b)) {
                        i = 1;
                        System.out.println(s + "已删除");
                        break;
                    }
                    count++;

                }
                if (i == 0) {
                    System.out.println("未找到该记忆任务，请重新输入");
                    b = new Scanner(System.in).next();
                }
            }
            f.close();
            e.close();
            delete(all, count);
            delete(time, count);
            delete(nexttime, count);
        }

    }

    public static boolean check2(File all) throws IOException {
        FileReader a = new FileReader(all);
        BufferedReader b = new BufferedReader(a);
        int i=0;
        while (b.readLine()!=null){
            i=1;
        }
        if(i==0){
            System.out.println("你还未添加任务，请添加任务后尝试");
            return false;
        }
        else
            return true;
    }

    public static void delete(File file,int count) throws IOException {
        FileReader a = new FileReader(file);
        BufferedReader b = new BufferedReader(a);
        b.mark((int)file.length()+1);
        String s;
        String[] e = new String[30];
        int i = 0;
        int k = 0;
        while (b.readLine()!=null){
            k++;
        }
        k=k-1;
        while (i<k){
            e[i]="null";
            i++;
        }
        i=0;
        int m = 1;
        b.reset();
        while ((s = b.readLine())!=null){
            if(m==count) {
                m++;
                continue;
            }
            else {
                e[i] = s;
            }
            i++;
            m++;
        }
        b.close();
        a.close();
        FileWriter f = new FileWriter(file,false);
        BufferedWriter g = new BufferedWriter(f);
        i=0;
        while (i<k){
            g.write(e[i]);
            g.newLine();
            i++;
        }
        g.close();
        f.close();
    }


    public static void main(String[] args) throws IOException, ParseException {
        String user = "FZB";
        String password = "qwer";
        String a = "null";
        String b = "null";
        denglu(user,password,a,b);
        File work = new File("Work.txt");
        File all = new File("All.txt");
        File time = new File("Time.txt");
        File nexttime = new File("Nexttime.txt");
        System.out.println(user+"你好,你今天需要完成的任务有:");
        writework(work,all,nexttime);
        read(work);
        int i=0;
        while (i==0) {
            System.out.println("请选择你要的操作:1.完成某件记忆任务 2.增加新的记忆任务 3.查询今天应完成的记忆任务 4.查询全部的记忆任务 5.删除某件任务 6.退出");
            int choice = new Scanner(System.in).nextInt();
            switch (choice) {
                case 1:
                    writework(work,all,nexttime);
                    finish(work, all, time, nexttime);
                    break;
                case 2:
                    add(all, time, nexttime);
                    break;
                case 3:
                    writework(work, all, nexttime);
                    read(work);
                    break;
                case 4:
                    readplus(all,time,nexttime);
                    break;
                case 5:
                    delete1(all,time,nexttime);
                    break;

                case 6:i=1;break;
                default:
                    System.out.println("请输入1或2");
                    break;
            }
        }
        //read(all);
        //read(time);
        //read(nexttime);
        //read(work);

    }
}
