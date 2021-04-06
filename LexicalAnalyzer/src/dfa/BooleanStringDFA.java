package dfa;

public class BooleanStringDFA {
    String [] booleanType=new String[]{"true", "false"};

    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
    private int symbolType=-1;
    private int bTypeIndex=0;

    public BooleanStringDFA(String inputStr){
        this.inputStr=inputStr;
    }

    public int analyze(int position){
        resultStr="";
        startPosition=position;
        bTypeIndex=0;
        symbolType=-1;
        //System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++, endPosition=i) {
            char ch = inputStr.charAt(i);
            if(symbolType==-1) {
                symbolType=inputSymbolType(ch);
            }
            if(symbolType=='E'){
                //System.out.println("Berror");
                endPosition=startPosition;
                break;
            }
            if(bTypeIndex>=booleanType[symbolType].length()){
                //System.out.println(resultStr);
                endPosition=i;
                break;
            }

            if(ch==booleanType[symbolType].charAt(bTypeIndex++)) {
                resultStr += ch;
            } else{
                //System.out.println("Berror");
                endPosition=startPosition;
                break;
            }
        }
        return endPosition;
    }
    public int inputSymbolType(char ch){
        if(ch=='t')
            return 0;
        else if(ch=='f')
            return 1;
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
