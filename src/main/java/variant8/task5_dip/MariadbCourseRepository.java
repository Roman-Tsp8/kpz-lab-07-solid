package variant8.task5_dip;

public final class MariadbCourseRepository implements CourseRepository {
    @Override
    public void bookCourseSeat(String courseCode, String studentId) {
        System.out.println("[MariaDB Storage] Студента " + studentId + " зараховано на курс '" + courseCode
                + "'. Кількість місць зменшено.");
    }
}
