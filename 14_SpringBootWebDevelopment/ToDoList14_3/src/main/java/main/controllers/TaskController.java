package main.controllers;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/todolist/")
    public List<Task> list() {
        Iterable<Task> taskList = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task:taskList) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping("/todolist/")
    public int add(Task task) {
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/todolist/{id}")
    public ResponseEntity<Object> get(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todolist/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        taskRepository.deleteById(id);
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @PutMapping("/todolist/{id}")
    public ResponseEntity<Object> put(@PathVariable int id, Task taskNew) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.save(taskNew);
        return new ResponseEntity<>(taskNew, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/todolist/{id}")
    public ResponseEntity<Object> patch(@PathVariable int id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        task.get().setDone(!task.get().getDone());
        taskRepository.save(task.get());
        return new ResponseEntity<>(task.get().getDone(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/todolist/search")
    public List<Task> searchText (@PathParam("query") String query) {
        List<Task> taskList = new ArrayList<>();
        if (query == null) {
            taskRepository.findAll().forEach(taskList::add);
        } else {
           taskList = taskRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(query, query);
        }

        return taskList;
    }

    @GetMapping("/todolist/done")
    public List<Task> searchDoTask () {
        return taskRepository.findByDoneTrue();
    }

    @GetMapping("/todolist/do")
    public List<Task> searchDoneTask () {
       return taskRepository.findByDoneFalse();
    }
}
