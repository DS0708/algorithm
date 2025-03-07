package c8.dp.quantize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//시간 복잡도 O(N*S*N)
public class DP {
    //최대 오차 제곱 합 = 1000^2 * 100 = 100,000,000
    static int INF = (int)1e8+1;
    static int N,S;
    static int[] Arr;
    static int[][] Cache;
    static int[] pSum;
    static int[] pSqSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        //Logic
        for(int c=0; c<cases; c++) {
            //init
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());
            Arr = new int[N];
            Cache = new int[N][S+1];
            Arrays.stream(Cache).forEach(row -> Arrays.fill(row, -1));
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++) Arr[i] = Integer.parseInt(st.nextToken());
            preCalc();

            //debug
//            System.out.println("Case #"+(c+1));
//            System.out.println("부분합 출력");
//            for(int i : pSum) System.out.print(i+" ");
//            System.out.println("\n부분 제곱 합 출력");
//            for(int i : pSqSum) System.out.print(i+" ");
//            System.out.println();
            //debug

            //Logic
            System.out.println(quantize(0,S));
        }
    }
    public static int quantize(int from, int parts){
        //base case 1 : 모든 숫자 양자화 완료
        if(from==N) {

            //debug
//            System.out.println("base case 1 : return 0 -> from:" + from + ", parts:" + parts);
            //debug

            return 0;
        }
        //base case 2 : 숫자가 남아있지만 더 이상 사용할 수 있는 부분 집합이 없을 떄
        if(parts==0) {

            //debug
//            System.out.println("base case 2 : return INF -> from:" +from + ", parts:"+parts);
            //debug

            return INF;
        }
        //Memoization
        if(Cache[from][parts]!=-1) {

            //debug
//            System.out.println("메모이제이션!! -> from:" +from + ", parts:"+parts);
            //debug

            return Cache[from][parts];
        }
        //Logic

        //debug
//        System.out.println("quantize() Logic 시작 -> from:" +from + ", parts:"+parts);
        //debug

        Cache[from][parts] = INF;
        for(int size=1; from+size<=N; size++){

            //debug
//            System.out.println();
//            System.out.println("for문 로직 시작!! -> from:" +from + ", parts:"+parts + " -> quantize("+(from+size)+","+(parts-1)+ ") 호출!!");
            //debug

            int minErrorVal = minError(from,from+size-1);
            Cache[from][parts] = Math.min(Cache[from][parts], quantize(from+size, parts-1) + minErrorVal);

            //debug
//            System.out.printf("for문 로직 종료!! -> from:" +from + ", parts:"+parts + " -> quantize("+(from+size)+","+(parts-1)+ ") 반환!!"
//                    + ", minError(%d,%d): "+ minErrorVal+"\n", from, from+size-1);
//            System.out.println();
            //debug

        }

        //debug
//        System.out.printf("quantize() Logic 종료 -> from:" +from + ", parts:"+parts + ", Cache[%d][%d]:%d\n", from,parts,Cache[from][parts]);
        //debug

        return Cache[from][parts];
    }
    //검증 완료
    public static int minError(int from, int to) {
        // S(Arr[i]-m)^2 = S(Arr[i]^2 -2mArr[i] + m^2) , S=sigma
        int sum = pSum[to] - (from==0 ? 0 : pSum[from-1]);
        int sqSum = pSqSum[to] - (from==0 ? 0 : pSqSum[from-1]);
        int mean = (int)(0.5+(double)sum/(to-from+1));
        int ret = sqSum - 2*mean*sum + (to-from+1)*mean*mean;

        //debug
//        System.out.printf("minError(%d,%d) 호출 !! -> sum:%d, sqSum:%d, mean:%d, ret:%d \n", from,to,sum,sqSum,mean,ret);
        //debug

        return ret;
    }
    // 검증완료된 함수
    public static void preCalc(){
        Arrays.sort(Arr);
        pSum = new int[N];
        pSqSum = new int[N];
        pSum[0] = Arr[0];
        pSqSum[0] = Arr[0] * Arr[0];
        for(int i=1; i<N; i++) {
            pSum[i] = Arr[i] + pSum[i-1];
            pSqSum[i] = Arr[i]*Arr[i] + pSqSum[i-1];
        }
    }
}

//문제 : https://algospot.com/judge/problem/read/QUANTIZE

//입력
/*
2
10 3
3 3 3 1 2 3 2 2 2 1
9 3
1 744 755 4 897 902 890 6 777
 */

//출력
/*
0
651
 */

//Test Case
/*
1
3 2
1 2 3

1
10 10
1000 1000 1000 1000 1000 1000 1000 1000 1000 1000

1
5 10
1000 1000 1000 1000 1000

1
1 1
1
 */
