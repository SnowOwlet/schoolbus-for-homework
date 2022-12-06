package cn.udday.shoolbus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.udday.shoolbus.mapper")
public class ShoolbusApplication {
	public static void main(String[] args) {
		SpringApplication.run(ShoolbusApplication.class, args);
	}

}
