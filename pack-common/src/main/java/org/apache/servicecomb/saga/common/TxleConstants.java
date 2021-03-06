/*
 * Copyright (c) 2018-2019 ActionTech.
 * License: http://www.apache.org/licenses/LICENSE-2.0 Apache License 2.0 or higher.
 */

package org.apache.servicecomb.saga.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TxleConstants {
    public static final String APP_NAME = "txle";
    public static final String LOG_PREFIX = "[txle info] ";
    public static final String LOG_DEBUG_PREFIX = "[txle debug] ";
    public static final String LOG_ERROR_PREFIX = "[txle error] ";
    public static final String ACTION_SQL = " /**txle_sql**/";
    // Usage in org.apache.servicecomb.saga.alpha.server.GrpcTxEventEndpointImpl.onTxEvent
    public static final String SPECIAL_KEY = "txle-special-key";
    public static final String STRING_SEPARATOR = "_@@_";

    public static final String OK = "ok";
    public static final String ERROR = "error";
    public static final String ENABLED = "enabled";
    public static final String DISABLED = "disabled";
    public static final int YES = 1;
    public static final int NO = 0;

    public static final String AUTO_COMPENSABLE_METHOD = "public boolean org.apache.servicecomb.saga.omega.transaction.AutoCompensateService.executeAutoCompensateByLocalTxId(java.lang.String,java.lang.String)";

    public static final String CONSUL_LEADER_KEY = APP_NAME + "/service/leader";
    public static final String CONSUL_LEADER_KEY_VALUE = "leader election key for " + TxleConstants.APP_NAME + " service";
    public static final String TXLE_CONFIG_KEY = APP_NAME + "/service/config/";
    public static final String TXLE_TX_KEY = APP_NAME + "/service/tx/";

    public enum Operation {
        DONOTHING,
        SUCCESS,
        FAIL
    }

    private TxleConstants() {
    }

    public static String logPrefixWithTime() {
        return logPrefixWithTime(LOG_PREFIX);
    }

    public static String logDebugPrefixWithTime() {
        return logPrefixWithTime(LOG_DEBUG_PREFIX);
    }

    public static String logErrorPrefixWithTime() {
        return logPrefixWithTime(LOG_ERROR_PREFIX);
    }

    private static String logPrefixWithTime(String type) {
        if (!LOG_DEBUG_PREFIX.equals(type) && !LOG_ERROR_PREFIX.equals(type)) {
            type = LOG_PREFIX;
        }
        type = type.substring(0, type.length() - 2) + " ";
        return type + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) + "] ";
    }

    public static String constructTxCacheKey(String globalTxId) {
        return TXLE_TX_KEY + globalTxId;
    }

    public static String constructTxStatusCacheKey(String globalTxId) {
        return TXLE_TX_KEY + globalTxId + "/status";
    }

    public static String constructTxConfigCacheKey(String globalTxId) {
        return TXLE_TX_KEY + globalTxId + "/config";
    }

    public static String constructGlobalConfigValueKey(String instanceId, String category, ConfigCenterType type) {
        StringBuilder key = new StringBuilder(TXLE_CONFIG_KEY);
        if (instanceId != null && instanceId.length() > 0) {
            key.append(instanceId + "/");
            if (category != null && category.length() > 0) {
                key.append(category + "/");
            }
        }
        key.append(type.toString() + "/value");
        return key.toString();
    }

    public static String constructGlobalConfigAbilityKey(String instanceId, String category, ConfigCenterType type) {
        StringBuilder key = new StringBuilder(TXLE_CONFIG_KEY);
        if (instanceId != null && instanceId.length() > 0) {
            key.append(instanceId + "/");
            if (category != null && category.length() > 0) {
                key.append(category + "/");
            }
        }
        key.append(type.toString() + "/ability");
        return key.toString();
    }

    public static String giveBackupTableNameForOldData(String dbSchema, String tableName) {
        return dbSchema + "_backup_old_" + tableName;
    }

    public static String giveBackupTableNameForNewData(String dbSchema, String tableName) {
        return dbSchema + "_backup_new_" + tableName;
    }

    public static String getServiceInstanceId(String serviceName, String serviceIp) {
        return serviceName + "-" + serviceIp;
    }

}
