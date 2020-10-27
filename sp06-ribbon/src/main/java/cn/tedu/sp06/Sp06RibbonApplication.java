package cn.tedu.sp06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@SpringBootApplication
public class Sp06RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp06RibbonApplication.class, args);
	}
	@LoadBalanced//负载均衡注解
	@Bean
	public RestTemplate getRestTemplate(){
		SimpleClientHttpRequestFactory schrf= new SimpleClientHttpRequestFactory();
		//只能在java内代码配置，不能在yml中配置
		schrf.setConnectTimeout(1000); //等待连接时长，
		schrf.setReadTimeout(1000);  //等待响应的超时时长
		return new RestTemplate(schrf);
	}
}
