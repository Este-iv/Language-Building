// Generated from varlang/parser/VarLang.g by ANTLR 4.5
package varlang.parser; import static varlang.AST.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class VarLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, Lete=7, Dec=8, Define=9, 
		Let=10, Dot=11, Number=12, Identifier=13, Letter=14, LetterOrDigit=15, 
		AT=16, ELLIPSIS=17, WS=18, Comment=19, Line_Comment=20;
	public static final int
		RULE_program = 0, RULE_exp = 1, RULE_varexp = 2, RULE_letexp = 3, RULE_leteexp = 4, 
		RULE_decexp = 5, RULE_definedecl = 6, RULE_numexp = 7, RULE_addexp = 8, 
		RULE_subexp = 9, RULE_multexp = 10, RULE_divexp = 11;
	public static final String[] ruleNames = {
		"program", "exp", "varexp", "letexp", "leteexp", "decexp", "definedecl", 
		"numexp", "addexp", "subexp", "multexp", "divexp"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'-'", "'+'", "'*'", "'/'", "'lete'", "'dec'", "'define'", 
		"'let'", "'.'", null, null, null, null, "'@'", "'...'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "Lete", "Dec", "Define", "Let", 
		"Dot", "Number", "Identifier", "Letter", "LetterOrDigit", "AT", "ELLIPSIS", 
		"WS", "Comment", "Line_Comment"
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

	@Override
	public String getGrammarFileName() { return "VarLang.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public VarLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Program ast;
		public ArrayList<DefineDecl> defs;
		public Exp expr;
		public DefinedeclContext def;
		public ExpContext e;
		public List<DefinedeclContext> definedecl() {
			return getRuleContexts(DefinedeclContext.class);
		}
		public DefinedeclContext definedecl(int i) {
			return getRuleContext(DefinedeclContext.class,i);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		 ((ProgramContext)_localctx).defs =  new ArrayList<DefineDecl>(); ((ProgramContext)_localctx).expr =  new UnitExp(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(24);
					((ProgramContext)_localctx).def = definedecl();
					 _localctx.defs.add(((ProgramContext)_localctx).def.ast); 
					}
					} 
				}
				setState(31);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(35);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << Number) | (1L << Identifier))) != 0)) {
				{
				setState(32);
				((ProgramContext)_localctx).e = exp();
				 ((ProgramContext)_localctx).expr =  ((ProgramContext)_localctx).e.ast; 
				}
			}

			 ((ProgramContext)_localctx).ast =  new Program(_localctx.defs, _localctx.expr); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Exp ast;
		public VarexpContext v;
		public NumexpContext n;
		public AddexpContext a;
		public SubexpContext s;
		public MultexpContext m;
		public DivexpContext d;
		public LetexpContext l;
		public LeteexpContext le;
		public DecexpContext de;
		public VarexpContext varexp() {
			return getRuleContext(VarexpContext.class,0);
		}
		public NumexpContext numexp() {
			return getRuleContext(NumexpContext.class,0);
		}
		public AddexpContext addexp() {
			return getRuleContext(AddexpContext.class,0);
		}
		public SubexpContext subexp() {
			return getRuleContext(SubexpContext.class,0);
		}
		public MultexpContext multexp() {
			return getRuleContext(MultexpContext.class,0);
		}
		public DivexpContext divexp() {
			return getRuleContext(DivexpContext.class,0);
		}
		public LetexpContext letexp() {
			return getRuleContext(LetexpContext.class,0);
		}
		public LeteexpContext leteexp() {
			return getRuleContext(LeteexpContext.class,0);
		}
		public DecexpContext decexp() {
			return getRuleContext(DecexpContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exp);
		try {
			setState(66);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				((ExpContext)_localctx).v = varexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).v.ast; 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(42);
				((ExpContext)_localctx).n = numexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).n.ast; 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(45);
				((ExpContext)_localctx).a = addexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).a.ast; 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				((ExpContext)_localctx).s = subexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).s.ast; 
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(51);
				((ExpContext)_localctx).m = multexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).m.ast; 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(54);
				((ExpContext)_localctx).d = divexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).d.ast; 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(57);
				((ExpContext)_localctx).l = letexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).l.ast; 
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(60);
				((ExpContext)_localctx).le = leteexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).le.ast; 
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(63);
				((ExpContext)_localctx).de = decexp();
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).de.ast; 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarexpContext extends ParserRuleContext {
		public VarExp ast;
		public Token id;
		public TerminalNode Identifier() { return getToken(VarLangParser.Identifier, 0); }
		public VarexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varexp; }
	}

	public final VarexpContext varexp() throws RecognitionException {
		VarexpContext _localctx = new VarexpContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((VarexpContext)_localctx).id = match(Identifier);
			 ((VarexpContext)_localctx).ast =  new VarExp((((VarexpContext)_localctx).id!=null?((VarexpContext)_localctx).id.getText():null)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetexpContext extends ParserRuleContext {
		public LetExp ast;
		public ArrayList<String> names =  new ArrayList<String>();
		public ArrayList<Exp> value_exps =  new ArrayList<Exp>();
		public Token id;
		public ExpContext e;
		public ExpContext body;
		public TerminalNode Let() { return getToken(VarLangParser.Let, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(VarLangParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(VarLangParser.Identifier, i);
		}
		public LetexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letexp; }
	}

	public final LetexpContext letexp() throws RecognitionException {
		LetexpContext _localctx = new LetexpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_letexp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__0);
			setState(72);
			match(Let);
			setState(73);
			match(T__0);
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(74);
				match(T__0);
				setState(75);
				((LetexpContext)_localctx).id = match(Identifier);
				setState(76);
				((LetexpContext)_localctx).e = exp();
				setState(77);
				match(T__1);
				 _localctx.names.add((((LetexpContext)_localctx).id!=null?((LetexpContext)_localctx).id.getText():null)); _localctx.value_exps.add(((LetexpContext)_localctx).e.ast); 
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(84);
			match(T__1);
			setState(85);
			((LetexpContext)_localctx).body = exp();
			setState(86);
			match(T__1);
			 ((LetexpContext)_localctx).ast =  new LetExp(_localctx.names, _localctx.value_exps, ((LetexpContext)_localctx).body.ast); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeteexpContext extends ParserRuleContext {
		public LeteExp ast;
		public ArrayList<String> names =  new ArrayList<String>();
		public ArrayList<Exp> value_exps =  new ArrayList<Exp>();
		public NumexpContext key;
		public Token id;
		public ExpContext e;
		public ExpContext body;
		public TerminalNode Lete() { return getToken(VarLangParser.Lete, 0); }
		public NumexpContext numexp() {
			return getRuleContext(NumexpContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> Identifier() { return getTokens(VarLangParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(VarLangParser.Identifier, i);
		}
		public LeteexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leteexp; }
	}

	public final LeteexpContext leteexp() throws RecognitionException {
		LeteexpContext _localctx = new LeteexpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_leteexp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(T__0);
			setState(90);
			match(Lete);
			setState(91);
			((LeteexpContext)_localctx).key = numexp();
			setState(92);
			match(T__0);
			setState(99); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(93);
				match(T__0);
				setState(94);
				((LeteexpContext)_localctx).id = match(Identifier);
				setState(95);
				((LeteexpContext)_localctx).e = exp();
				setState(96);
				match(T__1);
				_localctx.names.add((((LeteexpContext)_localctx).id!=null?((LeteexpContext)_localctx).id.getText():null)); _localctx.value_exps.add(((LeteexpContext)_localctx).e.ast); 
				}
				}
				setState(101); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__0 );
			setState(103);
			match(T__1);
			setState(104);
			((LeteexpContext)_localctx).body = exp();
			setState(105);
			match(T__1);
			 ((LeteexpContext)_localctx).ast =  new LeteExp(_localctx.names, _localctx.value_exps, ((LeteexpContext)_localctx).body.ast, ((LeteexpContext)_localctx).key.ast); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecexpContext extends ParserRuleContext {
		public DecExp ast;
		public NumexpContext key;
		public Token id;
		public TerminalNode Dec() { return getToken(VarLangParser.Dec, 0); }
		public NumexpContext numexp() {
			return getRuleContext(NumexpContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(VarLangParser.Identifier, 0); }
		public DecexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decexp; }
	}

	public final DecexpContext decexp() throws RecognitionException {
		DecexpContext _localctx = new DecexpContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_decexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(T__0);
			setState(109);
			match(Dec);
			setState(110);
			((DecexpContext)_localctx).key = numexp();
			setState(111);
			((DecexpContext)_localctx).id = match(Identifier);
			setState(112);
			match(T__1);
			((DecexpContext)_localctx).ast =  new DecExp(((DecexpContext)_localctx).key.ast, (((DecexpContext)_localctx).id!=null?((DecexpContext)_localctx).id.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefinedeclContext extends ParserRuleContext {
		public DefineDecl ast;
		public Token id;
		public ExpContext e;
		public TerminalNode Define() { return getToken(VarLangParser.Define, 0); }
		public TerminalNode Identifier() { return getToken(VarLangParser.Identifier, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public DefinedeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definedecl; }
	}

	public final DefinedeclContext definedecl() throws RecognitionException {
		DefinedeclContext _localctx = new DefinedeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_definedecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__0);
			setState(116);
			match(Define);
			setState(117);
			((DefinedeclContext)_localctx).id = match(Identifier);
			setState(118);
			((DefinedeclContext)_localctx).e = exp();
			setState(119);
			match(T__1);
			 ((DefinedeclContext)_localctx).ast =  new DefineDecl((((DefinedeclContext)_localctx).id!=null?((DefinedeclContext)_localctx).id.getText():null), ((DefinedeclContext)_localctx).e.ast); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumexpContext extends ParserRuleContext {
		public NumExp ast;
		public Token n0;
		public Token n1;
		public List<TerminalNode> Number() { return getTokens(VarLangParser.Number); }
		public TerminalNode Number(int i) {
			return getToken(VarLangParser.Number, i);
		}
		public TerminalNode Dot() { return getToken(VarLangParser.Dot, 0); }
		public NumexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numexp; }
	}

	public final NumexpContext numexp() throws RecognitionException {
		NumexpContext _localctx = new NumexpContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_numexp);
		try {
			setState(136);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				((NumexpContext)_localctx).n0 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Integer.parseInt((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null))); 
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				match(T__2);
				setState(125);
				((NumexpContext)_localctx).n0 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(-Integer.parseInt((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null))); 
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				((NumexpContext)_localctx).n0 = match(Number);
				setState(128);
				match(Dot);
				setState(129);
				((NumexpContext)_localctx).n1 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Double.parseDouble((((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null)+"."+(((NumexpContext)_localctx).n1!=null?((NumexpContext)_localctx).n1.getText():null))); 
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				match(T__2);
				setState(132);
				((NumexpContext)_localctx).n0 = match(Number);
				setState(133);
				match(Dot);
				setState(134);
				((NumexpContext)_localctx).n1 = match(Number);
				 ((NumexpContext)_localctx).ast =  new NumExp(Double.parseDouble("-" + (((NumexpContext)_localctx).n0!=null?((NumexpContext)_localctx).n0.getText():null)+"."+(((NumexpContext)_localctx).n1!=null?((NumexpContext)_localctx).n1.getText():null))); 
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AddexpContext extends ParserRuleContext {
		public AddExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public AddexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addexp; }
	}

	public final AddexpContext addexp() throws RecognitionException {
		AddexpContext _localctx = new AddexpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_addexp);
		 ((AddexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(T__0);
			setState(139);
			match(T__3);
			setState(140);
			((AddexpContext)_localctx).e = exp();
			 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
			setState(145); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				((AddexpContext)_localctx).e = exp();
				 _localctx.list.add(((AddexpContext)_localctx).e.ast); 
				}
				}
				setState(147); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(149);
			match(T__1);
			 ((AddexpContext)_localctx).ast =  new AddExp(_localctx.list); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SubexpContext extends ParserRuleContext {
		public SubExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public SubexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subexp; }
	}

	public final SubexpContext subexp() throws RecognitionException {
		SubexpContext _localctx = new SubexpContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_subexp);
		 ((SubexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__0);
			setState(153);
			match(T__2);
			setState(154);
			((SubexpContext)_localctx).e = exp();
			 _localctx.list.add(((SubexpContext)_localctx).e.ast); 
			setState(159); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(156);
				((SubexpContext)_localctx).e = exp();
				 _localctx.list.add(((SubexpContext)_localctx).e.ast); 
				}
				}
				setState(161); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(163);
			match(T__1);
			 ((SubexpContext)_localctx).ast =  new SubExp(_localctx.list); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultexpContext extends ParserRuleContext {
		public MultExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public MultexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multexp; }
	}

	public final MultexpContext multexp() throws RecognitionException {
		MultexpContext _localctx = new MultexpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_multexp);
		 ((MultexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(T__0);
			setState(167);
			match(T__4);
			setState(168);
			((MultexpContext)_localctx).e = exp();
			 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
			setState(173); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(170);
				((MultexpContext)_localctx).e = exp();
				 _localctx.list.add(((MultexpContext)_localctx).e.ast); 
				}
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(177);
			match(T__1);
			 ((MultexpContext)_localctx).ast =  new MultExp(_localctx.list); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DivexpContext extends ParserRuleContext {
		public DivExp ast;
		public ArrayList<Exp> list;
		public ExpContext e;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public DivexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_divexp; }
	}

	public final DivexpContext divexp() throws RecognitionException {
		DivexpContext _localctx = new DivexpContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_divexp);
		 ((DivexpContext)_localctx).list =  new ArrayList<Exp>(); 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__0);
			setState(181);
			match(T__5);
			setState(182);
			((DivexpContext)_localctx).e = exp();
			 _localctx.list.add(((DivexpContext)_localctx).e.ast); 
			setState(187); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(184);
				((DivexpContext)_localctx).e = exp();
				 _localctx.list.add(((DivexpContext)_localctx).e.ast); 
				}
				}
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << Number) | (1L << Identifier))) != 0) );
			setState(191);
			match(T__1);
			 ((DivexpContext)_localctx).ast =  new DivExp(_localctx.list); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\26\u00c5\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\3\2\3"+
		"\2\5\2&\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3E\n\3\3"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5S\n\5\r\5\16\5T\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6f\n\6\r\6"+
		"\16\6g\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\t\u008b\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0094\n\n\r\n\16\n\u0095"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\6\13\u00a2\n\13\r\13\16"+
		"\13\u00a3\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00b0\n\f\r\f"+
		"\16\f\u00b1\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\6\r\u00be\n\r\r\r"+
		"\16\r\u00bf\3\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\u00cb"+
		"\2\37\3\2\2\2\4D\3\2\2\2\6F\3\2\2\2\bI\3\2\2\2\n[\3\2\2\2\fn\3\2\2\2\16"+
		"u\3\2\2\2\20\u008a\3\2\2\2\22\u008c\3\2\2\2\24\u009a\3\2\2\2\26\u00a8"+
		"\3\2\2\2\30\u00b6\3\2\2\2\32\33\5\16\b\2\33\34\b\2\1\2\34\36\3\2\2\2\35"+
		"\32\3\2\2\2\36!\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 %\3\2\2\2!\37\3\2\2"+
		"\2\"#\5\4\3\2#$\b\2\1\2$&\3\2\2\2%\"\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\b"+
		"\2\1\2(\3\3\2\2\2)*\5\6\4\2*+\b\3\1\2+E\3\2\2\2,-\5\20\t\2-.\b\3\1\2."+
		"E\3\2\2\2/\60\5\22\n\2\60\61\b\3\1\2\61E\3\2\2\2\62\63\5\24\13\2\63\64"+
		"\b\3\1\2\64E\3\2\2\2\65\66\5\26\f\2\66\67\b\3\1\2\67E\3\2\2\289\5\30\r"+
		"\29:\b\3\1\2:E\3\2\2\2;<\5\b\5\2<=\b\3\1\2=E\3\2\2\2>?\5\n\6\2?@\b\3\1"+
		"\2@E\3\2\2\2AB\5\f\7\2BC\b\3\1\2CE\3\2\2\2D)\3\2\2\2D,\3\2\2\2D/\3\2\2"+
		"\2D\62\3\2\2\2D\65\3\2\2\2D8\3\2\2\2D;\3\2\2\2D>\3\2\2\2DA\3\2\2\2E\5"+
		"\3\2\2\2FG\7\17\2\2GH\b\4\1\2H\7\3\2\2\2IJ\7\3\2\2JK\7\f\2\2KR\7\3\2\2"+
		"LM\7\3\2\2MN\7\17\2\2NO\5\4\3\2OP\7\4\2\2PQ\b\5\1\2QS\3\2\2\2RL\3\2\2"+
		"\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2UV\3\2\2\2VW\7\4\2\2WX\5\4\3\2XY\7\4\2"+
		"\2YZ\b\5\1\2Z\t\3\2\2\2[\\\7\3\2\2\\]\7\t\2\2]^\5\20\t\2^e\7\3\2\2_`\7"+
		"\3\2\2`a\7\17\2\2ab\5\4\3\2bc\7\4\2\2cd\b\6\1\2df\3\2\2\2e_\3\2\2\2fg"+
		"\3\2\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\7\4\2\2jk\5\4\3\2kl\7\4\2\2l"+
		"m\b\6\1\2m\13\3\2\2\2no\7\3\2\2op\7\n\2\2pq\5\20\t\2qr\7\17\2\2rs\7\4"+
		"\2\2st\b\7\1\2t\r\3\2\2\2uv\7\3\2\2vw\7\13\2\2wx\7\17\2\2xy\5\4\3\2yz"+
		"\7\4\2\2z{\b\b\1\2{\17\3\2\2\2|}\7\16\2\2}\u008b\b\t\1\2~\177\7\5\2\2"+
		"\177\u0080\7\16\2\2\u0080\u008b\b\t\1\2\u0081\u0082\7\16\2\2\u0082\u0083"+
		"\7\r\2\2\u0083\u0084\7\16\2\2\u0084\u008b\b\t\1\2\u0085\u0086\7\5\2\2"+
		"\u0086\u0087\7\16\2\2\u0087\u0088\7\r\2\2\u0088\u0089\7\16\2\2\u0089\u008b"+
		"\b\t\1\2\u008a|\3\2\2\2\u008a~\3\2\2\2\u008a\u0081\3\2\2\2\u008a\u0085"+
		"\3\2\2\2\u008b\21\3\2\2\2\u008c\u008d\7\3\2\2\u008d\u008e\7\6\2\2\u008e"+
		"\u008f\5\4\3\2\u008f\u0093\b\n\1\2\u0090\u0091\5\4\3\2\u0091\u0092\b\n"+
		"\1\2\u0092\u0094\3\2\2\2\u0093\u0090\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\7\4"+
		"\2\2\u0098\u0099\b\n\1\2\u0099\23\3\2\2\2\u009a\u009b\7\3\2\2\u009b\u009c"+
		"\7\5\2\2\u009c\u009d\5\4\3\2\u009d\u00a1\b\13\1\2\u009e\u009f\5\4\3\2"+
		"\u009f\u00a0\b\13\1\2\u00a0\u00a2\3\2\2\2\u00a1\u009e\3\2\2\2\u00a2\u00a3"+
		"\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a6\7\4\2\2\u00a6\u00a7\b\13\1\2\u00a7\25\3\2\2\2\u00a8\u00a9\7\3\2"+
		"\2\u00a9\u00aa\7\7\2\2\u00aa\u00ab\5\4\3\2\u00ab\u00af\b\f\1\2\u00ac\u00ad"+
		"\5\4\3\2\u00ad\u00ae\b\f\1\2\u00ae\u00b0\3\2\2\2\u00af\u00ac\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2"+
		"\2\2\u00b3\u00b4\7\4\2\2\u00b4\u00b5\b\f\1\2\u00b5\27\3\2\2\2\u00b6\u00b7"+
		"\7\3\2\2\u00b7\u00b8\7\b\2\2\u00b8\u00b9\5\4\3\2\u00b9\u00bd\b\r\1\2\u00ba"+
		"\u00bb\5\4\3\2\u00bb\u00bc\b\r\1\2\u00bc\u00be\3\2\2\2\u00bd\u00ba\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c2\7\4\2\2\u00c2\u00c3\b\r\1\2\u00c3\31\3\2\2"+
		"\2\f\37%DTg\u008a\u0095\u00a3\u00b1\u00bf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}