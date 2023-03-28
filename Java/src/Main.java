import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import config.Config;
import ui.components.frames.AppFrame;
import ui.components.frames.CreateTournamentFrame;

import javax.swing.*;
import java.awt.*;

import static config.Config.DARK_THEME;
import static config.Config.LIGHT_THEME;

public class Main {

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            setLookAndFeel();
            new AppFrame();
        });
    }
    private static void setLookAndFeel(){
        try {
            if(Config.getTheme() == DARK_THEME) UIManager.setLookAndFeel( new FlatDarkLaf() );
            else if(Config.getTheme() == LIGHT_THEME) UIManager.setLookAndFeel(new FlatLightLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
    }

    public static CreateTournamentFrame getFrame() {
        return frame;
    }

    //private static AppFrame frame;

    private static CreateTournamentFrame frame;


}