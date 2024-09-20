package br.com.ProjetoReal.TudoList.Task;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InterTaskRepository extends JpaRepository<TaskModel,UUID> {

   List <TaskModel> findByIdUser(UUID idUser);

   // o spring espera que exista um campo chamado idUser dentro da entidade TaskModel
   //O parâmetro UUID idUser indica que a busca será feita com base nesse valor específico de ID do usuário.

}
