package com.prj.controller;

import com.prj.config.PageBean;
import com.prj.config.PageResult;
import com.prj.config.R;
import com.prj.pojo.entity.Require1DO;
import com.prj.pojo.vo.Require1VO;
import com.prj.service.Require1Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 需求列表(Require1)表控制层
 *
 * @author pengrj
 * @since 2023-05-21 11:27:15
 */
@Api(tags = "需求列表")
@RestController
@RequestMapping("require1")
public class Require1Controller {
    /**
     * 服务对象
     */
    @Resource
    private Require1Service require1Service;

    /**
     * 分页查询
     *
     * @param require1VO 筛选条件
     * @param pageBean   分页对象
     * @return 查询结果
     */
    @PostMapping
    @ApiOperation("分页查询")
    public PageResult<Require1DO> queryByPage(@RequestBody Require1VO require1VO, PageBean pageBean) {
        return this.require1Service.queryByPage(require1VO, pageBean);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("通过主键查询单条数据")
    public R queryById(@PathVariable("id") Integer id) {
        return R.ok(this.require1Service.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param require1VO 实体
     * @return 新增结果
     */
    @ApiOperation("新增数据")
    @PostMapping("/add")
    public R add(@RequestBody Require1VO require1VO) {
        return R.ok(this.require1Service.insert(require1VO));
    }

    /**
     * 编辑数据
     *
     * @param require1DO 实体
     * @return 编辑结果
     */
    @ApiOperation("编辑数据")
    @PostMapping("/edit")
    public R edit(@RequestBody Require1DO require1DO) {
        return R.ok(this.require1Service.update(require1DO));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @ApiOperation("删除数据")
    @DeleteMapping
    public R deleteById(Integer id) {
        return R.ok(this.require1Service.deleteById(id));
    }

}

