package raf.draft.dsw.gui.swing.commands.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

public class AddCommand implements AbstractCommand {

    private RoomElement child;
    private Room parent;

    public AddCommand(RoomElement child, Room parent) {
        this.child = child;
        this.parent = parent;
    }

    @Override
    public void doCommand() {
        if (child == null || parent == null) return;
        DraftRoomChildrenRepository.getInstance().addChildToList(child);
        DraftTreeItem elementItem = new DraftTreeItem(child, parent.getTreeItem());
        child.setTreeItem(elementItem);
        parent.getTreeItem().add(elementItem);
        parent.addChild(child);
        RoomView currentRoomView = parent.getRoomView();
        currentRoomView.refreshView();
    }

    @Override
    public void undoCommand() {
        if (child == null || parent == null) return;
        DraftRoomChildrenRepository.getInstance().removeChildFromList(child);
        child.getElementTreeItem().removeFromParent();
        RoomView currentRoomView = parent.getRoomView();
        parent.removeChild(child);
        counterUpdate(child);
        currentRoomView.refreshView();
    }

    private void counterUpdate(RoomElement element) {
        if (element instanceof Bed)
            Bed.setCounter(Bed.getCounter() - 1);
        else if (element instanceof Boiler)
            Boiler.setCounter(Boiler.getCounter() - 1);
        else if (element instanceof Closet)
            Closet.setCounter(Closet.getCounter() - 1);
        else if (element instanceof Door)
            Door.setCounter(Door.getCounter() - 1);
        else if (element instanceof Sink)
            Sink.setCounter(Sink.getCounter() - 1);
        else if (element instanceof Table)
            Table.setCounter(Table.getCounter() - 1);
        else if (element instanceof Toilet)
            Toilet.setCounter(Toilet.getCounter() - 1);
        else if (element instanceof Tub)
            Tub.setCounter(Tub.getCounter() - 1);
        else if (element instanceof WashingMachine)
            WashingMachine.setCounter(WashingMachine.getCounter() - 1);
    }
}
