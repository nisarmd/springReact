package com.nisar.springReact;


import com.nisar.springReact.Config.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
//@ComponentScan({"com.nisar.springReact.JPA"})
@EnableJpaRepositories("com.nisar.springReact.JPA")
public class SpringReactApplication {

	private static ApplicationContext context;
	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
		context = new AnnotationConfigApplicationContext(DataSource.class);
		connect();
	}
	public static void connect() {
		Connection conn = null;
		try {
			DriverManagerDataSource dataSource = context.getBean("sqliteDBSource", DriverManagerDataSource.class);
			conn = dataSource.getConnection();

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
