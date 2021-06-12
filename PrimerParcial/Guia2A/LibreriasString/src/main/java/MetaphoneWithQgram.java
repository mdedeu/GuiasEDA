import info.debatty.java.stringsimilarity.QGram;
import org.apache.commons.codec.language.Metaphone;

public class MetaphoneWithQgram {
    Metaphone metaphone = new Metaphone();
    QGram qgram = new QGram(1);
    public static void main(String[] args) {
        MetaphoneWithQgram myClass = new MetaphoneWithQgram();
        double a = myClass.distance(myClass.metaphone.encode("BROOKLIN"),myClass.metaphone.encode("CLEAN"));
        System.out.println(a);
    }
    public double distance(String s1, String s2){
        String aux= "#";
        s1= aux.concat(s1).concat(aux);
        s2= aux.concat(s2).concat(aux);
        return qgram.distance(s1,s2);

    }
}
