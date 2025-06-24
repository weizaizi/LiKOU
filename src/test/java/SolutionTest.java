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
    public void reverseTest(){
        Assert.assertEquals(321 ,solution.reverse(123));
        Assert.assertEquals(-321 ,solution.reverse(-123));
        Assert.assertEquals(21 ,solution.reverse(120));
        Assert.assertEquals(0 ,solution.reverse(0));
    }

}
