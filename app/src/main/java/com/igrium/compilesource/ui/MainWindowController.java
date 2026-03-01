package com.igrium.compilesource.ui;

import com.igrium.compilesource.config.Command;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.StringConverter;

import java.util.List;

//@Accessors(fluent = true)
public class MainWindowController {

    @FXML
    private ListView<MutableCommand> commandList;

    @FXML
    private Button upButton;

    @FXML
    private Button downButton;

    @FXML
    private Parent commandProps;

    @FXML
    private TextField commandNameField;

    @FXML
    private TextArea commandArgsArea;

    @FXML
    private CheckBox postFileCheck;

    @FXML
    private TextField postFileField;

    private ReadOnlyObjectProperty<MutableCommand> selectedCommandProperty() {
        return commandList.getSelectionModel().selectedItemProperty();
    }

    private final StringConverter<MutableCommand> converter = new StringConverter<MutableCommand>() {

        @Override
        public String toString(MutableCommand mutableCommand) {
            return mutableCommand.getName();
        }

        @Override
        public MutableCommand fromString(String s) {
            return null;
        }
    };

    @FXML
    void initialize() {
        commandList.setCellFactory(v -> new CommandListCell());
        commandList.getSelectionModel().selectedItemProperty().addListener(this::onSetSelectedCommand);

        upButton.disableProperty().bind(commandList.getSelectionModel().selectedIndexProperty().lessThanOrEqualTo(0));

        downButton.disableProperty().bind(commandList
                .getSelectionModel()
                .selectedIndexProperty()
                .greaterThanOrEqualTo(Bindings.size(commandList.getItems()).subtract(1))
                .or(commandList
                        .getSelectionModel()
                        .selectedIndexProperty()
                        .lessThan(0)));

        commandProps.disableProperty().bind(commandList.selectionModelProperty()
                .flatMap(s -> s.selectedItemProperty().isNull()));

        postFileField.disableProperty().bind(postFileCheck.selectedProperty().not());
    }

    private void onSetSelectedCommand(ObservableValue<? extends MutableCommand> observable, MutableCommand prevCommand, MutableCommand newCommand) {
        if (prevCommand != null) {
            commandNameField.textProperty().unbindBidirectional(prevCommand.nameProperty());
            commandArgsArea.textProperty().unbindBidirectional(prevCommand.argsProperty());
            postFileCheck.selectedProperty().unbindBidirectional(prevCommand.usePostFileProperty());
            postFileField.textProperty().unbindBidirectional(prevCommand.postFileProperty());
        }
        if (newCommand != null) {
            commandNameField.textProperty().bindBidirectional(newCommand.nameProperty());
            commandArgsArea.textProperty().bindBidirectional(newCommand.argsProperty());
            postFileCheck.selectedProperty().bindBidirectional(newCommand.usePostFileProperty());
            postFileField.textProperty().bindBidirectional(newCommand.postFileProperty());
        } else {
            commandNameField.setText("");
            commandArgsArea.setText("");
            postFileCheck.setSelected(false);
            postFileField.setText("");
        }
    }

    public void loadPreset(List<? extends Command> preset) {
        commandList.getItems().clear();
        for (Command command : preset) {
            commandList.getItems().add(new MutableCommand(command));
        }
    }

    public List<Command> savePreset(List<Command> preset) {
        for (var command : commandList.getItems()) {
            preset.add(command.toCommand());
        }
        return preset;
    }

    @FXML
    public void newCommand() {
        MutableCommand command = new MutableCommand();
        command.setEnabled(true);
        commandList.getItems().add(command);
    }

    @FXML
    public void removeCommand() {
        if (commandList.getSelectionModel().getSelectedItem() == null)
            return;

        commandList.getItems().remove(commandList.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void moveUp() {
        if (commandList.getItems().isEmpty())
            return;

        int selectedIdx = commandList.getSelectionModel().getSelectedIndex();
        if (selectedIdx == 0)
            return;

        var selected = commandList.getItems().get(selectedIdx);
        var other = commandList.getItems().get(selectedIdx - 1);

        commandList.getItems().set(selectedIdx - 1, selected);
        commandList.getItems().set(selectedIdx, other);

        commandList.getSelectionModel().select(selectedIdx - 1);
    }

    @FXML
    public void moveDown() {
        if (commandList.getItems().isEmpty())
            return;

        int selectedIdx = commandList.getSelectionModel().getSelectedIndex();
        if (selectedIdx >= commandList.getItems().size() - 1)
            return;

        var selected = commandList.getItems().get(selectedIdx);
        var other = commandList.getItems().get(selectedIdx + 1);

        commandList.getItems().set(selectedIdx + 1, selected);
        commandList.getItems().set(selectedIdx, other);

        commandList.getSelectionModel().select(selectedIdx + 1);
    }
}

class CommandListCell extends CheckBoxListCell<MutableCommand> {
    private MutableCommand observedItem;
    private final ChangeListener<String> nameListener = (obs, o, n) -> {
        if (getItem() == observedItem) setText(n);
    };

    CommandListCell() {
        super(MutableCommand::enabledProperty);
        setConverter(new StringConverter<>() {
            @Override
            public String toString(MutableCommand mutableCommand) {
                return mutableCommand.getName();
            }

            @Override
            public MutableCommand fromString(String s) {
                return null;
            }
        });
    }

    @Override
    public void updateItem(MutableCommand item, boolean empty) {
        super.updateItem(item, empty);
        if (observedItem != null) {
            observedItem.nameProperty().removeListener(nameListener);
        }
        observedItem = item;
        if (item != null && !empty) {
            item.nameProperty().addListener(nameListener);
        }
    }
}