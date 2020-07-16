package GoogleKickStart.Year_2019.Year_2019_Practice;

import java.util.*;

public class PracticeRound_NumberGuessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int i=0; i < test; i++) {
            int low = sc.nextInt()+1;
            int high = sc.nextInt();
            int guesses = sc.nextInt();
            while (true) {
                int mid = (low + high) / 2;
                System.out.println(mid);
                String cm = sc.next();
                if (cm.equals("CORRECT")) break;
                else if (cm.equals("TOO_SMALL")) low = mid + 1;
                else high = mid - 1;
            }
        }
    }
}
