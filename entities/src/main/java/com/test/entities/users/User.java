package com.test.entities.users;

import com.test.entities.poll.Poll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")
public class User  {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
    @Column(name = "finished_poll")
    private List<Poll> finishedPoll;

    public User(Long userId) {
        id = userId;
    }
}
