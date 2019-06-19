package br.com.etechoracio.etec_boaviagem.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public  static DateFormat DATE_FORMAT =  new SimpleDateFormat( "dd/MM/yyyy");



    public  static  String FormatDate (int dia,int mes,int ano){
        Calendar calendar = Calendar.getInstance();
        calendar.set(ano,mes,dia);
        return  DATE_FORMAT.format(calendar.getTime());
    }


    public  static  String formatDate(int dia, int mes,int ano) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(ano, mes, dia);
            return DATE_FORMAT.format(calendar.getTime());
        } catch (Exception e) {
            return  null;

        }
    }

    public  static Date toDate (String date){
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {

            return null;
        }
    }
}


