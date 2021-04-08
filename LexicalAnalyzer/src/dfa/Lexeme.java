package dfa;

public class Lexeme {
    private String key;
    private String value="";
    private int endPosition;
    boolean live=true;

    public Lexeme(){
    }
    public void lexemeClear(){
        this.value="";
        this.live=true;
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
    public int getEndPosition(){
        return endPosition;
    }
    public void setLive(boolean b){
        live=b;
    }
    public boolean getLive(){
        return live;
    }
}
