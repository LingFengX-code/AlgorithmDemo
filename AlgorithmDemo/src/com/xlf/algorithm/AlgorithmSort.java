package com.xlf.algorithm;
//冒泡排序的实现
public class AlgorithmSort {
    /*
    一.冒泡排序
    思路：
        俩俩交换，大的放在后面，第一次排序后最大值已在数组末尾。因为俩俩交换，需要n-1趟排序（比如10个数，需要9趟排序）

    代码实现要点：
        两个for循环，外层循环控制排序的趟数，内层循环控制比较的次数。每趟过后，比较的次数都应该要减1
     */
    public static double[] BubbleSort(double[] nums,Boolean asc){
        //asc表示升序排列
        for(int i = 0;i < nums.length-1;i++){   //共需要比较nums.length-1轮
            for (int j = 0;j < nums.length-i-1;j++){  //每一轮比较的次数比上一轮减1
                if(asc){
                    if(nums[j] > nums[j+1]){
                        double temp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = temp;
                    }
                }else {
                    if(nums[j] < nums[j+1]){
                        double temp = nums[j];
                        nums[j] = nums[j+1];
                        nums[j+1] = temp;
                    }
                }
            }
        }
        return nums;
    }

    /*
    二.选择排序
    思路：
        找到数组中最大的元素，与数组最后一位元素交换。当只有一个数时，则不需要选择了，因此需要n-1趟排序
    代码实现要点：
        两个for循环，外层循环控制排序的趟数，内层循环找到当前趟数的最大值，随后与当前趟数组最后的一位元素交换
     */
    public static double[] SelectionSort(double[] nums,Boolean asc) {
        double max = 0; //最大数
        int index = -1; //数组角标
        for(int i = 0;i < nums.length-1;i++){
            for(int j = 0;j < nums.length-i;j++){
                if (asc){
                    if(max < nums[j]){
                        max = nums[j];
                        index = j;
                    }
                } else{
                    if(max == 0){ max=10000;} //此处不是很完善
                    if(max > nums[j]){
                        max = nums[j];
                        index = j;
                    }
                }
            }
            nums[index] = nums[nums.length-1-i];
            nums[nums.length-1-i] = max;
            max = 0;    //max归0
        }
        return nums;
    }
    /*
    三.插入排序
    思路：
        将一个元素插入到已有序的数组中，在初始时未知是否存在有序的数据，因此将元素第一个元素看成是有序的。
      与有序的数组进行比较，比它大则直接放入，比它小交换顺序
    代码实现：
        一个for循环内嵌一个while循环实现，外层for循环控制需要排序的趟数，内层循环为光标当前位置(并
      且插入的位置不能小于0)
     */
    public static void insertionSort(double[] nums) {
        double temp=-1;    //临时变量
        int index=0;  //光标
        for(int i=0;i<nums.length-1;i++){
            index=i+1;  //光标位置
            if(nums[index]>=nums[index-1]){continue;}
            temp = nums[index];
            while(temp<nums[index-1]){
                nums[index] = nums[index-1];
                index--;
                if(index<=0){ break; }//已是最小的值
            }
            nums[index] = temp;
        }
    }

    /*
    四.快速排序
    学习快速排序的前提：需要了解递归
    思路：
        在数组中找一个元素(节点)，比它小的放在节点的左边，比它大的放在节点右边。一趟下来，
      比节点小的在左边，比节点大的在右边。不断执行这个操作….
    代码实现：
        支点取中间，使用L和R表示数组的最小和最大位置。不断进行比较，直到找到比支点小(大)
      的数，随后交换，不断减小范围。递归L到支点前一个元素(j)。递归支点后一个元素(i)到
      R元素
     start:一般0开始
     end:数组长度-1
     */
    public static void quickSort(double[] nums,int start,int end) {
        if(start>=end) {return;}    //递归出口
        double center = nums[start];  //用来比较大小的值
        int left = start;  //左光标
        int right = end; //右光标
        while(left<right){
            while(nums[right] > center && right>left){
                right--;
            }
            nums[left] = nums[right];
            while(nums[left] <= center && right>left){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[right] = center;
        //开始递归
        quickSort(nums,start,right);
        quickSort(nums,left+1,end);
    }
    /*
    五.归并排序
    学习归并排序的前提：
        需要了解递归
    思路：
        将两个已排好序的数组合并成一个有序的数组。将元素分隔开来，看成是有序的数组，进行比较合并。
      不断拆分和合并，直到只有一个元素
    代码实现：
        在第一趟排序时实质是两个元素(看成是两个已有序的数组)来进行合并，不断执行这样的操作，最终
      数组有序，拆分左边，右边，合并…
     */
    /*
        拆分:将一个无序的数组拆成两半
     */
    public static void mergeSort(double[] nums,int start,int end) {
        if(start>=end) {return;}    //递归出口
        int middle = (start+end)/2; //第二个数组
        mergeSort(nums,start,middle); //继续拆分左边
        mergeSort(nums,middle+1,end);   //继续拆分右边
        merge(nums,start,middle,end);
    }
    /*  归并：把两个有序的数组归并在一个数组里
        nums：要排序的数组
        start：拆分后的第一个数组首元素下标
        middle：拆分的位置（middle+1为第二个数组的首元素下标）
        end：数组的末元素下标
     */
    private static void merge(double[] nums,int start,int middle,int end) {
        double[] tempNums = new double[end-start+1];    //临时数组
        int i = start;  //第一个数组的光标
        int j = middle+1; //第二个数组的光标
        int index = 0;  //临时数组的下标
        while(i<=middle && j<=end){
            if(nums[i] <= nums[j]){
                tempNums[index] = nums[i];
                i++;
            }else{
                tempNums[index] = nums[j];
                j++;
            }
            index++;
        }
        while(j<=end){  //右边数组还有值
            tempNums[index] = nums[j];
            j++;
            index++;
        }
        while(i<=middle){  //左边数组还有值
            tempNums[index] = nums[i];
            i++;
            index++;
        }
        //把临时数组的数据放入原数组中去
        for(int n=start,k=0;n<=end;n++,k++){
            nums[n] = tempNums[k];
        }
    }


    /*
        六.堆排序

        学习堆排序的前提：
            需要了解二叉树
        思路：
            堆排序使用到了完全二叉树的一个特性，根节点比左孩子和右孩子都要大，完成一次建堆的操作
          实质上是比较根节点和左孩子、右孩子的大小，大的交换到根节点上，直至最大的节点在树顶。随
          后与数组最后一位元素进行交换
        代码实现：
            只要左子树或右子树大于当前根节点，则替换。替换后会导致下面的子树发生了变化，因此同样
          需要进行比较，直至各个节点实现父>子这么一个条件
     */
    public static void heapSort(double[] nums,int start,int middle,int end) {

    }

    /*
    七.希尔排序

    思路：
        希尔排序实质上就是插入排序的增强版，希尔排序将数组分隔成n组来进行插入排序，直至该数组宏
      观上有序，最后再进行插入排序时就不用移动那么多次位置了～
    代码思路：
        希尔增量一般是gap = gap / 2，只是比普通版插入排序多了这么一个for循环而已。
     */
    /*public static void shellSort(double[] nums) {
       //boolean isOdd = (nums.length%2)==0 ? false : true;  //是否为奇数
        //int gap = nums.length/2;    //步长
        for(int gap=nums.length/2;gap>0;gap/=2){
            double temp = 0;    //临时变量
            int index = 0;    //光标
            for(int i=0;i<nums.length;i+=gap){
                index = i+gap;
                if(nums[index]>=nums[index-gap]){continue;}
                temp = nums[index];
                while(temp<nums[index-gap]){
                    nums[index] = nums[index-gap];
                    index -= gap;
                    if(index<=0){ break; }
                }
                nums[index] = temp;
            }
        }
    }*/

    /*
    八.基数排序(桶排序)

    思路：
        基数排序(桶排序)：将数字切割成个、十、百、千位放入到不同的桶子里，放一次就按桶子顺序回收
      一次，直至最大位数的数字放完～那么该数组就有序了
    代码实现：
        先找到数组的最大值，然后根据最大值/10来作为循环的条件(只要>0，那么就说明还有位数)。将个
      位、十位、…分配到桶子上，每分配一次就回收一次
     */
    public static void radixSort(double[] nums) {

    }
}