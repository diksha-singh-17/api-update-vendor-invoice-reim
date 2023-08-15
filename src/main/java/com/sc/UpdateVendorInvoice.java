package com.sc;

import com.sc.config.Configuration;
import com.sc.config.LoggerConfig;
import com.sc.config.VaultConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({Configuration.class,VaultConfig.class, LoggerConfig.class})
public class UpdateVendorInvoice implements CommandLineRunner{

	  private final Configuration configuration;
	private final VaultConfig vaultConfig;

	private final LoggerConfig loggerConfig;


	  public UpdateVendorInvoice(Configuration configuration, VaultConfig vaultConfig, LoggerConfig loggerConfig) {
		  this.configuration = configuration;
		  this.vaultConfig = vaultConfig;
		  this.loggerConfig = loggerConfig;
	  }
	public static void main(String[] args) {
		SpringApplication.run(UpdateVendorInvoice.class, args);
	}
	@Override
	public void run(String... args) {

	  Logger logger = LoggerFactory.getLogger(UpdateVendorInvoice.class);

	  logger.info("----------------------------------------");
	  logger.info("Configuration properties");
	  logger.info("   example.username is {}", configuration.getCredentials_json());
	  logger.info("----------------------------------------");
	}
}
