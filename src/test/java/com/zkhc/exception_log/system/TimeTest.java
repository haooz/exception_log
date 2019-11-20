package com.zkhc.exception_log.system;

import com.zkhc.exception_log.base.BaseJunit;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeTest extends BaseJunit {
    @Test
    public void test(){
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月日

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse("2019-08-01"));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));

            max.setTime(sdf.parse("2019-08-20"));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar curr = min;
        while (curr.before(max) || curr.equals(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println(result.toString());
    }
}
