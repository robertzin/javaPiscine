public class UsersArrayList implements UserList {
    private User[] userArray = new User[10];
    private int spaceLeft = 10;

    public int getSpaceLeft() {
        return spaceLeft;
    }

    static class UserNotFoundException extends RuntimeException {
    }

    @Override
    public void addUser(User userToAdd) {
        if (spaceLeft < 1) {
            int oldCapacity = userArray.length;
            int newCapacity = oldCapacity + oldCapacity / 2;
            User[] newArray = new User[newCapacity];
            int i;

            for (i = 0; i < oldCapacity; i++) {
                newArray[i] = userArray[i];
            }
            newArray[i] = userToAdd;
            userArray = newArray;
            spaceLeft = newCapacity - oldCapacity - 1;
        } else {
            int i = 0;

            while (i < userArray.length - spaceLeft)
                i++;
            userArray[i] = userToAdd;
            spaceLeft -= 1;
        }
    }

    @Override
    public User retrieveByID(int ID) {
        if (ID < 0)
            throw new UserNotFoundException();
        for(int i = 0; i < userArray.length; i++) {
            if (userArray[i].getIdentifier() == ID)
                return userArray[i];
        }
        throw new UserNotFoundException();
    }

    @Override
    public User retrieveByIndex(int index) {
        if (index < userArray.length)
            return userArray[index];
        return null;
    }

    @Override
    public int retrieveNum() {
        return userArray.length - spaceLeft;
    }

    public User[] getUserArray() {
        User[] retArray = new User[userArray.length - spaceLeft];
        for (int i = 0; i < userArray.length - spaceLeft; i++)
            retArray[i] = userArray[i];
        return retArray;
    }
}
