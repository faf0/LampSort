package de.ffoerg.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Test class for Lamport's non-recursive variant of quicksort. Tests random
 * input.
 * 
 * @author Fabian Foerg
 */
public final class LampSortTest {
	/**
	 * Runs the tests.
	 * 
	 * @param args
	 *            ignored.
	 */
	public static void main(String[] args) throws NotSortedException {
		Random rand = new Random();

		for (int i = 0; i <= 12345; i++) {
			List<Integer> list = new ArrayList<Integer>(i);
			fillList(rand, list, i);
			LampSort.sort(list);
			if (!isSorted(list)) {
				throw new NotSortedException(i);
			}
		}
	}

	/**
	 * Fills the given list with random integers produced by the given random
	 * number generator.
	 * 
	 * @param rand
	 *            the random number generator.
	 * @param list
	 *            the list to fill.
	 */
	private static void fillList(Random rand, List<Integer> list, int size) {
		assert (size >= 0);

		for (int i = 0; i < size; i++) {
			Integer r = new Integer(rand.nextInt());
			list.add(r);
		}
	}

	/**
	 * Returns whether the given list is sorted in ascending order.
	 * 
	 * @param list
	 *            the list to check.
	 * @return <code>true</code>, if the given list is sorted in ascending
	 *         order. Otherwise, <code>false</code> is returned.`
	 */
	private static <T extends Comparable<? super T>> boolean isSorted(
			List<T> list) {
		boolean result = true;

		for (int i = 1; (i < list.size()) && result; i++) {
			if (list.get(i - 1).compareTo(list.get(i)) > 0) {
				result = false;
			}
		}

		return result;
	}

	/**
	 * Exception that is thrown when a list is not sorted in ascending order.
	 */
	private static class NotSortedException extends Exception {
		private static final long serialVersionUID = 4431951670579499843L;

		/**
		 * Exception indicating that an array is not correctly sorted.
		 * 
		 * @param i
		 *            the length of the array.
		 */
		public NotSortedException(int i) {
			super("Array of length " + i + " is not sorted!");
		}
	}
}
