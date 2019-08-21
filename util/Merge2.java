public static void merge2(int[] arr, int lo, int mid, int hi) {
        // prepartcopy is original array's previous part copy([lo, mid])
        int[] prePartCopy = new int[mid - lo + 1];
        for (int i = 0; i < prePartCopy.length; i++) {
            prePartCopy[i] = arr[lo + i]; 
        }
        // traverse from prepartcopy's first position as j, original array mid + 1 position as k
        // and original array lo position as i for merge part prepartcopy and original array (mid, hi] part to orginal array
        // when have none element both prepartcopy part and original array (mid, hi] part terminal iteration
        for (int i = lo, j = 0, k = mid + 1; (j < prePartCopy.length) || (k <= hi);) {
            // if prepartcopy not run out and 
            // original array (mid, hi] part is run out or current element of position j is equal or smaller than k
            // copy prepartcopy element in position j to orginal array
            if ((j < prePartCopy.length) && ((hi < k) || (prePartCopy[j] <= arr[k]))) {
                arr[i++] = prePartCopy[j++];
            }
            // same to above
            if ((k <= hi) && ((prePartCopy.length <= j) || (arr[k] < prePartCopy[j]))) {
                arr[i++] = arr[k++];
            }
        }
    }
