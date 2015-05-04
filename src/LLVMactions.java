import org.antlr.v4.runtime.ParserRuleContext;
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

    @Override
    public void exitAssign(@NotNull GramatykaParser.AssignContext ctx) {

    }

    @Override
    public void exitStat(@NotNull GramatykaParser.StatContext ctx) {
        super.exitStat(ctx);
    }

    @Override
    public void exitSingle0(@NotNull GramatykaParser.Single0Context ctx) {
        super.exitSingle0(ctx);
    }

    @Override
    public void exitAdd(@NotNull GramatykaParser.AddContext ctx) {
        super.exitAdd(ctx);
    }

    @Override
    public void exitSingle1(@NotNull GramatykaParser.Single1Context ctx) {
        super.exitSingle1(ctx);
    }

    @Override
    public void exitMult(@NotNull GramatykaParser.MultContext ctx) {
        super.exitMult(ctx);
    }

    @Override
    public void exitValuue(@NotNull GramatykaParser.ValuueContext ctx) {
        super.exitValuue(ctx);
    }

    @Override
    public void exitPar(@NotNull GramatykaParser.ParContext ctx) {
        super.exitPar(ctx);
    }

    @Override
    public void exitValue(@NotNull GramatykaParser.ValueContext ctx) {
        super.exitValue(ctx);
    }

    @Override
    public void exitFunction(@NotNull GramatykaParser.FunctionContext ctx) {
        super.exitFunction(ctx);
    }

    @Override
    public void exitCall(@NotNull GramatykaParser.CallContext ctx) {
        super.exitCall(ctx);
    }

    @Override
    public void exitArgs(@NotNull GramatykaParser.ArgsContext ctx) {
        super.exitArgs(ctx);
    }

    @Override
    public void exitRestvalue(@NotNull GramatykaParser.RestvalueContext ctx) {
        super.exitRestvalue(ctx);
    }

    @Override
    public void exitPrint(@NotNull GramatykaParser.PrintContext ctx) {
        super.exitPrint(ctx);
    }

    @Override
    public void exitExpression(@NotNull GramatykaParser.ExpressionContext ctx) {
        super.exitExpression(ctx);
    }

    @Override
    public void exitRead(@NotNull GramatykaParser.ReadContext ctx) {
        super.exitRead(ctx);
    }

    @Override
    public void exitLop(@NotNull GramatykaParser.LopContext ctx) {
        super.exitLop(ctx);
    }

    @Override
    public void exitIff(@NotNull GramatykaParser.IffContext ctx) {
        super.exitIff(ctx);
    }

    @Override
    public void exitCond(@NotNull GramatykaParser.CondContext ctx) {
        super.exitCond(ctx);
    }

    @Override
    public void exitLt(@NotNull GramatykaParser.LtContext ctx) {
        super.exitLt(ctx);
    }

    @Override
    public void exitGt(@NotNull GramatykaParser.GtContext ctx) {
        super.exitGt(ctx);
    }

    @Override
    public void exitEq(@NotNull GramatykaParser.EqContext ctx) {
        super.exitEq(ctx);
    }

    @Override
    public void exitLoop(@NotNull GramatykaParser.LoopContext ctx) {
        super.exitLoop(ctx);
    }

    @Override
    public void exitEveryRule(@NotNull ParserRuleContext ctx) {
        super.exitEveryRule(ctx);
    }
}
