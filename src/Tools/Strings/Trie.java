package Tools.Strings;

import java.util.*;

class TrieNode {
    public char val;
    public boolean isWord;
    public HashMap<Character, TrieNode> children = new HashMap<>();

    public TrieNode() {}
    TrieNode(char c){ this.val = c; }

    public TrieNode get(char c) {
        if (children.containsKey(c)) return children.get(c);
        return null;
    }

    @Override
    public String toString() { return String.format("Node: %s", this.val); }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.get(c) == null){
                ws.children.put(c, new TrieNode(c));
            }
            ws = ws.get(c);
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = getPrefix(word);
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        return getPrefix(prefix) != null;
    }

    public TrieNode getPrefix(String prefix) {
        TrieNode ws = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.get(c) == null) return null;
            ws = ws.get(c);
        }
        return ws;
    }

    public static void main(String[] args) {
        Trie tt = new Trie();
        tt.insert("TONY");
        tt.insert("ANT");
        System.out.println(tt.root.get('T').get('O').get('N').get('Y').isWord);
        TrieNode t = new TrieNode('T');
        System.out.println(t.val);
    }
}