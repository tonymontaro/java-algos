package ArtCoder.ABC164;

import java.util.*;

public class MultipleOf2019 {
    static Collection<Integer> generateModulusArray(String word) {
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, 1);
        int prevMod = 0;
        int tensMod = 1;
        for (int i = word.length()-1; i >= 0; i--) {
            prevMod = (tensMod * (word.charAt(i) - '0') + prevMod) % 2019;
            seen.merge(prevMod, 1, Integer::sum);
            tensMod = (tensMod * 10) % 2019;
        }
        return seen.values();
    }

    public static void main(String[] args) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        String word = sc.next();
        Collection<Integer> reps = generateModulusArray(word);
        for (Integer r: reps) result += (r * (r - 1))/2;
        System.out.println(result);
    }
}
