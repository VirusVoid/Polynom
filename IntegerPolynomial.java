/**
 * Created by aleks on 02.03.2017.
 */

public class IntegerPolynomial {
    private int power;
    private int[] coef;

    int a;
    public IntegerPolynomial(int c, int pow) {

        if (pow < 1) throw new IllegalArgumentException("Степень многочлена не может быть меньше 1");
        power = getPower();
        coef = new int[pow + 1];
        coef[pow] = c;
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
        if (a.power != other.power) return false;
        for (int i = 0; i <= a.power; i++)
            if (a.coef[i] != other.coef[i]) return false;
        return true;
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
        IntegerPolynomial result = new IntegerPolynomial(0, Math.max(a.power, other.power));
        for (int i = 0; i <= a.power; i++)
            result.coef[i] = result.coef[i] + a.coef[i];
        for (int i = 0; i <= other.power; i++)
            result.coef[i] += other.coef[i];
        result.power = result.getPower();
        return result;
    }

    public IntegerPolynomial minus(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        IntegerPolynomial result = new IntegerPolynomial(0, Math.max(a.power, other.power));
        for (int i = 0; i <= a.power; i++)
            result.coef[i] = result.coef[i] + a.coef[i];
        for (int i = 0; i <= other.power; i++)
            result.coef[i] = result.coef[i] - other.coef[i];
        result.power = result.getPower();
        return result;
    }

    public IntegerPolynomial multiply(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        IntegerPolynomial result = new IntegerPolynomial(0, a.power + other.power);
        for (int i = 0; i <= a.power; i++)
            for (int j = 0; j <= other.power; j++)
                result.coef[i + j] = result.coef[i + j] + (a.coef[i] * other.coef[j]);
        result.power = result.getPower();
        return result;
    }

    public IntegerPolynomial divide(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        IntegerPolynomial temp;
        IntegerPolynomial result = new IntegerPolynomial(0, a.power - other.power);
        int k;
        if (power == other.power) {
            result.coef[0] = 1;
        } else if (power < other.power) {
            result.coef[0] = 0;
        } else {
            k = 0;
            while (power - k >= other.power) {
                result.coef[power - other.power - k] = a.coef[power - k] / other.coef[other.power];
                temp = other.mult(result.coef[power - other.power - k], result.power);
                a = a.minus(temp);
                k++;
            }
        }
        return result;
    }

    public IntegerPolynomial mult(int num, int p) {
        IntegerPolynomial a = this;
        IntegerPolynomial result = new IntegerPolynomial(0, a.power + p);
        for (int i = 0; i <= a.power; i++)
            result.coef[i + p] = (a.coef[i] * num);
        result.power = result.getPower();
        return result;
    }

    public IntegerPolynomial remainder(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        return a.minus((a.divide(other)).multiply(other));
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