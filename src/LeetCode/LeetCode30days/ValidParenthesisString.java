package LeetCode.LeetCode30days;

public class ValidParenthesisString {
    public static boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    public static boolean check(String s, int idx, int open) {
        if (idx >= s.length()) return open == 0;
        if (open < 0) return false;
        boolean result = false;
        if (s.charAt(idx) == ')') {
            if (open == 0) return false;
            open--;
        }
        else if (s.charAt(idx) == '(') open++;
        else {
            result = check(s, idx+1, open+1);
            result = result || check(s, idx+1, open-1);
        }
        result = result || check(s, idx+1, open);
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(checkValidString("()"));
//        System.out.println(checkValidString(")("));
//        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("()(()(*(())()*)(*)))()))*)((()(*(((()())()))()()*)((*)))()))(*)(()()(((()*()()((()))((*((*)()"));
    }
}
