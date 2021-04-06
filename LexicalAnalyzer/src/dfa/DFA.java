package dfa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DFA {
    private final String inputstr;
    private int startPostion;
    //private int endPostion;
    private String resultStr;
    private DFATable dfaTable=new DFATable();
    private JSONArray indentifierDFATable=dfaTable.identifierDFATable();
    private JSONArray singleCharacterDFATable=dfaTable.singleCharacterDFATable();
    private JSONArray literalStringDFATable=dfaTable.literalStringDFATable();
    private JSONArray signedIntegerDFATable=dfaTable.signedIntegerDFATable();
    private JSONArray booleanStringDFATable=dfaTable.booleanStringDFATable();
    private JSONArray keywordDFATable=dfaTable.keywordDFATable();
    private JSONArray variableTypeDFATable=dfaTable.variableTypeDFATable();
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
    public String getResultStr(){
        return resultStr;
    }
}
