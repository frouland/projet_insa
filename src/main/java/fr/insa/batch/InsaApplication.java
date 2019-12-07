package fr.insa.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.event.EventListener;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class InsaApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InsaProperties insaProperties;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(InsaApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
	
	
	public void run(String... args) throws Exception {
		log.info("run()");
		
		processInsa();
		
		log.info("Exit code : 0");

	}

	
	/**
	 * Lance le processus
	 * 
	 * @return
	 */
	public boolean processInsa() {
		log.info("processInsa()");

		log.info("Version : " + this.insaProperties.getVersion());
		
		
		int target = -5;
		int num = 3;

		target =- num;  // Noncompliant; target = -3. Is that really what's meant?
		target =+ num; // Noncompliant; target = 3
		
		return true;

	}

	@EventListener
	public void exitEvent(ExitCodeEvent event) {
		log.error("Exit code : " + event.getExitCode());
	}

	public InsaProperties getInsaProperties() {
		return insaProperties;
	}

	public void setInsaProperties(InsaProperties insaProperties) {
		this.insaProperties = insaProperties;
	}


}
