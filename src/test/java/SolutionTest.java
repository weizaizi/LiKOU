import org.junit.Assert;
import org.junit.Test;


public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void twoSumTest() {
        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9));
        Assert.assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6));
        Assert.assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6));
    }

    @Test
    public void addTwoNumbersTest() {
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

}
