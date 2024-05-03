package com.dao;

import com.entity.DaoxiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaoxiaoView;

/**
 * 到校确认 Dao 接口
 *
 * @author 
 */
public interface DaoxiaoDao extends BaseMapper<DaoxiaoEntity> {

   List<DaoxiaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
