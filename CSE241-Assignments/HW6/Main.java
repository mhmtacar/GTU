import com.homework.*;

public class Main {

  public static void main(String[] args) {
    
    System.out.println();
    System.out.println("ARRAYLIST");
    System.out.println();
    ArrayList<String> stringArrayList = new ArrayList<>();
    stringArrayList.add("Mehmet");
    stringArrayList.add("Mustafa");
    stringArrayList.add("Ali");

    stringArrayList.remove("Mustafa");

    System.out.println("Size of stringArrayList: " + stringArrayList.size());
    System.out.println("'Mehmet' is in stringArrayList: " + stringArrayList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringArrayList: " + stringArrayList.contains("Mustafa"));
    System.out.println("'Ali' is in stringArrayList: " + stringArrayList.contains("Ali"));
    System.out.println();

    ArrayList<String> stringArrayList2 = new ArrayList<>();
    stringArrayList2.add("Mehmet");
    stringArrayList2.add("Ali");

    stringArrayList.removeAll(stringArrayList2);

    System.out.println("Size of stringArrayList: " + stringArrayList.size());
    System.out.println("'Mehmet' is in stringArrayList: " + stringArrayList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringArrayList: " + stringArrayList.contains("Mustafa"));
    System.out.println("'Ali' is in stringArrayList: " + stringArrayList.contains("Ali"));
    System.out.println();

    stringArrayList.addAll(stringArrayList2);
    System.out.println("Size of stringArrayList: " + stringArrayList.size());
    System.out.println("'Mehmet' is in stringArrayList: " + stringArrayList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringArrayList: " + stringArrayList.contains("Mustafa"));
    System.out.println("'Ali' is in stringArrayList: " + stringArrayList.contains("Ali"));
    System.out.println();

    stringArrayList.retainAll(stringArrayList2);
    System.out.println("Size of stringArrayList: " + stringArrayList.size());
    System.out.println("stringArrayList contains all stringArrayList2 elements: " + stringArrayList.containsAll(stringArrayList2));
    System.out.println("'Mehmet' is in stringArrayList: " + stringArrayList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringArrayList: " + stringArrayList.contains("Mustafa"));
    System.out.println("'Ali' is in stringArrayList: " + stringArrayList.contains("Ali"));
    System.out.println();

    stringArrayList.clear();
    System.out.println("stringArrayList is empty: " + stringArrayList.isEmpty());
    System.out.println();
    System.out.println();


    ArrayList<Integer> intArrayList = new ArrayList<>();
    intArrayList.add(1);
    intArrayList.add(2);
    intArrayList.add(3);

    intArrayList.remove(1);

    System.out.println("Size of intArrayList: " + intArrayList.size());
    System.out.println("'1' is in intArrayList: " + intArrayList.contains(1));
    System.out.println("'2' is in intArrayList: " + intArrayList.contains(2));
    System.out.println("'3' is in intArrayList: " + intArrayList.contains(3));
    System.out.println();

    ArrayList<Integer> intArrayList2 = new ArrayList<>();
    intArrayList2.add(2);
    intArrayList2.add(3);

    intArrayList.removeAll(intArrayList2);

    System.out.println("Size of intArrayList: " + intArrayList.size());
    System.out.println("'1' is in intArrayList: " + intArrayList.contains(1));
    System.out.println("'2' is in intArrayList: " + intArrayList.contains(2));
    System.out.println("'3' is in intArrayList: " + intArrayList.contains(3));
    System.out.println();

    intArrayList.addAll(intArrayList2);
    System.out.println("Size of intArrayList: " + intArrayList.size());
    System.out.println("'1' is in intArrayList: " + intArrayList.contains(1));
    System.out.println("'2' is in intArrayList: " + intArrayList.contains(2));
    System.out.println("'3' is in intArrayList: " + intArrayList.contains(3));
    System.out.println();

    intArrayList.retainAll(intArrayList2);
    System.out.println("Size of intArrayList: " + intArrayList.size());
    System.out.println("intArrayList contains all intArrayList2 elements: " + intArrayList.containsAll(intArrayList2));
    System.out.println("'1' is in intArrayList: " + intArrayList.contains(1));
    System.out.println("'2' is in intArrayList: " + intArrayList.contains(2));
    System.out.println("'3' is in intArrayList: " + intArrayList.contains(3));
    System.out.println();

    intArrayList.clear();
    System.out.println("intArrayList is empty: " + intArrayList.isEmpty());
    System.out.println();
    System.out.println();

    
    System.out.println("HASHSET");
    System.out.println();

    HashSet<String> stringHashSet = new HashSet<>();
    stringHashSet.add("Mehmet");
    stringHashSet.add("Mustafa");
    stringHashSet.add("Mustafa");
    stringHashSet.add("Ali");
    stringHashSet.add("Mehmet");
    System.out.println("Size of stringHashSet: " + stringHashSet.size());
    System.out.println("'Mehmet' is in stringHashSet: " + stringHashSet.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringHashSet: " + stringHashSet.contains("Mustafa"));
    System.out.println("'Ali' is in stringHashSet: " + stringHashSet.contains("Ali"));
    System.out.println();

    stringHashSet.remove("Mustafa");

    System.out.println("Size of stringHashSet: " + stringHashSet.size());
    System.out.println("'Mehmet' is in stringHashSet: " + stringHashSet.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringHashSet: " + stringHashSet.contains("Mustafa"));
    System.out.println("'Ali' is in stringHashSet: " + stringHashSet.contains("Ali"));
    System.out.println();

    HashSet<String> stringHashSet2 = new HashSet<>();
    stringHashSet2.add("Mustafa");
    stringHashSet2.add("Ali");

    stringHashSet.removeAll(stringHashSet2);

    System.out.println("Size of stringHashSet: " + stringHashSet.size());
    System.out.println("'Mehmet' is in stringHashSet: " + stringHashSet.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringHashSet: " + stringHashSet.contains("Mustafa"));
    System.out.println("'Ali' is in stringHashSet: " + stringHashSet.contains("Ali"));
    System.out.println();

    stringHashSet.addAll(stringHashSet2);
    System.out.println("Size of stringHashSet: " + stringHashSet.size());
    System.out.println("'Mehmet' is in stringHashSet: " + stringHashSet.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringHashSet: " + stringHashSet.contains("Mustafa"));
    System.out.println("'Ali' is in stringHashSet: " + stringHashSet.contains("Ali"));
    System.out.println();

    stringHashSet.retainAll(stringHashSet2);
    System.out.println("Size of stringHashSet: " + stringHashSet.size());
    System.out.println("stringHashSet contains all stringHashSet2 elements: " + stringHashSet.containsAll(stringHashSet2));
    System.out.println("'Mehmet' is in stringHashSet: " + stringHashSet.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringHashSet: " + stringHashSet.contains("Mustafa"));
    System.out.println("'Ali' is in stringHashSet: " + stringHashSet.contains("Ali"));
    System.out.println();

    stringHashSet.clear();
    System.out.println("stringHashSet is empty: " + stringHashSet.isEmpty());
    System.out.println();
    System.out.println();

    HashSet<Integer> intHashSet = new HashSet<>();
    intHashSet.add(1);
    intHashSet.add(2);
    intHashSet.add(2);
    intHashSet.add(3);
    intHashSet.add(1);
    System.out.println("Size of intHashSet: " + intHashSet.size());
    System.out.println("'1' is in intHashSet: " + intHashSet.contains(1));
    System.out.println("'2' is in intHashSet: " + intHashSet.contains(2));
    System.out.println("'3' is in intHashSet: " + intHashSet.contains(3));
    System.out.println();

    intHashSet.remove(1);

    System.out.println("Size of intHashSet: " + intHashSet.size());
    System.out.println("'1' is in intHashSet: " + intHashSet.contains(1));
    System.out.println("'2' is in intHashSet: " + intHashSet.contains(2));
    System.out.println("'3' is in intHashSet: " + intHashSet.contains(3));
    System.out.println();

    HashSet<Integer> intHashSet2 = new HashSet<>();
    intHashSet2.add(2);
    intHashSet2.add(3);

    intHashSet.removeAll(intHashSet2);

    System.out.println("Size of intHashSet: " + intHashSet.size());
    System.out.println("'1' is in intHashSet: " + intHashSet.contains(1));
    System.out.println("'2' is in intHashSet: " + intHashSet.contains(2));
    System.out.println("'3' is in intHashSet: " + intHashSet.contains(3));
    System.out.println();

    intHashSet.addAll(intHashSet2);
    System.out.println("Size of intHashSet: " + intHashSet.size());
    System.out.println("'1' is in intHashSet: " + intHashSet.contains(1));
    System.out.println("'2' is in intHashSet: " + intHashSet.contains(2));
    System.out.println("'3' is in intHashSet: " + intHashSet.contains(3));
    System.out.println();

    intHashSet.retainAll(intHashSet2);
    System.out.println("Size of intHashSet: " + intHashSet.size());
    System.out.println("intHashSet contains all intHashSet2 elements: " + intHashSet.containsAll(intHashSet2));
    System.out.println("'1' is in intHashSet: " + intHashSet.contains(1));
    System.out.println("'2' is in intHashSet: " + intHashSet.contains(2));
    System.out.println("'3' is in intHashSet: " + intHashSet.contains(3));
    System.out.println();

    intHashSet.clear();
    System.out.println("intHashSet is empty: " + intHashSet.isEmpty());

    System.out.println();
    System.out.println();
    System.out.println("LINKEDLIST");
    System.out.println();

    LinkedList<String> stringLinkedList = new LinkedList<>();
    stringLinkedList.add("Mehmet");
    stringLinkedList.add("Mustafa");
    stringLinkedList.add("Ali");

    stringLinkedList.remove("Mustafa");

    System.out.println("Size of stringLinkedList: " + stringLinkedList.size());
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    LinkedList<String> stringLinkedList2 = new LinkedList<>();
    stringLinkedList2.offer("Mustafa");
    stringLinkedList2.offer("Ali");

    stringLinkedList.removeAll(stringLinkedList2);

    System.out.println("Size of stringLinkedList: " + stringLinkedList.size());
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    stringLinkedList.addAll(stringLinkedList2);
    System.out.println("Size of stringLinkedList: " + stringLinkedList.size());
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    stringLinkedList.retainAll(stringLinkedList2);
    System.out.println("Size of stringLinkedList: " + stringLinkedList.size());
    System.out.println("stringLinkedList contains all stringLinkedList2 elements: " + stringLinkedList.containsAll(stringLinkedList2));
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    System.out.println("Element: " + stringLinkedList.element());
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    System.out.println("Poll: " + stringLinkedList.poll());
    System.out.println("'Mehmet' is in stringLinkedList: " + stringLinkedList.contains("Mehmet"));
    System.out.println("'Mustafa' is in stringLinkedList: " + stringLinkedList.contains("Mustafa"));
    System.out.println("'Ali' is in stringLinkedList: " + stringLinkedList.contains("Ali"));
    System.out.println();

    stringLinkedList.clear();
    System.out.println("stringLinkedList is empty: " + stringLinkedList.isEmpty());
    System.out.println();
    System.out.println();


    LinkedList<Integer> intLinkedList = new LinkedList<>();
    intLinkedList.add(1);
    intLinkedList.add(2);
    intLinkedList.add(3);

    intLinkedList.remove(1);

    System.out.println("Size of intLinkedList: " + intLinkedList.size());
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    LinkedList<Integer> intLinkedList2 = new LinkedList<>();
    intLinkedList2.offer(2);
    intLinkedList2.offer(3);

    intLinkedList.removeAll(intLinkedList2);

    System.out.println("Size of intLinkedList: " + intLinkedList.size());
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    intLinkedList.addAll(intLinkedList2);
    System.out.println("Size of intLinkedList: " + intLinkedList.size());
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    intLinkedList.retainAll(intLinkedList2);
    System.out.println("Size of intLinkedList: " + intLinkedList.size());
    System.out.println("intLinkedList contains all intLinkedList2 elements: " + intLinkedList.containsAll(intLinkedList2));
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    System.out.println("Element: " + intLinkedList.element());
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    System.out.println("Poll: " + intLinkedList.poll());
    System.out.println("'1' is in intLinkedList: " + intLinkedList.contains(1));
    System.out.println("'2' is in intLinkedList: " + intLinkedList.contains(2));
    System.out.println("'3' is in intLinkedList: " + intLinkedList.contains(3));
    System.out.println();

    intLinkedList.clear();
    System.out.println("intLinkedList is empty: " + intLinkedList.isEmpty());
    System.out.println();

    
    try{
        stringLinkedList.poll();
    }catch (Exception e){
        System.out.println("ERROR: " + e.getMessage());
    }

     try{
        intLinkedList.poll();
    }catch (Exception e){
        System.out.println("ERROR: " + e.getMessage());
    }


  }
}
