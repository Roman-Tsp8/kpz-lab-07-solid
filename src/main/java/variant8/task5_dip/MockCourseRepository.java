package variant8.task5_dip;

import java.util.ArrayList;
import java.util.List;

/** Тестова підміна: запам'ятовує виклики замість звернення до БД. */
public final class MockCourseRepository implements CourseRepository {
    public final List<String> bookings = new ArrayList<>();

    @Override
    public void bookCourseSeat(String courseCode, String studentId) {
        bookings.add(studentId + "@" + courseCode);
    }
}
