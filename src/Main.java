import java.util.Random;
public class Main {
    public static Random rand = new Random();

    public static void main(String[] args) {

        MyHashTable<MyTest , Student> table =  new MyHashTable<MyTest , Student>();

        table.put(new MyTest("abc") , new Student("abc" , "def"));

        for(int i =0;i<1000000000;i++) {
           table.put(new MyTest(generateRandomString(5)) , new Student(generateRandomString(5) , generateRandomString(5)));
        }

        System.out.println(table.getM());

        for(int i =0;i< table.getM();i++) {
            System.out.println(table.countElements(i));
        }



    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for(int i = 0 ; i < length ; i++) {
            result.append(characters.charAt(rand.nextInt(characters.length())));
        }
        return result.toString();
    }
}
