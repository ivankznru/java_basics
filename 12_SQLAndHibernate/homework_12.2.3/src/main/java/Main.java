
import java.util.List;
import entity.Course;
import org.hibernate.Session;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Main {


    public static void main(String[] args)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> query = builder.createQuery(Course.class);
        Root<Course> root = query.from(Course.class);
        query.select(root);
        List <Course> courseList = session.createQuery(query).getResultList();
        for (Course course : courseList){
   System.out.println("Название курса : " + course.getName() + ". Количество студентов на курсе " + course.getStudents().size());
            System.out.println(" Имена студентов курса :" + course.getName());
            course.getStudents().forEach(s -> System.out.println(s.getName()));
        }
        HibernateUtil.getSessionFactory().close();


    }

    }





