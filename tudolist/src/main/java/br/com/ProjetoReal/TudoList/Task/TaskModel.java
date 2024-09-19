package br.com.ProjetoReal.TudoList.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.UUID;

/*
    necessita ter:
        Usuario (ID USUARIO)
        Descriçao
        Titulo
        Data de inicio
        Data e termino
        Prioridade

     */
@Data
@Entity(name = "tb_tasks")
// Construtor padrão, sem argumento, para conseguir criar um com argumento
@NoArgsConstructor
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String descricao;
    @Column(length = 50) // limita os caracteres a 50
    private String title;
    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;
    private String prioridade;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Construtor padrão

}
