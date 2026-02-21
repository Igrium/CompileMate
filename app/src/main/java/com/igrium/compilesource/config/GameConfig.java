package com.igrium.compilesource.config;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class GameConfig {
    /**
     * The game engine executable file.
     */
    @Getter @Setter @NonNull
    private String gameExe = "";

    /**
     * The global directory of gameinfo.txt
     */
    @Getter @Setter @NonNull
    private String gameDir = "";

    /**
     * The vbsp executable file
     */
    @Getter @Setter @NonNull
    private String bspExe = "";

    /**
     * The vvis executable file
     */
    @Getter @Setter @NonNull
    private String visExe = "";

    /**
     * The vrad executable file
     */
    @Getter @Setter @NonNull
    private String radExe = "";

    /**
     * Place compiled maps in this directory before running the game
     */
    @Getter @Setter @NonNull
    private String mapDir = "";
}
