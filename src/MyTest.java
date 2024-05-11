public class MyTest {
    String str1;
    public MyTest(String str1){
        this.str1 = str1;
    }
    @Override
    public int hashCode(){
        int hash = 31;
        for (int i = 0; i < str1.length(); i++){
            hash = 31* hash + str1.charAt(i);
        }
        return hash;
    }
}
