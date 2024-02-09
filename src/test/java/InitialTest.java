import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InitialTest {

    @Test
    public void verificarJUnit() {
        Assertions.assertTrue(true);
    }

    @Test
    public void testarSeIgualADois() {
        int a = 1+1;

        Assertions.assertEquals(2, a);
    }

    @Test
    public void testarSeIgualATres() {
        int x = 2+10-7;

        Assertions.assertEquals(3, x);
    }

}
