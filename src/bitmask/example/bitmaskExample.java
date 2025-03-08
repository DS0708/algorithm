package bitmask.example;

//10가지의 토핑을 가지고 있는 피자
public class bitmaskExample {
    public static void main(String[] args) {
        //공집합과 꽉 찬 집합 구하기
        int fullPizza = (1 << 10) -1 ; //꽉 찬 집합
        int none = 0;   //공집합
        System.out.println("공집합");
        printBinaryPizza(none);
        System.out.println("꽉찬 집합");
        printBinaryPizza(fullPizza);
        System.out.println();

        //원소 추가, 해당 비트를 키는 것
        //페페로니의 번호가 3번일때, 전체 0~19
        System.out.println("3번째 토핑 추가");
        int p = 3;
        int toppings = 0;
        toppings |= (1<<p);
        printBinaryPizza(toppings);
        System.out.println();

        //원소 포함 여부 확인,
        //확인하고 싶은 원소의 번호만 켜져있는 binary와 and연산 적용하여 0이 아닌 숫자이면 해당 원소가 존재한다는 것
        System.out.println("3번째 토핑이 존재하는지 확인");
        if((toppings & (1<<p)) != 0 ) System.out.println("Pepperoni is in");
        System.out.println();

        //원소의 삭제, 삭제하고 싶은 토핑부분만 0으로 만들고 나머지는 1으로 만들어서 and 연산을 진행하면 됨
        System.out.println("3번째 토핑 삭제");
        toppings &= ~(1<<p);
        printBinaryPizza(toppings);
        System.out.println();

        //원소의 토글,
        //토글은 해당 비트가 켜져있으면 끄고, 꺼져 있으면 켜는 것임
        //즉, 해당 토핑 부분만 켜져있는 binary에 XOR연산을 하면 됨
        System.out.println("원소의 토글");
        System.out.println("공집합에서 2번째 토핑 토글");
        toppings ^= (1<<2);
        printBinaryPizza(toppings);
        System.out.println("거기서 다시 한 번 2번째 토핑 토글");
        toppings ^= (1<<2);
        printBinaryPizza(toppings);
        System.out.println();

        //두 집합 연산하기
        int set1 = none | (1<<1);
        set1 |= (1<<2);
        int set2 = none | (1<<2);
        set2 |= (1<<3);
        System.out.println("set1, set2");
        printBinaryPizza(set1);
        printBinaryPizza(set2);
        System.out.println("합집합");
        printBinaryPizza(set1 | set2);
        System.out.println("교집합");
        printBinaryPizza(set1 & set2);
        System.out.println("set1에서 set2를 뺀 차집합");
        printBinaryPizza(set1 & ~set2);
        System.out.println("set1과 set2중 하나에만 포함된 원소들의 집합");
        printBinaryPizza(set1 ^ set2);
        System.out.println();

        //집합의 크기 구하기
        System.out.println("set1의 크기");
        System.out.println(Integer.bitCount(set1));

        //최소 원소 찾기
        System.out.println("set1의 최소 원소의 번호 찾기");
        System.out.println(Integer.numberOfTrailingZeros(set1));
        System.out.println("set1의 최소 원소의 결과를 비트마스크로 나타내기");
        printBinaryPizza(set1 & -set1);
        System.out.println();

        //최소 원소 지우기
        System.out.println("set1의 최소 원소 지우기");
        printBinaryPizza(set1 & (set1 - 1));
        System.out.println();

        //모든 부분 집합 순회하기
        int set3 = none;
        set3 |= (1<<1);
        set3 |= (1<<3);
        set3 |= (1<<5);
        System.out.println("set3");
        printBinaryPizza(set3);
        System.out.println("set3의 부분 집합 순회하기");
        for(int subset=set3; subset!=0; subset= ((subset-1) & set3) ){
            printBinaryPizza(subset);
        }
        //공집합까지출력
        printBinaryPizza(none);
    }
    public static void printBinaryPizza(int n) {
        System.out.println(String.format("%10s",Integer.toBinaryString(n)).replace(' ', '0'));
    }
}
