import java.util.*;
public class timeSpace{
    public static void main(String[] args){
        // int[] arr1 = {1,3,6,8,9};
        // int[] arr2 = {2,5,7,10};
        // int[] ans = mergeTwoSortedArray(arr1,arr2);
        // int[] arr = {7, 
        //             -2,
        //             4, 
        //             1, 
        //             3};
        // int[] ans = mergeSort(arr,0,4);
        // displayArray(ans);
        // partition(arr,3);
        // displayArray(arr);
        // quickSort(arr, 0, arr.length - 1);
        // displayArray(arr);
        //System.out.println(quickSelect(arr,0,arr.length - 1,3));
        // int[] arr = {
        //         9,
        //         -48, 
        //         100, 
        //         43, 
        //         84, 
        //         74, 
        //         86, 
        //         34, 
        //         -37, 
        //         60, 
        //         -29, 
        //         44,
        // };
        // targetSumPair(arr,160);
        // int arr[] = {1,0,2,2,1,0,2,1,0,2};
        // sort012(arr);
        // displayArray(arr);
        int arr[] = {4,3,2,1};
        // sort01(arr);
        bubbleSort(arr);
        //selectionSort(arr);
        //insertionSort(arr);
        // int arr[] = { -1, 2, -3, 4, 5, 6, -7, 8, 9 };
        // moveAllNegativeNumber(arr);
    }

    public static void insertionSort(int[] arr){
        for(int i = 1;i<arr.length;i++){
            int j = i;
            while(j> 0 && arr[j-1] > arr[j]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
        displayArray(arr);
    }
    public static void selectionSort(int[] arr) {
        for(int i = 0;i<arr.length-1;i++){
            int minIndex = i;
            for(int j = i+1;j<arr.length;j++){
                if(arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        displayArray(arr);
    }

    public static void bubbleSort(int[] arr){
        for(int i = 0;i<arr.length-1;i++){
            for(int j = 1;j<=arr.length-1-i;j++){
                if(arr[j-1] > arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        displayArray(arr);
    }
    public static void sort01(int[] arr){
        int i =0;
        int j = 0;
        int k = arr.length -1;
        while(i<=k){
            if(arr[i] == 0){
                swap(arr,i,j);
                i++;
                j++;
            }else if(arr[i] == 1){
                i++;
            }
        }
    }

    public static void sort012(int[] arr){
        int i =0;
        int j = 0;
        int k = arr.length -1;
        while(i<=k){
            if(arr[i] == 0){
                swap(arr,i,j);
                i++;
                j++;
            }else if(arr[i] == 1){
                i++;
            }else if(arr[i] == 2){
                swap(arr,i,k);
                k--;
            }
        }
    }
    public static void targetSumPair(int[] arr, int target){
        quickSort(arr,0,arr.length-1);
        int left = 0;
        int right = arr.length -1;
        while(left<right){
            if(arr[left] + arr[right] == target){
                System.out.print(arr[left] + ", "+arr[right]);
                System.out.println();
                left++;
                right--;
            }else if(arr[left] + arr[right] > target){
                right--;
            }else{
                left++;
            }

        }
        
    }
    public static void displayArray(int[] arr){
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int quickSelect(int[] arr, int lo, int hi,int k) {
        int pi = partition(arr,arr[hi],lo,hi);
        if(pi == k-1){
            return arr[pi];
        }else if(pi > k){
            return quickSelect(arr,lo,pi-1,k);   
        }else{
            return quickSelect(arr,pi+1,hi,k);
        }
        
    }
    public static void quickSort(int[] arr, int lo, int hi) {
        if(lo > hi){
            return;
        }
        int pi = partition(arr,arr[hi],lo,hi);
        quickSort(arr,lo,pi-1);
        quickSort(arr,pi+1,hi);
    }

    public static int partition(int[] arr, int pivot,int lo,int hi){
        int i = lo,j=lo;
        while(i <= hi){
            if(arr[i] <= pivot){
                swap(arr,i,j);
                i++;
                j++;
            }else{
                i++;
            }
        }
        return j-1;
    }

    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] mergeSort(int[] arr,int lo,int hi){
        if(hi == lo){
            int[] ans = new int[1];
            ans[0] = arr[hi];
            return ans;
        }
        int mid = (lo +hi) /2;
        int[] arr1 = mergeSort(arr,lo,mid);
        int[] arr2 = mergeSort(arr,mid+1,hi);
        int[] sortedArray = mergeTwoSortedArray(arr1,arr2);

        return sortedArray;
    }
    public static int[] mergeTwoSortedArray(int[] arr1, int[] arr2){
        int[] mergedArray = new int[arr1.length + arr2.length];
        int i = 0,j=0;
        int k = 0;
        while(i<arr1.length && j<arr2.length){
            if(arr1[i] > arr2[j]){
                mergedArray[k] = arr2[j];
                k++;
                j++;
            }else{
                mergedArray[k] = arr1[i];
                i++;
                k++;
            }
        }

        while(i<arr1.length){
            mergedArray[k] = arr1[i];
            i++;
            k++;
        }
        while(j<arr2.length){
            mergedArray[k] = arr2[j];
            j++;
            k++;
        }

        return mergedArray;
    }

    public static void moveAllNegativeNumber(int[] arr){
        int i = 0;
        int j = 0;
        for(int idx = 0;idx<arr.length;idx++){
            if(arr[i] < 0){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j++;
            }else{
                i++;
            }
        }

        displayArray(arr);
    }
}