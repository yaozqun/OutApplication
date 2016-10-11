package com.seatwe.zsws.db;

import java.sql.SQLException;

import com.j256.ormlite.android.AndroidDatabaseConnection;

public interface IBaseDbTransaction {

	/**
	 * 开启事务，使用时，务必保证在事务中执行的操作不会自动提交
	 * @return
	 * @throws SQLException
	 */
	public abstract AndroidDatabaseConnection beginTransc()
			throws SQLException;

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public abstract void commitTransc() throws SQLException;

	/**
	 *  回滚事务
	 * @throws SQLException
	 */
	public abstract void rollBack() throws SQLException;
}
