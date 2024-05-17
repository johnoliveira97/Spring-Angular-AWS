package br.com.livrariamaua.config;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

public abstract class DataSourceConfig {
	
	protected DataSource getDatasource(String url, String username) {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);

		return dataSource;
	}

	protected LocalContainerEntityManagerFactoryBean getEntityManager(DataSource dataSource, String... packagesToScan) {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(packagesToScan);
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		return em;
	}

	protected PlatformTransactionManager getTransactionManager(final LocalContainerEntityManagerFactoryBean bean) {

		if (bean.getObject() != null) {
			return new JpaTransactionManager(bean.getObject());
		}

		return null;
	}
}
