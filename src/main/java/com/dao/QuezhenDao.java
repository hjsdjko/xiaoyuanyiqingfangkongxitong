package com.dao;

import com.entity.QuezhenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.QuezhenView;

/**
 * 确诊上报 Dao 接口
 *
 * @author 
 */
public interface QuezhenDao extends BaseMapper<QuezhenEntity> {

   List<QuezhenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
