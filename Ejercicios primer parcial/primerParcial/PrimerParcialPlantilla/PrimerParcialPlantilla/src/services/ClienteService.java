package services;

import models.entities.Cliente;
import repositories.impl.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClienteService
{
    private ClienteRepository clienteRepository;

    public ClienteService()
    {
        this.setClienteRepository(new ClienteRepository());
    }

    public List<Cliente> getAllByLocality(String localidad)
    {
        try {
            List<Cliente> clientesList = clienteRepository.getAll().stream()
                    .filter(cliente -> cliente.getLocalidad().equals(localidad))
                    .collect(Collectors.toList());
            return clientesList;
        }catch (SQLException e){
            System.err.println("Error  " + e);
            return List.of();
        }
    }

    public Optional<Cliente> findByID(int id)
    {
        try {
            return Optional.ofNullable(clienteRepository.findById(id));
        } catch (SQLException e) {
            System.err.println("El cliente no existe " + e);
            return Optional.empty();
        }
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }

    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
}
