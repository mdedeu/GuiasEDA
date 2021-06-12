import java.util.HashMap;
import java.util.Map;

public class Qgram {
    private int length;
    private HashMap<String,Integer> map= new HashMap<>();

    public static void main(String[] args) {
        Qgram qgram = new Qgram(2);
        qgram.printTokens("alal");
    }

    Qgram(int n){
        length=n;
    }
    public void getGrams(String s1){
        int total=0;
        String start = "#";
        start = start.concat(s1);
        start = start.concat("#");
        while(total<start.length()-1){
            String substring = start.substring(total,total+length);
            if(map.getOrDefault(substring,0)==0){
                map.put(substring,1);
            }
            else{
                map.put(substring,map.get(substring)+1);
            }
            total++;
        }
    }
    public HashMap<String,Integer> getMap(){
        return map;
    }
    public void printTokens(String s1){
        getGrams(s1);
        System.out.println(map.toString());
    }
    public int similarity(Qgram other){
        HashMap<String,Integer> otherMap= other.getMap();
        HashMap<String,Integer> myMapCopy = map;
        int similarGrams=0;
        for (Map.Entry<String,Integer> entry:otherMap.entrySet()) {
            if(myMapCopy.containsKey(entry.getKey()) && myMapCopy.get(entry.getKey())>0){
                similarGrams++;
                myMapCopy.replace(entry.getKey(), myMapCopy.get(entry.getKey())-1);
                if(map.get(entry.getKey())==0){
                    map.remove(entry.getKey());
                }
            }
        }
        return similarGrams;
    }





}
