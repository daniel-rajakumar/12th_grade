public class test {

    test(){
        System.out.println(sum(new int[]{1, 2, 3, (int)3.9}));
    }

    int sum(int[] arr){
        int sum = 0;
        for (int i: arr) 
            sum += i;
        return sum;
    }

    public static void main(String[] args) {
        new test();
    }

    
}
