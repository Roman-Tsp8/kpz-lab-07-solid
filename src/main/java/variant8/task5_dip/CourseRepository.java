package variant8.task5_dip;

/** Абстракція сховища курсів (належить високорівневому модулю). */
public interface CourseRepository {
    void bookCourseSeat(String courseCode, String studentId);
}
