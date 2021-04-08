package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    private final String inputstr;
    private final int startPosition;

    private final static int TOKEN_NUM=19;
    private final List<Lexeme> liveDFAList=new ArrayList<>();
    private final Lexeme[] lexemes=new Lexeme[TOKEN_NUM];
    private final int[] state=new int[TOKEN_NUM];

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
        }
        lexemesInit();
        for(int i=startPosition; i<inputstr.length(); i++) {

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
            for (Lexeme lexeme : lexemes) {
                if (lexeme.getLive()) {
                    if (liveDFAListClear) {
                        liveDFAList.clear();
                        liveDFAListClear = false;
                        liveDFA = true;
                    }
                    liveDFAList.add(lexeme);
                    //System.out.println("<"+lexemes[j].getKey()+", "+lexemes[j].getValue()+">");
                }
            }
            if(!liveDFA){
                /*for (int k=0; k<liveDFAList.size(); k++){
                    Lexeme result=liveDFAList.get(k);
                    System.out.println("<"+result.getKey()+", "+result.getValue()+">");
                }*/
                //System.out.println("-------------------------------");
                Lexeme result=liveDFAList.get(0);
                System.out.println("<"+result.getKey()+", "+result.getValue()+">");
                for (int k=0; k<lexemes.length; k++){
                    lexemes[k].lexemeClear();
                    state[k]=0;
                }
                i--;
            }else if(i==inputstr.length()-1){
                Lexeme result=liveDFAList.get(0);
                System.out.println("<"+result.getKey()+", "+result.getValue()+">");
            }
        }
    }

    public void arithmeticOperatorDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)arithmeticOperatorDFATable.get(state[ARITHMETICOPERATOR]);
        try {
            state[ARITHMETICOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[ARITHMETICOPERATOR].setLive(false);
            return;
        }
        lexemes[ARITHMETICOPERATOR].addValue(ch);
    }
    public void comparsionOperatorDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)comparisonOperatorDFATable.get(state[COMPARISONOPERATOR]);
        try {
            state[COMPARISONOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[COMPARISONOPERATOR].setLive(false);
            return;
        }
        lexemes[COMPARISONOPERATOR].addValue(ch);
    }
    public void assignmentOperatorDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)assignmentOperatorDFATable.get(state[ASSIGNMENTOPERATOR]);
        try {
            state[ASSIGNMENTOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[ASSIGNMENTOPERATOR].setLive(false);
            return;
        }
        lexemes[ASSIGNMENTOPERATOR].addValue(ch);
    }
    public void terminateSymbolDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)terminateSymboleDFATable.get(state[TERMINATEOPERATOR]);
        try {
            state[TERMINATEOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[TERMINATEOPERATOR].setLive(false);
            return;
        }
        lexemes[TERMINATEOPERATOR].addValue(ch);
    }
    public void lParenDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lParenDFATable.get(state[LPAREN]);
        try {
            state[LPAREN] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[LPAREN].setLive(false);
            return;
        }
        lexemes[LPAREN].addValue(ch);
    }
    public void rParenDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rParenDFATable.get(state[RPAREN]);
        try {
            state[RPAREN] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[RPAREN].setLive(false);
            return;
        }
        lexemes[RPAREN].addValue(ch);
    }
    public void lBraceDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBraceDFATable.get(state[LBRACE]);
        try {
            state[LBRACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[LBRACE].setLive(false);
            return;
        }
        lexemes[LBRACE].addValue(ch);
    }
    public void rBraceDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBraceDFATAble.get(state[RBRACE]);
        try {
            state[RBRACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[RBRACE].setLive(false);
            return;
        }
        lexemes[RBRACE].addValue(ch);
    }
    public void lBranketDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)lBranketDFATable.get(state[LBRANKET]);
        try {
            state[LBRANKET] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[LBRANKET].setLive(false);
            return;
        }
        lexemes[LBRANKET].addValue(ch);
    }
    public void rBranketDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)rBranketDFATable.get(state[RBRANKET]);
        try {
            state[RBRANKET] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[RBRANKET].setLive(false);
            return;
        }
        lexemes[RBRANKET].addValue(ch);
    }
    public void commaDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)commaDFATable.get(state[COMMA]);
        try {
            state[COMMA] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[COMMA].setLive(false);
            return;
        }
        lexemes[COMMA].addValue(ch);
    }
    public void whiteSpaceDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)whiteSpaceDFATable.get(state[WHITESPACE]);
        try {
            state[WHITESPACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[WHITESPACE].setLive(false);
            return;
        }
        lexemes[WHITESPACE].addValue(ch);
    }

    public void singleCharacterDFA(int position) {
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
            lexemes[SINGLECAHRACTER].setLive(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) singleCharacterDFATable.get(state[SINGLECAHRACTER]);
            state[SINGLECAHRACTER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            lexemes[SINGLECAHRACTER].setLive(false);
            return;
        }
        if(ch!='\''){
            lexemes[SINGLECAHRACTER].addValue(ch);
        }
    }

    public void literalStringDFA(int position) {
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
            lexemes[LITERALSTRING].setLive(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) literalStringDFATable.get(state[LITERALSTRING]);
            state[LITERALSTRING] = Integer.parseInt(transition.get(symbolType).toString());
        }catch (NullPointerException e){
            lexemes[LITERALSTRING].setLive(false);
            return;
        }
        if(ch!='\"') {
            lexemes[LITERALSTRING].addValue(ch);
        }
    }

    public void signedIntegerDFA(int position) {
        char ch = inputstr.charAt(position);

        String symbolType;
        if ((ch >= '1' && ch <= '9') && (state[SIGNEDINTEGER] == 0 || state[SIGNEDINTEGER] == 2))
            symbolType = "positive";
        else if ((ch >= '0' && ch <= '9') && (state[SIGNEDINTEGER] == 1 || state[SIGNEDINTEGER] == 3))
            symbolType = "digit";
        else if (ch == '-')
            symbolType = "-";
        else if (ch == '0' && state[SIGNEDINTEGER] == 0)
            symbolType = "0";
        else {
            lexemes[SIGNEDINTEGER].setLive(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) signedIntegerDFATable.get(state[SIGNEDINTEGER]);
            state[SIGNEDINTEGER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            lexemes[SIGNEDINTEGER].setLive(false);
            return;
        }
        lexemes[SIGNEDINTEGER].addValue(ch);
    }

    public void keywordDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)keywordDFATable.get(state[KEYWORD]);
        try {
            state[KEYWORD] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[KEYWORD].setLive(false);
            return;
        }
        lexemes[KEYWORD].addValue(ch);
    }
    public void variableTypeDFA(int position){
        char ch=inputstr.charAt(position);

        JSONObject transition=(JSONObject)variableTypeDFATable.get(state[VARIABLETYPE]);
        try {
            state[VARIABLETYPE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            lexemes[VARIABLETYPE].setLive(false);
            return;
        }
        lexemes[VARIABLETYPE].addValue(ch);
    }
    public void booleanStringDFA(int position) {
        char ch = inputstr.charAt(position);

        JSONObject transition = (JSONObject) booleanStringDFATable.get(state[BOOLEANSTRING]);
        try {
            state[BOOLEANSTRING] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        } catch (NullPointerException e) {
            lexemes[BOOLEANSTRING].setLive(false);
            return;
        }
        lexemes[BOOLEANSTRING].addValue(ch);
    }
    public void identifierDFA(int position) {
        char ch = inputstr.charAt(position);

        String symbolType;
        if (ch == '_')
            symbolType = "_";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else {
            lexemes[IDENTIFIER].setLive(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) indentifierDFATable.get(state[IDENTIFIER]);
            state[IDENTIFIER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            lexemes[IDENTIFIER].setLive(false);
            return;
        }
        lexemes[IDENTIFIER].addValue(ch);
    }
}
