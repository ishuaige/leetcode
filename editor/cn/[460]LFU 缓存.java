//请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。 
//
// 实现 LFUCache 类： 
//
// 
// LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象 
// int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。 
// void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 
//capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。 
// 
//
// 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。 
//
// 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入：
//["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", 
//"get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
//输出：
//[null, null, null, 1, null, -1, 3, null, -1, 3, 4]
//
//解释：
//// cnt(x) = 键 x 的使用计数
//// cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
//LFUCache lfu = new LFUCache(2);
//lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
//lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
//lfu.get(1);      // 返回 1
//                 // cache=[1,2], cnt(2)=1, cnt(1)=2
//lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
//                 // cache=[3,1], cnt(3)=1, cnt(1)=2
//lfu.get(2);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,1], cnt(3)=2, cnt(1)=2
//lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
//                 // cache=[4,3], cnt(4)=1, cnt(3)=2
//lfu.get(1);      // 返回 -1（未找到）
//lfu.get(3);      // 返回 3
//                 // cache=[3,4], cnt(4)=1, cnt(3)=3
//lfu.get(4);      // 返回 4
//                 // cache=[3,4], cnt(4)=2, cnt(3)=3 
//
// 
//
// 提示： 
//
// 
// 0 <= capacity <= 10⁴ 
// 0 <= key <= 10⁵ 
// 0 <= value <= 10⁹ 
// 最多调用 2 * 10⁵ 次 get 和 put 方法 
// 
// Related Topics 设计 哈希表 链表 双向链表 👍 520 👎 0


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class LFUCache {

    //key到value的映射
    HashMap<Integer,Integer> keyToValue;
    //key到频率的映射
    HashMap<Integer,Integer> keyToFreq;
    //频率到key的映射
    HashMap<Integer, LinkedHashSet<Integer>> freqToKey;
    //最大容量
    int cap;
    //最小频率
    int minfreq;

    public LFUCache(int capacity) {
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
        this.cap = capacity;
        this.minfreq = 0;
    }
    
    public int get(int key) {
        if(!keyToValue.containsKey(key)){
            return -1;
        }
        //更新频率
        increaseFreq(key);
        return keyToValue.get(key);
    }
    
    public void put(int key, int value) {
        if (this.cap <= 0) return;
        /* 若 key 已存在，修改对应的 val 即可 */
        if(keyToValue.containsKey(key)){
            //更新value值
            keyToValue.put(key,value);
            //更新频率
            increaseFreq(key);
            return;
        }
        /* key 不存在，需要插入 */
        /* 容量已满的话需要淘汰一个 freq 最小的 key */
        if(this.cap <= keyToValue.size()){
            removeMinFreqKey();
        }

        /* 插入 key 和 val，对应的 freq 为 1 */
        // 插入 KV 表
        keyToValue.put(key,value);
        // 插入 KF 表
        keyToFreq.put(key,1);
        // 插入 FK 表
        freqToKey.putIfAbsent(1,new LinkedHashSet<>());
        freqToKey.get(1).add(key);
        // 插入新 key 后最小的 freq 肯定是 1
        this.minfreq = 1;
    }

    private void increaseFreq(Integer key){

        int freq = keyToFreq.get(key);
        int newfreq = freq + 1;
        /* 更新 KF 表 */
        keyToFreq.put(key,newfreq);
        /* 更新 FK 表 */
        // 将 key 从 freq 对应的列表中删除
        freqToKey.get(freq).remove(key);
        // 将 key 加入 freq + 1 对应的列表中
        freqToKey.putIfAbsent(newfreq,new LinkedHashSet<>());
        freqToKey.get(newfreq).add(key);
        // 如果 freq 对应的列表空了，移除这个 freq
        if(freqToKey.get(freq).isEmpty()){
            freqToKey.remove(freq);
        // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if(freq == this.minfreq){
                this.minfreq++;
            }
        }
    }

    private void removeMinFreqKey(){
        // freq 最小的 key 列表
        LinkedHashSet<Integer> keyList = freqToKey.get(this.minfreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        int deletedKey = keyList.iterator().next();
        /* 更新 FK 表 */
        keyList.remove(deletedKey);
        if(keyList.isEmpty()){
            freqToKey.remove(this.minfreq);
        }
        /* 更新 KV 表 */
        keyToValue.remove(deletedKey);
        /* 更新 KF 表 */
        keyToFreq.remove(deletedKey);


    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
