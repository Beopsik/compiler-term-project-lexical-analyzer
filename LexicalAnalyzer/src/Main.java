import java.io.*;

public class Main {

    public static void main(String[] args) {

        /*if(args.length == 0) {
            System.out.println("입력받은 파일이 없습니다.");
            System.exit(0);
        }*/

        File file = new File("C:\\Users\\문법식\\Desktop\\개인공부\\GitKraken\\compiler-term-project-lexical-analyzer\\LexicalAnalyzer\\src\\Input.java");

        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(file);

        lexicalAnalyzer.toString();
    }
}
