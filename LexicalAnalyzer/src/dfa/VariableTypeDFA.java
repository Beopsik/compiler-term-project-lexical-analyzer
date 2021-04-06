package dfa;

public class VariableTypeDFA {
    String [] variableType=new String[]{"int", "char", "boolean", "String"};

    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
    private int symbolType=-1;
    private int vTypeIndex=0;
    public VariableTypeDFA(String inputStr){
        this.inputStr=inputStr;
    }

    public int analyze(int position){
        resultStr="";
        startPosition=position;
        vTypeIndex=0;
        symbolType=-1;
        //System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++, endPosition=i) {
            char ch = inputStr.charAt(i);
            if(symbolType==-1) {
                symbolType=inputSymbolType(ch);
            }
            if(symbolType=='E'){
                //System.out.println("Verror");
                endPosition=startPosition;
                break;
            }
            if(vTypeIndex>=variableType[symbolType].length()){
                //System.out.println(resultStr);
                endPosition=i;
                break;
            }

            if(ch==variableType[symbolType].charAt(vTypeIndex++)) {
                resultStr += ch;
            } else{
                //System.out.println("Verror");
                endPosition=startPosition;
                break;
            }
        }
        return endPosition;
    }
    public int inputSymbolType(char ch){
        if(ch=='i')
            return 0;
        else if(ch=='c')
            return 1;
        else if(ch=='b')
            return 2;
        else if(ch=='S')
            return 3;
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
