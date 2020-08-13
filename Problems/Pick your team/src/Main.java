import java.util.Scanner;

class SelectionContext {

    private PersonSelectionAlgorithm algorithm;

    public void setAlgorithm(PersonSelectionAlgorithm algorithm) {
        // write your code here
        this.algorithm = algorithm;
    }

    public Person[] selectPersons(Person[] persons) {
        // write your code here
        return algorithm.select(persons);
    }
}

interface PersonSelectionAlgorithm {

    Person[] select(Person[] persons);
}

class TakePersonsWithStepAlgorithm implements PersonSelectionAlgorithm {
    int step;

    public TakePersonsWithStepAlgorithm(int step) {
        // write your code here
        this.step = step;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        int resultLen = (persons.length - 1) / step + 1;
        Person[] result = new Person[resultLen];

        for (int i = 0; i < result.length; i++) {
            result[i] = persons[i * step];
        }

        return result;
    }
}


class TakeLastPersonsAlgorithm implements PersonSelectionAlgorithm {
    int count;

    public TakeLastPersonsAlgorithm(int count) {
        // write your code here
        this.count = count;
    }

    @Override
    public Person[] select(Person[] persons) {
        // write your code here
        Person[] result = new Person[count];
        for (int i = 0; i < result.length; i++) {
            result[i] = persons[i + persons.length - count];
        }
        return result;
    }
}

class Person {

    String name;

    public Person(String name) {
        this.name = name;
    }

}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final int count = Integer.parseInt(scanner.nextLine());
        final Person[] persons = new Person[count];

        for (int i = 0; i < count; i++) {
            persons[i] = new Person(scanner.nextLine());
        }

        final String[] configs = scanner.nextLine().split("\\s+");

        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
        SelectionContext ctx = new SelectionContext();
        ctx.setAlgorithm(alg);

        final Person[] selected = ctx.selectPersons(persons);
        for (Person p : selected) {
            System.out.println(p.name);
        }
    }

    public static PersonSelectionAlgorithm create(String algType, int param) {
        switch (algType) {
            case "STEP": {
                return new TakePersonsWithStepAlgorithm(param);
            }
            case "LAST": {
                return new TakeLastPersonsAlgorithm(param);
            }
            default: {
                throw new IllegalArgumentException("Unknown algorithm type " + algType);
            }
        }
    }
}
//public class Main {
//    public static void main(String[] args) {
//
//        final int count = 8;
//        final Person[] persons = new Person[count];
//
//        for (int i = 0; i < count; i++) {
//            persons[i] = new Person("person_" + i);
//        }
//
//        for (Person p : persons) {
//            System.out.println(p.name);
//        }
//        System.out.println("---------------");
//
//
////        final String[] configs = scanner.nextLine().split("\\s+");
//
////        final PersonSelectionAlgorithm alg = create(configs[0], Integer.parseInt(configs[1]));
//        final PersonSelectionAlgorithm alg = create("LAST", 2);
//        SelectionContext ctx = new SelectionContext();
//        ctx.setAlgorithm(alg);
//
//
//        final Person[] selected = ctx.selectPersons(persons);
//        for (Person p : selected) {
//            System.out.println(p.name);
//        }
//    }
//
//    public static PersonSelectionAlgorithm create(String algType, int param) {
//        switch (algType) {
//            case "STEP": {
//                return new TakePersonsWithStepAlgorithm(param);
//            }
//            case "LAST": {
//                return new TakeLastPersonsAlgorithm(param);
//            }
//            default: {
//                throw new IllegalArgumentException("Unknown algorithm type " + algType);
//            }
//        }
//    }
//}