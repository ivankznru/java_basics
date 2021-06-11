package main.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository <Task, Integer> {

    List<Task> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name, String description);

    List<Task> findByDoneTrue();

    List<Task> findByDoneFalse();
}
