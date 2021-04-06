public class Pair {
    String resultStr;
    int position;
    public Pair(String str, int position){
        this.resultStr=str;
        this.position=position;
    }
    public String lexeme(){
        return resultStr;
    }
    public int getPosition(){
        return position;
    }
}
