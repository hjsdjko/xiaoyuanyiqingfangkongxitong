package com.entity.model;

import com.entity.HesuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 核酸上报
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HesuanModel implements Serializable {
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
     * 核酸打卡
     */
    private String hesuanName;


    /**
     * 核酸照片
     */
    private String hesuanFile;


    /**
     * 备注
     */
    private String hesuanText;


    /**
     * 逻辑删除
     */
    private Integer hesuanDelete;


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
