package io.github.jasonkayzk.sort;

import algorithm.util.iostream.StdOut;
import algorithm.util.watch.Stopwatch;
import io.github.jasonkayzk.utils.random.RandomArrayUtil;
import org.junit.Test;

import java.util.Arrays;

import static io.github.jasonkayzk.sort.BaseSort.isSorted;
import static io.github.jasonkayzk.sort.BaseSort.show;
import static io.github.jasonkayzk.sort.QuickSort.*;

public class QuickSortTest {

    @Test
    public void sortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sort(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void sort2Test() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        sort2(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void advancedSortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        advancedSort(a);
        show(a);
        assert isSorted(a);
    }


    @Test
    public void advancedSort2Test() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        advancedSort2(a);
        show(a);
        assert isSorted(a);
    }

    @Test
    public void threeWaySortTest() {
        Integer[] a = RandomArrayUtil.getRandomBoxedIntArray(0, 100, 50);
        threeWaySort(a);
        show(a);
        assert isSorted(a);
    }

    /**
     * 对快排的各种方法进行性能测试
     * <p>
     * 分别对五百万个随机数组和随机重复数组排序, 结果如下:
     * <p>
     * sort [random]: (2.23 seconds)
     * sort [random + duplicate]: (4.22 seconds)
     * <p>
     * sort2 [random]: (2.58 seconds)
     * sort2 [random + duplicate]: (32.91 seconds)
     * <p>
     * advancedSort [random]: (1.16 seconds)
     * advancedSort [random + duplicate]: (2.14 seconds)
     * <p>
     * advancedSort2 [random]: (1.55 seconds)
     * advancedSort2 [random + duplicate]: (26.64 seconds)
     * <p>
     * threeWaySort [random]: (2.38 seconds)
     * threeWaySort [random + duplicate]: (3.73 seconds)
     * <p>
     * 可见advancedSort性能最优(因为优化的最好)
     * <p>
     * 并且threeWaySort处理重复数组的能力与advancedSort几乎相同, 且优于普通的快排
     * <p>
     * 对于sort2系列, 优于采用的是挖坑法, 且没有对重复子数组进行特殊处理, 所以很容易陷入N^2复杂度!
     * <p>
     * 快速排序的性能优胜者是: advancedSort
     */
    @Test
    public void compareSort() {
        // 正常随机数组
        Integer[] a11 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000000000, 5000000);
        Integer[] a12 = Arrays.copyOf(a11, a11.length);
        Integer[] a13 = Arrays.copyOf(a11, a11.length);
        Integer[] a14 = Arrays.copyOf(a11, a11.length);
        Integer[] a15 = Arrays.copyOf(a11, a11.length);

        // 大量重复数组
        Integer[] a21 = RandomArrayUtil.getRandomBoxedIntArray(0, 1000, 5000000);
        Integer[] a22 = Arrays.copyOf(a21, a21.length);
        Integer[] a23 = Arrays.copyOf(a21, a21.length);
        Integer[] a24 = Arrays.copyOf(a21, a21.length);
        Integer[] a25 = Arrays.copyOf(a21, a21.length);

        System.out.println("Array created!");

        Stopwatch stopwatch = new Stopwatch();
        sort(a11);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random]:", stopwatch.elapsedTime());
        sort(a21);
        StdOut.printf("%s (%.2f seconds)\n", "sort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a11);
        assert isSorted(a21);
        System.out.println();

        stopwatch = new Stopwatch();
        sort2(a12);
        StdOut.printf("%s (%.2f seconds)\n", "sort2 [random]:", stopwatch.elapsedTime());
        sort2(a22);
        StdOut.printf("%s (%.2f seconds)\n", "sort2 [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a12);
        assert isSorted(a22);
        System.out.println();

        stopwatch = new Stopwatch();
        advancedSort(a13);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort [random]:", stopwatch.elapsedTime());
        advancedSort(a23);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a13);
        assert isSorted(a23);
        System.out.println();

        stopwatch = new Stopwatch();
        advancedSort2(a14);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort2 [random]:", stopwatch.elapsedTime());
        advancedSort2(a24);
        StdOut.printf("%s (%.2f seconds)\n", "advancedSort2 [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a14);
        assert isSorted(a24);
        System.out.println();

        stopwatch = new Stopwatch();
        threeWaySort(a15);
        StdOut.printf("%s (%.2f seconds)\n", "threeWaySort [random]:", stopwatch.elapsedTime());
        threeWaySort(a25);
        StdOut.printf("%s (%.2f seconds)\n", "threeWaySort [random + duplicate]:", stopwatch.elapsedTime());
        assert isSorted(a15);
        assert isSorted(a25);
        System.out.println();
    }

}
