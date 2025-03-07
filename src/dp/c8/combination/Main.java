package dp.c8.combination;


// nCr = n-1Cr + n-1Cr-1 -> n-1개일 때 n번째를 뽑거나 뽑지 않거나, 두 가지 경우로 나눌 수 있음
public class Main {
    static int[][] Combi;
    public static void main(String[] args) {
        int n = 5;
        int r = 3;
        Combi = new int[n+1][n+1];
        System.out.println(getCombination(5,5));
    }
    public static int getCombination(int n, int r) {
        //base case 1
        if(r==0 || n==r) return Combi[n][r] = 1;
        //base case 1
        if(n < r) return 0;
        //Logic
        if(Combi[n][r]!=0) return Combi[n][r];
        else return Combi[n][r]=(getCombination(n-1,r)+getCombination(n-1,r-1));

    }
}
