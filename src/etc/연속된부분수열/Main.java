package etc.연속된부분수열;

public class Main {
    //Time Complexity : O(n^2)
    public static void main(String[] args) {
        int[] arr = {-7, 4, -3, 6, 3, -8, 3, 4};

        int lt=0, rt=0;
        int answer = 0;

        for (int i=0; i<arr.length; i++) {
            int sum = 0;
            for(int j=i; j<arr.length; j++) {
                sum += arr[j];
                answer = Math.max(answer, sum);
            }
        }

        System.out.println(answer);
    }
}
