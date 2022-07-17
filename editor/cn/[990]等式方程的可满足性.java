// leetcode submit region begin(Prohibit modification and deletion)
class Solution {
  public boolean equationsPossible(String[] equations) {
    //声明一个并查集
    UF uf = new UF(26);
    for (String equation : equations) {
      //取出符号 equation.charAt(1)
      //将“==”连接的两个字母连接
      if (equation.charAt(1) == '=') {
        char b = equation.charAt(0);
        char c = equation.charAt(3);
        uf.union(b - 'a', c - 'a');
      }
    }

    for (String equation : equations) {
      if (equation.charAt(1) == '!') {
        char b = equation.charAt(0);
        char c = equation.charAt(3);
        //当关系为”！=“时，如果在并查集中时连接的，则冲突，返回false；
        boolean res =  uf.connected(b - 'a', c - 'a');
        if(res){
          return false;
        }
      }
    }
    return true;
  }
}

class UF {

  private int[] parents;
  private int count;
  private int[] size;

  public UF(int count) {
    this.count = count;
    parents = new int[count];
    size = new int[count];
    for (int i = 0; i < count; i++) {
      parents[i] = i;
      size[i] = 1;
    }
  }

  public void union(int a, int b) {
    int i = find(a);
    int j = find(b);
    if (i == j) {
      return;
    }
    if (size[j] > size[i]) {
      parents[i] = j;
      size[j] += size[i];
    } else {
      parents[j] = i;
      size[i] += size[j];
    }
    count--;
  }

  public boolean connected(int a, int b) {
    int i = find(a);
    int j = find(b);
    if (i == j) {
      return true;
    }
    return false;
  }

  public int find(int n) {
    while (parents[n] != n) {
      parents[n] = parents[parents[n]];
      n = parents[n];
    }
    return n;
  }

  public int count() {
    return count;
  }
}
// leetcode submit region end(Prohibit modification and deletion)
