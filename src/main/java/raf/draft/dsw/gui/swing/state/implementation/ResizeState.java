package raf.draft.dsw.gui.swing.state.implementation;

import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.state.State;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ResizeState implements State {

//    private int x, y, width, height;
//    private boolean flag = false;
//    private Painter resizing;
//    private RoomElement roomElement;
//    private int i=0;

    /// ODUSTAJEM

    @Override
    public void myMousePressed(MouseEvent e, RoomView roomView) {
//        Point point = e.getPoint();
//
//        for(Painter p: roomView.getPainters()){
//            if(p.isSelected() && p.elementAt(point)){
//                if(point.x>=(p.getElement().getX()+p.getElement().getWidth())-10
//                        && point.x<=p.getElement().getX()+p.getElement().getWidth()
//                        && point.y>=p.getElement().getY()+p.getElement().getHeight()-10
//                        && point.y<=p.getElement().getY()+p.getElement().getHeight()) {
//                    System.out.println("uso");
//                    flag = true;
//                    x = point.x;
//                    y = point.y;
//                    width=p.getElement().getWidth();
//                    height=p.getElement().getHeight();
//
//                    resizing = p;
//                //}
//            }
//        }
    }

    @Override
    public void myMouseReleased(MouseEvent e, RoomView roomView) {
//        roomView.refreshView();
//        resizing = null;
//        flag = false;
    }

    @Override
    public void myMouseDragged(MouseEvent e, RoomView roomView) {

//        Point point = e.getPoint();
//        for(Painter painter : roomView.getPainters()){
//            if(painter.equals(resizing)){
//                int dimX=width+(point.x-x);
//                int dimY=height+(point.y-y);
//                int locationX = painter.getElement().getX();
//                int locationY = painter.getElement().getY();
//                int newWidth = dimX;
//                int newHeight = dimY;
//                Painter resizedPainter;
//                if(resizing.getElement() instanceof Tub){
//                    resizedPainter = new TubPainter(new Tub(roomView.getRoom(), 0.5, 0.5, 0));
//                    resizedPainter.getElement().setX(locationX);
//                    resizedPainter.getElement().setY(locationY);
//                    resizedPainter.getElement().setWidth(newWidth);
//                    resizedPainter.getElement().setHeight(newHeight);
//                    roomView.getPainters().remove(resizing);
//                    resizing = resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Bed){
//                    roomElement=new Bed(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new BedPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Boiler){
//                    roomElement=new Boiler(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new BoilerPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Closet){
//                    roomElement=new Closet(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new ClosetPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Door){
//                    roomElement=new Door(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new DoorPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Sink){
//                    roomElement=new Sink(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new SinkPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Table){
//                    roomElement=new Table(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new TablePainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else if(resizing.getRoomElement() instanceof Toilet){
//                    roomElement=new Toilet(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new ToiletPainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                else{
//                    roomElement=new WashingMachine(list1,list2, resizing.getRoomElement().getColor());
//                    resizedPainter=new WashingMachinePainter(roomElement);
//                    roomView.getPainters().add(resizedPainter);
//                    roomView.getPainters().remove(resizing);
//                    resizing =resizedPainter;
//                }
//                break;
//            }
//
//        }
//        roomView.refreshView();
    }

    @Override
    public void myMouseScrolled(MouseWheelEvent e, RoomView roomView) {

    }

}
