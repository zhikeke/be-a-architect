package com.ke.aboutleetcode;

public class _1095 {
    public static int findInMountainArray(int target, MountainArray mountainArr) {
       int lo = 0, hi = mountainArr.length() - 1;

       while (lo + 1 < hi) {
           int mid = lo + (hi - lo) / 2;
           int midVal = mountainArr.get(mid);

           if (midVal > mountainArr.get(mid - 1)) {
               lo = mid;
           } else {
               hi = mid;
           }
       }

       int peekIndex = mountainArr.get(lo) > mountainArr.get(hi) ? lo : hi;

       int idx = binSearch(mountainArr, 0, peekIndex, target, true);

       return idx == -1 ? binSearch(mountainArr, peekIndex + 1, mountainArr.length() - 1, target, false) : idx;
    }

    private static int binSearch(MountainArray mountainArr, int lo, int hi, int target, boolean asc) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int midVal = mountainArr.get(mid);
            if (midVal == target) {
                return mid;
            } else if (midVal > target) {
                if (asc) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (asc) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }

        return  -1;
    }


    public static void main(String[] args) {
        MountainArray mountainArray = new MountainArray();
        System.out.println(_1095.findInMountainArray(3, mountainArray));
    }
}
