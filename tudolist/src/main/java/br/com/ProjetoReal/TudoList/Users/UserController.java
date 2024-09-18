package br.com.ProjetoReal.TudoList.Users;


import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController
@RequestMapping("/UserController")
public class UserController {

    // esta falando que oq vc esta recebendo ja esta dentro do body, ja na estrutura

    @PostMapping("/")
    // RequestBody, indica que o parametro UserModel deve ser preenchido com os dados enviados no corpo da requisiçao HTTP
    //normalmene em forma Json
    public ResponseEntity create(@RequestBody UserModel userModel) {
        // ResponseEntity : É uma forma mais flexível de retornar respostas HTTP
        // do que simplesmente retornar objetos ou valores simples nas suas controladoras (controllers).
        //incluindo o corpo da resposta, o código de status HTTP e os cabeçalhos de resposta

        UserModel user = this.userRepository.findByUserName(userModel.getUserName());
        // esta pegando o username


        if (user != null) {

            // return null; // para o o fluxo do codigo, fazendo a mensagem ficar na ultima linha
            // podemos retornar um status Code (define se foi um sucesso ou um erro, como o codigo 200, que foi tudo ok)


            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");

        }


        String passwordHashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        //hash, metodo de criptografia
        // 12, indica o numero de rouds da operaçao hashing, determina a complexidade do processo
        // userModel.get... converte a senha do usuario para um array de caracteres, que é o formato esperado pela funçao de hashing

        userModel.setPassword(passwordHashed); // coloca a senha no password

        UserModel userCreated = this.userRepository.save(userModel);

        //this.userRepository:  Refere-se à instância de userRepository que é um repositório do Spring Data JPA,
        // responsável pela comunicação com o banco de dados.

        // .save(userModel) :O método save do Spring Data JPA salva o objeto userModel no banco de dados.
        // Ele também pode retornar o objeto salvo, que geralmente contém o ID gerado pelo banco de dados.

        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
        //Retorna o usuário criado com todos os seus detalhes, com um ID gerado automaticamente.

        // lida com a criaçao de um novo UserModel e o salva no banco de dados

    }

    @Autowired // spring esta gerenciando o meu userRepository, instanciando e afins.
    private InterUserRepository userRepository;
}
