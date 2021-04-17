public class Levenshtein {
    private String string1;
    private String string2;
    Levenshtein(String s1, String s2){
                this.string1= s1;
                this.string2=s2;
    }
    public static void main(String[] args) {
        Levenshtein levenshtein = new Levenshtein("hola", "hola");
    }
    public int distance(){
        char[] arrayString1= string1.toCharArray();
        char[] arrayString2= string2.toCharArray();

        int i, j;
        int m = string1.length();
        int n = string2.length();

        // for all i and j, d[i,j] will hold the Levenshtein distance between
        // the first i characters of string1 and the first j characters of string2;
        // note that d has (m+1)*(n+1) values
        int[][] d = new int[m + 1][n+ 1];

        // set each element to zero
        // c# creates array already initialized to zero

        // source prefixes can be transformed into empty string by
        // dropping all characters
        for (i = 0; i <= m; i++) d[i][0] = i;

        // target prefixes can be reached from empty source prefix
        // by inserting every character
        for (j = 0; j <= n; j++) d[0][j] = j;

        for (j = 1; j <= n; j++) {
            for (i = 1; i <= m; i++) {
                if (arrayString1[i - 1] == arrayString2[j - 1])
                    d[i][j] = d[i - 1][j - 1];       // no operation required
                else {
                    int del = d[i - 1][j] + 1;   // a deletion
                    int ins = d[i][j - 1] + 1;   // an insertion
                    int sub = d[i - 1][j - 1]+ 1; // a substitution
                    if (i == j) {
                        d[i][j] = sub;
                    } else {
                        int insDel;
                        if (i < j) insDel = ins; else insDel = del;
                        // assign the smaller of insDel or sub
                        d[i][j] = Math.min(insDel, sub);
                    }
                }
            }
        }
        return d[m][n];

    }
    public double normalizedSimilarity(){
        return 1-((double) distance())/(string1.length());
    }
}
