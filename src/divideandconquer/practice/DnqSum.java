package divideandconquer.practice;


/*
1 + 2 + 3 + ... + n
= (1 + 2 + ... + n/2) + ( (n/2+1) + (n/2+2) + (n/2+3) + ... + (n/2+n/2) )
= (1 + 2 + ... + n/2) + n/2*n/2 + (1 + 2 + ... + n/2)
= 2*(1 + 2 + ... + n/2) + n^2/4
 */
// O(LogN)
public class DnqSum {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(fastSum(n));
    }
    public static int fastSum(int n){
        if(n==1) return 1;
        if(n%2==1) return fastSum(n-1) + n;
        return 2*fastSum(n/2) + n*n/4;
    }
}
