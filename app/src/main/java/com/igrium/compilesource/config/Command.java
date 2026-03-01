package com.igrium.compilesource.config;

import lombok.NonNull;
import org.jetbrains.annotations.Nullable;

public record Command(@NonNull String name, @NonNull String args, boolean enabled, boolean usePostFile, @Nullable String postFile) {

}
