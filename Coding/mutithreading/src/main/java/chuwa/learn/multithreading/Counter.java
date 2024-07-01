package chuwa.learn.multithreading;

class Counter {
    private Integer count = 0;
    private static Integer globalCount = 0;

    public static Integer getGlobalCount() {
        return globalCount;
    }
    public static void setGlobalCount(Integer globalCount) {
        Counter.globalCount = globalCount;
    }
    public int incrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count++;
        }
        return count;
    }

    public int decrementCount() {
        int k = 10000;
        while (k-- > 0) {
            count--;
        }
        return count;
    }
    public Integer getCount() {
        return count;
    }
}
