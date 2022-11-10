package smoke_test;

import common.TestBaseClass;
import org.testng.annotations.Test;

public class login_test extends TestBaseClass {

    @Test
    public void app_launch() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }


}
