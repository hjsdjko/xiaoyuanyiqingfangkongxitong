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
 * 核酸上报
 *
 * @author 
 * @email
 */
@TableName("hesuan")
public class HesuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HesuanEntity() {

	}

	public HesuanEntity(T t) {
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
     * 核酸打卡
     */
    @ColumnInfo(comment="核酸打卡",type="varchar(200)")
    @TableField(value = "hesuan_name")

    private String hesuanName;


    /**
     * 核酸照片
     */
    @ColumnInfo(comment="核酸照片",type="varchar(200)")
    @TableField(value = "hesuan_file")

    private String hesuanFile;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "hesuan_text")

    private String hesuanText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "hesuan_delete")

    private Integer hesuanDelete;


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
	 * 获取：核酸打卡
	 */
    public String getHesuanName() {
        return hesuanName;
    }
    /**
	 * 设置：核酸打卡
	 */

    public void setHesuanName(String hesuanName) {
        this.hesuanName = hesuanName;
    }
    /**
	 * 获取：核酸照片
	 */
    public String getHesuanFile() {
        return hesuanFile;
    }
    /**
	 * 设置：核酸照片
	 */

    public void setHesuanFile(String hesuanFile) {
        this.hesuanFile = hesuanFile;
    }
    /**
	 * 获取：备注
	 */
    public String getHesuanText() {
        return hesuanText;
    }
    /**
	 * 设置：备注
	 */

    public void setHesuanText(String hesuanText) {
        this.hesuanText = hesuanText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHesuanDelete() {
        return hesuanDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setHesuanDelete(Integer hesuanDelete) {
        this.hesuanDelete = hesuanDelete;
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
        return "Hesuan{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", hesuanName=" + hesuanName +
            ", hesuanFile=" + hesuanFile +
            ", hesuanText=" + hesuanText +
            ", hesuanDelete=" + hesuanDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}