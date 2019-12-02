package com.bestvike.portal.util;

import org.apache.commons.lang3.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * IP工具类
 */
public class IpUtils {
    private static String serverIp;
    /**
     * 获取本地ip
     *
     * @return
     */
    public static List<String> getLocalIPs() {
        List<String> ips = new ArrayList<String>();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        ips.add(inetAddress.getHostAddress().toString());
                    }
                }
            }
        } catch (SocketException e) {
            return null;
        }
        return ips;
    }

    /**
     * 获取本地ip
     *
     * @return
     */
    public static String getLocalIP() {
        String ip = null;
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = en.nextElement();
                Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                while (enumIpAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        ip = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            return null;
        }
        return ip;
    }

    /**
     * @description 获取本地ip
     * @method getServerIp
     * @param
     * @return java.lang.String
     */
    public static String getServerIp() {
        //已经有ip则返回
        if (StringUtils.isNotBlank(serverIp)) {
            return serverIp;
        }
        //使用jdk方法获取
        try {
            serverIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(serverIp)) {
            return serverIp;
        }
        //使用本类方法获取，较慢
        serverIp = IpUtils.getLocalIP();
        return serverIp;
    }

}
