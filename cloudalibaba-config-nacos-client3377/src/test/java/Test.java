import lombok.SneakyThrows;
import org.apache.http.client.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author paul
 * @Description
 * @create 2021-11-25 16:49
 */
public class Test {

    @SneakyThrows
    public static void main(String[] args) {

        String lastOpt = "20200408032620";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date parse = format.parse(lastOpt);

        System.out.println(parse);

    }

}
