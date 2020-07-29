package arithlang;

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
    public static abstract class ASTNode {
        public abstract Object accept(Visitor visitor);
    }
    public static class Program extends ASTNode {
        Exp _e;

        public Program(Exp e) {
            _e = e;
        }

        public Exp e() {
            return _e;
        }
		
        public Object accept(Visitor visitor) {
            return visitor.visit(this);
        }
    }
    public static abstract class Exp extends ASTNode {

    }

    public static class NumExp extends Exp {
        String _val;

        public NumExp(String v) {
            _val = v;
        }

        public String v() {
            return _val;
        }
		
        public Object accept(Visitor visitor) {
            return visitor.visit(this);
        }
    }

    public static abstract class CompoundArithExp extends Exp {

        // Do i add exps here??
        // for the right and left hand side Operators.

        Exp _LHS;
        Exp _RHS;
        public CompoundArithExp(Exp lOp, Exp rOp) {
            _LHS = lOp;
            _RHS = rOp;
        }

        public Exp getLHS() {
            return _LHS;
        }
        public Exp getRHS(){
            return _RHS;
        }

    }

    public static class AddExp extends CompoundArithExp {
        public AddExp(Exp lOp, Exp rOp) {
            super(lOp, rOp);
        }
        public Object accept(Visitor visitor) {
            return visitor.visit(this);
        }
    }



    public static class MultExp extends CompoundArithExp {
        public MultExp(Exp lOp, Exp rOp) { super(lOp, rOp); }
        public Object accept(Visitor visitor) {
            return visitor.visit(this);
        }
    }
		
    public interface Visitor <T> {
        // This interface should contain a signature for each concrete AST node.
        public T visit(AST.NumExp e);
        public T visit(AST.AddExp e);
        public T visit(AST.MultExp e);
        public T visit(AST.Program p);
    }	
}
