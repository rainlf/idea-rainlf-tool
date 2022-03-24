package com.rainlf.component.encode;

import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author : rain
 * @date : 3/24/2022 7:35 PM
 */
public class EncodeManagerProxy implements InvocationHandler {
    @Setter
    private EncodeManagerI encodeManagerI;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(encodeManagerI, args);
        } catch (Exception e) {
            return "convert failed: " + e.getMessage();
        }
    }

    public static EncodeManagerI getProxy(EncodeManagerI encodeManager) {
        EncodeManagerProxy encodeManagerProxy = new EncodeManagerProxy();
        encodeManagerProxy.setEncodeManagerI(encodeManager);
        return (EncodeManagerI) Proxy.newProxyInstance(encodeManager.getClass().getClassLoader(), encodeManager.getClass().getInterfaces(), encodeManagerProxy);
    }
}
