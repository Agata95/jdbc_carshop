package jdbc_carshop;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter

public class MysqlConnctionParameters {
    private static final String PROPERTIES_FILE_NAME = "/jdbc.properties";
    private Properties properties;

    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUsername;
    private String dbPassword;

    /**
     * This method use loadConfigurationFrom() method to load properties from file
     * and read parameters by name (loadParameter) to save it to values
     */
    public MysqlConnctionParameters() throws IOException {
        loadConfigurationFrom(PROPERTIES_FILE_NAME);

        dbHost = loadParameter("jdbc.database.host");
        dbPort = loadParameter("jdbc.database.port");
        dbName = loadParameter("jdbc.database.name");
        dbUsername = loadParameter("jdbc.username");
        dbPassword = loadParameter("jdbc.password");
    }

    /**
     * This method load all properties to the object Properties
     * from file jdbc.properties
     */
    private Properties loadConfigurationFrom(String resourceName) throws IOException {
        properties = new Properties();

        InputStream propertiesStream = this.getClass().getResourceAsStream(resourceName);
        if (propertiesStream == null) {
            throw new FileNotFoundException("Resource file cannot be loaded");
        }
        properties.load(propertiesStream);
        return properties;
    }

    /**
     * This method load parameters by names
     *
     * @return property's name by String
     */
    private String loadParameter(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
