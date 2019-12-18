package com.offcn.sellergoods.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.entity.PageResult;
import com.offcn.entity.Result;
import com.offcn.pojo.TbBrand;
import com.offcn.sellergoods.service.BrandService;

@RestController // �����ص������Զ���װ��json�ṹ
@RequestMapping("brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return brandService.findPage(page, rows);
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand brand, int page, int rows) {
        return brandService.findPage(brand, page, rows);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand brand) {
        try {
            brandService.add(brand);
            return new Result(true, "�½��ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "�½�ʧ��");
        }
    }

    @RequestMapping("/findById")
    public TbBrand findById(Long id) {
        return brandService.findById(id);
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand brand) {
        try {
            brandService.update(brand);
            return new Result(true, "�޸ĳɹ�");
        } catch (Exception exception) {
            // TODO: handle exception
            exception.printStackTrace();
            return new Result(false, "�޸�ʧ��");
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            List<Long> list = Arrays.asList(ids);
            brandService.batchDelete(list);
            return new Result(true, "ɾ���ɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "ɾ��ʧ��");
        }
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList() {
        return brandService.selectOptionList();
    }
}
