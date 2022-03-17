package algorithm;

import java.util.*;

/*
* 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。

实现 AllOne 类：

AllOne() 初始化数据结构的对象。
inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 

示例：

输入
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
输出
[null, null, null, "hello", "hello", null, "hello", "leet"]

解释
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "hello"
allOne.inc("leet");
allOne.getMaxKey(); // 返回 "hello"
allOne.getMinKey(); // 返回 "leet"
 

提示：

1 <= key.length <= 10
key 由小写英文字母组成
测试用例保证：在每次调用 dec 时，数据结构中总存在 key
最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/all-oone-data-structure
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class AllOne {
    Map<String,Integer> map;
    TreeMap<Integer,Set<String>> freq;
    public AllOne() {
        map=new HashMap<>();
        freq=new TreeMap<>();
    }

    public void inc(String key) {
        map.put(key,map.getOrDefault(key,0)+1);
        int count=map.get(key);//目前的频率
        if(count>1){
            if(freq.get(count-1).size()==1){freq.remove(count-1);}
            else{freq.get(count-1).remove(key);}
        }
        freq.putIfAbsent(count,new HashSet<>());
        freq.get(count).add(key);
    }

    public void dec(String key) {
        int count=map.get(key);//目前的频率
        if(freq.get(count).size()==1){freq.remove(count);}
        else{freq.get(count).remove(key);}
        if(count==1){map.remove(key);}
        else{map.put(key,count-1);}
        if(count>1){
            freq.putIfAbsent(count-1,new HashSet<>());
            freq.get(count-1).add(key);
        }
    }

    public String getMaxKey() {
        if(map.size()>0){
            Integer a=freq.lastKey();
            for(String s:freq.get(a)){return s;}
        }
        return "";
    }

    public String getMinKey() {
        if(map.size()>0){
            Integer a=freq.firstKey();
            for(String s:freq.get(a)){return s;}
        }
        return "";
    }
}
