package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int number) throws IllegalNumberException {

        if (number < 2) {
            throw new IllegalNumberException();
        } else {
            for (int i = 2; i * i <= number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int res = 0;

        while (number != 0) {
            int tmp = number % 10;
            res += tmp;
            number /= 10;
        }
        return res;
    }

    public static class IllegalNumberException extends RuntimeException {
    }
}
