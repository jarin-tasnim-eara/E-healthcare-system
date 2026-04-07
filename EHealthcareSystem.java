/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ehealthcaresystem;
import java.util.Scanner;
// Abstract class for medical personnel
abstract class MediPro{
      protected String name;
 public MediPro(String name){
        this.name=name;
    }
 public abstract void performDuties(); // Abstract method to be implemented by subclasses
    // Getters and setters(Encapsulation)
 public String getName(){
     return name;
 }
}
// Doctor class inheriting from MedicalPersonnel
class Doctor extends MediPro{
    private String sp;
    public Doctor(String name, String sp){
        super(name);
        this.sp=sp;
    }
public String getSpecialization(String sp){
   return sp; 
}
   //override
    public void performDuties(){
        System.out.println(name + " will examine the patient.");
    }
    // Getter for specialization
    public String getSpecialization(){ 
        return sp;
    }
}
// Eye Doctor class inheriting from Doctor
class EyeDoctor extends Doctor{
    public EyeDoctor(String name, String sp){
        super(name, sp);
    }
}
// Skin Doctor class inheriting from Doctor
class SkinDoctor extends Doctor{
    public SkinDoctor(String name, String sp){
        super(name, sp);
    }
}
// Patient class
class Patient{
    private String name;
    private int age;
    private String gender;
    private String checkType; // To specify the type of check
    public Patient( String name, int age, String gender, String checkType) {
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.checkType=checkType;
    }
    // Getters
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public String getCheckType(){
        return checkType; 
    }
}
// Appointment class
class Appointment{
    private String dateTime;
    private Patient patient;
    private MediPro doctor;
    private String reason;
 
public Appointment( String dateTime, Patient patient, MediPro doctor, String reason){
        this.dateTime=dateTime;
        this.patient=patient;
        this.doctor=doctor;
        this.reason=reason;
    }
    // Getters
    public String getDateTime(){
        return dateTime;
    }
    public Patient getPatient(){ 
        return patient; 
    }
    public MediPro getDoctor(){
        return doctor; 
    }
    public String getReason(){
        return reason;
    }
}
// Interface for confirming appointments
interface Confirm{
    void confirmAppointment();
}
// AppointmentConfirmation class implementing Confirmable interface
class AppointmentConfirmation implements Confirm{
   //override
    public void confirmAppointment(){
        System.out.println("Appointment confirmed.");
    }
}
public class EHealthcareSystem{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        // Input for patient
        System.out.println("Enter patient details:");
        System.out.print("Enter  name of the patient: ");
        String patientName=scanner.nextLine();
        System.out.print("Enter  age of the patient: ");
        int patientAge=scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter  gender of the patient: ");
        String patientGender=scanner.nextLine();
        System.out.print("Enter check type (eye/skin): ");
        String checkType=scanner.nextLine();
        // Creating patient1 object
        Patient p1 = new Patient(patientName,patientAge,patientGender,checkType);
        // Creating different doctors
        EyeDoctor eyeDoctor=new EyeDoctor("Dr.Rayhan kabir","Eye doctor");
        SkinDoctor skinDoctor=new SkinDoctor("Dr.Mahmuda Hasan","Skin Doctor");
        // Displaying doctor information and confirming appointment based on patient's choice
        if(p1.getCheckType().equalsIgnoreCase("eye")) {
            displayDoctorInfo(eyeDoctor);
            confirmAppointment(p1, eyeDoctor);
        }else if(p1.getCheckType().equalsIgnoreCase("skin")){
            displayDoctorInfo(skinDoctor);
            confirmAppointment(p1, skinDoctor);
        }else{
            System.out.println("No appointment scheduled because you can only take appointmrnt of eye and skin doctor.");
        }
    }
    // Display doctor's information
    public static void displayDoctorInfo(Doctor d){
        System.out.println("\n Details of doctor:");
        System.out.println("Doctor: " + d.getName() + " (" + d.getSpecialization() + ")");
    }
    // Confirm the appointment based on patient's choice
    public static void confirmAppointment(Patient p1, Doctor d1){
        Appointment a1 = new Appointment("2024-1-5 10:00 PM",p1,d1,"Regular checkup");
        // Displaying appointment information
        System.out.println("\nAppointment Details:");
        System.out.println("Patient: " +a1.getPatient().getName());
        System.out.println("Doctor: " +a1.getDoctor().getName());
        System.out.println("Date & Time: " +a1.getDateTime());
        System.out.println("Reason: " +a1.getReason());
        // Confirming the appointment using abstraction
        Confirm confirmable=new AppointmentConfirmation();
        confirmable.confirmAppointment();
        // Performing doctor's duties
        a1.getDoctor().performDuties();
    }
}
