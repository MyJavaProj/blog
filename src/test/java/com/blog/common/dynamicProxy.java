package com.blog.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.blog.dao.BaseTest;
/**
 *  动态代理实现
 * 一、 先定义一个接口Subject，添加request方法。
 * 二、 定义一个真实的实现上述接口的类，RealSubject
 * 三、 创建一个继承了InvocationHandler的处理器ProxyHandler
 * 四、 创建测试方法 dynamicProxyTest 测试
 */
public class dynamicProxy extends BaseTest{
	@Test
	public void dynamicProxyTest(){
	        RealSubject realSubject = new RealSubject();    //1.创建委托对象
	        Subject proxySubject = (Subject)new ProxyHandler().bind(realSubject);
	        proxySubject.request();    //4.通过代理对象调用方法
	    }
}


/**
 * 接口
 */
interface Subject{
    void request();
}

/**
 * 委托类
 */
class RealSubject implements Subject{
    public void request(){
        System.out.println("====RealSubject Request====");
    }
}
/**
 * 代理类的调用处理器
 */
class ProxyHandler implements InvocationHandler{
    private Subject subject;
    
    /**
     * 绑定委托对象并返回一个代理类 
     * @param delegate
     * @return
     */
    public Object bind(Object subj) {
        this.subject = (Subject)subj;
        return Proxy.newProxyInstance(subject.getClass().getClassLoader(), subject.getClass().getInterfaces(), this);
    }
    public ProxyHandler(){
        
    }
    public ProxyHandler(Subject subject){
        this.subject = subject;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("====before====");//定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        Object result = method.invoke(subject, args);
        System.out.println("====after====");
        return result;
    }
}
