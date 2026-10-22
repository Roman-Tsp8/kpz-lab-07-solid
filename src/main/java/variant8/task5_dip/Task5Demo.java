package variant8.task5_dip;

public final class Task5Demo {
    public static RegistrationRequest sampleRequest() {
        RegistrationRequest r = new RegistrationRequest();
        r.setStudentId("S-2025-042");
        r.setStudentEmail("student@univ.edu.ua");
        r.setCourseCode("CS-301");
        return r;
    }

    public static void run() {
        System.out.println("--- Production ---");
        CompositionRoot.production().registerStudent(sampleRequest());

        System.out.println("--- Mock ---");
        MockCourseRepository repo = new MockCourseRepository();
        MockRegistrationSlipGenerator slips = new MockRegistrationSlipGenerator();
        MockNotificationGateway mail = new MockNotificationGateway();
        CompositionRoot.withMocks(repo, slips, mail).registerStudent(sampleRequest());
        System.out.println("bookings=" + repo.bookings + ", slips=" + slips.slips + ", mail=" + mail.sentTo);
    }
}
