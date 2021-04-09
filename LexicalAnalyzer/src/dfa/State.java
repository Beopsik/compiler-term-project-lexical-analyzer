package dfa;

public class State {
    private boolean isFinalState;
    private boolean existNextState;
    private int stateLocation;

    public State(){
        this.isFinalState=false;
        this.existNextState=true;
        stateLocation=0;
    }
    public void setIsFinalState(boolean isFinalState){
        this.isFinalState=isFinalState;
    }
    public boolean getIsFinalState(){
        return isFinalState;
    }
    public void setExistNextState(boolean existNextState){
        this.existNextState=existNextState;
    }
    public boolean getExistNextState(){
        return existNextState;
    }
    public void setStateLocation(int stateLocation){
        this.stateLocation=stateLocation;
    }
    public int getStateLocation(){
        return stateLocation;
    }
    public void clear(){
        this.isFinalState=false;
        this.existNextState=true;
        stateLocation=0;
    }
}
