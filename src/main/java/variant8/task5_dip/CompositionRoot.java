package variant8.task5_dip;

/** Єдине місце, де створюються конкретні класи (ручний Manual DI, без Service Locator). */
public final class CompositionRoot {
    private CompositionRoot() { }

    public static CourseRegistrationService production() {
        return new CourseRegistrationService(new MariadbCourseRepository(), new PdfInvoiceGenerator(),
                new UniversityEmailGateway());
    }

    public static CourseRegistrationService withMocks(MockCourseRepository repo,
                                                      MockRegistrationSlipGenerator slips,
                                                      MockNotificationGateway mail) {
        return new CourseRegistrationService(repo, slips, mail);
    }
}
