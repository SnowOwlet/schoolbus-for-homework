package cn.udday.schoolbus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("cn.udday.schoolbus.mapper")
public class SchoolBusApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolBusApplication.class, args);
	}
}
