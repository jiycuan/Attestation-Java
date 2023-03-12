import java.io.IOException;
import java.util.Scanner;

public class UI {

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую необходимой операции: ");
        System.out.println("1 - Добавить новую игрушку");
        System.out.println("2 - Поменять вес игрушки");
        System.out.println("3 - Организовать розыгрыш игрушек");
        int whatDoYouWant = in.nextInt();
        if (whatDoYouWant==1) {
            Toys.addNewToy();         
        } 
        if (whatDoYouWant==2) {
            Toys.changeProbability();
        }
        if (whatDoYouWant==3) {
            Lottery.draw();
        }
        in.close();
    }
}
