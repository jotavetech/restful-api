package com.jotave.todolist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;


@Entity // Indica que a classe User é uma entidade do banco de dados
@Table(name = User.TABLE_NAME) // Indica que a tabela do banco de dados que representa a classe User se chama "user"
public class User {
    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "user"; // Essa constante é usada para referenciar o nome da tabela do banco de dados em outras partes do código

    @Id // Indica que o atributo id é a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que o valor do atributo id é gerado automaticamente pelo banco de dados
    @Column(name = "id", unique = true) // Indica que o atributo id se refere à coluna "id" da tabela do banco de dados
    private Long id;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class) // Faz a validação no código e não no banco de dados
    @Size(groups = CreateUser.class, min = 3, max = 30)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @Size(groups = {CreateUser.class, UpdateUser.class}, min = 5, max = 60) // Groups = {CreateUser.class, UpdateUser.class} indica que a validação será feita para os grupos CreateUser e UpdateUser
    private String password;

    //    private List<Task> tasks = new ArrayList<Task>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (this.id == null)
            if (other.id != null)
                return false;
            else return false;
        return Objects.equals(this.id, other.id) && Objects.equals(this.username, other.username)
                && Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}
