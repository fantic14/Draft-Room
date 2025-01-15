package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Boiler;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class BoilerPainter extends PrimalPainter{

    private Boiler boiler;
    private boolean selected;
    private Room room;


    public BoilerPainter(Boiler boiler) {
        super(boiler);
        this.boiler = boiler;
        this.room = (Room) boiler.getParent();
        this.boiler.setElementPainter(this);
    }



    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int boilerRadius = (int) (boiler.getWidthRatio()*width)/2;

        if (boiler.getX() == -1 && boiler.getY() == -1) {
            boiler.setX(x + (width - 2 * boilerRadius) / 2);
            boiler.setY(y + height - 2 * boilerRadius);
        }


        setShape(new GeneralPath());
        getShape().append(new java.awt.geom.Ellipse2D.Double(boiler.getX(), boiler.getY(), 2 * boilerRadius, 2 * boilerRadius), false);
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.fill(getShape());

        GeneralPath xShape = new GeneralPath();
        int centerX = boiler.getX() + boilerRadius;
        int centerY = boiler.getY() + boilerRadius;
        int lineLength = boilerRadius / 2;
        xShape.moveTo(centerX - lineLength, centerY - lineLength);
        xShape.lineTo(centerX + lineLength, centerY + lineLength);

        xShape.moveTo(centerX + lineLength, centerY - lineLength);
        xShape.lineTo(centerX - lineLength, centerY + lineLength);

        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(xShape);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(boiler);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), boiler.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return boiler.getX();
    }

    @Override
    public int getY() {
        return boiler.getY();
    }

    @Override
    public void setX(int x) {
        boiler.setX(x);
    }

    @Override
    public void setY(int y) {
        boiler.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return boiler.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return boiler.getHeight();
    }

    @Override
    public RoomElement getElement() {
        return boiler;
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
