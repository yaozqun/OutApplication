package com.seatwe.zsws.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.seatwe.zsws.MyApplication;

public class BaseDbDaoImpl<T> implements IBaseDbDao<T, Integer> {

    protected Dao<T, Integer> dao;

    protected QueryBuilder<T, Integer> queryBuilder;
    protected DeleteBuilder<T, Integer> deleteBuilder;
    protected UpdateBuilder<T, Integer> updateBuilder;

    /**
     * 默认构造方法
     * @param classOfT
     */
    public BaseDbDaoImpl(Class<T> classOfT) {
        super();
        try {
            dao = OpenHelperManager.getHelper(MyApplication.getContext(), DatabaseHelper.class).getDao(classOfT);
            queryBuilder = dao.queryBuilder();
            deleteBuilder = dao.deleteBuilder();
            updateBuilder = dao.updateBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 事务--构造方法
     * @param classOfT
     * @param baseDbTransactionImpl
     */
    public BaseDbDaoImpl(Class<T> classOfT, BaseDbTransactionImpl baseDbTransactionImpl) {
        super();
        try {
            dao = OpenHelperManager.getHelper(MyApplication.getContext(), DatabaseHelper.class).getDao(classOfT);
            baseDbTransactionImpl.beginTransc();
            dao.setAutoCommit(baseDbTransactionImpl.getTransCon(), false);
            queryBuilder = dao.queryBuilder();
            deleteBuilder = dao.deleteBuilder();
            updateBuilder = dao.updateBuilder();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T queryForId(Integer paramID) throws SQLException {
        return dao.queryForId(paramID);
    }

    @Override
    public List<T> queryForAll() throws SQLException {
        return dao.queryForAll();
    }

    @Override
    public List<T> queryForEq(String paramString, Object paramObject) throws SQLException {
        return dao.queryForEq(paramString, paramObject);
    }

    @Override
    public List<T> queryForMatching(T paramT) throws SQLException {
        return dao.queryForMatching(paramT);
    }

    @Override
    public List<T> queryForMatchingArgs(T paramT) throws SQLException {
        return dao.queryForMatchingArgs(paramT);
    }

    @Override
    public int delete(T paramT) throws SQLException {
        return dao.delete(paramT);
    }

    @Override
    public int deleteById(Integer paramID) throws SQLException {
        return dao.deleteById(paramID);
    }

    @Override
    public int delete(Collection<T> paramCollection) throws SQLException {
        return dao.delete(paramCollection);
    }

    @Override
    public int deleteIds(Collection<Integer> paramCollection) throws SQLException {
        return dao.deleteIds(paramCollection);
    }

    @Override
    public long countOf() throws SQLException {
        return dao.countOf();
    }

    @Override
    public int create(T paramT) throws SQLException {
        return dao.create(paramT);
    }

    @Override
    public int create(Collection<T> paramCollection) throws SQLException {
        return dao.create(paramCollection);
    }

    @Override
    public CreateOrUpdateStatus createOrUpdate(T paramT) throws SQLException {
        return dao.createOrUpdate(paramT);
    }

    @Override
    public int update(T paramT) throws SQLException {
        return dao.update(paramT);
    }

    @Override
    public int updateId(T paramT, Integer paramID) throws SQLException {
        return dao.updateId(paramT, paramID);
    }

    @Override
    public QueryBuilder<T, Integer> getQueryBuilder() {
        return queryBuilder;
    }
    @Override
    public DeleteBuilder<T, Integer> getDeleteBuilder() {
        return deleteBuilder;
    }
    @Override
    public UpdateBuilder<T, Integer> getUpdateBuilder() {
        return updateBuilder;
    }

}
