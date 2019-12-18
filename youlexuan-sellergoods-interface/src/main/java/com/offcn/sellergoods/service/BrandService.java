package com.offcn.sellergoods.service;

import java.util.List;
import java.util.Map;

import com.offcn.entity.PageResult;
import com.offcn.pojo.TbBrand;

public interface BrandService {

    List<TbBrand> findAll();

    public PageResult findPage(int pageNum, int pageSize);

    public PageResult findPage(TbBrand brand, int pageNum, int pageSize);

    public void add(TbBrand brand);

    public TbBrand findById(Long id);

    public void update(TbBrand brand);

    int batchDelete(List<Long> id);

    /**
     * 品牌下拉框数据
     */
    List<Map> selectOptionList();
}
