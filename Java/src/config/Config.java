package config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Config {
    /**
     * Config class for the application
     * TODO : code de I/O of the config to make it persistent
     * DARK_THEME & LIGHT_THEME : constant integers used tu determine witch theme is to be displayed
     * THEME : current theme of the application
     * APP_TITLE : string to be used to display the application's title
     */
    public static final int DARK_THEME = 0;
    public static final int LIGHT_THEME = 1;
    private static int THEME = DARK_THEME;
    public static final String APP_TITLE = "Planet Esport Planner";

    /**
     * Getter
     * @return THEME :int
     */
    public static int getTheme() {
        return THEME;
    }

    /**
     * Sets the theme
     * @param THEME: int
     */
    public static void setTheme(int THEME) {
        if (THEME == DARK_THEME || THEME == LIGHT_THEME) Config.THEME = THEME;
    }

    /**
     * TODO : write the config in a file
     */
    public static void writeConfig() {
        File configFile = new File("config.conf");
        try{
            if(!configFile.exists()) configFile.createNewFile();
            FileWriter fr = new FileWriter(configFile);
            fr.write("theme:"+THEME+";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * TODO : read the config file
     */
    public static void readConfig() {
        List<String> config = new ArrayList<>();
        try {
            File configFile = new File("config.conf");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(configFile)));
            String line;
            while ((line = br.readLine()) != null) {
                config.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}