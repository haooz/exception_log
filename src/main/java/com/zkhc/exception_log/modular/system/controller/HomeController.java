package com.zkhc.exception_log.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkhc.exception_log.core.util.CollectionUtil;
import com.zkhc.exception_log.core.util.DateTimeUtil;
import com.zkhc.exception_log.modular.system.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 首页控制器
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private HomeService homeService;


    /**
     * 各系统每日异常总数
     * @return
     */
    @RequestMapping(value = "/sysTotal", method = RequestMethod.GET)
    @ResponseBody
    public Object sysTotal() throws ParseException {
        JSONObject result = new JSONObject();
        List<String> dates=this.getDayBetween(homeService.dateScope().get("minDate").toString(),homeService.dateScope().get("maxDate").toString());
        List<String> systems=homeService.systemList();
        List<String> dateY=new ArrayList<>();
        for(String item:systems){
            dateY=homeService.systemTotal(item,homeService.dateScope().get("minDate").toString(),homeService.dateScope().get("maxDate").toString());
            result.put(item+"_yData",dateY);
        }
        result.put("legendData",systems);
        result.put("xData",dates);
        return JSON.toJSON(result);
    }

    /**
     * 各系统每日异常总数
     * @return
     */
    @RequestMapping(value = "/systemTotal", method = RequestMethod.GET)
    @ResponseBody
    public Object systemTotal() throws ParseException {
        JSONObject result = new JSONObject();
        Map<String,Object> map=homeService.dateScope();
        String startTime=new Date().toLocaleString();
        String endTime=new Date().toLocaleString();
        if(!CollectionUtils.isEmpty(map)){
            startTime=map.get("minDate").toString();
            endTime=map.get("maxDate").toString();
        }
        result.put("data",homeService.homeSystemTotal(startTime,endTime));
        return JSON.toJSON(result);
    }


    /**
     * 各系统每日异常占比
     * @return
     */
    @RequestMapping(value = "/sysPercent", method = RequestMethod.GET)
    @ResponseBody
    public Object sysPercent() throws ParseException {
        JSONObject result = new JSONObject();
        List<String> systems=homeService.systemList();
        List<Map<String,Object>> percent=homeService.totalCountBySys();
        result.put("legendData",systems);
        result.put("percentData",JSON.toJSON(percent));
        return JSON.toJSON(result);
    }

    /**
     * 各环境异常总数
     * @return
     */
    @RequestMapping(value = "/envTotal", method = RequestMethod.GET)
    @ResponseBody
    public Object envTotal() throws ParseException {
        JSONObject result = new JSONObject();
        List<Map<String,Object>> envTotal=homeService.envTotal();
        result.put("envTotal",JSON.toJSON(envTotal));
        return JSON.toJSON(result);
    }

    /**
     * 请求level
     * @return
     */
    @RequestMapping(value = "/requestLevel", method = RequestMethod.GET)
    @ResponseBody
    public Object requestLevel() throws ParseException {
        JSONObject result = new JSONObject();
        Date now=new Date();
        String time=format.format(now);
        List<Map<String,Object>> levelInfo=homeService.levelInfo(time);
        List<String> levels=homeService.levelList();
        List<Map<String,Object>> list=new ArrayList<>();
        List<String> levelIsExist=new ArrayList<>();
        if(levelInfo.size()==0){
            for(int i=0;i<levels.size();i++){
                Map<String,Object> map=new HashMap<>();
                map.put("name",levels.get(i));
                map.put("value",0);
                levelInfo.add(map);
            }
        }else{
            for(Map mp:levelInfo){
                levelIsExist.add(mp.get("name").toString());
            }
            List<String> newLevel= new ArrayList<>(CollectionUtil.getDiffentNoDuplicate(levelIsExist,levels));
            for(String item:newLevel){
                Map<String,Object> map=new HashMap<>();
                map.put("name",item);
                map.put("value",0);
                list.add(map);
            }
            levelInfo.addAll(list);
        }
        result.put("levelInfo",JSON.toJSON(levelInfo));
        return JSON.toJSON(result);
    }

    /**
     * 获取时间段中日期的打印
     *
     * @param minDate 开始时间
     * @param maxDate 结束时间
     * @return 时间段的打印.
     */
    public static List<String> getDayBetween(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月日

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), min.get(Calendar.DAY_OF_MONTH));

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), max.get(Calendar.DAY_OF_MONTH));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar curr = min;
        while (curr.before(max) || curr.equals(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.DAY_OF_MONTH, 1);
        }

        return result;
    }
}
