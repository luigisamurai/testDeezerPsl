

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TestDeezerApi extends SpringBootServletInitializer {
	private static final Logger logger = LoggerFactory.getLogger(TestDeezerApi.class);

	 @Override
	 protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
	  return application.sources(TestDeezerApi.class);
	 }

	 public static void main(String[] args) {
	  ApplicationContext ctx = SpringApplication.run(TestDeezerApi.class, args);
	  logger.info("Let's inspect the beans provided by Spring Boot:");

	  String[] beanNames = ctx.getBeanDefinitionNames();
	  Arrays.sort(beanNames);
	  for (String beanName : beanNames) {
	   logger.info(beanName);
	  }
	 }

}
