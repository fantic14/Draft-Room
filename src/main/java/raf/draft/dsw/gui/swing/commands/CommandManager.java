package raf.draft.dsw.gui.swing.commands;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<AbstractCommand> commands;
    int pointer = 0;

    public CommandManager() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(AbstractCommand command){
        while (pointer < commands.size())
            commands.remove(pointer);
        commands.add(command);
        doCommand();
    }

    public void doCommand() {
        if (pointer < commands.size()){
            commands.get(pointer++).doCommand();

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getDraftTree().getTreeView());
            MainFrame.getInstance().enableUndoAction();
        }
        if (pointer == commands.size()) {
            MainFrame.getInstance().disableDoAction();
        }
    }

    public void undoCommand() {
        if (pointer > 0){
            MainFrame.getInstance().enableDoAction();
            commands.get(--pointer).undoCommand();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getDraftTree().getTreeView());
        }
        if (pointer == 0)
            MainFrame.getInstance().disableUndoAction();

    }

}
