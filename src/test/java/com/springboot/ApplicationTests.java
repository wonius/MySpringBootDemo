package com.springboot;

import ai.grakn.redismock.RedisServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	RedisServer server = null;

	@Before
	public void beforeTest(){
		try {
			server = RedisServer.newRedisServer(1234);
			server.start();

			Jedis jedis = new Jedis(server.getHost(), server.getBindPort());
			jedis.set("hello", "world");
			jedis.get("hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void contextLoads() {
	}

	@After
	public void afterTest() {
		server.stop();
	}

}
