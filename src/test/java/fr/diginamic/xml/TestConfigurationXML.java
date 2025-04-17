package fr.diginamic.xml;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

import java.util.Iterator;


public class TestConfigurationXML {
    public static void main(String[] args){
        Configurations configs = new Configurations();
        try {
            XMLConfiguration config = configs.xml("src/main/resources/config.xml");
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
