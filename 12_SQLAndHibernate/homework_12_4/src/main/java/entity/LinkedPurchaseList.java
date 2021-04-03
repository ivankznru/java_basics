package entity;
import lombok.*;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "linkedPurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseId linkedPurchaseId;

   public void setLinkedPurchaseId(int student_id, int course_id) {
        linkedPurchaseId = new LinkedPurchaseId(student_id, course_id);
    }
    @Data
    @Embeddable
    public static class LinkedPurchaseId implements Serializable {

        private int studentId;
        private int courseId;

       public LinkedPurchaseId(int st, int c) {
            setStudentId(st);
           setCourseId(c);
       }

        public LinkedPurchaseId() {

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof LinkedPurchaseId)) return false;
            LinkedPurchaseId that = (LinkedPurchaseId) o;
            return studentId == that.studentId &&
                    courseId == that.courseId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseId);
        }
    }
}
