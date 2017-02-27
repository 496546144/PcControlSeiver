public class Main {

    /**
     * 作者　　: 李坤
     * 创建时间: 2017/2/17 9:53
     * <p>
     * 方法功能：有一对兔子，从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数？
     */

    public static void main(String[] args) {
        duiNumber(1, 24);
    }


    public static int duiNumber(int start, int all) {
        int number = 1;
        for (int i = start; i <= all; i++) {
            if (i >= start + 2) {//3月开始
                number += 1;
                if ((i - 1) / 2 > 1) {
                    number += Math.pow(2, (i - 1) / 2 - 1);
                }
            }
            System.out.println("第" + i + "月:" + number);
        }
        return number;
    }
}
