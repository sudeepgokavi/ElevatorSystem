package main.com.elevatorsystem.utilities;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSearchAlgorithm {

	private Integer findCrossOver(final Integer arr[], final Integer low, final Integer high, final Integer x) {

		if (arr[high] <= x) {
			return high;
		}
		
		if (arr[low] > x) {
			return low;
		}

		Integer mid = (low + high) / 2;

		if (arr[mid] <= x && arr[mid + 1] > x) {
			return mid;
		}

		if (arr[mid] < x) {
			return findCrossOver(arr, mid + 1, high, x);
		}

		return findCrossOver(arr, low, mid - 1, x);
	}

	public List<Integer> nearestNumbers(final Integer arr[], final Integer x, final Integer k, final Integer n) {
		List<Integer> nearestNoList = new ArrayList<Integer>();

		Integer l = findCrossOver(arr, 0, n - 1, x);
		Integer r = l + 1;
		Integer count = 0;

		if (arr[l] == x) {
			l--;
		}

		while (l >= 0 && r < n && count < k) {
			if (x - arr[l] < arr[r] - x) {
				nearestNoList.add(arr[l--]);
			} else {
				nearestNoList.add(arr[r++]);
			}
			count++;
		}

		while (count < k && l >= 0) {
			nearestNoList.add(arr[l--]);
			count++;
		}

		while (count < k && r < n) {
			nearestNoList.add(arr[r++]);
			count++;
		}
		return nearestNoList;
	}

	public static void main(String args[]){
		ElevatorSearchAlgorithm esa = new ElevatorSearchAlgorithm();
		Integer arr[] = {1,8};
		int n = arr.length;
		int x = 7, k = 1;
		List<Integer> l = esa.nearestNumbers(arr, x, k, n);
		System.out.println(l);
	}
}
