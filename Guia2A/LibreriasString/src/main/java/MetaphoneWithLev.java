import info.debatty.java.stringsimilarity.Levenshtein;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.text.similarity.LevenshteinDistance;

public class MetaphoneWithLev {
        Metaphone metaphone = new Metaphone();
        Levenshtein levenshtein = new Levenshtein();
    public static void main(String[] args) {
        MetaphoneWithLev met = new MetaphoneWithLev();
        System.out.println(met.distance("BROOKLYN", "CLEAN"));


    }
    public double distance(String s1, String s2){
        return levenshtein.distance(metaphone.encode(s1), metaphone.encode(s2));
    }
}
