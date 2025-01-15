package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.structures.RoomElement;

import java.awt.event.ActionEvent;
import java.util.List;

public class ShuffleOrganisedRoomAction extends AbstractRoomAction{

    private List<RoomElement> elements;
    private RoomView roomView;

    public ShuffleOrganisedRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/shuffle.png"));
        putValue(NAME, "Shuffle");
        putValue(SHORT_DESCRIPTION, "Shuffle");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MainFrame.getInstance().getActionManager().getOrganiseMyRoomAction().putInRoom(elements);
        } catch (NullPointerException _){}
    }

    public void takeInfo(RoomView roomView, List<RoomElement> elements){
        this.elements = elements;
        this.roomView = roomView;
    }
}
