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
    private final State[] _state=new State[TOKEN_NUM];

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
            _state[i]=new State();
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
                if (_state[j].getExistNextState()/*lexemes[j].getLive()*/) {
                    if (liveDFAListClear) {
                        liveDFAList.clear();
                        liveDFAListClear = false;
                        liveDFA = true;
                    }
                    if (_state[j].getIsFinalState()) {
                        if (lexemes[j].getValue().equals("-") && (beforeToken.equals("IDENTIFIER") || beforeToken.equals("SIGNEDINTEGER"))) {
                            liveDFAList.add(lexemes[j]);
                            _state[SIGNEDINTEGER].setExistNextState(false);
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
                        _state[k].clear();
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
        if(!_state[ARITHMETICOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)arithmeticOperatorDFATable.get(_state[ARITHMETICOPERATOR].getStateLocation());
        try {

            _state[ARITHMETICOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[ARITHMETICOPERATOR].getIsFinalState()&&_state[ARITHMETICOPERATOR].getStateLocation()==1)
                _state[ARITHMETICOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[ARITHMETICOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[ARITHMETICOPERATOR].addValue(ch);
    }
    public void comparsionOperatorDFA(int position){
        if(!_state[COMPARISONOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)comparisonOperatorDFATable.get(_state[COMPARISONOPERATOR].getStateLocation());
        try {
            _state[COMPARISONOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[COMPARISONOPERATOR].getIsFinalState()&&(_state[COMPARISONOPERATOR].getStateLocation()==3||_state[COMPARISONOPERATOR].getStateLocation()==4||_state[COMPARISONOPERATOR].getStateLocation()==5))
                _state[COMPARISONOPERATOR].setIsFinalState(true);

        }catch (NullPointerException e){
            _state[COMPARISONOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[COMPARISONOPERATOR].addValue(ch);
    }
    public void assignmentOperatorDFA(int position){
        if(!_state[ASSIGNMENTOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)assignmentOperatorDFATable.get(_state[ASSIGNMENTOPERATOR].getStateLocation());
        try {
            _state[ASSIGNMENTOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[ASSIGNMENTOPERATOR].getIsFinalState()&&_state[ASSIGNMENTOPERATOR].getStateLocation()==1)
                _state[ASSIGNMENTOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[ASSIGNMENTOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[ASSIGNMENTOPERATOR].addValue(ch);
    }
    public void terminateSymbolDFA(int position){
        if(!_state[TERMINATEOPERATOR].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)terminateSymboleDFATable.get(_state[TERMINATEOPERATOR].getStateLocation());
        try {
            _state[TERMINATEOPERATOR].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[TERMINATEOPERATOR].getIsFinalState()&&_state[TERMINATEOPERATOR].getStateLocation()==1)
                _state[TERMINATEOPERATOR].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[TERMINATEOPERATOR].setExistNextState(false);
            return;
        }
        lexemes[TERMINATEOPERATOR].addValue(ch);
    }
    public void lParenDFA(int position){
        if(!_state[LPAREN].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lParenDFATable.get(_state[LPAREN].getStateLocation());
        try {
            _state[LPAREN].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[LPAREN].getIsFinalState()&&_state[LPAREN].getStateLocation()==1)
                _state[LPAREN].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[LPAREN].setExistNextState(false);
            return;
        }
        lexemes[LPAREN].addValue(ch);
    }
    public void rParenDFA(int position){
        if(!_state[RPAREN].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rParenDFATable.get(_state[RPAREN].getStateLocation());
        try {
            _state[RPAREN].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[RPAREN].getIsFinalState()&&_state[RPAREN].getStateLocation()==1)
                _state[RPAREN].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[RPAREN].setExistNextState(false);
            return;
        }
        lexemes[RPAREN].addValue(ch);
    }
    public void lBraceDFA(int position){
        if(!_state[LBRACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBraceDFATable.get(_state[LBRACE].getStateLocation());
        try {
            _state[LBRACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[LBRACE].getIsFinalState()&&_state[LBRACE].getStateLocation()==1)
                _state[LBRACE].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[LBRACE].setExistNextState(false);
            return;
        }
        lexemes[LBRACE].addValue(ch);
    }
    public void rBraceDFA(int position){
        if(!_state[RBRACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBraceDFATAble.get(_state[RBRACE].getStateLocation());
        try {
            _state[RBRACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[RBRACE].getIsFinalState()&&_state[RBRACE].getStateLocation()==1)
                _state[RBRACE].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[RBRACE].setExistNextState(false);
            return;
        }
        lexemes[RBRACE].addValue(ch);
    }
    public void lBranketDFA(int position){
        if(!_state[LBRANKET].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBranketDFATable.get(_state[LBRANKET].getStateLocation());
        try {
            _state[LBRANKET].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[LBRANKET].getIsFinalState()&&_state[LBRANKET].getStateLocation()==1)
                _state[LBRANKET].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[LBRANKET].setExistNextState(false);
            return;
        }
        lexemes[LBRANKET].addValue(ch);
    }
    public void rBranketDFA(int position){
        if(!_state[RBRANKET].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBranketDFATable.get(_state[RBRANKET].getStateLocation());
        try {
            _state[RBRANKET].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[RBRANKET].getIsFinalState()&&_state[RBRANKET].getStateLocation()==1)
                _state[RBRANKET].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[RBRANKET].setExistNextState(false);
            return;
        }
        lexemes[RBRANKET].addValue(ch);
    }
    public void commaDFA(int position){
        if(!_state[COMMA].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)commaDFATable.get(_state[COMMA].getStateLocation());
        try {
            _state[COMMA].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[COMMA].getIsFinalState()&&_state[COMMA].getStateLocation()==1)
                _state[COMMA].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[COMMA].setExistNextState(false);
            return;
        }
        lexemes[COMMA].addValue(ch);
    }
    public void whiteSpaceDFA(int position){
        if(!_state[WHITESPACE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)whiteSpaceDFATable.get(_state[WHITESPACE].getStateLocation());
        try {
            _state[WHITESPACE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[WHITESPACE].getIsFinalState()&&_state[WHITESPACE].getStateLocation()==1)
                _state[WHITESPACE].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[WHITESPACE].setExistNextState(false);
            return;
        }
        lexemes[WHITESPACE].addValue(ch);
    }

    public void singleCharacterDFA(int position) {
        if(!_state[SINGLECAHRACTER].getExistNextState())
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
            _state[SINGLECAHRACTER].setExistNextState(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) singleCharacterDFATable.get(_state[SINGLECAHRACTER].getStateLocation());
            _state[SINGLECAHRACTER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!_state[SINGLECAHRACTER].getIsFinalState()&&_state[SINGLECAHRACTER].getStateLocation()==5)
                _state[SINGLECAHRACTER].setIsFinalState(true);
        }catch(NullPointerException e){
            _state[SINGLECAHRACTER].setExistNextState(false);
            return;
        }
        if(ch!='\''){
            lexemes[SINGLECAHRACTER].addValue(ch);
        }
    }

    public void literalStringDFA(int position) {
        if(!_state[LITERALSTRING].getExistNextState())
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
            _state[LITERALSTRING].setExistNextState(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) literalStringDFATable.get(_state[LITERALSTRING].getStateLocation());
            _state[LITERALSTRING].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!_state[LITERALSTRING].getIsFinalState()&&_state[LITERALSTRING].getStateLocation()==5)
                _state[LITERALSTRING].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[LITERALSTRING].setExistNextState(false);
            return;
        }
        if(ch!='\"') {
            lexemes[LITERALSTRING].addValue(ch);
        }
    }

    public void signedIntegerDFA(int position) {
        if(!_state[SIGNEDINTEGER].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        String symbolType;
        if ((ch >= '1' && ch <= '9') && (_state[SIGNEDINTEGER].getStateLocation() == 0 || _state[SIGNEDINTEGER].getStateLocation() == 2))
            symbolType = "positive";
        else if ((ch >= '0' && ch <= '9') && (_state[SIGNEDINTEGER].getStateLocation() == 1 || _state[SIGNEDINTEGER].getStateLocation() == 3))
            symbolType = "digit";
        else if (ch == '-')
            symbolType = "-";
        else if (ch == '0' && _state[SIGNEDINTEGER].getStateLocation() == 0)
            symbolType = "0";
        else {
            _state[SIGNEDINTEGER].setExistNextState(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) signedIntegerDFATable.get(_state[SIGNEDINTEGER].getStateLocation());
            _state[SIGNEDINTEGER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!_state[SIGNEDINTEGER].getIsFinalState()&&(_state[SIGNEDINTEGER].getStateLocation()==1||_state[SIGNEDINTEGER].getStateLocation()==3||_state[SIGNEDINTEGER].getStateLocation()==4))
                _state[SIGNEDINTEGER].setIsFinalState(true);
        }catch(NullPointerException e){
            _state[SIGNEDINTEGER].setExistNextState(false);
            return;
        }
        lexemes[SIGNEDINTEGER].addValue(ch);
    }

    public void keywordDFA(int position){
        if(!_state[KEYWORD].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)keywordDFATable.get(_state[KEYWORD].getStateLocation());
        try {
            _state[KEYWORD].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[KEYWORD].getIsFinalState()&&_state[KEYWORD].getStateLocation()==18)
                _state[KEYWORD].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[KEYWORD].setExistNextState(false);
            return;
        }
        lexemes[KEYWORD].addValue(ch);
    }
    public void variableTypeDFA(int position){
        if(!_state[VARIABLETYPE].getExistNextState())
            return;

        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)variableTypeDFATable.get(_state[VARIABLETYPE].getStateLocation());
        try {
            _state[VARIABLETYPE].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[VARIABLETYPE].getIsFinalState()&&_state[VARIABLETYPE].getStateLocation()==17)
                _state[VARIABLETYPE].setIsFinalState(true);
        }catch (NullPointerException e){
            _state[VARIABLETYPE].setExistNextState(false);
            return;
        }
        lexemes[VARIABLETYPE].addValue(ch);
    }
    public void booleanStringDFA(int position) {
        if(!_state[BOOLEANSTRING].getExistNextState())
            return;

        char ch = inputstr.charAt(position);

        JSONObject transition = (JSONObject) booleanStringDFATable.get(_state[BOOLEANSTRING].getStateLocation());
        try {
            _state[BOOLEANSTRING].setStateLocation(Integer.parseInt(transition.get(Character.toString(ch)).toString()));
            if(!_state[BOOLEANSTRING].getIsFinalState()&&_state[BOOLEANSTRING].getStateLocation()==8)
                _state[BOOLEANSTRING].setIsFinalState(true);
        } catch (NullPointerException e) {
            _state[BOOLEANSTRING].setExistNextState(false);
            return;
        }
        lexemes[BOOLEANSTRING].addValue(ch);
    }
    public void identifierDFA(int position) {
        if(!_state[IDENTIFIER].getExistNextState())
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
            _state[IDENTIFIER].setExistNextState(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) indentifierDFATable.get(_state[IDENTIFIER].getStateLocation());
            _state[IDENTIFIER].setStateLocation(Integer.parseInt(transition.get(symbolType).toString()));
            if(!_state[IDENTIFIER].getIsFinalState()&&(_state[IDENTIFIER].getStateLocation()==1||_state[IDENTIFIER].getStateLocation()==2||_state[IDENTIFIER].getStateLocation()==3||_state[IDENTIFIER].getStateLocation()==4||_state[IDENTIFIER].getStateLocation()==5))
                _state[IDENTIFIER].setIsFinalState(true);
        }catch(NullPointerException e){
            _state[IDENTIFIER].setExistNextState(false);
            return;
        }
        lexemes[IDENTIFIER].addValue(ch);
    }
}
