public class Main {
    public static void main(String[] args) {
        Animal gnomo = new Animal("Gnomo", 5, 120,20);
        System.out.println(gnomo.isHeavy());
        System.out.println(gnomo.isTall());
        Animal giraffa = new Animal("Girafa", 2, 400,700);
        System.out.println(giraffa.isHeavy());
        System.out.println(giraffa.isTall());
    }
}
