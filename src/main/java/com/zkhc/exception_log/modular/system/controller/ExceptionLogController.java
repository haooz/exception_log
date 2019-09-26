package com.zkhc.exception_log.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkhc.exception_log.core.common.annotion.BussinessLog;
import com.zkhc.exception_log.core.common.constant.DatasourceEnum;
import com.zkhc.exception_log.core.common.constant.dictmap.ExceptionLogDict;
import com.zkhc.exception_log.core.common.page.LayuiPageFactory;
import com.zkhc.exception_log.core.util.DD;
import com.zkhc.exception_log.core.util.DateTimeUtil;
import com.zkhc.exception_log.core.util.UUIDUtil;
import com.zkhc.exception_log.modular.system.entity.*;
import com.zkhc.exception_log.modular.system.service.*;
import com.zkhc.exception_log.modular.system.warpper.LogWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 异常日志管理控制器
 */
@Controller
@RequestMapping("/exceptionLog")
public class ExceptionLogController extends BaseController {
    private static String PREFIX = "/modular/exception_log/";

    @Autowired
    private ExceptionLogService exceptionLogService;
    @Autowired
    private ExceptionKeyWordService exceptionKeyWordService;
    @Autowired
    private ExceptionSolutionService exceptionSolutionService;
    @Autowired
    private SolutionRelationService solutionRelationService;
    @Autowired
    private ApplicationLogService applicationLogService;
    @Autowired
    private ExceptionOperateService exceptionOperateService;

    /**
     * 跳转到日志管理的首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "exceptionLog.html";
    }

    /**
     * 查询异常列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {

        //获取分页参数
        Page page = LayuiPageFactory.defaultPage();

        //根据条件查询日志
        List<Map<String, Object>> result = exceptionLogService.selectExceptionList(page, condition);
        page.setRecords(new LogWrapper(result).wrap());

        return LayuiPageFactory.createPageInfo(page);
    }

    /**
     * 跳转备注
     */
    @RequestMapping(value = "/remarkDetail/{id}")
    public Object remarkDetail(@PathVariable("id") String id, Model model) {
        model.addAttribute("id",id);
        model.addAttribute("remarks",this.findRemark(id));
        return PREFIX + "exceptionLog_remark_detail.html";
    }

    /**
     * 删除备注
     */
    @RequestMapping(value = "/deleteRemark",method = RequestMethod.POST)
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @BussinessLog(value = "删除备注", key = "id,remark", dict = ExceptionLogDict.class)
    @ResponseBody
    public Object newRemark(@RequestParam String id, @RequestParam String remark) {
        String re=remark.replace("& ", "&");
        String res=HtmlUtils.htmlUnescape(re);
        ExceptionLog exceptionLog = exceptionLogService.getById(id);
        String newRemarks=exceptionLog.getRemark().replace(res+";","");
        ExceptionLog ex=new ExceptionLog();
        ex.setId(id);
        ex.setRemark(newRemarks);
        exceptionLogService.updateById(ex);
        return SUCCESS_TIP;
    }

    /**
     * 跳转关键字
     */
    @RequestMapping(value = "/keyWords/{id}")
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    public Object keyWords(@PathVariable("id") String id, Model model) {
        model.addAttribute("id",id);
        model.addAttribute("keyWords",this.keyWords(id));
        return PREFIX + "exceptionLog_keyWord_detail.html";
    }
    /**
     * 删除关键字
     */
    @RequestMapping(value = "/deleteKeyWord",method = RequestMethod.POST)
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @BussinessLog(value = "删除关键字", key = "id,keyWord", dict = ExceptionLogDict.class)
    @ResponseBody
    public Object deleteKeyWord(@RequestParam String id, @RequestParam String keyWord) {
        ExceptionLog exceptionLog = exceptionLogService.getById(id);
        String newKeys=exceptionLog.getKeyWord().replace(exceptionKeyWordService.selectKeyWordId(keyWord)+",","");
        ExceptionLog ex=new ExceptionLog();
        ex.setId(id);
        ex.setKeyWord(newKeys);
        exceptionLogService.updateById(ex);
        return SUCCESS_TIP;
    }

    /**
     * 跳转解决办法
     */
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @RequestMapping(value = "/solution/{id}")
    public Object solution(@PathVariable("id") String id, Model model) {
        model.addAttribute("solutions",JSON.toJSON(this.getSolution(id)));
        return PREFIX + "exceptionLog_solution_detail.html";
    }

    /**
     * 删除解决办法
     */
    @RequestMapping(value = "/deleteSolution",method = RequestMethod.POST)
    @Transactional(propagation = Propagation.NESTED,
            isolation = Isolation.DEFAULT, readOnly = false)
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @BussinessLog(value = "删除解决办法", key = "solutionId", dict = ExceptionLogDict.class)
    @ResponseBody
    public Object deleteSolution(@RequestParam Integer solutionId) {
        solutionRelationService.deleteRelationBySolutionId(solutionId);
        exceptionSolutionService.removeById(solutionId);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到异常详情
     */
    @RequestMapping("/exceptionLog_detail/{exceptionLogId}")
    public String exceptionLogDetail(@PathVariable String exceptionLogId, Model model){
        ExceptionLog exceptionLog = exceptionLogService.selectByEId(exceptionLogId);
        String remark=exceptionLog.getRemark();
        List<Map<String, Object>> result=new ArrayList<>();
        String keyWord="";
        if(exceptionLog.getKeyWord()!=null && exceptionLog.getKeyWord()!=""){
            String[] kw = exceptionLog.getKeyWord().split(",");
            List<String> k = Arrays.asList(kw);
            List<Long> arrayList = new ArrayList(k);
            result = exceptionLogService.sameSolution(arrayList);
            List<Integer> al = new ArrayList(k);
            keyWord=exceptionKeyWordService.selectKeyWords(al).get(0);
        }else{
            String con = exceptionLog.getContent();
            if (con.indexOf("Caused by: ") != -1) {
                String a = con.substring(con.indexOf("Caused by: "));
                keyWord = a.substring(0, a.indexOf(":", 10));
            } else {
                if(con.indexOf(":") < con.indexOf("</br>")){
                    keyWord=con.substring(0,con.indexOf(":"));
                }else if(con.indexOf("</br>") < con.indexOf(":")) {
                    keyWord = con.substring(0, con.indexOf("</br>"));
                }
            }
        }
        /*List<Map<String, Object>> map=exceptionLogService.sameReason(b);*/
        List<String> specialWords=exceptionLogService.selectSpecialWords();
        StringBuffer sb=new StringBuffer();
        for (String item:specialWords){
            sb.append(item+",");
        }
        sb.deleteCharAt(sb.length()-1);
        String fileName= exceptionLog.getSystem()+"_"+ DateTimeUtil.dateToStr(exceptionLog.getCreatetime(),"MM_dd_HH_mm_ss");
        String time= DateTimeUtil.dateToStr(exceptionLog.getCreatetime());
        String[] res={};
        if(remark !=null && !remark.equals("") && !remark.equals("null")){
            if(remark.indexOf(";")!=-1){
                res=remark.split(";");
            }else{
                res=new String[]{remark};
            }
        }else{
            res=new String[]{"暂无备注"};
        }
        List<String> remarks = Arrays.asList(res);
        Map<String,Object> dateStr=getDate(exceptionLog.getCreatetime());
        List<String> requestErrorInfo=applicationLogService.requestErrorInfo(dateStr.get("startTime").toString(),dateStr.get("endTime").toString(),exceptionLog.getSystem(),exceptionLog.getRunEnvironment());
        model.addAttribute("requestInfo",requestErrorInfo);
        model.addAttribute("exception",keyWord);
        model.addAttribute("item",exceptionLog);
        model.addAttribute("time",time);
        model.addAttribute("remarks",JSON.toJSON(remarks));
        model.addAttribute("sameReason",JSON.toJSON(result));
        model.addAttribute("specialWords",sb.toString());
        model.addAttribute("fileName",fileName);
        return PREFIX + "exceptionLog_edit.html";
    }

    /**
     * 添加异常解决办法(未选择关键字)
     */
    @RequestMapping(value = "/addSolution")
    @ResponseBody
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @BussinessLog(value = "添加异常解决办法(未选择关键字)", key = "id,solution", dict = ExceptionLogDict.class)
    @Transactional(propagation = Propagation.NESTED,
            isolation = Isolation.DEFAULT, readOnly = false)
    public Object addRemark(@RequestParam String id, @RequestParam String solution) {
        ExceptionSolution exceptionSolution=new ExceptionSolution();
        exceptionSolution.setSolution(solution);
        exceptionSolutionService.addSolution(exceptionSolution);
        Integer solutionId=exceptionSolution.getId();
        ExceptionLog exceptionLog=exceptionLogService.selectByEId(id);
        String kw=exceptionLog.getKeyWord();
        String keyWordId="";
        String keyWordDesc="";
        if(kw!=null && kw!=""){
            String[] keyWord=kw.split(",");
            keyWordId=keyWord[0];
        }else{
            String con=exceptionLog.getContent();
            if(con.indexOf("Caused by: ")!=-1){
                String a=con.substring(con.indexOf("Caused by: "));
                keyWordDesc=a.substring(0, a.indexOf(":",10));
            }else{
                if(con.indexOf(":") < con.indexOf("</br>")){
                    keyWordDesc=con.substring(0,con.indexOf(":"));
                }else if(con.indexOf("</br>") < con.indexOf(":")) {
                    keyWordDesc = con.substring(0, con.indexOf("</br>"));
                }
            }
            ExceptionKeyWord ek=exceptionKeyWordService.keyWordIsExists(keyWordDesc);
            if(ek !=null){
                keyWordId=ek.getId().toString();
            }else{
                ExceptionKeyWord exceptionKeyWord=new ExceptionKeyWord();
                exceptionKeyWord.setKeyWord(keyWordDesc);
                exceptionKeyWordService.newKeyWord(exceptionKeyWord);
                keyWordId=exceptionKeyWord.getId().toString();
            }
            exceptionLogService.addKeyWord(id,keyWordId+",");
        }
        SolutionRelation solutionRelation=new SolutionRelation();
        solutionRelation.setKeyWordId(Integer.parseInt(keyWordId));
        solutionRelation.setSolutionId(solutionId);
        solutionRelationService.save(solutionRelation);
        //添加记录
        ExceptionOperate operate=new ExceptionOperate(UUIDUtil.getUUID(),id,"添加解决办法",solution,new Date());
        exceptionOperateService.save(operate);
        DD.send_dealMsg(id,exceptionLog.getSystem()+"系统\n"+"\n"+exceptionLog.getRunEnvironment()+"环境\n"+"\n"+DateTimeUtil.dateToStr(exceptionLog.getCreatetime())+"异常,添加如下信息:\n"+"\n解决办法:\t"+solution);
        return SUCCESS_TIP;
    }

    /**
     * 添加异常解决办法(选择关键字)
     */
    @RequestMapping(value = "/addSolutionWithKW")
    @ResponseBody
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @BussinessLog(value = "添加异常解决办法(选择关键字)", key = "id,keyWords,solution", dict = ExceptionLogDict.class)
    @Transactional(propagation = Propagation.NESTED,
            isolation = Isolation.DEFAULT, readOnly = false)
    public Object addSolutionWithKW(@RequestParam String id, @RequestParam String keyWords, @RequestParam String solution) {
        String cont=keyWords.replace("& ", "&");
        String conts= HtmlUtils.htmlUnescape(cont);
        String keyWord=conts.replaceAll("\'(.*?)\'", "*");
        //添加关键字
        String keyWordId="";
        ExceptionKeyWord ek=exceptionKeyWordService.keyWordIsExists(keyWord);
        if(ek !=null){
            keyWordId=ek.getId().toString();
        }else{
            ExceptionKeyWord exceptionKeyWord=new ExceptionKeyWord();
            exceptionKeyWord.setKeyWord(keyWord);
            exceptionKeyWordService.newKeyWord(exceptionKeyWord);
            keyWordId=exceptionKeyWord.getId().toString();
        }
        //异常表中关键字追加
        ExceptionLog exceptionLog=exceptionLogService.selectByEId(id);
        String kw=exceptionLog.getKeyWord();
        StringBuffer sb=new StringBuffer();
        sb.append(kw);
        if(kw!=null && kw!=""){
            //String[] keyWords=kw.split(",");
            if(kw.indexOf(keyWordId)==-1){
                sb.append(keyWordId+",");
                exceptionLogService.addKeyWord(id,sb.toString());
            }
        }else{
            exceptionLogService.addKeyWord(id,keyWordId+",");
        }
        //添加解决办法
        ExceptionSolution exceptionSolution=new ExceptionSolution();
        exceptionSolution.setSolution(solution);
        exceptionSolutionService.addSolution(exceptionSolution);
        Integer solutionId=exceptionSolution.getId();

        //添加对应关系
        SolutionRelation solutionRelation=new SolutionRelation();
        solutionRelation.setKeyWordId(Integer.parseInt(keyWordId));
        solutionRelation.setSolutionId(solutionId);
        solutionRelationService.save(solutionRelation);
        //添加记录
        ExceptionOperate operate=new ExceptionOperate(UUIDUtil.getUUID(),id,"添加解决办法",solution,new Date());
        exceptionOperateService.save(operate);
        DD.send_dealMsg(id,exceptionLog.getSystem()+"系统\n"+"\n"+exceptionLog.getRunEnvironment()+"环境\n"+"\n"+DateTimeUtil.dateToStr(exceptionLog.getCreatetime())+"异常,添加如下信息:\n"+"\n解决办法:\t"+solution);
        return SUCCESS_TIP;
    }

    /**
     * 添加备注
     */
    @RequestMapping(value = "/addRemark")
    @BussinessLog(value = "添加备注", key = "id,uName,remark", dict = ExceptionLogDict.class)
    @DataSource(name= DatasourceEnum.DATA_SOURCE_ZNKF_ADMIN)
    @Transactional(propagation = Propagation.NESTED,
            isolation = Isolation.DEFAULT, readOnly = false)
    @ResponseBody
    public Object addRemark(@RequestParam String id, @RequestParam String uName, @RequestParam String remark) {
        ExceptionLog exceptionLog = exceptionLogService.selectByEId(id);
        String info=remark+"(添加人:"+uName+","+DateTimeUtil.dateToStr(new Date())+")";
        StringBuffer sb=new StringBuffer();
        sb.append(info+";");
        if(exceptionLog.getRemark()!="" && exceptionLog.getRemark()!=null){
            sb.append(exceptionLog.getRemark());
        }
        exceptionLogService.addRemark(id,sb.toString());
        //添加记录
        ExceptionOperate operate=new ExceptionOperate(UUIDUtil.getUUID(),id,"添加备注",uName,remark,new Date());
        exceptionOperateService.save(operate);
        DD.send_dealMsg(id,exceptionLog.getSystem()+"系统\n"+"\n"+exceptionLog.getRunEnvironment()+"环境\n"+"\n"+DateTimeUtil.dateToStr(exceptionLog.getCreatetime())+"异常,添加如下信息:\n"+"\n备注:\t"+remark+"\n\n操作人:\t"+uName);
        return SUCCESS_TIP;
    }

    /**
     * 下载
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String id=request.getParameter("id");
        ExceptionLog exceptionLog = exceptionLogService.selectByEId(id);
        String fileName= exceptionLog.getSystem()+"_"+exceptionLog.getCreatetime().getTime()+".log";

        String str = exceptionLog.getContent().replaceAll("</br>","");
        response.reset();
        this.setFileDownloadHeader(request,response, fileName);
        PrintWriter writer = response.getWriter();
        writer.write(str);
        writer.flush();
    }

    /**
     * 跳转到类似异常解决办法页面
     */
    @RequestMapping(value = "/sameListPage/{exception}/{id}")
    public Object sameListPage(@PathVariable("exception") String exception, @PathVariable("id") String id, Model model) {
        model.addAttribute("exceptionLog",exception);
        model.addAttribute("id",id);
        return PREFIX + "exceptionLog_same.html";
    }

    /**
     * 类似异常解决办法
     */
    @RequestMapping(value = "/sameList/{id}")
    @ResponseBody
    public Object sameList(@PathVariable String id) {
        ExceptionLog exceptionLog = exceptionLogService.selectByEId(id);
        List<Map<String, Object>> result=new ArrayList<>();
        if(exceptionLog.getKeyWord()!=null && exceptionLog.getKeyWord()!=""){
            String[] kw = exceptionLog.getKeyWord().split(",");
            List<String> k = Arrays.asList(kw);
            List<Long> arrayList = new ArrayList(k);
            result = exceptionLogService.sameSolution(arrayList);
        }
        return result;
    }

    /**
     * 根据id查看
     */
    @RequestMapping("/exceptionLog_info/{exceptionLogId}")
    @ResponseBody
    public Object exceptionLogInfo(@PathVariable String exceptionLogId, Model model) {
        ExceptionLog exceptionLog = exceptionLogService.selectByEId(exceptionLogId);
        return JSON.toJSON(exceptionLog);
    }

    /**
     * 更新浏览次数
     */
    @RequestMapping(value = "/updateViewNum/{id}/{viewNum}")
    @ResponseBody
    public Object updateViewNum(@PathVariable String id, @PathVariable int viewNum) {
        exceptionLogService.updateViewNum(id,viewNum);
        return SUCCESS_TIP;
    }

    /**
     * 删除后台异常信息管理
     */
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除异常信息", key = "exceptionLogId", dict = ExceptionLogDict.class)
    @ResponseBody
    public Object delete(@RequestParam String exceptionLogId) {
        exceptionLogService.deleteByEId(exceptionLogId);
        return SUCCESS_TIP;
    }

    public List<String> findRemark(String id){
        List<String> list=new ArrayList<>();
        String res=exceptionLogService.selectRemark(id);
        String[] remarkArray={};
        if(res !=null && !res.equals("") && !res.equals("null")){
            if (res.indexOf(";") != -1) {
                remarkArray = res.split(";");
            }else{
                remarkArray=new String[]{res};
            }
        }else{
            remarkArray=new String[]{};
        }
        for(int i=0;i<remarkArray.length;i++){
            list.add(remarkArray[i]);
        }
        System.out.println(JSON.toJSON(list).toString());
        return list;
    }

    public List<String> keyWords(String id){
        List<String> keyWordsList=new ArrayList<>();
        ExceptionLog exceptionLog=exceptionLogService.getById(id);
        if(exceptionLog.getKeyWord()!=null && exceptionLog.getKeyWord()!=""){
            String[] kw = exceptionLog.getKeyWord().split(",");
            List<String> k = Arrays.asList(kw);
            List<Integer> al = new ArrayList(k);
            keyWordsList=exceptionKeyWordService.selectKeyWords(al);
        }
        return keyWordsList;
    }

    public List<Map<String, Object>> getSolution(String id){
        List<Map<String, Object>> solutionList=new ArrayList<>();
        ExceptionLog exceptionLog=exceptionLogService.getById(id);
        if(exceptionLog.getKeyWord()!=null && exceptionLog.getKeyWord()!=""){
            String[] kw = exceptionLog.getKeyWord().split(",");
            List<String> k = Arrays.asList(kw);
            List<Long> arrayList = new ArrayList(k);
            solutionList=exceptionLogService.sameSolution(arrayList);
        }
        return solutionList;
    }

    public static void setFileDownloadHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            //中文文件名支持
            String encodedfileName = null;
            String agent = request.getHeader("USER-AGENT");
            if(null != agent && -1 != agent.indexOf("MSIE")){//IE
                encodedfileName = java.net.URLEncoder.encode(fileName,"UTF-8");
            }else if(null != agent && -1 != agent.indexOf("Mozilla")){
                encodedfileName = new String (fileName.getBytes("UTF-8"),"iso-8859-1");
            }else{
                encodedfileName = java.net.URLEncoder.encode(fileName,"UTF-8");
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedfileName + "\"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static Map getDate(Date dateStr){
        Map<String,Object> map=new HashMap<>();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c=Calendar.getInstance();
        Calendar min=Calendar.getInstance();
        c.setTime(dateStr);
        min.setTime(dateStr);
        c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE)+1,0);
        min.set(min.get(Calendar.YEAR),min.get(Calendar.MONTH),min.get(Calendar.DAY_OF_MONTH),min.get(Calendar.HOUR_OF_DAY),min.get(Calendar.MINUTE),0);
        map.put("startTime",s.format(min.getTime()));
        map.put("endTime",s.format(c.getTime()));
        return map;
    }
}
