package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.painter.Painter;
import raf.draft.dsw.gui.swing.state.controller.MyMouseListener;
import raf.draft.dsw.gui.swing.painter.*;
import raf.draft.dsw.gui.swing.state.controller.StateActionManager;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;

public class RoomView extends JPanel implements ISubscriber {

    /// napraviti da prilikom biranja drugog taba, currentState postane NothingState
    private final Room room;
    private MyMouseListener myMouseListener;
    private List<Painter> painters = new ArrayList();
    private double zoomFactor = 1;
    private boolean zoomming;
    private Set<RoomElement> copiedElements = new HashSet<>();

    public RoomView(Room room) {
        this.room = room;
        this.room.setRoomView(this);
        this.myMouseListener = new MyMouseListener(this);
        this.addMouseListener(myMouseListener);
        this.addMouseMotionListener(myMouseListener);
        this.addMouseWheelListener(myMouseListener);

        setLayout(new BorderLayout());


        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderRoomElements(g);
            }
        };


        add(drawingPanel, BorderLayout.CENTER);
    }

    private void renderRoomElements(Graphics g) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();
        double roomWidthInPixels = room.getWidthInPixels();
        double roomHeightInPixels = room.getHeightInPixels();

        double scaleFactor = Math.min(panelWidth / roomWidthInPixels, panelHeight / roomHeightInPixels);

        roomWidthInPixels *= scaleFactor;
        roomHeightInPixels *= scaleFactor;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform at;
        if(zoomming){
            at = new AffineTransform();
            at.scale(zoomFactor,zoomFactor);
            zoomming=false;
            ((Graphics2D) g).transform(at);
        }

        int roomWidth = (int) roomWidthInPixels;
        int roomHeight = (int) roomHeightInPixels;

        int roomX = (panelWidth - roomWidth) / 2;
        int roomY = (panelHeight - roomHeight) / 2;


        g2d.setColor(Color.WHITE);
        g2d.fillRect(roomX, roomY, roomWidth, roomHeight);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(roomX, roomY, roomWidth, roomHeight);


        for (int i = 0; i < room.getChildren().size(); i++) {
            DraftNode element = room.getChildren().get(i);
            Painter painter = getPainterForElement(element, roomX, roomY, roomWidth, roomHeight, scaleFactor);

            if (painter != null) {
                painter.paint(g2d, roomX, roomY, roomWidth, roomHeight);
            }
        }
    }

    private Painter getPainterForElement(DraftNode element, int roomX, int roomY, int roomWidth, int roomHeight, double scaleFactor) {
        for (Painter p : painters) {
            if (p.getElement() == element) {
                return p;
            }
        }

        Painter painter = null;

        if (element instanceof Bed) {
            painter = new BedPainter((Bed) element);
        } else if (element instanceof Door) {
            painter = new DoorPainter((Door) element);
        } else if (element instanceof Boiler) {
            painter = new BoilerPainter((Boiler) element);
        } else if (element instanceof Closet) {
            painter = new ClosetPainter((Closet) element);
        } else if (element instanceof Sink) {
            painter = new SinkPainter((Sink) element);
        } else if (element instanceof Table) {
            painter = new TablePainter((Table) element);
        } else if (element instanceof Toilet) {
            painter = new ToiletPainter((Toilet) element);
        } else if (element instanceof Tub) {
            painter = new TubPainter((Tub) element);
        } else if (element instanceof WashingMachine) {
            painter = new WashingMachinePainter((WashingMachine) element);
        }

        if (painter != null) {
            painters.add(painter);
        }

        return painter;
    }

    public void refreshView() {
        revalidate();
        repaint();
    }

    public void findFreeLocation(Painter painter) {
        int minX = (this.getWidth() - this.getRealRoomWidthInPx())/2;
        int minY = (this.getHeight() - this.getRealRoomHeightInPx())/2;
        int maxX = minX + this.getRealRoomWidthInPx();
        int maxY = minY + this.getRealRoomHeightInPx();
        while (painter.elementAt(this.getPainters())){
            Random random = new Random();
            painter.getElement().setX(random.nextInt(maxX) + minX);
            painter.getElement().setY(random.nextInt(maxY) + minY);
            System.out.println(painter.getElement().getX());
        }
    }

    public int getRealRoomWidthInPx(){
        double roomAspectRatio = room.getWidthCm()/room.getHeightCm();
        double panelAspectRatio = (double) this.getWidth()/this.getHeight();
        int roomWidthInPixels;
        if (panelAspectRatio>roomAspectRatio)
            roomWidthInPixels = (int) (this.getHeight()*roomAspectRatio);
        else
            roomWidthInPixels = this.getWidth();
        return roomWidthInPixels;
    }

    public int getRealRoomHeightInPx(){
        double roomAspectRatio = room.getWidthCm()/room.getHeightCm();
        double panelAspectRatio = (double) this.getWidth()/this.getHeight();
        int roomHeightInPixels;
        if (panelAspectRatio>roomAspectRatio)
            roomHeightInPixels = this.getHeight();
        else
            roomHeightInPixels = (int) (this.getWidth() / roomAspectRatio);
        return roomHeightInPixels;
    }

    public double getZoomFactor() {
        return zoomFactor;
    }

    public void setZoomFactor(double zoomFactor) {
        /// dodati granice za zumiranje
        if(zoomFactor<this.zoomFactor){
            this.zoomFactor=this.zoomFactor/1.1;
        }
        else{
            this.zoomFactor=zoomFactor;
        }
        this.zoomming =true;
    }

    public Room getRoom() {
        return room;
    }

    public List<Painter> getPainters() {
        return painters;
    }

    public Set<RoomElement> getCopiedElements() {
        return copiedElements;
    }

    @Override
    public void update(Object data) {
        refreshView();
    }
}