package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 确诊上报
 *
 * @author 
 * @email
 */
@TableName("quezhen")
public class QuezhenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public QuezhenEntity() {

	}

	public QuezhenEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 确诊打卡
     */
    @ColumnInfo(comment="确诊打卡",type="varchar(200)")
    @TableField(value = "quezhen_name")

    private String quezhenName;


    /**
     * 确诊照片
     */
    @ColumnInfo(comment="确诊照片",type="varchar(200)")
    @TableField(value = "quezhen_file")

    private String quezhenFile;


    /**
     * 是否确诊
     */
    @ColumnInfo(comment="是否确诊",type="int(11)")
    @TableField(value = "quezhen_types")

    private Integer quezhenTypes;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "quezhen_text")

    private String quezhenText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "quezhen_delete")

    private Integer quezhenDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间   listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：是否确诊
	 */
    public Integer getQuezhenTypes() {
        return quezhenTypes;
    }
    /**
	 * 设置：是否确诊
	 */

    public void setQuezhenTypes(Integer quezhenTypes) {
        this.quezhenTypes = quezhenTypes;
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
	 * 获取：创建时间   listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间   listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Quezhen{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", quezhenName=" + quezhenName +
            ", quezhenFile=" + quezhenFile +
            ", quezhenTypes=" + quezhenTypes +
            ", quezhenText=" + quezhenText +
            ", quezhenDelete=" + quezhenDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
