import org.junit.jupiter.api.*;


public class Tests {
    @BeforeEach
    void before() {
        System.out.println("Iniciando test");
    }

    @AfterEach
    void after() {
        System.out.println("Test finalizado");
    }

    @Test
    void OneDayTest() {
        Timer t = new Timer(0);
        t.stop(24*3600*1000);
        Assertions.assertArrayEquals(new long[]{1, 0, 0, 0, 0}, getValues(t));
    }

    @Test
    void OneHourTest() {
        Timer t = new Timer(0);
        t.stop(3600*1000);
        Assertions.assertArrayEquals(new long[]{0, 1, 0, 0, 0}, getValues(t));
    }

    @Test
    void OneMinuteTest() {
        Timer t = new Timer(0);
        t.stop(60*1000);
        Assertions.assertArrayEquals(new long[]{0, 0, 1, 0, 0}, getValues(t));
    }

    @Test
    void OneSecondTest() {
        Timer t = new Timer(0);
        t.stop(1000);
        Assertions.assertArrayEquals(new long[]{0, 0, 0, 1, 0}, getValues(t));
    }

    @Test
    void _61minutesTest() {
        Timer t = new Timer(0);
        t.stop(3600*1000 + 60*1000);
        Assertions.assertArrayEquals(new long[]{0, 1, 1, 0, 0}, getValues(t));
    }

    @Test
    void OneMiliSecondsTest() {
        Timer t = new Timer(0);
        t.stop(1);
        Assertions.assertArrayEquals(new long[]{0, 0, 0, 0, 1}, getValues(t));
    }

    @Test
    void customTest() {
        Timer t = new Timer(0);
        t.stop(93623040);
        Assertions.assertArrayEquals(new long[]{1, 2, 0, 23, 40}, getValues(t));
    }

    @Test
    void BadOriginException() {
        Timer t = new Timer(20);
        Assertions.assertThrows(RuntimeException.class, () -> t.stop(0));
        Assertions.assertThrows(RuntimeException.class, t::toString);
    }

    long[] getValues(Timer t) {
        return new long[]{t.getDays(),t.getHours(), t.getMinutes(),
                (long)t.getSeconds(), (long)(t.getSeconds()*1000) - ((long)t.getSeconds())*1000};
    }
}
