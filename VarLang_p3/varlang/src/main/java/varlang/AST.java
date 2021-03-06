package varlang;

import java.util.ArrayList;
import java.util.List;

/**
 * This class hierarchy represents expressions in the abstract syntax tree
 * manipulated by this interpreter.
 * 
 * @author hridesh
 * 
 */
@SuppressWarnings("rawtypes")
public interface AST {
	public static abstract class ASTNode implements AST {
		public abstract Object accept(Visitor visitor, Env env);
	}

	public static class Program extends ASTNode {
		Exp _e;
		List<DefineDecl> dec;

		public Program(List<DefineDecl> dec, Exp e) {
			this.dec = dec;
			_e = e;
		}

		public List<DefineDecl> dec() {
			return this.dec;
		}

		public Exp e() {
			return _e;
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static abstract class Exp extends ASTNode {

	}

	public static class VarExp extends Exp {
		String _name;

		public VarExp(String name) {
			_name = name;
		}

		public String name() {
			return _name;
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static class NumExp extends Exp {
		double _val;

		public NumExp(double v) {
			_val = v;
		}

		public double v() {
			return _val;
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static abstract class CompoundArithExp extends Exp {
		List<Exp> _rest;

		public CompoundArithExp() {
			_rest = new ArrayList<Exp>();
		}

		public CompoundArithExp(Exp fst) {
			_rest = new ArrayList<Exp>();
			_rest.add(fst);
		}

		public CompoundArithExp(List<Exp> args) {
			_rest = new ArrayList<Exp>();
			for (Exp e : args)
				_rest.add((Exp) e);
		}

		public CompoundArithExp(Exp fst, List<Exp> rest) {
			_rest = new ArrayList<Exp>();
			_rest.add(fst);
			_rest.addAll(rest);
		}

		public CompoundArithExp(Exp fst, Exp second) {
			_rest = new ArrayList<Exp>();
			_rest.add(fst);
			_rest.add(second);
		}

		public Exp fst() {
			return _rest.get(0);
		}

		public Exp snd() {
			return _rest.get(1);
		}

		public List<Exp> all() {
			return _rest;
		}

		public void add(Exp e) {
			_rest.add(e);
		}

	}

	public static class AddExp extends CompoundArithExp {
		public AddExp(Exp fst) {
			super(fst);
		}

		public AddExp(List<Exp> args) {
			super(args);
		}

		public AddExp(Exp fst, List<Exp> rest) {
			super(fst, rest);
		}

		public AddExp(Exp left, Exp right) {
			super(left, right);
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static class SubExp extends CompoundArithExp {

		public SubExp(Exp fst) {
			super(fst);
		}

		public SubExp(List<Exp> args) {
			super(args);
		}

		public SubExp(Exp fst, List<Exp> rest) {
			super(fst, rest);
		}

		public SubExp(Exp left, Exp right) {
			super(left, right);
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static class DivExp extends CompoundArithExp {
		public DivExp(Exp fst) {
			super(fst);
		}

		public DivExp(List<Exp> args) {
			super(args);
		}

		public DivExp(Exp fst, List<Exp> rest) {
			super(fst, rest);
		}

		public DivExp(Exp left, Exp right) {
			super(left, right);
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static class MultExp extends CompoundArithExp {
		public MultExp(Exp fst) {
			super(fst);
		}

		public MultExp(List<Exp> args) {
			super(args);
		}

		public MultExp(Exp fst, List<Exp> rest) {
			super(fst, rest);
		}

		public MultExp(Exp left, Exp right) {
			super(left, right);
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	/**
	 * A let expression has the syntax
	 * <p>
	 * (let ((name expression)* ) expression)
	 *
	 * @author hridesh
	 */
	public static class LetExp extends Exp {
		List<String> _names;
		List<Exp> _value_exps;
		Exp _body;

		public LetExp(List<String> names, List<Exp> value_exps, Exp body) {
			_names = names;
			_value_exps = value_exps;
			_body = body;
		}


		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}

		public List<String> names() {
			return _names;
		}

		public List<Exp> value_exps() {
			return _value_exps;
		}

		public Exp body() {
			return _body;
		}

	}

	public static class LeteExp extends LetExp {
		NumExp _key;

		public LeteExp(List<String> names, List<Exp> value_exps, Exp body, NumExp key) {
			super(names, value_exps, body);
			_key = key;
		}

		public NumExp key() {
			return _key;
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public static class DecExp extends VarExp {
		NumExp _key;

		public DecExp(NumExp key, String varExp) {
			super(varExp);
			_key = key;
		}

		public NumExp key() {
			return _key;
		}

		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public class UnitExp extends Exp {
		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}

	public class DefineDecl extends Exp {
		String ident;
		Exp exp;

		public DefineDecl(String VarExp, Exp exp) {
			this.ident = VarExp;
			this.exp = exp;
		}

		public Exp getExp() {
			return exp;
		}

		public String getIdent() {
			return ident;
		}

		@Override
		public Object accept(Visitor visitor, Env env) {
			return visitor.visit(this, env);
		}
	}


	public interface Visitor<T> {
		// This interface should contain a signature for each concrete AST node.
		public T visit(AST.AddExp e, Env env);

		public T visit(AST.NumExp e, Env env);

		public T visit(AST.DivExp e, Env env);

		public T visit(AST.MultExp e, Env env);

		public T visit(AST.Program p, Env env);

		public T visit(AST.SubExp e, Env env);

		public T visit(AST.VarExp e, Env env);

		public T visit(AST.LetExp e, Env env); // New for the varlang

		public T visit(AST.LeteExp e, Env env); // For problem 6.

		public T visit(AST.DecExp e, Env env); // For problem 6.

		public T visit(AST.UnitExp e, Env env); //

		public T visit(AST.DefineDecl e, Env env);

	}

}


