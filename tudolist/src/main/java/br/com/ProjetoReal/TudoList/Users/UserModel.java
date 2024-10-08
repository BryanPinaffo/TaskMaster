package br.com.ProjetoReal.TudoList.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID; // da uma sequencia de id generator

@Data // ele é uma dependencia chamada projectlombok
// ajuda nos metodos getters e setters
// para baixar, vc so pesquisa ele no google e entra na opçao de maven, copia e cola no .xml

// podemos usar tbem o @Gettter e o @Setter para um unico

// nao esquecer que tem que baixar tbem o plugin lombok na IDE
@Entity(name = "tb_users") // estamos definindo as entidades (isso em si cria uma tabela)
public class UserModel {
    @Id // usar sempre o do jakarta.persistence  // definindo o id, a chave primaria
    @GeneratedValue(generator = "UUID") // fala para gerar um ID com o garador UUID
    private UUID id;
    @Column(unique = true) // vai ser uma coluna com uma retriçao de atributo unico
    private String userName;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
    //define quando o dado foi criado


    //se colocar na parte de cima do atributo, ele so vai criar os getter e os setters para aquele atributo
}
