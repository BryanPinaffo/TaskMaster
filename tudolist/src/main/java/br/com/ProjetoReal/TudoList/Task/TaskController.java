package br.com.ProjetoReal.TudoList.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/tasks")
// Controller para manipular tarefas (CRUD)
public class TaskController {
    @Autowired
    private InterTaskRepository taskRepository;
   @PostMapping("/")
    public TaskModel create(@RequestBody TaskModel taskModel){

       System.out.println("chegou no controller");

    TaskModel taskModel1 = this.taskRepository.save(taskModel);

    return taskModel1;
    }
}
