/**
 * Created by loku on 02.05.15.
 */
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class Main {
    static String fileName;

    public static void main(String [] args){
        try {
            ANTLRFileStream input;
            if(args.length<1) {
                fileName = "result.ll";
                input = new ANTLRFileStream("abc");
            }else{
                fileName = args[0] + ".ll";
                input = new ANTLRFileStream(args[0]);
            }

            GramatykaLexer lexer = new GramatykaLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GramatykaParser parser = new GramatykaParser(tokens);

            ParseTree tree = parser.prog();
            ParseTreeWalker walker = new ParseTreeWalker();

            walker.walk(new ActionImpl(), tree);
        }
        catch (Exception e1){
            e1.printStackTrace();
        }
    }
}
