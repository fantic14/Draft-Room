package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RenameNodeAction extends AbstractRoomAction{

    public RenameNodeAction() {
        putValue(SMALL_ICON, loadIcon("/images/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if (selected == null){
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("SELECT_NODE_FIRST", MessageType.NOTIFICATION);
            return;
        }
        //String input = JOptionPane.showInputDialog(null, "Type in a new name", "New name", JOptionPane.INFORMATION_MESSAGE);

        JFrame frame = new JFrame();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setSize(screenWidth/3, screenHeight/7);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Change Project");
        frame.setResizable(false);
        JPanel panel = new JPanel(new GridLayout(3,1));

        JLabel lbName = new JLabel("Project name:");
        JTextField tfName = new JTextField();

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(l -> frame.setVisible(false));

        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.addActionListener(l -> {
            if (!(tfName.getText().isBlank())) {
                for (DraftNode node : DraftRoomChildrenRepository.getInstance().getNodes()) {
                    if (node.getName().equals(tfName.getText())) {
                        MessageGenerator mg = new MessageGenerator();
                        mg.generateMessage("CANNOT_HAVE_SAME_NAME_AS_OTHER_NODE", MessageType.NOTIFICATION);
                        return;
                    }
                }
                selected.getDraftNode().setName(tfName.getText());
                frame.setVisible(false);
                System.out.println("Renamed into \"" + tfName.getText() + "\"");
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
        });

        JPanel confirmCancelPanel = new JPanel(new GridLayout(1,2));
        confirmCancelPanel.add(btnCancel);
        confirmCancelPanel.add(btnConfirm);

        panel.add(lbName);
        panel.add(tfName);
        panel.add(confirmCancelPanel);

        frame.add(panel);
        frame.setVisible(true);

    }
}
