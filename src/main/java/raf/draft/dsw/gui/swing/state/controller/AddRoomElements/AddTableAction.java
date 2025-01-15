package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.Bed;
import raf.draft.dsw.model.structures.RoomElements.Table;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddTableAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddTableAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String width = JOptionPane.showInputDialog("Enter table width (cm)");
        String height = JOptionPane.showInputDialog("Enter table lenght (cm)");

        int widthCm;
        int heightCm;

        try {
            if (width != null && height != null) {
                widthCm = Integer.parseInt(width);
                heightCm = Integer.parseInt(height);
            } else return;
        } catch (NumberFormatException e){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            return;
        } catch (NullPointerException _){
            return;
        }

        if (widthCm > room.getWidthCm() || heightCm > room.getHeightCm()){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
            return;
        }

        double tableWidth = widthCm*3.78;
        double tableHeight = heightCm*3.78;
        double tableWidthRatio = tableWidth / room.getWidthInPixels();
        double tableHeightRatio = tableHeight / room.getHeightInPixels();

        Table table = new Table(room, 0.3, 0.3, 0);
        table.setWidthRatio(tableWidthRatio);
        table.setHeightRatio(tableHeightRatio);
        table.setWidth(widthCm);
        table.setHeight(heightCm);

        table.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(table));
        notifySubscribers(null);
    }

    @Override
    public void addSubscriber(ISubscriber newSub) {
        if (!subs.contains(newSub))
            subs.add(newSub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subs.remove(sub);
    }

    @Override
    public void notifySubscribers(Object data) {
        for (ISubscriber sub : subs) {
            sub.update(data);
        }
    }
}
