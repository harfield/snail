package com.harfield.snail.tool;

import sun.misc.Launcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;

/**
 * Created by harfield on 2017/3/24.
 */
public class ProxyClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Account a = (Account) Proxy.newProxyInstance(
                Account.class.getClassLoader(),
                new Class[]{Account.class},
                new SecurityProxyInvocationHandler(new AccountImp())
        );
         a.add();
        System.out.println(a.getClass().getClassLoader());
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
//        System.out.println(sun.misc.Launcher.getLauncher().getClassLoader());

//        URL[] urls1 = sun.misc.Launcher.getLauncher().getClassLoader().getURLs();
//        for (int i = 0; i < urls.length; i++) {
//            System.out.println(urls1[i].toExternalForm());
//        }
    }

    interface Account{
        void add();
    }


    public static class AccountImp implements Account{
        public void add(){
            System.out.println("in add");
        }
    }

    static class SecurityProxyInvocationHandler implements InvocationHandler {
        private Object proxyedObject;
        public SecurityProxyInvocationHandler(Object o) {
            proxyedObject = o;
        }
        @Override
        public Object invoke(Object object, Method method, Object[] arguments)
                throws Throwable {
            if (object instanceof Account && method.getName().equals("add")) {
                System.out.println("in invocationHandler!");
            }
            return method.invoke(proxyedObject, arguments);
        }

    }
}
