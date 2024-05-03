package com.entity.model;

import com.entity.QuezhenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 确诊上报
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class QuezhenModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 确诊打卡
     */
    private String quezhenName;


    /**
     * 确诊照片
     */
    private String quezhenFile;


    /**
     * 备注
     */
    private String quezhenText;


    /**
     * 逻辑删除
     */
    private Integer quezhenDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：确诊打卡
	 */
    public String getQuezhenName() {
        return quezhenName;
    }


    /**
	 * 设置：确诊打卡
	 */
    public void setQuezhenName(String quezhenName) {
        this.quezhenName = quezhenName;
    }
    /**
	 * 获取：确诊照片
	 */
    public String getQuezhenFile() {
        return quezhenFile;
    }


    /**
	 * 设置：确诊照片
	 */
    public void setQuezhenFile(String quezhenFile) {
        this.quezhenFile = quezhenFile;
    }
    /**
	 * 获取：备注
	 */
    public String getQuezhenText() {
        return quezhenText;
    }


    /**
	 * 设置：备注
	 */
    public void setQuezhenText(String quezhenText) {
        this.quezhenText = quezhenText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getQuezhenDelete() {
        return quezhenDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setQuezhenDelete(Integer quezhenDelete) {
        this.quezhenDelete = quezhenDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：录入时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
