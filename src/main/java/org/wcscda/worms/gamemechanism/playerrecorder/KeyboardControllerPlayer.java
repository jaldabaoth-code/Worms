package org.wcscda.worms.gamemechanism.playerrecorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.wcscda.worms.gamemechanism.KeyboardController;
import org.wcscda.worms.utils.DefaultHashMap;

public class KeyboardControllerPlayer extends KeyboardController {
    @JsonProperty
    private final DefaultHashMap<Integer, Set<String>> records = new DefaultHashMap<>(() -> new HashSet<>());
    @JsonProperty private int seed;
    @JsonProperty private String recordDate;

    public static KeyboardControllerPlayer loadFromFile(String filename) throws JsonProcessingException {
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        String xml = null;
        try {
            xml = StringUtils.join(Files.readAllLines(new File(filename).toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xmlMapper.readValue(xml, KeyboardControllerPlayer.class);
    }
}
