package vu.psk.ugems.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Profile> profiles;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
