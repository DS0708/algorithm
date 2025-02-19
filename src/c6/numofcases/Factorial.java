package c6.numofcases;

//서로 다른 N개의 원소를 일렬로 줄 세운 경우의 수 구하기
//n!
public class Factorial {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(facto(n));
    }
    public static int facto(int n){
        if(n==1) return 1;
        else return n * facto(n-1);
    }
}
