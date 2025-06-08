package view;

import models.entities.Bebida;
import models.entities.Pedido;
import models.enums.EstadoPedido;
import models.enums.TipoBebida;
import models.exceptions.PedidoInvalidoException;
import services.BebidaService;
import services.ClienteService;
import services.PedidoService;

import javax.crypto.spec.OAEPParameterSpec;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class MenuController {

    private static final Scanner scanner = new Scanner(System.in);

    private static final BebidaService bebidaService = new BebidaService();
    private static final PedidoService pedidoService = new PedidoService();
    private static final ClienteService clienteService = new ClienteService();

    public static void mostrarMenuPrincipal()
    {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. CRUD de bebidas");
            System.out.println("2. Filtrar bebidas por tipo");
            System.out.println("3. Filtrar bebidas segun stock");
            System.out.println("4. Buscar clientes por localidad");
            System.out.println("5. Listar pedidos por cliente");
            System.out.println("6. Cambiar el estado de un pedido");
            System.out.println("7. Ingresar un nuevo pedido con validaciones");
            System.out.println("8. Contar la cantidad de pedidos agrupados por estado");

            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Que operacion desea realizar?");
                    System.out.println("A - Insertar una bebida nueva");
                    System.out.println("B - Listar todas las bebidas");
                    System.out.println("C - Actualizar una bebida");
                    System.out.println("D - Eliminar una bebida");

                    char operacion = scanner.nextLine().charAt(0);
                    switch (operacion){
                        case 'A':
                            System.out.println("Nombre: ");
                            String nombre = scanner.nextLine();
                            System.out.println("Stock: ");
                            int stock = scanner.nextInt();
                            System.out.println("Precio por unidad: ");
                            int precioUnitario = scanner.nextInt();
                            System.out.println("Tipo: ");
                            String tipoString = scanner.nextLine();
                            TipoBebida tb = TipoBebida.valueOf(tipoString);
                            Bebida bebida = new Bebida(nombre, precioUnitario, stock, tb);

                            bebidaService.add(bebida);
                            break;
                        case 'B':
                            bebidaService.getAll().forEach(System.out::println);
                            break;
                        case 'C':
                            System.out.println("id");
                            int id = scanner.nextInt();
                            Optional<Bebida> bebida2 = bebidaService.findById(id);
                            if(!bebida2.isPresent()){
                                System.out.println("No existe una bebida con ese id, pal");
                            }else {
                                System.out.println("Nombre: ");
                                nombre = scanner.nextLine();
                                System.out.println("precioUnitario");
                                precioUnitario = scanner.nextInt();
                                System.out.println("stock");
                                stock = scanner.nextInt();
                                System.out.println("Tipo de bebida");
                                tipoString = scanner.nextLine();
                                tb = TipoBebida.valueOf(tipoString);

                                bebida = new Bebida(id, nombre, precioUnitario, stock, tb);

                                bebidaService.update(bebida);
                            }
                            break;
                        case 'D':
                            System.out.println("Id de la bebida a eliminar: ");
                            id = scanner.nextInt();
                            Optional<Bebida> bebida1 = bebidaService.findById(id);
                            if(bebida1.isPresent()){
                                bebidaService.delete(id);
                                System.out.println("Bebida eliminada con éxito");
                            }else {
                                System.out.println("No existe una bebida con ese id.");
                            }
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Tipo por el cual filtrar las bebidas: ");
                    String tipoSTRING = scanner.nextLine();
                    TipoBebida tb = TipoBebida.valueOf(tipoSTRING);

                    bebidaService.bebidaFiltrada(tb).forEach(System.out::println);

                    break;
                case 3:
                    System.out.println("Stock máximo para mostrar bebidas");
                    int maxStock = scanner.nextInt();
                    bebidaService.bebidaFiltradaPorStock(maxStock).forEach(System.out::println);

                    break;
                case 4:
                    System.out.println("localidad por la cual buscar clientes: ");
                    String localidad = scanner.nextLine();

                    clienteService.getAllByLocality(localidad).forEach(System.out::println);

                    break;
                case 5:
                    System.out.println("Id del cliente: ");
                    int clienteID = scanner.nextInt();
                    pedidoService.getAllByCliente(clienteID).forEach(System.out::println);

                    break;
                case 6:
                    System.out.println("Cambiar el estado de un pedido ");
                    System.out.println("Ingresar el id del pedido que deseas modificar el estado: ");
                    int id = scanner.nextInt();
                    Optional<Pedido> pedido = pedidoService.findByID(id);
                    pedido.ifPresent(p -> System.out.println("El estado actual del pedido es " + p.getEstadoPedido().getNombre()));
                    System.out.println("Ingresar el nuevo estado del pedido: ");
                    EstadoPedido nuevoEstadoPedido = EstadoPedido.valueOf(scanner.nextLine());

                    pedidoService.cambiarEstadoPedido(id, nuevoEstadoPedido);
                    break;
                case 7:
                    System.out.println("clienteID");
                    int clienteId = scanner.nextInt();
                    System.out.println("bebida id");
                    int bebidaiD = scanner.nextInt();
                    System.out.println("Cantidad");
                    int cant = scanner.nextInt();
                    System.out.println("Estado");
                    String estadoSTR = scanner.nextLine();
                    EstadoPedido estadoPedido = EstadoPedido.valueOf(estadoSTR);
                    Pedido pedido1 = new Pedido(clienteId, bebidaiD, cant, estadoPedido);
                    try {
                        pedidoService.add(pedido1);
                    } catch (PedidoInvalidoException e) {
                        System.out.println(e);
                    }
                    break;
                case 8:
                    Map<EstadoPedido, Long> pedidoLongMap = pedidoService.getCantidadByEstado();
                    pedidoLongMap.forEach((e, c) -> System.out.println(e + " " + c));
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
