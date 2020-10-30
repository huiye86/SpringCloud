package cn.tedu.sp10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@EnableDiscoveryClient  //会自动配置可以不加
@EnableZuulProxy //代理
@SpringBootApplication
public class Sp10ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp10ZuulApplication.class, args);
	}

}
