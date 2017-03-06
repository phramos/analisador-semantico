// Generated from /home/fauno/Downloads/Compiladores/TP3/TP3_AnaliseSemantica/gramatica/CMinus.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CMinusLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, INT=3, FLOAT=4, CHAR=5, STRUCT=6, IF=7, ELSE=8, WHILE=9, 
		VOID=10, RETURN=11, IntegerLiteral=12, FloatingPointLiteral=13, CharacterLiteral=14, 
		StringLiteral=15, LPAREN=16, RPAREN=17, LBRACE=18, RBRACE=19, LBRACK=20, 
		RBRACK=21, SEMI=22, ASSIGN=23, LT=24, EQUAL=25, LE=26, GE=27, NOTEQUAL=28, 
		ADD=29, SUB=30, MUL=31, DIV=32, Identifier=33, WS=34, COMMENT=35;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "INT", "FLOAT", "CHAR", "STRUCT", "IF", "ELSE", "WHILE", 
		"VOID", "RETURN", "IntegerLiteral", "DecimalIntegerLiteral", "DecimalNumeral", 
		"Digits", "Digit", "NonZeroDigit", "FloatingPointLiteral", "DecimalFloatingPointLiteral", 
		"ExponentPart", "ExponentIndicator", "SignedInteger", "Sign", "CharacterLiteral", 
		"SingleCharacter", "StringLiteral", "StringCharacters", "StringCharacter", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "ASSIGN", 
		"LT", "EQUAL", "LE", "GE", "NOTEQUAL", "ADD", "SUB", "MUL", "DIV", "Identifier", 
		"Letter", "WS", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'>'", "'int'", "'float'", "'char'", "'struct'", "'if'", 
		"'else'", "'while'", "'void'", "'return'", null, null, null, null, "'('", 
		"')'", "'{'", "'}'", "'['", "']'", "';'", "'='", "'<'", "'=='", "'<='", 
		"'>='", "'!='", "'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, "INT", "FLOAT", "CHAR", "STRUCT", "IF", "ELSE", "WHILE", 
		"VOID", "RETURN", "IntegerLiteral", "FloatingPointLiteral", "CharacterLiteral", 
		"StringLiteral", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
		"SEMI", "ASSIGN", "LT", "EQUAL", "LE", "GE", "NOTEQUAL", "ADD", "SUB", 
		"MUL", "DIV", "Identifier", "WS", "COMMENT"
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

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public CMinusLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CMinus.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2%\u0123\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\6"+
		"\16\u009d\n\16\r\16\16\16\u009e\3\17\3\17\5\17\u00a3\n\17\3\20\6\20\u00a6"+
		"\n\20\r\20\16\20\u00a7\3\21\3\21\5\21\u00ac\n\21\3\22\3\22\3\23\3\23\3"+
		"\24\3\24\3\24\5\24\u00b5\n\24\3\24\5\24\u00b8\n\24\3\24\3\24\3\24\3\24"+
		"\5\24\u00be\n\24\3\25\3\25\3\25\3\26\3\26\3\27\5\27\u00c6\n\27\3\27\3"+
		"\27\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\33\3\33\5\33\u00d4\n\33"+
		"\3\33\3\33\3\34\6\34\u00d9\n\34\r\34\16\34\u00da\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3\'\3(\3"+
		"(\3(\3)\3)\3)\3*\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3/\3/\3/\7/\u0108\n/\f"+
		"/\16/\u010b\13/\3\60\3\60\3\61\6\61\u0110\n\61\r\61\16\61\u0111\3\61\3"+
		"\61\3\62\3\62\3\62\3\62\7\62\u011a\n\62\f\62\16\62\u011d\13\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\u011b\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\2\35\2\37\2!\2#\2%\17\'\2)\2+\2-\2/\2\61\20\63"+
		"\2\65\21\67\29\2;\22=\23?\24A\25C\26E\27G\30I\31K\32M\33O\34Q\35S\36U"+
		"\37W Y![\"]#_\2a$c%\3\2\t\3\2\63;\3\2GG\4\2--//\4\2))^^\4\2$$^^\3\2c|"+
		"\5\2\13\f\16\17\"\"\u0123\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2%\3\2\2\2\2\61\3\2\2\2\2\65\3\2"+
		"\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2"+
		"\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S"+
		"\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\3e\3\2\2\2\5g\3\2\2\2\7i\3\2\2\2\tm\3\2\2\2\13s\3\2\2"+
		"\2\rx\3\2\2\2\17\177\3\2\2\2\21\u0082\3\2\2\2\23\u0087\3\2\2\2\25\u008d"+
		"\3\2\2\2\27\u0092\3\2\2\2\31\u0099\3\2\2\2\33\u009c\3\2\2\2\35\u00a2\3"+
		"\2\2\2\37\u00a5\3\2\2\2!\u00ab\3\2\2\2#\u00ad\3\2\2\2%\u00af\3\2\2\2\'"+
		"\u00bd\3\2\2\2)\u00bf\3\2\2\2+\u00c2\3\2\2\2-\u00c5\3\2\2\2/\u00c9\3\2"+
		"\2\2\61\u00cb\3\2\2\2\63\u00cf\3\2\2\2\65\u00d1\3\2\2\2\67\u00d8\3\2\2"+
		"\29\u00dc\3\2\2\2;\u00de\3\2\2\2=\u00e0\3\2\2\2?\u00e2\3\2\2\2A\u00e4"+
		"\3\2\2\2C\u00e6\3\2\2\2E\u00e8\3\2\2\2G\u00ea\3\2\2\2I\u00ec\3\2\2\2K"+
		"\u00ee\3\2\2\2M\u00f0\3\2\2\2O\u00f3\3\2\2\2Q\u00f6\3\2\2\2S\u00f9\3\2"+
		"\2\2U\u00fc\3\2\2\2W\u00fe\3\2\2\2Y\u0100\3\2\2\2[\u0102\3\2\2\2]\u0104"+
		"\3\2\2\2_\u010c\3\2\2\2a\u010f\3\2\2\2c\u0115\3\2\2\2ef\7.\2\2f\4\3\2"+
		"\2\2gh\7@\2\2h\6\3\2\2\2ij\7k\2\2jk\7p\2\2kl\7v\2\2l\b\3\2\2\2mn\7h\2"+
		"\2no\7n\2\2op\7q\2\2pq\7c\2\2qr\7v\2\2r\n\3\2\2\2st\7e\2\2tu\7j\2\2uv"+
		"\7c\2\2vw\7t\2\2w\f\3\2\2\2xy\7u\2\2yz\7v\2\2z{\7t\2\2{|\7w\2\2|}\7e\2"+
		"\2}~\7v\2\2~\16\3\2\2\2\177\u0080\7k\2\2\u0080\u0081\7h\2\2\u0081\20\3"+
		"\2\2\2\u0082\u0083\7g\2\2\u0083\u0084\7n\2\2\u0084\u0085\7u\2\2\u0085"+
		"\u0086\7g\2\2\u0086\22\3\2\2\2\u0087\u0088\7y\2\2\u0088\u0089\7j\2\2\u0089"+
		"\u008a\7k\2\2\u008a\u008b\7n\2\2\u008b\u008c\7g\2\2\u008c\24\3\2\2\2\u008d"+
		"\u008e\7x\2\2\u008e\u008f\7q\2\2\u008f\u0090\7k\2\2\u0090\u0091\7f\2\2"+
		"\u0091\26\3\2\2\2\u0092\u0093\7t\2\2\u0093\u0094\7g\2\2\u0094\u0095\7"+
		"v\2\2\u0095\u0096\7w\2\2\u0096\u0097\7t\2\2\u0097\u0098\7p\2\2\u0098\30"+
		"\3\2\2\2\u0099\u009a\5\33\16\2\u009a\32\3\2\2\2\u009b\u009d\5\35\17\2"+
		"\u009c\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f"+
		"\3\2\2\2\u009f\34\3\2\2\2\u00a0\u00a3\7\62\2\2\u00a1\u00a3\5#\22\2\u00a2"+
		"\u00a0\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\36\3\2\2\2\u00a4\u00a6\5!\21"+
		"\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8 \3\2\2\2\u00a9\u00ac\7\62\2\2\u00aa\u00ac\5#\22\2\u00ab"+
		"\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\"\3\2\2\2\u00ad\u00ae\t\2\2\2"+
		"\u00ae$\3\2\2\2\u00af\u00b0\5\'\24\2\u00b0&\3\2\2\2\u00b1\u00b2\5\37\20"+
		"\2\u00b2\u00b4\7\60\2\2\u00b3\u00b5\5\37\20\2\u00b4\u00b3\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b8\5)\25\2\u00b7\u00b6\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00be\3\2\2\2\u00b9\u00ba\5\37\20\2\u00ba"+
		"\u00bb\5)\25\2\u00bb\u00be\3\2\2\2\u00bc\u00be\5\37\20\2\u00bd\u00b1\3"+
		"\2\2\2\u00bd\u00b9\3\2\2\2\u00bd\u00bc\3\2\2\2\u00be(\3\2\2\2\u00bf\u00c0"+
		"\5+\26\2\u00c0\u00c1\5-\27\2\u00c1*\3\2\2\2\u00c2\u00c3\t\3\2\2\u00c3"+
		",\3\2\2\2\u00c4\u00c6\5/\30\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2"+
		"\u00c6\u00c7\3\2\2\2\u00c7\u00c8\5\37\20\2\u00c8.\3\2\2\2\u00c9\u00ca"+
		"\t\4\2\2\u00ca\60\3\2\2\2\u00cb\u00cc\7)\2\2\u00cc\u00cd\5\63\32\2\u00cd"+
		"\u00ce\7)\2\2\u00ce\62\3\2\2\2\u00cf\u00d0\n\5\2\2\u00d0\64\3\2\2\2\u00d1"+
		"\u00d3\7$\2\2\u00d2\u00d4\5\67\34\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3"+
		"\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d6\7$\2\2\u00d6\66\3\2\2\2\u00d7\u00d9"+
		"\59\35\2\u00d8\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00d8\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db8\3\2\2\2\u00dc\u00dd\n\6\2\2\u00dd:\3\2\2\2\u00de"+
		"\u00df\7*\2\2\u00df<\3\2\2\2\u00e0\u00e1\7+\2\2\u00e1>\3\2\2\2\u00e2\u00e3"+
		"\7}\2\2\u00e3@\3\2\2\2\u00e4\u00e5\7\177\2\2\u00e5B\3\2\2\2\u00e6\u00e7"+
		"\7]\2\2\u00e7D\3\2\2\2\u00e8\u00e9\7_\2\2\u00e9F\3\2\2\2\u00ea\u00eb\7"+
		"=\2\2\u00ebH\3\2\2\2\u00ec\u00ed\7?\2\2\u00edJ\3\2\2\2\u00ee\u00ef\7>"+
		"\2\2\u00efL\3\2\2\2\u00f0\u00f1\7?\2\2\u00f1\u00f2\7?\2\2\u00f2N\3\2\2"+
		"\2\u00f3\u00f4\7>\2\2\u00f4\u00f5\7?\2\2\u00f5P\3\2\2\2\u00f6\u00f7\7"+
		"@\2\2\u00f7\u00f8\7?\2\2\u00f8R\3\2\2\2\u00f9\u00fa\7#\2\2\u00fa\u00fb"+
		"\7?\2\2\u00fbT\3\2\2\2\u00fc\u00fd\7-\2\2\u00fdV\3\2\2\2\u00fe\u00ff\7"+
		"/\2\2\u00ffX\3\2\2\2\u0100\u0101\7,\2\2\u0101Z\3\2\2\2\u0102\u0103\7\61"+
		"\2\2\u0103\\\3\2\2\2\u0104\u0109\5_\60\2\u0105\u0108\5_\60\2\u0106\u0108"+
		"\5!\21\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108\u010b\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a^\3\2\2\2\u010b\u0109\3\2\2\2"+
		"\u010c\u010d\t\7\2\2\u010d`\3\2\2\2\u010e\u0110\t\b\2\2\u010f\u010e\3"+
		"\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0114\b\61\2\2\u0114b\3\2\2\2\u0115\u0116\7\61\2"+
		"\2\u0116\u0117\7,\2\2\u0117\u011b\3\2\2\2\u0118\u011a\13\2\2\2\u0119\u0118"+
		"\3\2\2\2\u011a\u011d\3\2\2\2\u011b\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c"+
		"\u011e\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u011f\7,\2\2\u011f\u0120\7\61"+
		"\2\2\u0120\u0121\3\2\2\2\u0121\u0122\b\62\2\2\u0122d\3\2\2\2\21\2\u009e"+
		"\u00a2\u00a7\u00ab\u00b4\u00b7\u00bd\u00c5\u00d3\u00da\u0107\u0109\u0111"+
		"\u011b\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}