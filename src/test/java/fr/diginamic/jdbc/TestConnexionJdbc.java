package fr.diginamic.jdbc;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionJdbc {
    public static void main(String[] args) {
        try {
            Configuration config = new Configurations().properties("src/main/resources/config.properties");
            Object c = Class.forName(config.getString("database.driver")).getDeclaredConstructor().newInstance();
            System.out.println(config.getString("database.driver"));
            System.out.println(c.getClass());
            DriverManager.registerDriver((Driver) c);
            Connection maConnection = DriverManager.getConnection(
                    config.getString("database.url"),
                    config.getString("database.user"),
                    config.getString("database.password"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
