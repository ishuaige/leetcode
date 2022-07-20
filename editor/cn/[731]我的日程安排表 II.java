import java.util.Map;
import java.util.TreeMap;

// leetcode submit region begin(Prohibit modification and deletion)
class MyCalendarTwo {

  private TreeMap<Integer, Integer> cnt;

  public MyCalendarTwo() {
    cnt = new TreeMap<Integer, Integer>();
  }

  public boolean book(int start, int end) {
    int ans = 0;
    int maxBook = 0;
    cnt.put(start, cnt.getOrDefault(start, 0) + 1);
    cnt.put(end, cnt.getOrDefault(end, 0) - 1);
    for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
      int freq = entry.getValue();
      maxBook += freq;
      ans = Math.max(maxBook, ans);
      if (maxBook > 2) {
        cnt.put(start, cnt.getOrDefault(start, 0) - 1);
        cnt.put(end, cnt.getOrDefault(end, 0) + 1);
        return false;
      }
    }
    return true;
  }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such: MyCalendarTwo obj = new
 * MyCalendarTwo(); boolean param_1 = obj.book(start,end);
 */
// leetcode submit region end(Prohibit modification and deletion)
