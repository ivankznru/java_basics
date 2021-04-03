
import java.util.ArrayList;
import java.util.List;
import entity.Course;
import entity.LinkedPurchaseList;
import entity.PurchaseList;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class Main {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        String hqlSelectId = "FROM " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hqlSelectId).getResultList();
        List<Integer> idConnect = new ArrayList<>();

        for (PurchaseList p : purchaseList) {

            Query criteria = session.createQuery("FROM " + Student.class.getName() + " WHERE name = :nameStudent ");
            List<Student> student = criteria.setParameter("nameStudent", p.getNameCourseKey().getStudentName()).list();
            idConnect.add(student.get(0).getId());
            criteria = session.createQuery("FROM " + Course.class.getName() + " WHERE name = :nameCourse ");
            List<Course> course = criteria.setParameter("nameCourse", p.getNameCourseKey().getCourseName()).list();
            idConnect.add(course.get(0).getId());

            int studentIdInfo = 0;
            int courseIdInfo = 1;
            if (idConnect.size() == 2) {
                Transaction transaction = session.beginTransaction();
                LinkedPurchaseList linkedPurchase = new LinkedPurchaseList();
                linkedPurchase.setLinkedPurchaseId(idConnect.get(studentIdInfo), idConnect.get(courseIdInfo));
                session.saveOrUpdate(linkedPurchase);
                session.flush();
                transaction.commit();
                idConnect.clear();
            }
        }
        HibernateUtil.getSessionFactory().close();


    }
}
