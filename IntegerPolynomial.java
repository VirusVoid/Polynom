public class IntegerPolynomial {
    private int[] coef;

    public int getDegree(int[] a) {
        int d = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0)
                d = i;
        }
        return d;
    }

    public IntegerPolynomial(int[] coef) {
        int[] coeff = new int[getDegree(coef) + 1];
        if (coef.length == 0) throw new IllegalArgumentException("Многочлен не существует");
        if (coef == null) throw new NullPointerException("Null");
        System.arraycopy(coef, 0, coeff, 0, getDegree(coef) + 1);
        this.coef = coeff;
    }


    public boolean equal(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        boolean k = true;
        if (getDegree(a.coef) != getDegree(other.coef)) {
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
        for (int i = 0; i < coef.length; i++) {
            r += coef[i] * q;
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
        int[] x = new int[maxLength];
        for (int i = 0; i < minLength; i++) {
            x[i] = min[i];
        }
        for (int i = 0; i < maxLength; i++) {
            x[i] += max[i];
        }
        IntegerPolynomial result = new IntegerPolynomial(x);
        return result;
    }


    public IntegerPolynomial minus(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        int maxLength = Math.max(a.coef.length, other.coef.length);
        int minLength = Math.min(a.coef.length, other.coef.length);
        int[] q = new int[maxLength];
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
        if (getDegree(a.coef) < getDegree(other.coef)) {
            for (int i = 0; i < maxLength; i++)
                q[i] = s[i] - other.coef[i];
        } else {
            for (int i = 0; i < maxLength; i++)
                q[i] = a.coef[i] - s[i];
        }
        IntegerPolynomial result = new IntegerPolynomial(q);
        return result;
    }

    public IntegerPolynomial multiply(IntegerPolynomial other) {
        IntegerPolynomial a = this;
        int[] res = new int[getDegree(a.coef) + getDegree(other.coef) + 1];
        for (int i = 0; i < a.coef.length; i++) {
            for (int j = 0; j < other.coef.length; j++) {
                res[i + j] += a.coef[i] * other.coef[j];
            }
        }
        IntegerPolynomial result = new IntegerPolynomial(res);
        return result;
    }

    private IntegerPolynomial multiply(int num, int p) {
        IntegerPolynomial a = this;
        int[] x = new int[getDegree(a.coef) + p + 1];
        for (int i = 0; i < a.coef.length; i++)
            x[i + p] = a.coef[i] * num;
        IntegerPolynomial result = new IntegerPolynomial(x);
        return result;
    }

    public IntegerPolynomial divide(IntegerPolynomial other) {
        if (getDegree(other.coef) == 0 & other.coef[0] == 0)
            throw new ArithmeticException("Знаменатель не может быть равен нулю");
        IntegerPolynomial a = this;
        IntegerPolynomial temp;
        int[] x = new int[a.coef.length];
        int dividentdegree = getDegree(a.coef);
        int divisordegree = getDegree(other.coef);
        while (dividentdegree >= divisordegree) {
            x[dividentdegree - divisordegree] = a.coef[dividentdegree] / other.coef[divisordegree];
            temp = other.multiply(x[dividentdegree - divisordegree], dividentdegree - divisordegree);
            a = a.minus(temp);
            dividentdegree--;

        }
        IntegerPolynomial result = new IntegerPolynomial(x);
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
            if (getDegree(((IntegerPolynomial) obj).coef) != getDegree(other.coef)) return false;
            for (int i = 0; i <= getDegree(other.coef); i++)
                if (((IntegerPolynomial) obj).coef[i] != other.coef[i]) return false;
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(coef);
    }

    @Override
    public String toString() {
        return "Polynomial{" +
                "coefficients=" + coef +
                '}';
    }
}