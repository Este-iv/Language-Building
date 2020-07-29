package arithlang;
import static arithlang.AST.*;
import static arithlang.Value.*;

import java.util.List;

public class Evaluator implements Visitor<Value> {
    private NumVal record = new NumVal(0);
    Printer.Formatter ts = new Printer.Formatter();
	
    Value valueOf(Program p) {
        // Value of a program in this language is the value of the expression
        return (Value) p.accept(this);
    }
	
    @Override
    public Value visit(AddExp e) {
        List<Exp> operands = e.all();
        double result = 0;
        for(Exp exp: operands) {
            NumVal intermediate = (NumVal) exp.accept(this); // Dynamic type-checking
            result += intermediate.v(); //Semantics of AddExp in terms of the target language.
        }
        return new NumVal(result);
    }

    @Override
    public Value visit(NumExp e) {
        return new NumVal(e.v());
    }

    @Override
    public Value visit(DivExp e) {
        List<Exp> operands = e.all();
        NumVal lVal = (NumVal) operands.get(0).accept(this);
        double result = lVal.v(); 
        for(int i=1; i<operands.size(); i++) {
            NumVal rVal = (NumVal) operands.get(i).accept(this);
            if (rVal.v() == 0) {
                return new DynamicError(ts.visit(e));
            }
            result = result / rVal.v();
        }
        return new NumVal(result);
    }

    @Override
    public Value visit(MultExp e) {
        List<Exp> operands = e.all();
        double result = 1;
        for(Exp exp: operands) {
            NumVal intermediate = (NumVal) exp.accept(this); // Dynamic type-checking
            result *= intermediate.v(); //Semantics of MultExp.
        }
        return new NumVal(result);
    }

    @Override
    public Value visit(Program p) {
        return (Value) p.e().accept(this);
    }

    @Override
    public Value visit(SubExp e) {
        List<Exp> operands = e.all();
        NumVal lVal = (NumVal) operands.get(0).accept(this);
        double result = lVal.v();
        for(int i=1; i<operands.size(); i++) {
            NumVal rVal = (NumVal) operands.get(i).accept(this);
            result = result - rVal.v();
        }
        return new NumVal(result);
    }

    /*
        A thought :

        Pow should work like Sub, it should take rVal and lVal.
        But for the rVal it should loop/mult the rval. lVal of times
        in an example  rVal = 3 , lVal = 2  ... so 3 * 3  = 6
     */
    // This has the sub method... so it should act like sub.. for now
    /* Finally got the pow thing to work add the tthe 'pow' bellow the DOT in the .g file
    * this should be the last thing i need to work on, Would i need a helper method??
    **/

//    @Override
//    public Value visit(PowExp e){
@Override
public Value visit(PowExp e) {
    List<Exp> operands = e.all();
    NumVal lVal = (NumVal) operands.get(0).accept(this);
//    NumVal rVal = (NumVal) operands.get(0).accept(this);
    double result = lVal.v();
    for(int i=1; i<operands.size(); i++) {
        NumVal rVal = (NumVal) operands.get(i).accept(this);
        if(rVal.v() < 0){
            return new DynamicError("Value Cannot be Less than 0");
        }
        result = Math.pow(lVal.v(),rVal.v());
    }
    return new NumVal(result);
}
    //Helper method
//    private int pow(int a, int b){
//
//    }
}
