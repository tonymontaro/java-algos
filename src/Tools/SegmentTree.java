package Tools;


import java.util.Arrays;

public class SegmentTree {

    public static void main(String[] args)  {
//        int[] arr = new int[]{-1, 2, 4, 0};
//
//        SegTree segTree = new SegTree(arr);
//        System.out.println(segTree.rangeMinQuery(1, 2)); // 2
//        System.out.println(segTree.rangeMinQuery(0, 3)); // -1
//        System.out.println(segTree.rangeMinQuery(2, 2)); // 4
        int[] arr = new int[]{1, 5, 2, -3, 4, -1};
        SegTree segTree = new SegTree(arr);
        System.out.println(Arrays.toString(segTree.segTree));
        segTree.update(1, -2);
        System.out.println(Arrays.toString(segTree.segTree));
    }

    static class SegTree {
        int[] arr;
        Integer[] segTree;

        public SegTree(int[] arr) {
            this.arr = arr;
            segTree = new Integer[arr.length * 4];
            Arrays.fill(segTree, null);
            construct(0, 0, arr.length - 1);
        }

        int construct(int idx, int start, int end) {
            if (start == end) segTree[idx] = arr[start];
            else {
                int mid = (start + end) / 2;
                int left = construct(2 * idx + 1, start, mid);
                int right = construct(2 * idx + 2, mid + 1, end);
                segTree[idx] = Math.min(left, right);
            }
            return segTree[idx];
        }

        int rangeMinQuery(int qStart, int qEnd) {
            // Queries are zero indexed
            return rangeMinQuery(0, 0, arr.length - 1, qStart, qEnd);
        }

        int rangeMinQuery(int idx, int start, int end, int qStart, int qEnd) {
            if (start >= qStart && end <= qEnd) return segTree[idx];
            else if (start > qEnd || end < qStart) return Integer.MAX_VALUE;
            int mid = (start + end) / 2;
            int left = rangeMinQuery(2 * idx + 1, start, mid, qStart, qEnd);
            int right = rangeMinQuery(2 * idx + 2, mid + 1, end, qStart, qEnd);
            return Math.min(left, right);
        }

        void update(int queryIdx, int value) {
            arr[queryIdx] = value;
            update(0, 0, arr.length-1, queryIdx);
        }
        void update(int idx, int start, int end, int qIdx) {
            if (start > qIdx || end < qIdx) return;
            segTree[idx] = Math.min(segTree[idx], arr[qIdx]);
            if (start != end) {
                int mid = (start + end) / 2;
                update(2 * idx + 1, start, mid, qIdx);
                update(2 * idx + 2, mid + 1, end, qIdx);
            }
        }
    }
}
