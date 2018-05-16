package leetcode_algorithm;

import java.util.Stack;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * For example, given the following matrix:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Return 6.
 */

public class q085_MaximalRectangle {


    public static void main(String[] args) {
        char[][] nums = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        q085_MaximalRectangle solution = new q085_MaximalRectangle();
        System.out.println(solution.maximalRectangle(nums));
    }

    /**
     * ��� q084_LargestRectangleInHistogram
     * ������������H���г��ȼ�¼��߶�Ϊ'1'��������ɨ��͸������ҳ�ÿ�е������Ρ�
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int cLen = matrix[0].length;
        int rLen = matrix.length;
        int[] h = new int[cLen + 1];
        h[cLen] = 0;
        int max = 0;

        for (int row = 0; row < rLen; row++) {
            Stack<Integer> s = new Stack<>();
            for (int i = 0; i < cLen + 1; i++) {
                //����ÿһ�еĸ߶�
                if (i < cLen) {
                    if (matrix[row][i] == '1') h[i] += 1;
                    else h[i] = 0;
                }
                //����q084_LargestRectangleInHistogram���㷨������е�������
                if (s.isEmpty() || h[s.peek()] <= h[i]) s.push(i);
                else {
                    while (!s.isEmpty() && h[i] < h[s.peek()]) {
                        int top = s.pop();
                        int area = h[top] * (s.isEmpty() ? i : (i - s.peek() - 1));
                        if (area > max) max = area;
                    }
                    s.push(i);
                }
            }
        }
        return max;
    }
}
