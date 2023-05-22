package Chapter03;

import java.util.Arrays;
import java.util.Comparator;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class PersonComparatorTest {
    public static void main(String[] args) {
        Person[] people = {
            new Person("Zoo", 25),
            new Person("Bob", 30),
            new Person("Tomy", 20),
            new Person("David", 35)
        };

        // Comparator를 사용하여 people 배열을 이름(Name)을 기준으로 오름차순 정렬하세요.
        // 이름이 같은 경우 나이(Age)를 기준으로 내림차순 정렬하세요.
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge).reversed();

        Arrays.sort(people, nameComparator.thenComparing(ageComparator));

        // 정렬 후 결과를 출력
        for (Person person : people) {
            System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
        }
    }
}
