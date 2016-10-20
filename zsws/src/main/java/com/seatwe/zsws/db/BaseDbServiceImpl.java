package com.seatwe.zsws.db;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;

/**
 * 
 * 版权所有：2015-GRGBANKING 项目名称：EscortWebFwh
 * 
 * 类描述：业务实现基类
 * 类名称：com.grgbanking.escortfwh.framework.base.service.BaseServiceImpl
 * 创建人：G0210181 创建时间：2015-7-22 下午4:25:11 修改人： 修改时间：2015-7-22 下午4:25:11 修改备注：
 * 
 * @version V1.0
 */
public class BaseDbServiceImpl<TEntity> implements IBaseDbService<TEntity, Integer> {

    protected IBaseDbDao<TEntity, Integer> dao;
    /**
     * 默认构造方法
     * @param classEntity
     */
    public BaseDbServiceImpl(Class<TEntity> classEntity) {
        super();
        initDao(classEntity);
    }

    /**
     *事务--构造方法
     * @param classEntity
     * @param baseDbTransactionImpl
     */
    public BaseDbServiceImpl(Class<TEntity> classEntity, BaseDbTransactionImpl baseDbTransactionImpl) {
        super();
        initDao(classEntity, baseDbTransactionImpl);
    }

    @Override
    public TEntity queryForId(Integer paramID) throws SQLException {
        return dao.queryForId(paramID);
    }

    @Override
    public List<TEntity> queryForAll() throws SQLException {
        return dao.queryForAll();
    }

    @Override
    public List<TEntity> queryForEq(String paramString, Object paramObject) throws SQLException {
        return dao.queryForEq(paramString, paramObject);
    }

    @Override
    public List<TEntity> queryForMatching(TEntity paramT) throws SQLException {
        return dao.queryForMatching(paramT);
    }

    @Override
    public List<TEntity> queryForMatchingArgs(TEntity paramT) throws SQLException {
        return dao.queryForMatchingArgs(paramT);
    }

    @Override
    public int delete(TEntity paramT) throws SQLException {
        return dao.delete(paramT);
    }

    @Override
    public int deleteById(Integer paramID) throws SQLException {
        return dao.deleteById(paramID);
    }

    @Override
    public int delete(Collection<TEntity> paramCollection) throws SQLException {
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
    public int create(TEntity paramT) throws SQLException {
        return dao.create(paramT);
    }

    @Override
    public int create(Collection<TEntity> paramCollection) throws SQLException {
        return dao.create(paramCollection);
    }

    @Override
    public CreateOrUpdateStatus createOrUpdate(TEntity paramT) throws SQLException {
        return dao.createOrUpdate(paramT);
    }

    @Override
    public int update(TEntity paramT) throws SQLException {
        return dao.update(paramT);
    }

    @Override
    public int updateId(TEntity paramT, Integer paramID) throws SQLException {
        return dao.updateId(paramT, paramID);
    }

/*    @Override
    public TEntity beanConvertEntity(TEntity paramTEntity, TBean paramTBean) {
        try {
            BeanCopyUtil.copy(paramTEntity, paramTBean);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return paramTEntity;
    }*/

 /*   @Override
    public List<TEntity> beanConvertListEntity(List<TEntity> paramTListEntity, List<TBean> paramTListBean) {
        for (int i = 0; i < paramTListBean.size(); i++) {
            beanConvertEntity(paramTListEntity.get(i), paramTListBean.get(i));
        }
        return paramTListEntity;
    }*/

    @Override
    public void initDao(Class<TEntity> classEntity) {
        dao = new BaseDbDaoImpl<TEntity>(classEntity);
    }

    @Override
    public void initDao(Class<TEntity> classEntity, BaseDbTransactionImpl baseDbTransactionImpl) {
        dao = new BaseDbDaoImpl<TEntity>(classEntity, baseDbTransactionImpl);
    }
}
