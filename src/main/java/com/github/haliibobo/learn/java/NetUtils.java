package com.github.haliibobo.learn.java;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * IP and Port Helper for RPC.
 *
 * <p> Modified from <a herf="https://github.com/alibaba/dubbo/blob/master/dubbo-common/src/main/java/com/alibaba/dubbo/common/utils/NetUtils.java">NetUtils.java</a>
 * </p>
 *
 * @author shawn.qianx
 */

public class NetUtils {

    public static final String LOCALHOST = "127.0.0.1";
    public static final String ANYHOST = "0.0.0.0";
    private static final int RND_PORT_START = 30000;

    private static final int RND_PORT_RANGE = 10000;

    private static final Random RANDOM = new Random(System.currentTimeMillis());
    private static final int MIN_PORT = 0;
    private static final int MAX_PORT = 65535;
    private static final Pattern ADDRESS_PATTERN = Pattern
        .compile("^\\d{1,3}(\\.\\d{1,3}){3}:\\d{1,5}$");
    private static final Pattern LOCAL_IP_PATTERN = Pattern.compile("127(\\.\\d{1,3}){3}$");
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");
    private static final LoadingCache<String, String> hostNameCache = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build(new CacheLoader<String, String>() {
            public String load(String address) throws UnknownHostException {
                InetAddress inetAddress = InetAddress.getByName(address);
                return inetAddress.getHostName();
            }
        });
    private static volatile InetAddress LOCAL_ADDRESS = null;

    /**
     * get a random port.
     */
    public static int getRandomPort() {
        return RND_PORT_START + RANDOM.nextInt(RND_PORT_RANGE);
    }

    /**
     * get an available port.
     */
    public static int getAvailablePort() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket();
            ss.bind(null);
            return ss.getLocalPort();
        } catch (IOException e) {
            return getRandomPort();
        } finally {
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * check the port is invalid or not.
     */
    public static boolean isInvalidPort(int port) {
        return port < MIN_PORT || port > MAX_PORT;
    }

    /**
     * check the address is invalid or not.
     */
    public static boolean isValidAddress(String address) {
        return ADDRESS_PATTERN.matcher(address).matches();
    }

    /**
     * check the address is valid or not.
     */
    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null
            && !ANYHOST.equals(name)
            && !LOCALHOST.equals(name)
            && IP_PATTERN.matcher(name).matches());
    }

    /**
     * check the address is local host or not.
     */
    public static boolean isLocalHost(String host) {
        return host != null
            && (LOCAL_IP_PATTERN.matcher(host).matches()
            || host.equalsIgnoreCase("localhost"));
    }

    /**
     * check the address is "0.0.0.0".
     */
    public static boolean isAnyHost(String host) {
        return "0.0.0.0".equals(host);
    }

    /**
     * check the address is invalid local host or not.
     */
    public static boolean isInvalidLocalHost(String host) {
        return host == null
            || host.length() == 0
            || host.equalsIgnoreCase("localhost")
            || host.equals("0.0.0.0")
            || (LOCAL_IP_PATTERN.matcher(host).matches());
    }

    /**
     * check the address is valid local host or not.
     */
    public static boolean isValidLocalHost(String host) {
        return !isInvalidLocalHost(host);
    }

    /**
     * get local socket address by host and port.
     */
    public static InetSocketAddress getLocalSocketAddress(String host, int port) {
        return isInvalidLocalHost(host) ? new InetSocketAddress(port)
            : new InetSocketAddress(host, port);
    }

    /**
     * get local host string.
     */
    public static String getLocalHost() {
        InetAddress address = getLocalAddress();
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    /**
     * 遍历本地网卡，返回第一个合理的IP.
     *
     * @return 本地网卡IP
     */
    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }

    public static String getLogHost() {
        InetAddress address = LOCAL_ADDRESS;
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            try {
                                InetAddress address = addresses.nextElement();
                                if (isValidAddress(address)) {
                                    return address;
                                }
                            } catch (Throwable e) {
                            }
                        }
                    } catch (Throwable e) {
                    }
                }
            }
        } catch (Throwable e) {
        }
        return localAddress;
    }

    /**
     * get host name form address.
     */
    public static String getHostName(String address) {
        try {
            int i = address.indexOf(':');
            if (i > -1) {
                address = address.substring(0, i);
            }
            return hostNameCache.get(address);
        } catch (Throwable e) {
            // ignore
        }
        return address;
    }

    /**
     * ip address or hostName if UnknownHostException.
     */
    public static String getIpByHost(String hostName) {
        try {
            return InetAddress.getByName(hostName).getHostAddress();
        } catch (UnknownHostException e) {
            return hostName;
        }
    }

    public static String toAddressString(InetSocketAddress address) {
        return address.getAddress().getHostAddress() + ":" + address.getPort();
    }

    /**
     * get address string from {@link InetSocketAddress}.
     */
    public static InetSocketAddress toAddress(String address) {
        int i = address.indexOf(':');
        String host;
        int port;
        if (i > -1) {
            host = address.substring(0, i);
            port = Integer.parseInt(address.substring(i + 1));
        } else {
            host = address;
            port = 0;
        }
        return new InetSocketAddress(host, port);
    }

    /**
     * render a url string from protocol, host, port, path.
     */
    public static String toUrl(String protocol, String host, int port, String path) {
        StringBuilder sb = new StringBuilder();
        sb.append(protocol).append("://");
        sb.append(host).append(':').append(port);
        if (path.charAt(0) != '/') {
            sb.append('/');
        }
        sb.append(path);
        return sb.toString();
    }

}