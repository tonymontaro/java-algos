package ArtCoder.ABC164;

import java.util.*;

public class Gacha {
    public static void main(String[] args) {
        HashSet<String> seen = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int t = 0; t < tests; t++) seen.add(sc.next());
        System.out.println(seen.size());

    }
}
