package entity;

import entity.Course;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id.student")
    private List<Subscription> subscriptions;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")}
    )
    private List<Course> courses;
}

//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id.student")
//    private List<Subscription> subscriptions;
//
//    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY)
//    private List<Course> courses;