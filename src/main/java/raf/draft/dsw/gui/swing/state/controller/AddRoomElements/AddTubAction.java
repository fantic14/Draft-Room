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
import raf.draft.dsw.model.structures.RoomElements.Tub;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddTubAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddTubAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String width = JOptionPane.showInputDialog("Enter tub width (cm)");
        String height = JOptionPane.showInputDialog("Enter tub lenght (cm)");

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

        double tubWidth = widthCm*3.78;
        double tubHeight = heightCm*3.78;
        double tubWidthRatio = tubWidth / room.getWidthInPixels();
        double tubHeightRatio = tubHeight / room.getHeightInPixels();

        Tub tub = new Tub(room, 0.5, 0.5, 0);
        tub.setWidthRatio(tubWidthRatio);
        tub.setHeightRatio(tubHeightRatio);
        tub.setWidth(widthCm);
        tub.setHeight(heightCm);

        tub.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(tub));
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
