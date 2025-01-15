package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;

public class RemoveNodeAction extends AbstractRoomAction{

    public RemoveNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/remove.png"));
        putValue(NAME, "Remove Node");
        putValue(SHORT_DESCRIPTION, "Remove Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftNode selected = MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode();
        try {
            DraftNodeComposite dnc;
            if (selected instanceof DraftNodeComposite) {
                dnc = (DraftNodeComposite) selected;
                System.out.println(dnc.getChildren());
                for (int i = dnc.getChildren().size()-1; i >= 0; --i) {
                    MainFrame.getInstance().getDraftTree().removeChild(dnc.getChildren().get(i).getParent(), dnc.getChildren().get(i));
                }
            }
            MainFrame.getInstance().getDraftTree().removeChild(selected.getParent(), selected);
            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();

        } catch (NullPointerException exc){
            MessageGenerator mg = new MessageGenerator();
            if (selected == null) {
                mg.generateMessage("SELECT_NODE_FIRST", MessageType.NOTIFICATION);
                return;
            }
            mg.generateMessage("NODE_CANNOT_BE_DELETED", MessageType.WARNING);
        }
    }
}
