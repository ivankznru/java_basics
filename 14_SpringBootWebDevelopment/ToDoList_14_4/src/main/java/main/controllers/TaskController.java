package main.controllers;

import main.model.Task;
import main.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

@GetMapping("/todolist")
public List<Task> list() {return taskService.list();}

@PostMapping("/todolist")
public int add(Task task){return taskService.add(task);}

@GetMapping("/todolist/{id}")
public ResponseEntity<Object> get(@PathVariable int id){return taskService.get(id);}

@DeleteMapping("/todolist/{id}")
public ResponseEntity<Object> delete(@PathVariable int id){return taskService.delete(id);}

@PutMapping("/todolist/{id}")
public ResponseEntity<Object> put(@PathVariable int id, Task taskNew) {return taskService.put(id,taskNew);}

@PatchMapping("/todolist/{id}")
public ResponseEntity<Object> patch(@PathVariable int id){return taskService.patch(id);}

@GetMapping("/todolist/search")
public List<Task> searchText (@PathParam("query") String query){return taskService.searchText(query);}

@GetMapping("/todolist/done")
public List<Task> searchDoTask () {
        return taskService.searchDoTask();
    }

@GetMapping("/todolist/do")
public List<Task> searchDoneTask () { return taskService.searchDoneTask(); }}
