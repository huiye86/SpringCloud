package cn.tedu.sp08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableCircuitBreaker
//@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient  //新版本可以不加
@SpringCloudApplication
public class Sp08FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp08FeignApplication.class, args);
	}

}
