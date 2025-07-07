import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings("all")
public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void twoSumTest() {
        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6));
    }


    @Test
    public void lengthOfLongestSubstringTest() {
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
        Assert.assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    public void findMedianSortedArraysTest() {
        Assert.assertEquals(2, solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}), 0);
        Assert.assertEquals(2.5, solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}), 0);
    }

    @Test
    public void longestPalindromeTest() {
        Assert.assertTrue(solution.longestPalindrome("babad").equals("bab") || solution.longestPalindrome("babad").equals("aba"));
        Assert.assertEquals("bb", solution.longestPalindrome("cbbd"));
        Assert.assertEquals("aca", solution.longestPalindrome("aacabdkacaa"));
    }

    @Test
    public void convertTest() {
        Assert.assertEquals("PAHNAPLSIIGYIR", solution.convert("PAYPALISHIRING", 3));
        Assert.assertEquals("PINALSIGYAHRPI", solution.convert("PAYPALISHIRING", 4));
        Assert.assertEquals("A", solution.convert("A", 1));
    }

    @Test
    public void reverseTest() {
        Assert.assertEquals(321, solution.reverse(123));
        Assert.assertEquals(-321, solution.reverse(-123));
        Assert.assertEquals(21, solution.reverse(120));
        Assert.assertEquals(0, solution.reverse(0));
    }

    @Test
    public void myAtoiTest() {
        Assert.assertEquals(42, solution.myAtoi("42"));
        Assert.assertEquals(-42, solution.myAtoi("-042"));
        Assert.assertEquals(1337, solution.myAtoi("1337c0d3"));
        Assert.assertEquals(0, solution.myAtoi("0-1"));
        Assert.assertEquals(0, solution.myAtoi("words and 987"));
        Assert.assertEquals(Integer.MIN_VALUE, solution.myAtoi("-2147483649"));
    }

    @Test
    public void isPalindromeTest() {
        Assert.assertTrue(solution.isPalindrome(121));
        Assert.assertFalse(solution.isPalindrome(-121));
        Assert.assertFalse(solution.isPalindrome(10));
    }

    @Test
    public void isMatchTest() {
        Assert.assertFalse(solution.isMatch("aa", "a"));
        Assert.assertTrue(solution.isMatch("aa", "a*"));
        Assert.assertTrue(solution.isMatch("ab", ".*"));
    }

    @Test
    public void maxAreaTest() {
        Assert.assertEquals(49, solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        Assert.assertEquals(1, solution.maxArea(new int[]{1, 1}));
    }

    @Test
    public void intToRomanTest() {
        Assert.assertEquals("MMMDCCXLIX", solution.intToRoman(3749));
        Assert.assertEquals("LVIII", solution.intToRoman(58));
        Assert.assertEquals("MCMXCIV", solution.intToRoman(1994));
    }

    @Test
    public void longestCommonPrefixTest() {
        Assert.assertEquals("fl", solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        Assert.assertEquals("", solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
    }

    @Test
    public void threeSumClosestTest() {
        Assert.assertEquals(2, solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        Assert.assertEquals(0, solution.threeSumClosest(new int[]{0, 0, 0}, 0));
    }

    @Test
    public void isValidTest() {
        Assert.assertTrue(solution.isValid("()"));
        Assert.assertTrue(solution.isValid("(){}[]"));
        Assert.assertFalse(solution.isValid("(]"));
        Assert.assertTrue(solution.isValid("([])"));
    }

    @Test
    public void removeDuplicatesTest() {
        Assert.assertEquals(2, solution.removeDuplicates(new int[]{1, 1, 2}));
        Assert.assertEquals(5, solution.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }

    @Test
    public void removeElementTest() {
        Assert.assertEquals(2, solution.removeElement(new int[]{3, 2, 2, 3}, 3));
        Assert.assertEquals(5, solution.removeElement(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

    @Test
    public void strStrTest() {
        Assert.assertEquals(0, solution.strStr("sadbutsad", "sad"));
        Assert.assertEquals(-1, solution.strStr("leetcode", "leeto"));
        Assert.assertEquals(4, solution.strStr("mississippi", "issip"));
        Assert.assertEquals(9, solution.strStr("mississippi", "pi"));
        Assert.assertEquals(4, solution.strStr("aabaaabaaac", "aabaaac"));
        Assert.assertEquals(-1, solution.strStr("adcadcaddcadde", "adcadde"));
    }

    @Test
    public void divideTest() {
        Assert.assertEquals(3, solution.divide(10, 3));
        Assert.assertEquals(-2, solution.divide(7, -3));
        Assert.assertEquals(1, solution.divide(2, 2));
        Assert.assertEquals(-1073741824, solution.divide(-2147483648, 2));
    }

    @Test
    public void longestValidParenthesesTest() {
        Assert.assertEquals(2, solution.longestValidParentheses("(()"));
        Assert.assertEquals(4, solution.longestValidParentheses(")()())"));
        Assert.assertEquals(0, solution.longestValidParentheses(""));
        Assert.assertEquals(2, solution.longestValidParentheses("())"));
        Assert.assertEquals(6, solution.longestValidParentheses("()(())"));
    }

    @Test
    public void searchTest() {
        Assert.assertEquals(4, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        Assert.assertEquals(-1, solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        Assert.assertEquals(-1, solution.search(new int[]{1}, 0));
        Assert.assertEquals(1, solution.search(new int[]{1, 3, 5}, 3));
        Assert.assertEquals(1, solution.search(new int[]{1, 3}, 3));
        Assert.assertEquals(2, solution.search(new int[]{5, 1, 3}, 3));
    }

    @Test
    public void searchRangeTest() {
        Assert.assertArrayEquals(new int[]{3, 4}, solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8));
        Assert.assertArrayEquals(new int[]{-1, -1}, solution.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6));
        Assert.assertArrayEquals(new int[]{-1, -1}, solution.searchRange(new int[]{}, 0));
        Assert.assertArrayEquals(new int[]{1, 1}, solution.searchRange(new int[]{1, 4}, 4));
        Assert.assertArrayEquals(new int[]{0, 1}, solution.searchRange(new int[]{2, 2}, 2));
    }

    @Test
    public void searchInsertTest() {
        Assert.assertEquals(2, solution.searchInsert(new int[]{1, 3, 5, 6}, 5));
        Assert.assertEquals(1, solution.searchInsert(new int[]{1, 3, 5, 6}, 2));
        Assert.assertEquals(4, solution.searchInsert(new int[]{1, 3, 5, 6}, 7));
        Assert.assertEquals(0, solution.searchInsert(new int[]{1, 3, 5, 6}, 0));
    }

    @Test
    public void isValidSudokuTest() {
        Assert.assertTrue(solution.isValidSudoku(new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}));

        Assert.assertFalse(solution.isValidSudoku(new char[][]{{'8', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}));
    }

    @Test
    public void countAndSayTest() {
        Assert.assertEquals("1211", solution.countAndSay(4));

        Assert.assertEquals("1", solution.countAndSay(1));
    }

    @Test
    public void firstMissingPositiveTest() {
        Assert.assertEquals(3, solution.firstMissingPositive(new int[]{1, 2, 0}));
        Assert.assertEquals(2, solution.firstMissingPositive(new int[]{3, 4, -1, 1}));
        Assert.assertEquals(1, solution.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
        Assert.assertEquals(2, solution.firstMissingPositive(new int[]{1, 1}));
    }

    @Test
    public void trapTest() {
        Assert.assertEquals(6, solution.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        Assert.assertEquals(9, solution.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

}