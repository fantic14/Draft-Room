package raf.draft.dsw.gui.swing.state;

import raf.draft.dsw.gui.swing.state.implementation.*;

public class StateManager {

    private State currentState;
    private AddState addState;
    private CopyState copyState;
    private PasteState pasteState;
    private DeleteState deleteState;
    private EditElementState editElementState;
    private EditRoomState editRoomState;
    private MoveState moveState;
    private ResizeState resizeState;
    private RotateState rotateState;
    private SelectState selectState;
    private ZoomState zoomState;

    public StateManager() {
        init();
    }

    private void init(){
        this.currentState = new NothingState();
        this.addState = new AddState();
        this.copyState = new CopyState();
        this.deleteState = new DeleteState();
        this.editElementState = new EditElementState();
        this.editRoomState = new EditRoomState();
        this.pasteState = new PasteState();
        this.moveState = new MoveState();
        this.resizeState = new ResizeState();
        this.rotateState = new RotateState();
        this.selectState = new SelectState();
        this.zoomState = new ZoomState();
    }

    public void setAddState(String element) {
        this.addState.setCurrentElement(element);
        this.currentState = addState;

    }

    public void setCopyState() {
        this.currentState = copyState;
    }

    public void setDeleteState() {
        this.currentState = deleteState;
    }

    public void setEditElementState() {
        this.currentState = editElementState;
    }

    public void setEditRoomState() {
        this.currentState = editRoomState;
    }

    public void setMoveState() {
        this.currentState = moveState;
    }

    public void setPasteState() {this.currentState = pasteState;}

    public void setResizeState() {
        this.currentState = resizeState;
    }

    public void setRotateState() {
        this.currentState = rotateState;
    }

    public void setSelectState() {
        this.currentState = selectState;
    }

    public void setZoomState() {
        this.currentState = zoomState;
    }

    public State getCurrentState() {
        return currentState;
    }
}
