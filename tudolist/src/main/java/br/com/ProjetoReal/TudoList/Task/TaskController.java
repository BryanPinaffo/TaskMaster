package br.com.ProjetoReal.TudoList.Task;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/tasks")
// Controller para manipular tarefas (CRUD)
public class TaskController {
    @Autowired
    private InterTaskRepository taskRepository;

    @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel, HttpServletRequest request, HttpServletResponse response) {

        Object idUser = request.getAttribute("idUser");
        // Esse método está sendo usado para recuperar o valor
        // que foi previamente armazenado no objeto HttpServletRequest usando o método setAttribute()
        // oq nesse caso seria o idUser
        // Em Java, a classe Object é a classe mais genérica de todas
        // Como request.getAttribute() pode retornar qualquer tipo de dado (dependendo de como ele foi armazenado),
        // o tipo retornado é automaticamente tratado como Object. Isso oferece flexibilidade,
        // pois você pode armazenar e recuperar atributos de diferentes tipos

        taskModel.setIdUser((UUID) idUser);
        // quando colocado o setter, foi necessario converter para UUID


        // Verifica se a data de início é anterior ao momento atual
        if (taskModel.getDataInicio().isBefore(LocalDateTime.now()) || taskModel.getDataTermino().isBefore(LocalDateTime.now())) {
            ResponseEntity.status(400).body("A data de início/termino deve ser maior que a data atual");
            return null;
        }

        // Verifica se a data de término é anterior à data de início
        if (taskModel.getDataTermino().isBefore(taskModel.getDataInicio())) {
            ResponseEntity.status(400).body("A data de término deve ser maior do que a data de inicio");
            //a diferença é que o response.sendError mostra apenas o erro ao usuario, e esse mostra o erro e a mensagem
            // ele mostra a mensagem ao cliente

            return null;
        }

        TaskModel taskModel1 = this.taskRepository.save(taskModel);

        return taskModel1;
    }

    @GetMapping("/")
    public List<TaskModel> lista(HttpServletRequest request) {
        Object idUser = request.getAttribute("idUser"); // pegando o idUser, que foi previamente armazenado no Filter
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        // o metodo esta consultando o repositorio para buscar todas as tarefas relacionadas ao usuario cujo id foi passado



        return tasks;

    }
    @PutMapping("/{id}")
    public void update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){


    }


}
