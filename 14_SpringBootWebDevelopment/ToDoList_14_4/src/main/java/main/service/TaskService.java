package main.service;

import main.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import main.model.Task;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private  TaskRepository taskRepository;


    public List<Task> list() {
        Iterable<Task> taskList = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task:taskList) {
            tasks.add(task);
        }
        return tasks;
    }

    public int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    public ResponseEntity<Object> get(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> delete (@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    public ResponseEntity<Object> put(@PathVariable int id, Task taskNew) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.save(taskNew);
        return new ResponseEntity<>(taskNew, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<Object> patch (@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        task.get().setDone(!task.get().getDone());
        taskRepository.save(task.get());
        return new ResponseEntity<>(task.get().getDone(), HttpStatus.NO_CONTENT);
    }

    public List<Task> searchText (@PathParam("query") String query) {
        List<Task> taskList = new ArrayList<>();
        if (query == null) {
            taskRepository.findAll().forEach(taskList::add);
        } else {
            taskList = taskRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(query,query);
        }
        return taskList;
    }

    public List<Task> searchDoneTask () {
        return taskRepository.findByDoneFalse();
    }

    public List<Task> searchDoTask () {
        return taskRepository.findByDoneTrue();
    }


}
