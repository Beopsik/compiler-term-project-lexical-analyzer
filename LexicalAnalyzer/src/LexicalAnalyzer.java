import dfa.LiteralStringDFA;

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
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            /*while((str = br.readLine())!=null){

            }*/
            str=br.readLine();
            literalStringDFA=new LiteralStringDFA(str, 0);
            System.out.println(literalStringDFA.analyze());
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