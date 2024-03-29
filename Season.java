import java.util.Scanner;
public class Season {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("월(1 ~ 12)을 입력하시오");
        int month = scanner.nextInt();

        if(month < 6 && month > 2){
            System.out.println("봄입니다.");
        }
        else if(month < 9 && month > 5){
            System.out.println("여름입니다.");
        }
        else if(month < 12 && month > 8){
            System.out.println("가을입니다.");
        }
        else if(month > 12){
            System.out.println("잘못된 입력입니다.");
        }
        else{
            System.out.println("겨울입니다.");
        }
        scanner.close();
    }
}
