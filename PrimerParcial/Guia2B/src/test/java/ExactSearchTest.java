import org.junit.Assert;
import org.junit.Test;

public class ExactSearchTest {
    @Test
    public void test1() {
        char[] target = "abracadabra".toCharArray();
        char[] query = "ra".toCharArray();
        Assert.assertEquals(2,ExactSearch.indexOf(query, target));
    }
    @Test
    public void test2() {
        char[] target= "abracadabra".toCharArray();
        char[] query= "abra".toCharArray();
        Assert.assertEquals(0,ExactSearch.indexOf(query, target));
    }
    @Test
    public void test3() {
        char[] target= "abracadabra".toCharArray();
        char[] query= "aba".toCharArray();
        Assert.assertEquals(-1,ExactSearch.indexOf(query, target));
    }
    @Test
    public void test4() {
        char[] target= "ab".toCharArray();
        char[] query= "aba".toCharArray();
        Assert.assertEquals(-1,ExactSearch.indexOf(query, target));
    }

    @Test
    public void test5() {
        char[] target= "xa".toCharArray();
        char[] query= "aba".toCharArray();
        Assert.assertEquals(-1,ExactSearch.indexOf(query, target));

    }
}
