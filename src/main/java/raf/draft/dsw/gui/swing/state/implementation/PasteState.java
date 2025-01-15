package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.commands.implementation.CopyPasteCommand;
import raf.draft.dsw.gui.swing.state.State;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.RoomElement;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.*;

public class PasteState implements State {
    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
        List<RoomElement> clonedElements = new ArrayList<>();
        for (RoomElement copiedElement : roomView.getCopiedElements()) {
            if (!clonedElements.contains(copiedElement))
                clonedElements.add(copiedElement.clone());
        }
        AbstractCommand command = new CopyPasteCommand(clonedElements, roomView.getRoom());
        MainFrame.getInstance().getCommandManager().addCommand(command);
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }
}
