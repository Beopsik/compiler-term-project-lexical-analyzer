package dfa;

public class Lexeme {
    private String key;
    private String value="";
    public Lexeme(){
    }
    public void lexemeClear(){
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
