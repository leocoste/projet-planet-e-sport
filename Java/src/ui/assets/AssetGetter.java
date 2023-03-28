package ui.assets;

import config.Config;

import java.util.Objects;

public class AssetGetter {

    /**
     * Retrieves the relative path to the file
     * @param iconName: string
     * @return iconPath: string
     */
    public static String getIconPath(String iconName) {
        try {
            if (Config.getTheme() == Config.DARK_THEME) {
                return Objects.requireNonNull(AssetGetter.class.getResource("./icons/darktheme/" + iconName)).getPath();
            }
            return Objects.requireNonNull(AssetGetter.class.getResource("./icons/lighttheme/" + iconName)).getPath();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }
}