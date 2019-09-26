package todo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
public class Task {

    private @Id @GeneratedValue Long id;
    private String username;
    private String title;
    private String description;

    Task(){}

    Task(String username, String title, String description){
        this.username = username;
        this.title = title;
        this.description = description;
    }
}
