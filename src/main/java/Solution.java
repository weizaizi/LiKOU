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

    private final char[] intToRomanArray1 = {'I', 'X', 'C', 'M'};
    private final char[] intToRomanArray2 = {'V', 'L', 'D'};

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
    private final char[] romanToIntArray1 = {'I', 'X', 'C', 'M'};
    private final char[] romanToIntArray2 = {'V', 'L', 'D'};

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


    @SuppressWarnings("all")
    //25. K 个一组翻转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 0 || k == 1) return head;
        head = new ListNode(-1, head);
        ListNode p = head;
        while (p != null) {
            ListNode t = p;
            ListNode temp = p.next;
            int i = 0;
            while (i != k) {
                i++;
                p = p.next;
                if (p == null) return head.next;
            }

            reverseGroup(t, p);

            p = temp;
        }

        return head.next;

    }

    private void reverseGroup(ListNode head, ListNode tail) {
        ListNode end = tail.next;
        ListNode t = head.next;
        ListNode last = head.next;
        ListNode p = last.next;

        while (p != end) {
            ListNode next = p.next;

            p.next = last;
            last = p;
            p = next;
        }

        head.next = last;
        t.next = end;
    }

    //26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int j = 1;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != num) {
                nums[j++] = nums[i];
                num = nums[i];
            }
        }

        return j;
    }

    //27. 移除元素
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int num : nums) if (val != num) nums[i++] = num;
        return i;
    }

    //28. 找出字符串中第一个匹配项的下标
    public int strStr(String haystack, String needle) {
        char[] needleCharArray = needle.toCharArray();
        char[] haystackCharArray = haystack.toCharArray();
        int[] kmpNext = strStrKmpNext(needleCharArray);

        int i = 0;
        int j = 0;

        while (i < haystackCharArray.length) {

            if (needleCharArray[j] == haystackCharArray[i]) {
                j++;
                i++;
            } else {
                if (j > 0) j = kmpNext[j - 1];
                else i++;
            }

            if (j == needleCharArray.length) return i - needleCharArray.length;
        }

        return -1;
    }

    private int[] strStrKmpNext(char[] charArray) {
        int[] result = new int[charArray.length];
        int j = 0;
        for (int i = 1; i < charArray.length - 1; i++) {
            while (j > 0 && charArray[i] != charArray[j]) j = result[j - 1];

            if (charArray[i] == charArray[j]) j++;

            result[i] = j;
        }
        return result;
    }

    //29. 两数相除
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;

        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            else return -dividend;
        }

        if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }

        if (divisor == Integer.MAX_VALUE) {
            if (dividend == Integer.MIN_VALUE) return -1;
            else if (dividend == Integer.MAX_VALUE) return 1;
            else return 0;
        }
        boolean f = false;
        if (dividend > 0) dividend = -dividend;
        else f = true;
        if (divisor > 0) divisor = -divisor;
        else f = !f;

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(divisor);
        int index = 0;

        while (arrayList.get(index) >= dividend - arrayList.get(index)) arrayList.add(arrayList.get(index++) << 1);
        int result = 0;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (dividend > divisor) break;

            int num = arrayList.get(size);

            if (num >= dividend) {
                dividend = dividend - num;
                result += (1 << size);
            }
        }

        return f ? -result : result;
    }

    @SuppressWarnings("all")
    //30. 串联所有单词的子串
    public List<Integer> findSubstring(String s, String[] words) {
        int n = words.length;
        int m = words[0].length();

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            if (n * m + i > s.length()) break;
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String word = s.substring(j * m + i, i + j * m + m);
                hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
            }

            for (String word : words) {
                hashMap.put(word, hashMap.getOrDefault(word, 0) - 1);
                if (hashMap.get(word) == 0) hashMap.remove(word);
            }

            for (int start = i; start <= s.length() - n * m; start += m) {
                if (start != i) {
                    String word = s.substring(n * m + start - m, start + n * m);
                    hashMap.put(word, hashMap.getOrDefault(word, 0) + 1);
                    if (hashMap.get(word) == 0) hashMap.remove(word);

                    word = s.substring(start - m, start);
                    hashMap.put(word, hashMap.getOrDefault(word, 0) - 1);
                    if (hashMap.get(word) == 0) hashMap.remove(word);
                }

                if (hashMap.isEmpty()) result.add(start);
            }

        }

        return result;
    }

    @SuppressWarnings("all")
    //31. 下一个排列
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return;
        int i;
        for (i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) break;
            if (i == 0) {
                Arrays.sort(nums);
                return;
            }
        }

        int j;
        for (j = nums.length - 1; j > i; j--) {
            if (nums[j] > nums[i]) break;
        }

        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
        j = nums.length - 1;
        i++;

        while (i < j) {
            t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }

    //32. 最长有效括号
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) return 0;
        char[] charArray = s.toCharArray();
        int[] dp = new int[charArray.length + 1];

        int result = 0;
        for (int i = 2; i < dp.length; i++) {
            if (charArray[i - 1] == ')') {
                if (charArray[i - 2] == '(') dp[i] = dp[i - 2] + 2;
                else if (i - 2 - dp[i - 1] >= 0 && charArray[i - 2 - dp[i - 1]] == '(')
                    dp[i] = dp[i - 1] + dp[i - 2 - dp[i - 1]] + 2;
            }
            result = Integer.max(result, dp[i]);
        }

        return result;
    }

    //33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        if (nums[0] == target) return 0;
        if (nums.length == 1) return -1;
        int n = nums.length;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                n = i;
                break;
            }
        }
        int i;
        int j;
        if (n == nums.length) {
            i = 0;
            j = nums.length - 1;
        } else if (target <= nums[0]) {
            j = nums.length - 1;
            i = n;
        } else {
            i = 0;
            j = n - 1;
        }
        int mid;
        while (i <= j) {
            mid = (i + j) / 2;
            if (target == nums[mid]) return mid;
            if (target < nums[mid]) j = mid - 1;
            else i = mid + 1;
        }

        return -1;
    }

    //34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target > nums[nums.length - 1] || target < nums[0]) return new int[]{-1, -1};
        int[] result = new int[2];
        int start = 0;
        int end = nums.length - 1;
        int i = start;
        int j = end;
        int mid = 0;
        while (i <= j) {
            mid = (i + j) / 2;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] > target) {
                end = j;
                j = mid - 1;
            } else {
                start = i;
                i = mid + 1;
            }
        }
        if (i > j) return new int[]{-1, -1};

        int f = start;
        int l = mid;

        while (f <= l) {
            mid = (f + l) / 2;
            if (nums[mid] == target && (mid == 0 || nums[mid] > nums[mid - 1])) {
                result[0] = mid;
                break;
            } else if (nums[mid] >= target) {
                l = mid - 1;
            } else {
                f = mid + 1;
            }
        }

        f = mid;
        l = end;
        while (f <= l) {
            mid = (f + l) / 2;
            if (nums[mid] == target && (mid == nums.length - 1 || nums[mid] < nums[mid + 1])) {
                result[1] = mid;
                break;
            } else if (nums[mid] > target) {
                l = mid - 1;
            } else {
                f = mid + 1;
            }
        }

        return result;
    }

    //35. 搜索插入位置
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        int mid = 0;

        while (i <= j) {
            mid = (i + j) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) j = mid - 1;
            else i = mid + 1;
        }

        return target > nums[mid] ? mid + 1 : mid;
    }

    //36. 有效的数独
    public boolean isValidSudoku(char[][] board) {
        boolean[][] line = new boolean[9][9];
        boolean[][] block = new boolean[9][9];
        boolean[][] column = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - 1 - '0';

                if (line[i][num]) return false;
                else line[i][num] = true;

                if (column[num][j]) return false;
                else column[num][j] = true;

                int indexRow = i / 3 * 3 + num / 3;
                int indexColumn = j / 3 * 3 + num % 3;
                if (block[indexRow][indexColumn]) return false;
                else block[indexRow][indexColumn] = true;
            }
        }

        return true;
    }

    //37.解数独
    private final int[] solveSudokuLine = new int[9];
    private final int[] solveSudokuColumn = new int[9];
    private final int[][] solveSudokuBlock = new int[3][3];
    private final ArrayList<int[]> solveSudokuList = new ArrayList<>();
    private boolean solveSudokuValid = false;

    @SuppressWarnings("all")
    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') solveSudokuList.add(new int[]{i, j});
                else solveSudokuFlip(board[i][j] - '0' - 1, i, j);
            }
        }

        solveSudokuDfs(board, 0);
    }

    private void solveSudokuDfs(char[][] board, int pos) {
        if (solveSudokuList.size() == pos) {
            solveSudokuValid = true;
            return;
        }

        int[] space = solveSudokuList.get(pos);

        int i = space[0];
        int j = space[1];

        int mask = ~(solveSudokuLine[i] | solveSudokuColumn[j] | solveSudokuBlock[i / 3][j / 3]) & 0x1ff;

        for (; !solveSudokuValid && mask != 0; mask &= (mask - 1)) {
            int digitMask = mask & (-mask);
            int digit = Integer.bitCount(digitMask - 1);

            solveSudokuFlip(digit, i, j);

            board[i][j] = (char) (digit + '0' + 1);

            solveSudokuDfs(board, pos + 1);

            solveSudokuFlip(digit, i, j);
        }
    }

    private void solveSudokuFlip(int num, int i, int j) {
        solveSudokuLine[i] ^= 1 << num;
        solveSudokuColumn[j] ^= 1 << num;
        solveSudokuBlock[i / 3][j / 3] ^= 1 << num;
    }

    //38. 外观数列
    public String countAndSay(int n) {
        return countAndSayDfs(n, new StringBuilder("1"));
    }

    private String countAndSayDfs(int n, StringBuilder last) {
        if (n == 1) return last.toString();

        char[] charArray = last.toString().toCharArray();

        last = new StringBuilder();

        char chars = charArray[0];
        int num = 1;
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] != chars) {
                last.append(num);
                last.append(chars);

                chars = charArray[i];
                num = 1;
                continue;
            }
            num++;
        }

        last.append(num);
        last.append(chars);
        return countAndSayDfs(n - 1, last);
    }

    //39. 组合总和
    private final List<List<Integer>> combinationSumList = new ArrayList<>();

    @SuppressWarnings("all")
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSumDfs(candidates, target, 0, 0, new ArrayList<>());
        return combinationSumList;
    }

    private void combinationSumDfs(int[] candidates, int target, int index, int last, List<Integer> list) {
        if (last == target) {
            combinationSumList.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];

            last += num;
            if (last > target) return;
            list.add(num);
            combinationSumDfs(candidates, target, i, last, list);
            list.removeLast();
            last -= num;
        }
    }

    //40. 组合总和 II
    private final List<List<Integer>> combinationSum2List = new ArrayList<>();

    @SuppressWarnings("all")
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        combinationSum2Dfs(candidates, target, 0, 0, new ArrayList<>());
        return combinationSum2List;
    }

    private void combinationSum2Dfs(int[] candidates, int target, int index, int last, List<Integer> list) {
        if (target == last) {
            combinationSum2List.add(new ArrayList<>(list));
            return;
        }

        int l = -1;
        for (int i = index; i < candidates.length; i++) {
            int num = candidates[i];
            if (num == l) continue;

            l = num;
            last += num;
            if (last > target) return;
            list.add(num);
            combinationSum2Dfs(candidates, target, i + 1, last, list);
            list.removeLast();
            last -= num;
        }

    }

    //41. 缺失的第一个正数
    public int firstMissingPositive(int[] nums) {
        if (nums.length == 1 && nums[0] == 1) return 2;
        if (nums.length == 1) return 1;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length || i == nums[i] - 1 || nums[nums[i] - 1] == nums[i]) continue;

            firstMissingPositiveSwap(nums, i, nums[i] - 1);
            i--;

        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        return nums.length + 1;
    }

    private void firstMissingPositiveSwap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    //42. 接雨水
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int ans = 0;
        while (left <= right) {
            leftMax = Integer.max(leftMax, height[left]);
            rightMax = Integer.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                ans += (leftMax - height[left]);
                left++;
            } else {
                ans += (rightMax - height[right]);
                right--;
            }
        }

        return ans;
    }

    //43. 字符串相乘
    public String multiply(String num1, String num2) {
        if (num1.length() == 1) {
            if (num1.equals("0")) return "0";
            if (num1.equals("1")) return num2;
        }
        if (num2.length() == 1) {
            if (num2.equals("0")) return "0";
            if (num2.equals("1")) return num1;
        }
        int[] result = new int[num1.length() + num2.length()];

        char[] num1CharArray = num1.toCharArray();
        char[] num2CharArray = num2.toCharArray();

        for (int i = num1CharArray.length - 1; i >= 0; i--) {
            int charNum1 = num1CharArray[i] - '0';
            for (int j = num2CharArray.length - 1; j >= 0; j--) {
                int charNum2 = num2CharArray[j] - '0';
                multiplyAdd(result.length - 1 - i - j, result, charNum1 * charNum2);
            }
        }
        if (result[0] == 0) result = Arrays.copyOfRange(result, 1, result.length);
        StringBuilder builder = new StringBuilder();
        for (int i : result) builder.append(i);
        return builder.toString();

    }

    private void multiplyAdd(int start, int[] result, int add) {

        result[result.length - start] += add;
        for (int j = result.length - start - 1; j > 0; j--) {
            if (result[j + 1] < 10) return;

            result[j] += (result[j + 1] / 10);
            result[j + 1] %= 10;
        }
    }

    //44. 通配符匹配
    public boolean isMatch44(String s, String p) {
        if (p.equals("*")) return true;
        if (p.isEmpty() && !s.isEmpty()) return false;
        if (p.isEmpty()) return true;

        String[] split = p.split("\\*");
        if (split.length == 0) return true;
        if (split.length == 1 && p.charAt(0) != '*' && p.charAt(p.length() - 1) != '*') {

            if (split[0].length() != s.length()) return false;
            for (int i = 0; i < s.length(); i++)
                if (split[0].charAt(i) != s.charAt(i) && split[0].charAt(i) != '?') return false;

            return true;
        }

        int first = 0;
        int last = split.length;

        int start = 0;
        int end = s.length();

        if (p.charAt(0) != '*') {
            String p1 = split[0];
            if (p1.length() > s.length()) return false;
            for (int i = 0; i < p1.length(); i++) {
                if (p1.charAt(i) != s.charAt(i) && p1.charAt(i) != '?') return false;
            }
            first++;
            start = p1.length();
        }


        if (p.charAt(p.length() - 1) != '*' && first < last) {
            String pLast = split[split.length - 1];
            if (pLast.length() > s.length()) return false;
            for (int i = 0; i < pLast.length(); i++) {
                if (s.length() - i - 1 < start) return false;
                char c = s.charAt(s.length() - i - 1);
                char c1 = pLast.charAt(pLast.length() - i - 1);

                if (c1 != c && c1 != '?') return false;
            }
            last--;
            end = s.length() - pLast.length();
        }

        while (first < last) {
            int n = start;
            int i = 0;
            int j = start;
            String p1 = split[first];
            while (i < p1.length()) {
                if (j == end) return false;
                if (p1.charAt(i) == s.charAt(j) || p1.charAt(i) == '?') {
                    i++;
                    j++;
                    continue;
                }

                j = n + 1;
                n++;
                i = 0;

            }
            start = j;

            first++;

            if (start > end) return false;
        }
        return true;
    }


    //45. 跳跃游戏 II
    public int jump(int[] nums) {
        int end = 0;
        int max = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Integer.max(max, i + nums[i]);
            if (end == i) {
                step++;
                end = max;
            }
        }
        return step;
    }


    @SuppressWarnings("all")
    //46. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        permuteDfs(nums, result, list, nums.length);
        return result;
    }

    private void permuteDfs(int[] nums, List<List<Integer>> result, List<Integer> list, int n) {
        if (n == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = nums.length - n; i < nums.length; i++) {
            Collections.swap(list, nums.length - n, i);

            permuteDfs(nums, result, list, n - 1);

            Collections.swap(list, nums.length - n, i);
        }
    }

    @SuppressWarnings("all")
    //47. 全排列 II
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        permuteUniqueDfs(result, new ArrayList<>(), nums, 0, new boolean[nums.length]);
        return result;
    }

    private void permuteUniqueDfs(List<List<Integer>> result, List<Integer> list, int[] nums, int index, boolean[] visit) {
        if (index == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visit[i] || (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1])) continue;

            list.add(nums[i]);

            visit[i] = true;
            permuteUniqueDfs(result, list, nums, index + 1, visit);
            visit[i] = false;

            list.removeLast();
        }
    }

    @SuppressWarnings("all")
    //48. 旋转图像
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                rotateSwap(matrix, i, j, n - j - 1, n - i - 1);
            }
        }

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                rotateSwap(matrix, i, j, n - i - 1, j);
            }
        }
    }

    private void rotateSwap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int t = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = t;
    }

    //49. 字母异位词分组
    @SuppressWarnings("all")
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        HashMap<String, List<String>> re = new HashMap<>();

        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String s = new String(charArray);

            List<String> list = re.get(s);
            if (list == null) {
                list = new ArrayList<>();

                result.add(list);
            }

            re.put(s, list);
            list.add(str);
        }
        return result;
    }

    //50. Pow(x, n)
    public double myPow(double x, int n) {
        return n >= 0 ? myPowPow(x, n) : 1 / myPowPow(x, n);
    }

    private double myPowPow(double x, int n) {
        long m = Math.abs((long) n);

        if (m == 0) return 1;
        if (m == 1) return x;

        double pow = myPowPow(x, n / 2);

        return m % 2 == 0 ? pow * pow : pow * pow * x;
    }

    //51. N 皇后
    @SuppressWarnings("all")
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        solveNQueensDfs(result, list, n, new boolean[n], new boolean[n * 2 - 1], new boolean[n * 2 - 1]);
        return result;
    }

    private void solveNQueensDfs(List<List<String>> result, List<String> list, int n, boolean[] chosen, boolean[] z, boolean[] f) {
        if (list.size() == n) {
            result.add(new ArrayList<>(list));
            return;
        }

        char[] s = new char[n];
        s[0] = 'Q';
        for (int i = 1; i < n; i++) s[i] = '.';

        for (int i = 0; i < n; i++) {
            if (chosen[i]) continue;

            if (z[i + list.size()] || f[i - list.size() + n - 1]) continue;
            else {
                z[i + list.size()] = true;
                f[i - list.size() + n - 1] = true;
            }

            chosen[i] = true;

            solveNQueensSwap(s, i);
            list.add(new String(s));

            solveNQueensDfs(result, list, n, chosen, z, f);

            list.removeLast();
            z[i + list.size()] = false;
            f[i - list.size() + n - 1] = false;
            solveNQueensSwap(s, i);
            chosen[i] = false;
        }
    }

    private void solveNQueensSwap(char[] s, int j) {
        char t = s[0];
        s[0] = s[j];
        s[j] = t;
    }

    //52. N 皇后 II
    private int totalNQueensResult;

    public int totalNQueens(int n) {
        totalNQueensResult = 0;
        totalNQueensDfs(0, n, new boolean[n], new boolean[n * 2 - 1], new boolean[n * 2 - 1]);
        return totalNQueensResult;
    }

    private void totalNQueensDfs(int num, int n, boolean[] chosen, boolean[] z, boolean[] f) {
        if (num == n) {
            totalNQueensResult++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (chosen[i]) continue;
            if (z[i + num] || f[i - num + n - 1]) continue;
            else {
                z[i + num] = true;
                f[i - num + n - 1] = true;
            }
            chosen[i] = true;
            num++;
            totalNQueensDfs(num, n, chosen, z, f);
            num--;
            z[i + num] = false;
            f[i - num + n - 1] = false;
            chosen[i] = false;
        }
    }

    //53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            sum += num;
            ans = Math.max(ans, sum);
            if (sum < 0) sum = 0;
        }

        return ans;
    }

    @SuppressWarnings("all")
    //54. 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < Integer.min(m / 2, n / 2); i++) {
            for (int j = i; j < n - i; j++) result.add(matrix[i][j]);

            for (int j = i + 1; j < m - i; j++) result.add(matrix[j][n - i - 1]);

            for (int j = n - i - 2; j >= i; j--) result.add(matrix[m - i - 1][j]);

            if (i != n / 2) for (int j = m - i - 2; j > i; j--) result.add(matrix[j][i]);
        }

        if (m % 2 == 1 && n >= m) for (int i = m / 2; i < n - m / 2; i++) result.add(matrix[m / 2][i]);


        if (n % 2 == 1 && n < m) for (int i = n / 2; i < m - n / 2; i++) result.add(matrix[i][n / 2]);

        return result;
    }

    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (maxLength >= nums.length - 1) return true;


            int n = nums[i];

            if (maxLength == i && n == 0) return false;

            maxLength = Integer.max(maxLength, n + i);
        }

        return true;
    }

    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        mergeQuickSort(intervals, 0, intervals.length - 1);
        int j = 0;
        int[] last = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > last[1]) {
                intervals[j] = last;
                j++;
                last = intervals[i];
                continue;
            }

            last = new int[]{last[0], Integer.max(last[1], intervals[i][1])};
        }

        intervals[j] = last;
        j++;

        return Arrays.copyOfRange(intervals, 0, j);
    }

    private void mergeQuickSort(int[][] intervals, int start, int end) {
        if (end <= start) return;
        int i = start + 1;
        int j = end;

        int n = intervals[start][0];
        while (i <= j) {
            while (i <= j && intervals[i][0] <= n) i++;

            while (i <= j && intervals[j][0] > n) j--;

            if (i < j) {
                mergeShift(intervals, i, j);
                i++;
                j--;
            }
        }

        mergeShift(intervals, start, j);

        mergeQuickSort(intervals, start, j - 1);
        mergeQuickSort(intervals, j + 1, end);
    }

    private void mergeShift(int[][] intervals, int i, int j) {
        int[] t = intervals[i];
        intervals[i] = intervals[j];
        intervals[j] = t;
    }

    //57. 插入区间
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) return new int[][]{newInterval};

        int i = 0;
        int j = 0;
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) i++;
            if (interval[0] <= newInterval[1]) j++;
        }
        if (i >= intervals.length) {
            int[][] result = new int[intervals.length + 1][];
            System.arraycopy(intervals, 0, result, 0, intervals.length);
            result[intervals.length] = newInterval;
            return result;
        }
        if (j == 0) {
            int[][] result = new int[intervals.length + 1][];
            System.arraycopy(intervals, 0, result, 1, intervals.length);
            result[0] = newInterval;
            return result;
        }
        int[] array = new int[]{Integer.min(intervals[i][0], newInterval[0]), Integer.max(intervals[j - 1][1], newInterval[1])};

        int[][] result = new int[intervals.length - j + i + 1][];
        System.arraycopy(intervals, 0, result, 0, i);
        result[i] = array;
        System.arraycopy(intervals, j, result, i + 1, result.length - i - 1);
        return result;
    }

    //58. 最后一个单词的长度
    public int lengthOfLastWord(String s) {
        char[] charArray = s.toCharArray();
        int result = 0;
        boolean end = true;
        for (int i = charArray.length - 1; i >= 0; i--) {
            if (charArray[i] != ' ') {
                end = false;
                result++;
            }

            if (charArray[i] == ' ' && end) continue;
            if (charArray[i] == ' ') break;
        }

        return result;
    }

    //59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int end = n * n;

        int direction = 0;

        int m = n;

        int row = 0;
        int column = 0;

        int k = 0;

        for (int i = 1; i <= end; i++) {
            k++;
            if (k == m) {
                if (direction == 3) {
                    column++;
                    row++;
                    m -= 2;
                }
                direction = (direction + 1) % 4;
                k = 1;
            }

            result[row][column] = i;

            if (direction == 0) column++;
            if (direction == 1) row++;
            if (direction == 2) column--;
            if (direction == 3) row--;


        }

        return result;
    }

    //60. 排列序列
    public String getPermutation(int n, int k) {
        List<Integer> list = new ArrayList<>();
        int num = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            num *= i;
        }

        num /= n;
        StringBuilder sb = new StringBuilder();
        k--;

        for (int i = n - 1; i >= 1; i--) {
            if (k == 0) break;
            if (num > k) {
                sb.append(list.removeFirst());
                num /= i;
                continue;
            }

            sb.append(list.remove(k / num));
            k = k - (k / num) * num;
            num /= i;
        }

        for (Integer i : list) {
            sb.append(i);
        }


        return sb.toString();
    }

    @SuppressWarnings("all")
    //61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        if (k == 0) return head;
        int len = 0;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }
        ListNode end = p;
        len++;
        k = k % len;
        if (k == 0) return head;

        int l = len - k;
        p = head;
        for (int i = 0; i < l - 1; i++) p = p.next;
        end.next = head;
        head = p.next;
        p.next = null;
        return head;
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int sum = m + n - 2;
        int c = Integer.min(m, n) - 1;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= c; i++) {
            list.add(i);
        }
        int result = 1;
        for (int i = sum; i > sum - c; i--) {
            result *= i;
            int len = list.size();

            for (int j = len - 1; j >= 0; j--) {
                int num = list.get(j);

                if (result % num == 0) {
                    result /= num;
                    list.remove(j);
                }
            }
        }

        return result;
    }

    //63. 不同路径 II
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }

                if (i > 0 && obstacleGrid[i - 1][j] == 1) dp[j] = 0;

                if (j > 0 && obstacleGrid[i][j - 1] == 0) dp[j] += dp[j - 1];
            }
        }

        return dp[n - 1];

    }

    //64. 最小路径和
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i] + grid[0][i];
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j > 1) dp[j] = Integer.min(dp[j], dp[j - 1]) + grid[i - 1][j - 1];
                else dp[j] = dp[j] + grid[i - 1][j - 1];
            }
        }

        return dp[n];
    }

    //65. 有效数字
    public boolean isNumber(String s) {

        char[] charArray = s.toCharArray();

        int start = 0;
        boolean point = false;
        boolean e = false;
        boolean needNumber = true;
        boolean number = false;
        if (charArray[0] == '+' || charArray[0] == '-') {
            start++;
        }
        if (start < charArray.length && (charArray[start] == 'e' || charArray[start] == 'E')) return false;

        for (int i = start; i < charArray.length; i++) {
            char c = charArray[i];

            if (c >= '0' && c <= '9') {
                needNumber = false;
                number = true;
                continue;
            } else if (c == '.' && !point) {
                point = true;
                continue;
            } else if ((c == 'e' || c == 'E') && !e) {
                if ((charArray[i - 1] < '0' || charArray[i - 1] > '9') && !number) return false;
                if (i == charArray.length - 1) return false;
                if (charArray[i + 1] == '+' || charArray[i + 1] == '-') {
                    if (i == charArray.length - 2) return false;
                    i++;
                }
                needNumber = true;
                e = true;
                point = true;
                continue;
            }
            return false;
        }

        if (point && !number) return false;
        return !needNumber;
    }

    //66. 加一
    public int[] plusOne(int[] digits) {
        int i = digits.length - 1;
        while (true) {
            digits[i] += 1;
            if (digits[i] == 10) {
                digits[i] = 0;
                i--;
            } else break;
            if (i == -1) {
                int[] result = new int[digits.length + 1];
                result[0] = 1;
                System.arraycopy(digits, 0, result, 1, digits.length);
                return result;
            }
        }

        return digits;
    }

    //67. 二进制求和
    public String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;

        StringBuilder sb = new StringBuilder();
        int add = 0;

        while (i >= 0 || j >= 0) {
            if (i >= 0) add += (a.charAt(i--) - '0');
            if (j >= 0) add += (b.charAt(j--) - '0');
            sb.append(add % 2);
            add /= 2;
        }

        if (add == 1) sb.append(1);

        return sb.reverse().toString();
    }

    @SuppressWarnings("all")
    //68. 文本左右对齐
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>();
        int len = 0;
        for (String word : words) {
            if (word.length() + len > maxWidth) {
                //单词个数
                int size = queue.size();
                if (size == 1) {
                    String remove = queue.remove();
                    int space = maxWidth - remove.length();
                    result.add(remove + " ".repeat(space));
                } else {
                    //空格总数
                    int spaceNum = maxWidth - (len - size);
                    //前几个需要比别人多一个空格
                    int one = spaceNum % (size - 1);
                    //每个单词需要的字母数量
                    int singleCharSpace = spaceNum / (size - 1);
                    StringBuilder sb = new StringBuilder();
                    String space = " ".repeat(Math.max(0, singleCharSpace));
                    sb.append(queue.remove());
                    for (int i = 0; i < one; i++) {
                        sb.append(" ").append(space).append(queue.remove());
                    }

                    for (String s : queue) {
                        sb.append(space).append(s);
                    }
                    queue.clear();
                    result.add(sb.toString());
                }
                len = 0;
            }

            len += word.length() + 1;
            queue.add(word);
        }
        StringBuilder sb = new StringBuilder();
        if (!queue.isEmpty()) {
            if (queue.size() == 1) {
                String remove = queue.remove();
                int space = maxWidth - remove.length();
                result.add(remove + " ".repeat(space));
            } else {
                for (String s : queue) {
                    sb.append(s).append(" ");
                }
                int space = maxWidth - len;
                sb.append(" ".repeat(Math.max(0, space)));
                if (sb.length() > maxWidth) sb.deleteCharAt(sb.length() - 1);
                result.add(sb.toString());
            }
        } else {
            String last = result.removeLast();
            len = 0;
            String[] split = last.split(" ");
            for (String s : split) {
                len += s.length() + 1;
                sb.append(s).append(" ");
            }
            int space = maxWidth - len;
            sb.append(" ".repeat(Math.max(0, space)));
            if (sb.length() > maxWidth) sb.deleteCharAt(sb.length() - 1);
            result.add(sb.toString());
        }

        return result;
    }

    //69. x 的平方根
    public int mySqrt(int x) {
        if (x == 0 || x == 1) return x;

        int i = 0;
        int j = x / 2;
        int mid;

        while (i <= j) {
            mid = (i + j) / 2;
            long left = (long) mid * mid;
            long right = (long) (mid + 1) * (mid + 1);

            if (left <= x && x < right) return mid;

            if (left > x) j = mid - 1;
            if (right <= x) i = mid + 1;
        }
        return -1;
    }

    //70. 爬楼梯
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] f = new int[n];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f[n - 1];
    }

    //71. 简化路径
    public String simplifyPath(String path) {
        String[] pathArray = path.split("/");

        ArrayList<String> p = new ArrayList<>();

        for (String s : pathArray) {
            if (s.equals(".") || s.isEmpty()) continue;
            if (s.equals("..")) {
                if (!p.isEmpty()) p.removeLast();
                continue;
            }
            p.add(s);
        }
        StringBuilder sb = new StringBuilder();
        if (p.isEmpty()) return "/";
        for (String s : p) {
            sb.append("/").append(s);
        }
        return sb.toString();
    }

    //72. 编辑距离
    public int minDistance(String word1, String word2) {
        if (word1.isEmpty()) return word2.length();
        if (word2.isEmpty()) return word1.length();
        char[] word1CharArray = word1.toCharArray();
        char[] word2CharArray = word2.toCharArray();

        int[][] dp = new int[word1CharArray.length + 1][word2CharArray.length + 1];
        for (int i = 1; i < word2CharArray.length + 1; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i < word1CharArray.length + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < word1CharArray.length + 1; i++)
            for (int j = 1; j < word2CharArray.length + 1; j++) {
                dp[i][j] = Integer.min(dp[i][j - 1], dp[i - 1][j]) + 1;
                if (word1CharArray[i - 1] == word2CharArray[j - 1]) dp[i][j] = Integer.min(dp[i - 1][j - 1], dp[i][j]);
                else dp[i][j] = Integer.min(dp[i - 1][j - 1] + 1, dp[i][j]);
            }

        return dp[word1CharArray.length][word2CharArray.length];
    }

    @SuppressWarnings("all")
    //73. 矩阵置零
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] column = new boolean[m];
        boolean[] row = new boolean[n];

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0) column[i] = row[j] = true;

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (column[i] || row[j]) matrix[i][j] = 0;
    }

    //74. 搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix.length * matrix[0].length;

        int mid;
        while (i < j) {
            mid = (i + j) / 2;
            int num = matrix[mid / matrix[0].length][mid % matrix[0].length];
            if (target == num) return true;
            else if (target > num) i = mid + 1;
            else j = mid;
        }

        return false;
    }

    @SuppressWarnings("all")
    //75. 颜色分类
    public void sortColors(int[] nums) {
        int p0 = 0;
        int p1 = nums.length - 1;

        for (int i = 0; i < nums.length; i++) {
            while (p1 >= i && nums[i] == 2) sortColorsSwap(nums, i, p1--);

            if (nums[i] == 0) sortColorsSwap(nums, i, p0++);
        }
    }

    private void sortColorsSwap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    //76. 最小覆盖子串
    public String minWindow(String s, String t) {
        int start = 0;
        int end = Integer.MAX_VALUE;
        int[] array = new int[128];
        int need = 0;
        for (int i = 0; i < t.length(); i++) {
            array[t.charAt(i)]++;
        }

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (array[c]-- > 0) need++;

            while (need == t.length()) {
                if (end - start > i - j) {
                    start = j;
                    end = i;
                }
                if (++array[s.charAt(j++)] > 0) need--;
            }
        }

        if (end == Integer.MAX_VALUE) return "";
        else return s.substring(start, end + 1);
    }

    @SuppressWarnings("all")
    //77. 组合
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combineDfs(k, n, 1, result, new ArrayList<>());
        return result;
    }

    private void combineDfs(int k, int max, int min, List<List<Integer>> result, List<Integer> list) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = min; i <= max; i++) {
            if (max - i + 1 < k) return;

            list.add(i);
            combineDfs(k - 1, max, i + 1, result, list);
            list.removeLast();
        }
    }

    @SuppressWarnings("all")
    //78. 子集
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetsDfs(nums, 0, result, new ArrayList<>());
        return result;
    }

    private void subsetsDfs(int[] nums, int start, List<List<Integer>> result, List<Integer> list) {
        if (nums.length == start) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            subsetsDfs(nums, i + 1, result, list);
            list.removeLast();
        }
        subsetsDfs(nums, nums.length, result, list);
    }

    //79. 单词搜索
    public boolean exist(char[][] board, String word) {
        char[] charArray = word.toCharArray();
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDfs(used, board, i, j, charArray, 0)) return true;
            }
        }
        return false;
    }

    private boolean existDfs(boolean[][] used, char[][] board, int i, int j, char[] word, int k) {

        if (k == word.length) return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false;

        if (used[i][j]) return false;

        if (board[i][j] != word[k]) return false;

        used[i][j] = true;
        k++;

        if (existDfs(used, board, i + 1, j, word, k)) return true;
        if (existDfs(used, board, i - 1, j, word, k)) return true;
        if (existDfs(used, board, i, j + 1, word, k)) return true;
        if (existDfs(used, board, i, j - 1, word, k)) return true;

        used[i][j] = false;
        return false;
    }

    //80. 删除有序数组中的重复项 II
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        int len = 0;
        int last = nums[0];
        for (int num : nums) {
            if (num == last) {
                if (len < 2) {
                    len++;
                    nums[i++] = num;
                }
            } else {
                len = 1;
                nums[i++] = num;
                last = num;
            }
        }

        return i;
    }

    //81. 搜索旋转排序数组 II
    public boolean search2(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        if (target >= nums[0]) {
            while (i < nums.length) {
                if (target == nums[i]) {
                    return true;
                }
                if (target < nums[i] || nums[i] < nums[0]) {
                    return false;
                }
                i++;
            }
        }
        if (target <= nums[nums.length - 1]) {
            while (j >= 0) {
                if (target == nums[j]) {
                    return true;
                }

                if (target > nums[j] || nums[j] > nums[nums.length - 1]) {
                    return false;
                }
                j--;
            }
        }
        return false;
    }

    @SuppressWarnings("all")
    //82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        head = new ListNode(-101, head);
        ListNode last = head;
        ListNode p = last.next;

        while (p != null && p.next != null) {
            int val = p.val;
            if (val == p.next.val) {
                while (p != null && p.val == val) p = p.next;
                last.next = p;
            } else {
                last = last.next;
                p = p.next;
            }
        }

        return head.next;
    }

    @SuppressWarnings("all")
    //83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode last = head;
        ListNode p = head.next;

        while (p != null) {
            if (p.val != last.val) {
                last.next = p;
                last = p;
            }
            p = p.next;
        }

        last.next = null;

        return head;
    }

    //84.柱状图中最大的矩形
    public int largestRectangleArea(int[] heights) {
        int result = 0;

        int[] re = new int[heights.length];
        int k = -1;
        for (int i = 0; i < heights.length; i++) {
            while (k != -1 && heights[i] < heights[re[k]]) {
                int r = re[k--];
                result = Integer.max(result, heights[r] * (i - (k == -1 ? -1 : re[k]) - 1));
            }
            re[++k] = i;
        }

        while (k != -1) {
            int r = re[k--];
            result = Integer.max(result, heights[r] * (heights.length - (k == -1 ? -1 : re[k]) - 1));
        }

        return result;
    }

    //85. 最大矩形
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] array = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            array[0][i] = matrix[0][i] - '0';
        }
        int result = largestRectangleArea(array[0]);
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') array[i][j] = array[i - 1][j] + 1;
            }
            result = Integer.max(result, largestRectangleArea(array[i]));
        }

        return result;
    }

    @SuppressWarnings("all")
    //86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        ListNode big = new ListNode(0, null);
        ListNode bigP = big;
        ListNode small = new ListNode(0, null);
        ListNode smallP = small;

        while (p != null) {
            if (p.val >= x) {
                bigP.next = p;
                bigP = p;
            } else {
                smallP.next = p;
                smallP = p;
            }
            p = p.next;
        }

        head = small.next;
        if (head != null) smallP.next = big.next;
        else head = big.next;
        bigP.next = null;
        return head;
    }

    //87. 扰乱字符串
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        //第一个参数是s1的起始位置，第二个是s2，第三个是长度
        //0 未计算 1 是 2 不是
        int[][][] memory = new int[len][len][len + 1];
        return isScrambleDfs(s1.toCharArray(), 0, s2.toCharArray(), 0, len, memory);
    }

    public boolean isScrambleDfs(char[] s1, int s1Start, char[] s2, int s2Start, int len, int[][][] memory) {
        if (memory[s1Start][s2Start][len] != 0) return memory[s1Start][s2Start][len] == 1;

        if (len == 1) {
            memory[s1Start][s2Start][len] = s1[s1Start] == s2[s2Start] ? 1 : 2;
            return memory[s1Start][s2Start][len] == 1;
        }

        if (len == 2) {
            memory[s1Start][s2Start][len] = (s1[s1Start] == s2[s2Start] && s1[s1Start + 1] == s2[s2Start + 1] || s1[s1Start] == s2[s2Start + 1] && s1[s1Start + 1] == s2[s2Start]) ? 1 : 2;
            return memory[s1Start][s2Start][len] == 1;
        }

        for (int i = 1; i < len; i++) {
            memory[s1Start][s2Start][len] = (isScrambleDfs(s1, s1Start, s2, s2Start, i, memory) && isScrambleDfs(s1, s1Start + i, s2, s2Start + i, len - i, memory) || isScrambleDfs(s1, s1Start, s2, s2Start + len - i, i, memory) && isScrambleDfs(s1, s1Start + i, s2, s2Start, len - i, memory)) ? 1 : 2;
            if (memory[s1Start][s2Start][len] == 1) return true;
        }

        return false;
    }

    @SuppressWarnings("all")
    //88. 合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        int now = n + m - 1;
        int i = m - 1;
        int j = n - 1;
        while (now >= 0) {
            if (i < 0) {
                nums1[now--] = nums2[j--];
                continue;
            }
            if (j < 0) {
                nums1[now--] = nums1[i--];
                continue;
            }
            if (nums1[i] > nums2[j]) nums1[now--] = nums1[i--];
            else nums1[now--] = nums2[j--];
        }
    }

    @SuppressWarnings("all")
    //89. 格雷编码
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int num = 1 << n;
        for (int i = 0; i < num; i++) {
            result.add((i >> 1) ^ i);
        }
        return result;
    }

    @SuppressWarnings("all")
    //90. 子集 II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupDfs(nums, new boolean[nums.length], 0, result, new ArrayList<>());
        return result;
    }

    private void subsetsWithDupDfs(int[] nums, boolean[] used, int start, List<List<Integer>> result, List<Integer> list) {
        if (start == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        subsetsWithDupDfs(nums, used, start + 1, result, list);
        if (start != 0 && nums[start] == nums[start - 1] && !used[start - 1]) return;
        list.add(nums[start]);
        used[start] = true;
        subsetsWithDupDfs(nums, used, start + 1, result, list);
        used[start] = false;
        list.removeLast();
    }

    //91. 解码方法
    public int numDecodings(String s) {
        int len = s.length();

        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (int i = 2; i <= len; i++) {
            if (dp[i - 1] == 0) return 0;
            char c = s.charAt(i - 1);
            char last = s.charAt(i - 2);
            if (last == '0') {
                if (c == '0') return 0;
                dp[i] = dp[i - 1];
                continue;
            }
            if (c == '0') {
                if (last == '1' || last == '2') dp[i] = dp[i - 2];
                continue;
            }
            int n = (last - '0') * 10 + c - '0';
            if (n <= 26 && n >= 1) dp[i] = dp[i - 1] + dp[i - 2];
            else dp[i] = dp[i - 1];
        }

        return dp[len];
    }

    @SuppressWarnings("all")
    //92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        head = new ListNode(-1, head);

        ListNode leftToRight;

        ListNode pLeft = head;

        ListNode pRight;
        for (int i = 0; i < left - 1; i++) {
            pLeft = pLeft.next;
        }

        leftToRight = pLeft;
        pLeft = pLeft.next;
        pRight = pLeft != null ? pLeft.next : null;

        int go = right - left;

        ListNode next;

        for (int i = 0; i < go; i++) {
            next = pRight.next;
            pRight.next = pLeft;
            pLeft = pRight;
            pRight = next;
        }

        if (leftToRight.next != null) leftToRight.next.next = pRight;

        leftToRight.next = pLeft;

        return head.next;
    }

    @SuppressWarnings("all")
    //93. 复原 IP 地址
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) return new ArrayList<>();
        List<String> result = new ArrayList<>();
        restoreIpAddressesDfs(result, new StringBuilder(), 0, s, 0);
        return result;
    }

    //0 - 还可以继续增加长度 ， 1 - 只有上一级不可以增加，2 - 上述都不可以再增加长度
    private int restoreIpAddressesDfs(List<String> result, StringBuilder sb, int start, String s, int num) {
        //收集结果
        if (num == 4 && start == s.length()) {
            result.add(sb.toString());
            return 0;
        }
        // 如果已经遍历到了字符串的末尾，则返回1
        if (start == s.length()) return 0;
        //如果需要的最少位数比剩余的少，则返回1
        if (4 - num > s.length() - start) return 1;
        //如果需要的最多位数比剩余的多，则返回0
        if ((4 - num) * 3 < s.length() - start) return 0;
        int begin = sb.length();

        if (num != 0) sb.append('.');
        //如果是最后一次，则剩余的都要加入
        if (num == 3) {
            //处理最后一次前导0
            if (s.length() - start != 1 && s.charAt(start) == '0') {
                sb.delete(begin, sb.length());
                return 0;
            }
            if (s.length() - start == 3) {
                int ip = Integer.parseInt(s.substring(start));
                if (ip > 255) {
                    sb.delete(begin, sb.length());
                    return 0;
                }
            }
            sb.append(s.substring(start));
            int re = restoreIpAddressesDfs(result, sb, s.length(), s, 4);
            sb.delete(begin, sb.length());
            return re;
        }

        if (s.charAt(start) == '0') {
            sb.append(0);
            restoreIpAddressesDfs(result, sb, start + 1, s, num + 1);
            sb.delete(begin, sb.length());
            return 0;
        }
        for (int i = 0; i < 2; i++) {
            if (start >= s.length()) {
                sb.delete(begin, sb.length());
                return 0;
            }
            sb.append(s.charAt(start++));
            int re = restoreIpAddressesDfs(result, sb, start, s, num + 1);
            if (re == 1) {
                sb.delete(begin, sb.length());
                return 0;
            }
            if (re == 2) {
                sb.delete(begin, sb.length());
                return 2;
            }
        }
        sb.delete(begin, sb.length());
        if (num != 0) sb.append('.');
        start++;
        if (start > s.length()) return 0;
        int ip = Integer.parseInt(s.substring(start - 3, start));
        if (ip > 255) {
            sb.delete(begin, sb.length());
            return 0;
        }
        sb.append(ip);
        restoreIpAddressesDfs(result, sb, start, s, num + 1);
        sb.delete(begin, sb.length());
        return 0;
    }

    @SuppressWarnings("all")
    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        inorderTraversalDfs(root, result);
        return result;
    }

    private void inorderTraversalDfs(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) return;
        inorderTraversalDfs(treeNode.left, result);
        result.add(treeNode.val);
        inorderTraversalDfs(treeNode.right, result);
    }


    //95. 不同的二叉搜索树 II
    @SuppressWarnings("all")
    private ArrayList<TreeNode>[][] generateTreesArrayLists;

    @SuppressWarnings("all")
    public List<TreeNode> generateTrees(int n) {
        generateTreesArrayLists = new ArrayList[n][n];
        return generateTreesDfs(1, n);
    }


    private List<TreeNode> generateTreesDfs(int start, int end) {
        if (start > end) return new ArrayList<>();
        if (generateTreesArrayLists[start - 1][end - 1] != null) return generateTreesArrayLists[start - 1][end - 1];
        List<TreeNode> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = generateTreesDfs(start, i - 1);
            List<TreeNode> rightNodes = generateTreesDfs(i + 1, end);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    result.add(new TreeNode(i, leftNode, rightNode));
                }
            }
            if (leftNodes.isEmpty() && !rightNodes.isEmpty()) {
                for (TreeNode rightNode : rightNodes) {
                    result.add(new TreeNode(i, null, rightNode));
                }
            }

            if (rightNodes.isEmpty() && !leftNodes.isEmpty()) {
                for (TreeNode leftNode : leftNodes) {
                    result.add(new TreeNode(i, leftNode, null));
                }
            }

            if (rightNodes.isEmpty() && leftNodes.isEmpty()) result.add(new TreeNode(i, null, null));
        }

        return result;
    }

    //96. 不同的二叉搜索树
    private int[][] numTreesArray;

    public int numTrees(int n) {
        numTreesArray = new int[n + 1][n + 1];
        return numTreesDfs(1, n);
    }

    private int numTreesDfs(int start, int end) {
        if (start > end) return 1;
        if (numTreesArray[start][end] != 0) return numTreesArray[start][end];
        int result = 0;
        for (int i = start; i <= end; i++) {
            int add;
            int left = numTreesDfs(start, i - 1);
            int right = numTreesDfs(i + 1, end);
            add = left * right;
            result += add;
        }
        numTreesArray[start][end] = result;
        return result;
    }

    //97. 交错字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        if (l1 + l2 != s3.length()) return false;

        boolean[] dp = new boolean[l2 + 1];
        dp[0] = true;

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                int p = i + j - 1;
                if (i > 0) dp[j] &= (s1.charAt(i - 1) == s3.charAt(p));
                if (j > 0) dp[j] |= (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));

            }
        }

        return dp[l2];
    }

    //98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return isValidBSTDfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTDfs(TreeNode treeNode, long min, long max) {
        if (treeNode == null) return true;
        int val = treeNode.val;
        if (val >= max || val <= min) return false;
        return isValidBSTDfs(treeNode.left, min, val) && isValidBSTDfs(treeNode.right, val, max);
    }

    @SuppressWarnings("all")
    //99. 恢复二叉搜索树
    public void recoverTree(TreeNode root) {
        TreeNode x = null;
        TreeNode y = null;
        TreeNode pre = null;
        TreeNode predecessor;

        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) predecessor = predecessor.right;
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    if (pre != null && pre.val > root.val) {
                        y = root;
                        if (x == null) x = pre;
                    }
                    pre = root;
                    predecessor.right = null;
                    root = root.right;
                }
            } else {
                if (pre != null && pre.val > root.val) {
                    y = root;
                    if (x == null) x = pre;
                }
                pre = root;
                root = root.right;
            }
        }

        int t = x.val;
        x.val = y.val;
        y.val = t;
    }

    //100. 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricRecursion(root.left, root.right);
    }

    private boolean isSymmetricRecursion(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetricRecursion(left.left, right.right) && isSymmetricRecursion(left.right, right.left);
    }

    @SuppressWarnings("all")
    //102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode remove = queue.remove();
                list.add(remove.val);
                if (remove.left != null) queue.add(remove.left);
                if (remove.right != null) queue.add(remove.right);
            }
            result.add(list);
        }

        return result;
    }

    @SuppressWarnings("all")
    //103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        boolean left = true;
        stack.add(root);

        while (!stack.isEmpty()) {
            Stack<TreeNode> newStack = new Stack<>();
            List<Integer> list = new ArrayList<>();
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = stack.pop();
                list.add(pop.val);
                if (left) {
                    if (pop.left != null) newStack.add(pop.left);
                    if (pop.right != null) newStack.add(pop.right);
                } else {
                    if (pop.right != null) newStack.add(pop.right);
                    if (pop.left != null) newStack.add(pop.left);
                }
            }

            stack = newStack;
            left = !left;
            result.add(list);
        }

        return result;
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Integer.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    @SuppressWarnings("all")
    //105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeDfs(preorder, 0, preorder.length, inorder, 0);
    }

    private TreeNode buildTreeDfs(int[] preorder, int begin, int len, int[] inorder, int left) {
        if (len == 0) return null;

        int num = preorder[begin];
        int l = 0;
        int repeat = len + left;
        for (int i = left; i < repeat; i++) {
            if (num == inorder[i]) {
                l = i - left;
                break;
            }
        }

        return new TreeNode(num, buildTreeDfs(preorder, begin + 1, l, inorder, left), buildTreeDfs(preorder, begin + 1 + l, len - l - 1, inorder, left + 1 + l));
    }

    @SuppressWarnings("all")
    //105. 从前序与中序遍历序列构造二叉树
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return buildTree2Dfs(inorder, 0, inorder.length, postorder, postorder.length - 1);
    }

    private TreeNode buildTree2Dfs(int[] inorder, int begin, int len, int[] postorder, int right) {
        if (len == 0) return null;

        int num = postorder[right];
        int l;
        int i = begin;
        while (inorder[i] != num) i++;
        l = i - begin;
        return new TreeNode(num, buildTree2Dfs(inorder, begin, l, postorder, right - (len - l)), buildTree2Dfs(inorder, begin + l + 1, len - l - 1, postorder, right - 1));
    }

    @SuppressWarnings("all")
    //107. 二叉树的层序遍历 II
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = levelOrder(root);
        Collections.reverse(result);
        return result;
    }

    @SuppressWarnings("all")
    //108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTRecursion(0, nums.length, nums);
    }

    private TreeNode sortedArrayToBSTRecursion(int start, int end, int[] nums) {
        if (start >= end) return null;
        int mid = (start + end) / 2;
        return new TreeNode(nums[mid], sortedArrayToBSTRecursion(start, mid, nums), sortedArrayToBSTRecursion(mid + 1, end, nums));
    }

    //109. 有序链表转换二叉搜索树
    @SuppressWarnings("all")
    public TreeNode sortedListToBST(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        return sortedListToBSTDfs(head, len);
    }

    private TreeNode sortedListToBSTDfs(ListNode root, int len) {
        if (len == 0 || root == null) return null;
        int mid = len / 2;
        ListNode last = root;
        for (int i = 0; i < mid; i++) root = root.next;

        return new TreeNode(root.val, sortedListToBSTDfs(last, mid), sortedListToBSTDfs(root.next, len - mid - 1));
    }

    //110. 平衡二叉树
    public boolean isBalanced(TreeNode root) {
        return !(isBalancedDfs(root) == -1);
    }

    private int isBalancedDfs(TreeNode root) {
        if (root == null) return 0;
        int left = isBalancedDfs(root.left);
        if (left == -1) return -1;
        int right = isBalancedDfs(root.right);
        if (right == -1) return -1;
        int result = Math.abs(left - right);
        return result >= 2 ? -1 : Math.max(left, right) + 1;
    }

    //111. 二叉树的最小深度
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right != null) return Integer.min(minDepth(root.left), minDepth(root.right)) + 1;
        else if (root.left != null) return minDepth(root.left) + 1;
        else return minDepth(root.right) + 1;
    }

    //112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return hasPathSumDfs(root, targetSum);
    }

    private boolean hasPathSumDfs(TreeNode treeNode, int targetSum) {
        if (treeNode == null) return targetSum == 0;
        if (treeNode.left != null && treeNode.right != null)
            return hasPathSumDfs(treeNode.left, targetSum - treeNode.val) || hasPathSumDfs(treeNode.right, targetSum - treeNode.val);
        else if (treeNode.left != null) return hasPathSumDfs(treeNode.left, targetSum - treeNode.val);
        else return hasPathSumDfs(treeNode.right, targetSum - treeNode.val);
    }

    @SuppressWarnings("all")
    //113. 路径总和 II
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        pathSumDfs(root, targetSum, result, new ArrayList<>());
        return result;
    }

    private void pathSumDfs(TreeNode treeNode, int targetSum, List<List<Integer>> result, List<Integer> list) {
        if (treeNode == null) {
            if (targetSum == 0) result.add(new ArrayList<>(list));
            return;
        }
        list.add(treeNode.val);
        targetSum -= treeNode.val;
        if (treeNode.left != null && treeNode.right != null) {
            pathSumDfs(treeNode.left, targetSum, result, list);
            pathSumDfs(treeNode.right, targetSum, result, list);
        } else if (treeNode.right == null) pathSumDfs(treeNode.left, targetSum, result, list);
        else pathSumDfs(treeNode.right, targetSum, result, list);

        list.removeLast();
    }

    @SuppressWarnings("all")
    //114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        TreeNode predecessor;
        while (root != null) {
            if (root.left != null) {
                predecessor = root.left;

                while (predecessor.right != null && predecessor.right != root) predecessor = predecessor.right;

                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                } else {
                    TreeNode temp = predecessor.right;
                    predecessor.right = temp.right;
                    temp.right = temp.left;
                    temp.left = null;
                }
            } else {
                root = root.right;
            }
        }
    }

    //115. 不同的子序列
    public int numDistinct(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int sLen = sChar.length;
        int tLen = tChar.length;
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i <= sLen; i++) dp[i][0] = 1;
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (sChar[i - 1] == tChar[j - 1]) dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                else dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[sLen][tLen];
    }

    @SuppressWarnings("all")
    //116. 填充每个节点的下一个右侧节点指针
    public ThreeTreeNode connect(ThreeTreeNode root) {
        ThreeTreeNode result = root;
        ThreeTreeNode head = root;
        while (head != null) {
            while (root != null) {
                if (root.left != null) root.left.next = root.right;
                ThreeTreeNode right = root.right;
                root = root.next;
                if (root != null && right != null) right.next = root.left;
            }
            root = head.left;

            head = root;
        }

        return result;
    }

    @SuppressWarnings("all")
    //117. 填充每个节点的下一个右侧节点指针 II
    public ThreeTreeNode connect2(ThreeTreeNode root) {
        ThreeTreeNode p = root;
        ThreeTreeNode head = root;
        while (head != null) {
            while (p != null) {
                ThreeTreeNode left = null;
                while (p != null && left == null) {
                    if (p.left != null && p.left.next == null) left = p.left;
                    if (left == null) {
                        left = p.right;
                        p = p.next;
                    }
                }

                ThreeTreeNode right = null;
                while (p != null && right == null) {
                    if (p.left != left) right = p.left;
                    if (right == null) {
                        right = p.right;
                        if (right == null) p = p.next;
                    }
                }

                if (left != null) left.next = right;
            }

            while (p == null && head != null) {
                p = head.left;
                if (p == null) p = head.right;
                head = head.next;
            }

            head = p;
        }

        return root;
    }

    @SuppressWarnings("all")
    //118. 杨辉三角
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        result.add(a);
        for (int i = 2; i <= numRows; i++) {
            List<Integer> last = result.getLast();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i - 1; j++) list.add(last.get(j) + last.get(j - 1));
            list.add(1);
            result.add(new ArrayList<>(list));
        }
        return result;
    }

    @SuppressWarnings("all")
    //119. 杨辉三角 II
    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        for (int i = 2; i <= rowIndex + 1; i++) {
            List<Integer> last = list;
            list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i - 1; j++) list.add(last.get(j) + last.get(j - 1));
            list.add(1);
        }
        return list;
    }

    @SuppressWarnings("all")
    //120. 三角形最小路径和
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        int n = triangle.get(m - 1).size();
        int[][] dp = new int[2][n + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        Arrays.fill(dp[1], Integer.MAX_VALUE);
        dp[0][1] = triangle.getFirst().getFirst();
        for (int i = 2; i < m + 1; i++) {
            List<Integer> list = triangle.get(i - 1);
            for (int j = 1; j <= i; j++) {
                dp[1][j] = Integer.min(dp[0][j], dp[0][j - 1]) + list.get(j - 1);
            }
            dp[0] = dp[1];
            dp[1] = new int[n + 1];
            Arrays.fill(dp[1], Integer.MAX_VALUE);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) min = Integer.min(dp[0][i], min);
        return min;
    }

    //121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int min = prices[0];
        int result = 0;
        for (int price : prices) {
            min = Integer.min(min, price);
            result = Integer.max(result, price - min);
        }
        return result;
    }

    //122. 买卖股票的最佳时机 II
    public int maxProfit2(int[] prices) {
        if (prices.length == 1) return 0;
        int result = 0;
        int now;
        if (prices[1] < prices[0]) now = -1;
        else now = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i - 1]) {
                if (now != -1) result += prices[i - 1] - now;
                now = prices[i];
            }
        }

        result += prices[prices.length - 1] - now;
        return result;
    }

    //123. 买卖股票的最佳时机 III
    public int maxProfit3(int[] prices) {
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2 = 0;


        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            buy1 = Integer.max(buy1, -price);
            sell1 = Integer.max(sell1, price + buy1);

            buy2 = Integer.max(buy2, sell1 - price);
            sell2 = Integer.max(sell2, price + buy2);
        }

        return sell2;
    }

    //124. 二叉树中的最大路径和
    private int maxPathSumResult;

    public int maxPathSum(TreeNode root) {
        maxPathSumResult = root.val;
        maxPathSumDfs(root);
        return maxPathSumResult;
    }

    private int maxPathSumDfs(TreeNode treeNode) {
        if (treeNode == null) return 0;

        int left = maxPathSumDfs(treeNode.left);
        int right = maxPathSumDfs(treeNode.right);

        int mid = left + right;
        //两边加中间
        maxPathSumResult = Integer.max(maxPathSumResult, mid + treeNode.val);

        int max = Math.max(left, right);

        if (max != 0) maxPathSumResult = Integer.max(maxPathSumResult, max + Math.max(0, treeNode.val));

        return Math.max(max + treeNode.val, 0);
    }

    //125. 验证回文串
    public boolean isPalindrome(String s) {
        char[] sCharArray = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j) {
                if (sCharArray[i] >= 'A' && sCharArray[i] <= 'Z') {
                    sCharArray[i] += 32;
                    break;
                }
                if ((sCharArray[i] > 'z' || sCharArray[i] < 'a') && (sCharArray[i] > '9' || sCharArray[i] < '0')) i++;
                else break;
            }

            while (i < j) {

                if (sCharArray[j] >= 'A' && sCharArray[j] <= 'Z') {
                    sCharArray[j] += 32;
                    break;
                }
                if ((sCharArray[j] > 'z' || sCharArray[j] < 'a') && (sCharArray[j] > '9' || sCharArray[j] < '0')) j--;
                else break;
            }

            if (i < j && sCharArray[i++] != sCharArray[j--]) return false;
        }
        return true;
    }

    @SuppressWarnings("all")
    //126. 单词接龙 II
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        //结果
        List<List<String>> result = new ArrayList<>();
        //词典
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return result;
        words.remove(beginWord);

        int len = beginWord.length();
        //记录需要继续演变的单词
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        //记录是否发现
        boolean found = false;

        //记录单词由哪个单词演变过来
        HashMap<String, List<String>> hashWord = new HashMap<>();

        //记录单词演变的速度
        HashMap<String, Integer> hashStep = new HashMap<>();
        hashStep.put(beginWord, 0);

        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                char[] need = poll.toCharArray();

                for (int j = 0; j < len; j++) {
                    char origin = need[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        need[j] = c;
                        String s = new String(need);
                        //如果有其他单词演变过且步骤和这次一样 则加入需求
                        if (hashStep.containsKey(s) && hashStep.get(s) == step) hashWord.get(s).add(poll);
                        //如果词典中没有则继续
                        if (!words.contains(s)) continue;
                        words.remove(s);
                        queue.add(s);

                        hashWord.putIfAbsent(s, new ArrayList<>());

                        hashWord.get(s).add(poll);
                        hashStep.put(s, step);
                        if (s.equals(endWord)) found = true;
                    }
                    need[j] = origin;
                }
            }
            step++;
            if (found) break;
        }
        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            findLaddersDfs(result, path, hashWord, beginWord, endWord);
        }

        return result;
    }

    private void findLaddersDfs(List<List<String>> result, List<String> list, HashMap<String, List<String>> hashWord, String beginWord, String cur) {
        if (cur.equals(beginWord)) {
            result.add(new ArrayList<>(list));
            return;
        }

        List<String> stringList = hashWord.get(cur);
        for (String s : stringList) {
            list.addFirst(s);
            findLaddersDfs(result, list, hashWord, beginWord, s);
            list.removeFirst();
        }
    }

    @SuppressWarnings("all")
    //127. 单词接龙
    //todo 优化
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> words = new HashSet<>(wordList);
        if (!words.contains(endWord)) return 0;
        int len = beginWord.length();
        int step = 0;
        words.remove(beginWord);
        Queue<String> queue = new ArrayDeque<>();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) return step;
                ArrayList<String> deleted = new ArrayList<>();
                for (String word : words) {
                    int diff = 0;
                    for (int j = 0; j < len; j++) {
                        if (word.charAt(j) != poll.charAt(j)) diff++;
                    }
                    if (diff == 1) {
                        deleted.add(word);
                        queue.add(word);
                    }
                }

                for (String s : deleted) {
                    words.remove(s);
                }
                words.remove(poll);
            }
        }

        return 0;
    }

    //128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;
        int result = 0;

        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);

        for (int num : nums) {
            if (!set.contains(num)) continue;
            set.remove(num);
            int now = 1;

            int add = 1;
            while (set.contains(add + num)) {
                set.remove(add + num);
                now++;
                add++;
            }

            add = -1;

            while (set.contains(add + num)) {
                set.remove(add + num);
                now++;
                add--;
            }

            result = Integer.max(result, now);
        }

        return result;
    }

    //129. 求根节点到叶节点数字之和
    public int sumNumbers(TreeNode root) {
        return sumNumbersDfs(root, 0);
    }

    private int sumNumbersDfs(TreeNode treeNode, int sum) {
        if (treeNode == null) return 0;

        sum = sum * 10 + treeNode.val;
        if (treeNode.left == null && treeNode.right == null) return sum;

        return sumNumbersDfs(treeNode.left, sum) + sumNumbersDfs(treeNode.right, sum);
    }

    @SuppressWarnings("all")
    //130. 被围绕的区域
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O') solveDfs(board, 0, i);
        }
        for (int i = 0; i < n; i++) {
            if (board[i][m - 1] == 'O') solveDfs(board, i, m - 1);
        }

        for (int i = 0; i < m; i++) {
            if (board[n - 1][i] == 'O') solveDfs(board, n - 1, i);
        }

        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O') solveDfs(board, i, 0);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'a') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    private void solveDfs(char[][] board, int i, int j) {
        board[i][j] = 'a';

        if (i > 0 && board[i - 1][j] == 'O') solveDfs(board, i - 1, j);
        if (i < board.length - 1 && board[i + 1][j] == 'O') solveDfs(board, i + 1, j);
        if (j > 0 && board[i][j - 1] == 'O') solveDfs(board, i, j - 1);
        if (j < board[0].length - 1 && board[i][j + 1] == 'O') solveDfs(board, i, j + 1);
    }

    @SuppressWarnings("all")
    //131. 分割回文串
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        char[] sCharArray = s.toCharArray();
        int len = sCharArray.length;
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int i = len - 1; i >= 0; i--) {
            if (i + 1 < dp.length) dp[i][i + 1] = sCharArray[i] == sCharArray[i + 1];
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = sCharArray[i] == sCharArray[j] && dp[i + 1][j - 1];
            }
        }
        partitionDfs(result, new ArrayList<>(), sCharArray, dp, 0);
        return result;
    }

    private void partitionDfs(List<List<String>> result, List<String> list, char[] sCharArray, boolean[][] dp, int start) {
        if (start == dp.length) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i < dp.length; i++) {
            if (!dp[start][i]) continue;

            list.add(String.copyValueOf(sCharArray, start, i - start + 1));
            partitionDfs(result, list, sCharArray, dp, i + 1);
            list.removeLast();
        }
    }

    //132. 分割回文串 II
    public int minCut(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];

        for (int i = 0; i < len; i++) Arrays.fill(dp[i], true);

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }

        int[] arr = new int[len];

        for (int i = 1; i < len; i++) {
            if (dp[0][i]) continue;
            arr[i] = arr[i - 1] + 1;
            for (int j = i - 1; j > 0; j--) {
                if (!dp[j][i]) continue;

                arr[i] = Integer.min(arr[i], arr[j - 1] + 1);
            }
        }

        return arr[len - 1];
    }

    //2561. 重排水果（八月二日每日一题）
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> diff = new TreeMap<>();
        int min = Integer.MAX_VALUE;
        for (int i : basket1) {
            diff.put(i, diff.getOrDefault(i, 0) + 1);
            min = Integer.min(min, i);
        }

        for (int i : basket2) {
            diff.put(i, diff.getOrDefault(i, 0) - 1);
            min = Integer.min(min, i);
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : diff.entrySet()) {
            int value = Math.abs(entry.getValue());
            if (value % 2 != 0) return -1;
            int repeat = value / 2;
            for (int i = 0; i < repeat; i++) {
                arrayList.add(entry.getKey());
            }
        }

        Collections.sort(arrayList);
        long result = 0;
        int size = arrayList.size() / 2;
        for (int i = 0; i < size; i++) {
            result += Integer.min(arrayList.get(i), 2 * min);
        }

        return result;
    }

    @SuppressWarnings("all")
    //133. 克隆图
    public GraphNode cloneGraph(GraphNode graphNode) {
        if (graphNode == null) return null;
        Map<Integer, GraphNode> map = new HashMap<>();
        GraphNode n = new GraphNode(graphNode.val, new ArrayList<>());
        map.put(graphNode.val, n);
        cloneGraphDfs(map, graphNode, n);
        return n;
    }

    private void cloneGraphDfs(Map<Integer, GraphNode> map, GraphNode p, GraphNode n) {
        for (GraphNode neighbor : p.neighbors) {
            if (map.containsKey(neighbor.val)) {
                n.neighbors.add(map.get(neighbor.val));
                continue;
            }
            GraphNode k = new GraphNode(neighbor.val, new ArrayList<>());
            map.put(neighbor.val, k);
            n.neighbors.add(k);
        }

        int size = p.neighbors.size();
        for (int i = 0; i < size; i++) {
            if (!map.get(p.neighbors.get(i).val).neighbors.isEmpty()) continue;
            cloneGraphDfs(map, p.neighbors.get(i), n.neighbors.get(i));
        }
    }

    //134. 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            gas[i] -= cost[i];
        }

        int sum = 0;
        int need = 0;
        int i = 0;
        int index = 0;
        while (i < gas.length) {
            sum += gas[i];
            i++;
            if (sum < 0) {
                while (i < gas.length && gas[i] < 0) {
                    sum += gas[i];
                    i++;
                }
                index = i;
                need += Math.abs(sum);
                sum = 0;
            }
        }

        return sum >= need ? index : -1;

    }

    //135. 分发糖果
    public int candy(int[] ratings) {
        int result = 1;
        int now = 1;
        int i = 1;
        int dec = 0;
        int last;
        while (i < ratings.length) {
            if (ratings[i] >= ratings[i - 1]) {
                now = ratings[i] == ratings[i - 1] ? 1 : now + 1;
                dec = 0;
                result += now;
                i++;
            } else {
                last = now;
                while (i < ratings.length && ratings[i] < ratings[i - 1]) {
                    dec++;
                    i++;
                }
                now = 1;
                result += ((1 + dec) * dec) / 2;
                result += Integer.max(0, dec - last + 1);
            }
        }
        return result;
    }

    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int single = 0;

        for (int num : nums) {
            single = single ^ num;
        }
        return single;
    }

    //137. 只出现一次的数字 II
    public int singleNumber2(int[] nums) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            ans += (total % 3 == 1 ? 1 : 0) << i;
        }

        return ans;
    }

    @SuppressWarnings("all")
    //138. 随机链表的复制
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        for (Node p = head; p != null; p = p.next.next) {
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
        }

        for (Node p = head; p != null; p = p.next.next) {
            Node next = p.next;
            next.random = p.random == null ? null : p.random.next;
        }

        Node newHead = head.next;
        for (Node p = head; p != null; p = p.next) {
            Node newNode = p.next;
            p.next = p.next.next;
            newNode.next = newNode.next == null ? null : newNode.next.next;
        }

        return newHead;
    }

    @SuppressWarnings("all")
    //139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        int len = s.length();

        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }


    @SuppressWarnings("all")
    //140. 单词拆分 II
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        wordBreak2Dfs(result, s, new StringBuilder(), 0, 1, new HashSet<>(wordDict));
        return result;
    }

    private void wordBreak2Dfs(List<String> result, String s, StringBuilder sb, int start, int end, HashSet<String> wordDict) {
        if (end == s.length() + 1) {
            if (start == s.length()) result.add(sb.deleteCharAt(sb.length() - 1).toString());
            return;
        }

        if (end - start > 10) return;
        String newString = s.substring(start, end);
        if (!wordDict.contains(newString)) wordBreak2Dfs(result, s, sb, start, end + 1, wordDict);
        else {
            int len = sb.length();
            sb.append(newString).append(" ");
            wordBreak2Dfs(result, s, sb, end, end + 1, wordDict);
            sb.delete(len, sb.length());
            wordBreak2Dfs(result, s, sb, start, end + 1, wordDict);
        }
    }

    @SuppressWarnings("all")
    //141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean result = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                result = true;
                break;
            }
        }

        return result;
    }

    @SuppressWarnings("all")
    //142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    @SuppressWarnings("all")
    //143. 重排链表
    public void reorderList(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            len++;
            p = p.next;
        }

        int repeat = len / 2;

        p = head;
        for (int i = 0; i < repeat; i++) p = p.next;

        ListNode last = p;
        ListNode now = p.next;
        while (now != null) {
            ListNode next = now.next;
            now.next = last;
            last = now;
            now = next;
        }

        ListNode before = head;
        ListNode after = last;

        while (before.next != after && before != after) {
            ListNode lastNext = after.next;
            after.next = before.next;
            before.next = after;
            after = lastNext;
            before = before.next.next;
        }

        p.next = null;
    }


    @SuppressWarnings("all")
    //144. 二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            ans.add(root.val);
            stack.add(root);
            if (root.left != null) root = root.left;
            else {
                do root = stack.pop().right; while (!stack.isEmpty() && root == null);
            }
        }

        return ans;
    }

    @SuppressWarnings("all")
    //145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null) {
            int val = root.val;
            stack.add(root);
            if (root.left != null) root = root.left;
            else {
                do root = stack.pop().right; while (!stack.isEmpty() && root == null);
            }

            ans.add(val);
        }

        return ans;
    }

    @SuppressWarnings("all")
    //147. 对链表进行插入排序
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }

    //148. 排序链表
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(next);

        return sortListMerge(left, right);
    }

    private ListNode sortListMerge(ListNode left, ListNode right) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (left != null && right != null) {
            if (left.val > right.val) {
                p.next = right;
                right = right.next;
            } else {
                p.next = left;
                left = left.next;
            }
            p = p.next;
        }

        if (left != null) p.next = left;
        if (right != null) p.next = right;

        return head.next;
    }

    //191. 位1的个数
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n &= (n - 1);
            ans++;
        }
        return ans;
    }

    //231. 2 的幂
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    //326. 3 的幂
    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) n /= 3;
        return n == 1;
    }

    //342. 4的幂
    public boolean isPowerOfFour(int n) {
        return (n & (n - 1)) == 0 && n % 3 == 1;
    }

    //371. 两整数之和
    public int getSum(int a, int b) {
        if (b == 0) return a;
        return getSum((a ^ b), ((a & b) << 1));
    }

    @SuppressWarnings("all")
    //461. 汉明距离
    public int hammingDistance(int x, int y) {
        x ^= y;
        return hammingWeight(x);
    }

    //693. 交替位二进制数
    public boolean hasAlternatingBits(int n) {
        int a = n ^ (n >> 1);
        return (a & (a + 1)) == 0;
    }

    //904. 水果成篮（8.4每日一题）
    public int totalFruit(int[] fruits) {
        int i = 0;
        int j = 1;
        int[][] basket = new int[2][2];
        basket[0][0] = fruits[0];
        basket[0][1] = 1;
        while (j < fruits.length && fruits[j] == basket[0][0]) {
            basket[0][1]++;
            j++;
        }
        if (j < fruits.length) {
            basket[1][0] = fruits[j];
            basket[1][1] = 1;
            j++;
        }
        int ans = basket[0][1] + basket[1][1];

        while (j < fruits.length) {
            if (fruits[j] == basket[0][0]) basket[0][1]++;
            else if (fruits[j] == basket[1][0]) basket[1][1]++;
            else {
                ans = Integer.max(ans, basket[0][1] + basket[1][1]);
                while (basket[0][1] != 0 && basket[1][1] != 0) {
                    if (basket[0][0] == fruits[i]) basket[0][1]--;
                    else if (basket[1][0] == fruits[i]) basket[1][1]--;
                    i++;
                }

                if (basket[0][1] == 0) {
                    basket[0][1] = 1;
                    basket[0][0] = fruits[j];
                } else {
                    basket[1][1] = 1;
                    basket[1][0] = fruits[j];
                }
            }

            j++;
        }
        ans = Integer.max(ans, basket[0][1] + basket[1][1]);
        return ans;
    }

    //1863. 找出所有子集的异或总和再求和
    public int subsetXORSum(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < 1 << len; i++) {
            int ans = 0;
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) ans ^= nums[j];
            }
            sum += ans;
        }
        return sum;
    }

    //2106. 摘水果（8.3每日一题）
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int index = 0;
        int after = 0;
        int before = 0;
        int result;
        int now = 0;
        for (int i = 0; i < fruits.length; i++) {
            int[] fruit = fruits[i];
            if (fruit[0] < startPos) {
                before++;
            } else if (fruit[0] == startPos) now = fruit[1];
            else {
                if (startPos + k >= fruit[0]) {
                    now += fruit[1];
                    index = i;
                    after++;
                } else break;
            }
        }
        before--;
        result = now;

        while (before >= 0 && after != 0 && k >= Integer.min(startPos - fruits[before][0], fruits[index][0] - startPos) + fruits[index][0] - fruits[before][0]) {
            now += fruits[before--][1];
            result = Integer.max(result, now);
        }

        while (before >= 0 && after > 0) {
            now -= fruits[index--][1];
            after--;

            while (before >= 0 && after > 0 && k >= Integer.min(startPos - fruits[before][0], fruits[index][0] - startPos) + fruits[index][0] - fruits[before][0]) {
                now += fruits[before--][1];
                result = Integer.max(result, now);
            }
        }

        while (before >= 0 && k >= startPos - fruits[before][0]) {
            now += fruits[before--][1];
            result = Integer.max(result, now);
        }


        return result;
    }

    //3477. 水果成篮 II(8.5每日一题)
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int ans = 0;
        int bLen = baskets.length;
        boolean[] used = new boolean[bLen];
        for (int fruit : fruits) {
            for (int j = 0; j < bLen; j++) {
                if (used[j]) continue;
                if (baskets[j] >= fruit) {
                    used[j] = true;
                    break;
                }
            }
        }

        for (boolean b : used) {
            if (!b) ans++;
        }
        return ans;
    }

    //面试题 05.01. 插入
    public int insertBits(int N, int M, int i, int j) {
        int mask = (0xffffffff << j << 1) + ((1 << i) - 1);
        return N & mask | M << i;
    }

    //面试题 16.01. 交换数字
    public int[] swapNumbers(int[] numbers) {
        numbers[0] = numbers[0] + numbers[1];
        numbers[1] = numbers[0] - numbers[1];
        numbers[0] = numbers[0] - numbers[1];
        return numbers;
    }
}