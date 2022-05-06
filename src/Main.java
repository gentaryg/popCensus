
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        Stream<Person> infantStream = persons.stream();
        long infant = infantStream
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(infant);
        Stream<Person> recruitStream = persons.stream();
        Collection<String> recruit = recruitStream
                .filter(person -> person.getSex() == Sex.MAN)
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruit);
        Stream<Person> workableStream = persons.stream();
        Collection<String> workable = workableStream
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() < 65 : person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(workable);

    }
}
