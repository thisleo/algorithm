package algorithm;

import java.util.ArrayList;
import java.util.List;
// 2022.3.2 周三下午10:55
/*给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。

“最近的”定义为两个整数差的绝对值最小。

示例 1:

输入: n = "123"
输出: "121"
示例 2:

输入: n = "1"
输出: "0"
解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class NearestPalindromic {

    public String nearestPalindromic(String n) {
        long selNumber = Long.parseLong(n), ans = -1;
        List<Long> candidates = getCandidates(n);
        for (long candidate : candidates) {
            if (candidate != selNumber) {
                if (ans == -1 ||
                        Math.abs(candidate - selNumber) < Math.abs(ans - selNumber) ||
                        Math.abs(candidate - selNumber) == Math.abs(ans - selNumber) &&
                                candidate < ans) {
                    ans = candidate;
                }
            }
        }
        return Long.toString(ans);
    }


    public List<Long> getCandidates(String n) {
        int len = n.length();
        List<Long> candidates = new ArrayList<Long>() {
            {
                add((long) (Math.pow(10, len - 1) - 1));
                add((long) (Math.pow(10, len) + 1));
            }
        };
        long selfPrefix = Long.parseLong(n.substring(0, (len + 1) / 2));
        for (long i = selfPrefix - 1; i <= selfPrefix + 1; i++) {
            StringBuffer sb = new StringBuffer();
            String prefix = String.valueOf(i);
            sb.append(prefix);
            StringBuffer suffix = new StringBuffer(prefix).reverse();
            sb.append(suffix.substring(len & 1));
            String candidate = sb.toString();
            candidates.add(Long.parseLong(candidate));
        }
        return candidates;
    }
}
