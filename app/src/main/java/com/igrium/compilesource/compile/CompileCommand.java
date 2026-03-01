package com.igrium.compilesource.compile;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CompileCommand {

    @Getter @Setter @NonNull
    private String command = "";

    @Getter
    private final List<String> args = new ArrayList<>();
}
