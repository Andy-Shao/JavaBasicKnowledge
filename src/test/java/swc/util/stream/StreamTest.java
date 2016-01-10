package swc.util.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Intermediate operations:<br>
 * <ul>
 * <li>filter 1 Exclude all elements that don't match a Predicate.</li>
 * <li>map 1 2 3 4 - Perform a one-to-one transformation of elements using a
 * Function.</li>
 * <li>flatMap 1 2 3 4 - Transform each element into zero or more elements by
 * way of Stream.</li>
 * <li>peek 1 - Perform some action on each element as it is encountered.
 * Primarily useful for debugging.</li>
 * <li>distinct 1 - Exclude all duplicate elements according to their.equals
 * behavior. This is a stateful operation.</li>
 * <li>sorted 1 2 - Ensure that stream elements in subsequent operations are
 * encountered according to the order imposed by a Comparator. This is a
 * stateful operation.</li>
 * <li>limit 1 - Ensure that stream elements in subsequent operations are
 * encountered according to the order imposed by a Comparartor. This is a
 * stateful operation.</li>
 * <li>substream 1 2 - Ensure that subsequent operations only see a rang(by
 * index) of elements. Like String.substring except for streams. There are two
 * forms, one with a begin index and one with an end index as well. Both are
 * stateful operations, and the form with an end index is also a
 * short-circuiting operation.</li>
 * </ul>
 * <br>
 * Terminal operations:<br>
 * <ul>
 * <li>forEach 1 - Perform some action for each element in the stream.</li>
 * <li>toArray 1 2 - Dump the elements in the stream to an array.</li>
 * <li>reduce 1 2 3 - Combine the stream elements into one using a
 * BinaryOperator.</li>
 * <li>collect 1 2 - Dump the elements in the stream into some container, such
 * as a Collection or Map.</li>
 * <li>min 1 - Find the minimum element of the stream according to a Comparator.
 * </li>
 * <li>max 1 - Find the maximum element of the stream according to a Comparator.
 * </li>
 * <li>count 1 - Find the number of elements in the stream.</li>
 * <li>anyMatch 1 - Find out whether at least one of the elements in the stream
 * matches a Predicate. This is a short-circuiting operation.</li>
 * <li>allMatch 1 - Find out whether every in the stream matches a Predicate.
 * This is a short-circuiting operation.</li>
 * <li>noneMatch 1 - Find out whether zero elements in the stream match a
 * Predicate. This is a short-circuiting operation.</li>
 * <li>findFirst 1 - Find the first element in the stream. This is a
 * short-circuiting operation.</li>
 * <li>findAny 1 - Find any element in the stream, which may be cheaper than
 * findFirst for some streams. This is a short-circuiting operation.</li>
 * </ul>
 */
public class StreamTest {

    private volatile Stream<String> stream;

    @Before
    public void before() {
        Collection<String> collection = new ArrayList<>();
        collection.add("Andy.Shao");
        collection.add("swc");

        this.stream = collection.stream();
    }

    @Test
    public void getStream() {
        Collection<String> collection = new ArrayList<>();
        Assert.assertTrue(collection.stream() instanceof Stream);
    }

    @Test
    public void parallel() {
        System.out.println(this.stream.parallel());
    }

    @Test
    public void sequential() {
        System.out.println(this.stream.sequential());
    }

    @Test
    public void sumOfLength() {
        System.out.println(this.stream.filter(b -> b instanceof String).mapToInt(b -> b.length()).sum());
    }
}
