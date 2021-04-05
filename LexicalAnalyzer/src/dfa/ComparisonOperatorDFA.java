package dfa;

public class ComparisonOperatorDFA {
    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
                                                     //    =    !      <      >
    private final int[][] dfaTable={{1, 2, 3, 4},    //T0: T1   -      -      -
                                    {5},             //T1: -    T2     T3     T4
                                    {5},             //T2: T5   -      -      -
                                    {5},             //T3: T5   -      -      -
                                    {5}};            //T4: T5   -      -      -
    private int state;

    public ComparisonOperatorDFA(String inputStr, int position){
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
                System.out.println(resultStr);
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
        if(ch=='=')
            return 0;
        else if(ch=='!')
            return 1;
        else if(ch=='<')
            return 2;
        else if(ch=='>')
            return 3;
        else
            return 'E'; //E is error
    }
}
