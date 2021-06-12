package core;

public class Person implements Comparable<Person> {
    private int legajo;
    private String name;
    public Person(int legajo, String name){
        this.legajo=legajo;
        this.name=name;
    }

    @Override
    public int compareTo(Person o) {
        return legajo-o.getLegajo();
    }
    public int getLegajo(){
        return legajo;
    }

    @Override
    public String toString() {
        return "legajo=" + legajo +
                "\n name=" + name;
    }
}
