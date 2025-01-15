package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Tub;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class TubPainter extends PrimalPainter{

    private Tub tub;
    private boolean selected;
    private Room room;

    public TubPainter(Tub tub) {
        super(tub);
        this.tub = tub;
        room = (Room)tub.getParent();
        this.tub.setElementPainter(this);
    }

    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int tubWidth = (int) (tub.getWidthRatio()*width);
        int tubHeight = (int) (tub.getHeightRatio()*height);
        int ovalWidth = (int) (tubWidth *0.9);
        int ovalHeight = (int) (tubHeight *0.85);

        if (tub.getX() == -1 && tub.getY() == -1) {
            tub.setX(x + width / 2);
            tub.setY(y + height / 2);
        }

        setShape(new GeneralPath());

        switch (tub.getRotateRatio()) {
            case 0,2:
                getShape().moveTo(tub.getX() - tubWidth / 2, tub.getY() - tubHeight / 2);
                getShape().lineTo(tub.getX() + tubWidth / 2, tub.getY() - tubHeight / 2);
                getShape().lineTo(tub.getX() + tubWidth / 2, tub.getY() + tubHeight / 2);
                getShape().lineTo(tub.getX() - tubWidth / 2, tub.getY() + tubHeight / 2);
                getShape().closePath();

                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.draw(getShape());

                g2d.setColor(Color.WHITE);
                g2d.fillOval(tub.getX() - ovalWidth / 2, tub.getY() - ovalHeight / 2, ovalWidth, ovalHeight);

                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.drawOval(tub.getX() - ovalWidth / 2, tub.getY() - ovalHeight / 2, ovalWidth, ovalHeight);
                break;
            case 1,3:
                if (tub.getParent() instanceof Room room) {
                    if (tubHeight > room.getWidthCm()) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("ROTATED_DIMENSIONS_GREATER_THAN_ROOM_WIDTH_OR_HEIGHT", MessageType.WARNING);
                        tub.setRotateRatio(0);
                        return;
                    }
                }
                getShape().moveTo(tub.getX() - tubHeight / 2, tub.getY() - tubWidth / 2);
                getShape().lineTo(tub.getX() + tubHeight / 2, tub.getY() - tubWidth / 2);
                getShape().lineTo(tub.getX() + tubHeight / 2, tub.getY() + tubWidth / 2);
                getShape().lineTo(tub.getX() - tubHeight / 2, tub.getY() + tubWidth / 2);
                getShape().closePath();

                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.draw(getShape());

                g2d.setColor(Color.WHITE);
                g2d.fillOval(tub.getX() - ovalHeight / 2, tub.getY() - ovalWidth / 2, ovalHeight, ovalWidth);

                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.drawOval(tub.getX() - ovalHeight / 2, tub.getY() - ovalWidth / 2, ovalHeight, ovalWidth);
                break;
            default:
                break;
        }


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(tub);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), tub.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return tub.getX();
    }

    @Override
    public int getY() {
        return tub.getY();
    }

    @Override
    public void setX(int x) {
        tub.setX(x);
    }

    @Override
    public void setY(int y) {
        tub.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return tub.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return tub.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return tub;
    }

    @Override
    public void setSelected(boolean b, RoomView roomView) {
        this.selected = b;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

}
