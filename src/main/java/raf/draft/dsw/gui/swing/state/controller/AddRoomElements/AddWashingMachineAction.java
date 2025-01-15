package raf.draft.dsw.gui.swing.state.controller.AddRoomElements;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.observer.IPublisher;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElements.WashingMachine;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddWashingMachineAction implements IPublisher {

    private final Room room;
    private final RoomView roomView;
    private List<ISubscriber> subs;

    public AddWashingMachineAction(RoomView roomView) {
        this.room = roomView.getRoom();
        this.roomView = roomView;
        subs = new ArrayList<>();
        addSubscriber(roomView);

        String widthHeight = JOptionPane.showInputDialog("Enter machine width/height (cm)");

        int widthHeightCm;

        try {
            if (widthHeight != null ) {
                widthHeightCm = Integer.parseInt(widthHeight);
            } else return;

        } catch (NumberFormatException e){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
            return;
        } catch (NullPointerException _){
            return;
        }

        if (widthHeightCm > room.getWidthCm() || widthHeightCm > room.getHeightCm()){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_ADD_ELEMENT_GREATER_THAN_ROOM", MessageType.ERROR);
            return;
        }

        double machineWidthHeight = Double.parseDouble(widthHeight)*3.78;
        double machineWidthHeightRatio = machineWidthHeight / room.getWidthInPixels();

        WashingMachine machine = new WashingMachine(room, 0.6, 0.6, 0);
        machine.setWidthRatio(machineWidthHeightRatio);
        machine.setHeightRatio(machineWidthHeightRatio);
        machine.setWidth(widthHeightCm);
        machine.setHeight(widthHeightCm);

        machine.setElementTreeItem(MainFrame.getInstance().getDraftTree().addChild(machine));
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
