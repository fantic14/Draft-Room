package raf.draft.dsw.gui.swing.painter;

import raf.draft.dsw.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.List;

public abstract class PrimalPainter implements Painter{

    private RoomElement element;
    private GeneralPath shape;

    public PrimalPainter(RoomElement element) {
        this.element = element;
    }

    @Override
    public boolean elementAt(List<Painter> painters){
        for (Painter painter : painters) {
            if (painter != this && shape.intersects(painter.getShape().getBounds2D()))
                return true;
        }
        return false;
    }

    @Override
    public boolean elementAt(Point p) {
        return shape.contains(p);
    }

    @Override
    public RoomElement getElement() {
        return element;
    }

    public GeneralPath getShape() {
        return shape;
    }

    public void setShape(GeneralPath shape) {
        this.shape = shape;
    }
}
