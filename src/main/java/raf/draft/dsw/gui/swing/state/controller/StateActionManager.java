package raf.draft.dsw.gui.swing.state.controller;


public class StateActionManager {

    private AddAction addAction;
    private CopyAction copyAction;
    private PasteAction pasteAction;
    private DeleteAction deleteAction;
    private EditElementAction editElementAction;
    private EditRoomAction editRoomAction;
    private MoveAction moveAction;
    private ResizeAction resizeAction;
    private RotateAction rotateAction;
    private SelectAction selectAction;
    private ZoomAction zoomAction;

    public StateActionManager() {
        init();
    }

    private void init(){
        this.addAction = new AddAction();
        this.copyAction = new CopyAction();
        this.pasteAction = new PasteAction();
        this.deleteAction = new DeleteAction();
        this.editElementAction = new EditElementAction();
        this.editRoomAction = new EditRoomAction();
        this.moveAction = new MoveAction();
        this.resizeAction = new ResizeAction();
        this.rotateAction = new RotateAction();
        this.selectAction = new SelectAction();
        this.zoomAction = new ZoomAction();
    }

    public AddAction getAddAction() {
        return addAction;
    }

    public CopyAction getCopyAction() {
        return copyAction;
    }

    public PasteAction getPasteAction() {
        return pasteAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public EditElementAction getEditElementAction() {
        return editElementAction;
    }

    public EditRoomAction getEditRoomAction() {
        return editRoomAction;
    }

    public MoveAction getMoveAction() {
        return moveAction;
    }

    public ResizeAction getResizeAction() {
        return resizeAction;
    }

    public RotateAction getRotateAction() {
        return rotateAction;
    }

    public SelectAction getSelectAction() {
        return selectAction;
    }

    public ZoomAction getZoomAction() {
        return zoomAction;
    }


}
