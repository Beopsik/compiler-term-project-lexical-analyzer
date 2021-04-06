package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DFA {
    private final String inputstr;
    private int startPostion;
    //private int endPostion;
    private String resultStr;
    private final DFATable dfaTable=new DFATable();
    private final JSONArray indentifierDFATable=dfaTable.identifierDFATable();
    private final JSONArray singleCharacterDFATable=dfaTable.singleCharacterDFATable();
    private final JSONArray literalStringDFATable=dfaTable.literalStringDFATable();
    private final JSONArray signedIntegerDFATable=dfaTable.signedIntegerDFATable();
    private final JSONArray booleanStringDFATable=dfaTable.booleanStringDFATable();
    private final JSONArray keywordDFATable=dfaTable.keywordDFATable();
    private final JSONArray variableTypeDFATable=dfaTable.variableTypeDFATable();
    private final JSONArray arithmeticOperatorDFATable=dfaTable.arithmeticOperatorDFATable();
    private final JSONArray assignmentOperatorDFATable=dfaTable.assginmentOperatorDFATable();
    private final JSONArray terminateSymboleDFATable=dfaTable.terminateSymbolDFATable();
    private final JSONArray lParenDFATable=dfaTable.lParenDFATable();
    private final JSONArray rParenDFATable=dfaTable.rParenDFATable();
    private final JSONArray lBraceDFATable=dfaTable.lBraceDFATable();
    private final JSONArray rBraceDFATAble=dfaTable.rBraceDFATable();
    private final JSONArray lBranketDFATable=dfaTable.lBranketDFATable();
    private final JSONArray rBranketDFATable=dfaTable.rBranketDFATable();
    private final JSONArray commaDFATable=dfaTable.commaDFATable();
    private final JSONArray whiteSpaceDFATable=dfaTable.whiteSpaceDFATable();

    public DFA(String inputstr){
        this.inputstr=inputstr;
    }
    public void identifierDFA(int position){
        boolean identifierDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;
            if(ch=='_')
                symbolType="_";
            else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
                symbolType="letter";
            else if(ch>='0'&&ch<='9')
                symbolType="digit";
            else {
                symbolType = "E"; //E is error
                System.out.println("iEnd");
                break;
            }

            JSONObject transition=(JSONObject)indentifierDFATable.get(state);
            state=Integer.parseInt(transition.get(symbolType).toString());
            resultStr+=ch;
        }
        System.out.println(resultStr);
     }
    public void singleCharacterDFA(int position){
        boolean signleCharacterDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;
        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;
            if(ch=='\'')
                symbolType="\'";
            else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
                symbolType="letter";
            else if(ch>='0'&&ch<='9')
                symbolType="digit";
            else if(ch==' ')
                symbolType="blank";
            else {
                symbolType = "E"; //E is error
                break;
            }

            JSONObject transition=(JSONObject)singleCharacterDFATable.get(state);
            state=Integer.parseInt(transition.get(symbolType).toString());
            resultStr+=ch;
            if (state == 5) {
                System.out.println(resultStr);
                endPostion=i+1;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void literalStringDFA(int position){
        boolean literalStringDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;
            if(ch=='\"')
                symbolType="double quotes";
            else if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
                symbolType="letter";
            else if(ch>='0'&&ch<='9')
                symbolType="digit";
            else if(ch==' ')
                symbolType="blank";
            else {
                symbolType = "E"; //E is error
                break;
            }

            JSONObject transition=(JSONObject)literalStringDFATable.get(state);
            state=Integer.parseInt(transition.get(symbolType).toString());
            resultStr+=ch;
            if (state == 5) {
                System.out.println(resultStr);
                endPostion=i+1;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void signedIntegerDFA(int position){
        boolean signedIntegerDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;
            if((ch>='1'&&ch<='9')&&(state==0||state==2))
                symbolType="positive";
            else if((ch>='0'&&ch<='9')&&(state==1||state==3))
                symbolType="digit";
            else if(ch=='-')
                symbolType="-";
            else if(ch=='0'&&state==0)
                symbolType="0";
            else {
                symbolType = "E"; //E is error
                break;
            }

            JSONObject transition=(JSONObject)signedIntegerDFATable.get(state);
            state=Integer.parseInt(transition.get(symbolType).toString());
            resultStr+=ch;
        }
        System.out.println(resultStr);
    }
    public void booleanStringDFA(int position){
        boolean booleanStringDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)booleanStringDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 8) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void keywordDFA(int position){
        boolean keywordDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)keywordDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 18) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void variableTypeDFA(int position){
        boolean variableTypeDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)variableTypeDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 17) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void arithmeticOperatorDFA(int position){
        boolean arithmenticOperatorDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)arithmeticOperatorDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void assignmentOperatorDFA(int position){
        boolean assignmnetOperatorDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)assignmentOperatorDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void terminateSymbolDFA(int position){
        boolean terminateSymbolDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)terminateSymboleDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void lParenDFA(int position){
        boolean lParenDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)lParenDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void rParenDFA(int position){
        boolean rParenDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)rParenDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void lBraceDFA(int position){
        boolean lBraceDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)lBraceDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void rBraceDFA(int position){
        boolean rBraceDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)rBraceDFATAble.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void lBranketDFA(int position){
        boolean lBranketDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)lBranketDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void rBranketDFA(int position){
        boolean rBranketDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)rBranketDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void commaDFA(int position){
        boolean commaDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)commaDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr);
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr);
    }
    public void whiteSpaceDFA(int position){
        boolean whiteSpaceDFALive=false;
        int state=0;
        resultStr="";
        startPostion=position;
        int endPostion=0;

        for(int i=startPostion; i<inputstr.length(); i++, endPostion=i){
            char ch=inputstr.charAt(i);
            String symbolType;

            JSONObject transition=(JSONObject)whiteSpaceDFATable.get(state);
            try {
                state = Integer.parseInt(transition.get(Character.toString(ch)).toString());
            }catch (NullPointerException e){
                symbolType="E";
                break;
            }
            resultStr+=ch;
            if (state == 1) {
                System.out.println(resultStr+";");
                endPostion=startPostion;
                break;
            }
        }
        System.out.println(resultStr+";");
    }
    public String getResultStr(){
        return resultStr;
    }
}
