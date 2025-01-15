package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.RoomElement;
import raf.draft.dsw.model.structures.RoomElements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrganiseMyRoomAction extends AbstractRoomAction{

    private JButton shuffle;
    private RoomView myRoomView;

    public OrganiseMyRoomAction() {
        putValue(SMALL_ICON, loadIcon("/images/organiseMyRoomAction.png"));
        putValue(NAME, "Organise my room");
        putValue(SHORT_DESCRIPTION, "Organise my room");
        this.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel widthLabel = new JLabel("Width");
        JTextField widthField = new JTextField(5);
        widthField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JLabel heightLabel = new JLabel("Height");
        JTextField heightField = new JTextField(5);
        heightField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("Bed");
        comboBox.addItem("Boiler");
        comboBox.addItem("Closet");
        comboBox.addItem("Door");
        comboBox.addItem("Sink");
        comboBox.addItem("Table");
        comboBox.addItem("Toilet");
        comboBox.addItem("Tub");
        comboBox.addItem("Washing machine");

        inputPanel.add(widthLabel);
        inputPanel.add(widthField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(comboBox);

        panel.add(inputPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JButton addButton = new JButton("Add");
        DefaultListModel<String> listModel = new DefaultListModel<>();

        addButton.addActionListener(_ -> {
            String selectedElement = (String) comboBox.getSelectedItem();
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());

            if (selectedElement != null) {
                listModel.addElement(selectedElement + " ( " + width + " x " + height + " )");
            }
        });

        centerPanel.add(addButton, BorderLayout.NORTH);

        JList<String> elementList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(elementList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JButton removeButton = new JButton("<html>R<br>E<br>M<br>O<br>V<br>E<br><br>S<br>E<br>L<br>E<br>C<br>T<br>E<br>D</html>");
        removeButton.addActionListener(_ -> {
            try {
                listModel.remove(elementList.getSelectedIndex());
            }
            catch(ArrayIndexOutOfBoundsException _) {

            }
        });
        centerPanel.add(removeButton, BorderLayout.EAST);

        panel.add(centerPanel, BorderLayout.CENTER);

        elementList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel itemPanel = new JPanel(new BorderLayout());
                JLabel label = new JLabel(value.toString());

                itemPanel.add(label, BorderLayout.CENTER);

                if (isSelected) {
                    itemPanel.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    itemPanel.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }

                return itemPanel;
            }
        });

        JButton organiseButton = new JButton("Organise");
        JFrame components = new JFrame("Adding elements");

        List<RoomElement> elements = new ArrayList<>();
        organiseButton.addActionListener(_ -> {
            for (int i = 0; i < listModel.size(); i++) {
                String elementRaw = listModel.get(i);
                String[] elementArray = elementRaw.split(" ");
                String element = elementArray[0];
                switch (element) {
                    case "Bed" -> {
                        RoomElement el = new Bed(myRoomView.getRoom(), 0.1, 0.1, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Boiler" -> {
                        RoomElement el = new Boiler(myRoomView.getRoom(), 0.2, 0.2, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Closet" -> {
                        RoomElement el = new Closet(myRoomView.getRoom(), 0.1, 0.1, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Door" -> {
                        RoomElement el = new Door(myRoomView.getRoom(), 0.0, 0.1, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Sink" -> {
                        RoomElement el = new Sink(myRoomView.getRoom(), 0.2, 0.2, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Table" -> {
                        RoomElement el = new Table(myRoomView.getRoom(), 0.3, 0.3, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Toilet" -> {
                        RoomElement el = new Toilet(myRoomView.getRoom(), 0.4, 0.4, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Tub" -> {
                        RoomElement el = new Tub(myRoomView.getRoom(), 0.5, 0.5, 0);
                        el.setHeight(Integer.parseInt(elementArray[4]));
                        el.setWidth(Integer.parseInt(elementArray[2]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                    case "Washing" -> {
                        RoomElement el = new WashingMachine(myRoomView.getRoom(), 0.6, 0.6, 0);
                        el.setHeight(Integer.parseInt(elementArray[5]));
                        el.setWidth(Integer.parseInt(elementArray[3]));
                        el.setWidthRatio(el.getWidth()*3.78 / myRoomView.getRoom().getWidthInPixels());
                        el.setHeightRatio(el.getHeight()*3.78 / myRoomView.getRoom().getHeightInPixels());
                        DraftRoomChildrenRepository.getInstance().addChildToList(el);
                        DraftTreeItem elementItem = new DraftTreeItem(el, el.getParent().getTreeItem());
                        el.setTreeItem(elementItem);
                        el.getParent().getTreeItem().add(elementItem);
                        ((Room)el.getParent()).addChild(el);
                        elements.add(el);
                    }
                }
            }
            RoomElement greatest = elements.getFirst();
            for (RoomElement element : elements) {
                if ((element.getHeight()*element.getWidth()) > (greatest.getHeight()*greatest.getWidth())) {
                    greatest = element;
                }
            }

            Collections.shuffle(elements);

            int gridHeight = (int)myRoomView.getRoom().getHeightCm() / greatest.getHeight();
            int gridWidth = (int)myRoomView.getRoom().getWidthCm() / greatest.getWidth();
            int heightDifference = myRoomView.getRealRoomHeightInPx() /gridHeight;
            int widthDifference = myRoomView.getRealRoomWidthInPx() /gridWidth;



            double pixelsPerCmWidth = myRoomView.getRealRoomWidthInPx() / myRoomView.getRoom().getWidthCm();
            double pixelsPerCmHeight = myRoomView.getRealRoomHeightInPx() / myRoomView.getRoom().getHeightCm();
            int rightMax = gridWidth - 1;
            int botMax = gridHeight - 1;
            int leftMax = 0;
            int topMax = 0;
            String direction = "right";
            int leftRightRoom = (myRoomView.getWidth() - myRoomView.getRealRoomWidthInPx())/2;
            int topBottomRoom = (myRoomView.getHeight() - myRoomView.getRealRoomHeightInPx())/2;
            int cnt = 1;
            int x = 1;
            int y = 0;

            elements.getFirst().setX(leftRightRoom);
            elements.getFirst().setY(topBottomRoom);

            while (cnt < elements.size()){
                RoomElement element = elements.get(cnt);
                if (cnt != elements.size()-1) {
                    if (x == rightMax && y == topMax) {
                        direction = "down";
                        if (topMax != 0)
                            leftMax++;
                    }
                    if (y == botMax && x == rightMax) {
                        direction = "left";
                        topMax++;

                    }
                    if (x == leftMax && y == botMax) {
                        direction = "up";
                        rightMax--;

                    }
                    if (y == topMax && x == leftMax) {
                        direction = "right";
                        botMax--;
                    }
                }
                switch (direction){
                    case "right":
                        if (x == leftMax) {
                            element.setX(leftRightRoom + x*widthDifference);
                            element.setY(topBottomRoom + y*heightDifference);
                        } else {
                            element.setX(leftRightRoom + x * widthDifference + widthDifference / 2 - (int) (element.getWidth() * pixelsPerCmWidth) / 2);
                            element.setY(topBottomRoom + y*heightDifference);
                        }
                        x++;
                        break;
                    case "down":
                        if (y == topMax) {
                            element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                            element.setY(topBottomRoom + y*heightDifference);
                        } else {
                            element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                            element.setY(topBottomRoom + (int)(y*heightDifference*1.5) - (int)(element.getHeight()*pixelsPerCmHeight)/2);
                        }
                        y++;
                        break;
                    case "left":
                        if (x == rightMax){
                            element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                            element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                        } else {
                            element.setX(leftRightRoom + x*widthDifference + widthDifference/2 - (int) (element.getWidth() * pixelsPerCmWidth)/2);
                            element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                        }
                        x--;
                        break;
                    case "up":
                        if (y == botMax){
                            element.setX(leftRightRoom + x*widthDifference);
                            element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                        } else {
                            element.setX(leftRightRoom + x*widthDifference);
                            element.setY(topBottomRoom + (int)(y*heightDifference*1.5) - (int)(element.getHeight()*pixelsPerCmHeight)/2);
                        }
                        y--;
                        break;
                }
                cnt++;
            }
            myRoomView.refreshView();
            components.dispose();
            MainFrame.getInstance().getActionManager().getShuffleOrganisedRoomAction().takeInfo(myRoomView, elements);
            MainFrame.getInstance().getActionManager().getOrganiseMyRoomAction().setEnabled(true);
        });


        centerPanel.add(organiseButton, BorderLayout.SOUTH);

        components.setLocationRelativeTo(MainFrame.getInstance());
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        components.setSize(screenSize.width / 2-100, screenSize.height / 2-50);
        components.setResizable(false);
        components.add(panel);
        components.setVisible(true);
    }

    protected void putInRoom(List<RoomElement> elements) {
        RoomElement greatest = elements.getFirst();
        for (RoomElement element : elements) {
            if ((element.getHeight()*element.getWidth()) > (greatest.getHeight()*greatest.getWidth())) {
                greatest = element;
            }
        }

        Collections.shuffle(elements);

        int gridHeight = (int)myRoomView.getRoom().getHeightCm() / greatest.getHeight();
        int gridWidth = (int)myRoomView.getRoom().getWidthCm() / greatest.getWidth();
        int heightDifference = myRoomView.getRealRoomHeightInPx() /gridHeight;
        int widthDifference = myRoomView.getRealRoomWidthInPx() /gridWidth;



        double pixelsPerCmWidth = myRoomView.getRealRoomWidthInPx() / myRoomView.getRoom().getWidthCm();
        double pixelsPerCmHeight = myRoomView.getRealRoomHeightInPx() / myRoomView.getRoom().getHeightCm();
        int rightMax = gridWidth - 1;
        int botMax = gridHeight - 1;
        int leftMax = 0;
        int topMax = 0;
        String direction = "right";
        int leftRightRoom = (myRoomView.getWidth() - myRoomView.getRealRoomWidthInPx())/2;
        int topBottomRoom = (myRoomView.getHeight() - myRoomView.getRealRoomHeightInPx())/2;
        int cnt = 1;
        int x = 1;
        int y = 0;

        elements.getFirst().setX(leftRightRoom);
        elements.getFirst().setY(topBottomRoom);

        while (cnt < elements.size()){
            RoomElement element = elements.get(cnt);
            if (cnt != elements.size()-1) {
                if (x == rightMax && y == topMax) {
                    direction = "down";
                    if (topMax != 0)
                        leftMax++;
                }
                if (y == botMax && x == rightMax) {
                    direction = "left";
                    topMax++;

                }
                if (x == leftMax && y == botMax) {
                    direction = "up";
                    rightMax--;

                }
                if (y == topMax && x == leftMax) {
                    direction = "right";
                    botMax--;
                }
            }
            switch (direction){
                case "right":
                    if (x == leftMax) {
                        element.setX(leftRightRoom + x*widthDifference);
                        element.setY(topBottomRoom + y*heightDifference);
                    } else {
                        element.setX(leftRightRoom + x * widthDifference + widthDifference / 2 - (int) (element.getWidth() * pixelsPerCmWidth) / 2);
                        element.setY(topBottomRoom + y*heightDifference);
                    }
                    x++;
                    break;
                case "down":
                    if (y == topMax) {
                        element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                        element.setY(topBottomRoom + y*heightDifference);
                    } else {
                        element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                        element.setY(topBottomRoom + (int)(y*heightDifference*1.5) - (int)(element.getHeight()*pixelsPerCmHeight)/2);
                    }
                    y++;
                    break;
                case "left":
                    if (x == rightMax){
                        element.setX(myRoomView.getWidth() - leftRightRoom - (int) (element.getWidth() * pixelsPerCmWidth));
                        element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                    } else {
                        element.setX(leftRightRoom + x*widthDifference + widthDifference/2 - (int) (element.getWidth() * pixelsPerCmWidth)/2);
                        element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                    }
                    x--;
                    break;
                case "up":
                    if (y == botMax){
                        element.setX(leftRightRoom + x*widthDifference);
                        element.setY(myRoomView.getHeight() - topBottomRoom - (int) (element.getHeight() * pixelsPerCmHeight));
                    } else {
                        element.setX(leftRightRoom + x*widthDifference);
                        element.setY(topBottomRoom + (int)(y*heightDifference*1.5) - (int)(element.getHeight()*pixelsPerCmHeight)/2);
                    }
                    y--;
                    break;
            }
            cnt++;
        }
        myRoomView.refreshView();
    }

    public RoomView getMyRoomView() {
        return myRoomView;
    }

    public void setMyRoomView(RoomView myRoomView) {
        this.myRoomView = myRoomView;
    }

    public JButton getShuffle() {
        return shuffle;
    }
}
