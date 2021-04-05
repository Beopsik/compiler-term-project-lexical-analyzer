package dfa;

public class LiteralStringDFA {
    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
                                               //    "   digit letter blank
    private final int[][] dfaTable={{1},             //T0: T1   -      -      -
                                    {5, 2, 3, 4},    //T1: T5   T2     T3     T4
                                    {5, 2, 3, 4},    //T2: T5   T2     T3     T4
                                    {5, 2, 3, 4},    //T3: T5   T2     T3     T4
                                    {5, 2, 3, 4}};   //T4: T5   T2     T3     T4
    private int state;

    public LiteralStringDFA(String inputStr, int position){
        this.inputStr=inputStr;
        this.startPosition=position;
        this.state=0;
    }

    public int analyze(){
        System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++) {
            char ch = inputStr.charAt(i);
            int symbolType = inputSymbolType(ch);
            if (symbolType == 'E') {
                System.out.println("error");
                endPosition=i;
                break;
            }
            System.out.println("state:"+state+"symbolType:"+symbolType);
            state = dfaTable[state][symbolType];
            resultStr+=ch;
            if (state == 5) {
                System.out.println(resultStr);
                endPosition=i+1;
                break;
            }
        }
        return endPosition;
    }
    public int inputSymbolType(char ch){
        if(ch=='"')
            return 0;
        else if(ch>='0'&&ch<='9')
            return 1;
        else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
            return 2;
        else if(ch==' ')
            return 3;
        else
            return 'E'; //E is error
    }
}
