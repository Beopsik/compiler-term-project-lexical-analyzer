package dfa;

public class Lexeme {
    private String key;
    private String value="";
    boolean live=true;
    boolean isFinalState=false;

    public Lexeme(){
    }
    public void lexemeClear(){
        this.value="";
        this.live=true;
        this.isFinalState=false;
    }
    public void addValue(char ch){
        value+=ch;
    }
    public void setKey(String key){
        this.key=key;
    }
    public String getKey(){
        return key;
    }
    public String getValue(){
        return value;
    }
    public void setLive(boolean b){
        live=b;
    }
    public boolean getLive(){
        return live;
    }
    public void setIsFinalState(boolean isFinalState){this.isFinalState=isFinalState;}
    public boolean getIsFinalState(){return isFinalState;}
}
