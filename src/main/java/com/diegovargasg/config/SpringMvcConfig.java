package com.diegovargasg.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.diegovargasg.dao.ContactDao;
import com.diegovargasg.dao.ContactDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "net.codejava.contact")
public class SpringMvcConfig {
	
	private DriverManagerDataSource dataSource;
	
	@Bean
	public DataSource getDataSource() {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/contacts");
		dataSource.setUsername("userdb");
		dataSource.setPassword("password");
		
		return dataSource;
	}
	
	/*@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}*/
	
	@Bean
	public ContactDao getContactDao( ) {
		return new ContactDaoImpl(getDataSource());
	}
}
