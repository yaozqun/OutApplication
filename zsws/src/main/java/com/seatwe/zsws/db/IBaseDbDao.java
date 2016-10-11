package com.seatwe.zsws.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

public interface IBaseDbDao<T, ID> {

    /**
     * 根据主键ID查询
     * 
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract T queryForId(ID paramID) throws SQLException;

    /**
     * 查询全部
     * 
     * @return
     * @throws SQLException
     */
    public abstract List<T> queryForAll() throws SQLException;

    /**
     * 根据条件查询
     * 
     * @param paramString
     * @param paramObject
     * @return
     * @throws SQLException
     */
    public abstract List<T> queryForEq(String paramString, Object paramObject) throws SQLException;

    /**
     * 根据条件查询
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract List<T> queryForMatching(T paramT) throws SQLException;

    /**
     * 根据实体类条件查询
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract List<T> queryForMatchingArgs(T paramT) throws SQLException;

    /**
     * 根据实体类删除
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int delete(T paramT) throws SQLException;

    /**
     * 根据主键ID删除
     * 
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract int deleteById(ID paramID) throws SQLException;

    /**
     * 删除多条记录
     * 
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int delete(Collection<T> paramCollection) throws SQLException;

    /**
     * 删除多条记录
     * 
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int deleteIds(Collection<ID> paramCollection) throws SQLException;

    /**
     * 获取总条数
     * 
     * @return
     * @throws SQLException
     */
    public abstract long countOf() throws SQLException;

    /**
     * 插入记录
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int create(T paramT) throws SQLException;

    /**
     * 插入多条记录
     * 
     * @param paramCollection
     * @return
     * @throws SQLException
     */
    public abstract int create(Collection<T> paramCollection) throws SQLException;

    /**
     * 插入或更新
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract CreateOrUpdateStatus createOrUpdate(T paramT) throws SQLException;

    /**
     * 更新
     * 
     * @param paramT
     * @return
     * @throws SQLException
     */
    public abstract int update(T paramT) throws SQLException;

    /**
     * 根据主键ID更新记录
     * 
     * @param paramT
     * @param paramID
     * @return
     * @throws SQLException
     */
    public abstract int updateId(T paramT, ID paramID) throws SQLException;

    /**
     * 
     * @Title: getQueryBuilder
     * @Description: 查询QueryBuilder
     * @return
     */
    public abstract QueryBuilder<T, Integer> getQueryBuilder();
    /**
     * 
     * @Title: getDeleteBuilder
     * @Description: 删除DeleteBuilder
     * @return
     */
    public abstract DeleteBuilder<T, Integer> getDeleteBuilder();
    /**
     * 
     * @Title: getUpdateBuilder
     * @Description: 更新UpdateBuilder
     * @return
     */
    public abstract UpdateBuilder<T, Integer> getUpdateBuilder();
}
