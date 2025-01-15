package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Closet;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class ClosetPainter extends PrimalPainter{

    private Closet closet;
    private boolean selected;
    private Room room;

    public ClosetPainter(Closet closet) {
        super(closet);
        this.closet = closet;
        room = (Room)closet.getParent();
        this.closet.setElementPainter(this);
    }

    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {

        GeneralPath linePath;
        int lineX, lineY;
        int dotSize = 6;
        int leftDotX;
        int rightDotX;

        int closetWidth = (int) (closet.getWidthRatio()*width);
        int closetHeight = (int) (closet.getHeightRatio()*height);

        if (closet.getX() == -1 && closet.getY() == -1) {
            closet.setX(x + width - closetWidth * 2);
            closet.setY(y + (height - closetHeight) / 2);
        }

        switch (closet.getRotateRatio()) {
            case 0,2:
                setShape(new GeneralPath());
                getShape().moveTo(closet.getX(), closet.getY());
                getShape().lineTo(closet.getX() + closetWidth, closet.getY());
                getShape().lineTo(closet.getX() + closetWidth, closet.getY() + closetHeight);
                getShape().lineTo(closet.getX(), closet.getY() + closetHeight);
                getShape().closePath();

                linePath = new GeneralPath();
                lineX = closet.getX() + closetWidth / 2;
                linePath.moveTo(lineX, closet.getY());
                linePath.lineTo(lineX, closet.getY() + closetHeight);

                leftDotX = lineX - closetWidth / 4 - dotSize / 2;
                rightDotX = lineX + closetWidth / 4 - dotSize / 2;

                g2d.setColor(Color.WHITE);
                g2d.fill(getShape());
                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.draw(getShape());
                g2d.draw(linePath);
                g2d.fillOval(leftDotX, closet.getY() + closetHeight / 2 - dotSize / 2, dotSize, dotSize);
                g2d.fillOval(rightDotX, closet.getY() + closetHeight / 2 - dotSize / 2, dotSize, dotSize);
                break;
            case 1,3:
                if (closet.getParent() instanceof Room room) {
                    if (closetHeight > room.getWidthCm()) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("ROTATED_DIMENSIONS_GREATER_THAN_ROOM_WIDTH_OR_HEIGHT", MessageType.WARNING);
                        closet.setRotateRatio(0);
                        return;
                    }
                }
                closetWidth = (int) (closet.getHeightRatio()*height);
                closetHeight = (int) (closet.getWidthRatio()*width);
                setShape(new GeneralPath());
                getShape().moveTo(closet.getX(), closet.getY());
                getShape().lineTo(closet.getX() + closetWidth, closet.getY());
                getShape().lineTo(closet.getX() + closetWidth, closet.getY() + closetHeight);
                getShape().lineTo(closet.getX(), closet.getY() + closetHeight);
                getShape().closePath();

                linePath = new GeneralPath();
                lineY = closet.getY() + closetHeight / 2;
                linePath.moveTo(closet.getX(), lineY);
                linePath.lineTo(closet.getX() + closetWidth, lineY);

                leftDotX = closet.getX() + closetWidth / 2 - dotSize / 2;
                rightDotX = closet.getX() + closetWidth / 2 - dotSize / 2;

                g2d.setColor(Color.WHITE);
                g2d.fill(getShape());
                g2d.setColor(selected ? Color.RED : Color.BLACK);
                g2d.draw(getShape());
                g2d.draw(linePath);
                g2d.fillOval(leftDotX, closet.getY() + closetHeight / 4 - dotSize / 2, dotSize, dotSize);
                g2d.fillOval(rightDotX, closet.getY() + 3*closetHeight / 4 - dotSize / 2, dotSize, dotSize);
                break;
            default:
                break;
        }


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(closet);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), closet.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }



    @Override
    public int getX() {
        return closet.getX();
    }

    @Override
    public int getY() {
        return closet.getY();
    }

    @Override
    public void setX(int x) {
        closet.setX(x);
    }

    @Override
    public void setY(int y) {
        closet.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return closet.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return closet.getHeight();
    }

    @Override
    public RoomElement getElement() {
        return closet;
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
