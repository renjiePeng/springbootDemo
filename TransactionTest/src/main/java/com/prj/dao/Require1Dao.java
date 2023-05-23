package com.prj.dao;

import com.prj.pojo.entity.Require1DO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


/**
 * 需求列表(Require1)表数据库访问层
 *
 * @author pengrj
 * @since 2023-05-21 11:27:15
 */
@Mapper
public interface Require1Dao extends BaseMapper<Require1DO> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Require1DO queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param require1DO 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    //List<Require1DO> queryAllByLimit(Require1DO require1DO, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param require1DO 查询条件
     * @return 总行数
     */
    long count(Require1DO require1DO);

    /**
     * 新增数据
     *
     * @param require1DO 实例对象
     * @return 影响行数
     */
    int insert(Require1DO require1DO);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Require1DO> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Require1DO> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Require1> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Require1DO> entities);

    /**
     * 修改数据
     *
     * @param require1DO 实例对象
     * @return 影响行数
     */
    int update(Require1DO require1DO);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

