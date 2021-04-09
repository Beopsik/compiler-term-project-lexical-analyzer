package dfa;

public class State {
    private boolean isFinalState=false;
    private boolean existNextState;

    private void setIsFinalState(boolean isFinalState){
        this.isFinalState=isFinalState;
    }
}
