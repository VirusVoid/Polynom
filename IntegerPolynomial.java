import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by aleks on 02.03.2017.
 */

public class IntegerPolynomial {
    private int[] coef;
    private int power;

    public IntegerPolynomial(int[] coef, int power) {
        if (power < 0) throw new IllegalArgumentException("Степень многочлена не может быть меньше нуля");
        this.coef = coef;
        this.power = getPower();
    }

    public int getPower() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0)
                d = i;
        return d;
    }

    public boolean equal(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        boolean k = true;
        int n = 0;
        if (a.power != other.power) {
            k = false;
        } else {
            for (int i = 0; i < a.coef.length; i++) {
                if (a.coef[i] != other.coef[i]) k = false;
            }
        }
        return k;
    }

    public int meaning(int x) {
        int r = 0;
        int q = 1;
        for (int i = 0; i <= power; i++) {
            r += +coef[i] * q;
            q *= x;
        }
        return r;
    }

    public IntegerPolynomial plus(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        int maxLength = Math.max(a.coef.length, other.coef.length);
        int minLength = Math.min(a.coef.length, other.coef.length);
        int[] max;
        int[] min;
        if (a.coef.length >= other.coef.length) {
            max = a.coef;
            min = other.coef;
        } else {
            max = other.coef;
            min = a.coef;
        }
        int pow = Math.max(a.power, other.power);
        int[] x = new int[maxLength];
        for (int i = 0; i < minLength; i++) {
            x[i] = min[i];
        }
        for (int i = 0; i < maxLength; i++) {
            x[i] += max[i];
        }
        IntegerPolynomial result = new IntegerPolynomial(x, pow);
        return result;
    }

    public IntegerPolynomial minus(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        int maxLength = Math.max(a.coef.length, other.coef.length);
        int minLength = Math.min(a.coef.length, other.coef.length);
        int[] q = new int[maxLength];
        int pow = Math.max(a.power, other.power);
        int[] min;
        if (a.coef.length >= other.coef.length) {
            min = other.coef;
        } else {
            min = a.coef;
        }
        int[] s = new int[maxLength];
        for (int i = 0; i < minLength; i++) {
            s[i] = min[i];
        }
        if (a.power < other.power) {
            for (int i = 0; i < maxLength; i++)
                q[i] = s[i] - other.coef[i];
        } else {
            for (int i = 0; i < maxLength; i++)
                q[i] = a.coef[i] - s[i];
        }
        IntegerPolynomial result = new IntegerPolynomial(q, pow);
        return result;
    }

    public IntegerPolynomial multiply(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        int[] res = new int[a.power + other.power + 1];
        for (int i = 0; i < a.coef.length; i++) {
            for (int j = 0; j < other.coef.length; j++) {
                res[i + j] += a.coef[i] * other.coef[j];
            }
        }
        IntegerPolynomial result = new IntegerPolynomial(res, a.power + other.power);
        return result;
    }

    public IntegerPolynomial multiply(int num, int p) {
        IntegerPolynomial a = this;
        int[] x = new int[a.power + p + 1];
        for (int i = 0; i < a.coef.length; i++)
            x[i + p] = a.coef[i] * num;
        IntegerPolynomial result = new IntegerPolynomial(x, a.power + p);
        return result;
    }

    public IntegerPolynomial divide(IntegerPolynomial other) {
        if (other.power == 0 & other.coef[0] == 0)
            throw new ArithmeticException("Знаменатель не может быть равен нулю");
        IntegerPolynomial a = this;
        IntegerPolynomial temp;
        int[] x = new int[1];
        int k;
        if (power == other.power & a.coef[power] == other.coef[other.power]) {
            x[0] = 1;
        } else if (power < other.power) {
            x[0] = 0;
        } else {
            x = new int[power - other.power + 1];
            k = 0;
            while (power - k >= other.power) {
                x[power - other.power - k] = a.coef[power - k] / other.coef[other.power];
                temp = other.multiply(x[power - other.power - k], power - other.power - k);
                a = a.minus(temp);
                k++;
            }
        }
        IntegerPolynomial result = new IntegerPolynomial(x, x.length - 1);
        return result;
    }


    public IntegerPolynomial remainder(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        IntegerPolynomial result = a.minus((a.divide(other)).multiply(other));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj instanceof IntegerPolynomial) {
            IntegerPolynomial other = (IntegerPolynomial) obj;
            if (power != other.power) return false;
            for (int i = 0; i <= power; i++)
                if (coef[i] != other.coef[i]) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = power;
        for (int a : coef)
            result = 37 * result + a;
        return result;
    }

    @Override
    public String toString() {
        return "Polynomial{" +
                "coefficients=" + coef +
                '}';
    }
}