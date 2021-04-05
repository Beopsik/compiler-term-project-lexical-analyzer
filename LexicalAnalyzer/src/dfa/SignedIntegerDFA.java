package dfa;

public class SignedIntegerDFA {
    private final String inputStr;
    private String resultStr="";
    private int position;
                                                   //    P-digit   digit  -   0
    private final int[][] dfaTable={{1, -1, 2, 4}, //T0: T1        .      T2  T4
                                    {-1, 3},       //T1: .         T3     .   .
                                    {1},           //T2: T1        .      .   .
                                    {-1, 3}};      //T3: .         T3     .   .
    private int state;

    public SignedIntegerDFA(String inputStr, int position){
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
            }else if(symbolType == '4'){
                System.out.println(resultStr);
                position=i+1;
            }
            System.out.println("state:"+state+", symbolType:"+symbolType+", ch:"+ch);
            state = dfaTable[state][symbolType];
            resultStr+=ch;
        }
        return position;
    }
    public int inputSymbolType(char ch){
        if((ch>='1'&&ch<='9')&&(state==0||state==2))
            return 0;
        else if((ch>='0'&&ch<='9')&&(state==1||state==3))
            return 1;
        else if(ch=='-')
            return 2;
        else if(state==0&&ch=='0')
            return 3;
        else
            return 'E'; //E is error
    }
}
