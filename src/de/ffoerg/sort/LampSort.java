package de.ffoerg.sort;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Utility class for carrying out Lamport's non-recursive quicksort variant.
 * 
 * @author Fabian Foerg
 */
public final class LampSort {
	/**
	 * Hidden constructor. This is a utility class with static methods.
	 */
	private LampSort() {
	}

	/**
	 * Executes Lamport's non-recursive quicksort variant in-place. The given
	 * array will be sorted in ascending order.
	 * 
	 * @param toSort
	 *            the array to sort.
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> toSort) {
		if ((toSort == null) || (toSort.size() < 2)) {
			return;
		}

		Stack<Pair<Integer>> intervals = new Stack<Pair<Integer>>();
		intervals.add(new Pair<Integer>(0, toSort.size() - 1));

		do {
			Pair<Integer> curr = intervals.pop();
			int lo = curr.getFirst();
			int hi = curr.getSecond();
			int span = hi - lo;

			assert ((lo >= 0) && (lo < toSort.size()));
			assert ((hi >= 0) && (hi < toSort.size()));
			assert (lo <= hi);

			if (span >= 2) {
				/*
				 * Select the element with the largest index as the pivot
				 * element.
				 */
				int pivotIndex = lo;
				T pivot = toSort.get(hi);

				/*
				 * Check all elements in partition. If an element is smaller
				 * than the pivot, swap it in at the current pivot index and
				 * increment the pivot index.
				 */
				for (int i = lo; i < hi; i++) {
					if (toSort.get(i).compareTo(pivot) < 0) {
						Collections.swap(toSort, pivotIndex, i);
						pivotIndex++;
					}
				}
				// Swap the pivot at hi in at the right index.
				Collections.swap(toSort, pivotIndex, hi);

				// Create the next two intervals.
				Pair<Integer> loPart = new Pair<Integer>(lo, Math.max(lo,
						pivotIndex - 1));
				Pair<Integer> hiPart = new Pair<Integer>(Math.min(
						pivotIndex + 1, hi), hi);
				intervals.add(loPart);
				intervals.add(hiPart);
			} else if (span == 1) {
				if (toSort.get(lo).compareTo(toSort.get(hi)) > 0) {
					Collections.swap(toSort, lo, hi);
				}
			}
			/*
			 * Otherwise, we only had one element in the interval and we do
			 * nothing.
			 */
		} while (!intervals.isEmpty());
	}

	/**
	 * Pair data type encapsulating two elements of a given type.
	 *
	 * @param <T>
	 *            the type of the elements stored in the pair.
	 */
	private static class Pair<T> {
		private T fst;
		private T snd;

		/**
		 * Constructs a new pair.
		 * 
		 * @param fst
		 *            the first element of the pair.
		 * @param snd
		 *            the second element of the pair.
		 */
		public Pair(T fst, T snd) {
			this.fst = fst;
			this.snd = snd;
		}

		/**
		 * Returns the first element of the pair.
		 * 
		 * @return the first element of the pair.
		 */
		public T getFirst() {
			return fst;
		}

		/**
		 * Returns the second element of the pair.
		 * 
		 * @return the second element of the pair.
		 */
		public T getSecond() {
			return snd;
		}
	}
}
