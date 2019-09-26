package todo;

class TaskNotFoundException extends RuntimeException {
    TaskNotFoundException(Long id){
        super("Could not find the task with id: " + id);
    }
}
