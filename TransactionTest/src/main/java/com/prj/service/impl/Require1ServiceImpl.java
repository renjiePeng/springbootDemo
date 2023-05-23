package com.prj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prj.config.CopyBeans;
import com.prj.config.PageBean;
import com.prj.config.PageResult;
import com.prj.config.Query;
import com.prj.dao.Require1Dao;
import com.prj.pojo.entity.Require1DO;
import com.prj.pojo.vo.Require1VO;
import com.prj.service.Require1Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 需求列表(Require1)表服务实现类
 *
 * @author pengrj
 * @since 2023-05-21 11:28:24
 */
@Service("require1Service")
public class Require1ServiceImpl extends ServiceImpl<Require1Dao, Require1DO> implements Require1Service {
    @Resource
    private Require1Dao require1Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Require1DO queryById(Integer id) {
        return this.require1Dao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param require1VO 筛选条件
     * @param pageBean   分页对象
     * @return 查询结果
     */
    @Override
    public PageResult<Require1DO> queryByPage(Require1VO require1VO, PageBean pageBean) {
        Require1DO require1DO = CopyBeans.copyBean(require1VO, Require1DO::new);
        QueryWrapper<Require1DO> wrapper = new QueryWrapper<>(require1DO);
        IPage<Require1DO> page = this.page(
                new Query<Require1DO>().getPage(pageBean),
                wrapper
        );
        return new PageResult<>(page.getTotal(), page.getRecords(), pageBean);
    }

    /**
     * 新增数据
     *
     * @param require1VO 实例对象
     * @return 实例对象
     */
    @Override
    public String insert(Require1VO require1VO) {
        Require1DO require1DO = CopyBeans.copyBean(require1VO, Require1DO::new);
        this.require1Dao.insert(require1DO);
        return "SUCCESS";
    }

    /**
     * 修改数据
     *
     * @param require1DO 实例对象
     * @return 实例对象
     */
    @Override
    public String update(Require1DO require1DO) {
        this.require1Dao.updateById(require1DO);
        return "SUCCESS";
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public String deleteById(Integer id) {
        this.require1Dao.deleteById(id);
        return "SUCCESS";
    }
}
