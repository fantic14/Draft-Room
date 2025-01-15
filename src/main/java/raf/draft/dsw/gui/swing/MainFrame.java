package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.ActionManager;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.commands.CommandManager;
import raf.draft.dsw.gui.swing.tree.DraftTree;
import raf.draft.dsw.gui.swing.tree.DraftTreeImplementation;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;
    private ActionManager actionManager;
    private DraftTree draftTree;
    private JSplitPane splitPane;
    private ProjectView projectView;
    private JTree projectExplorer;
    private CommandManager commandManager;

    private MainFrame(){
        this.actionManager = new ActionManager();
        this.projectView = new ProjectView();
        this.draftTree = new DraftTreeImplementation(projectView);
        this.commandManager = new CommandManager();
        setResizable(true);
        initialize();
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");
        setMinimumSize(new Dimension(screenWidth / 2,screenHeight / 2));

        MyMenuBar menu = new MyMenuBar(this);
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar(this);
        add(toolBar, BorderLayout.NORTH);


        projectExplorer = draftTree.generateTree(ApplicationFramework.getInstance().getDraftRoomRepository().getRoot());

        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200, 150));
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, new JPanel());
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);

    }

    public static MainFrame getInstance() {
        if (instance == null)
            instance = new MainFrame();
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void enableDoAction(){
        actionManager.getDoAction().enableDisable(true);
    }

    public void enableUndoAction(){
        actionManager.getUndoAction().enableDisable(true);
    }

    public void disableDoAction(){
        actionManager.getDoAction().enableDisable(false);
    }

    public void disableUndoAction(){
        actionManager.getUndoAction().enableDisable(false);
    }

    public DraftTree getDraftTree() {
        return draftTree;
    }

    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public ProjectView getProjectView() {
        return this.projectView;
    }

    public void setProjectView(Project project) {
        projectView.addRoom(project);
        getSplitPane().setRightComponent(projectView);
    }

    @Override
    public void update(Object data) {
        if (data instanceof Message){
            JOptionPane.showMessageDialog(null, ((Message) data).getFinalMessage());
        }
    }
}

