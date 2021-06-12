public class Main {
    public static void main(String[] args) {
        int[] array = new int[] {10, 30, 50, 80, 100, 130};
        System.out.println("The index of 30 in {10, 30, 50, 80, 100, 130} is: "+BinarySearch.iterative(array,30));
        System.out.println("The index of 100 in {10, 30, 50, 80, 100, 130} is: "+BinarySearch.recursive(array,100));
        System.out.println("The index of 30 in {10, 30, 50, 80, 100, 130} is: "+BinarySearch.iterative(array,329));
        System.out.println("The index of 100 in {10, 30, 50, 80, 100, 130} is: "+BinarySearch.recursive(array,15));

        IndexService myIndex = new IndexWithDuplicates();
        myIndex.insert(74);
        System.out.println("Insert 74: " + myIndex);
        myIndex.initialize(new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100});
        System.out.println(myIndex);
//        try{
//            indexService.delete(20);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println(indexService);
        myIndex.insert(11);
        System.out.println(myIndex);


        System.out.println (myIndex.occurrences( 10 ) );  // se obtiene 0
        //myIndex.delete( 10 );  // ignora
        System.out.println (myIndex.search( 10 ) );  // se obtiene false
        System.out.println(myIndex);
        myIndex.insert(80);
        System.out.println("Insert 80: " + myIndex);
        myIndex.insert( 20 );  // almacena [20, 80]
        System.out.println("Insert 20: " + myIndex);
        myIndex.insert( 80 );  // almacena [20, 80, 80]
        System.out.println("Insert 80: " + myIndex);
        myIndex.insert(8);
        System.out.println("Insert 8: " + myIndex);
        myIndex.insert(101);
        System.out.println("Insert 101: " + myIndex);
        myIndex.insert(31);
        System.out.println("Insert 31: " + myIndex);
        try
        {
            myIndex.initialize( null );
        }
        catch(Exception e)
        {
        }
        // sigue con lo anterior
        System.out.println (myIndex.occurrences( 80 ) );  // se obtiene 2
        try
        {
            myIndex.initialize( new int[] {100, 50, 30, 50, 80, 100, 100, 30} );
        }
        catch(Exception e)
        {
        }
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println( myIndex.search( 20 ));   // se obtiene false
        System.out.println( myIndex.search( 80 ));   // se obtiene true
        System.out.println (myIndex.occurrences( 50 ) );  // se obtiene 2
        myIndex.delete( 50 );
        System.out.println (myIndex.occurrences( 50 ) );  // se obtiene 1
    }


}
