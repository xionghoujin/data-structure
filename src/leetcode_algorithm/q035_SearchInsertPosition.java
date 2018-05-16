package leetcode_algorithm;

/**
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

        You may assume no duplicates in the array.

        Here are few examples.

 [1,3,5,6], 5 �� 2
 [1,3,5,6], 2 �� 1
 [1,3,5,6], 7 �� 4
 [1,3,5,6], 0 �� 0

*/


public class q035_SearchInsertPosition {

    public static void main(String[] args) {
        System.out.println(new q035_SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(new q035_SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(new q035_SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(new q035_SearchInsertPosition().searchInsert(new int[]{1, 3, 5, 6}, 0));


        System.out.println(new q035_SearchInsertPosition().searchInsert1(new int[]{1, 3, 5, 6}, 5));
        System.out.println(new q035_SearchInsertPosition().searchInsert1(new int[]{1, 3, 5, 6}, 2));
        System.out.println(new q035_SearchInsertPosition().searchInsert1(new int[]{1, 3, 5, 6}, 7));
        System.out.println(new q035_SearchInsertPosition().searchInsert1(new int[]{1, 3, 5, 6}, 0));


    }


    /**
     * �ⷨ1 (���˽ⷨ)
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int position = 0;
        for(int i = 0;i < nums.length;i++) {
            position = i;
            if(nums[i] == target) return i;
            if(nums[i] > target) break;
        }
        if(nums[position] < target) return position+1;
        return position;
    }


    /**
     * �ⷨ2(�Ƽ��ⷨ)
     * @param nums ����
     * @param target Ŀ��ֵ
     * @return
     */
    public int searchInsert1(int[] nums , int target){
        int low = 0 , high = nums.length - 1;
        while (low <= high){
            int mid = low + (high-low)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) high = mid-1;
            else low = mid + 1;
        }
        return low;
    }
}
