package com.springboot.jmockit;

import com.springboot.domain.User;
import com.springboot.service.IUserService;
import com.springboot.service.impl.UserService;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;

/**
 * 请编写注释
 *
 * @author yangfan
 * @Date 2018/4/20
 */
public class JMockitInterfaceTest {
    //此处使用Injectable和Mocked为一样的效果
    @Injectable
    SimpleInterface simpleInterface;

    @Test
    public void testUpper() throws Exception {

        new MockUp<SimpleInterfaceImpl>() {
            @Mock
            public String getCityName() {
                return "BEIJING(MOCK)";
            }

            @Mock
            public String getAreaName() {
                return "HUABEI(MOCK)";
            }

        };

        new MockUp<UserService>() {
            @Mock
            public User getUser() {
                User user = new User();
                user.setUser("123");
                user.setHost("abc");
                return user;
            }
        };

        SimpleInterface mockInstance = new SimpleInterfaceImpl();
        System.out.println(mockInstance.getCityName());
        System.out.println(mockInstance.getAreaName());
        System.out.println(simpleInterface.getCityName());
        System.out.println(simpleInterface.getAreaName());
        SimpleInterfaceImpl simpleInterfaceImpl = new SimpleInterfaceImpl();
        System.out.println(simpleInterfaceImpl.getCityName());
        System.out.println(simpleInterfaceImpl.getAreaName());

        IUserService userService = new UserService();
        System.out.println(userService.getUser().getHost());
        System.out.println(userService.getUser().getUser());

    }
}
