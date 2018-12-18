package com.p6spy.engine.autocompensate.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public interface IAutoCompensateHandler {
	
	void saveAutoCompensationInfo(PreparedStatement delegate, String executeSql, boolean isBeforeNotice, Map<String, Object> standbyParams) throws SQLException;

}
