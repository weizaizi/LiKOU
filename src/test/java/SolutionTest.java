import org.junit.Assert;
import org.junit.Test;


@SuppressWarnings("all")
public class SolutionTest {

    private Solution solution = new Solution();

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

    @Test
    public void multiplyTest() {
        Assert.assertEquals("6", solution.multiply("2", "3"));
        Assert.assertEquals("56088", solution.multiply("123", "456"));
    }

    @Test
    public void isMatch44Test() {
        Assert.assertFalse(solution.isMatch44("aa", "a"));
        Assert.assertTrue(solution.isMatch44("aa", "a*"));
        Assert.assertFalse(solution.isMatch44("cb", "?a"));
        Assert.assertFalse(solution.isMatch44("mississippi", "m??*ss*?i*pi"));
        Assert.assertTrue(solution.isMatch44("aa", "aa"));
        Assert.assertTrue(solution.isMatch44("", "*******"));
        Assert.assertTrue(solution.isMatch44("abcabczzzde", "*abc???de*"));
        Assert.assertFalse(solution.isMatch44("zacabz", "*a?b*"));
        Assert.assertFalse(solution.isMatch44("b", "*?*?*"));
        Assert.assertTrue(solution.isMatch44("hi", "*?"));
        Assert.assertFalse(solution.isMatch44("b", "?*?"));
    }

    @Test
    public void jumpTest() {
        Assert.assertEquals(2, solution.jump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertEquals(2, solution.jump(new int[]{2, 3, 0, 1, 4}));
    }

    @Test
    public void myPowTest() {
        Assert.assertEquals(1024, solution.myPow(2, 10), 0.0001);
        Assert.assertEquals(9.261, solution.myPow(2.1, 3), 0.0001);
        Assert.assertEquals(0.25, solution.myPow(2, -2), 0.0001);
    }

    @Test
    public void solveNQueensTest() {
        Assert.assertEquals(2, solution.totalNQueens(4));
        Assert.assertEquals(1, solution.totalNQueens(1));
    }

    @Test
    public void maxSubArrayTest() {
        Assert.assertEquals(6, solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        Assert.assertEquals(1, solution.maxSubArray(new int[]{1}));
        Assert.assertEquals(23, solution.maxSubArray(new int[]{5, 4, -1, 7, 8}));
        Assert.assertEquals(-1, solution.maxSubArray(new int[]{-2, -1}));
    }

    @Test
    public void canJumpTest() {
        Assert.assertTrue(solution.canJump(new int[]{2, 3, 1, 1, 4}));
        Assert.assertFalse(solution.canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    public void mergeTest() {
        Assert.assertArrayEquals(new int[][]{{1, 6}, {8, 10}, {15, 18}}, solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
        Assert.assertArrayEquals(new int[][]{{1, 5}}, solution.merge(new int[][]{{1, 4}, {4, 5}}));
    }

    @Test
    public void insertTest() {
        Assert.assertArrayEquals(new int[][]{{1, 5}, {6, 9}}, solution.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
        Assert.assertArrayEquals(new int[][]{{1, 2}, {3, 10}, {12, 16}}, solution.insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8}));
        Assert.assertArrayEquals(new int[][]{{1, 5}, {6, 8}}, solution.insert(new int[][]{{1, 5}}, new int[]{6, 8}));
        Assert.assertArrayEquals(new int[][]{{0, 0}, {1, 5}}, solution.insert(new int[][]{{1, 5}}, new int[]{0, 0}));
    }

    @Test
    public void lengthOfLastWordTest() {
        Assert.assertEquals(5, solution.lengthOfLastWord("Hello World"));
        Assert.assertEquals(4, solution.lengthOfLastWord(" fly me   to   the moon  "));
        Assert.assertEquals(6, solution.lengthOfLastWord("luffy is still joyboy"));
    }

    @Test
    public void generateMatrixTest() {
        Assert.assertArrayEquals(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}}, solution.generateMatrix(3));
        Assert.assertArrayEquals(new int[][]{{1}}, solution.generateMatrix(1));
    }

    @Test
    public void getPermutationTest() {
        Assert.assertEquals("12", solution.getPermutation(2, 1));
        Assert.assertEquals("213", solution.getPermutation(3, 3));
        Assert.assertEquals("2314", solution.getPermutation(4, 9));
        Assert.assertEquals("123", solution.getPermutation(3, 1));
        Assert.assertEquals("21", solution.getPermutation(2, 2));
        Assert.assertEquals("132", solution.getPermutation(3, 2));
        Assert.assertEquals("312", solution.getPermutation(3, 5));
    }

    @Test
    public void uniquePathsTest() {
        Assert.assertEquals(28, solution.uniquePaths(3, 7));
        Assert.assertEquals(3, solution.uniquePaths(3, 2));
    }

    @Test
    public void uniquePathsWithObstaclesTest() {
        Assert.assertEquals(2, solution.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));

        Assert.assertEquals(0, solution.uniquePathsWithObstacles(new int[][]{{0, 0}, {0, 1}}));

        Assert.assertEquals(1, solution.uniquePathsWithObstacles(new int[][]{{0, 1}, {0, 0}}));
    }

    @Test
    public void minPathSumTest() {
        Assert.assertEquals(7, solution.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
        Assert.assertEquals(12, solution.minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}));
        Assert.assertEquals(3, solution.minPathSum(new int[][]{{1, 2}, {1, 1}}));
    }

    @Test
    public void isNumberTest() {
        Assert.assertTrue(solution.isNumber("0"));
        Assert.assertFalse(solution.isNumber("e"));
        Assert.assertFalse(solution.isNumber("."));
        Assert.assertTrue(solution.isNumber(".1"));
        Assert.assertFalse(solution.isNumber("0e"));
        Assert.assertFalse(solution.isNumber("4e+"));
        Assert.assertFalse(solution.isNumber("e9"));
        Assert.assertTrue(solution.isNumber("3."));
        Assert.assertFalse(solution.isNumber(".e1"));
        Assert.assertFalse(solution.isNumber("+"));
    }

    @Test
    public void plusOneTest() {
        Assert.assertArrayEquals(new int[]{1, 2, 4}, solution.plusOne(new int[]{1, 2, 3}));
        Assert.assertArrayEquals(new int[]{4, 3, 2, 2}, solution.plusOne(new int[]{4, 3, 2, 1}));
        Assert.assertArrayEquals(new int[]{1, 0}, solution.plusOne(new int[]{9}));
        Assert.assertArrayEquals(new int[]{1, 0, 0}, solution.plusOne(new int[]{9, 9}));
    }

    @Test
    public void addBinaryTest() {
        Assert.assertEquals("100", solution.addBinary("11", "1"));
        Assert.assertEquals("10101", solution.addBinary("1010", "1011"));
    }

    @Test
    public void mySqrtTest() {
        Assert.assertEquals(2, solution.mySqrt(4));
        Assert.assertEquals(2, solution.mySqrt(8));
    }

    @Test
    public void climbStairsTest() {
        Assert.assertEquals(2, solution.climbStairs(2));
        Assert.assertEquals(3, solution.climbStairs(3));
    }

    @Test
    public void simplifyPathTest() {
        Assert.assertEquals("/home", solution.simplifyPath("/home/"));
        Assert.assertEquals("/home/foo", solution.simplifyPath("/home//foo/"));
        Assert.assertEquals("/home/user/Pictures", solution.simplifyPath("/home/user/Documents/../Pictures"));
        Assert.assertEquals("/", solution.simplifyPath("/../"));
        Assert.assertEquals("/.../b/d", solution.simplifyPath("/.../a/../b/c/../d/./"));
    }

    @Test
    public void minDistanceTest() {
        Assert.assertEquals(3, solution.minDistance("horse", "ros"));
        Assert.assertEquals(5, solution.minDistance("intention", "execution"));
        Assert.assertEquals(1, solution.minDistance("", "a"));
        Assert.assertEquals(27, solution.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }

    @Test
    public void searchMatrixTest() {
        Assert.assertTrue(solution.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
        Assert.assertFalse(solution.searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 13));
        Assert.assertFalse(solution.searchMatrix(new int[][]{{1}}, 2));
        Assert.assertFalse(solution.searchMatrix(new int[][]{{1, 1}}, 0));
    }

    @Test
    public void minWindowTest() {
        Assert.assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
        Assert.assertEquals("a", solution.minWindow("a", "a"));
        Assert.assertEquals("", solution.minWindow("a", "aa"));
    }

    @Test
    public void existTest() {
        Assert.assertTrue(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
        Assert.assertTrue(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));
        Assert.assertFalse(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"));
    }

    @Test
    public void removeDuplicates2Test() {
        Assert.assertEquals(5, solution.removeDuplicates2(new int[]{1, 1, 1, 2, 2, 3}));
        Assert.assertEquals(7, solution.removeDuplicates2(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }

    @Test
    public void search2Test() {
        Assert.assertTrue(solution.search2(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        Assert.assertFalse(solution.search2(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
    }

    @Test
    public void largestRectangleAreaTest() {
        Assert.assertEquals(10, solution.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        Assert.assertEquals(4, solution.largestRectangleArea(new int[]{2, 4}));
        Assert.assertEquals(4, solution.largestRectangleArea(new int[]{4, 2}));
        Assert.assertEquals(3, solution.largestRectangleArea(new int[]{2, 1, 2}));
    }

    @Test
    public void maximalRectangle() {
        Assert.assertEquals(6, solution.maximalRectangle(new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}}));
        Assert.assertEquals(0, solution.maximalRectangle(new char[][]{{'0'}}));
        Assert.assertEquals(1, solution.maximalRectangle(new char[][]{{'1'}}));
    }

    @Test
    public void isScrambleTest() {
        Assert.assertFalse(solution.isScramble("abcde", "caebd"));
        Assert.assertTrue(solution.isScramble("a", "a"));
        Assert.assertTrue(solution.isScramble("great", "rgeat"));
        Assert.assertTrue(solution.isScramble("abcdbdacbdac", "bdacabcdbdac"));
        Assert.assertTrue(solution.isScramble("abca", "caba"));
    }

    @Test
    public void numDecodingsTest() {
        Assert.assertEquals(2, solution.numDecodings("12"));
        Assert.assertEquals(3, solution.numDecodings("226"));
        Assert.assertEquals(0, solution.numDecodings("06"));
        Assert.assertEquals(1, solution.numDecodings("2101"));
        Assert.assertEquals(5, solution.numDecodings("1123"));
        Assert.assertEquals(0, solution.numDecodings("10011"));
    }

    @Test
    public void numTreesTest() {
        Assert.assertEquals(5, solution.numTrees(3));
        Assert.assertEquals(1, solution.numTrees(1));
    }

    @Test
    public void isInterleaveTest() {
        Assert.assertTrue(solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        Assert.assertFalse(solution.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        Assert.assertTrue(solution.isInterleave("", "", ""));
        Assert.assertFalse(solution.isInterleave("db", "b", "cbb"));
    }

    @Test
    public void isValidBSTDfs() {
        Assert.assertTrue(solution.isValidBST(new TreeNode(2, new TreeNode(1), new TreeNode(3))));
        Assert.assertFalse(solution.isValidBST(new TreeNode(5, new TreeNode(4), new TreeNode(6, new TreeNode(3), new TreeNode(7)))));
        Assert.assertFalse(solution.isValidBST(new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)))));
    }

    @Test
    public void isSameTreeTest() {
        Assert.assertTrue(solution.isSameTree(new TreeNode(1, new TreeNode(2), new TreeNode(3)), new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        Assert.assertFalse(solution.isSameTree(new TreeNode(1, new TreeNode(2), null), new TreeNode(1, null, new TreeNode(2))));
        Assert.assertFalse(solution.isSameTree(new TreeNode(1, new TreeNode(2), new TreeNode(1)), new TreeNode(1, new TreeNode(1), new TreeNode(2))));
    }

    @Test
    public void isSymmetricTest() {
        Assert.assertTrue(solution.isSymmetric(new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)), new TreeNode(2, new TreeNode(4), new TreeNode(3)))));
        Assert.assertFalse(solution.isSymmetric(new TreeNode(1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)))));
    }

    @Test
    public void maxDepthTest() {
        Assert.assertEquals(3, solution.maxDepth(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        Assert.assertEquals(2, solution.maxDepth(new TreeNode(1, null, new TreeNode(2))));
    }

    @Test
    public void isBalancedTest() {
        Assert.assertTrue(solution.isBalanced(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        Assert.assertTrue(solution.isBalanced(null));
        Assert.assertFalse(solution.isBalanced(new TreeNode(1, new TreeNode(2, new TreeNode(3, new TreeNode(4), new TreeNode(4)), new TreeNode(3)), new TreeNode(2))));
    }

    @Test
    public void minDepthTest() {
        Assert.assertEquals(2, solution.minDepth(new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        Assert.assertEquals(5, solution.minDepth(new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null, new TreeNode(6)))))));
    }

    @Test
    public void hasPathSumTest() {
        Assert.assertTrue(solution.hasPathSum(new TreeNode(5, new TreeNode(4, new TreeNode(11, new TreeNode(7), new TreeNode(2)), null), new TreeNode(8, new TreeNode(13), new TreeNode(4, null, new TreeNode(1)))), 22));
        Assert.assertFalse(solution.hasPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3)), 5));
        Assert.assertFalse(solution.hasPathSum(null, 0));
    }

    @Test
    public void numDistinctTest() {
        Assert.assertEquals(3, solution.numDistinct("rabbbit", "rabbit"));
        Assert.assertEquals(5, solution.numDistinct("babgbag", "bag"));
    }

    @Test
    public void maxProfitTest() {
        Assert.assertEquals(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void maxProfit2Test() {
        Assert.assertEquals(7, solution.maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
        Assert.assertEquals(4, solution.maxProfit2(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(0, solution.maxProfit2(new int[]{7, 6, 4, 3, 1}));
        Assert.assertEquals(8, solution.maxProfit2(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }

    @Test
    public void maxProfit3Test() {
        Assert.assertEquals(6, solution.maxProfit3(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        Assert.assertEquals(4, solution.maxProfit3(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(0, solution.maxProfit3(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    public void maxPathSumTest() {
        Assert.assertEquals(6, solution.maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        Assert.assertEquals(42, solution.maxPathSum(new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
    }

    @Test
    public void isPalindrome2Test() {
        Assert.assertTrue(solution.isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(solution.isPalindrome("race a car"));
        Assert.assertTrue(solution.isPalindrome(" "));
        Assert.assertFalse(solution.isPalindrome("0P"));
    }

    @Test
    public void longestConsecutiveTest() {
        Assert.assertEquals(4, solution.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        Assert.assertEquals(9, solution.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        Assert.assertEquals(3, solution.longestConsecutive(new int[]{1, 0, 1, 2}));
    }

    @Test
    public void sumNumbersTest() {
        Assert.assertEquals(25, solution.sumNumbers(new TreeNode(1, new TreeNode(2), new TreeNode(3))));
        Assert.assertEquals(1026, solution.sumNumbers(new TreeNode(4, new TreeNode(9, new TreeNode(5), new TreeNode(1)), new TreeNode(0))));
    }

    @Test
    public void minCutTest() {
        Assert.assertEquals(1, solution.minCut("aab"));
        Assert.assertEquals(0, solution.minCut("a"));
        Assert.assertEquals(1, solution.minCut("ab"));
        Assert.assertEquals(1, solution.minCut("cdd"));
    }

    @Test
    public void minCostTest() {
        Assert.assertEquals(1, solution.minCost(new int[]{4, 2, 2, 2}, new int[]{1, 4, 1, 2}));
        Assert.assertEquals(-1, solution.minCost(new int[]{2, 3, 4, 1}, new int[]{3, 2, 5, 1}));
    }

    @Test
    public void canCompleteCircuitTest() {
        Assert.assertEquals(3, solution.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        Assert.assertEquals(-1, solution.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }

    @Test
    public void candyTest() {
        Assert.assertEquals(5, solution.candy(new int[]{1, 0, 2}));
        Assert.assertEquals(4, solution.candy(new int[]{1, 2, 2}));
        Assert.assertEquals(7, solution.candy(new int[]{1, 3, 2, 2, 1}));
        Assert.assertEquals(11, solution.candy(new int[]{1, 3, 4, 5, 2}));
        Assert.assertEquals(12, solution.candy(new int[]{29, 51, 87, 87, 72, 12}));
        Assert.assertEquals(15, solution.candy(new int[]{0, 1, 2, 5, 3, 2, 7}));
    }

    @Test
    public void singleNumberTest() {
        Assert.assertEquals(1, solution.singleNumber(new int[]{2, 2, 1}));
        Assert.assertEquals(4, solution.singleNumber(new int[]{4, 1, 2, 1, 2}));
        Assert.assertEquals(1, solution.singleNumber(new int[]{1}));
    }

    @Test
    public void singleNumber2Test() {
        Assert.assertEquals(3, solution.singleNumber2(new int[]{2, 2, 3, 2}));
        Assert.assertEquals(99, solution.singleNumber2(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    @Test
    public void maxPointsTest() {
        Assert.assertEquals(3, solution.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}}));
        Assert.assertEquals(4, solution.maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}}));
    }

    @Test
    public void evalRPNTest() {
        Assert.assertEquals(9, solution.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        Assert.assertEquals(6, solution.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        Assert.assertEquals(22, solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    public void reverseWordsTest() {
        Assert.assertEquals("blue is sky the", solution.reverseWords("the sky is blue"));
        Assert.assertEquals("world hello", solution.reverseWords("  hello world  "));
        Assert.assertEquals("example good a", solution.reverseWords("a good   example"));
    }

    @Test
    public void maxProductTest() {
        Assert.assertEquals(6, solution.maxProduct(new int[]{2, 3, -2, 4}));
        Assert.assertEquals(0, solution.maxProduct(new int[]{-2, 0, -1}));
    }

    @Test
    public void findMinTest() {
        Assert.assertEquals(1, solution.findMin(new int[]{3, 4, 5, 1, 2}));
        Assert.assertEquals(0, solution.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        Assert.assertEquals(11, solution.findMin(new int[]{11, 13, 15, 17}));
        Assert.assertEquals(1, solution.findMin(new int[]{2, 1}));
    }

    @Test
    public void findMin2Test() {
        Assert.assertEquals(1, solution.findMin2(new int[]{1, 3, 5}));
        Assert.assertEquals(0, solution.findMin2(new int[]{2, 2, 2, 0, 1}));
        Assert.assertEquals(1, solution.findMin2(new int[]{3, 1}));
    }

    @Test
    public void hammingWeightTest() {
        Assert.assertEquals(3, solution.hammingWeight(11));
        Assert.assertEquals(1, solution.hammingWeight(128));
        Assert.assertEquals(31, solution.hammingWeight(-3));
    }

    @Test
    public void isPowerOfTwoTest() {
        Assert.assertTrue(solution.isPowerOfTwo(1));
        Assert.assertTrue(solution.isPowerOfTwo(16));
        Assert.assertFalse(solution.isPowerOfTwo(3));
    }

    @Test
    public void isPowerOfThreeTest() {
        Assert.assertTrue(solution.isPowerOfThree(27));
        Assert.assertFalse(solution.isPowerOfThree(0));
        Assert.assertTrue(solution.isPowerOfThree(9));
        Assert.assertFalse(solution.isPowerOfThree(45));
    }

    @Test
    public void isPowerOfFourTest() {
        Assert.assertTrue(solution.isPowerOfFour(16));
        Assert.assertFalse(solution.isPowerOfFour(5));
        Assert.assertTrue(solution.isPowerOfFour(1));
    }

    @Test
    public void getSumTest() {
        Assert.assertEquals(3, solution.getSum(1, 2));
        Assert.assertEquals(5, solution.getSum(2, 3));
    }


    @Test
    public void hammingDistanceTest() {
        Assert.assertEquals(2, solution.hammingDistance(1, 4));
        Assert.assertEquals(1, solution.hammingDistance(1, 3));
    }

    @Test
    public void hasAlternatingBitsTest() {
        Assert.assertTrue(solution.hasAlternatingBits(5));
        Assert.assertFalse(solution.hasAlternatingBits(7));
        Assert.assertFalse(solution.hasAlternatingBits(11));
    }

    @Test
    public void totalFruitTest() {
        Assert.assertEquals(3, solution.totalFruit(new int[]{1, 2, 1}));
        Assert.assertEquals(3, solution.totalFruit(new int[]{0, 1, 2, 1}));
        Assert.assertEquals(4, solution.totalFruit(new int[]{1, 2, 3, 2, 2}));
        Assert.assertEquals(5, solution.totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
        Assert.assertEquals(5, solution.totalFruit(new int[]{1, 0, 1, 4, 1, 4, 1, 2, 3}));
    }

    @Test
    public void subsetXORSumTest() {
        Assert.assertEquals(6, solution.subsetXORSum(new int[]{1, 3}));
        Assert.assertEquals(28, solution.subsetXORSum(new int[]{5, 1, 6}));
        Assert.assertEquals(480, solution.subsetXORSum(new int[]{3, 4, 5, 6, 7, 8}));
    }

    @Test
    public void maxTotalFruitsTest() {
        Assert.assertEquals(9, solution.maxTotalFruits(new int[][]{{2, 8}, {6, 3}, {8, 6}}, 5, 4));
        Assert.assertEquals(14, solution.maxTotalFruits(new int[][]{{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}}, 5, 4));
        Assert.assertEquals(0, solution.maxTotalFruits(new int[][]{{0, 3}, {6, 4}, {8, 5}}, 3, 2));
        Assert.assertEquals(54, solution.maxTotalFruits(new int[][]{{0, 7}, {1, 9}, {2, 3}, {4, 10}, {5, 10}, {10, 7}, {12, 9}, {13, 6}, {14, 5}, {16, 5}, {18, 4}, {23, 7}, {25, 5}, {33, 10}, {34, 3}, {35, 2}, {39, 3}}, 34, 23));
        Assert.assertEquals(15, solution.maxTotalFruits(new int[][]{{1, 2}, {2, 3}, {4, 1}, {6, 6}, {8, 1}, {21, 1}, {24, 2}, {26, 1}, {29, 10}}, 27, 23));
        Assert.assertEquals(40, solution.maxTotalFruits(new int[][]{{0, 1}, {13, 9}, {14, 10}, {15, 5}, {18, 1}, {21, 1}, {23, 6}, {26, 10}, {27, 7}, {30, 1}, {31, 10}, {32, 6}, {38, 6}, {40, 6}}, 31, 10));
        Assert.assertEquals(16, solution.maxTotalFruits(new int[][]{{0, 2}, {4, 1}, {11, 1}, {15, 9}, {18, 8}, {19, 10}, {20, 2}, {24, 5}, {25, 6}, {27, 7}, {28, 8}, {29, 1}, {31, 5}, {38, 4}, {40, 6}}, 30, 3));
    }

    @Test
    public void maxCollectedFruitsTest() {
        Assert.assertEquals(100, solution.maxCollectedFruits(new int[][]{{1, 2, 3, 4}, {5, 6, 8, 7}, {9, 10, 11, 12}, {13, 14, 15, 16}}));
        Assert.assertEquals(4, solution.maxCollectedFruits(new int[][]{{1, 1}, {1, 1}}));
        Assert.assertEquals(105, solution.maxCollectedFruits(new int[][]{{16, 3, 11, 14, 14}, {3, 0, 10, 13, 14}, {7, 18, 8, 7, 18}, {7, 8, 5, 7, 5}, {0, 14, 8, 1, 0}}));
    }

    @Test
    public void numOfUnplacedFruitsTest() {
        Assert.assertEquals(1, solution.numOfUnplacedFruits(new int[]{4, 2, 5}, new int[]{3, 5, 4}));
        Assert.assertEquals(0, solution.numOfUnplacedFruits(new int[]{3, 6, 1}, new int[]{6, 4, 7}));
    }

    @Test
    public void insertBitsTest() {
        Assert.assertEquals(1100, solution.insertBits(1024, 19, 2, 6));
        Assert.assertEquals(31, solution.insertBits(0, 31, 0, 4));
        Assert.assertEquals(2082885133, solution.insertBits(1143207437, 1017033, 11, 31));
    }

    @Test
    public void swapNumbersTest() {
        Assert.assertArrayEquals(new int[]{2147483647, 0}, solution.swapNumbers(new int[]{0, 2147483647}));
    }

    @Test
    public void LRUCacheTest() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1);
        lruCache.put(2, 2);
        Assert.assertEquals(2, lruCache.get(2));
        lruCache.put(1, 1);
        lruCache.put(4, 1);
        Assert.assertEquals(-1, lruCache.get(2));
    }

    @Test
    public void findPeakElementTest() {
        Assert.assertEquals(2, solution.findPeakElement(new int[]{1, 2, 3, 1}));
        int a = solution.findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 4});
        Assert.assertTrue(a == 1 || a == 5);
        int b = solution.findPeakElement(new int[]{6, 5, 4, 3, 2, 3, 2});
        Assert.assertTrue(b == 0 || b == 5);
    }

    @Test
    public void maximumGapTest() {
        Assert.assertEquals(3, solution.maximumGap(new int[]{3, 6, 9, 1}));
        Assert.assertEquals(0, solution.maximumGap(new int[]{10}));
    }

    @Test
    public void compareVersionTest() {
        Assert.assertEquals(-1, solution.compareVersion("1.2", "1.10"));
        Assert.assertEquals(0, solution.compareVersion("1.01", "1.001"));
        Assert.assertEquals(0, solution.compareVersion("1.0", "1.0.0.0"));
    }

    @Test
    public void fractionToDecimalTest() {
        Assert.assertEquals("0.5", solution.fractionToDecimal(1, 2));
        Assert.assertEquals("2", solution.fractionToDecimal(2, 1));
        Assert.assertEquals("0.(012)", solution.fractionToDecimal(4, 333));
        Assert.assertEquals("-6.25", solution.fractionToDecimal(-50, 8));
        Assert.assertEquals("0.0000000004656612873077392578125", solution.fractionToDecimal(-1, -2147483648));
    }

    @Test
    public void debug() {
    }
}
