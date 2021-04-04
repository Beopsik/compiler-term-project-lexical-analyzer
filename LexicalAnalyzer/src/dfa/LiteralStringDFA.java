package dfa;

public class LiteralStringDFA {
    private String inputStr;
    private int position;
    private char state;

    public LiteralStringDFA(String inputStr, int position){
        this.inputStr=inputStr;
        this.position=position;
        this.state='0';
    }

    public void analyze(){
        char ch=inputStr.charAt(position);
        if(state=='0'){
            if(ch=='"')
                state='1';
            else
                state='E'; // E is error state
        }else if(state>='1'&&state<='4'){
            if(Character.isDigit(ch))
                state='2';
            else if(isLetter(ch))
                state='3';
            else if(ch==' ')
                state='4';
            else if(ch=='"')
                state='5';
            else
                state='E';
        }else if(state=='5'){

        }else if(state=='E'){

        }

    }
    public boolean isLetter(char ch){
        if(Character.isUpperCase(ch)|Character.isLowerCase(ch))
            return true;
        else
            return false;
    }

}
