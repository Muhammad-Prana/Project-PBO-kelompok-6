package number;

public class Narrowing {

    double vrDouble = 123.456;
    float vrFloat2 = (float) vrDouble;
    long vrLong2 = (long) vrFloat2;
    int vrInt2 = (int) vrLong2;
    short vrShort2 = (short) vrInt2;

}
