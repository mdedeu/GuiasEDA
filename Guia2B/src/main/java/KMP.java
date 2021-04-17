import java.util.ArrayList;
import java.util.Arrays;

public class KMP {
    public static void main(String[] args) {
        char[] query=new char[]{'n','i'};
        char[] target = new String("sino se los digo no se si es nocivo").toCharArray();
        System.out.println(findAll(query,target));

    }
    public static ArrayList<Integer> findAll(char[] query, char[] target){
        ArrayList<Integer> list = new ArrayList<>();
        int i=0;
        int initialLength = target.length;
       while(i<initialLength){
           int indexOf = indexOf(query,target);
           if(indexOf == -1)
               break;
           if(indexOf+ query.length <= target.length){
               target = Arrays.copyOfRange(target,indexOf+query.length,target.length);
               list.add(indexOf+i);
               i+=indexOf+ query.length;
           }
       }
       return list;
    }

    private static int indexOf(char[]query, char[]target) {
        if (query == null || query.length == 0)
            throw new RuntimeException("Bad query String");
        if (target == null || target.length == 0)
            throw new RuntimeException("Bad target String");

        int[] next = nextComputation(query);
        int rec = 0;
        int pquery = 0;
        while (rec < target.length) {
            if (target[rec] == query[pquery]) {
                rec++;
                pquery++;
            }
            if (pquery == query.length) {
                break;
            } else if (rec < target.length && target[rec] != query[pquery]) {
                if (pquery != 0) {
                    pquery = next[pquery - 1];
                } else {
                    rec++;
                }
            }
        }
        if (pquery == query.length)
            return rec - pquery;
        else
            return -1;
    }

    private static int[] nextComputation(char[] query){
        int[] next = new int[query.length];
        int border=0;
        int rec=1;
        while(rec < query.length){
            if(query[rec]!=query[border]){
                if(border!=0) {
                    border = next[border - 1];
                }else
                    next[rec++]=0;
            }
            else{
                border++;
                next[rec]=border;
                rec++;
            }
        }
        return next;
    }

}
