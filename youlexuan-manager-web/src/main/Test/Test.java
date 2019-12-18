import java.math.BigDecimal;
import java.util.Calendar;

/**
 * @BelongsProject: youlexuan-parent
 * @BelongsPackage: PACKAGE_NAME
 * @author: Thinkpad
 * @CreateTime: 2019-12-02 15:39
 * @Description: none
 */
public class Test {
    @org.junit.Test
    public void test1() {
        System.out.println(2 << 3);
        int n;
        for (int i = 156; i < 100000; i++) {
            n = i;
            int c = 0;
            while (n % 5 == 1) {
                n = n / 5;
                // System.out.println(n);
                c++;
            }
            if (c == 6) {
                System.out.println(i);
                System.exit(0);
            }
        }

    }

    @org.junit.Test
    public void test2() {
        int a = 3906;
        while (a >= 0) {
            System.out.println(a);
            a = a / 5 - 1;
        }
    }

    @org.junit.Test
    public void test3() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        System.out.println(cal.getTime());
    }
}
