package funclang;
import static funclang.AST.*;
import static funclang.Value.*;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import funclang.Env.*;
//import jdk.internal.util.xml.impl.Pair;

public class Evaluator implements Visitor<Value> {

	Printer.Formatter ts = new Printer.Formatter();

	Env initEnv = initialEnv(); //New for definelang

	Value valueOf(Program p) {
			return (Value) p.accept(this, initEnv);
	}

	@Override
	public Value visit(AddExp e, Env env) {
		List<Exp> operands = e.all();
		double result = 0;
		for(Exp exp: operands) {
			NumVal intermediate = (NumVal) exp.accept(this, env); // Dynamic type-checking
			result += intermediate.v(); //Semantics of AddExp in terms of the target language.
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(UnitExp e, Env env) {
		return new UnitVal();
	}

	@Override
	public Value visit(NumExp e, Env env) {
		return new NumVal(e.v());
	}

	@Override
	public Value visit(StrExp e, Env env) {
		return new StringVal(e.v());
	}

	@Override
	public Value visit(BoolExp e, Env env) {
		return new BoolVal(e.v());
	}

	@Override
	public Value visit(DivExp e, Env env) {
		List<Exp> operands = e.all();
		NumVal lVal = (NumVal) operands.get(0).accept(this, env);
		double result = lVal.v();
		for(int i=1; i<operands.size(); i++) {
			NumVal rVal = (NumVal) operands.get(i).accept(this, env);
			result = result / rVal.v();
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(MultExp e, Env env) {
		List<Exp> operands = e.all();
		double result = 1;
		for(Exp exp: operands) {
			NumVal intermediate = (NumVal) exp.accept(this, env); // Dynamic type-checking
			result *= intermediate.v(); //Semantics of MultExp.
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(Program p, Env env) {
		try {
			for(DefineDecl d: p.decls())
				d.accept(this, initEnv);
			return (Value) p.e().accept(this, initEnv);
		} catch (ClassCastException e) {
			return new DynamicError(e.getMessage());
		}
	}

	@Override
	public Value visit(SubExp e, Env env) {
		List<Exp> operands = e.all();
		NumVal lVal = (NumVal) operands.get(0).accept(this, env);
		double result = lVal.v();
		for(int i=1; i<operands.size(); i++) {
			NumVal rVal = (NumVal) operands.get(i).accept(this, env);
			result = result - rVal.v();
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(VarExp e, Env env) {
		// Previously, all variables had value 42. New semantics.
		return env.get(e.name());
	}

	@Override
	public Value visit(LetExp e, Env env) { // New for varlang.
		List<String> names = e.names();
		List<Exp> value_exps = e.value_exps();
		List<Value> values = new ArrayList<Value>(value_exps.size());

		for(Exp exp : value_exps)
			values.add((Value)exp.accept(this, env));

		Env new_env = env;
		for (int index = 0; index < names.size(); index++)
			new_env = new ExtendEnv(new_env, names.get(index), values.get(index));

		return (Value) e.body().accept(this, new_env);
	}

	@Override
	public Value visit(DefineDecl e, Env env) { // New for definelang.
		String name = e.name();
		Exp value_exp = e.value_exp();
		Value value = (Value) value_exp.accept(this, env);
		((GlobalEnv) initEnv).extend(name, value);
		return new Value.UnitVal();
	}

	@Override
	public Value visit(LambdaExp e, Env env) { // New for funclang.

		TmpExp hold = e.temp();
		String lmda = hold.holder();
		Exp val = hold.v1();
		List<String> frm = e.formals();

		if(frm.contains(lmda))
			return new Value.FunVal(env, e.formals(),e.body());

	   else{
	   	List<String> vals = new ArrayList<>();
	   	List<Exp> ex = new ArrayList<>();

	   	vals.add(lmda);
	   	ex.add(val);

	   	Exp Expression = e.body();
	   	LetExp Madehole = new LetExp(vals , ex ,Expression);
	   	return new Value.FunVal(env,e.formals(),Madehole);
		}

	}

	@Override
	public Value visit(CallExp e, Env env) { // New for funclang.
		Object result = e.operator().accept(this, env);
		if(!(result instanceof Value.FunVal))
			return new Value.DynamicError("Operator not a function in call " +  ts.visit(e, env));
		Value.FunVal operator =  (Value.FunVal) result; //Dynamic checking
		List<Exp> operands = e.operands();

		// Call-by-value semantics
		List<Value> actuals = new ArrayList<Value>(operands.size());
		for(Exp exp : operands)
			actuals.add((Value)exp.accept(this, env));

		List<String> formals = operator.formals();
		if (formals.size()!=actuals.size())
			return new Value.DynamicError("Argument mismatch in call " + ts.visit(e, env));

		Env fun_env = operator.env();
		for (int index = 0; index < formals.size(); index++)
			fun_env = new ExtendEnv(fun_env, formals.get(index), actuals.get(index));

		return (Value) operator.body().accept(this, fun_env);
	}

	@Override
	public Value visit(IfExp e, Env env) { // New for funclang.
		Object result = e.conditional().accept(this, env);
		if(!(result instanceof Value.BoolVal))
			return new Value.DynamicError("Condition not a boolean in expression " +  ts.visit(e, env));
		Value.BoolVal condition =  (Value.BoolVal) result; //Dynamic checking

		if(condition.v())
			return (Value) e.then_exp().accept(this, env);
		else return (Value) e.else_exp().accept(this, env);
	}

	@Override
	public Value visit(LessExp e, Env env) { // New for funclang.
		Value first = (Value) e.first_exp().accept(this, env);
		Value second = (Value) e.second_exp().accept(this, env);
		return new Value.BoolVal(compareValue(first,second)<0);
	}

	public static int compareValue(Value fst, Value sec) {
		if(equalValue(fst,sec)){
			return 0;
		}
		if(fst instanceof NumVal && sec instanceof NumVal){
			NumVal first = (NumVal)fst;
			NumVal second = (NumVal)sec;
			return Double.compare(first.v(),second.v());
		}else if (fst instanceof StringVal && sec instanceof StringVal){
			String str1 = ((StringVal)fst).v();
			String str2 = ((StringVal)sec).v();
			return str1.compareTo(str2);
		}else if(fst instanceof PairVal && sec instanceof PairVal){
			PairVal p1 = (PairVal)fst;
			PairVal p2 = (PairVal)sec;
			if (p1.isList()&&p2.isList()){
				return Integer.compare(p1.size(),p2.size());
			}else if (!p1.isList()&& !p2.isList()){
				return 0;
			}
		} else if (fst instanceof PairVal && sec instanceof Null) {
			return 1;
		}else if(fst instanceof Null && sec instanceof PairVal) {
			return  -1;
		}

		return  -1;
	}

	@Override
	public Value visit(EqualExp e, Env env) { // New for funclang.
		Value first = (Value) e.first_exp().accept(this, env);
		Value second = (Value) e.second_exp().accept(this, env);
		return new Value.BoolVal(equalValue(first,second));
	}

	public static boolean equalValue(Value val1, Value val2) {
		if(val1 instanceof NumVal && val2 instanceof NumVal){
			NumVal first = (NumVal)val1;
			NumVal second = (NumVal)val2;
			return Double.compare(first.v(),second.v()) ==0;
		}else if (val1 instanceof StringVal && val2 instanceof StringVal){
			String str1 = ((StringVal)val1).v();
			String str2 =((StringVal)val2).v();
			return str1.equals(str2);
		}else if (val1 instanceof PairVal && val2 instanceof PairVal){
			boolean bool1 = equalValue(((PairVal)val1).fst(),((PairVal)val2).fst());
			boolean bool2 = equalValue(((PairVal)val1).snd(),((PairVal)val2).snd());
			if(bool1&&bool2) return true;
			return  false;
		} else if (val1 instanceof BoolVal && val2 instanceof BoolVal){
			boolean v1 = ((BoolVal)val1).v();
			boolean v2 = ((BoolVal)val2).v();
			return v1 == v2 ;
		} else if(val1 instanceof Null && val2 instanceof Null){
			return true;
		}else{
			return false;

		}
	}

	@Override
	public Value visit(GreaterExp e, Env env) { // New for funclang.
		Value first = (Value) e.first_exp().accept(this, env);
		Value second = (Value) e.second_exp().accept(this, env);
		return new Value.BoolVal(compareValue(first,second)>0);
	}

	@Override
	public Value visit(CarExp e, Env env) {
		Value.PairVal pair = (Value.PairVal) e.arg().accept(this, env);
		return pair.fst();
	}

	@Override
	public Value visit(CdrExp e, Env env) {
		Value.PairVal pair = (Value.PairVal) e.arg().accept(this, env);
		return pair.snd();
	}

	@Override
	public Value visit(ConsExp e, Env env) {
		Value first = (Value) e.fst().accept(this, env);
		Value second = (Value) e.snd().accept(this, env);
		return new Value.PairVal(first, second);
	}

	@Override
	public Value visit(ListExp e, Env env) { // New for funclang.
		List<Exp> elemExps = e.elems();
		int length = elemExps.size();
		if(length == 0)
			return new Value.Null();

		//Order of evaluation: left to right e.g. (list (+ 3 4) (+ 5 4))
		Value[] elems = new Value[length];
		for(int i=0; i<length; i++)
			elems[i] = (Value) elemExps.get(i).accept(this, env);

		Value result = new Value.Null();
		for(int i=length-1; i>=0; i--)
			result = new PairVal(elems[i], result);
		return result;
	}

	@Override
	public Value visit(NullExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.Null);
	}

	@Override
	public Value visit(LengthStrExp e, Env env) {
		Value val = (Value) e.getStrExpr().accept(this, env);
		if (val instanceof StringVal) {
			String string = ((StringVal) val).v();
			return new NumVal(string.length() - 2); // - 2 to remove double quotes
		}

		return new DynamicError("Parameter for length was not a string.");

	}

	@Override
	public Value visit(TmpExp e, Env env){
		NumVal v = (NumVal) e.v1().accept(this,env);
		return v;
	}

	public Value visit(EvalExp e, Env env) {
		StringVal programText = (StringVal) e.code().accept(this, env);
		Program p = _reader.parse(programText.v());
		return (Value) p.accept(this, env);
	}

	public Value visit(ReadExp e, Env env) {
		StringVal fileName = (StringVal) e.file().accept(this, env);
		try {
			String text = Reader.readFile("" + System.getProperty("user.dir") + File.separator + fileName.v());
			return new StringVal(text);
		} catch (IOException ex) {
			return new DynamicError(ex.getMessage());
		}
	}

	private Env initialEnv() {
		GlobalEnv initEnv = new GlobalEnv();

		/* Procedure: (read <filename>). Following is same as (define read (lambda (file) (read file))) */
		List<String> formals = new ArrayList<>();
		formals.add("file");
		Exp body = new AST.ReadExp(new VarExp("file"));
		Value.FunVal readFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("read", readFun);

		/* Procedure: (require <filename>). Following is same as (define require (lambda (file) (eval (read file)))) */
		formals = new ArrayList<>();
		formals.add("file");
		body = new EvalExp(new AST.ReadExp(new VarExp("file")));
		Value.FunVal requireFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("require", requireFun);

		/* Add new built-in procedures here */
		formals = new ArrayList<>();
		formals.add("str");
		body = new AST.LengthStrExp(new VarExp("str"));
		Value.FunVal lengthFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("length", lengthFun);

		return initEnv;
	}

	Reader _reader;
	public Evaluator(Reader reader) {
		_reader = reader;
	}
}
