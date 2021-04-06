import dfa.*;

import java.io.*;

public class LexicalAnalyzer {

    private File file;

    public LexicalAnalyzer(File file) {
        this.file = file;
    }

    @Override
    public String toString() {

        String str = "";
        String inputstr="";
        LiteralStringDFA literalStringDFA;
        IdentifierDFA identifierDFA;
        SignedIntegerDFA signedIntegerDFA;
        SingleCharaterDFA singleCharaterDFA;
        VariableTypeDFA variableTypeDFA;
        KeywordDFA keywordDFA;
        ComparisonOperatorDFA comparisonOperatorDFA;
        BooleanStringDFA booleanStringDFA;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((str=br.readLine())!=null){
                inputstr+=str;
            }
            //literalStringDFA=new LiteralStringDFA(str, 0);
            //identifierDFA=new IdentifierDFA(str, 0);
            //signedIntegerDFA=new SignedIntegerDFA(str, 0);
            //singleCharaterDFA=new SingleCharaterDFA(str, 0);
            //comparisonOperatorDFA=new ComparisonOperatorDFA(str, 0);
            variableTypeDFA=new VariableTypeDFA(inputstr);
            keywordDFA=new KeywordDFA(inputstr);
            booleanStringDFA=new BooleanStringDFA(inputstr);
            identifierDFA=new IdentifierDFA(inputstr);
            //System.out.println("Vposition: " + variableTypeDFA.analyze(0));
            //System.out.println("------------------------------------------------------");
            //System.out.println("Kposition: " + keywordDFA.analyze(0)+", "+keywordDFA.getResultStr());
            //System.out.println("------------------------------------------------------");
            //System.out.println("Bposition: " + booleanStringDFA.analyze(0)+", "+booleanStringDFA.getResultStr());
            //System.out.println("------------------------------------------------------");
            //System.out.println("Iposition: " + identifierDFA.analyze(0));
            //System.out.println("------------------------------------------------------");
            for(int i=0; i<inputstr.length();) {
                if(inputstr.charAt(i)==' ')
                    i++;
                System.out.println(i);

                variableTypeDFA.analyze(i);
                System.out.println("i: "+i+", v:"+variableTypeDFA.getEndPosition());

                keywordDFA.analyze(i);
                System.out.println("i: "+i+", k:"+keywordDFA.getEndPosition());

                booleanStringDFA.analyze(i);
                System.out.println("i: "+i+", b:"+booleanStringDFA.getEndPosition());

                identifierDFA.analyze(i);
                System.out.println("i: "+i+", I:"+identifierDFA.getEndPosition());

                if(variableTypeDFA.getEndPosition()>=i){
                    if(variableTypeDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0){
                        System.out.println("<VARIABLETYPE, " +variableTypeDFA.getResultStr()+">");
                        i=variableTypeDFA.getEndPosition();
                    }else{
                        System.out.println("<VARIABLETYPE, " +variableTypeDFA.getResultStr()+">");
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        System.out.println();
                        i= identifierDFA.getEndPosition();;
                    }
                }else if(keywordDFA.getEndPosition()>=i){
                    if(keywordDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0){
                        System.out.println("<KEYWORD, " +keywordDFA.getResultStr()+">");
                        i=keywordDFA.getEndPosition();
                    }else{
                        System.out.println("<KEYWORD, " +keywordDFA.getResultStr()+">");
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        System.out.println();
                        i= identifierDFA.getEndPosition();
                    }
                }else if(booleanStringDFA.getEndPosition()>=i){
                    if(booleanStringDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0){
                        System.out.println("<BOOLEAN, " +booleanStringDFA.getResultStr()+">");
                        i=booleanStringDFA.getEndPosition();
                    }else{
                        System.out.println("<BOOLEAN, " +booleanStringDFA.getResultStr()+">");
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        System.out.println();
                        i= identifierDFA.getEndPosition();
                    }
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "LexicalAnalyzer{" +
                "file=" + str +
                '}';
    }
}