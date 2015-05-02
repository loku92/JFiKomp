// Generated from /home/loku/IdeaProjects/Testowy/src/Gramatyka.g4 by ANTLR 4.5
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GramatykaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PRINT=9, 
		READ=10, ID=11, EQUALS=12, INT=13, REAL=14, ADD=15, MINUS=16, MULT=17, 
		DIV=18, NEWLINE=19, WS=20, OIF=21, CIF=22, OPEN=23, CLOSE=24;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "PRINT", 
		"READ", "ID", "EQUALS", "INT", "REAL", "ADD", "MINUS", "MULT", "DIV", 
		"NEWLINE", "WS", "OIF", "CIF", "OPEN", "CLOSE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'function'", "','", "'<'", "'>'", "'=='", "'loop('", 
		"'print'", "'read'", null, "'='", null, null, "'+'", "'-'", "'*'", "'/'", 
		null, null, "'<<'", "'>>'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "PRINT", "READ", 
		"ID", "EQUALS", "INT", "REAL", "ADD", "MINUS", "MULT", "DIV", "NEWLINE", 
		"WS", "OIF", "CIF", "OPEN", "CLOSE"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override
	@NotNull
	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public GramatykaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Gramatyka.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\32\u0093\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\6"+
		"\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\6\f\\\n\f\r\f\16\f]\3\r\3\r\3\16\6\16"+
		"c\n\16\r\16\16\16d\3\17\6\17h\n\17\r\17\16\17i\3\17\3\17\6\17n\n\17\r"+
		"\17\16\17o\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\5\24{\n\24\3\24"+
		"\3\24\3\25\3\25\5\25\u0081\n\25\3\25\6\25\u0084\n\25\r\25\16\25\u0085"+
		"\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3\31\2\2\32\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\3\2\4\4\2C\\c|\4\2\13\13\""+
		"\"\u009a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2"+
		"\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7\67\3\2\2\2\t@\3\2"+
		"\2\2\13B\3\2\2\2\rD\3\2\2\2\17F\3\2\2\2\21I\3\2\2\2\23O\3\2\2\2\25U\3"+
		"\2\2\2\27[\3\2\2\2\31_\3\2\2\2\33b\3\2\2\2\35g\3\2\2\2\37q\3\2\2\2!s\3"+
		"\2\2\2#u\3\2\2\2%w\3\2\2\2\'z\3\2\2\2)\u0083\3\2\2\2+\u0089\3\2\2\2-\u008c"+
		"\3\2\2\2/\u008f\3\2\2\2\61\u0091\3\2\2\2\63\64\7*\2\2\64\4\3\2\2\2\65"+
		"\66\7+\2\2\66\6\3\2\2\2\678\7h\2\289\7w\2\29:\7p\2\2:;\7e\2\2;<\7v\2\2"+
		"<=\7k\2\2=>\7q\2\2>?\7p\2\2?\b\3\2\2\2@A\7.\2\2A\n\3\2\2\2BC\7>\2\2C\f"+
		"\3\2\2\2DE\7@\2\2E\16\3\2\2\2FG\7?\2\2GH\7?\2\2H\20\3\2\2\2IJ\7n\2\2J"+
		"K\7q\2\2KL\7q\2\2LM\7r\2\2MN\7*\2\2N\22\3\2\2\2OP\7r\2\2PQ\7t\2\2QR\7"+
		"k\2\2RS\7p\2\2ST\7v\2\2T\24\3\2\2\2UV\7t\2\2VW\7g\2\2WX\7c\2\2XY\7f\2"+
		"\2Y\26\3\2\2\2Z\\\t\2\2\2[Z\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^\30"+
		"\3\2\2\2_`\7?\2\2`\32\3\2\2\2ac\4\62;\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2"+
		"de\3\2\2\2e\34\3\2\2\2fh\4\62;\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2"+
		"\2jk\3\2\2\2km\7\60\2\2ln\4\62;\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2"+
		"\2\2p\36\3\2\2\2qr\7-\2\2r \3\2\2\2st\7/\2\2t\"\3\2\2\2uv\7,\2\2v$\3\2"+
		"\2\2wx\7\61\2\2x&\3\2\2\2y{\7\17\2\2zy\3\2\2\2z{\3\2\2\2{|\3\2\2\2|}\7"+
		"p\2\2}(\3\2\2\2~\u0084\t\3\2\2\177\u0081\7\17\2\2\u0080\177\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\7\f\2\2\u0083~\3\2\2\2"+
		"\u0083\u0080\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086"+
		"\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\25\2\2\u0088*\3\2\2\2\u0089"+
		"\u008a\7>\2\2\u008a\u008b\7>\2\2\u008b,\3\2\2\2\u008c\u008d\7@\2\2\u008d"+
		"\u008e\7@\2\2\u008e.\3\2\2\2\u008f\u0090\7]\2\2\u0090\60\3\2\2\2\u0091"+
		"\u0092\7_\2\2\u0092\62\3\2\2\2\13\2]dioz\u0080\u0083\u0085\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}