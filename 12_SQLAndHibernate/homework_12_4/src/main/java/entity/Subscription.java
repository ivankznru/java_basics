package entity;

import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @EmbeddedId
    private Key id;

    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    public void setId(Key id) {
        this.id = id;
    }
    public Key getId() {
        return id;
    }

    @Data
    @Embeddable
    public static class Key implements Serializable {

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;

        public Key(Student student, Course course) {
            this.student = student;
            this.course = course;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Key key = (Key) o;

            if (!student.equals(key.student)) return false;
            return course.equals(key.course);
        }

        @Override
        public int hashCode() {
            int result = student.hashCode();
            result = 31 * result + course.hashCode();
            return result;
        }

    }
}



