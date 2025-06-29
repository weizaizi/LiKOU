import java.util.*;

class Solution {


    //1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                return new int[]{hashMap.get(nums[i]), i};
            } else {
                hashMap.put(target - nums[i], i);
            }
        }

        return new int[2];
    }

    //2. 两数相加
    @SuppressWarnings("all")
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        int sum = 0;
        if (l1 != null) sum += l1.val;
        if (l2 != null) sum += l2.val;
        if (sum / 10 != 0) {
            if (l1 != null && l1.next != null) l1.next.val++;
            else if (l2 != null && l2.next != null) l2.next.val++;
            else return new ListNode(sum % 10, new ListNode(1));
        }

        return new ListNode(sum % 10, addTwoNumbers(l1 != null ? l1.next : null, l2 != null ? l2.next : null));
    }

    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        char[] charArray = s.toCharArray();
        int i = 0;
        int j = 0;
        while (j < charArray.length) {
            if (hashMap.containsKey(charArray[j]) && hashMap.get(charArray[j]) >= i) {
                result = Integer.max(result, j - i);
                i = hashMap.get(charArray[j]) + 1;
            }
            hashMap.put(charArray[j], j++);
        }
        return Integer.max(result, j - i);
    }

    //4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int now = -1;
        int last = -1;
        int length = nums1.length + nums2.length;
        for (int k = 0; k <= length / 2; k++) {
            last = now;
            if (i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) now = nums1[i++];
            else now = nums2[j++];
        }
        if (length % 2 == 0) return (last + now) / 2.0;
        else return now;
    }

    //5. 最长回文子串
    public String longestPalindrome(String s) {
        char[] charArray = s.toCharArray();
        boolean[][] dp = new boolean[charArray.length + 2][charArray.length + 2];
        int length = 0;
        int start = 0;
        int end = 0;
        for (int i = charArray.length; i >= 1; i--) {
            for (int j = i; j <= charArray.length; j++) {
                if (charArray[i - 1] == charArray[j - 1] && ((dp[i + 1][j - 1]) || i + 2 > j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > length) {
                        length = j - i + 1;
                        start = i - 1;
                        end = j - 1;
                    }
                }
            }
        }
        return s.substring(start, end + 1);
    }

    //6. Z 字形变换
    public String convert(String s, int numRows) {
        StringBuilder result = new StringBuilder();
        char[] charArray = s.toCharArray();

        int length = (numRows - 1) * 2;
        if (length == 0) length = 1;

        for (int i = 0; i < numRows; i++) {
            int j = 0;
            while (i + j * length < charArray.length) {
                if (i == 0 || i == numRows - 1) result.append(charArray[i + j * length]);
                else {
                    result.append(charArray[i + j * length]);
                    if ((j + 1) * length - i < charArray.length) result.append(charArray[(j + 1) * length - i]);
                }
                j++;
            }
        }

        return result.toString();
    }

    //7. 整数反转
    public int reverse(int x) {
        long num = x;
        num = Math.abs(num);
        long result = 0;

        while (num != 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        if (x < 0) result = -result;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) return 0;
        return (int) result;
    }

    //8. 字符串转换整数 (atoi)
    public int myAtoi(String s) {
        char[] charArray = s.toCharArray();
        boolean minus = false;
        int i = 0;

        while (i < charArray.length && charArray[i] == ' ') i++;

        if (i < charArray.length && charArray[i] == '-') {
            minus = true;
            i++;
        } else if (i < charArray.length && charArray[i] == '+') i++;

        long result = 0;

        while (i < charArray.length && charArray[i] >= '0' && charArray[i] <= '9') {
            result = result * 10 + charArray[i] - '0';
            if (result >= Integer.MAX_VALUE && !minus) return Integer.MAX_VALUE;
            else if (result >= 2147483648L) return Integer.MIN_VALUE;
            i++;
        }

        return minus ? -(int) result : (int) result;
    }

    //9. 回文数
    public boolean isPalindrome(int x) {
        char[] s = String.valueOf(x).toCharArray();
        int i = 0;
        int j = s.length - 1;
        while (i <= j) {
            if (s[i] != s[j]) return false;
            i++;
            j--;
        }
        return true;
    }

    //10. 正则表达式匹配
    public boolean isMatch(String s, String p) {
        char[] sCharArray = s.toCharArray();
        char[] pCharArray = p.toCharArray();
        boolean[][] dp = new boolean[sCharArray.length + 1][pCharArray.length + 1];
        dp[0][0] = true;
        for (int i = 2; i <= pCharArray.length; i += 2) {
            if (pCharArray[i - 1] == '*') dp[0][i] = dp[0][i - 2];
            else break;
        }

        for (int i = 1; i <= sCharArray.length; i++) {
            for (int j = 1; j <= pCharArray.length; j++) {
                if (pCharArray[j - 1] == '*')
                    dp[i][j] = dp[i - 1][j] && (pCharArray[j - 2] == sCharArray[i - 1] || pCharArray[j - 2] == '.') || dp[i][j - 2];
                else
                    dp[i][j] = dp[i - 1][j - 1] && (pCharArray[j - 1] == sCharArray[i - 1] || pCharArray[j - 1] == '.');
            }
        }

        return dp[sCharArray.length][pCharArray.length];
    }

    //11. 盛最多水的容器
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;

        while (i < j) {
            max = Integer.max(max, (j - i) * Integer.min(height[i], height[j]));
            if (height[i] > height[j]) {
                int last = height[j];
                while (i < j && height[j] <= last) j--;
            } else {
                int last = height[i];
                while (i < j && height[i] <= last) i++;
            }
        }

        return max;
    }

    char[] intToRomanArray1 = {'I', 'X', 'C', 'M'};
    char[] intToRomanArray2 = {'V', 'L', 'D'};

    //12. 整数转罗马数字
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        char[] charArray = String.valueOf(num).toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '4') {
                sb.append(intToRomanArray1[charArray.length - i - 1]).append(intToRomanArray2[charArray.length - i - 1]);
                continue;
            } else if (charArray[i] == '9') {
                sb.append(intToRomanArray1[charArray.length - i - 1]).append(intToRomanArray1[charArray.length - i]);
                continue;
            }
            if (charArray[i] >= '5') {
                charArray[i] -= 5;
                sb.append(intToRomanArray2[charArray.length - i - 1]);
            }

            sb.append(String.valueOf(intToRomanArray1[charArray.length - i - 1]).repeat(Math.max(0, charArray[i] - '0')));
        }

        return sb.toString();
    }


    //13. 罗马数字转整数
    static char[] romanToIntArray1 = {'I', 'X', 'C', 'M'};
    static char[] romanToIntArray2 = {'V', 'L', 'D'};

    @SuppressWarnings("all")
    public int romanToInt(String s) {
        char[] charArray = s.toCharArray();
        int result;
        result = add(charArray[charArray.length - 1], 0);
        for (int i = charArray.length - 2; i >= 0; i--) {
            char c = charArray[i];
            char last = charArray[i + 1];
            if (getIndex(romanToIntArray1, c) == getIndex(romanToIntArray2, last) && getIndex(romanToIntArray1, c) != Integer.MAX_VALUE || getIndex(romanToIntArray1, c) < getIndex(romanToIntArray1, last) && getIndex(romanToIntArray1, last) != Integer.MAX_VALUE) {
                result = 2 * result - add(c, result);
            } else {
                result = add(c, result);
            }
        }
        return result;
    }

    private int getIndex(char[] array, char r) {
        for (int i = 0; i < array.length; i++) {
            if (r == array[i]) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private int add(char c, int i) {
        if (c == 'I') {
            i += 1;
        } else if (c == 'X') {
            i += 10;
        } else if (c == 'C') {
            i += 100;
        } else if (c == 'M') {
            i += 1000;
        } else if (c == 'V') {
            i += 5;
        } else if (c == 'L') {
            i += 50;
        } else if (c == 'D') {
            i += 500;
        }
        return i;
    }

    //14. 最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        return getCommonPrefix(strs, 0, strs.length - 1);
    }

    private String getCommonPrefix(String[] strs, int num1, int num2) {
        if (num1 == num2) {
            return strs[num1];
        }
        if (num2 - num1 == 1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Integer.min(strs[num1].length(), strs[num2].length()); i++) {
                if (strs[num1].charAt(i) == strs[num2].charAt(i)) sb.append(strs[num1].charAt(i));
                else break;
            }
            return sb.toString();
        }

        String str1 = getCommonPrefix(strs, num1, (num2 + num1) / 2);
        String str2 = getCommonPrefix(strs, (num1 + num2) / 2 + 1, num2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Integer.min(str1.length(), str2.length()); i++) {
            if (str1.charAt(i) == str2.charAt(i)) sb.append(str1.charAt(i));
            else break;
        }
        return sb.toString();
    }

    //15. 三数之和
    @SuppressWarnings("all")
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            List<Integer> list = new ArrayList<>(4);

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                if (nums[start] + nums[end] == -nums[i]) {
                    list.add(nums[start]);
                    list.add(nums[end]);
                    list.add(nums[i]);
                    result.add(list);
                    list = new ArrayList<>(4);
                    int n = nums[start];
                    while (start < end && nums[start] == n) start++;
                    n = nums[end];
                    while (start < end && nums[end] == n) end--;
                } else if (nums[start] + nums[end] > -nums[i]) end--;
                else start++;
            }
        }
        return result;
    }

    //16. 最接近的三数之和
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                int add = nums[start] + nums[end] + nums[i];
                if (add == target) return target;
                else if (add > target) end--;
                else start++;
                result = Math.abs(result - target) > Math.abs(add - target) ? add : result;
            }
        }

        return result;
    }

    //17. 电话号码的字母组合
    @SuppressWarnings("all")
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.isEmpty()) return new ArrayList<>();
        char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}};
        char[] charArray = digits.toCharArray();
        List<String> result = new ArrayList<>();
        letterCombinationsDfs(result, new StringBuilder(), charArray, 0, chars);
        return result;
    }

    private void letterCombinationsDfs(List<String> result, StringBuilder sb, char[] charArray, int index, char[][] chars) {
        if (index == charArray.length) {
            result.add(sb.toString());
            return;
        }

        int n = charArray[index] - '0' - 2;

        for (char c : chars[n]) {
            sb.append(c);
            letterCombinationsDfs(result, sb, charArray, index + 1, chars);
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    @SuppressWarnings("all")
    //18. 四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }

            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {

                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }

                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int start = j + 1;
                int end = nums.length - 1;
                while (start < end) {
                    long t = (long) nums[start] + nums[end] + nums[i] + nums[j];
                    if (t >= Integer.MAX_VALUE) {
                        end--;
                        continue;
                    }
                    if (t <= Integer.MIN_VALUE) {
                        start++;
                        continue;
                    }
                    if (t == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[start]);
                        temp.add(nums[end]);
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        result.add(temp);
                        int n = nums[start];
                        while (start < end && nums[start] == n) start++;
                    } else if (t > target) end--;
                    else start++;
                }

            }
        }
        return result;
    }

    @SuppressWarnings("all")
    //19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        int now = 1;

        while (now < n) {
            if (p.next == null) return head;
            p = p.next;
            now++;
        }
        ListNode delete = head;
        if (p.next == null) return head.next;

        ListNode last = delete;
        while (p.next != null) {
            p = p.next;
            last = delete;
            delete = delete.next;
        }
        last.next = delete.next;
        return head;
    }

    //20. 有效的括号
    public boolean isValid(String s) {
        Stack<Character> queue = new Stack<>();

        char[] charArray = s.toCharArray();

        for (char c : charArray) {
            if (c == '(' || c == '{' || c == '[') queue.add(c);
            else if (c == ')' && (queue.isEmpty() || !(queue.pop() == '('))) return false;
            else if (c == '}' && (queue.isEmpty() || !(queue.pop() == '{'))) return false;
            else if (c == ']' && (queue.isEmpty() || !(queue.pop() == '['))) return false;
        }
        return queue.isEmpty();
    }

    //21. 合并两个有序链表
    @SuppressWarnings("all")
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;
        ListNode p = list1;
        ListNode last = new ListNode(-1, list1);
        list1 = last;
        while (true) {
            if (p == null) {
                last.next = list2;
                break;
            }
            if (list2 == null) break;

            if (list2.val < p.val) {
                ListNode next = list2.next;

                last.next = list2;

                last = list2;
                list2.next = p;
                list2 = next;
            } else {
                last = p;
                p = p.next;
            }
        }
        return list1.next;
    }

    @SuppressWarnings("all")
    //22. 括号生成
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateParenthesisDfs(result, new StringBuilder(), n, 0);
        return result;
    }

    private void generateParenthesisDfs(List<String> result, StringBuilder sb, int n, int add) {
        if (n == 0) {
            sb.append(")".repeat(Math.max(0, add)));
            result.add(sb.toString());
            sb.delete(sb.length() - Math.max(0, add), sb.length());
            return;
        }

        if (add > 0) {
            sb.append(')');
            generateParenthesisDfs(result, sb, n, add - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append('(');
        generateParenthesisDfs(result, sb, n - 1, add + 1);
        sb.deleteCharAt(sb.length() - 1);
    }


    @SuppressWarnings("all")
    //23. 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int count = 0;
        int now = 0;
        int length = lists.length;
        while (true) {
            while (now < length) {
                ListNode left = lists[now++];
                ListNode right = now < length ? lists[now++] : null;
                lists[count++] = mergeTwoLists(left, right);
            }
            now = 0;
            length = count;
            if (count == 1) break;
            else count = 0;
        }

        return lists[0];
    }

    @SuppressWarnings("all")
    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = new ListNode(-1, head);
        head = last;
        ListNode left = head.next;

        ListNode right = left.next;

        while (true) {
            last.next = right;
            left.next = right.next;
            right.next = left;

            last = left;
            left = left.next;
            if (left == null || left.next == null) break;
            right = left.next;
        }

        return head.next;
    }

}