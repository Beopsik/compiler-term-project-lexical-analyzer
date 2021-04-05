package dfa;

public class IdentifierDFA {
    private final String inputStr;
    private String resultStr="";
    private int position;
                                                   //    _   letter digit
    private final int[][] dfaTable={{1, 2},        //T0: T1   T2      -
                                    {3, 4 ,5},     //T1: T3   T4     T5
                                    {3, 4 ,5},     //T2: T3   T4     T5
                                    {3, 4 ,5},     //T3: T3   T4     T5
                                    {3, 4 ,5},     //T4: T3   T4     T5
                                    {3, 4 ,5}};    //T5: T3   T4     T5
    private int state;

    public IdentifierDFA(String inputStr, int position){
        this.inputStr=inputStr;
        this.position=position;
        this.state=0;
    }

    public int analyze(){
        System.out.println(inputStr);
        for(int i=position; i<inputStr.length(); i++) {
            char ch = inputStr.charAt(i);
            int symbolType = inputSymbolType(ch);
            if (symbolType == 'E') {
                System.out.println("error");
                System.out.println(resultStr);
                position=i;
                break;
            }
            System.out.println("state:"+state+"symbolType:"+symbolType);
            state = dfaTable[state][symbolType];
            resultStr+=ch;
        }
        return position;
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
}
