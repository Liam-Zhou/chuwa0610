package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 4:43 PM
 */
public class ArrayListExerciseTest {
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list.get(0));
        System.out.println(list.size());
        List<Integer> tmp = Arrays.asList(4,5,6);
        list.addAll(tmp);
        list.stream().forEach(System.out::println);
    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex)
     * removeAll(Collection<?> c)
     * clear()
     *
     * Update:
     * set(int index, E e)
     * replaceAll(UnaryOperator<E> operator)
     *
     * check:
     * contains(Object o)
     * indexOf(Object o)
     * lastIndexOf(Object o)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        List<String> tmp = Arrays.asList("D","E","F", "A", "B","c","d");
        list.addAll(tmp);
        list.stream().forEach(e -> System.out.print(e + " "));
        System.out.println();
        list.remove(2);// remove idx
        list.stream().forEach(e -> System.out.print(e + " ")); // A B D E F A ...
        System.out.println();

        list.remove("A"); // remove first occurrence
        list.stream().forEach(e -> System.out.print(e + " ")); // B D E F A ...
        System.out.println();

        // protected removeRange, need some class extends ArrayList
        // list.removeRange(2, 5);

        List<String> tmp2 = Arrays.asList("D","E","F", "z");
        list.removeAll(tmp2);
        list.stream().forEach(e -> System.out.print(e + " ")); // B A B c d
        System.out.println();

        list.clear();
        list.stream().forEach(e -> System.out.print(e + " ")); //empty
        System.out.println();

        list= Arrays.asList("D","E","F", "A", "b","a");
        list.set(0, "A");
        list.stream().forEach(e -> System.out.print(e + " ")); //empty
        System.out.println();

        UnaryOperator<String> toLowerCase = new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                return s.toLowerCase();
            }
        };
        list.replaceAll(toLowerCase);
        list.stream().forEach(e -> System.out.print(e + " ")); //empty
        System.out.println();
        // a e f a b a
        System.out.println("contains a : " +list.contains("a") );
        System.out.println("contains z : " +list.contains("z") );
        System.out.println("first index of a : " +list.indexOf("a") );
        System.out.println("last index of a : " +list.lastIndexOf("a") );
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     * forEachRemaining(Consumer<? super E> action) -- from Java8
     */

    @Test
    public void learn_Iterator() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.remove("Z");
        Iterator<String> iter = list.iterator();
        while(iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();

        Consumer<String> printConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        iter = list.iterator();
        iter.forEachRemaining(printConsumer);
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<String> list = new ArrayList<>();
        list.add("Z");
        list.add("B");
        list.add("C");


        Collections.sort(list); // = list.sort(null);
        Collections.sort(list, Collections.reverseOrder()); // = list.sort(Collections.reverseOrder());


        list.stream().forEach(System.out::println);
    }
}
