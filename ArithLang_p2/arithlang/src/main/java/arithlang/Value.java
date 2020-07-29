package arithlang;

public interface Value {
    public String toString();
    // modify after this line.
    static class EvenVal implements Value{
        public String toString() {
            return "e" ;
        }

    }
    static class OddVal implements Value{
        public String toString() {
            return "o" ;
        }

    }
    static class UnknownVal implements  Value{
        public String toString() {
            return "u" ;
        }
    }

    // Do not touch this at all!
    static class DynamicError implements Value {
        private String _errorMsg;
        public DynamicError(String v) {
            _errorMsg = v;
        }
        public String v() {
            return _errorMsg;
        }
        public String toString() {
            String tmp = _errorMsg;
            if (tmp == _errorMsg) return "" + tmp;
            return "" + _errorMsg;
        }
    }
}
