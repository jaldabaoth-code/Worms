package org.wcscda.worms;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import java.io.*;
import java.util.*;

@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, fieldVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public class Config {
    private Properties prop;
    /* Singleton design pattern */
    private static final String CONFIG_FILENAME = "src/resources/app.config";
    private static Config config = null;

    public static Config getConfig() {
        if (config == null) loadConfig();
        return config;
    }

    public static void loadConfig() {
        try {
            config = new Config();
        } catch (IOException e) {
            System.err.println("Sorry, could not load config file, exiting ...");
            System.exit(2);
        }
    }

    public static boolean isDebug() {
        return getConfig().prop.getProperty("app.debug").equals("1");
    }

    public static int getClockDelay() {
        return Integer.parseInt(config.prop.getProperty("app.clock_delay"));
    }

    public static int getMaxWormTurnDuration() {
        return Integer.parseInt(config.prop.getProperty("app.worms.turn_delay"));
    }

    public static String getScriptFilename() {
        return getConfig().prop.getProperty("app.script_file");
    }

    public static Boolean getRecordGame() {
        return Boolean.parseBoolean(getConfig().prop.getProperty("app.record_game"));
    }

    public static Boolean getPlayRecord() {
        return Boolean.parseBoolean(getConfig().prop.getProperty("app.play_record"));
    }

    public static Integer getRandomSeed() {
        String randomSeedProperty = getConfig().prop.getProperty("app.random_seed");
        if (randomSeedProperty == null || randomSeedProperty.equals("") || randomSeedProperty.equals("null")) {
            return null;
        }
        return Integer.parseInt(randomSeedProperty);
    }

    public Config() throws IOException {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILENAME)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            throw ex;
        } catch (IOException ex) {
            throw ex;
        }
    }
}
