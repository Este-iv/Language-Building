package arithlang;
import static arithlang.AST.*;
import static arithlang.Value.*;

import java.util.List;
// What do I do for the error out?
// would i need something else in the AST??

public class Evaluator implements Visitor<Value> {
//    private NumVal record = new NumVal(0);
    Printer.Formatter ts = new Printer.Formatter();

    Value valueOf(Program p) {
        // Value of a program in this language is the value of the expression
        return (Value) p.accept(this);
    }



    @Override
    public Value visit(AddExp e) {


        Value lResult = (Value) e.getLHS().accept(this);
        Value rResult = (Value) e.getRHS().accept(this);


        if (lResult instanceof EvenVal) {
            if (rResult instanceof OddVal) {
                return new OddVal();
            } else if (rResult instanceof EvenVal) {
                return new EvenVal();
            } else {
                return new UnknownVal();
            }
        } else  if (lResult instanceof OddVal) {
            if (rResult instanceof OddVal) {
                return new EvenVal();
            } else if (rResult instanceof EvenVal) {
                return new OddVal();
            } else{ return new UnknownVal();
        }
        }
        return new UnknownVal();
    }

    @Override
    public Value visit(NumExp e) {
        if (e.v().equals("e")) {
            return new EvenVal();
        }else if (e.v().equals("o")) {
            return new OddVal();
        }else if (e.v().equals("u")) {
            return new UnknownVal();
        }
        return new UnknownVal();
    }

    // Maybe make this a switch statement?


    @Override
    public Value visit(MultExp e) {
//            Exp lOper = e.getLoper();
//            Exp rOper = e.getRoper();

            Value lResult = (Value) e.getLHS().accept(this);
            Value rResult = (Value) e.getRHS().accept(this);

      if(lResult instanceof EvenVal){
          if (rResult instanceof OddVal){
              return new EvenVal();
          }else if(rResult instanceof EvenVal){
              return new EvenVal();
          }else{ return new UnknownVal();}
        }else if(lResult instanceof OddVal){
          if(rResult instanceof EvenVal){
              return new EvenVal();
          }else if(rResult instanceof OddVal){
              return new OddVal();
          }else {return new UnknownVal();}
      }
      return new UnknownVal();

    }

    @Override
    public Value visit(Program p) {
        return (Value) p.e().accept(this);
    }

}
