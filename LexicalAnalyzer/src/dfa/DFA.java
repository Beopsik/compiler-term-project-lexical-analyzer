package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DFA {
    private final String inputstr;
    private int startPosition;
    //private int endPostion;
    private String resultStr;
    private final DFATable dfaTable = new DFATable();
    private List<Lexeme> liveDFAList=new ArrayList<>();
    private Lexeme[] lexemes=new Lexeme[19];
    private int[] state=new int[19];
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

    public DFA(String inputstr, int postion) {
        this.inputstr = inputstr;
        this.startPosition = postion;
    }

    public void run() {
        System.out.println(lexemes.length);
        for(int i=0; i<lexemes.length; i++){
            lexemes[i]=new Lexeme();
        }
        for(int i=startPosition; i<inputstr.length(); i++) {
            //System.out.println(i);
            //System.out.println(count);

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
            for (int j=0; j<lexemes.length; j++){
                if(lexemes[j].getLive()){
                    if(liveDFAListClear){
                        liveDFAList.clear();
                        liveDFAListClear=false;
                        liveDFA=true;
                    }
                    liveDFAList.add(lexemes[j]);
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
            }
        }
    }

    public void arithmeticOperatorDFA(int position){
        boolean arithmenticOperatorDFALive=true;
        lexemes[ARITHMETICOPERATOR].setKey("ARITHMETICOPERATOR");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)arithmeticOperatorDFATable.get(state[ARITHMETICOPERATOR]);
        try {
            state[ARITHMETICOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            arithmenticOperatorDFALive=false;
            lexemes[ARITHMETICOPERATOR].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[ARITHMETICOPERATOR].addValue(ch);
    }
    public void comparsionOperatorDFA(int position){
        boolean comparisonOperatorDFALive=true;
        lexemes[COMPARISONOPERATOR].setKey("COMPARISONOPERATOR");
        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)comparisonOperatorDFATable.get(state[COMPARISONOPERATOR]);
        try {
            state[COMPARISONOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            comparisonOperatorDFALive=false;
            lexemes[COMPARISONOPERATOR].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[COMPARISONOPERATOR].addValue(ch);
    }
    public void assignmentOperatorDFA(int position){
        boolean assignmnetOperatorDFALive=true;
        lexemes[ASSIGNMENTOPERATOR].setKey("ASSIGNMENTOPERATOR");
        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)assignmentOperatorDFATable.get(state[ASSIGNMENTOPERATOR]);
        try {
            state[ASSIGNMENTOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            assignmnetOperatorDFALive=false;
            lexemes[ASSIGNMENTOPERATOR].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[ASSIGNMENTOPERATOR].addValue(ch);
    }
    public void terminateSymbolDFA(int position){
        boolean terminateSymbolDFALive=true;
        lexemes[TERMINATEOPERATOR].setKey("TERMINATESYMBOL");
        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)terminateSymboleDFATable.get(state[TERMINATEOPERATOR]);
        try {
            state[TERMINATEOPERATOR] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            terminateSymbolDFALive=false;
            lexemes[TERMINATEOPERATOR].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[TERMINATEOPERATOR].addValue(ch);
    }
    public void lParenDFA(int position){
        boolean lParenDFALive=true;
        lexemes[LPAREN].setKey("LPAREN");
        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)lParenDFATable.get(state[LPAREN]);
        try {
            state[LPAREN] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            lParenDFALive=false;
            lexemes[LPAREN].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[LPAREN].addValue(ch);
    }
    public void rParenDFA(int position){
        boolean rParenDFALive=true;
        lexemes[RPAREN].setKey("RPAREN");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)rParenDFATable.get(state[RPAREN]);
        try {
            state[RPAREN] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            rParenDFALive=false;
            lexemes[RPAREN].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[RPAREN].addValue(ch);
    }
    public void lBraceDFA(int position){
        boolean lBraceDFALive=true;
        lexemes[LBRACE].setKey("LBRACE");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)lBraceDFATable.get(state[LBRACE]);
        try {
            state[LBRACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            lBraceDFALive=false;
            lexemes[LBRACE].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[LBRACE].addValue(ch);
    }
    public void rBraceDFA(int position){
        boolean rBraceDFALive=true;
        lexemes[RBRACE].setKey("RBRACE");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)rBraceDFATAble.get(state[RBRACE]);
        try {
            state[RBRACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            rBraceDFALive=false;
            lexemes[RBRACE].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[RBRACE].addValue(ch);
    }
    public void lBranketDFA(int position){
        boolean lBranketDFALive=true;
        lexemes[LBRANKET].setKey("LBRANKET");

        //int state=0;
        resultStr="";
        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)lBranketDFATable.get(state[LBRANKET]);
        try {
            state[LBRANKET] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            lBranketDFALive=false;
            lexemes[LBRANKET].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[LBRANKET].addValue(ch);
    }
    public void rBranketDFA(int position){
        boolean rBranketDFALive=true;
        lexemes[RBRANKET].setKey("RBRANKET");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)rBranketDFATable.get(state[RBRANKET]);
        try {
            state[RBRANKET] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            rBranketDFALive=false;
            lexemes[RBRANKET].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[RBRANKET].addValue(ch);
    }
    public void commaDFA(int position){
        boolean commaDFALive=true;
        lexemes[COMMA].setKey("COMMA");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)commaDFATable.get(state[COMMA]);
        try {
            state[COMMA] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            commaDFALive=false;
            lexemes[COMMA].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[COMMA].addValue(ch);
    }
    public void whiteSpaceDFA(int position){
        boolean whiteSpaceDFALive=true;
        lexemes[WHITESPACE].setKey("WHITESPACE");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)whiteSpaceDFATable.get(state[WHITESPACE]);
        try {
            state[WHITESPACE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            whiteSpaceDFALive=false;
            lexemes[WHITESPACE].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[WHITESPACE].addValue(ch);
    }

    public void singleCharacterDFA(int position) {
        boolean signleCharacterDFALive = true;
        lexemes[SINGLECAHRACTER].setKey("SINGLECHARACTER");

        //int state = 0;
        char ch = inputstr.charAt(position);
        String symbolType;
        if (ch == '\'')
            symbolType = "\'";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else if (ch == ' ')
            symbolType = "blank";
        else {
            symbolType = "E"; //E is error
            signleCharacterDFALive=false;
            lexemes[SINGLECAHRACTER].setLive(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) singleCharacterDFATable.get(state[SINGLECAHRACTER]);
            state[SINGLECAHRACTER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            symbolType = "E"; //E is error
            signleCharacterDFALive=false;
            lexemes[SINGLECAHRACTER].setLive(false);
            return;
        }
        resultStr += ch;
        lexemes[SINGLECAHRACTER].addValue(ch);
    }

    public void literalStringDFA(int position) {
        boolean literalStringDFALive = true;
        lexemes[LITERALSTRING].setKey("LITERALSTRING");

        //int state = 0;
        resultStr = "";

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
            symbolType = "E"; //E is error
            literalStringDFALive=false;
            lexemes[LITERALSTRING].setLive(false);
            return;
        }
        try {
            JSONObject transition = (JSONObject) literalStringDFATable.get(state[LITERALSTRING]);
            state[LITERALSTRING] = Integer.parseInt(transition.get(symbolType).toString());
        }catch (NullPointerException e){
            symbolType = "E"; //E is error
            literalStringDFALive=false;
            lexemes[LITERALSTRING].setLive(false);
            return;
        }
        resultStr += ch;
        lexemes[LITERALSTRING].addValue(ch);
    }

    public void signedIntegerDFA(int position) {
        boolean signedIntegerDFALive = true;
        lexemes[SIGNEDINTEGER].setKey("SIGNEDINTEGER");

        //int state = 0;
        resultStr = "";

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
            symbolType = "E"; //E is error
            signedIntegerDFALive=false;
            lexemes[SIGNEDINTEGER].setLive(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) signedIntegerDFATable.get(state[SIGNEDINTEGER]);
            state[SIGNEDINTEGER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            symbolType = "E"; //E is error
            signedIntegerDFALive=false;
            lexemes[SIGNEDINTEGER].setLive(false);
            return;
        }
        resultStr += ch;
        lexemes[SIGNEDINTEGER].addValue(ch);
    }

    public void keywordDFA(int position){
        boolean keywordDFALive=true;
        lexemes[KEYWORD].setKey("KEYWORD");

        //int state=0;
        resultStr="";
        char ch=inputstr.charAt(position);
        String symbolType;

        JSONObject transition=(JSONObject)keywordDFATable.get(state[KEYWORD]);
        try {
            state[KEYWORD] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            keywordDFALive=false;
            lexemes[KEYWORD].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[KEYWORD].addValue(ch);
    }
    public void variableTypeDFA(int position){
        boolean variableTypeDFALive=true;
        lexemes[VARIABLETYPE].setKey("VARIABLETYPE");

        //int state=0;
        resultStr="";

        char ch=inputstr.charAt(position);
        String symbolType;
        JSONObject transition=(JSONObject)variableTypeDFATable.get(state[VARIABLETYPE]);
        try {
            state[VARIABLETYPE] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        }catch (NullPointerException e){
            symbolType="E";
            variableTypeDFALive=false;
            lexemes[VARIABLETYPE].setLive(false);
            return;
        }
        resultStr+=ch;
        lexemes[VARIABLETYPE].addValue(ch);
    }
    public void booleanStringDFA(int position) {
        boolean booleanStringDFALive = true;
        lexemes[BOOLEANSTRING].setKey("BOOLEANSTRING");

        //int state = 0;
        resultStr = "";

        char ch = inputstr.charAt(position);
        String symbolType;

        JSONObject transition = (JSONObject) booleanStringDFATable.get(state[BOOLEANSTRING]);
        try {
            state[BOOLEANSTRING] = Integer.parseInt(transition.get(Character.toString(ch)).toString());
        } catch (NullPointerException e) {
            symbolType = "E";
            booleanStringDFALive=false;
            lexemes[BOOLEANSTRING].setLive(false);
            return;
        }
        resultStr += ch;
        lexemes[BOOLEANSTRING].addValue(ch);

    }
    public void identifierDFA(int position) {

        boolean identifierDFALive = true;
        lexemes[IDENTIFIER].setKey("IDENTIFIER");

        //int state = 0;
        resultStr = "";

        char ch = inputstr.charAt(position);
        String symbolType;
        if (ch == '_')
            symbolType = "_";
        else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
            symbolType = "letter";
        else if (ch >= '0' && ch <= '9')
            symbolType = "digit";
        else {
            symbolType = "E"; //E is error
            identifierDFALive=false;
            lexemes[IDENTIFIER].setLive(false);
            return;
        }

        try {
            JSONObject transition = (JSONObject) indentifierDFATable.get(state[IDENTIFIER]);
            state[IDENTIFIER] = Integer.parseInt(transition.get(symbolType).toString());
        }catch(NullPointerException e){
            symbolType = "E"; //E is error
            identifierDFALive=false;
            lexemes[IDENTIFIER].setLive(false);
            return;
        }
        resultStr += ch;
        lexemes[IDENTIFIER].addValue(ch);
    }
    public String getResultStr(){
        return resultStr;
    }
}
