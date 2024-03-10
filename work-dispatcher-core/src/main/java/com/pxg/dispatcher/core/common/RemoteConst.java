package com.pxg.dispatcher.core.common;

public class RemoteConst {

    private static int HEARTBEAT_TIMEOUT = 1000;

    private static int HEARTBEAT_INTERVAL = 30;

    private static int DEAD_INTERVAL = 90;

    private static String HEART_BEAT_PATH = "/perform/heartbeat";

    private static String REMOTE_WORK_PATH = "/perform/run";

    public static final String RPC_SCRIP = "Rpc-Scrip";

    private RemoteConst() {
    }

    public static int getHeartbeatTimeout() {
        return HEARTBEAT_TIMEOUT;
    }

    public static void setHeartbeatTimeout(int timeoutMillis) {
        HEARTBEAT_TIMEOUT = timeoutMillis;
    }

    public static int getHeartbeatInterval() {
        return HEARTBEAT_INTERVAL;
    }

    public static void setHeartbeatInterval(int heartbeatSeconds) {
        HEARTBEAT_INTERVAL = heartbeatSeconds;
    }

    public static int getDeadInterval() {
        return DEAD_INTERVAL;
    }

    public static void setDeadInterval(int deadSeconds) {
        DEAD_INTERVAL = deadSeconds;
    }

    public static String getHeartBeatPath() {
        return HEART_BEAT_PATH;
    }

    public static void setHeartBeatPath(String heartBeatPath) {
        HEART_BEAT_PATH = heartBeatPath;
    }

    public static String getRemoteWorkPath() {
        return REMOTE_WORK_PATH;
    }

    public static void setRemoteWorkPath(String remoteWorkPath) {
        REMOTE_WORK_PATH = remoteWorkPath;
    }

}
