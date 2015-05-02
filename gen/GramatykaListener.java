// Generated from /home/loku/IdeaProjects/Testowy/src/Gramatyka.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramatykaParser}.
 */
public interface GramatykaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(@NotNull GramatykaParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(@NotNull GramatykaParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(@NotNull GramatykaParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(@NotNull GramatykaParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single0}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 */
	void enterSingle0(@NotNull GramatykaParser.Single0Context ctx);
	/**
	 * Exit a parse tree produced by the {@code single0}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 */
	void exitSingle0(@NotNull GramatykaParser.Single0Context ctx);
	/**
	 * Enter a parse tree produced by the {@code add}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 */
	void enterAdd(@NotNull GramatykaParser.AddContext ctx);
	/**
	 * Exit a parse tree produced by the {@code add}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 */
	void exitAdd(@NotNull GramatykaParser.AddContext ctx);
	/**
	 * Enter a parse tree produced by the {@code single1}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterSingle1(@NotNull GramatykaParser.Single1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code single1}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitSingle1(@NotNull GramatykaParser.Single1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code mult}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void enterMult(@NotNull GramatykaParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 */
	void exitMult(@NotNull GramatykaParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valuue}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterValuue(@NotNull GramatykaParser.ValuueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valuue}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitValuue(@NotNull GramatykaParser.ValuueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code par}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 */
	void enterPar(@NotNull GramatykaParser.ParContext ctx);
	/**
	 * Exit a parse tree produced by the {@code par}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 */
	void exitPar(@NotNull GramatykaParser.ParContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull GramatykaParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull GramatykaParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull GramatykaParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull GramatykaParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(@NotNull GramatykaParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(@NotNull GramatykaParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(@NotNull GramatykaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(@NotNull GramatykaParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#restvalue}.
	 * @param ctx the parse tree
	 */
	void enterRestvalue(@NotNull GramatykaParser.RestvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#restvalue}.
	 * @param ctx the parse tree
	 */
	void exitRestvalue(@NotNull GramatykaParser.RestvalueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code print}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterPrint(@NotNull GramatykaParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code print}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitPrint(@NotNull GramatykaParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expression}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull GramatykaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull GramatykaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterAssign(@NotNull GramatykaParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitAssign(@NotNull GramatykaParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code read}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterRead(@NotNull GramatykaParser.ReadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code read}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitRead(@NotNull GramatykaParser.ReadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lop}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterLop(@NotNull GramatykaParser.LopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lop}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitLop(@NotNull GramatykaParser.LopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code iff}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void enterIff(@NotNull GramatykaParser.IffContext ctx);
	/**
	 * Exit a parse tree produced by the {@code iff}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 */
	void exitIff(@NotNull GramatykaParser.IffContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#if}.
	 * @param ctx the parse tree
	 */
	void enterIf(@NotNull GramatykaParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#if}.
	 * @param ctx the parse tree
	 */
	void exitIf(@NotNull GramatykaParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#lt}.
	 * @param ctx the parse tree
	 */
	void enterLt(@NotNull GramatykaParser.LtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#lt}.
	 * @param ctx the parse tree
	 */
	void exitLt(@NotNull GramatykaParser.LtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#gt}.
	 * @param ctx the parse tree
	 */
	void enterGt(@NotNull GramatykaParser.GtContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#gt}.
	 * @param ctx the parse tree
	 */
	void exitGt(@NotNull GramatykaParser.GtContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#eq}.
	 * @param ctx the parse tree
	 */
	void enterEq(@NotNull GramatykaParser.EqContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#eq}.
	 * @param ctx the parse tree
	 */
	void exitEq(@NotNull GramatykaParser.EqContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramatykaParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(@NotNull GramatykaParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramatykaParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(@NotNull GramatykaParser.LoopContext ctx);
}