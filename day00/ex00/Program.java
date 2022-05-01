public class Program {
    public static void main(String[] args) {
        int someInt = 999999;
        int res = 0;

		while (someInt != 0) {
			res += someInt % 10;
			someInt /= 10;
		}
        System.out.println(res);
    }
}
