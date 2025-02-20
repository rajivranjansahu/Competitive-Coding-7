// Time Complexity : O(log N)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    // TC = O(log n), SC = O(1)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0]; // smallest element
        int right = matrix[n - 1][n - 1]; // largest element
        while (left < right) {
            int mid = left + (right - left) / 2; // middle element of the current range
            int count = getCount(matrix, mid); // count elements <= mid
            System.out.print(count + " ");
            if (count < k) {
                left = mid + 1; // increase the search range
            } else {
                right = mid; // decrease the search range (mid could be the answer)
            }
        }
        return left; // return the kth smallest element
    }

    public int getCount(int[][] matrix, int mid) {
        int count = 0;
        int j = matrix.length - 1; // start from the last column
        for (int i = 0; i < matrix.length; i++) {
            // Move leftward in the row to find elements <= mid
            while (j >= 0 && matrix[i][j] > mid) {
                j--; // move left until matrix[i][j] <= mid
            }
            count += j + 1; // add the number of elements <= mid in this row
        }
        return count;
    }
}
