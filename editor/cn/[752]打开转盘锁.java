import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int openLock(String[] deadends, String target) {
        int step = 0;
        HashSet<String> dead = new HashSet<>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }

        HashSet<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add("0000");
        visited.add("0000");
        // 启动广度优先搜索
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                if (dead.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                // 遍历将未访问过的节点加入队列
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    String plusOne(String cur, int n) {
        char[] ch = cur.toCharArray();
        if (ch[n] == '9') {
            ch[n] = '0';
        } else {
            ch[n] += 1;
        }
        return new String(ch);
    }

    String minusOne(String cur, int n) {
        char[] ch = cur.toCharArray();
        if (ch[n] == '0') {
            ch[n] = '9';
        } else {
            ch[n] -= 1;
        }
        return new String(ch);
    }
}
// leetcode submit region end(Prohibit modification and deletion)
