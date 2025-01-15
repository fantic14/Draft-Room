package raf.draft.dsw.gui.swing.tree.view;


import raf.draft.dsw.gui.swing.tree.controller.DraftTreeCellEditor;
import raf.draft.dsw.gui.swing.tree.controller.DraftTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class DraftTreeView extends JTree {


    public DraftTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);
        DraftTreeCellRenderer draftTreeCellRenderer = new DraftTreeCellRenderer();
        addTreeSelectionListener(new DraftTreeSelectionListener());
        setCellEditor(new DraftTreeCellEditor(this, draftTreeCellRenderer));
        setCellRenderer(draftTreeCellRenderer);
        setEditable(true);
    }
}
