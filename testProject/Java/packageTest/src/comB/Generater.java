package comB;
import comC.Person;

public class Generater {
    public Generater(){}

    public void print_person(Person person){
        System.out.println("name:"+person.getName());
        System.out.println("sex:"+person.getSex());
        System.out.println("address:"+person.getAddress());
    }
}
