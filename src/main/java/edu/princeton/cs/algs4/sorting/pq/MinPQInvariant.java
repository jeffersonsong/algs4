package edu.princeton.cs.algs4.sorting.pq;

public abstract class MinPQInvariant {
    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    protected void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    protected void sink(int k) {
        int n = size();
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    protected boolean isMinHeapOrdered(int k) {
        int n = size();
        if (k > n) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= n && greater(k, left))  return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    abstract int size();

    protected abstract boolean greater(int i, int j);

    protected abstract void exch(int i, int j);
}
