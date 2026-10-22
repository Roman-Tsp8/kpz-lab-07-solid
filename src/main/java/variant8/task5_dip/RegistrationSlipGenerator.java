package variant8.task5_dip;

/** Абстракція генератора квитанції про зарахування. */
public interface RegistrationSlipGenerator {
    void generateRegistrationSlip(String studentId, String courseCode);
}
