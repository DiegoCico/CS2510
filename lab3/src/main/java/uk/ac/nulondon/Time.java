package uk.ac.nulondon;

public class Time {
    int hour;
    int second;
    int minute;

    public Time(int h, int s, int m){
        hour = h;
        second = s;
        minute = m;
    }

    public Time(){
        hour = 0;
        second = 0;
        minute = 0;
    }

    public  boolean sameTime(Time t){
        if(t.getHour() == hour && t.getMinute() == minute && t.getSecond() ==  second)
             return true;
         else
             return false;
    }

    public int getHour() { return hour; }
    public int getSecond() { return second; }
    public int getMinute() { return minute; }

}
