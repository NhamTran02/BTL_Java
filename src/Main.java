import modal.Student;

import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        Student student = new Student();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                student.storeStudentOut();
                student.storeStudentsOut();
            }
        });
        while (true){
            System.out.println("1. Mảng tĩnh");
            System.out.println("2. Mảng động");
            System.out.println("3. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            switch(choice) {
                case 1:
                    main.menuStatic();
                    break;
                case 2:
                    main.menuDynamic();
                    break;
                case 3:
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public void menuStatic(){
        Student student=new Student();
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("\n Làm việc với mảng tĩnh");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiện danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo ID");
            System.out.println("4. Cập nhật sinh viên theo ID");
            System.out.println("5. Xóa sinh viên theo ID");
            System.out.println("6. Tỉ lệ phần trăm học lực của các sinh viên");
            System.out.println("7. Tỉ lệ phần trăm trung bình của các sinh viên");
            System.out.println("8. Hiện danh sách các sinh viên được nhập vào tùy theo học lực");
            System.out.println("9. Lưu trữ danh sách sinh viên vào file");
            System.out.println("10. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    student.addStudent();
                    break;
                case 2:
                    student.printListStudent();
                    break;
                case 3:
                    System.out.print("Nhập ID sinh viên cần tìm: ");
                    String searchId = sc.nextLine();
                    Student.displaySearchResult(Integer.parseInt(searchId));
                    break;
                case 4:
                    System.out.println("nhập mã sinh viên muốn cập nhật: ");
                    String idToUpdate = sc.nextLine();
                    Student.updateStudentById(Integer.parseInt(idToUpdate));
                    break;
                case 5:
                    System.out.println("nhập mã sinh viên muốn xóa: ");
                    String idToDelete = sc.nextLine();
                    Student.deleteStudentById(Integer.parseInt(idToDelete));
                    break;
                case 6:
                    student.displayAcademicPerformancePercentagesstatic();
                    break;
                case 7:
                    Student.displayGpaPercentagesstatic();
                    break;
                case 8:
                    student.displayStudentsByAcademicPerformanceStatic();
                    break;
                case 9:
                    student.storeStudent();
                    break;
                case 10:
                    student.storeStudentOut();
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    public void menuDynamic(){
        Student student=new Student();
        Scanner sc=new Scanner(System.in);
        while(true) {
            System.out.println("\n Làm việc với mảng động");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Hiện danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo ID");
            System.out.println("4. Cập nhật sinh viên theo ID");
            System.out.println("5. Xóa sinh viên theo ID");
            System.out.println("6. Tỉ lệ phần trăm học lực của các sinh viên");
            System.out.println("7. Tỉ lệ phần trăm trung bình của các sinh viên");
            System.out.println("8. Hiện danh sách các sinh viên được nhập vào tùy theo học lực");
            System.out.println("9. Lưu trữ danh sách sinh viên vào file");
            System.out.println("10. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    student.addStudents();
                    break;
                case 2:
                    student.printListStudents();
                    break;
                case 3:
                    System.out.print("Nhập ID sinh viên cần tìm: ");
                    String searchId = sc.nextLine();
                    Student.searchStudents(Integer.parseInt(searchId));
                    break;
                case 4:
                    System.out.println("nhập mã sinh viên muốn cập nhật: ");
                    String idToUpdate = sc.nextLine();
                    Student.updateStudentByIds(Integer.parseInt(idToUpdate));
                    break;
                case 5:
                    System.out.println("nhập mã sinh viên muốn xóa: ");
                    String idToDelete = sc.nextLine();
                    Student.deleteStudentByIds(Integer.parseInt(idToDelete));
                    break;
                case 6:
                    student.displayAcademicPerformancePercentages();
                    break;
                case 7:
                    student.displayGpaPercentages();
                    break;
                case 8:
                    student.displayStudentsByAcademicPerformance();
                    break;
                case 9:
                    student.storeStudents();
                    break;
                case 10:
                    student.storeStudentsOut();
                    System.out.println("Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
}