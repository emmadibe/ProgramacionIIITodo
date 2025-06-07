package views;

import models.entitie.Producto;
import models.enums.Categoria;
import services.ProductoService;

import java.lang.classfile.Opcode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.spi.AbstractResourceBundleProvider;

public class MenuController
{
    public static final ProductoService productoService = new ProductoService();
    public static final Scanner scanner = new Scanner(System.in);
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static void mainMenu()
    {
        int opcion = 0;
        do{
            do{
                System.out.println("Elegir una opcion: \n" +
                        "0) Go out\n" +
                        "1) CRUD de productos\n" +
                        "2) Filtrar por categoria y precio\n");
                opcion = scanner.nextInt();
                MenuController.opcion(opcion);
            }while (opcion < 0 || opcion > 10);
        }while (opcion != 0);
    }

    public static void opcion(int opciones)
    {
        switch (opciones)
        {
            case 0:
                System.out.println("Hasta luego!\n");
                break;
            case 1:
                System.out.println("Qué operación del CRUD desea hacer? \n" +
                        "A) Agregar un producto\n" +
                        "B)Eliminar un producto\n" +
                        "C) Listar los productos\n" +
                        "D)Modificar un producto\n" +
                        "F) Salir");
                scanner.nextLine();
                char letra = scanner.nextLine().charAt(0);
                switch (letra)
                {
                    case 'A':
                        System.out.println("Nombre del producto: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Stock");
                        int stock = scanner.nextInt();
                        System.out.println("Pecio Unitario ");
                        int precioUnitario = scanner.nextInt();
                        System.out.println("Categoría ");
                        scanner.nextLine();
                        String categoriaSTR = scanner.nextLine();
                        Categoria ctg = Categoria.valueOf(categoriaSTR);
                        System.out.println("Fecha de vencimiento yyyy-MM-dd: \n");
                        String fvSTR = scanner.nextLine();
                        LocalDate fechaVencimiento = LocalDate.parse(fvSTR, formatter);

                        Producto producto = new Producto(nombre, ctg, precioUnitario, stock, fechaVencimiento);
                        productoService.add(producto);
                        break;
                    case 'B':
                        System.out.println("Id del producto a eliminar: \n");
                        int id = scanner.nextInt();
                        productoService.delete(id);
                        break;
                    case 'C':
                        productoService.getAll().forEach(System.out::println);
                        break;
                    case 'D':
                        System.out.println("Id del producto a actualizar: ");
                        int ID = scanner.nextInt();

                        Optional<Producto> producto1 = productoService.findByID(ID);
                        if(producto1.isEmpty()){
                            System.err.println("No existe un producto con ese id");
                        }else{
                            System.out.println("Datos del producto: \n " + producto1.get());
                            System.out.println("Nuevo nombre del producto ");
                            scanner.nextLine();
                            String nombRe = scanner.nextLine();
                            System.out.println("Categoria ");
                            Categoria categoria = Categoria.valueOf(scanner.nextLine());
                            System.out.println("Precio unitario");
                            int precioUn = scanner.nextInt();
                            System.out.println("Stock ");
                            int stocK = scanner.nextInt();
                            System.out.println("Nueva fecha de vencimiento \n");
                            scanner.nextLine();
                            String fechaSTR = scanner.nextLine();
                            LocalDate fechaV = LocalDate.parse(fechaSTR, formatter);

                            Producto producto2 = new Producto(ID, nombRe, categoria, precioUn, stocK, fechaV);
                            productoService.update(producto2);
                        }
                        break;
                    case 'F':
                        System.out.println("ADIOS!\n");
                        break;
                }
                break;
            case 2:
                System.out.println("Categoria: ");
                scanner.nextLine();
                Categoria categoria = Categoria.valueOf(scanner.nextLine());
                System.out.println("Precio minimo ");
                int minPrice = scanner.nextInt();
                System.out.println("Precio maximo");
                int maxPrice = scanner.nextInt();

                productoService.ejercicio2(categoria, minPrice, maxPrice).forEach(System.out::println);
                break;
        }
    }
}
