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
        return null;
    }
}