import java.util.Arrays;
import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        UsersArrayList userList = new UsersArrayList();
        System.out.print("Free space left: " + userList.getSpaceLeft() + " ");
        for (int i = 0; i < 12; i++) {
            userList.addUser(new User("someName_" + i, i * 190 / 8.2));
            System.out.print(userList.getSpaceLeft() + " ");
        }

        System.out.println();
        System.out.println("Number of users: " + userList.retrieveNum());
        for (int i = 0; i < userList.getUserArray().length; i++) {
            System.out.println(userList.getUserArray()[i].getName() + " id: " + userList.getUserArray()[i].getIdentifier());
        }
        System.out.println(userList.retrieveByIndex(2).getIdentifier());
        System.out.println(userList.retrieveByID(4).getName());
//        System.out.println(userList.retrieveByID(28).getName());
    }
}