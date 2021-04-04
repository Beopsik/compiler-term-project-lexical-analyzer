package dfa;

public class LiteralStringDFA {
    private String inputStr;
    private String resultStr="";
    private int position;
    private int[][] dfaTable={{1},
                              {5, 2, 3, 4},
                              {5, 2, 3, 4},
                              {5, 2, 3, 4},
                              {5, 2, 3, 4}};
    private int state;

    public LiteralStringDFA(String inputStr, int position){
        this.inputStr=inputStr;
        this.position=position;
        this.state=0;
    }

    public int analyze(){
        /*for(int i=0; i<dfaTable.length; i++){
            for(int j=0; j<dfaTable[i].length; j++){
                System.out.print(dfaTable[i][j]);
            }
            System.out.println();
        }*/
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
            resultStr += ch;
            if (state == 5) {
                System.out.println(resultStr);
                position=i+1;
                break;
            }
        }
        return position;
    }
    public int inputSymbolType(char ch){
        if(ch=='"')
            return 0;
        else if(Character.isDigit(ch))
            return 1;
        else if(is_Letter(ch))
            return 2;
        else if(ch==' ')
            return 3;
        else
            return 'E'; //E is error
    }
    public boolean is_Letter(char ch){
        if(Character.isUpperCase(ch)|Character.isLowerCase(ch))
            return true;
        else
            return false;
    }

}
