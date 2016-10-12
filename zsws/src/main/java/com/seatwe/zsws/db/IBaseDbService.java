package com.seatwe.zsws.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：EscortWebFwh
 * 
 * 类描述：业务实现基类接口 类名称：com.grgbanking.escortfwh.framework.base.service.BaseService
 * 创建人：G0210181 创建时间：2015-7-22 下午4:25:29 修改人： 修改时间：2015-7-22 下午4:25:29 修改备注：
 * 
 * @version V1.0
 */
public interface IBaseDbService<TEntity, ID> {
    /**
     * 根据主键ID查询
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract TEntity queryForId(ID paramID) throws SQLException;

    /**
     * 查询全部
     * @return
     * @throws SQLException
     */
    public abstract List<TEntity> queryForAll() throws SQLException;

    /**
     * 根据条件查询
     * @param paramString
     * @param paramObject
     * @return
     * @throws SQLException
     */
    public abstract List<TEntity> queryForEq(String paramString, Object paramObject) throws SQLException;

    /**
     * 根据条件查询
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract List<TEntity> queryForMatching(TEntity paramT) throws SQLException;

    /**
     * 根据实体类条件查询
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract List<TEntity> queryForMatchingArgs(TEntity paramT) throws SQLException;

    /**
     * 根据实体类删除
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int delete(TEntity paramT) throws SQLException;

    /**
     * 根据主键ID删除
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract int deleteById(ID paramID) throws SQLException;

    /**
     * 删除多条记录
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int delete(Collection<TEntity> paramCollection) throws SQLException;

    /**
     * 删除多条记录
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int deleteIds(Collection<ID> paramCollection) throws SQLException;

    /**
     * 获取总条数
     * @return
     * @throws SQLException
     */
    public abstract long countOf() throws SQLException;

    /**
     * 插入记录
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int create(TEntity paramT) throws SQLException;

    /**
     * 插入多条记录
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int create(Collection<TEntity> paramCollection) throws SQLException;

    /**
     * 插入或更新
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract CreateOrUpdateStatus createOrUpdate(TEntity paramT) throws SQLException;

    /**
     * 更新
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int update(TEntity paramT) throws SQLException;

    /**
     * 根据主键ID更新记录
     * @param paramT
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract int updateId(TEntity paramT, ID paramID) throws SQLException;

   /* *//**
     * 
     * @Title: beanConvertEntity
     * @Description: 实体类转换为Entity
     * @param paramTEntity
     * @param paramTBean
     * @return
     *//*
    public abstract TEntity beanConvertEntity(TEntity paramTEntity, TBean paramTBean);*/

   /* *//**
     * 
     * @Title: beanConvertListEntity
     * @Description:实体类转换为list Entity
     * @param paramTListBean
     * @return
     *//*
    public abstract List<TEntity> beanConvertListEntity(List<TEntity> paramTListEntity, List<TBean> paramTListBean);*/

    /**
     * 
     * @Title: initDao
     * @Description: 自定义dao层 初始化dao
     * @param classEntity
     */
    public abstract void initDao(Class<TEntity> classEntity);

    /**
     * 
     * @Title: initDao
     * @Description: 自定义dao层 初始化dao
     * @param classEntity
     * @param baseDbTransactionImpl
     */
    public abstract void initDao(Class<TEntity> classEntity, BaseDbTransactionImpl baseDbTransactionImpl);



}
