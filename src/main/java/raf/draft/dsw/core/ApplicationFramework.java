package raf.draft.dsw.core;

import raf.draft.dsw.controller.messagegenerator.*;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.repository.DraftRoomExplorerImplementation;
import raf.draft.dsw.model.repository.DraftRoomRepository;

import java.util.ArrayList;
import java.util.List;


public class ApplicationFramework {

    private static ApplicationFramework instance;
    private MainFrame mainFrame;
    private ArrayList<Logger> loggers = new ArrayList<>();
    private DraftRoomRepository draftRoomRepository;

    private ApplicationFramework(){
        draftRoomRepository = new DraftRoomExplorerImplementation();
    }

    public void initialize(){
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    public static ApplicationFramework getInstance() {
        if (instance == null)
            instance = new ApplicationFramework();
        return instance;
    }

    public List<Logger> getLogger() {
        return loggers;
    }

    public DraftRoomRepository getDraftRoomRepository() {
        return draftRoomRepository;
    }

}