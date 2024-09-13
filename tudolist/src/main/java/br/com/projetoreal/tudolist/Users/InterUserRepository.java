package br.com.projetoreal.tudolist.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

// sinal de menor e maior, significa que ela recebe um generator, atributos dinamicos
// qual classe nos estamos usando, e qual tipo de ID estamos usando
public interface InterUserRepository extends JpaRepository<UserModel, UUID> {



}
