package br.com.ProjetoReal.TudoList.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InterTaskRepository extends JpaRepository<TaskModel,UUID> {
}
