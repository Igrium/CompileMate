package com.igrium.compilesource.config;

import org.jetbrains.annotations.Nullable;

public record Command(String name, String args, @Nullable String postExists) {
}
