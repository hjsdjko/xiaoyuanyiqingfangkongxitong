package com.entity.vo;

import com.entity.DaoxiaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 到校确认
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("daoxiao")
public class DaoxiaoVO implements Serializable {
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
     * 到校
     */

    @TableField(value = "daoxiao_name")
    private String daoxiaoName;


    /**
     * 照片
     */

    @TableField(value = "daoxiao_file")
    private String daoxiaoFile;


    /**
     * 备注
     */

    @TableField(value = "daoxiao_text")
    private String daoxiaoText;


    /**
     * 到校时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_shijian_time")
    private Date insertShijianTime;


    /**
     * 逻辑删除
     */

    @TableField(value = "daoxiao_delete")
    private Integer daoxiaoDelete;


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
	 * 设置：到校
	 */
    public String getDaoxiaoName() {
        return daoxiaoName;
    }


    /**
	 * 获取：到校
	 */

    public void setDaoxiaoName(String daoxiaoName) {
        this.daoxiaoName = daoxiaoName;
    }
    /**
	 * 设置：照片
	 */
    public String getDaoxiaoFile() {
        return daoxiaoFile;
    }


    /**
	 * 获取：照片
	 */

    public void setDaoxiaoFile(String daoxiaoFile) {
        this.daoxiaoFile = daoxiaoFile;
    }
    /**
	 * 设置：备注
	 */
    public String getDaoxiaoText() {
        return daoxiaoText;
    }


    /**
	 * 获取：备注
	 */

    public void setDaoxiaoText(String daoxiaoText) {
        this.daoxiaoText = daoxiaoText;
    }
    /**
	 * 设置：到校时间
	 */
    public Date getInsertShijianTime() {
        return insertShijianTime;
    }


    /**
	 * 获取：到校时间
	 */

    public void setInsertShijianTime(Date insertShijianTime) {
        this.insertShijianTime = insertShijianTime;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getDaoxiaoDelete() {
        return daoxiaoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setDaoxiaoDelete(Integer daoxiaoDelete) {
        this.daoxiaoDelete = daoxiaoDelete;
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
