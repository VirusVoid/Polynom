import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerPolynomialTest {

    @Test
    public void equal() {
        int[] a = {2, 6, 7, 4, 2, 1};
        int[] b = {2, 6, 7, 4, 2, 1};
        assertTrue(new IntegerPolynomial(a).equal(new IntegerPolynomial(b)));
        int[] a1 = {2, 6, 7, 4, 2, 1};
        int[] b1 = {2, 6, 7, 1};
        assertFalse(new IntegerPolynomial(a1).equal(new IntegerPolynomial(b1)));
    }

    @Test
    public void meaning() {
        int[] c = {2, 0, 3, 1, 6};
        assertEquals(10, new IntegerPolynomial(c).meaning(-1));
        int[] c1 = {7};
        assertEquals(7, new IntegerPolynomial(c1).meaning(3));
    }

    @Test(expected = NullPointerException.class)
    public void nullException() {
        int[] a = null;
        new IntegerPolynomial(a);
    }

    @Test(expected = IllegalArgumentException.class)
    public void polynomialNotExist() {
        int[] a = {};
        new IntegerPolynomial(a);
    }

    @Test
    public void plus() {
        int[] a = {5, 1, 1};
        int[] b = {6, 1, 1};
        int[] c = {11, 2, 2};
        int k = 8;
        assertEquals(new IntegerPolynomial(c),
                new IntegerPolynomial(a).plus(new IntegerPolynomial(b)));
        int[] d = {1, 2, 0, 3};
        int[] e = {25, 65};
        int[] f = {26, 67, 0, 3};
        assertEquals(new IntegerPolynomial(f),
                new IntegerPolynomial(d).plus(new IntegerPolynomial(e)));
        int[] d1 = {5, -4, 5, 1};
        int[] e1 = {2, -1, 3, -1};
        int[] f1 = {7, -5, 8};
        assertEquals(new IntegerPolynomial(f1),
                new IntegerPolynomial(d1).plus(new IntegerPolynomial(e1)));
    }

    @Test
    public void minus() {
        int[] g = {2, 0, 6};
        int[] h = {3, -2, 4, 12};
        int[] k = {-1, 2, 2, -12};
        assertEquals(new IntegerPolynomial(k),
                new IntegerPolynomial(g).minus(new IntegerPolynomial(h)));
        int[] g1 = {-4, 8, 3, 6};
        int[] h1 = {3, 8, -5};
        int[] k1 = {-7, 0, 8, 6};
        assertEquals(new IntegerPolynomial(k1),
                new IntegerPolynomial(g1).minus(new IntegerPolynomial(h1)));
    }

    @Test
    public void multiply() {
        int[] l = {-3, 5};
        int[] m = {4, -3};
        int[] n = {-12, 29, -15};
        assertEquals(new IntegerPolynomial(n),
                new IntegerPolynomial(l).multiply(new IntegerPolynomial(m)));
        int[] l1 = {5, 6, 1};
        int[] m1 = {2, 0, 0, -3, 2};
        int[] n1 = {10, 12, 2, -15, -8, 9, 2};
        assertEquals(new IntegerPolynomial(n1),
                new IntegerPolynomial(l1).multiply(new IntegerPolynomial(m1)));
        int[] l2 = {0, 0, 5};
        int[] m2 = {2, 0, 0, -3, 2};
        int[] n2 = {0, 0, 10, 0, 0, -15, 10};
        assertEquals(new IntegerPolynomial(n2),
                new IntegerPolynomial(l2).multiply(new IntegerPolynomial(m2)));
    }

    @Test
    public void divide() {
        int[] p = {5, -3, 0, 2};
        int[] q = {-4, 1};
        int[] r = {29, 8, 2};
        assertEquals(new IntegerPolynomial(r),
                new IntegerPolynomial(p).divide(new IntegerPolynomial(q)));
        int[] p1 = {-2};
        int[] q1 = {7, -2, 0, 1};
        int[] r1 = {0};
        assertEquals(new IntegerPolynomial(r1),
                new IntegerPolynomial(p1).divide(new IntegerPolynomial(q1)));
        int[] p2 = {0};
        int[] q2 = {7, -2, 0, 1};
        int[] r2 = {0};
        assertEquals(new IntegerPolynomial(r2),
                new IntegerPolynomial(p2).divide(new IntegerPolynomial(q2)));
        int[] p3 = {5, -2};
        int[] q3 = {7, 1};
        int[] r3 = {-2};
        assertEquals(new IntegerPolynomial(r3),
                new IntegerPolynomial(p3).divide(new IntegerPolynomial(q3)));
        int[] p4 = {5, 1};
        int[] q4 = {7, 1};
        int[] r4 = {1};
        assertEquals(new IntegerPolynomial(r4),
                new IntegerPolynomial(p4).divide(new IntegerPolynomial(q4)));
    }

    @Test(expected = ArithmeticException.class)
    public void divideZero() {
        int[] p2 = {5, -9, 1, 4, 2, 3};
        int[] q2 = {0};
        new IntegerPolynomial(p2).divide(new IntegerPolynomial(q2));
    }

    @Test
    public void remainder() {
        int[] s = {-2, 10, 12, -5, 1, 2};
        int[] t = {7, -2, 0, 1};
        int[] v = {5, 1};
        assertEquals(new IntegerPolynomial(v),
                new IntegerPolynomial(s).remainder(new IntegerPolynomial(t)));
        int[] s1 = {5, -3, 0, 2};
        int[] t1 = {-4, 1};
        int[] v1 = {121};
        assertEquals(new IntegerPolynomial(v1),
                new IntegerPolynomial(s1).remainder(new IntegerPolynomial(t1)));
    }
}