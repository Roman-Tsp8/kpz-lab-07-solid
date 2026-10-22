package variant8.task5_dip;

import java.util.ArrayList;
import java.util.List;

public final class MockNotificationGateway implements NotificationGateway {
    public final List<String> sentTo = new ArrayList<>();

    @Override
    public void sendOfficialMail(String email, String subject, String body) {
        sentTo.add(email);
    }
}
