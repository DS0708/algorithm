package dp.c8.initarr;


import java.util.Arrays;

//배열을 간단하게 초기화 하는 방법
public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] arr = new int[n][n];

        System.out.println("Arrays.fill 사용");
        for(int[] i : arr) Arrays.fill(i,-1);
        //출력
        for(int[] row: arr){
            for(int col: row) System.out.print(col+" ");
            System.out.println();
        }

        System.out.println("Stream 사용");
        Arrays.stream(arr).forEach(row -> Arrays.fill(row,-2));
        //출력
        for(int[] row: arr){
            for(int col: row) System.out.print(col+" ");
            System.out.println();
        }
    }
}
