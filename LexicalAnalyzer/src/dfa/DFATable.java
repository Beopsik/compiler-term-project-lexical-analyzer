package dfa;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DFATable {
    public DFATable(){}

    public JSONArray identifierDFATable(){
        String dfaTable="[" +
                "{\"_\":1, \"letter\":2}"+
                "{\"_\":3, \"letter\":4, \"digit\": 5}"+
                "{\"_\":3, \"letter\":4, \"digit\": 5}"+
                "{\"_\":3, \"letter\":4, \"digit\": 5}"+
                "{\"_\":3, \"letter\":4, \"digit\": 5}"+
                "{\"_\":3, \"letter\":4, \"digit\": 5}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            //JSONArray dfaTableArray= (JSONArray) jsonParser.parse(dfaTable);
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray singleCharacterDFATable(){
        String dfaTable="[" +
                "{\"'\":1}"+
                "{\"digit\":2, \"letter\": 3, \"blank\":4}"+
                "{\"'\":5}"+
                "{\"'\":5}"+
                "{\"'\":5}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray literalStringDFATable(){
        String dfaTable="[" +
                "{\"double quotes\":1}"+
                "{\"double quotes\":5, \"digit\":2, \"letter\": 3, \"blank\":4}"+
                "{\"double quotes\":5, \"digit\":2, \"letter\": 3, \"blank\":4}"+
                "{\"double quotes\":5, \"digit\":2, \"letter\": 3, \"blank\":4}"+
                "{\"double quotes\":5, \"digit\":2, \"letter\": 3, \"blank\":4}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray signedIntegerDFATable(){
        String dfaTable="[" +
                "{\"positive\":1, \"-\":2, \"0\":4}"+
                "{\"digit\":3}"+
                "{\"positive\":1}"+
                "{\"digit\":3}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray booleanStringDFATable(){
        String dfaTable="[" +
                "{\"t\":1, \"f\":4}"+
                "{\"r\":2}"+
                "{\"u\":3}"+
                "{\"e\":8}"+
                "{\"a\":5}"+
                "{\"l\":6}"+
                "{\"s\":7}"+
                "{\"e\":8}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray keywordDFATable(){
        String dfaTable="[" +
                "{\"i\":1, \"e\":2, \"w\":5, \"c\":9, \"r\":13}"+
                "{\"f\":18}"+
                "{\"l\":3}"+
                "{\"s\":4}"+
                "{\"e\":18}"+
                "{\"h\":6}"+
                "{\"i\":7}"+
                "{\"l\":8}"+
                "{\"e\":18}"+
                "{\"l\":10}"+
                "{\"a\":11}"+
                "{\"s\":12}"+
                "{\"s\":18}"+
                "{\"e\":14}"+
                "{\"t\":15}"+
                "{\"u\":16}"+
                "{\"r\":17}"+
                "{\"n\":18}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray variableTypeDFATable(){
        String dfaTable="[" +
                "{\"i\":1, \"c\":3, \"b\":6, \"S\":12}"+
                "{\"n\":2}"+
                "{\"t\":17}"+
                "{\"h\":4}"+
                "{\"a\":5}"+
                "{\"r\":17}"+
                "{\"o\":7}"+
                "{\"o\":8}"+
                "{\"l\":9}"+
                "{\"e\":10}"+
                "{\"a\":11}"+
                "{\"n\":17}"+
                "{\"t\":13}"+
                "{\"r\":14}"+
                "{\"i\":15}"+
                "{\"n\":16}"+
                "{\"g\":17}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray arithmeticOperatorDFATable(){
        String dfaTable="[" +
                "{\"+\":1, \"-\":1, \"*\":1, \"/\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray assginmentOperatorDFATable(){
        String dfaTable="[" +
                "{\"=\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray ComparisonOperatorDFATable(){
        String dfaTable="[" +
                "{\"=\":1, \"!\":2, \"<\":3, \">\":4}"+
                "{\"=\":5}"+
                "{\"=\":5}"+
                "{\"=\":5}"+
                "{\"=\":5}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray terminateSymbolDFATable(){
        String dfaTable="[" +
                "{\";\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray lParenDFATable(){
        String dfaTable="[" +
                "{\"(\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray rParenDFATable(){
        String dfaTable="[" +
                "{\")\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray lBraceDFATable(){
        String dfaTable="[" +
                "{\"{\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray rBraceDFATable(){
        String dfaTable="[" +
                "{\"}\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray lBranketDFATable(){
        String dfaTable="[" +
                "{\"[\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray rBranketDFATable(){
        String dfaTable="[" +
                "{\"]\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray commaDFATable(){
        String dfaTable="[" +
                "{\",\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
    public JSONArray whiteSpaceDFATable(){
        String dfaTable="[" +
                "{\" \":1, \"\t\":1, \"\n\":1}"+
                "{}"+
                "]";
        JSONParser jsonParser=new JSONParser();
        try {
            return (JSONArray) jsonParser.parse(dfaTable);
        }catch(ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
