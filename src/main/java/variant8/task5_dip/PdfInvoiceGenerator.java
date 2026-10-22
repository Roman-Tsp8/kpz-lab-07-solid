package variant8.task5_dip;

public final class PdfInvoiceGenerator implements RegistrationSlipGenerator {
    @Override
    public void generateRegistrationSlip(String studentId, String courseCode) {
        System.out.println("[PDF Engine] Згенеровано PDF-повідомлення 'registration_" + studentId + "_"
                + courseCode + ".pdf'.");
    }
}
