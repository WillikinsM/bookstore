package com.will.bookstoreapi.config;

import com.will.bookstoreapi.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public void initDb() {
		this.dbService.initDataBase();
	}
}
