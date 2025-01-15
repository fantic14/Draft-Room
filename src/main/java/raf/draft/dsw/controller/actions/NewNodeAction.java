package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.ProjectView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class NewNodeAction extends AbstractRoomAction {

    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/add.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if (selected != null) {
            DraftNode newNode = MainFrame.getInstance().getDraftTree().addChild(selected);
            if (newNode instanceof Room) {
                Room room = (Room) newNode;
                JPanel roomPanel = MainFrame.getInstance().getProjectView().createRoomPanel(room);

                double widthInPixels = room.getWidthInPixels();
                double heightInPixels = room.getHeightInPixels();
                roomPanel.setPreferredSize(new Dimension((int) widthInPixels, (int) heightInPixels));

                ProjectView projectView = MainFrame.getInstance().getProjectView();
                projectView.addRoomTab(room, roomPanel);
            }
        } else {
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_NODE_FROM_HERE", MessageType.WARNING);
        }
    }
}
