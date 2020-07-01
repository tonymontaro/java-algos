package Tools;

import java.util.*;

public class Tuples {
    static class Tuple implements Comparable<Tuple> {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int compareTo(Tuple other) {
            if (this.a == other.a) return Integer.compare(this.b, other.b);
            return Integer.compare(this.a, other.a);
        }

        @Override
        public int hashCode() { return Arrays.deepHashCode(new Integer[]{a, b});}

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString () {
            return String.format("%d,%d  ", this.a, this.b);
        }
    }

    static class LongTuple implements Comparable<LongTuple> {
        long a;
        long b;

        public LongTuple(long a, long b) {
            this.a = a;
            this.b = b;
        }

        public long getA() {
            return a;
        }

        public long getB() {
            return b;
        }

        public int compareTo(LongTuple other) {
            if (this.a == other.a) return Long.compare(this.b, other.b);
            return Long.compare(this.a, other.a);
        }

        @Override
        public int hashCode() { return Arrays.deepHashCode(new Long[]{a, b});}

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof LongTuple)) return false;
            LongTuple pairo = (LongTuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }

        @Override
        public String toString () {
            return String.format("%d,%d", this.a, this.b);
        }
    }


    public class MultiTuple<T> {
        private final T[] contents;

        public MultiTuple (T[] contents) {
            if (contents.length != 2)
                throw new IllegalArgumentException();
            this.contents = contents;
        }

        public T[] getContents () {
            return this.contents.clone();
        }

        @Override
        public int hashCode () {
            return Arrays.deepHashCode(this.contents);
        }

//        @Override
//        public boolean equals (Object other) {
//            return Arrays.deepEquals(this.contents, other.getContents());
//        }

        @Override
        public String toString () {
            return Arrays.deepToString(this.contents);
        }
    }
}


