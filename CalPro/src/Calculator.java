public class Calculator {

    int result;

    public int add(int a,int b){
        int result = a + b;
        System.out.println(result);
        return result;
    }

    public int sub(int a,int b){
        int result = a - b;
        System.out.println(result);
        return result;
    }
    public int mul(int a,int b){
        int result = a * b;
        System.out.println(result);
        return result;
    }
    public int div(int a,int b){
        int result = a / b;
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.add(10,10);
        calculator.sub(10,10);
        calculator.mul(10,10);
        calculator.div(10,10);


    }

}
