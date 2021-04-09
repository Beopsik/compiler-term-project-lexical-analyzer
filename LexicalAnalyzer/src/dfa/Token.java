package dfa;

public class Token {
    private String key;
    private String value="";
    public Token(){
    }
    public void tokenClear(){
        this.value="";
    }
    public void addValue(char ch){
        value+=ch;
    }
    public String getValue(){
        return value;
    }
    public void setKey(String key){
        this.key=key;
    }
    public String getKey(){
        return key;
    }
}
