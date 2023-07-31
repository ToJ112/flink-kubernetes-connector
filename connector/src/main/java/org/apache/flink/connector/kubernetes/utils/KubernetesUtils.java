package org.apache.flink.connector.kubernetes.utils;

import org.jetbrains.annotations.NotNull;

public class KubernetesUtils {

    public static String getHost(String address) {
        String[] hostPort = getHostPort(address);
        return hostPort[0];
    }
    public static String getPort(String address) {
        String[] hostPort = getHostPort(address);
        return hostPort[1];
    }

    @NotNull
    private static String[] getHostPort(String address) {
        if (address == null || "".equalsIgnoreCase(address)) {
            throw new IllegalArgumentException("empty address");
        }
        String[] hostPort = address.split(KubernetesConstant.COLON);
        if (hostPort.length < 2) {
            throw new IllegalArgumentException("wrong address");
        }
        return hostPort;
    }

}
