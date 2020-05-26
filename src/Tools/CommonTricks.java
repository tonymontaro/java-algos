package Tools;

import java.util.*;

public class CommonTricks {

    static class Tuple {
        int a;
        int b;

        public Tuple(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() { return a ^ b; }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple)) return false;
            Tuple pairo = (Tuple) o;
            return (this.a == pairo.a && this.b == pairo.b);
        }
    }

    public void comparatorSortExample() {
        // Collections.sort(students, Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getFname).thenComparing(Student::getId));
        // Arrays.sort(array, Comparator.comparingDouble(o -> o[0]));
    }

    public static void main(String[] args) {
        Map<Tuple, Integer> seen = new HashMap<>();
        seen.put(new Tuple(1, 2), 1);
        seen.put(new Tuple(1, 2), 2);
        seen.put(new Tuple(2, 1), 2);
        System.out.println(seen);
    }
}
