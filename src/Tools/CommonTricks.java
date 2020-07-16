package Tools;


import java.util.*;

public class CommonTricks {


    public void comparatorSortExample() {
        // Collections.sort(students, Comparator.comparing(Student::getCgpa).reversed().thenComparing(Student::getFname).thenComparing(Student::getId));
        // Arrays.sort(array, Comparator.comparingDouble(o -> o[0]));

        // Reverse Sort
        //         Long[] colorless = in.nextLongArray(C);
        //        Arrays.sort(reds, Collections.reverseOrder());

//        java.util.Arrays.sort(arr, new Comparator<int[]>() {
//            public int compare(int[] a, int[] b) {
//                if (a[0] == b[0]) return Integer.compare(a[1], b[1]);
//                return Integer.compare(a[0], b[0]);
//            }
//        });

//        ArrayList<Long> xKeys = new ArrayList<>(xs.keySet());
//        xKeys.sort(new Comparator<Long>() {
//            public int compare(Long a, Long b) {
//                return Integer.compare(xs.get(a).size(), xs.get(b).size());
//            }
//        });
    }

    /*
    READ FROM FILE
    File file = new File("/Users/tonymontaro/Dropbox/Code/CompetitionPractice/src/CSES_FI/SortingAndSearching/in.txt");
    br = new BufferedReader(new FileReader(file));
     */

    public static void main(String[] args) {
//        Map<Tuple, Integer> seen = new HashMap<>();
//        seen.put(new Tuple(1, 2), 1);
//        seen.put(new Tuple(1, 2), 2);
//        seen.put(new Tuple(2, 1), 2);
//        System.out.println(seen);
    }
}
