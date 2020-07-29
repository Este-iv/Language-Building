package typelang;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

//import jdk.internal.util.xml.impl.Pair;
import typelang.AST.*;
import typelang.Env.ExtendEnv;
import typelang.Env.GlobalEnv;
import typelang.Type.*;

public class Checker implements Visitor<Type,Env<Type>> {
	Printer.Formatter ts = new Printer.Formatter();
	Env<Type> initEnv = initialEnv(); //New for definelang

	private Env<Type> initialEnv() {
		GlobalEnv<Type> initEnv = new GlobalEnv<Type>();

		/* Type for procedure: (read <filename>). Following is same as (define read (lambda (file) (read file))) */
		List<Type> formalTypes = new ArrayList<Type>();
		formalTypes.add(new Type.StringT());
		initEnv.extend("read", new Type.FuncT(formalTypes, new Type.StringT()));

		/* Type for procedure: (require <filename>). Following is same as (define require (lambda (file) (eval (read file)))) */
		formalTypes = new ArrayList<Type>();
		formalTypes.add(new Type.StringT());
		initEnv.extend("eval", new Type.FuncT(formalTypes, new Type.UnitT()));

		/* Add type for new built-in procedures here */

		return initEnv;
	}

	Type check(Program p) {
		return (Type) p.accept(this, null);
	}

	public Type visit(Program p, Env<Type> env) {
		Env<Type> new_env = initEnv;

		for (DefineDecl d : p.decls()) {
			Type type = (Type) d.accept(this, new_env);

			if (type instanceof ErrorT) {
				return type;
			}

			Type dType = d.type();

			if (!type.typeEqual(dType)) {
				return new ErrorT("Expected " + dType + " found " + type + " in " + ts.visit(d, null));
			}

			new_env = new ExtendEnv<Type>(new_env, d.name(), dType);
		}
		return (Type) p.e().accept(this, new_env);
	}

	public Type visit(VarExp e, Env<Type> env) {
		try {
			return env.get(e.name());
		} catch (Exception ex) {
			return new ErrorT("Variable " + e.name() +
					" has not been declared in " + ts.visit(e, null));
		}
	}

	//@TODO
	public Type visit(LetExp e, Env<Type> env) {
		// answer question 6
		List<String> names = e.names();
		List<Exp> val_exps = e.value_exps();
		List<Type> types = e.varTypes();
		List<Type> vals = new ArrayList<Type>(val_exps.size());

		int i = 0;
		for (Exp exp : val_exps) {
			Type type = (Type)exp.accept(this, env);
			if (type instanceof ErrorT) { return type; }

			Type argType = types.get(i);
			if (!type.typeEqual(argType)) {
				return new ErrorT("The declared type of the " + i +
						" let variable and the actual type mismatch, expect " +
						argType.tostring() + " found " + type.tostring() +
						" in " + ts.visit(e, null));
			}

			vals.add(type);
			i++;
		}

		Env<Type> new_env = env;
		for (int index = 0; index < names.size(); index++)
			new_env = new ExtendEnv<Type>(new_env, names.get(index),
					vals.get(index));

		return (Type) e.body().accept(this, new_env);
	}

	public Type visit(DefineDecl d, Env<Type> env) {
		String name = d.name();
		Type t = (Type) d._value_exp.accept(this, env);
		((GlobalEnv<Type>) initEnv).extend(name, t);
		return t;
	}

	public Type visit(LambdaExp e, Env<Type> env) {
		List<String> names = e.formals();
		List<Type> types = e.types();
		String message = "The number of formal parameters and the number of "
				+ "arguments in the function type do not match in ";
		if (types.size() == names.size()) {
			Env<Type> new_env = env;
			int index = 0;
			for (Type argType : types) {
				new_env = new ExtendEnv<Type>(new_env, names.get(index),
						argType);
				index++;
			}

			Type bodyType = (Type) e.body().accept(this, new_env);
			return new FuncT(types, bodyType);
		}
		return new ErrorT(message + ts.visit(e, null));
	}

	public Type visit(CallExp e, Env<Type> env) {
		// answer question 7

		Exp exp = e.operator();
		Type type = (Type) exp.accept(this,env);

		List<Exp> rules = e.operands();

		if(type instanceof ErrorT){return type;}
		if(!(type instanceof FuncT)){
			return new ErrorT("Expect a function type in the call expression, found "
					+ type.tostring() + " in " + ts.visit(e,null));
		}
		for(int index = 0; index< rules.size(); index++){
			Type type1 = (Type) rules.get(index).accept(this,env);
			if(type1 instanceof ErrorT){ return  type1;}
		}

		Type t = ((FuncT)type).returnType();
		List<Type> rule = ((FuncT)type).argTypes();

		for(int index = 0; index<rule.size(); index++){
			if(!(rule.get(index).typeEqual((Type)rules.get(index).accept(this,env)))){
				return new ErrorT("The Expected type of the "+ index +"th actual parameter is "+
						rules.get(index).toString() + " found "+ ((Type)rules.get(index).accept(this,env)).tostring()
						+ " in " + ts.visit(e,null));
			}
		}
		return t;
	}

//	@TODO
	public Type visit(IfExp e, Env<Type> env) {
		// answer question 5
		Exp con = e.conditional();
		Type condType = (Type)con.accept(this,env);
		Type _else = (Type)e._else_exp.accept(this,env);
		Type _then = (Type)e._then_exp.accept(this,env);

		if (!(condType instanceof BoolT))
		{
			return new ErrorT("The condition should have boolean type, found " +
					condType.tostring() + " in " + ts.visit(e, null));
		}
		if(_then instanceof ErrorT){return _then;}
		if(_else instanceof  ErrorT){return _else;}
		if (_then.typeEqual(_else)){return _then;}

		return new ErrorT("The then and else expressions should have the same "
				+ "type, then has type " + _then.tostring() +
				" else has type " + _else.tostring() + " in " +
				ts.visit(e, null));
	}

	public Type visit(CarExp e, Env<Type> env) {
		Exp exp = e.arg();
		Type type = (Type) exp.accept(this, env);
		if (type instanceof ErrorT) {
			return type;
		}

		if (type instanceof PairT) {
			PairT pt = (PairT) type;
			return pt.fst();
		}

		return new ErrorT("The car expect an expression of type Pair, found "
				+ type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(CdrExp e, Env<Type> env) {
		// answer question 2(a)
		Exp exp = e.arg();
		Type type = (Type) exp.accept(this, env);
		if (type instanceof ErrorT)
			return type;

		if (type instanceof PairT) {
			PairT pair = (PairT) type;
			return pair.snd();
		}

		return new ErrorT("The cdr expect an expression of type Pair, found "
				+ type.tostring() + " in " + ts.visit(e, null));
	}


	public Type visit(ConsExp e, Env<Type> env) {
		Exp fst = e.fst(); 
		Exp snd = e.snd();

		Type t1 = (Type)fst.accept(this, env);
		if (t1 instanceof ErrorT) { return t1; }

		Type t2 = (Type)snd.accept(this, env);
		if (t2 instanceof ErrorT) { return t2; }

		return new PairT(t1, t2);
	}
	//@TODO
	public Type visit(ListExp e, Env<Type> env) {
		// answer question 2(b)
		List<Exp> els = e.elems();
		Type type = e.type();

		int i ;
		for(i = 0; i < els.size(); i++) {
			Exp element = els.get(i);
			Type eltType = (Type) element.accept(this, env);
			if(eltType instanceof ErrorT) {
				return eltType;
			}

			if (!assignable(type, eltType)) {
				return new ErrorT("The " + i + " expression should have type " +
						type.tostring() + " found " + eltType.tostring() +
						" in " + ts.visit(e, null));
			}

		}
		return new ListT(type);
	}

	public Type visit(NullExp e, Env<Type> env) {
		Exp arg = e.arg();
		Type type = (Type)arg.accept(this, env);
		if (type instanceof ErrorT) { return type; }

		if (type instanceof ListT) { return BoolT.getInstance(); }

		return new ErrorT("The null? expect an expression of type List, found "
				+ type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(RefExp e, Env<Type> env) {
		// answer question 1(a)
		Exp value = e.value_exp();
		Type type  = e.type();
		Type expType=(Type)value.accept(this,env);
		if(type instanceof ErrorT)
			return type;
		if(expType.typeEqual(type))
			return new RefT(type);

		return new ErrorT("The Ref expression expect type " + type.tostring() +
				" found " + expType.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(DerefExp e, Env<Type> env) {
		Exp exp = e.loc_exp();
		Type type = (Type)exp.accept(this, env);
		if (type instanceof ErrorT) { return type; }

		if (type instanceof RefT) {
			RefT rt = (RefT)type;
			return rt.nestType();
		}

		return new ErrorT("The dereference expression expect a reference type " +
				"found " + type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(AssignExp e, Env<Type> env) {
		// answer question 1(b)
		Exp lhs = e.lhs_exp();
		Type lhsType = (Type)lhs.accept(this,env);
		if(lhsType instanceof ErrorT)
			return lhsType;
		if(lhsType instanceof RefT){
			Exp rhs = e.rhs_exp();
			Type rhsType = (Type)rhs.accept(this,env);
			if(rhsType instanceof ErrorT)
				return rhsType;
			RefT reft = (RefT)lhsType;
			Type nest = reft.nestType();

			if(rhsType.typeEqual(nest)){return  rhsType;}
			return new ErrorT("The inner type of the reference type is " +
					nest.tostring() + " the rhs type is " + rhsType.tostring()
					+ " in " + ts.visit(e, null));
		}

		return new ErrorT("The lhs of the assignment expression expect a "
				+ "reference type found " + lhsType.tostring() + " in " +
				ts.visit(e, null));
	}

	public Type visit(FreeExp e, Env<Type> env) {
		Exp exp = e.value_exp();
		Type type = (Type)exp.accept(this, env);

		if (type instanceof ErrorT) { return type; }

		if (type instanceof RefT) { return UnitT.getInstance(); }

		return new ErrorT("The free expression expect a reference type " +
				"found " + type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(UnitExp e, Env<Type> env) {
		return Type.UnitT.getInstance();
	}

	public Type visit(NumExp e, Env<Type> env) {
		return NumT.getInstance();
	}

	public Type visit(StrExp e, Env<Type> env) {
		return Type.StringT.getInstance();
	}

	public Type visit(BoolExp e, Env<Type> env) {
		return Type.BoolT.getInstance();
	}

	public Type visit(LessExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

	public Type visit(EqualExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

	public Type visit(GreaterExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

//	@TODO
	private Type visitBinaryComparator(BinaryComparator e, Env<Type> env,
			String printNode) {
		// answer question 4
		Exp one = e.first_exp();
		Exp two = e.second_exp();

		Type type_fst = (Type)one.accept(this,env);
		Type type_snd = (Type)two.accept(this,env);

		if(type_fst instanceof ErrorT){return type_fst;}
		if( type_snd instanceof ErrorT){return type_snd;}

		if (!(type_fst instanceof NumT)) {
			return new ErrorT("The first argument of a binary expression "
					+ "should be num Type, found " + type_fst.tostring() +
					" in " + printNode);
		}

		if (!(type_snd instanceof NumT)) {
			return new ErrorT("The second argument of a binary expression "
					+ "should be num Type, found " + type_snd.tostring() +
					" in " + printNode);
		}

		return BoolT.getInstance();
	}


	public Type visit(AddExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(DivExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(MultExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(SubExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	private Type visitCompoundArithExp(CompoundArithExp e, Env<Type> env, String printNode) {
		// answer question 3
		List<Exp> op = e.all();

		int i ;
		for(i = 0; i < op.size(); i++) {
			Exp rules = op.get(i);
			Type ruleType = (Type) rules.accept(this, env);
			if(ruleType instanceof ErrorT) {return ruleType; }

			if (!(ruleType instanceof Type.NumT)) {
				return new ErrorT("Expected number found " + ruleType.tostring() +
						" in " + printNode);
			}
		}
		return NumT.getInstance();
	}

	private static boolean assignable(Type t1, Type t2) {
		if (t2 instanceof UnitT) { return true; }

		return t1.typeEqual(t2);
	}
	
	public Type visit(ReadExp e, Env<Type> env) {
		return UnitT.getInstance();
	}

	public Type visit(EvalExp e, Env<Type> env) {
		return UnitT.getInstance();
	}
}
