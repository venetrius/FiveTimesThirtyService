package ftt.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public final class AppConfig {
	private static AppConfig appConfig = null;
	// TODO it has a value of null, however application.properties is found
	// learn more about Spring to fix this...
	@Value("${name}")
    private String name;
	private Map<String,String> properties = new HashMap<String, String>();
	
	
	private AppConfig() {
		try(FileReader fr = new FileReader("application.properties");
			BufferedReader br = new BufferedReader(fr);	) {
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				String[] line = sCurrentLine.split("=");
				if(line.length == 2) {
					properties.put(line[0],line[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public String getName() {
		return name;
	}
	
	public String getProperty(String property) {
		return properties.get(property);
	}
	
	public static AppConfig getInstance() {
		if(appConfig == null) {
			appConfig = new AppConfig();
		}
		return appConfig;
	}
	
}
