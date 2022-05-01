public interface UserList {
    public void addUser(User userToAdd);
    public User retrieveByID(int ID);
    public User retrieveByIndex(int index);
    public int retrieveNum();
}