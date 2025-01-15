package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.state.StateManager;
import raf.draft.dsw.gui.swing.state.controller.StateActionManager;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ProjectView extends JPanel implements ISubscriber {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private List<DraftNode> rooms = new ArrayList<>();
    private Project lastDisplayedProject;
    private StateManager stateManager;
    private StateActionManager stateActionManager;
    private List<RoomView> roomViews = new ArrayList<>();


    public ProjectView() {
        this.stateManager = new StateManager();
        this.stateActionManager = new StateActionManager();

        setLayout(new BorderLayout());
        add(createActionsBar(), BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);
    }

    public JToolBar createActionsBar(){
        JToolBar actionsBar = new JToolBar();
        actionsBar.add(stateActionManager.getAddAction());
        actionsBar.add(stateActionManager.getCopyAction());
        actionsBar.add(stateActionManager.getPasteAction());
        actionsBar.add(stateActionManager.getDeleteAction());
        actionsBar.add(stateActionManager.getEditElementAction());
        actionsBar.add(stateActionManager.getEditRoomAction());
        actionsBar.add(stateActionManager.getMoveAction());
        actionsBar.add(stateActionManager.getResizeAction());
        actionsBar.add(stateActionManager.getRotateAction());
        actionsBar.add(stateActionManager.getSelectAction());
        actionsBar.add(stateActionManager.getZoomAction());


        return actionsBar;
    }

    public JPanel createRoomPanel(Room room) {
        JPanel roomPanel = new JPanel(new BorderLayout());
        roomPanel.setLayout(new BorderLayout());

        RoomView roomView = new RoomView(room);
        this.roomViews.add(roomView);
        roomPanel.add(roomView, BorderLayout.CENTER);

        return roomPanel;
    }

    public void addRoomTab(DraftNode room, JPanel roomPanel) {
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getTitleAt(i).equals("Room: " + room.getName())) {
                return;
            }
        }

        String tabTitle = room.getName();
        tabbedPane.addTab(tabTitle, roomPanel);
        tabbedPane.setSelectedComponent(roomPanel);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(Color.TRANSLUCENT));
        JLabel titleLabel = new JLabel("Room: " + room.getName() + " Building: " + room.getParent().getName());
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, titlePanel);
    }

    public void addRoom(Project project) {
        if (lastDisplayedProject != project) {
            lastDisplayedProject = project;
            rooms.clear();
        }

        for (DraftNode n : lastDisplayedProject.getChildren()) {
            if (n instanceof Building) {
                Building building = (Building) n;
                for (DraftNode room : building.getChildren()) {
                    if (room instanceof Room && !rooms.contains(room)) {
                        rooms.add(room);
                    }
                }
            }
            if (n instanceof Room && !rooms.contains(n)) {
                rooms.add(n);
            }
        }
        refreshTabs();
        MainFrame.getInstance().getSplitPane().setRightComponent(this);
    }

    private void refreshTabs() {
        tabbedPane.removeAll();
        for (DraftNode node : rooms) {
            JPanel roomPanel = createRoomPanel((Room) node);
            tabbedPane.addTab("Room: " + node.getName(), roomPanel);
            JPanel titlePanel = new JPanel(new BorderLayout());
            Color tabColor = new Color(Color.TRANSLUCENT);
            if (node.getParent() instanceof Building) {
                Building parent = (Building) node.getParent();
                if (parent.getTabColor() == null) {
                    tabColor = new Color((int) (Math.random() * 0x1000000));
                    parent.setTabColor(tabColor);
                } else tabColor = parent.getTabColor();
            }
            titlePanel.setBackground(tabColor);
            JLabel titleLabel = new JLabel("Room: " + node.getName());
            titleLabel.setForeground(Color.WHITE);
            titlePanel.add(titleLabel, BorderLayout.CENTER);
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, titlePanel);
        }
        MainFrame.getInstance().getActionManager().getOrganiseMyRoomAction().setEnabled(!rooms.isEmpty());
        MainFrame.getInstance().getActionManager().getShuffleOrganisedRoomAction().setEnabled(!rooms.isEmpty());
        tabbedPane.addChangeListener(_ -> {
            try {
                JPanel current = (JPanel) tabbedPane.getSelectedComponent();
                RoomView roomView = (RoomView) current.getComponent(0);
                MainFrame.getInstance().getActionManager().getOrganiseMyRoomAction().setMyRoomView(roomView);
            } catch (NullPointerException _){}
        });
    }

    @Override
    public void update(Object data) {
        reloadTabs();
    }

    public void reloadTabs() {
        if (lastDisplayedProject != null) {
            rooms.clear();
            addRoom(lastDisplayedProject);
        }
    }

    public Project getLastDisplayedProject() {
        return lastDisplayedProject;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setLastDisplayedProject(Project lastDisplayedProject) {
        this.lastDisplayedProject = lastDisplayedProject;
    }

    public void startAddState(String element){
        this.stateManager.setAddState(element);
    }

    public void startCopyState(){
        this.stateManager.setCopyState();
    }

    public void startPasteState(){
        this.stateManager.setPasteState();
    }


    public void startDeleteState(){
        this.stateManager.setDeleteState();
    }

    public void startEditElementState(){
        this.stateManager.setEditElementState();
    }

    public void startEditRoomState(){
        this.stateManager.setEditRoomState();
    }

    public void startMoveState(){
        this.stateManager.setMoveState();
    }

    public void startResizeState(){
        this.stateManager.setResizeState();
    }

    public void startRotateState(){
        this.stateManager.setRotateState();
    }

    public void startSelectState(){
        this.stateManager.setSelectState();
    }

    public void startZoomState(){
        this.stateManager.setZoomState();
    }

}
