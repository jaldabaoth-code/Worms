package org.wcscda.worms.gamemechanism;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class ScriptPlayer {
    private HashMap<Integer, String> keyboardInput = new HashMap<>();

    public HashMap<Integer, String> getKeyboardInput() {
        return keyboardInput;
    }

    public ScriptPlayer(String scriptFilename) {
        readScriptFile(scriptFilename);
    }

    private void readScriptFile(String scriptFilename) {
        try {
            for (String line : Files.readAllLines(new File(scriptFilename).toPath())) {
                String[] splitLines = line.split(";");
                if (splitLines.length != 2) break;
              keyboardInput.put(Integer.parseInt(splitLines[0]), splitLines[1]);
          }
          System.out.println("Job done !");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Script file not found");
            System.exit(3);
        }
    }
}
