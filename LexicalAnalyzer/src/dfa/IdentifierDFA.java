package dfa;

public class IdentifierDFA {
    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
                                                   //    _   letter digit
    private final int[][] dfaTable={{1, 2},        //T0: T1   T2      -
                                    {3, 4 ,5},     //T1: T3   T4     T5
                                    {3, 4 ,5},     //T2: T3   T4     T5
                                    {3, 4 ,5},     //T3: T3   T4     T5
                                    {3, 4 ,5},     //T4: T3   T4     T5
                                    {3, 4 ,5}};    //T5: T3   T4     T5
    private int state;

    public IdentifierDFA(String inputStr){
        this.inputStr=inputStr;
        this.state=0;
    }

    public int analyze(int position){
        resultStr="";
        startPosition=position;
        //System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++, endPosition=i) {
            char ch = inputStr.charAt(i);
            int symbolType = inputSymbolType(ch);
            if (symbolType == 'E') {
                //System.out.println("error");
                //System.out.println(resultStr);
                //endPosition=startPosition;
                break;
            }
            //.out.println("state:"+state+"symbolType:"+symbolType);
            state = dfaTable[state][symbolType];
            resultStr+=ch;
        }
        return endPosition;
    }
    public int inputSymbolType(char ch){
        if(ch=='_')
            return 0;
        else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
            return 1;
        else if(ch>='0'&&ch<='9')
            return 2;
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
