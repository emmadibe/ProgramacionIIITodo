import Models.Producto;
import Services.ProductoService;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main
{
    public static void main(String[] args)
    {
        List<Producto> arrayProducto = ProductoService.cargarListaProducto();
        //Ejercicio 1
        List<Producto> ejercicio1 = arrayProducto.stream()
                .filter(p -> p.getPrecio() > 1000 && p.getCategoria().equals("Electrónica"))
                .sorted() //Acordate que requiere la implementación de la interfaz comparable.
                .collect(Collectors.toList());
        System.out.println(ejercicio1);

        //Ejercicio 2
        OptionalDouble promedioHogar = arrayProducto.stream()
                .filter(p -> p.getCategoria().equals("Hogar") && p.getStock() > 0)
                .mapToInt(p -> (int) p.getPrecio()) //Me quedo con el precio
                .average();
        System.out.println("El promedio de precios de los productos de la categoría Hogar " + promedioHogar);

        System.out.println("\nSegunda forma de hacerlo\n ");
        Double promedio2 = arrayProducto.stream()
                .filter(p -> p.getCategoria().equals("Hogar") && p.getStock() > 0)
                .map(p -> (int) p.getPrecio())
                .collect(Collectors.averagingInt(n -> n));
        System.out.println("El promedio obtenido con la segunda forma de resolver el ejercicio 2 es " + promedio2);

        //Ejercicio 3
        Map<String, Producto> mapEjercicio3 = arrayProducto.stream()
                .collect(Collectors.toMap(p -> p.getCategoria(), //key
                                            p -> p, //value
                                             (p1, p2) -> p1.getPrecio() > p2.getPrecio() ? p1 : p2)); // Merge Function. P ara decidir qué producto se queda en la lista ante dos alternativas.
        System.out.println(mapEjercicio3);

        //Ejercicio 4
        List<String> ejercicio4 = arrayProducto.stream()
                .filter(p -> p.getStock() > 10 && p.getCategoria().equals("Deportes"))
                .map(p -> p.getNombre().toLowerCase()) //Me quedo solo con el nombre del producto y lo paso a minúscula.
                .collect(Collectors.toList());
        System.out.println(ejercicio4);

        //Ejercicio 5
        Optional<Producto> chepeastProduct = arrayProducto.stream()
                .reduce((a, b) -> a.getPrecio() < b.getPrecio() ? a : b);
        Producto noExiste = new Producto("no existe", 0, "no existe", 0);
        System.out.println("El producto más barato es " + chepeastProduct.orElse(noExiste));

        //Ejercicio 6
        List<String> ejercicio6 = arrayProducto.stream()
                .filter(p -> p.getStock() > 0 && p.getNombre().length() > 5)
                .map(p -> p.getNombre())
                .sorted() //Los wrapper, como los String, ya tienen por defecto un método comparable incorporado.
                .collect(Collectors.toList());
        System.out.println(ejercicio6);

        //Ejercicio 7
        OptionalInt stockTotal = arrayProducto.stream()
                .filter(p -> p.getPrecio() > arrayProducto.stream()
                        .collect(Collectors.averagingInt(pr -> (int) pr.getPrecio())))//Acá me quedo con los productos cuyo precio es superior al promedio
                .mapToInt(pr -> pr.getStock()) //Me quedo con el stock de los productos cuyo precio es superior al promedio
                .reduce((a, b) -> a + b);
        System.out.println("El stock total es de " + stockTotal.orElse(-1));

        //Ejercicio 8
        Map<String, Long> productsByCategory = arrayProducto.stream()
                .collect(Collectors.groupingBy(p -> p.getCategoria(),
                                                Collectors.counting())); //Acá tengo la cantidad de productos distintos que hay por categoría.
        System.out.println(productsByCategory);

        Map<String, Integer> stockByCategory = arrayProducto.stream()
                .filter(p -> productsByCategory.get(p.getCategoria()) >= 3)//Me quedo con los productos que pertenezcan a una categoría que posee, al menos, tres productos.
                .collect(Collectors.groupingBy(p -> p.getCategoria(), //La key del mapa es la categoría
                        Collectors.summingInt(p -> p.getStock()))); //El value del mapa es el stock total de todos los productos de la categoría.
        System.out.println("Stock total por categoría: \n" + stockByCategory);

        //Ejercicio 9
        List<Producto> ejercicio9 = new ArrayList<>();
        arrayProducto.stream()
                .filter(p -> p.getStock() > 20)
                .forEach(p -> ejercicio9.add(ProductoService.aplicarDescuento(0.15, p)));

        System.out.println("Lista de productos con los descuentos aplicados: " + ejercicio9);

        //Ejercicio 10
         double gananciaTotal = arrayProducto.stream()
                 .mapToDouble(p -> ProductoService.calcularGanancia(p)) //Me quedo solo con la ganancia que obtengo al vender cada producto.
                 .sum(); //Sumo todos los elementos de la secuencia.
        System.out.println("La ganancia total es de " + gananciaTotal);
    }

}