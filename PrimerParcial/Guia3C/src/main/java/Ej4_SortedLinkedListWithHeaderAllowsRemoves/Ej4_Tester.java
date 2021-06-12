package Ej4_SortedLinkedListWithHeaderAllowsRemoves;

import Ej1_SortedLinkedList.SortedLinkedList;
import Ej1_SortedLinkedList.SortedListService;

import java.util.Iterator;

public class Ej4_Tester {
    public static void main(String[] args) {
        SortedListService<Integer> a= new SortedLinkedListWithHeaderAllowsRemoves<>();

        System.out.println("tamaño = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax()); a.delete(100);


        a.add(50); a.add(30); a.add(40); a.add(10);
        a.add(20); a.add(60); a.add(70); a.add(80);
        a.dump();

        System.out.println("tamaño = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());

        System.out.println("con iterador ...");

        for (Iterator<Integer> iter = a.iterator(); iter.hasNext();) {
            Integer nro =  iter.next();
            if (nro.equals(80) || nro.equals(10) || nro.equals(40)) {
                System.out.println(String.format("deleting %s", nro));
                iter.remove();
            }
            else
                System.out.println(String.format("intacto %s", nro));
        }

        a.dump();
        System.out.println("tamaño = " + a.size());
        System.out.println("min = " + a.getMin());
        System.out.println("max = " + a.getMax());

        System.out.println(".......................");
        SortedListService<Integer> list = new SortedLinkedListWithHeaderAllowsRemoves<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Min: " + list.getMin());
        System.out.println("Max: " + list.getMax());//40
        list.delete(40);
        System.out.println("Max: " + list.getMax());//30

    }
}
