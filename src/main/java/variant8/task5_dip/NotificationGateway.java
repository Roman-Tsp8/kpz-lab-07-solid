package variant8.task5_dip;

/** Абстракція каналу сповіщень. */
public interface NotificationGateway {
    void sendOfficialMail(String email, String subject, String body);
}
