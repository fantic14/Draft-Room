package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public interface Painter {

    void paint(Graphics2D g2d, int x, int y, int width, int height);

    int getX();
    int getY();
    void setX(int x);
    void setY(int y);
    int getOriginalWidth();
    int getOriginalHeight();
    boolean elementAt(List<Painter> painters);
    boolean elementAt(Point p);
    RoomElement getElement();
    GeneralPath getShape();
    void setSelected(boolean b, RoomView roomView);
    boolean isSelected();


}


