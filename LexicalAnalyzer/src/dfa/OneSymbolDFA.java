package dfa;

public class OneSymbolDFA {
    char [] symbol=new char[]{'+', '-', '*', '/', '=', ';', '{', '}', '(', ')', '[', ']', ','};

    private final String inputStr;
    private String resultStr="";
    private int startPosition;
    private int endPosition;
    private int symbolType=-1;
    private int kTypeIndex=0;

    public OneSymbolDFA(String inputStr, int position){
        this.inputStr=inputStr;
        this.startPosition=position;
    }

    public int analyze(){
        System.out.println(inputStr);
        for(int i=startPosition; i<inputStr.length(); i++, endPosition=i) {
            char ch = inputStr.charAt(i);
            if(symbolType==-1) {
                symbolType=inputSymbolType(ch);
                if(symbolType=='E'){
                    System.out.println("error");
                    endPosition=i;
                    break;
                }
            }

            /*if(kTypeIndex>=keywordType[symbolType].length()){
                System.out.println(resultStr);
                endPosition=i+1;
                System.out.println(endPosition);
                break;
            }

            if(ch==keywordType[symbolType].charAt(kTypeIndex++)) {
                resultStr += ch;
            } else{
                System.out.println("error");
                endPosition=i;
                break;
            }*/
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
}
