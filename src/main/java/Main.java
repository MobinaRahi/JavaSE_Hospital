import hospital.model.entity.*;
import hospital.model.service.*;
import testServices.*;


public class Main {
    public static void main(String[] args) throws Exception {

        UsersTestService.saveUsers();
        UsersTestService.login("A", "B");

        DoctorTestService.saveDoctors();
        DoctorService.getService().findAll().forEach(System.out::println);
        System.out.println(DoctorService.getService().findByNameAndFamily("A", "B"));
        System.out.println(DoctorService.getService().findBySpecialty("dermatologist"));


        EmployeeTestService.saveEmployees();

        MedicalTestService.saveMedicals();

        PatientTestService.savePatients();

        TimeShiftTestService.saveTimeShifts();

        PrescriptionTestService.savePrescriptions();

        VisitsTestService.saveVisits();


        for (DoctorShift bookedTimeShift : DoctorService.getService().findPaidTimeShifts()) {
            System.out.println(bookedTimeShift.getAppointmentStart()+" -> "+bookedTimeShift.getAppointmentEnd());
        }


        System.out.println(DoctorService.getService().showPatientInformation(3));


//       PrescriptionService.getService().prescriptionsDrugs(drugList,1);

        System.out.println(PrescriptionService.getService().findById(26).getVisit());
        PrescriptionService.getService().findById(26).getDrugList().forEach(System.out::println);
        System.out.println(PrescriptionService.getService().findById(26).getPrice());

//         show prescription

        for (Drug drug : PrescriptionService.getService().showPrescription(1)) {
            System.out.println(drug.getName());
        }


//        employee
        DoctorService.getService().findAll().forEach(System.out::println);
        for (DoctorShift bookedTimeShift : DoctorService.getService().findPaidTimeShifts()) {
            System.out.println(DoctorService.getService().findById(bookedTimeShift.getDoctorId()).getUser().getName()+":  BOOKED " +bookedTimeShift.getAppointmentStart()+" -> "+bookedTimeShift.getAppointmentEnd());
        }
        for (DoctorShift findAvailableTimeShifts : DoctorService.getService().findPaidTimeShifts()) {
            System.out.println(DoctorService.getService().findById(findAvailableTimeShifts.getDoctorId()).getUser().getName()+":  Available " +findAvailableTimeShifts.getAppointmentStart()+" -> "+findAvailableTimeShifts.getAppointmentEnd());
        }
        for (DoctorShift findPaidTimeShifts : DoctorService.getService().findPaidTimeShifts()) {
            System.out.println(DoctorService.getService().findById(findPaidTimeShifts.getDoctorId()).getUser().getName()+":  Paid " +findPaidTimeShifts.getAppointmentStart()+" -> "+findPaidTimeShifts.getAppointmentEnd());
        }


    }
}
