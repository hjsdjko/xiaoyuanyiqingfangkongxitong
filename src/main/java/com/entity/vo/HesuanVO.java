package com.entity.vo;

import com.entity.HesuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 核酸上报
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("hesuan")
public class HesuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 核酸打卡
     */

    @TableField(value = "hesuan_name")
    private String hesuanName;


    /**
     * 核酸照片
     */

    @TableField(value = "hesuan_file")
    private String hesuanFile;


    /**
     * 备注
     */

    @TableField(value = "hesuan_text")
    private String hesuanText;


    /**
     * 逻辑删除
     */

    @TableField(value = "hesuan_delete")
    private Integer hesuanDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：核酸打卡
	 */
    public String getHesuanName() {
        return hesuanName;
    }


    /**
	 * 获取：核酸打卡
	 */

    public void setHesuanName(String hesuanName) {
        this.hesuanName = hesuanName;
    }
    /**
	 * 设置：核酸照片
	 */
    public String getHesuanFile() {
        return hesuanFile;
    }


    /**
	 * 获取：核酸照片
	 */

    public void setHesuanFile(String hesuanFile) {
        this.hesuanFile = hesuanFile;
    }
    /**
	 * 设置：备注
	 */
    public String getHesuanText() {
        return hesuanText;
    }


    /**
	 * 获取：备注
	 */

    public void setHesuanText(String hesuanText) {
        this.hesuanText = hesuanText;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getHesuanDelete() {
        return hesuanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setHesuanDelete(Integer hesuanDelete) {
        this.hesuanDelete = hesuanDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
