package raf.draft.dsw.gui.swing.state.controller;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class AddAction extends AbstractRoomAction {

    private String selected;

    public AddAction() {
        putValue(SMALL_ICON, loadIcon("/images/addState.png"));
        putValue(NAME, "Add element");
        putValue(SHORT_DESCRIPTION, "Add element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        openSelectionWindow();
    }

    private void openSelectionWindow(){
        JFrame choose = new JFrame();
        JPanel choosePanel = new JPanel();
        JButton bed = new JButton(loadIcon("/images/bed.png"));
        bed.addActionListener(e -> {
            selected = "bed";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton boiler = new JButton(loadIcon("/images/boiler.png"));
        boiler.addActionListener(_ -> {
            selected = "boiler";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton closet = new JButton(loadIcon("/images/closet.png"));
        closet.addActionListener(_ -> {
            selected = "closet";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton door = new JButton(loadIcon("/images/door.png"));
        door.addActionListener(_ -> {
            selected = "door";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton sink = new JButton(loadIcon("/images/sink.png"));
        sink.addActionListener(_ -> {
            selected = "sink";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton table = new JButton(loadIcon("/images/table.png"));
        table.addActionListener(_ -> {
            selected = "table";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton toilet = new JButton(loadIcon("/images/toilet.png"));
        toilet.addActionListener(_ -> {
            selected = "toilet";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton tub = new JButton(loadIcon("/images/tub.png"));
        tub.addActionListener(_ -> {
            selected = "tub";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });
        JButton washingMachine = new JButton(loadIcon("/images/washingmachine.png"));
        washingMachine.addActionListener(_ -> {
            selected = "washing machine";
            MainFrame.getInstance().getProjectView().startAddState(selected);
            choose.dispose();
        });

        addAll(choosePanel, bed, boiler, closet, door, sink, table, toilet, tub, washingMachine);

        choose.add(choosePanel);
        choose.pack();
        choose.setMinimumSize(choose.getSize());
        choose.setLocationRelativeTo(null);
        choose.setTitle("Choose");
        choose.setResizable(false);
        choose.setVisible(true);
    }

    private void addAll(JPanel panel, Component... components){
        for (Component component : components) {
            panel.add(component);
        }
    }
}
