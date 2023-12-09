package com.qa.gorest.configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.qa.gorest.frameworkexception.AppFrameworkException;

public class ConfigurationManager {

	private Properties prop;
	private FileInputStream ip;

	public Properties initProp() {

		prop = new Properties();
		// Maven command line argument:
		// mvn clean install -Denv="qa"
		// mvn clean install

		String envName = System.getProperty("env");
		if (envName == null) {
			System.out.println("No env is given hence running on QA enviournment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running test on enviournment: " + envName);
			try {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;

				default:
					System.out.println("Please pass the right enviornment name : " + envName);
					throw new AppFrameworkException("Wrong Env Is Given");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
