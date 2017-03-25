import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aleks on 09.03.2017.
 */
public class IntegerPolynomialTest {

    @Test
    public void equal() {
        int[] a = {2, 6, 7, 4, 2, 1};
        int[] b = {2, 6, 7, 4, 2, 1};
        assertTrue(new IntegerPolynomial(a, a.length - 1).equal(new IntegerPolynomial(b, b.length - 1)));
        int[] a1 = {2, 6, 7, 4, 2, 1};
        int[] b1 = {2, 6, 7, 1};
        assertFalse(new IntegerPolynomial(a1, a1.length - 1).equal(new IntegerPolynomial(b1, b1.length - 1)));
    }

    @Test
    public void meaning() {
        int[] c = {2, 0, 3, 1, 6};
        assertEquals(10, new IntegerPolynomial(c, c.length - 1).meaning(-1));
        int[] c1 = {7};
        assertEquals(7, new IntegerPolynomial(c1, c1.length - 1).meaning(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void powerLessZero() {
        int[] a = {};
        new IntegerPolynomial(a, a.length - 1);
    }

    @Test
    public void plus() {
        int[] d = {1, 2, 0, 3};
        int[] e = {25, 65};
        int[] f = {26, 67, 0, 3};
        assertEquals(new IntegerPolynomial(f, f.length-1),
                new IntegerPolynomial(d, d.length-1).plus(new IntegerPolynomial(e, e.length-1)));
        int[] d1 = {5, -4, 5, 1};
        int[] e1 = {2, -1, 3, -1};
        int[] f1 = {7, -5, 8};
        assertEquals(new IntegerPolynomial(f1, f1.length-1),
                new IntegerPolynomial(d1, d1.length-1).plus(new IntegerPolynomial(e1, e1.length-1)));
    }

    @Test
    public void minus() {
        int[] g = {2, 0, 6};
        int[] h = {3, -2, 4, 12};
        int[] k = {-1, 2, 2, -12};
        assertEquals(new IntegerPolynomial(k, k.length-1),
                new IntegerPolynomial(g, g.length-1).minus(new IntegerPolynomial(h, h.length-1)));
        int[] g1 = {-4, 8, 3, 6};
        int[] h1 = {3, 8, -5};
        int[] k1 = {-7, 0, 8, 6};
        assertEquals(new IntegerPolynomial(k1, k1.length-1),
                new IntegerPolynomial(g1, g1.length-1).minus(new IntegerPolynomial(h1, h1.length-1)));
    }

    @Test
    public void multiply() {
        int[] l = {-3, 5};
        int[] m = {4, -3};
        int[] n = {-12, 29, -15};
        assertEquals(new IntegerPolynomial(n, n.length-1),
                new IntegerPolynomial(l, l.length-1).multiply(new IntegerPolynomial(m, m.length-1)));
        int[] l1 = {5, 6, 1};
        int[] m1 = {2, 0, 0, -3, 2};
        int[] n1 = {10, 12, 2, -15, -8, 9, 2};
        assertEquals(new IntegerPolynomial(n1, n1.length-1),
                new IntegerPolynomial(l1, l1.length-1).multiply(new IntegerPolynomial(m1, m1.length-1)));
        int[] l2 = {0, 0, 5};
        int[] m2 = {2, 0, 0, -3, 2};
        int[] n2 = {0, 0, 10, 0, 0, -15, 10};
        assertEquals(new IntegerPolynomial(n2, n2.length-1),
                new IntegerPolynomial(l2, l2.length-1).multiply(new IntegerPolynomial(m2, m2.length-1)));
    }

    @Test
    public void divide() {
        int[] p = {5, -3, 0, 2};
        int[] q = {-4, 1};
        int[] r = {29, 8, 2};
        assertEquals(new IntegerPolynomial(r, r.length-1),
                new IntegerPolynomial(p, p.length-1).divide(new IntegerPolynomial(q, q.length-1)));
       int[] p1 = {-2};
        int[] q1 = {7, -2, 0, 1};
        int[] r1 = {0};
        assertEquals(new IntegerPolynomial(r1, r1.length-1),
                new IntegerPolynomial(p1, p1.length-1).divide(new IntegerPolynomial(q1, q1.length-1)));
        int[] p2 = {0};
        int[] q2 = {7, -2, 0, 1};
        int[] r2 = {0};
        assertEquals(new IntegerPolynomial(r2, r2.length-1),
                new IntegerPolynomial(p2, p2.length-1).divide(new IntegerPolynomial(q2, q2.length-1)));
        int[] p3 = {5, -2};
        int[] q3 = {7, 1};
        int[] r3 = {-2};
        assertEquals(new IntegerPolynomial(r3, r3.length-1),
                new IntegerPolynomial(p3, p3.length-1).divide(new IntegerPolynomial(q3, q3.length-1)));
        int[] p4 = {5, 1};
        int[] q4 = {7, 1};
        int[] r4 = {1};
        assertEquals(new IntegerPolynomial(r4, r4.length-1),
                new IntegerPolynomial(p4, p4.length-1).divide(new IntegerPolynomial(q4, q4.length-1)));
    }

    @Test(expected = ArithmeticException.class)
    public void divideZero() {
        int[] p2 = {5, -9, 1, 4, 2, 3};
        int[] q2 = {0};
        new IntegerPolynomial(p2, p2.length-1).divide(new IntegerPolynomial(q2, q2.length-1));
    }

    @Test
    public void remainder() {
        int[] s = {-2, 10, 12, -5, 1, 2};
        int[] t = {7, -2, 0, 1};
        int[] v = {5, 1};
        assertEquals(new IntegerPolynomial(v, v.length-1),
                new IntegerPolynomial(s, s.length-1).remainder(new IntegerPolynomial(t, t.length-1)));
        int[] s1 = {5, -3, 0, 2};
        int[] t1 = {-4, 1};
        int[] v1 = {121};
        assertEquals(new IntegerPolynomial(v1, v1.length-1),
                new IntegerPolynomial(s1, s1.length-1).remainder(new IntegerPolynomial(t1, t1.length-1)));
    }
}