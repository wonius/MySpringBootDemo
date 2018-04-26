package com.springboot.jmockit;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Test;

/**
 * @Mocked：被修饰的对象将会被Mock，对应的类和实例都会受影响（同一个测试用例中）
 * @Injectable：仅Mock被修饰的对象
 * @Capturing：可以mock接口以及其所有的实现类
 * @Mock：MockUp模式中，指定被Fake的方法
 *
 * @author yangfan
 * @Date 2018/4/20
 */
public class JMockitStatusBasedTest {
    @Mocked
    com.springboot.jmockit.SimpleTool mockedSimpleTool;
    @Injectable
    com.springboot.jmockit.SimpleTool injectableSimpleTool;

    @Test
    public void testMockedExpectation() {
        //未mock函数返回null
        new Expectations() {
            {
                mockedSimpleTool.fun1(anyString);
                result = "MOCK";
            }
        };

        System.out.println(mockedSimpleTool.fun1("param"));
        System.out.println(mockedSimpleTool.fun3("param"));
        System.out.println(new com.springboot.jmockit.UseSimpleTool().fun1("param"));

        new Verifications() {
            {
                mockedSimpleTool.fun1(anyString);
                times = 2;
            }
        };
    }

    @Test
    public void testInjectableExpectation() {
        //未mock函数返回null
        new Expectations() {
            {
                injectableSimpleTool.fun1(anyString);
                result = "MOCK";
            }
        };

        System.out.println(injectableSimpleTool.fun1("param"));
        System.out.println(injectableSimpleTool.fun3("param"));
        System.out.println(new com.springboot.jmockit.UseSimpleTool().fun1("param"));

        new Verifications() {
            {
                injectableSimpleTool.fun1(anyString);
                times = 1;
            }
        };
    }

    @Test
    public void testDelegateExcectation() {
        new Expectations() {
            {
                mockedSimpleTool.fun1(anyString);
                result = new Delegate<String>() {
                    public String aDelegateMethod(String str) {
                        return str.equals("param0") ? "MOCK0" : "MOCK1";
                    }
                };
            }
        };

        System.out.println(mockedSimpleTool.fun1("param0"));
        System.out.println(mockedSimpleTool.fun3("param"));
        System.out.println(new com.springboot.jmockit.UseSimpleTool().fun1("param1"));

        new Verifications() {
            {
                mockedSimpleTool.fun1(anyString);
                times = 2;
            }
        };
    }
}
