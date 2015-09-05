package net.alteiar.config;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.alteiar.context.AppContext;

public class ContextLoaderListener extends org.springframework.web.context.ContextLoaderListener {

	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);

	public ContextLoaderListener() {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		logger.info("Starting the application...");

		try {

			AppContext.getInstance().initialize();
			AppContext.getInstance().start();

		} catch (Throwable ex) {

			logger.error("Fail to initialize the appContext", ex);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		logger.info("Stopping the application...");
		AppContext.getInstance().stop();
	}

}
