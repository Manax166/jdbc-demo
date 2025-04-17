package fr.diginamic.props;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.util.Iterator;


public class TestConfigurationProps {
    public static void main(String[] args){
        Configurations configs = new Configurations();
        try {
            Configuration config = configs.properties("src/main/resources/config.properties");
            Iterator<String> keys = config.getKeys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println(config.getString(key));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
