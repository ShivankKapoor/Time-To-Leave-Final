import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class routeTest {
    @Test
    void routeTest() throws Exception {
        assertEquals(411, Route.getTime("Green+Hall+UTD", "ECSS+UTD"));
        assertEquals(482, Route.getTime("Sciences+Building+UTD", "JSOM+UTD"));
    }
}
