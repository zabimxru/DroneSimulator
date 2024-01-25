package Katze.DroneSimulation.logic;

import java.util.Arrays;

//import Katze.DroneSimulation.data.ui.HomepageResultlistData;
import Katze.DroneSimulation.data.api.Drone;

public class MergeSort<T extends Comparable<T>> {
	public void mergeSort(T[] array, int low, int high) {
		if(low < high) {
			int mid = (low + high)/2;
			
			//Sort the first and second halves
			mergeSort(array, low, mid);
			mergeSort(array, mid +1, high);
			
			//Merge the sorted halves
			merge(array, low, mid, high);
		}
	}
	
	private void merge(T[] array, int low, int mid, int high) {
		int n1 = mid - low + 1;
		int n2 = high - mid;
		
		//Create temporary arrays
		T[] leftArray = Arrays.copyOfRange(array, low, low+n1);
		T[] rightArray = Arrays.copyOfRange(array, mid + 1, mid + 1 + n2);
		
		
		// Merge the temp arrays
		int i = 0, j = 0;
		int k = low;
		
		while (j < n1 && j < n2) {
			//Compare elements and update the main array
			if(leftArray[i].compareTo(rightArray[j]) <= 0) {
				array[k] = leftArray[i];
				i++;
			} else {
				array[k] = rightArray[j];
				j++;
			}
			k++;
		}
		
		//Copy remaining elements of leftArray if any
		while (i < n1) {
			array[k] = leftArray[i];
			i++;
			k++;
		}
		
		while (j < n2) {
			array[k] = rightArray[j];
			j++;
			k++;
		}

	} 
}
