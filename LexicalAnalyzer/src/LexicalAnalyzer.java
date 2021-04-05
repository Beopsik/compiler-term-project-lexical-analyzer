import dfa.IdentifierDFA;
import dfa.LiteralStringDFA;
import dfa.SignedIntegerDFA;
import dfa.SingleCharaterDFA;

import java.io.*;

public class LexicalAnalyzer {

    private File file;

    public LexicalAnalyzer(File file) {
        this.file = file;
    }

    @Override
    public String toString() {

        String str = "";
        LiteralStringDFA literalStringDFA;
        IdentifierDFA identifierDFA;
        SignedIntegerDFA signedIntegerDFA;
        SingleCharaterDFA singleCharaterDFA;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            /*while((str = br.readLine())!=null){

            }*/
            str=br.readLine();
            //literalStringDFA=new LiteralStringDFA(str, 0);
            //identifierDFA=new IdentifierDFA(str, 0);
            //signedIntegerDFA=new SignedIntegerDFA(str, 0);
            singleCharaterDFA=new SingleCharaterDFA(str, 0);
            System.out.println("position: "+singleCharaterDFA.analyze());
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