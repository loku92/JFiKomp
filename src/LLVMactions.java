import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;

/**
 * Created by loku on 02.05.15.
 */
public class LLVMactions extends GramatykaBaseListener{

    HashSet<String> variables = new HashSet<String>();
    String value;

    @Override
    public void exitProg(@NotNull GramatykaParser.ProgContext ctx) {
        System.out.println(LLVMGenerator.generate());
    }
}
