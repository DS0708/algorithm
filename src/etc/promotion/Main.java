package etc.promotion;

public class Main {
    public static void main(String[] args) {
        // 예제 1: byte와 byte의 연산 (자동으로 int로 프로모션됨)
        byte a = 10;
        byte b = 20;
        int resultByteAddition = a + b; // byte들이 int로 자동 프로모션됨
        // autoboxing으로 Integer 객체로 변환하여 자료형 확인
        Integer boxedResult1 = resultByteAddition;
        System.out.println("byte + byte = " + resultByteAddition + " (Type: "
                + boxedResult1.getClass().getSimpleName() + ")");

        // 예제 2: int와 double의 연산 (int가 double로 프로모션됨 → 결과는 double)
        int x = 5;
        double y = 2.0;
        double resultIntDouble = x / y;  // 연산에서 int가 double로 자동 변환됨
        Double boxedResult2 = resultIntDouble;
        System.out.println("int / double = " + resultIntDouble + " (Type: "
                + boxedResult2.getClass().getSimpleName() + ")");

        // 예제 3: long과 float의 연산 (long이 float로 프로모션됨 → 결과는 float)
        long longValue = 100L;
        float floatValue = 3.14f;
        float resultLongFloat = longValue * floatValue; // long이 float로 변환 후 연산
        Float boxedResult3 = resultLongFloat;
        System.out.println("long * float = " + resultLongFloat + " (Type: "
                + boxedResult3.getClass().getSimpleName() + ")");

        // 예제 4: float와 double의 연산 (float가 double로 프로모션됨 → 결과는 double)
        float f = 2.5f;
        double d = 4.0;
        double resultFloatDouble = f * d; // f가 double로 변환됨
        Double boxedResult4 = resultFloatDouble;
        System.out.println("float * double = " + resultFloatDouble + " (Type: "
                + boxedResult4.getClass().getSimpleName() + ")");

        // 예제 5: 명시적 형 변환 (double을 int로 캐스팅 → 소수점 이하 손실)
        double originalDouble = 123.456;
        int convertedInt = (int) originalDouble;
        Integer boxedResult5 = convertedInt;
        System.out.println("double -> int (explicit cast): " + convertedInt + " (Type: "
                + boxedResult5.getClass().getSimpleName() + ")");
    }
}
