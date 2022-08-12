package JUnit5Test;



import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;

public class parametirizedTests {

    @ParameterizedTest(name = "Run: {index} - value: {arguments}") //looks nicer on the test box
    @ValueSource(ints = {1,5,6}) //valueSource can get ONE parameter, so we used an array
    void intValues(int param){
        System.out.println("theParam = " + param);
    }

    @ParameterizedTest
    @NullSource //passes a null parameter
    @EmptySource //passes an empty string - doesn't work with primitives
    @ValueSource(strings = {"firstStr", "secondStr"})
    void stringValues(String param){
        System.out.println("stringParam = " + param);
    }


    //USING csvSource annotation

    @ParameterizedTest (name = "Run: {index} - values: {arguments}")
    @CsvSource(value = {"Superman,Clark","Batman,Bruce","Daredevil,Matt"})
    void csvSource_string(String str1, String str2){
        System.out.println("Alias = " + str1 + ", Name = " + str2);
    }

    @ParameterizedTest(name = "Run: {index} - values: {arguments}")
    @CsvSource(value = {"Steve,32,true","captain,21,false","Bucky,5,true"})
    void csvSource_boolean(String Name, int num, boolean param){
        System.out.println("Name: " + Name + " - Number: " +  num + " - Boolean: " + param);
    }

    @ParameterizedTest(name = "Run: {index} - values: {arguments}")
    @CsvSource(value = {"captain america, 'Steve,rogers'","winter Soldier, 'Bucky,Banners'"})// including commas with ''
    void csvSource_stringWithComma (String alias, String name){
        System.out.println("Alias: " + alias + " - Name: " + name);
    }

    @ParameterizedTest(name = "Run: {index} - values: {arguments}")
    @CsvSource(value = {"Steve?Rogers", "Bucky?Barnes"}, delimiter = '?') //specifying a separator(delimiter)
    void csvSource_delimiter(String str1, String str2){
        System.out.println("Name: " + str1 + " - Surname: " + str2);
    }

    //using the csv files shoppingList and shoppingList2
    @ParameterizedTest(name = "Run: {index} - values: {arguments}")
    @CsvFileSource(files ={ "src/test/resources/params/shoppingList.csv", "src/test/resources/params/shoppingList2.csv"},numLinesToSkip = 1)//skips the first line of titles in the file
    void csvFileSourceTest(String name, double price, int qnt, String unit, String provider){
        System.out.println("Name: " + name +" - Price: " + price + " - Quantity: " + qnt + " - Unit: " + unit + " - Suppler: " + provider);
    }

    //MethodSource annotation
    @ParameterizedTest
    @MethodSource(value = "sourceString")
    void methodSource(String param1){

    }

    List<String> sourceString(){
        //processing done here
        return Arrays.asList("tomato", "carrot", "cabbage");
    }
}

