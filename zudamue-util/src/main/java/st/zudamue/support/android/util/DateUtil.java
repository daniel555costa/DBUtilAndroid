package st.zudamue.support.android.util;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by xdaniel on 02/02/17.
 *
 * @author Daniel Costa <costa.xdaniel@gmail.com>
 */
public class DateUtil {

    private Date date;

    public DateUtil(Date date){

        this.date = date;

    }

    public DateUtil() {
        this.date = Calendar.getInstance().getTime();
    }

    public Date firtDayOfWeek() {

        /*
        SUNDAY as domingo          1  | 10
        MONDAY as segunda-feira    2  | 11
        TUESDAY as terça-feira     3  | 12
        WEDNESDAY as quarta-feira  4  | 13
        THURSDAY as quinta-feira   5  | 14
        FRIDAY as sexta-feira      6  | 15
        SATURDAY as sabado         7  | 16
         */


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);

        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int decrementDays = (currentDayOfWeek-1) * -1;

        calendar.add(Calendar.DATE, decrementDays);

        return calendar.getTime();
    }

    public Date lastDayOfWeek () {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);

        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int incrementDays = 7 - currentDayOfWeek ;

        calendar.add(Calendar.DATE, incrementDays);
        return calendar.getTime();

    }


    public Date firstDayOfMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        calendar.set(Calendar.DATE, 1);
        return  calendar.getTime();

    }

    public Date lastDayOfMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) +1);
        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -1);

        /*
        int dateMaximun = calendar.getMaximum(Calendar.MONTH);
        calendar.value(Calendar.DATE, dateMaximun);
        */
        return calendar.getTime();
    }

    public int countDayOfMonth() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.lastDayOfMonth());
        return calendar.get(Calendar.DATE);

    }


    /**
     * Calcular a diference entra as datas
     * A difirence e entra a data classe - a ountra data ( data do paramentro )
     * @param differenceDate como outra data
     * @return
     */
    public Interval difference( Date differenceDate ){
        Interval interval = new Interval();
        Calendar local = Calendar.getInstance();
        local.setTime( this.date );
        Calendar difference = Calendar.getInstance();
        difference.setTime( differenceDate );

        // this.date =  2016-12-03 08:30:28
        // this.other = 2017-03-04 08:30:28

        interval.intervalInMillis = local.getTimeInMillis() - difference.getTimeInMillis();
        interval.year = local.get( Calendar.YEAR ) - difference.get( Calendar.YEAR ); // ( 2016 - 2017 ) -1 -> 0
        interval.month = local.get( Calendar.MONTH ) - difference.get( Calendar.MONTH );  // (12 - 03) = 9 ->

        interval.day = local.get( Calendar.DATE ) - difference.get( Calendar.DATE ); // (03 - 04 ) = - 1
        interval.hour = local.get( Calendar.HOUR_OF_DAY ) - difference.get( Calendar.HOUR_OF_DAY );
        interval.minute = local.get( Calendar.MINUTE ) - difference.get( Calendar.MINUTE );
        interval.second = local.get( Calendar.SECOND ) - difference.get( Calendar.SECOND );
        interval.msecond = local.get( Calendar.MILLISECOND ) - difference.get( Calendar.MILLISECOND );

        return interval;
    }


    public Interval differencePositive( Date diferenceDate ){
        Interval interval = difference( diferenceDate );
        if( interval.intervalInMillis < 0) {
            Date aux = this.date;
            this.date = diferenceDate;
            interval = difference(aux);
            this.date = aux;
        }

        return interval;
    }

    /**
     * Calcular a diferença negativa entre as datas
     * @param date
     * @return
     */
    public Interval differenceNormalized(Date date ) {
        Interval interval = difference( date );
        return normalize( interval );
    }

    public Interval differencePositiveNormalized( Date date ){
        Interval interval = differencePositive( date );
        return normalize( interval );
    }

    @NonNull
    private Interval normalize(Interval interval) {
        if( interval.year < 0 && interval.month > 0) {
            interval.year++;
            interval.month = interval.month - 12;
        }

        if( interval.month < 0 && interval.day > 0 ){
            interval.month++;
            interval.day = interval.day - 30;
        }

        if( interval.day <0 && interval.hour > 0 ){
            interval.day++;
            interval.hour = interval.hour - 24;
        }

        if( interval.hour < 0 && interval.minute > 0 ){
            interval.hour++;
            interval.minute = interval.minute - 59;
        }

        if (interval.minute < 0 && interval.second > 0 ){
            interval.minute++;
            interval.second = interval.second - 59;
        }

        return interval;
    }

    public boolean isToday() {
        Calendar current = Calendar.getInstance();
        Calendar  date = Calendar.getInstance();
        date.setTime( this.date );

        return current.get( Calendar.YEAR ) == date.get( Calendar.YEAR )
                && current.get( Calendar.MONTH ) == date.get( Calendar.MONTH )
                && current.get( Calendar.DAY_OF_MONTH ) == date.get( Calendar.DAY_OF_MONTH )
                ;
    }


    public class  Interval {
        int year;
        int month;
        int day;
        int hour;
        int minute;
        int second;
        int msecond;
        private long intervalInMillis;

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }


        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSecond() {
            return second;
        }

        public int getMsecond() {
            return msecond;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Interval{");
            sb.append("year=").append(year);
            sb.append(", month=").append(month);
            sb.append(", day=").append(day);
            sb.append(", hour=").append(hour);
            sb.append(", minute=").append(minute);
            sb.append(", second=").append(second);
            sb.append(", msecond=").append(msecond);
            sb.append('}');
            return sb.toString();
        }

        public long getIntervalInMillis() {
            return this.intervalInMillis;
        }
    }




}
