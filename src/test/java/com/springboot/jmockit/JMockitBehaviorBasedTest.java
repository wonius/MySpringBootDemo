package com.springboot.jmockit;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/20
 */
public class JMockitBehaviorBasedTest {
    @Test
    public void testPublicMethodMockUp() {
        //影响该类所有实例
        new MockUp<SimpleTool>(){
            //未mock函数不受影响
            @Mock
            public String fun1(String str) {
                return "(MOCK)";
            }
        };

        SimpleTool simpleTool = new SimpleTool();
        System.out.println(simpleTool.fun1("param"));
        System.out.println(simpleTool.fun3("param"));
        UseSimpleTool useSimpleTool = new UseSimpleTool();
        System.out.println(useSimpleTool.fun1("param"));
    }

    @Test
    public void testPrivateMethodMockUp() {
        new MockUp<SimpleTool>(){
            //未mock函数不受影响
            @Mock
            private String fun2(String str) {
                return "(MOCK)";
            }
        };

        SimpleTool simpleTool = new SimpleTool();
        System.out.println(simpleTool.fun1("param"));
        System.out.println(simpleTool.fun3("param"));
        System.out.println(simpleTool.fun4("param"));
    }
}
