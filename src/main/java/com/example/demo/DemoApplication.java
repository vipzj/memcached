package com.example.demo;

import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public XMemcachedClientBuilder getXMemcachedClientBuilder(){
		return new XMemcachedClientBuilder(AddrUtil.getAddresses("10.181.101.19:11211"));
	}
}
