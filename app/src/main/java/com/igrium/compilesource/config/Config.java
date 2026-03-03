package com.igrium.compilesource.config;

import com.igrium.compilesource.CompileSourceApp;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Config {

    public static Config getConfig() {
        return CompileSourceApp.getInstance().getConfig();
    }

    @Getter
    private final Map<String, GameConfig> games = new HashMap<>();

    @Getter
    private final LinkedHashMap<String, List<Command>> presets = new LinkedHashMap<>();

    @Getter @Setter @NonNull
    private String selectedGame = "";

    @Getter @Setter @NonNull
    private String selectedPreset = "";

    public @Nullable GameConfig getGame() {
        return games.get(selectedGame);
    }

    public @Nullable List<Command> getPreset() {
        return presets.get(selectedPreset);
    }

    public List<Command> getOrInitPreset() {
        return presets.computeIfAbsent(selectedPreset, _ -> new ArrayList<>());
    }

    public void savePreset(String presetName, List<Command> preset) {
        presets.put(presetName, preset);
    }
}
