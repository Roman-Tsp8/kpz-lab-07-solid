package variant8.task5_dip;

public final class UniversityEmailGateway implements NotificationGateway {
    @Override
    public void sendOfficialMail(String email, String subject, String body) {
        System.out.println("[University Mail Gateway] Лист на " + email + " | Тема: '" + subject + "'.");
    }
}
