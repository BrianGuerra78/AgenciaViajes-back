package mx.com.basantader.AgenciaViajeTA.integration;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import mx.com.basantader.AgenciaViajeTA.Application;
import mx.com.basantader.AgenciaViajeTA.base.TestBase;

import static org.junit.Assert.assertTrue;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class TestService extends TestBase{ 

    @Test
    public void contextLoads() {
        assertTrue(template.getForEntity("/actuator/health", String.class).getStatusCode().is2xxSuccessful());
    }
    @Test
    public void test_is_server_up() {
        assertTrue(template.getForEntity("/actuator/health", String.class).getStatusCode().is2xxSuccessful());

    }

}
