public class MyTest {
    String str1;
    String str2;
    public MyTest(String str1, String str2){
        this.str1 = str1;
        this.str2 = str2;
    }
    @Override
    public int hashCode(){
        int hash = 31;
        for (int i = 0; i < str1.length(); i++){
            hash = 31* hash + str1.charAt(i);
        }
        for (int i = 0; i < str2.length(); i++){
            hash = 31* hash + str2.charAt(i);
        }
        return hash;
    }
}
