import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {

    @Test
    public void distanceTest(){
        Levenshtein levenshteinDistance1= new Levenshtein("big data","bigdaa");
        assertEquals(2,levenshteinDistance1.distance());
        assertEquals(0.75,levenshteinDistance1.normalizedSimilarity());
    }
}
