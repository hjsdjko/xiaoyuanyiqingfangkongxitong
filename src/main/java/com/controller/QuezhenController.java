
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 确诊上报
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/quezhen")
public class QuezhenController {
    private static final Logger logger = LoggerFactory.getLogger(QuezhenController.class);

    private static final String TABLE_NAME = "quezhen";

    @Autowired
    private QuezhenService quezhenService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DakaService dakaService;//健康上报
    @Autowired
    private DaoxiaoService daoxiaoService;//到校确认
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//公告
    @Autowired
    private HesuanService hesuanService;//核酸上报
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private NewsService newsService;//疫情知识信息
    @Autowired
    private YaopinService yaopinService;//药品
    @Autowired
    private YaopinOrderService yaopinOrderService;//药品订单
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        params.put("quezhenDeleteStart",1);params.put("quezhenDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = quezhenService.queryPage(params);

        //字典表数据转换
        List<QuezhenView> list =(List<QuezhenView>)page.getList();
        for(QuezhenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QuezhenEntity quezhen = quezhenService.selectById(id);
        if(quezhen !=null){
            //entity转view
            QuezhenView view = new QuezhenView();
            BeanUtils.copyProperties( quezhen , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(quezhen.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody QuezhenEntity quezhen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,quezhen:{}",this.getClass().getName(),quezhen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            quezhen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<QuezhenEntity> queryWrapper = new EntityWrapper<QuezhenEntity>()
            .eq("yonghu_id", quezhen.getYonghuId())
            .eq("quezhen_name", quezhen.getQuezhenName())
            .eq("quezhen_types", quezhen.getQuezhenTypes())
            .eq("quezhen_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QuezhenEntity quezhenEntity = quezhenService.selectOne(queryWrapper);
        if(quezhenEntity==null){
            quezhen.setQuezhenDelete(1);
            quezhen.setInsertTime(new Date());
            quezhen.setCreateTime(new Date());
            quezhenService.insert(quezhen);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody QuezhenEntity quezhen, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,quezhen:{}",this.getClass().getName(),quezhen.toString());
        QuezhenEntity oldQuezhenEntity = quezhenService.selectById(quezhen.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            quezhen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(quezhen.getQuezhenFile()) || "null".equals(quezhen.getQuezhenFile())){
                quezhen.setQuezhenFile(null);
        }

            quezhenService.updateById(quezhen);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<QuezhenEntity> oldQuezhenList =quezhenService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<QuezhenEntity> list = new ArrayList<>();
        for(Integer id:ids){
            QuezhenEntity quezhenEntity = new QuezhenEntity();
            quezhenEntity.setId(id);
            quezhenEntity.setQuezhenDelete(2);
            list.add(quezhenEntity);
        }
        if(list != null && list.size() >0){
            quezhenService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<QuezhenEntity> quezhenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            QuezhenEntity quezhenEntity = new QuezhenEntity();
//                            quezhenEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            quezhenEntity.setQuezhenName(data.get(0));                    //确诊打卡 要改的
//                            quezhenEntity.setQuezhenFile(data.get(0));                    //确诊照片 要改的
//                            quezhenEntity.setQuezhenTypes(Integer.valueOf(data.get(0)));   //是否确诊 要改的
//                            quezhenEntity.setQuezhenText(data.get(0));                    //备注 要改的
//                            quezhenEntity.setQuezhenDelete(1);//逻辑删除字段
//                            quezhenEntity.setInsertTime(date);//时间
//                            quezhenEntity.setCreateTime(date);//时间
                            quezhenList.add(quezhenEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        quezhenService.insertBatch(quezhenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = quezhenService.queryPage(params);

        //字典表数据转换
        List<QuezhenView> list =(List<QuezhenView>)page.getList();
        for(QuezhenView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        QuezhenEntity quezhen = quezhenService.selectById(id);
            if(quezhen !=null){


                //entity转view
                QuezhenView view = new QuezhenView();
                BeanUtils.copyProperties( quezhen , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(quezhen.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody QuezhenEntity quezhen, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,quezhen:{}",this.getClass().getName(),quezhen.toString());
        Wrapper<QuezhenEntity> queryWrapper = new EntityWrapper<QuezhenEntity>()
            .eq("yonghu_id", quezhen.getYonghuId())
            .eq("quezhen_name", quezhen.getQuezhenName())
            .eq("quezhen_types", quezhen.getQuezhenTypes())
            .eq("quezhen_text", quezhen.getQuezhenText())
            .eq("quezhen_delete", quezhen.getQuezhenDelete())
//            .notIn("quezhen_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        QuezhenEntity quezhenEntity = quezhenService.selectOne(queryWrapper);
        if(quezhenEntity==null){
            quezhen.setQuezhenDelete(1);
            quezhen.setInsertTime(new Date());
            quezhen.setCreateTime(new Date());
        quezhenService.insert(quezhen);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

