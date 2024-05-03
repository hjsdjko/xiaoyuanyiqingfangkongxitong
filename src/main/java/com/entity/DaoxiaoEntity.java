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
 * 到校确认
 *
 * @author 
 * @email
 */
@TableName("daoxiao")
public class DaoxiaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DaoxiaoEntity() {

	}

	public DaoxiaoEntity(T t) {
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
     * 到校
     */
    @ColumnInfo(comment="到校",type="varchar(200)")
    @TableField(value = "daoxiao_name")

    private String daoxiaoName;


    /**
     * 照片
     */
    @ColumnInfo(comment="照片",type="varchar(200)")
    @TableField(value = "daoxiao_file")

    private String daoxiaoFile;


    /**
     * 备注
     */
    @ColumnInfo(comment="备注",type="text")
    @TableField(value = "daoxiao_text")

    private String daoxiaoText;


    /**
     * 到校时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @ColumnInfo(comment="到校时间",type="date")
    @TableField(value = "insert_shijian_time")

    private Date insertShijianTime;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "daoxiao_delete")

    private Integer daoxiaoDelete;


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
	 * 获取：到校
	 */
    public String getDaoxiaoName() {
        return daoxiaoName;
    }
    /**
	 * 设置：到校
	 */

    public void setDaoxiaoName(String daoxiaoName) {
        this.daoxiaoName = daoxiaoName;
    }
    /**
	 * 获取：照片
	 */
    public String getDaoxiaoFile() {
        return daoxiaoFile;
    }
    /**
	 * 设置：照片
	 */

    public void setDaoxiaoFile(String daoxiaoFile) {
        this.daoxiaoFile = daoxiaoFile;
    }
    /**
	 * 获取：备注
	 */
    public String getDaoxiaoText() {
        return daoxiaoText;
    }
    /**
	 * 设置：备注
	 */

    public void setDaoxiaoText(String daoxiaoText) {
        this.daoxiaoText = daoxiaoText;
    }
    /**
	 * 获取：到校时间
	 */
    public Date getInsertShijianTime() {
        return insertShijianTime;
    }
    /**
	 * 设置：到校时间
	 */

    public void setInsertShijianTime(Date insertShijianTime) {
        this.insertShijianTime = insertShijianTime;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getDaoxiaoDelete() {
        return daoxiaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setDaoxiaoDelete(Integer daoxiaoDelete) {
        this.daoxiaoDelete = daoxiaoDelete;
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
        return "Daoxiao{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", daoxiaoName=" + daoxiaoName +
            ", daoxiaoFile=" + daoxiaoFile +
            ", daoxiaoText=" + daoxiaoText +
            ", insertShijianTime=" + DateUtil.convertString(insertShijianTime,"yyyy-MM-dd") +
            ", daoxiaoDelete=" + daoxiaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
