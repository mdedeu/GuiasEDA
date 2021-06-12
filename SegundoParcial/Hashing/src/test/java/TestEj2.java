import org.junit.jupiter.api.Test;

public class TestEj2 {
    @Test
    public void preHashTest(){
        Hash<Integer, String> map2= new Hash<Integer, String>(p->p);
        map2.insert(10,"Marado");
        map2.insert(1,"Menem");
        map2.insert(7,"The Builder");
        map2.insert(3,"The Racist");
        map2.insert(8,"Steve");
        map2.dump();
    }
}
