// Time Complexity : O(nlogn) -> sort + heapify(nlogm), m = max rooms 
// Space Complexity : O(m), size of PQ
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.*;

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // base
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sorting ac to start time
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int[] interval : intervals) {
            if(!pq.isEmpty() && interval[0] >= pq.peek()) { // comparing start time of incoming meeeting with earliest end time (As PQ)
                pq.poll(); // room is reused
            }
            pq.add(interval[1]); // add the end time of incoming meeting (its always added)
        }
        return pq.size(); // at the end, the pq size is no of reqd rooms
    }
}

class Solution {
    // TC = O(nlogn), SC = O(n)
    public static int minMeetingRooms(int[][] intervals) {
        // base
        if(intervals == null || intervals.length == 0)
            return 0;
        int room = 0, n = intervals.length, i = 0;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int[] interval: intervals) {
            start[i] = interval[0];
            end[i] = interval[1];
            i++;
        }
        Arrays.sort(start); // sort start & end time irrespective of how they appear, as we are concerned about start & end time only
        Arrays.sort(end);
        int a = 0, b = 0, count = 0;
        for(int j = 0; j < n; j++) {
            if(start[a] < end[b]) { // now compare start & end time, if start is smaller, add room
                count++;
                a++;
            }
            else { // now compare start & end time, if start is greater or equal, reduce room
                count--;
                b++;
            }
            room = Math.max(count, room); // keep track of max rooms used
        }
        return room;
    }
    public static void main(String[] args) {
        int[][] intervals = {{0,30},{5,10},{15,20}};
        int[][] intervals2 = {{7,10},{2,4}};
        System.out.println(minMeetingRooms(intervals));
    }
}