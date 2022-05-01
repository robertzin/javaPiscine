public class UserIdsGenerator {
    int id = 0;
    private static UserIdsGenerator instance;

    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public int generateId() {
        id++;
        return id;
    }
}
