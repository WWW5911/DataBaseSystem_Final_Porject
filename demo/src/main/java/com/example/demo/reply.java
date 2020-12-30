package com.example.demo;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.example.MixedResult.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class reply {
    public static String SelectResult(String query, int type) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringTokenizer st = new StringTokenizer(query, "@");
        double la = st.hasMoreTokens() ? Double.parseDouble(st.nextToken()) : 0;
        double lo = st.hasMoreTokens() ? Double.parseDouble(st.nextToken()) : 0;
        String order = st.hasMoreTokens() ? st.nextToken() : "";
        String need = st.hasMoreTokens() ? st.nextToken() : "";
        int time = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) * 100 : 0;
        Date myDate = new Date();
        @SuppressWarnings("deprecation")
        int currentTime = (myDate.getHours() * 100 + myDate.getMinutes());
        Calendar c = Calendar.getInstance();
        c.setTime(myDate);
        int Week = c.get(Calendar.DAY_OF_WEEK) - 1;

        int[] weight = new int[8];
        boolean[] btmp = new boolean[8];
        int i = 0;
        for (i = 0; i < 7; ++i) {
            int index = order.charAt(i) - '0';
            if (index != 0) {
                weight[index] = 8 - i;
                btmp[i] = true;
            } else
                break;
        }
        for (int j = 0; j < 7; ++j) {
            if (!btmp[j]) {
                weight[j] = 8 - i;
                ++i;
            }
        }
        int ne_rating = need.charAt(0) - '0', ne_km = need.charAt(1) - '0', ne_weather = need.charAt(2) - '0',
                ne_pop = (need.charAt(3) - '0') * 10, ne_temp = (need.charAt(4) - '0') * 10 + need.charAt(5) - '0',
                ne_AvgAt = (need.charAt(6) - '0') * 10 + need.charAt(7) - '0',
                ne_Air = ((need.charAt(8) - '0') * 10 + need.charAt(9) - '0') * 10, ne_status = need.charAt(10) - '0',
                flag = 0;
        String ne_WX = "", ne_STAT = "";
        if (ne_weather == 1)
            ne_WX = "晴";
        else if (ne_weather == 2)
            ne_WX = "陰";
        else if (ne_weather == 3)
            ne_WX = "雨";
        if (ne_status == 1)
            ne_STAT = "良";
        else
            ne_STAT = "普";
        String sql = "select distinct top 100 r.ID, r.latitude, r.longitude, r.name, r.Address, r.rating, week, openTime, closeTime, AQI, Status, startTime, Wx, pop, temperature, AvgAt, (  (r.latitude - 25.0335)*(r.latitude -25.0335) + (r.longitude -121.4322)*(r.longitude -121.4322) ) as t from (select * from Restaurant, Service where Restaurant.ID = Service.R_ID) as r inner join ServiceTime on r.S_ID = ServiceTime.ID inner join (select * from inAir inner join AQI on inAir.A_SiteId = AQI.SiteId ) as a on r.ID = a.R_ID  inner join (select * from inWeather inner join Weather on inWeather.W_ID = Weather.ID ) as w on r.ID = w.R_ID where ";
        String[] where = new String[8];
        for (int k = 0; k < 8; ++k)
            where[k] = "";
        if (ne_rating != 0)
            where[0] = "rating >= " + ne_rating;
        if (ne_km != 0)
            where[1] = " (  (r.latitude - " + lo + ")*(r.latitude -" + lo + ") + (r.longitude -" + la
                    + ")*(r.longitude -" + la + ") )*2000 <= " + ne_km;
        else
            where[1] = " (  (r.latitude - " + lo + ")*(r.latitude -" + lo + ") + (r.longitude -" + la
                    + ")*(r.longitude -" + la + ") )*2000 <= " + 4;
        if (ne_weather != 0)
            where[2] = " Wx  like '%" + ne_WX + "%'";
        if (ne_pop != 0)
            where[3] = " pop <= " + ne_pop;
        if (ne_temp != 0)
            where[4] = " temperature <= " + ne_temp;
        if (ne_AvgAt != 0)
            where[5] = " AvgAt <= " + ne_AvgAt;
        if (ne_Air != 0)
            where[6] = " AQI <= " + ne_Air;
        if (ne_status != 0)
            where[7] = " Status like '%" + ne_STAT + "%'";
        for (int k = 0; k < 8; ++k) {
            if (where[k].length() < 2)
                continue;
            if (flag++ != 0)
                sql += " and ";
            sql += where[k];
        }
        if (time >= 2500) {
            if (flag++ != 0)
                sql += " and ";
            sql += "openTime < " + currentTime;
            sql += "and startTime <= " + (currentTime / 100) + " and (endTime > " + (currentTime / 100)
                    + " or (endTime = 0 and endTime+24 > " + (currentTime / 100) + ")) ";
        } else if (time < 2500) {
            if (flag++ != 0)
                sql += " and ";
            sql += "openTime < " + time;
            sql += "and startTime <= " + (time / 100) + " and (endTime > " + (time / 100)
                    + " or (endTime = 0 and endTime+24 > " + (time / 100) + ")) ";
        }
        if (flag++ != 0)
            sql += " and ";
        sql += "week = " + Week + " order by  t";
        System.out.println(sql);
        // 取得select結果物件 ( 只找la lo之平方和開根號 < 4或指定 (1度為100公里) where 搜尋條件)
        List<MixedResult> mrlist = HomeController.mixedResultJDBCTemplate.listMixedResults(sql);
        for (MixedResult mr : mrlist) {
            double tmp = 0;
            tmp = (mr.rating - 4) * 10 * weight[1];
            // 距離
            /*
             * switch(mr.Wx){ case "晴天" : tmp += 10*weight[3]; break; case "陰天" : tmp +=
             * 5*weight[3]; break; case "雨天" : break; }
             */
            tmp += (10 - mr.pop / 10) * weight[4];
            tmp += Math.abs(mr.temperature - 25.5) > 10 ? -10 * weight[5] : Math.abs(mr.temperature - 25.5) * weight[5];
            tmp += Math.abs(mr.AvgAt - 25.5) > 10 ? -10 * weight[6] : Math.abs(mr.AvgAt - 25.5) * weight[6];
            tmp += (50 - mr.Air) / 10 < -10 ? -10 * weight[7] : (50 - mr.Air) / 10 * weight[7];
            mr.weight = tmp;
        }
        mrlist.sort(Comparator.comparing(MixedResult::getWeight).reversed());
        String ans = "";
        if (type == 1) {
            for (MixedResult mr : mrlist) {
                ans += mr.toQuery() + "\n\r";
            }
        }
        if (type == 2) {
            try {
                ans += "{\"results\":";
                ans += objectMapper.writeValueAsString(mrlist);
                ans += "}";
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ans;
    }
    double getdis(double la1, double la2, double lo1, double lo2){
        return Math.sqrt( (la1-la2)*(la1-la2) + (lo1-lo2)*(lo1-lo2) )*1000000;
    }

}
