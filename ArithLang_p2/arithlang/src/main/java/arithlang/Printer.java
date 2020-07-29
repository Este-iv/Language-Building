package arithlang;

import static arithlang.AST.*;

public class Printer {
    public void print(Value v) {
        System.out.println(v.toString());
    }
	
    public static class Formatter implements AST.Visitor<String> {
		
        public String visit(Program p) {
            return (String) p.e().accept(this);
        }
		
        public String visit(NumExp e) {
            return "" +  e.v();
        }

        // this is needing to be modified more.
        public String visit(AddExp e) {
            String result = "(+";
            result += " " + e.getLHS() + " "+ e.getRHS();
            return result + ")";
        }
		
        public String visit(MultExp e) {
            String result = "(+";
            result += " " + e.getLHS()+ " "+ e.getRHS();
            return result + ")";
        }


        }
    }
