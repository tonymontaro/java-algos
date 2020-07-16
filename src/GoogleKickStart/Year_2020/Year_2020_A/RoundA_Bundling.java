package GoogleKickStart.Year_2020.Year_2020_A;

import java.util.*;

public class RoundA_Bundling {

    public static int solve(String[] arr, int group) {
        Trie trie = new Trie();
        for (String word: arr) trie.insert(word);
        Solver solver = new Solver(trie.root, group);
        return solver.solve();
    }

    static class Solver {
        int result = 0;
        int group;
        TrieNode node;

        public Solver(TrieNode node, int group) {
            this.node = node;
            this.group = group;
        }

        public int solve() {
            if (this.node != null) this.solve(this.node);
            return this.result;
        }

        public int solve(TrieNode node) {
            int total = node.ends;

            for (TrieNode child: node.children) {
                if (child != null) total += this.solve(child);
            }

            int rem = total % this.group;
            int rs = ((total - rem) / this.group) * node.depth;
            this.result += rs;
            return rem;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        for (int caseNum = 1; caseNum < tests+1; caseNum++) {
            int n = sc.nextInt();
            int group = sc.nextInt();
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) arr[i] = sc.next();
            int result = solve(arr, group);
            System.out.printf("Case #%d: %d\n", caseNum, result);
        }
    }
}


class TrieNode {
    public char val;
    public boolean isWord;
    public TrieNode[] children = new TrieNode[26];

    public int seen = 0;
    public int ends = 0;
    public int depth;

    public TrieNode() {
        this.val = ' ';
        this.depth = 0;
    }
    TrieNode(char c, int depth){
        this.val = c;
        this.depth = depth;
    }

    public TrieNode get(char c) {
        return this.children[c - 'A'];
    }

    @Override
    public String toString() {
        return String.format("%s(%d) -> %d, %d", this.val, this.depth, this.seen, this.ends);
    }
}

class Trie {
    public TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'A'] == null){
                ws.children[c - 'A'] = new TrieNode(c, i + 1);
            }
            ws.seen += 1;
            ws = ws.children[c - 'A'];
        }
        ws.seen += 1;
        ws.ends += 1;
        ws.isWord = true;
    }
}

//        System.out.println(solve(new String[]{"C", "AA", "AAA", "B", "CC", "AAA", "AAA", "AA", "A"}, 2));