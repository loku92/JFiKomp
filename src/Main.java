/**
 * Created by loku on 02.05.15.
 */
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;


public class Main {

    public static void main(String [] args){
        try {
            ANTLRFileStream input = new ANTLRFileStream("abc");

            GramatykaLexer lexer = new GramatykaLexer(input);

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            GramatykaParser parser = new GramatykaParser(tokens);

            ParseTree tree = parser.prog();

            //System.out.println(tree.toStringTree(parser));

            ParseTreeWalker walker = new ParseTreeWalker();
            walker.walk(new LLVMactions(), tree);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
