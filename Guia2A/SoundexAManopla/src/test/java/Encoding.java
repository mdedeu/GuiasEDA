import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Encoding {
    Soundex soundex= new Soundex();
    @Test
    public void assertStrings(){

        assertEquals("T624",soundex.encode("threshold"));
        assertEquals("H430",soundex.encode("hold"));
        assertEquals("P500",soundex.encode("phone"));
        assertEquals("F500",soundex.encode("foun"));
    }
    @Test
    public void assertSimilarity(){
       assertEquals(soundex.similarity( "threshold", "hold"),0.0);
       assertEquals(soundex.similarity( "phone","foun"),0.75);

    }
}
