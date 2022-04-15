package comA;

import comB.Generater;
import comC.Person;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("张三","男","吉林省长春市区");
        Generater g = new Generater();
        g.print_person(person);
    }
}
