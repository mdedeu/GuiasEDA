public class Bag<T>{
    private OpenHash<T,Integer> map;
    public int getCount(T o){
        Integer value= map.getValue(o);
        if(value==null)
            return 0;
        return value;
    }
    public void add(T element){
         Integer apariciones= map.getValue(element);
         if(apariciones==null){
             map.insert(element,1);
         }else{
             map.insert(element,apariciones+1);
         }
    }
    public void remove(T element){
        Integer apariciones = map.getValue(element);
        if(apariciones==null)
            return;
        if(apariciones==1){
            map.delete(element);
            return;
        }
        map.insert(element,apariciones-1);




    }
}
