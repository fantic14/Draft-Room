package raf.draft.dsw.model.structures;


import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.DraftNode;


public class Room extends DraftNodeComposite {

    private double widthCm;
    private double heightCm;
    private DraftTreeItem roomTreeItem;
    private RoomView roomView;
    private int roomAspectRatio;
    public static final double CM_TO_PIXELS = 3.78;

    public Room(String name, double widthCm, double heightCm, DraftNode parent) {
        super(name, parent);
        this.widthCm = widthCm;
        this.heightCm = heightCm;
    }


    public double getRoomAspectRatio() {
        return widthCm/heightCm;
    }

    public void setRoomAspectRatio(int roomAspectRatio) {
        this.roomAspectRatio = roomAspectRatio;
    }

    public double getWidthCm() {
        return widthCm;
    }

    public double getHeightCm() {
        return heightCm;
    }

    public void setWidthCm(double widthCm) {
        this.widthCm = widthCm;
    }

    public void setHeightCm(double heightCm) {
        this.heightCm = heightCm;
    }

    public int getWidthInPixels() {
        return (int) (widthCm * CM_TO_PIXELS);
    }

    public int getHeightInPixels() {
        return (int) (heightCm * CM_TO_PIXELS);
    }

    public void setRoomTreeItem(DraftTreeItem roomTreeItem) {
        this.roomTreeItem = roomTreeItem;
    }

    public DraftTreeItem getRoomTreeItem() {
        return roomTreeItem;
    }

    @Override
    public void addChild(DraftNode child) {
        super.getChildren().add(child);
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public void setRoomView(RoomView roomView) {
        this.roomView = roomView;
    }
}
