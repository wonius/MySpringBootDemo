package com.springboot.integration;

import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

/**
 * 使用@Service将该类注入spring，方便管理
 * 实现InitializingBean接口，初始化类对象时，实例化mockServer对象
 * 实现DisposableBean接口，销毁类对象时，关闭服务
 *
 * @author yangfan
 * @Date 2018/4/26
 */
@Service
public class HttpMockServerManual implements InitializingBean, DisposableBean {

    //创建对象
    private ClientAndProxy proxy;
    private ClientAndServer mockServer;

    @Override
    public void destroy() throws Exception {
        //别忘了关闭服务
        proxy.stop();
        mockServer.stop();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //实例化对象
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
    }

}
