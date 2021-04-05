package dfa;

public class SingleCharaterDFA {
    private final String inputStr;
    private String resultStr="";
    private int position;
                                                     //    '   digit letter blank
    private final int[][] dfaTable={{1},             //T0: T1   -      -      -
                                    {-1, 2, 3, 4},   //T1: -    T2     T3     T4
                                    {5},             //T2: T5   -      -      -
                                    {5},             //T3: T5   -      -      -
                                    {5}};            //T4: T5   -      -      -
    private int state;

    public SingleCharaterDFA(String inputStr, int position){
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
                return 0;
            }
            System.out.println("state:"+state+"symbolType:"+symbolType);
            state = dfaTable[state][symbolType];
            resultStr+=ch;
            if (state == 5) {
                System.out.println(resultStr);
                position=i+1;
                break;
            }
        }
        return position;
    }
    public int inputSymbolType(char ch){
        if(ch=='\'')
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
