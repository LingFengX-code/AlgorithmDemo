package com.xlf.algorithm;

import java.util.Arrays;

public class SortMain {
    public static void main(String[] args) {
        double[] nums = {4.4,3.3,1.1,2.2,5.5};
        AlgorithmSort.shellSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
