package com.prj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.prj.config.PageBean;
import com.prj.config.PageResult;
import com.prj.pojo.entity.Require1DO;
import com.prj.pojo.vo.Require1VO;

/**
 * 需求列表(Require1)表服务接口
 *
 * @author pengrj
 * @since 2023-05-21 11:28:01
 */
public interface Require1Service extends IService<Require1DO> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Require1DO queryById(Integer id);

    /**
     * 分页查询
     *
     * @param require1VO 筛选条件
     * @param pageBean   分页对象
     * @return 查询结果
     */
    PageResult<Require1DO> queryByPage(Require1VO require1VO, PageBean pageBean);

    /**
     * 新增数据
     *
     * @param require1VO 实例对象
     * @return 实例对象
     */
    String insert(Require1VO require1VO);

    /**
     * 修改数据
     *
     * @param require1DO 实例对象
     * @return 实例对象
     */
    String update(Require1DO require1DO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    String deleteById(Integer id);

}
