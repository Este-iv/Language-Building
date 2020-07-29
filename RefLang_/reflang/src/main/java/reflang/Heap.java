package reflang;

public interface Heap {

    Value ref (Value value);
    Value deref (Value.RefVal loc);
    Value setref (Value.RefVal loc, Value value);
    Value free (Value.RefVal value);

    static public class Heap16Bit implements Heap{
        static final int HEAPSIZE = 65_536;

        Value[] _rep = new Value[HEAPSIZE];
        int i = 0;

        public  Value ref  (Value value){
            if (i >= HEAPSIZE)
                return new Value.DynamicError("out of memory error");
            Value.RefVal new_loc = new Value.RefVal(i);
            _rep[i++] = value;
            return new_loc;
        }
        public Value deref (Value.RefVal loc){
            try{
                return _rep[loc.loc()];
            }catch (ArrayIndexOutOfBoundsException e ){
                return new Value.DynamicError("Segmentation fault at access  "+ loc);
            }
        }
        public Value setref (Value.RefVal loc, Value value){
            try{
                return _rep[loc.loc()] = value;
            }catch (ArrayIndexOutOfBoundsException e ){
                return new Value.DynamicError("Segmentation fault at access  "+ loc);
            }
        }
        public Value free (Value.RefVal loc){
            try{
                _rep[loc.loc()] = null;
                return loc;
            } catch (ArrayIndexOutOfBoundsException e){
                return new Value.DynamicError("Segmentation fault at access  "+ loc);
            }
        }
        public  Heap16Bit(){}
    }
}
