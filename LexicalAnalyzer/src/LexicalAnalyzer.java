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
            System.out.println("Vposition: " + variableTypeDFA.analyze(0));
            System.out.println("------------------------------------------------------");
            System.out.println("Kposition: " + keywordDFA.analyze(0));
            System.out.println("------------------------------------------------------");
            System.out.println("Bposition: " + booleanStringDFA.analyze(0));
            System.out.println("------------------------------------------------------");
            System.out.println("Iposition: " + identifierDFA.analyze(0));
            System.out.println("------------------------------------------------------");
            for(int i=0; i<inputstr.length(); i++) {
                variableTypeDFA.analyze(i);
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