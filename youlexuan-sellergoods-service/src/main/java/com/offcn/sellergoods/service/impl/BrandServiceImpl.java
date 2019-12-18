package com.offcn.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.entity.PageResult;
import com.offcn.mapper.TbBrandMapper;
import com.offcn.pojo.TbBrand;
import com.offcn.pojo.TbBrandExample;
import com.offcn.pojo.TbBrandExample.Criteria;
import com.offcn.sellergoods.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        // TODO Auto-generated method stub
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult findPage(TbBrand brand, int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        TbBrandExample example = new TbBrandExample();
        Criteria criteria = example.createCriteria();
        if (brand != null) {
            if (brand.getName() != null && brand.getName().length() > 0) {
                criteria.andNameLike("%" + brand.getName() + "%");
            }
            if (brand.getFirstChar() != null && brand.getFirstChar().length() > 0) {
                criteria.andFirstCharEqualTo(brand.getFirstChar());
            }
        }
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        // TODO Auto-generated method stub
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbBrand brand) {
        // TODO Auto-generated method stub
        brandMapper.insertSelective(brand);
    }

    @Override
    public TbBrand findById(Long id) {
        // TODO Auto-generated method stub
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbBrand brand) {
        // TODO Auto-generated method stub
        brandMapper.updateByPrimaryKey(brand);
    }

    @Override
    public int batchDelete(List<Long> id) {
        // TODO Auto-generated method stub
        return brandMapper.batchDelete(id);
    }

    /**
     * 品牌下拉框数据
     */
    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
        
    }

}
