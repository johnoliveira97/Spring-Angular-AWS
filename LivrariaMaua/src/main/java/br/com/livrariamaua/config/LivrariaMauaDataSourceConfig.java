package br.com.livrariamaua.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.livrariamaua", entityManagerFactoryRef = "livrariaMauaEntityManager", transactionManagerRef = "livrariaMauaTransactionManager")
public class LivrariaMauaDataSourceConfig extends DataSourceConfig {

	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.username}")
	private String username;

	@Bean
	@ConfigurationProperties(prefix = "datasource")
	DataSource livrariaMauaDatasource() {
		try {
			
			return getDatasource(url, username);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(
					"Falha durante abertura de conexao com base da LivrariaMaua. Falha: " + ex.getMessage());
		}
	}

	@Bean("livrariaMauaEntityManager")
	LocalContainerEntityManagerFactoryBean livrariaMauaEntityManager() {
		return getEntityManager(livrariaMauaDatasource(),
				"br.com.livrariamaua.domain");
	}

	@Bean("livrariaMauaTransactionManager")
	PlatformTransactionManager livrariaMauaTransactionManager(
			final @Qualifier("livrariaMauaEntityManager") LocalContainerEntityManagerFactoryBean livrariaMauaEntityManager) {
		return getTransactionManager(livrariaMauaEntityManager);
	}
}
