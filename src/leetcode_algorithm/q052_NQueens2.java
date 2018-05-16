package leetcode_algorithm;


/**

    Follow up for N-Queens problem.

        Now, instead outputting board configurations, return the total number of distinct solutions.

*/

public class q052_NQueens2 {

    public static void main(String[] args) {
        System.out.println(new q052_NQueens2().totalNQueens(4));
        System.out.println(new q052_NQueens2().totalNQueens2(4));
        System.out.println(new q052_NQueens2().totalNQueens(1));
        System.out.println(new q052_NQueens2().totalNQueens2(1));
        System.out.println(new q052_NQueens2().totalNQueens2(5));
        System.out.println(new q052_NQueens2().totalNQueens2(5));
    }

    /**
     * �ⷨ1
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d1 = new boolean[2 * n];
        boolean[] d2 = new boolean[2 * n];
        backtracking(0 , cols , d1 , d2 , n);
        return count;
    }

    int count = 0;
    private void backtracking(int row, boolean[] cols, boolean[] d1, boolean[] d2, int n) {
        if(row == n) count ++;
        for(int col = 0; col < n ;col++) {
            int id1 = col - row + n;
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) continue;

            cols[col] = true;
            d1[id1] = true;
            d2[id2] = true;
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false;
            d1[id1] = false;
            d2[id2] = false;
        }
    }


    /**
     *�ⷨ2
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        if(n == 1) return 1;
        dfs(0 , n , 0 , 0 , 1);
        return count;
    }

        /**
         ����n-queens�ⷨ, ���𰸸���.
         ��column��Ǵ���֮ǰ����Щcolumn�Ѿ�������queen. ��������(row, col)��Ӧcolumn�ĵ�colλ(LSB --> MSB, ��ͬ).
         ��diag��Ǵ�λ��֮ǰ����Щ���Խ����Ѿ�������queen. ��������(row, col)��Ӧdiag�ĵ�(n - 1 + row - col)λ.
         ��antiDiag��Ǵ�λ��֮ǰ����Щ���Խ����Ѿ�������queen. ��������(row, col)��ӦantiDiag�ĵ�(row + col)λ.
    */
    private void dfs(int row, int n, int column, int diag, int antiDiag) {
        if (row == n) {
            ++count;
            return;
        }

        for(int i = 0; i< n; ++i) {
            boolean isColSafe = ((1 << i) & column) == 0;
            boolean isDiagSafe = ((1 << (n - 1 + row -i )) & diag) == 0;
            boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
            if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                dfs(row + 1, n , (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag);
            }
        }
    }


}
