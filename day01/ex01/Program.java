import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User userRob = new User("Rob", 10.12);
        User userBob = new User("Bob", 127834.03);
        User userCop = new User("Cob", 901);

        System.out.println(userRob.getIdentifier());
        System.out.println(userBob.getIdentifier());
        System.out.println(userCop.getIdentifier());
    }
}