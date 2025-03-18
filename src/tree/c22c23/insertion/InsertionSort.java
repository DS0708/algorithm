package tree.c22c23.insertion;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = { 4, 3, 1, 5, 10, 9 };
        insertionSorting(arr);
        for(int i : arr) System.out.print(i+" ");
    }
    public static void insertionSorting(int[] arr) {
        for(int i=0; i < arr.length; i++){
            int j = i;
            while(j>0 && arr[j-1] > arr[j]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }
    }
}
