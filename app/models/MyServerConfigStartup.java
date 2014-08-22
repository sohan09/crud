package models;

import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebean.config.DataSourceConfig;
import com.avaje.ebean.event.ServerConfigStartup;

import java.net.*;

public class MyServerConfigStartup implements ServerConfigStartup {
    @Override
    public void onStart(ServerConfig serverConfig) {
	
        serverConfig.setDatabaseSequenceBatchSize(1);
		
		URI dbUri;
		
		try {
			
			dbUri = new URI(System.getenv("DATABASE_URL"));
			
		} catch (URISyntaxException ex) {
		
			return;
		}
		
		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		
		dataSourceConfig.setUrl(dbUrl);
		dataSourceConfig.setPassword(password);
		dataSourceConfig.setUsername(username);
		
		serverConfig.setDataSourceConfig(dataSourceConfig);
    }
	
}