package Tools;

public class FenWickTree_RangedUpdate {
    static class FenWick {


        public FenWick(int maxVal) {
//            this.
        }

        double leastSignificantBit(long n) {
            long pow = 0;
            while ((n & 1) != 1) {
                n >>= 1;
                pow += 1;
            }
            return Math.pow(2, pow);
        }



    }


    public static void main(String[] args) {
//        leastSignificantBit(14)
        System.out.println();
    }
}
