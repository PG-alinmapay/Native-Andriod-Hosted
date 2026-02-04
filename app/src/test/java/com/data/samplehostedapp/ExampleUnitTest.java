package com.data.samplehostedapp;

import android.content.Context;
import android.util.Log;



import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void printIPv6Addresses() {
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //Log.d("IPv6Test", "Running on package: " + appContext.getPackageName());

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (!networkInterface.isUp() || networkInterface.isLoopback()) continue;

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet6Address && !address.isLoopbackAddress()) {
                        Log.d("IPv6Test", "Interface: " + networkInterface.getName() +
                                " - IPv6: " + address.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            //Log.e("IPv6Test", "Error getting IPv6", e);
        }
    }
}