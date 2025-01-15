package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.Bed;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddBedAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddBedAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String width = JOptionPane.showInputDialog("Enter bed width (cm)");
        String height = JOptionPane.showInputDialog("Enter bed length (cm)");
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
        double bedWidth = widthCm*3.78;
        double bedHeight = heightCm*3.78;

        double bedWidthRatio = bedWidth / room.getWidthInPixels();
        double bedHeightRatio = bedHeight / room.getHeightInPixels();

        Bed bed = new Bed(room, 0.1, 0.1, 0);
        bed.setWidthRatio(bedWidthRatio);
        bed.setHeightRatio(bedHeightRatio);
        bed.setWidth(widthCm);
        bed.setHeight(heightCm);

        bed.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(bed));
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
