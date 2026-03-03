package com.igrium.compilesource.ui;

import com.igrium.compilesource.config.Command;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

@Accessors(fluent = true)
public final class MutableCommand {

    public MutableCommand() {}

    public MutableCommand(Command command) {
        fromCommand(command);
    }

    @Getter
    private final StringProperty nameProperty = new SimpleStringProperty();

    public String getName() {
        return nameProperty.get();
    }

    public void setName(String name) {
        nameProperty.set(name);
    }

    @Getter
    private final StringProperty argsProperty = new SimpleStringProperty();

    public String getArgs() {
        return argsProperty.get();
    }

    public void setArgs(String args) {
        argsProperty.set(args);
    }

    @Getter
    private final BooleanProperty enabledProperty = new SimpleBooleanProperty();

    public boolean isEnabled() {
        return enabledProperty.get();
    }

    public void setEnabled(boolean enabled) {
        enabledProperty.set(enabled);
    }

    @Getter
    private final BooleanProperty usePostFileProperty = new SimpleBooleanProperty();

    public boolean isUsePostFile() {
        return usePostFileProperty.get();
    }

    public void setUsePostFile(boolean usePostFile) {
        usePostFileProperty.set(usePostFile);
    }

    @Getter
    private final StringProperty postFileProperty = new SimpleStringProperty();

    public String getPostFile() {
        return postFileProperty.get();
    }

    public void setPostFile(String postFile) {
        postFileProperty.set(postFile);
    }

    public Command toCommand() {

        return new Command(orEmpty(getName()), orEmpty(getArgs()), isEnabled(), isUsePostFile(), getPostFile());
    }

    public void fromCommand(Command command) {
        setName(command.name());
        setArgs(command.args());
        setEnabled(command.enabled());
        setUsePostFile(command.usePostFile());
        setPostFile(command.postFile());
    }

    private @NonNull String orEmpty(@Nullable String in) {
        return in != null ? in : "";
    }
}
