package com.seatwe.zsws.db;

import java.sql.SQLException;
import java.sql.Savepoint;

import android.content.Context;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * 数据库事务提交
 * 调用类最后必须要执行commitTransc 和 rollBack方法，这样数据库才不会死锁
 */
public class BaseDbTransactionImpl implements IBaseDbTransaction {
	private Context context;
	private AndroidDatabaseConnection transCon;
	private String currentPoint;
	private Savepoint savePoint;

	public BaseDbTransactionImpl(String currentPoint) {
		super();
		this.currentPoint = currentPoint;
	}

	@Override
	public AndroidDatabaseConnection beginTransc() throws SQLException {
		transCon = new AndroidDatabaseConnection(OpenHelperManager.getHelper(
				context, DatabaseHelper.class).getWritableDatabase(), true);
		savePoint = transCon.setSavePoint(currentPoint);
		return transCon;
	}

	@Override
	public void commitTransc() throws SQLException {
		transCon.commit(savePoint);
	}

	@Override
	public void rollBack() throws SQLException {
		transCon.rollback(savePoint);
	}

	public AndroidDatabaseConnection getTransCon() {
		return transCon;
	}

}
