package dfa;

public class KeywordDFA {
    String [] keywordType=new String[]{"if", "else", "while", "class", "return"};

    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
    private int symbolType=-1;
    private int kTypeIndex=0;

    public KeywordDFA(String inputStr){
        this.inputStr=inputStr;
    }

    public int analyze(int position){
        resultStr="";
        startPosition=position;
        //System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++, endPosition=i) {
            char ch = inputStr.charAt(i);
            if(symbolType==-1) {
                symbolType=inputSymbolType(ch);
            }
            if(symbolType=='E'){
                //System.out.println("error");
                //endPosition=startPosition;
                break;
            }
            if(kTypeIndex>=keywordType[symbolType].length()){
                //System.out.println(resultStr);
                //endPosition=i;
                //System.out.println(endPosition);
                break;
            }

            if(ch==keywordType[symbolType].charAt(kTypeIndex++)) {
                resultStr += ch;
            } else{
                //System.out.println("error");
                //endPosition=startPosition;
                break;
            }
        }
        return endPosition;
    }
    public int inputSymbolType(char ch){
        if(ch=='i')
            return 0;
        else if(ch=='e')
            return 1;
        else if(ch=='w')
            return 2;
        else if(ch=='c')
            return 3;
        else if(ch=='r')
            return 4;
        else
            return 'E'; //E is error
    }
    public String getResultStr(){
        return resultStr;
    }
    public int getEndPosition(){
        return endPosition;
    }
}
