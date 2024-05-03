
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
 * 到校确认
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daoxiao")
public class DaoxiaoController {
    private static final Logger logger = LoggerFactory.getLogger(DaoxiaoController.class);

    private static final String TABLE_NAME = "daoxiao";

    @Autowired
    private DaoxiaoService daoxiaoService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DakaService dakaService;//健康上报
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
    private QuezhenService quezhenService;//确诊上报
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
        params.put("daoxiaoDeleteStart",1);params.put("daoxiaoDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = daoxiaoService.queryPage(params);

        //字典表数据转换
        List<DaoxiaoView> list =(List<DaoxiaoView>)page.getList();
        for(DaoxiaoView c:list){
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
        DaoxiaoEntity daoxiao = daoxiaoService.selectById(id);
        if(daoxiao !=null){
            //entity转view
            DaoxiaoView view = new DaoxiaoView();
            BeanUtils.copyProperties( daoxiao , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(daoxiao.getYonghuId());
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
    public R save(@RequestBody DaoxiaoEntity daoxiao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daoxiao:{}",this.getClass().getName(),daoxiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            daoxiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<DaoxiaoEntity> queryWrapper = new EntityWrapper<DaoxiaoEntity>()
            .eq("yonghu_id", daoxiao.getYonghuId())
            .eq("daoxiao_name", daoxiao.getDaoxiaoName())
            .eq("insert_shijian_time", new SimpleDateFormat("yyyy-MM-dd").format(daoxiao.getInsertShijianTime()))
            .eq("daoxiao_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaoxiaoEntity daoxiaoEntity = daoxiaoService.selectOne(queryWrapper);
        if(daoxiaoEntity==null){
            daoxiao.setDaoxiaoDelete(1);
            daoxiao.setInsertTime(new Date());
            daoxiao.setCreateTime(new Date());
            daoxiaoService.insert(daoxiao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaoxiaoEntity daoxiao, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,daoxiao:{}",this.getClass().getName(),daoxiao.toString());
        DaoxiaoEntity oldDaoxiaoEntity = daoxiaoService.selectById(daoxiao.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            daoxiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(daoxiao.getDaoxiaoFile()) || "null".equals(daoxiao.getDaoxiaoFile())){
                daoxiao.setDaoxiaoFile(null);
        }

            daoxiaoService.updateById(daoxiao);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<DaoxiaoEntity> oldDaoxiaoList =daoxiaoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<DaoxiaoEntity> list = new ArrayList<>();
        for(Integer id:ids){
            DaoxiaoEntity daoxiaoEntity = new DaoxiaoEntity();
            daoxiaoEntity.setId(id);
            daoxiaoEntity.setDaoxiaoDelete(2);
            list.add(daoxiaoEntity);
        }
        if(list != null && list.size() >0){
            daoxiaoService.updateBatchById(list);
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
            List<DaoxiaoEntity> daoxiaoList = new ArrayList<>();//上传的东西
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
                            DaoxiaoEntity daoxiaoEntity = new DaoxiaoEntity();
//                            daoxiaoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            daoxiaoEntity.setDaoxiaoName(data.get(0));                    //到校 要改的
//                            daoxiaoEntity.setDaoxiaoFile(data.get(0));                    //照片 要改的
//                            daoxiaoEntity.setDaoxiaoText(data.get(0));                    //备注 要改的
//                            daoxiaoEntity.setInsertShijianTime(sdf.parse(data.get(0)));          //到校时间 要改的
//                            daoxiaoEntity.setDaoxiaoDelete(1);//逻辑删除字段
//                            daoxiaoEntity.setInsertTime(date);//时间
//                            daoxiaoEntity.setCreateTime(date);//时间
                            daoxiaoList.add(daoxiaoEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        daoxiaoService.insertBatch(daoxiaoList);
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
        PageUtils page = daoxiaoService.queryPage(params);

        //字典表数据转换
        List<DaoxiaoView> list =(List<DaoxiaoView>)page.getList();
        for(DaoxiaoView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DaoxiaoEntity daoxiao = daoxiaoService.selectById(id);
            if(daoxiao !=null){


                //entity转view
                DaoxiaoView view = new DaoxiaoView();
                BeanUtils.copyProperties( daoxiao , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(daoxiao.getYonghuId());
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
    public R add(@RequestBody DaoxiaoEntity daoxiao, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,daoxiao:{}",this.getClass().getName(),daoxiao.toString());
        Wrapper<DaoxiaoEntity> queryWrapper = new EntityWrapper<DaoxiaoEntity>()
            .eq("yonghu_id", daoxiao.getYonghuId())
            .eq("daoxiao_name", daoxiao.getDaoxiaoName())
            .eq("daoxiao_text", daoxiao.getDaoxiaoText())
            .eq("daoxiao_delete", daoxiao.getDaoxiaoDelete())
//            .notIn("daoxiao_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaoxiaoEntity daoxiaoEntity = daoxiaoService.selectOne(queryWrapper);
        if(daoxiaoEntity==null){
            daoxiao.setDaoxiaoDelete(1);
            daoxiao.setInsertTime(new Date());
            daoxiao.setCreateTime(new Date());
        daoxiaoService.insert(daoxiao);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

