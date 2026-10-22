package variant8.task5_dip;

import java.util.ArrayList;
import java.util.List;

public final class MockRegistrationSlipGenerator implements RegistrationSlipGenerator {
    public final List<String> slips = new ArrayList<>();

    @Override
    public void generateRegistrationSlip(String studentId, String courseCode) {
        slips.add(studentId + "@" + courseCode);
    }
}
