package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.repository.DraftRoomChildrenRepository;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class DraftTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {


    private Object clickedOn =null;
    private JTextField edit=null;

    public DraftTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn =arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }

    public void actionPerformed(ActionEvent e){

        if (!(clickedOn instanceof DraftTreeItem))
            return;

        DraftTreeItem clicked = (DraftTreeItem) clickedOn;
        if ( !e.getActionCommand().isEmpty() || !e.getActionCommand().isBlank()){
            for (DraftNode node : DraftRoomChildrenRepository.getInstance().getNodes()) {
                if (node.getName().equals(e.getActionCommand())){
                    MessageGenerator mg = new MessageGenerator();
                    mg.generateMessage("CANNOT_HAVE_SAME_NAME_AS_OTHER_NODE", MessageType.NOTIFICATION);
                    return;
                }
            }
            clicked.setName(e.getActionCommand());
            System.out.println("Renamed into \"" + e.getActionCommand() + "\"");
            }
        else {
            MessageGenerator mg = new MessageGenerator();
            mg.generateMessage("CANNOT_RENAME_NODE_EMPTY", MessageType.WARNING);
        }
    }



}
