package com.igrium.compilesource.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompileSourceConfig {
    @Getter
    private final Map<String, GameConfig> games = new HashMap<>();

    @Getter
    private final Map<String, List<Command>> presets = new HashMap<>();

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
}
