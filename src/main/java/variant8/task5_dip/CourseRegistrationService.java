package variant8.task5_dip;

import java.util.Objects;

/** Високорівнева логіка залежить лише від абстракцій; залежності отримує через конструктор. */
public final class CourseRegistrationService {
    private final CourseRepository courseRepository;
    private final RegistrationSlipGenerator slipGenerator;
    private final NotificationGateway notificationGateway;

    public CourseRegistrationService(CourseRepository courseRepository,
                                     RegistrationSlipGenerator slipGenerator,
                                     NotificationGateway notificationGateway) {
        this.courseRepository = Objects.requireNonNull(courseRepository, "courseRepository");
        this.slipGenerator = Objects.requireNonNull(slipGenerator, "slipGenerator");
        this.notificationGateway = Objects.requireNonNull(notificationGateway, "notificationGateway");
    }

    public void registerStudent(RegistrationRequest request) {
        if (request == null || isNullOrEmpty(request.getStudentId()) || isNullOrEmpty(request.getCourseCode())) {
            throw new IllegalArgumentException("Некоректна заявка на реєстрацію.");
        }

        courseRepository.bookCourseSeat(request.getCourseCode(), request.getStudentId());
        slipGenerator.generateRegistrationSlip(request.getStudentId(), request.getCourseCode());
        notificationGateway.sendOfficialMail(request.getStudentEmail(), "Підтвердження реєстрації на курс",
                "Вас успішно зараховано на курс " + request.getCourseCode() + ".");
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
