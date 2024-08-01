import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Contains mergeSort using arrayList taken from textbook.
 */
public class ALSort {

    /**
     * Helper method for mergeSort
     */
    private static<T extends Comparable<T>> void merge(List<T> outputSequence, List<T> leftSequence,
     List<T> rightSequence, Comparator<? super T> c){
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSequence.size() && j < rightSequence.size()) {

            if (c.compare(leftSequence.get(i), rightSequence.get(j)) < 0) {
                outputSequence.set(k, leftSequence.get(i));
                k++;
                i++;
            } else {
                outputSequence.set(k, rightSequence.get(j));
                k++;
                j++;
            }
        }

        while (i < leftSequence.size()) {
            outputSequence.set(k, leftSequence.get(i));
            k++;
            i++;
        }

        while (j < rightSequence.size()) {
            outputSequence.set(k, rightSequence.get(j));
            k++;
            j++;
        }
    }

    /**
     * mergeSort method for given list and takes comparator.
     * @param table list of T type
     * @param c comparator of T type
     */
    public static <T extends Comparable<T>> void mergeSort(List<T> table, Comparator<? super T> c) {

        if (table.size() > 1) {

            int halfSize = table.size() / 2;
            List<T> leftTable = new ArrayList<>();
            List<T> rightTable = new ArrayList<>();
            leftTable.addAll(table.subList(0, halfSize));
            rightTable.addAll(table.subList(halfSize, table.size()));

            mergeSort(leftTable, c);
            mergeSort(rightTable, c);

            merge(table, leftTable, rightTable, c);
        }
    }
}
