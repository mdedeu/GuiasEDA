public class ExactSearch {
    public static int indexOf(char[] query, char[] target){
        // Complejidad espacial
       int index=-1;
       int i=0;
       int anterior =1;
       if(target.length < query.length)
           return index;
            for(int j=0; j< target.length && i< query.length;){
                if (query[i] == target[j]) {
                    if (index == -1)
                        index = j;
                    i++;

                    if(anterior!=1)
                        return -1;
                    anterior=1;
                }else{
                    if(index!=-1)
                        anterior=0;
                }
                j++;
            }
        return index;
    }
}
