
import java.util.HashMap;

class Solution {

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

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        HashMap<Character , Integer> hashMap = new HashMap<>();
        char[] charArray = s.toCharArray();
        int i = 0;
        int j = 0;
        while (j < charArray.length){
            if(hashMap.containsKey(charArray[j]) && hashMap.get(charArray[j]) >= i){
                result = Integer.max(result ,j - i);
                i = hashMap.get(charArray[j]) + 1;
            }
            hashMap.put(charArray[j] , j++);
        }
        return Integer.max(result , j - i);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i =0;
        int j = 0;
        int now = -1;
        int last = -1;
        int length = nums1.length + nums2.length;
        for (int k = 0; k <= length/2; k++) {
            last = now;
            if(i < nums1.length && (j >= nums2.length || nums1[i] < nums2[j])) now = nums1[i++];
            else now =nums2[j++];
        }
        if(length % 2==0) return (last + now)/2.0;
        else return now;
    }


}