package br.com.ProjetoReal.TudoList.Task;

import br.com.ProjetoReal.TudoList.Utils.Utils;
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
    public ResponseEntity<?> create(@RequestBody TaskModel taskModel, HttpServletRequest request, HttpServletResponse response) {
        // o <> faz parte do recurso de "Generic", que permitem que classes e metodos trabalhem com diferentes tipos de dados
        //  o <?> significa que nao é especifico ou indefinido
        // nesse caso, estamos dizendo que ele pode envolver qualquer tipo de objeto como o corpo da resposta,
        // mas nao estamos especificando qual é
        // ex: ResponseEntity<String> : retornara uma String como resposta
        // ex: ResponseEntity<TaskModel> : retornara um TaskModel como resposta

        //ResponseEntity é uma classe do Spring Framework que representa toda a resposta HTTP
        //  O código de status HTTP da resposta (por exemplo, 200 OK, 404 Not Found, 400 Bad Request, etc.).

        Object idUser = request.getAttribute("idUser");
        System.out.println("ele chegou aqui manior01" );
        //Esse método está sendo usado para recuperar o valor
        // que foi previamente armazenado no objeto HttpServletRequest usando o método setAttribute()
        // oq nesse caso seria o idUser
        // Em Java, a classe Object é a classe mais genérica de todas
        // Como request.getAttribute() pode retornar qualquer tipo de dado (dependendo de como ele foi armazenado),
        // o tipo retornado é automaticamente tratado como Object. Isso oferece flexibilidade,
        // pois você pode armazenar e recuperar atributos de diferentes tipos

        taskModel.setIdUser((UUID) idUser);
        // quando colocado o setter, foi necessario converter para UUID


        if (taskModel.getDataInicio() == null || taskModel.getDataTermino() == null) {

             return ResponseEntity.status(404).body("As datas de início e término não podem ser nulas");
        }
        // Verifica se a data de início é anterior ao momento atual
        if (taskModel.getDataInicio().isBefore(LocalDateTime.now()) || taskModel.getDataTermino().isBefore(LocalDateTime.now())) {

            return ResponseEntity.status(400).body("A data de início/término deve ser maior que a data atual");
        }

        // Verifica se a data de término é anterior à data de início
        if (taskModel.getDataTermino().isBefore(taskModel.getDataInicio())) {
            //a diferença é que o response.sendError mostra apenas o erro ao usuario, e esse mostra o erro e a mensagem
            // ele mostra a mensagem ao cliente

            return ResponseEntity.status(400).body("A data de término deve ser maior do que a data de inicio");
        }

        // tive que mudar um pouco o codigo, coloquei o ReponseEntity<?> para que o responseEntity funcione de forma correta
        // pois como anteriormente eu retornava null, ele acabava dando uma mensagem 200 (OK) e nao era oq eu queria



        TaskModel taskModel1 = this.taskRepository.save(taskModel);

        return ResponseEntity.ok(taskModel1);
    }

    @GetMapping("/")
    public List<TaskModel> lista(HttpServletRequest request) {
        Object idUser = request.getAttribute("idUser"); // pegando o idUser, que foi previamente armazenado no Filter
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        // o metodo esta consultando o repositorio para buscar todas as tarefas relacionadas ao usuario cujo id foi passado
       // this.taskRepository.findAll();
        // findAll : retornar uma lista contendo todas as instancias da entidade associada ao repositorio


        return tasks;

    }
    @PutMapping("/{id}") //são normalmente usadas para atualizar recursos existentes em uma aplicação
    public TaskModel update(@RequestBody TaskModel taskModel, @PathVariable UUID id,HttpServletRequest request){
    // esse metodo define que vai atualizar uma tarefa do TaskModel
        // o @RequestBody é usado para indicar que os dados do corpo da requisição HTTP serão mapeados para o objeto taskModel
        // o @PathVariable é usado para indicar que o valor do parâmetro id será extraído da URL da requisição

       var tasks =  this.taskRepository.findById(id).orElse(null);
       // pegando a task existente para poder mesclar as informações atraves do ID fornecido
        // e caso nao tenha, retornar null



        Utils.copyNonNullProperty(taskModel,tasks); // mesclando todas propriedades do TaskModel que nao sao null na entidade tasks existente.


        //Object idUser = request.getAttribute("idUser");       nao precisamos mais pois ja estamos pegando as informaçoes no Utils
       // taskModel.setIdUser((UUID) idUser);
       // taskModel.setId(id); // ele garante que a tarefa que iremos atualizar seja com o id especificado na url
       return this.taskRepository.save(tasks);


    }
    //Quando o cliente faz a requisição PUT, ele envia um corpo (body) com os novos dados da tarefa que deseja atualizar,
    // e o Spring automaticamente transforma esses dados no objeto taskModel que será utilizado dentro do método.


}
