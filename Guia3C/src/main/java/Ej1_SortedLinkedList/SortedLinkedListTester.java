package Ej1_SortedLinkedList;

public class SortedLinkedListTester {
    public static void main(String[] args) {
        SortedListService<Integer> list = new SortedLinkedList<>();
        System.out.println("isEmpty?: " + list.isEmpty()); // True
        list.add(3);
        list.add(1);
        list.add(5);
        list.add(-5);
        list.add(8);
        list.dump(); // -5 1 3 5 8
        list.delete(-5);
        list.delete(8);
        list.delete(3);
        list.dump(); // 1 5
        list.add(-10);
        list.add(10);
        list.add(7);
        System.out.println("Size: " + list.size()); //5
        System.out.println("isEmpty?: " + list.isEmpty()); //False
        System.out.println("Min: " + list.getMin()); //-10
        System.out.println("Max: " + list.getMax()); //10

        SortedListService<Integer> d = new SortedLinkedList<>();
        d.add(16);
        d.add(14);
        d.add(12);
        d.dump();

        for (Integer elem : d) {
            System.out.println(elem);
        }

        SortedListService<String> s = new SortedLinkedList<>();
        s.add("hola");;
        s.add("tal");
        s.add("que");;
        s.dump();

        for (String elem : s) {
            System.out.println(elem);
        }
    }
}
