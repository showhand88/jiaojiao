package com.offcn.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.offcn.mapper.TbSpecificationMapper;
import com.offcn.mapper.TbSpecificationOptionMapper;
import com.offcn.pojo.TbSpecification;
import com.offcn.pojo.TbSpecificationExample;
import com.offcn.pojo.TbSpecificationExample.Criteria;
import com.offcn.pojo.TbSpecificationOption;
import com.offcn.pojo.TbSpecificationOptionExample;
import com.offcn.sellergoods.service.SpecificationService;

import com.offcn.entity.PageResult;
import com.offcn.group.Specification;

/**
 * 閺堝秴濮熺�圭偟骞囩仦锟�
 *
 * @author Administrator
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    /**
     * 閺屻儴顕楅崗銊╁劥
     */
    @Override
    public List<TbSpecification> findAll() {
        return specificationMapper.selectByExample(null);
    }

    /**
     * 閹稿鍨庢い鍨叀鐠囷拷
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 婢х偛濮�
     */
    @Override
    public void add(TbSpecification specification) {
        specificationMapper.insert(specification);
    }

    /**
     * 娣囶喗鏁�
     */
    @Override
    public void update(TbSpecification specification) {
        specificationMapper.updateByPrimaryKey(specification);
    }

    /**
     * 閺嶈宓両D閼惧嘲褰囩�圭偘缍�
     *
     * @param id
     * @return
     */
    @Override
    public Specification findOne(Long id) {
        // 查询规格
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        // 查询规格选项列表
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);// 根据规格ID查询
        PageHelper.orderBy("orders");
        List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
        // 构建组合实体类返回结果
        Specification spec = new Specification();
        spec.setSpecification(tbSpecification);
        spec.setSpecificationOptionList(optionList);
        return spec;
    }

    /**
     * 閹靛綊鍣洪崚鐘绘珟
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            specificationMapper.deleteByPrimaryKey(id);
            //删除原有的规格选项
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            com.offcn.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);//指定规格ID为条件
            specificationOptionMapper.deleteByExample(example);//删除
        }
    }

    @Override
    public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbSpecificationExample example = new TbSpecificationExample();
        Criteria criteria = example.createCriteria();

        if (specification != null) {
            if (specification.getSpecName() != null && specification.getSpecName().length() > 0) {
                criteria.andSpecNameLike("%" + specification.getSpecName() + "%");
            }
        }


        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Specification specification) {
        // TODO Auto-generated method stub
        specificationMapper.insert(specification.getSpecification());// 插入规格
        // 循环插入规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getSpecification().getId());// 设置规格ID
            // specificationOptionMapper.insert(specificationOption);
        }
    }

    /**
     * 品牌下拉框数据
     */
    @Override
    public List<Map> selectOptionList() {
        return specificationMapper.selectOptionList();
    }

    @Override
    public void update(Specification specification) {
        // TODO Auto-generated method stub
        // 保存修改的规格
        specificationMapper.updateByPrimaryKey(specification.getSpecification());// 保存规格
        // 删除原有的规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());// 指定规格ID为条件
        specificationOptionMapper.deleteByExample(example);// 删除
        // 循环插入规格选项
        for (TbSpecificationOption specificationOption : specification.getSpecificationOptionList()) {
            specificationOption.setSpecId(specification.getSpecification().getId());
            specificationOptionMapper.insert(specificationOption);
        }
    }

}
