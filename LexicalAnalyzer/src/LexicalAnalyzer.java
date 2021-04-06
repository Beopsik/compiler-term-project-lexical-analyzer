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
            System.out.println(inputstr);
            for(int i=0; i<inputstr.length();) {
                if(inputstr.charAt(i)==' ')
                    i++;
                //System.out.println("------------------------------------------------------");
                //System.out.println(i);
                //System.out.println("start input: "+inputstr.charAt(i));
                variableTypeDFA.analyze(i);
                //System.out.println("i: "+i+", v:"+variableTypeDFA.getEndPosition());
                //System.out.println();

                keywordDFA.analyze(i);
                //System.out.println("i: "+i+", k:"+keywordDFA.getEndPosition());
                //System.out.println();

                booleanStringDFA.analyze(i);
                //System.out.println("i: "+i+", b:"+booleanStringDFA.getEndPosition());
                //System.out.println();

                identifierDFA.analyze(i);
                //System.out.println("i: "+i+", I:"+identifierDFA.getEndPosition());
                if(variableTypeDFA.getEndPosition()>i){
                    //System.out.println("V: "+variableTypeDFA.getResultStr());
                    if(variableTypeDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0) {
                        System.out.println("<VARIABLETYPE, " + variableTypeDFA.getResultStr() + ">");
                        i = variableTypeDFA.getEndPosition();
                    }else{
                        //System.out.println('I');
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        i= identifierDFA.getEndPosition();;
                    }
                }else if(keywordDFA.getEndPosition()>i){
                    //System.out.println("K: "+keywordDFA.getResultStr());
                    if(keywordDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0){
                        System.out.println("<KEYWORD, " +keywordDFA.getResultStr()+">");
                        i=keywordDFA.getEndPosition();
                    }else{
                        //System.out.println('I');
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        i= identifierDFA.getEndPosition();;
                    }
                }else if(booleanStringDFA.getEndPosition()>i){
                    //System.out.println("B: "+booleanStringDFA.getResultStr());
                    if(booleanStringDFA.getResultStr().compareTo(identifierDFA.getResultStr())==0){
                        System.out.println("<BOOLEAN, " +booleanStringDFA.getResultStr()+">");
                        i=booleanStringDFA.getEndPosition();
                    }else{
                        //System.out.println('I');
                        System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                        i= identifierDFA.getEndPosition();;
                    }
                }else{
                    //System.out.println('I');
                    System.out.println("<IDENTIFIER, " +identifierDFA.getResultStr()+">");
                    i= identifierDFA.getEndPosition();;
                }
                //System.out.println("------------------------------------------------------");
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