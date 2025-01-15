package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.net.URL;
import java.awt.*;

public class DraftNodeFactory {

    public DraftNodeFactory() {
    }

    public DraftNode createNode(DraftNode parent) {
        if (parent instanceof ProjectExplorer) {
            if (!DraftRoomChildrenRepository.getInstance().getNodes().contains(parent)) {
                DraftRoomChildrenRepository.getInstance().addChildToList(parent);
            }
            String input = JOptionPane.showInputDialog("Type in a project name");
            if (input != null) {
                if (!checkNameAvailability(input))
                    return null;
                if (!input.isBlank()) {
                    return new Project(input, parent, "User X", null);
                } else {
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("CANNOT_CREATE_NAMELESS_NODE", MessageType.WARNING);
                }
            }
        } else if (parent instanceof Project) {
            Object[] possibleValues = {"Building", "Room"};
            Icon icon = loadIcon("/images/bobTheBuilder.png");

            Object choise = JOptionPane.showInputDialog(null, "Choose", "Building or Room?", JOptionPane.INFORMATION_MESSAGE, icon, possibleValues, possibleValues[0]);
            if (choise != null) {
                if (choise.equals("Building")) {
                    String input = JOptionPane.showInputDialog("Type in a building name");
                    if (input != null) {
                        if (!checkNameAvailability(input))
                            return null;
                        if (!input.isBlank()) {
                            return new Building(input, parent);
                        } else {
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("CANNOT_CREATE_NAMELESS_NODE", MessageType.WARNING);
                        }
                    }
                } else if (choise.equals("Room")) {
                    String input = JOptionPane.showInputDialog("Type in a room name");
                    if (input != null) {
                        if (input.isBlank()) {
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("CANNOT_CREATE_NAMELESS_NODE", MessageType.WARNING);
                            return null;
                        }
                        String widthInput = JOptionPane.showInputDialog("Type in a room width (cm)");
                        if (widthInput == null)
                            return null;
                        String heightInput = JOptionPane.showInputDialog("Type in a room height (cm)");
                        if (heightInput == null)
                            return null;
                        if (widthInput.isBlank() || heightInput.isBlank()) {
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("CANNOT_CREATE_DETAILLESS_NODE", MessageType.WARNING);
                            return null;
                        }

                        try {
                            if (!checkNameAvailability(input))
                                return null;
                            int width = Integer.parseInt(widthInput);
                            int height = Integer.parseInt(heightInput);
                            return new Room(input, width, height, parent);
                        } catch (NumberFormatException e) {
                            MessageGenerator mg = new MessageGenerator();
                            mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
                            return null;
                        }
                    }
                }
            }
        } else if (parent instanceof Building) {
            String input = JOptionPane.showInputDialog("Type in a room name");

            if (input != null) {
                if (input.isBlank()) {
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("CANNOT_CREATE_NAMELESS_NODE", MessageType.WARNING);
                    return null;
                }
                String widthInput = JOptionPane.showInputDialog("Type in a room width (cm)");
                if (widthInput == null)
                    return null;
                String heightInput = JOptionPane.showInputDialog("Type in a room height (cm)");
                if (heightInput == null)
                    return null;

                if (widthInput.isBlank() || heightInput.isBlank()) {
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("CANNOT_CREATE_DETAILLESS_NODE", MessageType.WARNING);
                    return null;
                }


                try {
                    if (!checkNameAvailability(input))
                        return null;
                    Double width = Double.valueOf(widthInput);
                    Double height = Double.valueOf(heightInput);
                    return new Room(input, width, height, parent);
                } catch (NumberFormatException e) {
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("INVALID_DIMENSIONS", MessageType.ERROR);
                    return null;
                }
            }
        }
        return null;
    }

    private boolean checkNameAvailability(String name){
        for (DraftNode node : DraftRoomChildrenRepository.getInstance().getNodes()) {
            if (name.equalsIgnoreCase(node.getName())) {
                MessageGenerator mg = new MessageGenerator();
                mg.generateMessage("CANNOT_CREATE_NODE_WITH_SAME_NAME_AS_OTHER", MessageType.WARNING);
                return false;
            }
        }
        return true;
    }

    private Icon loadIcon(String path) {
        Icon icon = null;
        URL ImageURL = getClass().getResource(path);
        if (ImageURL != null) {
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            icon = new ImageIcon(newImg);
        } else {
            System.err.println("File bobTheBuilder.png not found");
        }

        return icon;
    }
}
