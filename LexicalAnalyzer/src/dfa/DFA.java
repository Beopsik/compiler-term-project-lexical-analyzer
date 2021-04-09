package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.management.remote.rmi.RMIConnectionImpl_Stub;
import java.util.ArrayList;
import java.util.List;

public class DFA {
    private final DFATable dfaTable = new DFATable();

    private final JSONArray arithmeticOperatorDFATable = dfaTable.arithmeticOperatorDFATable();
    private final JSONArray assignmentOperatorDFATable = dfaTable.assginmentOperatorDFATable();
    private final JSONArray comparisonOperatorDFATable=dfaTable.ComparisonOperatorDFATable();
    private final JSONArray terminateSymboleDFATable = dfaTable.terminateSymbolDFATable();
    private final JSONArray lParenDFATable = dfaTable.lParenDFATable();
    private final JSONArray rParenDFATable = dfaTable.rParenDFATable();
    private final JSONArray lBraceDFATable = dfaTable.lBraceDFATable();
    private final JSONArray rBraceDFATAble = dfaTable.rBraceDFATable();
    private final JSONArray lBranketDFATable = dfaTable.lBranketDFATable();
    private final JSONArray rBranketDFATable = dfaTable.rBranketDFATable();
    private final JSONArray commaDFATable = dfaTable.commaDFATable();
    private final JSONArray whiteSpaceDFATable = dfaTable.whiteSpaceDFATable();

    private final JSONArray singleCharacterDFATable = dfaTable.singleCharacterDFATable();
    private final JSONArray literalStringDFATable = dfaTable.literalStringDFATable();
    private final JSONArray signedIntegerDFATable = dfaTable.signedIntegerDFATable();

    private final JSONArray keywordDFATable = dfaTable.keywordDFATable();
    private final JSONArray variableTypeDFATable = dfaTable.variableTypeDFATable();
    private final JSONArray booleanStringDFATable = dfaTable.booleanStringDFATable();
    private final JSONArray indentifierDFATable = dfaTable.identifierDFATable();

    private final String inputstr;
    private final int startPosition;

    private String beforeToken="";
    private final static int TOKEN_NUM=19;
    private final List<Lexeme> liveDFAList=new ArrayList<>();
    private final Lexeme[] lexemes=new Lexeme[TOKEN_NUM];
    private final State[] state=new State[TOKEN_NUM];

    private final static int ARITHMETICOPERATOR=0;
    private final static int ASSIGNMENTOPERATOR=1;
    private final static int COMPARISONOPERATOR=2;
    private final static int TERMINATEOPERATOR=3;
    private final static int LPAREN=4;
    private final static int RPAREN=5;
    private final static int LBRACE=6;
    private final static int RBRACE=7;
    private final static int LBRANKET=8;
    private final static int RBRANKET=9;
    private final static int COMMA=10;
    private final static int SINGLECAHRACTER=11;
    private final static int LITERALSTRING=12;
    private final static int WHITESPACE=13;
    private final static int SIGNEDINTEGER=14;
    private final static int KEYWORD=15;
    private final static int VARIABLETYPE=16;
    private final static int BOOLEANSTRING=17;
    private final static int IDENTIFIER=18;

    public DFA(String inputstr, int postion) {
        this.inputstr = inputstr;
        this.startPosition = postion;
    }
    public void lexemesInit(){
        lexemes[ARITHMETICOPERATOR].setKey("ARITHMETICOPERATOR");
        lexemes[COMPARISONOPERATOR].setKey("COMPARISONOPERATOR");
        lexemes[ASSIGNMENTOPERATOR].setKey("ASSIGNMENTOPERATOR");
        lexemes[TERMINATEOPERATOR].setKey("TERMINATESYMBOL");
        lexemes[LPAREN].setKey("LPAREN");
        lexemes[RPAREN].setKey("RPAREN");
        lexemes[LBRACE].setKey("LBRACE");
        lexemes[RBRACE].setKey("RBRACE");
        lexemes[LBRANKET].setKey("LBRANKET");
        lexemes[RBRANKET].setKey("RBRANKET");
        lexemes[COMMA].setKey("COMMA");
        lexemes[WHITESPACE].setKey("WHITESPACE");
        lexemes[SINGLECAHRACTER].setKey("SINGLECHARACTER");
        lexemes[LITERALSTRING].setKey("LITERALSTRING");
        lexemes[SIGNEDINTEGER].setKey("SIGNEDINTEGER");
        lexemes[KEYWORD].setKey("KEYWORD");
        lexemes[VARIABLETYPE].setKey("VARIABLETYPE");
        lexemes[BOOLEANSTRING].setKey("BOOLEANSTRING");
        lexemes[IDENTIFIER].setKey("IDENTIFIER");
    }
    public void run() {
        //System.out.println(lexemes.length);
        for(int i=0; i<lexemes.length; i++){
            lexemes[i]=new Lexeme();
            state[i]=new State();
        }
        lexemesInit();
        for(int i=startPosition; i<inputstr.length(); i++) {
            /*if(Error){
                Lexeme error=liveDFAList.get(0);
                System.out.println("Occured Error");
                System.out.println(error.getValue());
            }*/
            arithmeticOperatorDFA(i);
            assignmentOperatorDFA(i);
            comparsionOperatorDFA(i);
            terminateSymbolDFA(i);
            lParenDFA(i);
            rParenDFA(i);
            lBraceDFA(i);
            rBraceDFA(i);
            lBranketDFA(i);
            rBranketDFA(i);
            commaDFA(i);

            singleCharacterDFA(i);
            literalStringDFA(i);
            whiteSpaceDFA(i);
            signedIntegerDFA(i);

            keywordDFA(i);
            variableTypeDFA(i);
            booleanStringDFA(i);
            identifierDFA(i);

            boolean liveDFAListClear=true;
            boolean liveDFA=false;
            for (int j=0; j<lexemes.length; j++) {
                if (state[j].getExistNextState()) {
                    if (liveDFAListClear) {
                        liveDFAList.clear();
                        liveDFAListClear = false;
                        liveDFA = true;
                    }
                    if (state[j].getIsFinalState()) {
                        if (lexemes[j].getValue().equals("-") && (beforeToken.equals("IDENTIFIER") || beforeToken.equals("SIGNEDINTEGER"))) {
                            liveDFAList.add(lexemes[j]);
                            state[SIGNEDINTEGER].setExistNextState(false);
                        } else {
                            liveDFAList.add(lexemes[j]);
                        }
                        //System.out.println("<"+lexemes[j].getKey()+", "+lexemes[j].getValue()+">");
                    }
                }
            }
            if(!liveDFA){
                /*for (int k=0; k<liveDFAList.size(); k++){
                    Lexeme result=liveDFAList.get(k);
                    System.out.println("<"+result.getKey()+", "+result.getValue()+">");
                }*/
                //System.out.println("-------------------------------");
                if(liveDFAList.isEmpty()){
                    System.out.println("Occured error at "+inputstr.charAt(i));
                    return;
                }else {
                    Lexeme result = liveDFAList.get(0);
                    beforeToken=result.getKey();
                    //System.out.println(beforeToken);
                    System.out.println("<" + result.getKey() + ", " + result.getValue() + ">");
                    for (int k = 0; k < lexemes.length; k++) {
                        lexemes[k].lexemeClear();
                        state[k].clear();
                    }
                    i--;
                }
            }else if(i==inputstr.length()-1){
                if(liveDFAList.isEmpty()){
                    System.out.println("Occured error at "+inputstr.charAt(i));
                    return;
                }else {
                    Lexeme result = liveDFAList.get(0);
                    beforeToken=result.getKey();
                    System.out.println("<" + result.getKey() + ", " + result.getValue() + ">");
                }
            }
        }
    }

    public void arithmeticOperatorDFA(int position){
        if(!state[ARITHMETICOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)arithmeticOperatorDFATable.get(state[ARITHMETICOPERATOR].getStateLocation());
        try {

            state[ARITHMETICOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[ARITHMETICOPERATOR].getIsFinalState()&&state[ARITHMETICOPERATOR].getStateLocation()==1)
                state[ARITHMETICOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            state[ARITHMETICOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[ARITHMETICOPERATOR].addValue(ch);
    }
    public void comparsionOperatorDFA(int position){
        if(!state[COMPARISONOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)comparisonOperatorDFATable.get(state[COMPARISONOPERATOR].getStateLocation());
        try {
            state[COMPARISONOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[COMPARISONOPERATOR].getIsFinalState()&&(state[COMPARISONOPERATOR].getStateLocation()==3||state[COMPARISONOPERATOR].getStateLocation()==4||state[COMPARISONOPERATOR].getStateLocation()==5))
                state[COMPARISONOPERATOR].setIsFinalState(true);

        }catch (NullPointerException e){
            state[COMPARISONOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[COMPARISONOPERATOR].addValue(ch);
    }
    public void assignmentOperatorDFA(int position){
        if(!state[ASSIGNMENTOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)assignmentOperatorDFATable.get(state[ASSIGNMENTOPERATOR].getStateLocation());
        try {
            state[ASSIGNMENTOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[ASSIGNMENTOPERATOR].getIsFinalState()&&state[ASSIGNMENTOPERATOR].getStateLocation()==1)
                state[ASSIGNMENTOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            state[ASSIGNMENTOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[ASSIGNMENTOPERATOR].addValue(ch);
    }
    public void terminateSymbolDFA(int position){
        if(!state[TERMINATEOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)terminateSymboleDFATable.get(state[TERMINATEOPERATOR].getStateLocation());
        try {
            state[TERMINATEOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[TERMINATEOPERATOR].getIsFinalState()&&state[TERMINATEOPERATOR].getStateLocation()==1)
                state[TERMINATEOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            state[TERMINATEOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[TERMINATEOPERATOR].addValue(ch);
    }
    public void lParenDFA(int position){
        if(!state[LPAREN].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lParenDFATable.get(state[LPAREN].getStateLocation());
        try {
            state[LPAREN].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[LPAREN].getIsFinalState()&&state[LPAREN].getStateLocation()==1)
                state[LPAREN].setIsFinalState(true);
        }catch (NullPointerException e){
            state[LPAREN].setExistNextState(false);
            return;
        }
        lexemes[LPAREN].addValue(ch);
    }
    public void rParenDFA(int position){
        if(!state[RPAREN].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rParenDFATable.get(state[RPAREN].getStateLocation());
        try {
            state[RPAREN].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[RPAREN].getIsFinalState()&&state[RPAREN].getStateLocation()==1)
                state[RPAREN].setIsFinalState(true);
        }catch (NullPointerException e){
            state[RPAREN].setExistNextState(false);
            return;
        }
        lexemes[RPAREN].addValue(ch);
    }
    public void lBraceDFA(int position){
        if(!state[LBRACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBraceDFATable.get(state[LBRACE].getStateLocation());
        try {
            state[LBRACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[LBRACE].getIsFinalState()&&state[LBRACE].getStateLocation()==1)
                state[LBRACE].setIsFinalState(true);
        }catch (NullPointerException e){
            state[LBRACE].setExistNextState(false);
            return;
        }
        lexemes[LBRACE].addValue(ch);
    }
    public void rBraceDFA(int position){
        if(!state[RBRACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBraceDFATAble.get(state[RBRACE].getStateLocation());
        try {
            state[RBRACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[RBRACE].getIsFinalState()&&state[RBRACE].getStateLocation()==1)
                state[RBRACE].setIsFinalState(true);
        }catch (NullPointerException e){
            state[RBRACE].setExistNextState(false);
            return;
        }
        lexemes[RBRACE].addValue(ch);
    }
    public void lBranketDFA(int position){
        if(!state[LBRANKET].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBranketDFATable.get(state[LBRANKET].getStateLocation());
        try {
            state[LBRANKET].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[LBRANKET].getIsFinalState()&&state[LBRANKET].getStateLocation()==1)
                state[LBRANKET].setIsFinalState(true);
        }catch (NullPointerException e){
            state[LBRANKET].setExistNextState(false);
            return;
        }
        lexemes[LBRANKET].addValue(ch);
    }
    public void rBranketDFA(int position){
        if(!state[RBRANKET].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBranketDFATable.get(state[RBRANKET].getStateLocation());
        try {
            state[RBRANKET].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[RBRANKET].getIsFinalState()&&state[RBRANKET].getStateLocation()==1)
                state[RBRANKET].setIsFinalState(true);
        }catch (NullPointerException e){
            state[RBRANKET].setExistNextState(false);
            return;
        }
        lexemes[RBRANKET].addValue(ch);
    }
    public void commaDFA(int position){
        if(!state[COMMA].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)commaDFATable.get(state[COMMA].getStateLocation());
        try {
            state[COMMA].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[COMMA].getIsFinalState()&&state[COMMA].getStateLocation()==1)
                state[COMMA].setIsFinalState(true);
        }catch (NullPointerException e){
            state[COMMA].setExistNextState(false);
            return;
        }
        lexemes[COMMA].addValue(ch);
    }
    public void whiteSpaceDFA(int position){
        if(!state[WHITESPACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)whiteSpaceDFATable.get(state[WHITESPACE].getStateLocation());
        try {
            state[WHITESPACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[WHITESPACE].getIsFinalState()&&state[WHITESPACE].getStateLocation()==1)
                state[WHITESPACE].setIsFinalState(true);
        }catch (NullPointerException e){
            state[WHITESPACE].setExistNextState(false);
            return;
        }
        lexemes[WHITESPACE].addValue(ch);
    }

    public void singleCharacterDFA(int position) {
        if(!state[SINGLECAHRACTER].getExistNextState())
            return;

        char ch = inputstr.charAt(position);
        String symbolType;

        if (ch == '\'')
            symbolType = "'";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else if (ch == ' ')
            symbolType = "blank";
        else {
            state[SINGLECAHRACTER].setExistNextState(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) singleCharacterDFATable.get(state[SINGLECAHRACTER].getStateLocation());
            state[SINGLECAHRACTER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!state[SINGLECAHRACTER].getIsFinalState()&&state[SINGLECAHRACTER].getStateLocation()==5)
                state[SINGLECAHRACTER].setIsFinalState(true);
        }catch(NullPointerException e){
            state[SINGLECAHRACTER].setExistNextState(false);
            return;
        }
        if(ch!='\''){
            lexemes[SINGLECAHRACTER].addValue(ch);
        }
    }

    public void literalStringDFA(int position) {
        if(!state[LITERALSTRING].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        String symbolType;
        if (ch == '\"')
            symbolType = "double quotes";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else if (ch == ' ')
            symbolType = "blank";
        else {
            state[LITERALSTRING].setExistNextState(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) literalStringDFATable.get(state[LITERALSTRING].getStateLocation());
            state[LITERALSTRING].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!state[LITERALSTRING].getIsFinalState()&&state[LITERALSTRING].getStateLocation()==5)
                state[LITERALSTRING].setIsFinalState(true);
        }catch (NullPointerException e){
            state[LITERALSTRING].setExistNextState(false);
            return;
        }
        if(ch!='\"') {
            lexemes[LITERALSTRING].addValue(ch);
        }
    }

    public void signedIntegerDFA(int position) {
        if(!state[SIGNEDINTEGER].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        String symbolType;
        if ((ch >= '1' && ch <= '9') && (state[SIGNEDINTEGER].getStateLocation() == 0 || state[SIGNEDINTEGER].getStateLocation() == 2))
            symbolType = "positive";
        else if ((ch >= '0' && ch <= '9') && (state[SIGNEDINTEGER].getStateLocation() == 1 || state[SIGNEDINTEGER].getStateLocation() == 3))
            symbolType = "digit";
        else if (ch == '-')
            symbolType = "-";
        else if (ch == '0' && state[SIGNEDINTEGER].getStateLocation() == 0)
            symbolType = "0";
        else {
            state[SIGNEDINTEGER].setExistNextState(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) signedIntegerDFATable.get(state[SIGNEDINTEGER].getStateLocation());
            state[SIGNEDINTEGER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!state[SIGNEDINTEGER].getIsFinalState()&&(state[SIGNEDINTEGER].getStateLocation()==1||state[SIGNEDINTEGER].getStateLocation()==3||state[SIGNEDINTEGER].getStateLocation()==4))
                state[SIGNEDINTEGER].setIsFinalState(true);
        }catch(NullPointerException e){
            state[SIGNEDINTEGER].setExistNextState(false);
            return;
        }
        lexemes[SIGNEDINTEGER].addValue(ch);
    }

    public void keywordDFA(int position){
        if(!state[KEYWORD].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)keywordDFATable.get(state[KEYWORD].getStateLocation());
        try {
            state[KEYWORD].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[KEYWORD].getIsFinalState()&&state[KEYWORD].getStateLocation()==18)
                state[KEYWORD].setIsFinalState(true);
        }catch (NullPointerException e){
            state[KEYWORD].setExistNextState(false);
            return;
        }
        lexemes[KEYWORD].addValue(ch);
    }
    public void variableTypeDFA(int position){
        if(!state[VARIABLETYPE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)variableTypeDFATable.get(state[VARIABLETYPE].getStateLocation());
        try {
            state[VARIABLETYPE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[VARIABLETYPE].getIsFinalState()&&state[VARIABLETYPE].getStateLocation()==17)
                state[VARIABLETYPE].setIsFinalState(true);
        }catch (NullPointerException e){
            state[VARIABLETYPE].setExistNextState(false);
            return;
        }
        lexemes[VARIABLETYPE].addValue(ch);
    }
    public void booleanStringDFA(int position) {
        if(!state[BOOLEANSTRING].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        JSONObject transition = (JSONObject) booleanStringDFATable.get(state[BOOLEANSTRING].getStateLocation());
        try {
            state[BOOLEANSTRING].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!state[BOOLEANSTRING].getIsFinalState()&&state[BOOLEANSTRING].getStateLocation()==8)
                state[BOOLEANSTRING].setIsFinalState(true);
        } catch (NullPointerException e) {
            state[BOOLEANSTRING].setExistNextState(false);
            return;
        }
        lexemes[BOOLEANSTRING].addValue(ch);
    }
    public void identifierDFA(int position) {
        if(!state[IDENTIFIER].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        String symbolType;
        if (ch == '_')
            symbolType = "_";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else {
            state[IDENTIFIER].setExistNextState(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) indentifierDFATable.get(state[IDENTIFIER].getStateLocation());
            state[IDENTIFIER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!state[IDENTIFIER].getIsFinalState()&&(state[IDENTIFIER].getStateLocation()==1||state[IDENTIFIER].getStateLocation()==2||state[IDENTIFIER].getStateLocation()==3||state[IDENTIFIER].getStateLocation()==4||state[IDENTIFIER].getStateLocation()==5))
                state[IDENTIFIER].setIsFinalState(true);
        }catch(NullPointerException e){
            state[IDENTIFIER].setExistNextState(false);
            return;
        }
        lexemes[IDENTIFIER].addValue(ch);
    }
}
