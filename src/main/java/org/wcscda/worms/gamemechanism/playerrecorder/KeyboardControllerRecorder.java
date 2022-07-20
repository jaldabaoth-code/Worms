package org.wcscda.worms.gamemechanism.playerrecorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import org.wcscda.worms.Config;
import org.wcscda.worms.Helper;
import org.wcscda.worms.RandomGenerator;
import org.wcscda.worms.gamemechanism.Board;
import org.wcscda.worms.gamemechanism.KeyboardController;
import org.wcscda.worms.utils.DefaultHashMap;
import org.wcscda.worms.utils.IWindowListenerWithDefault;

@JsonRootName("GameRecording")
public class KeyboardControllerRecorder extends KeyboardController implements IWindowListenerWithDefault {
    private final String filename;
    @JsonProperty
    private final DefaultHashMap<Integer, Set<String>> records = new DefaultHashMap<>(() -> new HashSet<>());

    public KeyboardControllerRecorder(Board mainWindow) {
        this(mainWindow, Config.getScriptFilename());
    }

    public KeyboardControllerRecorder(Board mainWindow, String filename) {
        super();
        mainWindow.addWindowListener(this);
        this.filename = filename;
    }

    @Override
    protected void sendKey(String keyAsString) {
        super.sendKey(keyAsString);
        records.get(Helper.getClock()).add(keyAsString);
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        XmlMapper xmlMapper = new XmlMapper();
        String xml = null;
        try {
            xml = xmlMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            Files.write(new File(filename).toPath(), xml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @JsonProperty
    private Integer getSeed() {
        return RandomGenerator.getSeed();
    }

    @JsonProperty
    private String getRecordDate() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(new Date());
    }
}
