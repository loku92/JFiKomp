// Generated from /home/loku/IdeaProjects/Testowy/src/Gramatyka.g4 by ANTLR 4.5
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GramatykaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GramatykaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(@NotNull GramatykaParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(@NotNull GramatykaParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single0}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle0(@NotNull GramatykaParser.Single0Context ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link GramatykaParser#expr0}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(@NotNull GramatykaParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code single1}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingle1(@NotNull GramatykaParser.Single1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code mult}
	 * labeled alternative in {@link GramatykaParser#expr1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(@NotNull GramatykaParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valuue}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValuue(@NotNull GramatykaParser.ValuueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code par}
	 * labeled alternative in {@link GramatykaParser#expr2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPar(@NotNull GramatykaParser.ParContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull GramatykaParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull GramatykaParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(@NotNull GramatykaParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(@NotNull GramatykaParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#restvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRestvalue(@NotNull GramatykaParser.RestvalueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code print}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(@NotNull GramatykaParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expression}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull GramatykaParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(@NotNull GramatykaParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code read}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(@NotNull GramatykaParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lop}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLop(@NotNull GramatykaParser.LopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code iff}
	 * labeled alternative in {@link GramatykaParser#inside}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIff(@NotNull GramatykaParser.IffContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(@NotNull GramatykaParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#lt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(@NotNull GramatykaParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#gt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGt(@NotNull GramatykaParser.GtContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#eq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq(@NotNull GramatykaParser.EqContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramatykaParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(@NotNull GramatykaParser.LoopContext ctx);
}