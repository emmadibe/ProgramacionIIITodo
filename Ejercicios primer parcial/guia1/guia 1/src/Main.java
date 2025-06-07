import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        //Carga de las listas originales
        List<Integer> noumberArray = loadNoumberArray(20);
        List<Integer> newNoumberList = new ArrayList<>();

        List<String> stringList = loadStringArray();
        //Ejercicio 1
         newNoumberList = noumberArray.stream()
                 .filter(n -> n % 2 == 0)
                 .toList();
         newNoumberList.stream()
                 .forEach(n -> System.out.println(n));

         //Ejercicio 2
        stringList.stream()
                .map(n -> n.toUpperCase())
                .forEach(n -> System.out.println(n));

        //Ejercicio 3
        noumberArray.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(n -> System.out.println(n));

        //Ejercicio 4
        long noumberBiggestThanSeven = noumberArray.stream()
                .filter(n -> n > 7)
                .count();
        System.out.println("la cantidad de numeros mayores a 7 son " + noumberBiggestThanSeven);

        //Ejercicio 5
        noumberArray.stream()
                .limit(5)
                .forEach(n -> System.out.println(n));

        //Ejercicio 6
        newNoumberList = stringList.stream()
                .map(n -> n.length())
                .toList();
        newNoumberList.stream()
                .forEach(n -> System.out.println(n));

        //Ejercicio 7
        Optional<String> onlyOneString = stringList.stream()
                .reduce((n1, n2) -> n1 + ", " + n2);
        System.out.println("La palabra completa es " + onlyOneString.orElse("empty"));

        //Ejercicio 8


    }

    public static List<Integer> loadNoumberArray(int max)
    {
        List<Integer> noumberArray = new ArrayList<>();
        for(int i = 0; i < max; i++){
            noumberArray.add(i, i +3);
        }
        return noumberArray;
    }

    public static List<String> loadStringArray()
    {
        List<String> stringArray = new ArrayList<String>(9);
        stringArray.add( "emmanuel");
        stringArray.add( "jorge");
        stringArray.add( "santiago");
        stringArray.add( "natasha");
        stringArray.add( "rocio");
        stringArray.add( "bianca");
        stringArray.add( "juan");
        return stringArray;
    }
}