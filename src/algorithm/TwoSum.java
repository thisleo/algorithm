package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 2022.3.1 周二 下午10:37
/*给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

 

示例 1：

输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
示例 2：

输入：nums = [3,2,4], target = 6
输出：[1,2]
示例 3：

输入：nums = [3,3], target = 6
输出：[0,1]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/two-sum
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class TwoSum {

    // 数组有序版本
    // 时间复杂度O(n), 空间复杂度O(1)
    public static int[] arraySortedTwoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[2];
        // 如果数组非有序,要进行排序,但是数字的索引值就会发生改变,如果不要求原始索引值,可以使用此方法
        // 使用数组排序后,时间复杂度变为O(log^n)
        // Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left, right};
            }
        }
        return new int[2];
    }

    // 数组无需排序,使用hashMap版本
    public static int[] hashMapTwoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int desired = target - nums[i];
            if (map.containsKey(desired)) {
                return new int[] {map.get(desired), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 11, 15};
//        for (int i : arraySortedTwoSum(arr, 9)) {
//            System.out.println(i);
//        }

        for (int i : hashMapTwoSum(arr, 9)) {
            System.out.println(i);
        }
    }
}
