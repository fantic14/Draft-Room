package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.Sink;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class SinkPainter extends PrimalPainter{

    private Sink sink;
    private boolean selected;
    private Room room;

    public SinkPainter(Sink sink) {
        super(sink);
        this.sink = sink;
        room = (Room)sink.getParent();
        this.sink.setElementPainter(this);
    }



    @Override
    public void paint(Graphics2D g2d, int x, int y, int width, int height) {
        int sinkWidth = (int) (sink.getWidthRatio()*width);
        int sinkHeight = (int) (sink.getHeightRatio()*height);

        if (sink.getX() == -1 && sink.getY() == -1){
            sink.setX(x + (width - sinkWidth) / 2);
            sink.setY(y + height - sinkHeight);
        }

        setShape(new GeneralPath());

        int dotRadius = 5;
        int dotX = 0;
        int dotY = 0;

        switch (sink.getRotateRatio()) {
            case 0:
                getShape().moveTo(sink.getX(), sink.getY());
                getShape().lineTo(sink.getX() + sinkWidth, sink.getY());
                getShape().lineTo(sink.getX() + sinkWidth / 2.0, sink.getY() + sinkHeight);
                getShape().closePath();
                dotX = sink.getX() + sinkWidth / 2 - dotRadius;
                dotY = sink.getY() + sinkHeight / 2 - dotRadius;
                break;
            case 1:
                getShape().moveTo(sink.getX() + sinkWidth, sink.getY());
                getShape().lineTo(sink.getX() + sinkWidth, sink.getY() + sinkHeight);
                getShape().lineTo(sink.getX(), sink.getY() + sinkWidth / 2.0);
                getShape().closePath();
                dotX = sink.getX() + sinkWidth / 2 - dotRadius;
                dotY = sink.getY() + sinkHeight / 2 - dotRadius;
                break;
            case 2:
                getShape().moveTo(sink.getX(), sink.getY());
                getShape().lineTo(sink.getX() + sinkWidth, sink.getY());
                getShape().lineTo(sink.getX() + sinkWidth / 2.0, sink.getY() - sinkHeight);
                getShape().closePath();
                dotX = sink.getX() + sinkWidth / 2 - dotRadius;
                dotY = sink.getY() - sinkHeight / 2 - dotRadius;
                break;
            case 3:
                getShape().moveTo(sink.getX(), sink.getY());
                getShape().lineTo(sink.getX(), sink.getY() + sinkWidth);
                getShape().lineTo(sink.getX() + sinkHeight, sink.getY() + sinkWidth / 2.0);
                getShape().closePath();
                dotX = sink.getX() + sinkWidth / 2 - dotRadius;
                dotY = sink.getY() + sinkHeight / 2 - dotRadius;
                break;
            default:
                break;
        }


        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.draw(getShape());
        g2d.setColor(Color.WHITE);
        g2d.fill(getShape());



        g2d.setColor(selected ? Color.RED : Color.BLACK);
        g2d.fillOval(dotX, dotY, 2 * dotRadius, 2 * dotRadius);


//        if (this.elementAt(room.getRoomView().getPainters())){
//            room.getChildren().remove(sink);
//            room.getRoomView().getPainters().remove(this);
//            MainFrame.getInstance().getDraftTree().removeChild(room.getRoomTreeItem(), sink.getElementTreeItem());
//            MainFrame.getInstance().getDraftTree().getTreeView().clearSelection();
//            MessageGenerator mg = new MessageGenerator();
//            mg.generateMessage("CANNOT_ADD_ELEMENT_OVER_ANOTHER", MessageType.WARNING);
//        }
    }

    @Override
    public int getX() {
        return sink.getX();
    }

    @Override
    public int getY() {
        return sink.getY();
    }

    @Override
    public void setX(int x) {
        sink.setX(x);
    }

    @Override
    public void setY(int y) {
        sink.setY(y);
    }

    @Override
    public int getOriginalWidth() {
        return sink.getWidth();
    }

    @Override
    public int getOriginalHeight() {
        return sink.getHeight();
    }
    @Override
    public RoomElement getElement() {
        return sink;
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
