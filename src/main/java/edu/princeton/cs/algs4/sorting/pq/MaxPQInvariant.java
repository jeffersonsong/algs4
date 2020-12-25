package edu.princeton.cs.algs4.sorting.pq;

public abstract class MaxPQInvariant {
    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    protected void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    protected void sink(int k) {
        int n = size();
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    protected boolean isMaxHeapOrdered(int k) {
        int n = size();
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    protected abstract boolean less(int i, int j);

    protected abstract void exch(int i, int j);

    abstract int size();
}
