import org.bouncycastle.math.Primes;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author paul
 * @Description
 * @create 2021-11-09 21:33
 */
public class T {

    @Test
    public void testMaxInteger() {
        System.out.println(Integer.MAX_VALUE);
        Long aLong1 = new Long(2344);
        Long aLong2 = new Long(2344);

        Long aLong3 = new Long(126);
        Long aLong4 = new Long(126);

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        Calendar instance = Calendar.getInstance();

        Calendar instance2 = Calendar.getInstance();

        instance.add(Calendar.DATE, 0);
        instance2.add(Calendar.DATE, 0);

        instance.set(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DATE), 00,00,00);
        instance2.set(instance2.get(Calendar.YEAR), instance2.get(Calendar.MONTH), instance2.get(Calendar.DATE), 23,59,59);


        System.out.println("昨天：" + format.format(instance.getTime()));

        System.out.println("今天：" + format.format(instance2.getTime()));

        System.out.println("20190128060720");

        System.out.println("===============================");

        System.out.println(aLong1.equals(aLong2));
        System.out.println(aLong1 == aLong2);
        System.out.println("----------------------------");
        System.out.println(aLong3.equals(aLong4));
        System.out.println(aLong3 == aLong4);

    }

    @Test
    public void testUUID() {
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }

}
