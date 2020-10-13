package testarmy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

public class PropertyService {

    private String rootPath;
    private String appConfigPath;
    private Properties appProps;

    public PropertyService() {
        File file = new File("src/test/resources");
        rootPath = file.getAbsolutePath();
        appConfigPath = rootPath + "/app.properties";
        appProps = new Properties();
        try {
            appProps.load(new FileInputStream(appConfigPath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyKey){
        return appProps.getProperty(propertyKey);
    }
    public Set<String> getPropertiesList(){
        return appProps.stringPropertyNames();
    }
}