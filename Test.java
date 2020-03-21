有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？

如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。

你允许：

装满任意一个水壶
清空任意一个水壶
从一个水壶向另外一个水壶倒水，直到装满或者倒空
class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z>x+y) return false;
        if(z==0) return true;
        int c=func(x,y);
        return z%c==0;
    }
    private int func(int a,int b){
        while(b!=0){
            int c=a%b;
            a=b;
            b=c;
        }
        return a;
    }
}

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if(z>x+y) return false;
        return dfs(x,y,z,new HashSet<>());
    }
    private boolean dfs(int x,int y,int z,Set<Integer> set){
        if(x==z||y==z||Math.abs(x-y)==z||x+y==z){
            return true;
        }
        if(set.contains(z)){
            return false;
        }
        set.add(z);
        if(dfs(x,y,Math.abs(x-z),set)||dfs(x,y,Math.abs(y-z),set)){
            return true;
        }
        return false;
        
    }
}

给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
class Solution {
    Queue<Integer> queue=new LinkedList<>();
    private int row;
    private int col;
    private int count;
    public int numIslands(char[][] grid) {
        row=grid.length;
        if(row==0) return 0;
        col=grid[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                    count++;
                    dfs(grid,i,j);
                }
            }
        }
        return count;
    }
    private void dfs(char[][] grid,int i,int j){
        if(i<0||j<0||i>=row||j>=col||grid[i][j]=='0'){
            return ;
        }
        grid[i][j]='0';
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }
}

你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。

列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。

字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。

 
 class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();
        for(String s:deadends){
            if(s.equals("0000")||s.equals(target)) return -1;
            visited.add(s);
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");
        int count=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size--!=0){
                String s = queue.poll();
                if (s.equals(target)) {
                    return count;
                }
                for (int i = 0; i < 4; i++) {
                    String plus=s.substring(0,i)+((s.charAt(i)-'0'+1)%10)+s.substring(i+1,4);
                   String sub=s.substring(0,i)+((s.charAt(i)-'0'-1+10)%10)+s.substring(i+1,4);
                   if(!visited.contains(plus)){
                       queue.offer(plus);
                       visited.add(plus);
                   }
                   if(!visited.contains(sub)){
                       queue.offer(sub);
                       visited.add(sub);
                   }
                }
            }
            count++;
        }
        return -1;
    }
}

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少
class Solution {
    public int numSquares(int n) {
        int[] arr=new int[n+1];
        Arrays.fill(arr,n+1);
        arr[0]=0;
        arr[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=1;i-j*j>=0;j++){
                arr[i]=Math.min(arr[i],arr[i-j*j]+1);
            }
        }
        return arr[n];
    }
}

