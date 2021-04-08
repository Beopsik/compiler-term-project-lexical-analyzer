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
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((str=br.readLine())!=null){
                inputstr+=str;
            }
            DFA dfa=new DFA(inputstr, 0);
            dfa.run();
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