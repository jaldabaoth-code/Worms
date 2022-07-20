package org.wcscda.worms.gamemechanism;

import java.awt.EventQueue;
import java.util.Arrays;
import javax.swing.JFrame;
import org.wcscda.worms.Config;

public class WormLauncher extends JFrame {
    private static final long serialVersionUID = 1L;
    private static WormLauncher instance;

    public static WormLauncher getInstance() {
        return instance;
    }

    public WormLauncher() {
        instance = this;
        initUI();
    }

    private void initUI() {
        add(TimeController.getInstance().getBoard());
        setResizable(false);
        pack();
        setTitle("WCS - CDA - Worms");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        try {
            Config.loadConfig();
        } catch (Exception ex) {
            System.err.println("Could not load configuration, please check ...");
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        }
        // --launch-test is an option for CI. No headless for the moment
        if (Arrays.asList(args).contains("--launch-test")) {
            System.out.println("Launching successfull !");
            System.exit(0);
        }
        EventQueue.invokeLater(() -> {
            JFrame ex = new WormLauncher();
            ex.setVisible(true);
        });
    }
}
