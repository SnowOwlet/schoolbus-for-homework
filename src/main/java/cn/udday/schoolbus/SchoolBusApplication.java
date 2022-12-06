package cn.udday.schoolbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.udday.schoolbus.mapper")
public class SchoolBusApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolBusApplication.class, args);
	}

}
