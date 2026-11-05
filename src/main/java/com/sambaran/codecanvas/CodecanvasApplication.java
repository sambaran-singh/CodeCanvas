package com.sambaran.codecanvas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodecanvasApplication {

	public static void main(String[] args) {
		System.out.println("TIMEZONE = " + java.util.TimeZone.getDefault().getID());
		SpringApplication.run(CodecanvasApplication.class, args);
		System.out.println(java.time.ZoneId.systemDefault());
	}

}
