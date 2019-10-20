package AntiUI;

public class testClass {
    public static void main(String[] args) {
        FileOperator operator = FileOperator.getInstance();
        operator.initialFile("Google.txt");
        System.out.println("success");

    }
}
