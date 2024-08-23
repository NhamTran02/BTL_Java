package modal;

import validator.Const;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Student extends Person {
    private String studentId;
    private String schoolName;
    private int studentYear;
    private double gpa;
    public AcademicPerformance academicPerformance;

    //mảng
    private static final Student[] listStudent = new Student[100];
    private static int studentCount = 0;
    private static final List<Student> listStudents = new ArrayList<>();
    private static int dynamicIdCounter = 1;

    //enum
    public enum AcademicPerformance  {
        KEM,YEU,TRUNG_BINH,KHA,GIOI,XUAT_SAC;
    }
    //constructor
    public Student() {
    }

    public Student(int id,String name, Date dateOfBirth, String address, double height, double weight, String studentId, String schoolName, int studentYear, double gpa, AcademicPerformance academicPerformance) {
        super(id,name, dateOfBirth, address, height, weight);
        this.studentId = studentId;
        this.schoolName = schoolName;
        this.studentYear = studentYear;
        this.gpa = gpa;
        this.academicPerformance = calculateAcademicPerformance(gpa);
    }

    //getters and setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getStudentYear() {
        return studentYear;
    }

    public void setStudentYear(int studentYear) {
        this.studentYear = studentYear;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public String getAcademicPerformance() {
        switch (academicPerformance) {
            case KEM:
                return "Kém";
            case YEU:
                return "Yếu";
            case TRUNG_BINH:
                return "Trung bình";
            case KHA:
                return "Khá";
            case GIOI:
                return "Giỏi";
            case XUAT_SAC:
                return "Xuất sắc";
            default:
                return "";
        }
    }
    public void setAcademicPerformance() {
        this.academicPerformance = calculateAcademicPerformance(gpa);
    }

    //Tính học lực
    private AcademicPerformance calculateAcademicPerformance(double score) {
        if(score <3){
            return AcademicPerformance.KEM;
        }
        else if(score <5){
            return AcademicPerformance.YEU;
        }
        else if(score <6.5){
            return AcademicPerformance.TRUNG_BINH;
        }
        else if(score <7.5){
            return AcademicPerformance.KHA;
        }
        else if(score <9){
            return AcademicPerformance.GIOI;
        }
        else {
            return AcademicPerformance.XUAT_SAC;
        }
    }

    //toString
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateOfBirthFormatted = dateFormat.format(super.getDateOfBirth());

        return "Student{" +
                "id=" + super.getId() +
                ", tên='" + super.getName() + '\'' +
                ", ngày sinh=" + dateOfBirthFormatted+
                ", địa chỉ='" + super.getAddress() + '\'' +
                ", chiều cao=" + super.getHeight() +
                ", cân nặng=" + super.getWeight() +
                " mã sinh viên='" + studentId + '\'' +
                ", tên trường='" + schoolName + '\'' +
                ", năm học=" + studentYear +
                ", điểm trung bình=" + gpa +
                ", học lực= "+ getAcademicPerformance() +
                '}';
    }


    public static void printListStudent() {
        for (int i = 0; i < studentCount; i++) {
            if(listStudent[i]!=null){
                System.out.println(listStudent[i].toString());
            }
        }
    }

    public static void addStudent() {
        if (studentCount >= listStudent.length) {
            System.out.println("Mảng đã đầy, không thể thêm sinh viên mới.");
            return;
        }
        Student student = new Student();
        student.inputStudent();
        student.setId(studentCount+1);
        listStudent[studentCount++] = student;
        System.out.println("Thêm sinh viên thành công.");
        printListStudent();
    }

    public void inputStudent() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.println("Nhập tên sinh viên: ");
            name = sc.nextLine();
            if (!Const.validateName(name)) {
                System.out.println("Tên không được trống và phải ít hơn " + Const.MAX_NAME_LENGTH + " ký tự.");
            }
        } while (!Const.validateName(name));
        setName(name);

        String dateOfBirthStr;
        do {
            System.out.println("Nhập ngày sinh (yyyy-MM-dd): ");
            dateOfBirthStr = sc.nextLine();
            if (!Const.validateBirth(dateOfBirthStr)) {
                System.out.println("Nhập sai định dạng ngày sinh");
            }
        } while (!Const.validateBirth(dateOfBirthStr));
        try {
            Date dateOfBirth = Const.DATE_FORMAT.parse(dateOfBirthStr);
            setDateOfBirth(dateOfBirth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        String address;
        do {
            System.out.println("Nhập địa chỉ: ");
            address = sc.nextLine();
            if (!Const.validateAddress(address)) {
                System.out.println("Địa chỉ phải ít hơn " + Const.MAX_ADDRESS_LENGTH + "  ký tự");
            }
        } while (!Const.validateAddress(address));
        setAddress(address);

        double height;
        do {
            System.out.println("Nhập chiều cao (cm): ");
            height = sc.nextDouble();
            if (!Const.validateHeight(height)) {
                System.out.println("Chiều cao phải nằm trong khoảng " + Const.MIN_HEIGHT + " đến " + Const.MAX_HEIGHT + " cm.");
            }
        } while (!Const.validateHeight(height));
        setHeight(height);

        double weight;
        do {
            System.out.println("Nhập cân nặng (kg): ");
            weight = sc.nextDouble();
            if (!Const.validateWeight(weight)) {
                System.out.println("Cân nặng phải nằm trong khoảng " + Const.MIN_WEIGHT + " đến " + Const.MAX_WEIGHT + " kg.");
            }
        } while (!Const.validateWeight(weight));
        setWeight(weight);
        sc.nextLine();

        String studentId;
        boolean isValidStudentId;
        do {
            System.out.println("Nhập mã sinh viên: ");
            studentId = sc.nextLine();

            isValidStudentId = Const.validateStudentId(studentId);
            if (!isValidStudentId) {
                System.out.println("Mã sinh viên phải có độ dài " + Const.STUDENT_ID_MAX_LENGTH + " ký tự.");
            } else {
                for (int i = 0; i < studentCount; i++) {
                    if (listStudent[i] != null && listStudent[i].getStudentId().equals(studentId)) {
                        System.out.println("Mã sinh viên đã tồn tại vui lòng nhập mã sinh viên khác.");
                        isValidStudentId = false;
                        break;
                    }
                }
            }
        } while (!isValidStudentId);
        setStudentId(studentId);

        do {
            System.out.println("Nhập tên trường: ");
            schoolName = sc.nextLine();
            if (!Const.validateSchoolName(schoolName)) {
                System.out.println("Tên trường không được trống và phải ít hơn " + Const.SCHOOL_NAME_MAX_LENGTH + " ký tự.");
            }
        } while (!Const.validateSchoolName(schoolName));
        setSchoolName(schoolName);

        int studentYear;
        do {
            System.out.println("Nhập năm học: ");
            studentYear = sc.nextInt();
            if (!Const.validateSchoolYear(studentYear)) {
                System.out.println("Năm nhập học phải từ năm " + Const.MIN_SCHOOL_YEAR + " đến năm " + Const.MAX_SCHOOL_YEAR + ".");
            }
        } while (!Const.validateSchoolYear(studentYear));
        setStudentYear(studentYear);

        double gpa;
        do {
            System.out.println("Nhập điểm trung bình: ");
            gpa = sc.nextDouble();
            if (!Const.validateGPA(gpa)) {
                System.out.println("Điểm trung bình phải nằm trong khoảng " + Const.MIN_GPA + " đến " + Const.MAX_GPA + ".");
            }
        } while (!Const.validateGPA(gpa));
        setGpa(gpa);

        setAcademicPerformance();
    }

    public static Student searchId(int id) {
        for (int i = 0; i < studentCount; i++) {
            if (listStudent[i] != null && listStudent[i].getId() == id) {
                return listStudent[i];
            }
        }
        return null;
    }

    public static void displaySearchResult(int id) {
        Student foundStudent = searchId(id);
        if (foundStudent != null) {
            System.out.println("Tìm thấy sinh viên: ");
            System.out.println(foundStudent.toString());
        } else {
            System.out.println("Không tìm thấy sinh viên với id là: " + id);
        }
    }

    public static void updateStudentById(int id) {
        Student student = searchId(id);

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số tương ứng để cập nhật thông tin:");
        System.out.println("1. Tên");
        System.out.println("2. Ngày sinh");
        System.out.println("3. Địa chỉ");
        System.out.println("4. Chiều cao");
        System.out.println("5. Cân nặng");
        System.out.println("6. Mã sinh viên");
        System.out.println("7. Tên trường");
        System.out.println("8. Năm học");
        System.out.println("9. Điểm GPA");
        System.out.println("10. Hoàn thành cập nhật");

        while (true){
            System.out.print("Chọn thông tin cần cập nhật (1-10): ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    String newName;
                    do {
                        newName = sc.nextLine();
                        if (!Const.validateName(newName)) {
                            System.out.println("Tên không được trống và phải ít hơn " + Const.MAX_NAME_LENGTH + " ký tự.");
                            System.out.print("Vui lòng nhập lại tên: ");
                        }
                    }while (!Const.validateName(newName));
                    student.setName(newName);
                    break;
                case 2:
                    System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
                    String newDateOfBirth = sc.nextLine();
                    if (Const.validateBirth(newDateOfBirth)) {
                        try {
                            Date dateOfBirth = Const.DATE_FORMAT.parse(newDateOfBirth);
                            student.setDateOfBirth(dateOfBirth);
                        } catch (Exception e) {
                            System.out.println("Lỗi: Định dạng ngày không hợp lệ.");
                        }
                    } else {
                        System.out.println("Lỗi: Định dạng ngày không hợp lệ.");
                    }
                    break;
                case 3:
                    System.out.print("Nhập địa chỉ mới: ");
                    String newAddress;
                    do {
                        newAddress= sc.nextLine();
                        if (!Const.validateAddress(newAddress)) {
                            System.out.println("Địa chỉ phải ít hơn " + Const.MAX_ADDRESS_LENGTH + "  ký tự và kh đc trống" );
                            System.out.print("Nhập lại địa chỉ mới: ");
                        }
                    }while (!Const.validateAddress(newAddress));
                    student.setAddress(newAddress);
                    break;
                case 4:
                    System.out.print("Nhập chiều cao mới (cm): ");
                    double newHeight;
                    do {
                        newHeight = sc.nextDouble();
                        if (!Const.validateHeight(newHeight)) {
                            System.out.println("Chiều cao phải nằm trong khoảng " + Const.MIN_HEIGHT + " đến " + Const.MAX_HEIGHT + " cm.");
                            System.out.print("Nhập lại chiều cao mới: ");
                        }
                    }while (!Const.validateHeight(newHeight));
                    student.setHeight(newHeight);
                    break;
                case 5:
                    System.out.print("Nhập cân nặng mới (kg): ");
                    double newWeight;
                    do {
                        newWeight = sc.nextDouble();
                        if (!Const.validateWeight(newWeight)) {
                            System.out.println("Cân nặng phải nằm trong khoảng " + Const.MIN_WEIGHT + " đến " + Const.MAX_WEIGHT + " kg.");
                            System.out.print("Nhập lại cân nặng mới: ");
                        }
                    }while (!Const.validateWeight(newWeight));
                    student.setWeight(newWeight);
                    break;
                case 6:
                    System.out.print("Nhập mã sinh viên mới: ");
                    String newStudentId;
                    boolean isValid=false;
                    do {
                        newStudentId = sc.nextLine();
                        if(newStudentId.equals(student.getId())){
                            isValid=true;
                            break;
                        }
                        if (!Const.validateStudentId(newStudentId)) {
                            System.out.println("Mã sinh viên phải có độ dài " + Const.STUDENT_ID_MAX_LENGTH + " ký tự.");
                            System.out.print("Nhập lại mã sinh viên mới: ");
                        }
                        else {
                            for (int i=0;i<studentCount;i++){
                                if (newStudentId.equals(listStudent[i].getStudentId())){
                                    System.out.println("Mã sinh viên đã tồn tại vui lòng nhập mã sinh viên khác.");
                                    break;
                                }
                            }
                        }
                    }while (!Const.validateStudentId(newStudentId));
                    student.setStudentId(newStudentId);
                    break;
                case 7:
                    System.out.print("Nhập tên trường mới: ");
                    String newSchoolName;
                    do {
                        newSchoolName = sc.nextLine();
                        if (!Const.validateSchoolName(newSchoolName)) {
                            System.out.println("Tên trường không được trống và phải ít hơn " + Const.SCHOOL_NAME_MAX_LENGTH + " ký tự.");
                            System.out.println("Nhập lại tên trường khác: ");
                        }
                    }while (!Const.validateSchoolName(newSchoolName));
                    student.setSchoolName(newSchoolName);
                    break;
                case 8:
                    System.out.print("Nhập năm học mới: ");
                    int newStudentYear;
                    do {
                        newStudentYear = sc.nextInt();
                        if (!Const.validateSchoolYear(newStudentYear)) {
                            System.out.println("Năm nhập học phải từ năm " + Const.MIN_SCHOOL_YEAR + " đến năm " + Const.MAX_SCHOOL_YEAR + ".");
                            System.out.println("Nhập lại năm học mới: ");
                        }
                    }while (!Const.validateSchoolYear(newStudentYear));
                    student.setStudentYear(newStudentYear);
                    break;
                case 9:
                    System.out.print("Nhập điểm GPA mới: ");
                    double newGpa;
                    do {
                        newGpa = sc.nextDouble();
                        if (!Const.validateGPA(newGpa)) {
                            System.out.println("Điểm trung bình phải nằm trong khoảng " + Const.MIN_GPA + " đến " + Const.MAX_GPA + ".");
                            System.out.print("Nhập lại điểm GPA mới: ");
                        }
                    }while (!Const.validateGPA(newGpa));
                    student.setGpa(newGpa);
                    student.setAcademicPerformance();
                    break;
                case 10:
                    System.out.println("Hoàn thành cập nhật.");
                    System.out.println("Thông tin sinh viên sau khi cập nhật:");
                    System.out.println(student.toString());
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");

            }
        }
    }

    public static void deleteStudentById(int id){
        int indexToRemove=-1;
        for(int i=0;i<studentCount;i++){
            if(listStudent[i] != null && listStudent[i].getId()==id){
                indexToRemove=i;
                break;
            }
        }
        if(indexToRemove==-1){
            System.out.println("Không tìm thấy sinh viên cần xóa ");
            return;
        }

        for(int i = indexToRemove ; i<studentCount - 1;i++){
            listStudent[i]=listStudent[i+1];
        }

        listStudent[studentCount - 1]=null;
        studentCount--;

        System.out.println("Xóa thành công sinh viên có id là "+ id);
        printListStudent();
    }

    public void displayAcademicPerformancePercentagesstatic() {
        Map<AcademicPerformance, Integer> performanceCount = countAcademicPerformancestatic();
        int totalStudents = studentCount;
        Map<AcademicPerformance, Double> performancePercentages = new EnumMap<>(AcademicPerformance.class);

        for (Map.Entry<AcademicPerformance, Integer> entry : performanceCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalStudents;
            performancePercentages.put(entry.getKey(), percentage);
        }

        List<Map.Entry<AcademicPerformance, Double>> sortedPerformancePercentages = performancePercentages.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());

        System.out.println("Tỷ lệ phần trăm học lực của các sinh viên:");
        for (Map.Entry<AcademicPerformance, Double> entry : sortedPerformancePercentages) {
            System.out.printf("%s: %.2f%%\n",entry.getKey(), entry.getValue());
        }
    }

    private Map<AcademicPerformance, Integer> countAcademicPerformancestatic() {
        Map<AcademicPerformance, Integer> performanceCount = new EnumMap<>(AcademicPerformance.class);
        for (AcademicPerformance performance : AcademicPerformance.values()) {
            performanceCount.put(performance, 0);
        }
        for (int i=0;i<studentCount;i++) {
            if(listStudent[i] != null){
                AcademicPerformance performance =listStudent[i].academicPerformance;
                performanceCount.put(performance, performanceCount.get(performance) + 1);
            }
        }
        return performanceCount;
    }

    public static void displayGpaPercentagesstatic() {
        Map<Double, Integer> gpaCount = new HashMap<>();
        for (int i=0;i<studentCount;i++) {
            Student student=listStudent[i];
            gpaCount.put(student.gpa, gpaCount.getOrDefault(student.gpa, 0) + 1);
        }

        int totalStudent = studentCount;
        Map<Double, Double> gpaPercentages = new HashMap<>();

        for (Map.Entry<Double, Integer> entry : gpaCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalStudent;
            gpaPercentages.put(entry.getKey(), percentage);
        }

        List<Map.Entry<Double, Double>> sortedGpaPercentages = gpaPercentages.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());

        System.out.println("Tỷ lệ phần trăm điểm trung bình của các sinh viên:");
        for (Map.Entry<Double, Double> entry : sortedGpaPercentages) {
            System.out.printf("%.1f: %.2f%%\n", entry.getKey(), entry.getValue());
        }


    }

    public void displayStudentsByAcademicPerformanceStatic() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào học lực sinh viên(KEM, YEU, TRUNG_BINH, KHA, GIOI, XUAT_SAC): ");
        String input = sc.nextLine().toUpperCase().replace(" ", "_");

        AcademicPerformance performance;
        try {
            performance = AcademicPerformance.valueOf(input);
        } catch (Exception e) {
            System.out.println("Học lực không hợp lệ.");
            return;
        }

        System.out.println("Danh sách sinh viên với học lực " + performance + " : ");
        for (int i = 0; i < studentCount; i++) {
            if (listStudent[i] != null && listStudent[i].academicPerformance == performance) {
                System.out.println(listStudent[i]);
            }
        }
    }

    public void storeStudent(){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("student.txt"));
            for(int i=0;i<studentCount;i++){
                writer.write(listStudent[i].toString()+ "\n");
            }
            writer.close();
            System.out.println("Lưu trữ danh sách sinh viên vào file student.txt thành công");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void storeStudentOut(){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("student.txt"));
            for(int i=0;i<studentCount;i++){
                writer.write(listStudent[i].toString()+ "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Mảng động
    public void inputStudents() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.println("Nhập tên sinh viên: ");
            name = sc.nextLine();
            if (!Const.validateName(name)) {
                System.out.println("Tên không được trống và phải ít hơn " + Const.MAX_NAME_LENGTH + " ký tự.");
            }
        } while (!Const.validateName(name));
        setName(name);

        String dateOfBirthStr;
        do {
            System.out.println("Nhập ngày sinh (yyyy-MM-dd): ");
            dateOfBirthStr = sc.nextLine();
            if (!Const.validateBirth(dateOfBirthStr)) {
                System.out.println("Nhập sai định dạng ngày sinh");
            }
        } while (!Const.validateBirth(dateOfBirthStr));
        try {
            Date dateOfBirth = Const.DATE_FORMAT.parse(dateOfBirthStr);
            setDateOfBirth(dateOfBirth);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        String address;
        do {
            System.out.println("Nhập địa chỉ: ");
            address = sc.nextLine();
            if (!Const.validateAddress(address)) {
                System.out.println("Địa chỉ phải ít hơn " + Const.MAX_ADDRESS_LENGTH + "  ký tự");
            }
        } while (!Const.validateAddress(address));
        setAddress(address);

        double height;
        do {
            System.out.println("Nhập chiều cao (cm): ");
            height = sc.nextDouble();
            if (!Const.validateHeight(height)) {
                System.out.println("Chiều cao phải nằm trong khoảng " + Const.MIN_HEIGHT + " đến " + Const.MAX_HEIGHT + " cm.");
            }
        } while (!Const.validateHeight(height));
        setHeight(height);

        double weight;
        do {
            System.out.println("Nhập cân nặng (kg): ");
            weight = sc.nextDouble();
            if (!Const.validateWeight(weight)) {
                System.out.println("Cân nặng phải nằm trong khoảng " + Const.MIN_WEIGHT + " đến " + Const.MAX_WEIGHT + " kg.");
            }
        } while (!Const.validateWeight(weight));
        setWeight(weight);
        sc.nextLine();

        String studentId;
        boolean isValidStudentId;
        do {
            System.out.println("Nhập mã sinh viên: ");
            studentId = sc.nextLine();

            isValidStudentId = Const.validateStudentId(studentId);
            if (!isValidStudentId) {
                System.out.println("Mã sinh viên phải có độ dài " + Const.STUDENT_ID_MAX_LENGTH + " ký tự.");
            } else {
                for (Student i:listStudents ) {
                    if (studentId.equals(i.getStudentId())) {
                        System.out.println("Mã sinh viên đã tồn tại vui lòng nhập mã sinh viên khác.");
                        isValidStudentId = false;
                        break;
                    }
                }
            }
        } while (!isValidStudentId);
        setStudentId(studentId);

        do {
            System.out.println("Nhập tên trường: ");
            schoolName = sc.nextLine();
            if (!Const.validateSchoolName(schoolName)) {
                System.out.println("Tên trường không được trống và phải ít hơn " + Const.SCHOOL_NAME_MAX_LENGTH + " ký tự.");
            }
        } while (!Const.validateSchoolName(schoolName));
        setSchoolName(schoolName);

        int studentYear;
        do {
            System.out.println("Nhập năm học: ");
            studentYear = sc.nextInt();
            if (!Const.validateSchoolYear(studentYear)) {
                System.out.println("Năm nhập học phải từ năm " + Const.MIN_SCHOOL_YEAR + " đến năm " + Const.MAX_SCHOOL_YEAR + ".");
            }
        } while (!Const.validateSchoolYear(studentYear));
        setStudentYear(studentYear);

        double gpa;
        do {
            System.out.println("Nhập điểm trung bình: ");
            gpa = sc.nextDouble();
            if (!Const.validateGPA(gpa)) {
                System.out.println("Điểm trung bình phải nằm trong khoảng " + Const.MIN_GPA + " đến " + Const.MAX_GPA + ".");
            }
        } while (!Const.validateGPA(gpa));
        setGpa(gpa);

        setAcademicPerformance();
    }
    public static void printListStudents() {
        for(Student student : listStudents) {
            if(student!=null){
                System.out.println(student.toString());
            }
        }
    }
    public static void addStudents() {
        Student students = new Student();
        students.inputStudents();
        students.setId(dynamicIdCounter++);
        listStudents.add(students);
        System.out.println("Thêm sinh viên thành công.");
        printListStudents();
    }
    public static Student searchStudents(int id) {
        for (Student student: listStudents) {
            if(student.getId()==id){
                System.out.println("Tìm thấy sinh viên có id: "+ id);
                System.out.println(student.toString());
                return student;
            }
            else {
                System.out.println("Không tìm thấy sinh viên có id: "+ id);
            }
        }
        return null;
    }

    public static void updateStudentByIds(int id) {
        Student studentToUpdate = searchStudents(id);
        if (studentToUpdate == null) {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
            return;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập số tương ứng để cập nhật thông tin:");
        System.out.println("1. Tên");
        System.out.println("2. Ngày sinh");
        System.out.println("3. Địa chỉ");
        System.out.println("4. Chiều cao");
        System.out.println("5. Cân nặng");
        System.out.println("6. Mã sinh viên");
        System.out.println("7. Tên trường");
        System.out.println("8. Năm học");
        System.out.println("9. Điểm GPA");
        System.out.println("10. Hoàn thành cập nhật");

        while (true){
            System.out.print("Chọn thông tin cần cập nhật (1-10): ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhập tên mới: ");
                    String newName;
                    do {
                        newName = sc.nextLine();
                        if (!Const.validateName(newName)) {
                            System.out.println("Tên không được trống và phải ít hơn " + Const.MAX_NAME_LENGTH + " ký tự.");
                            System.out.print("Vui lòng nhập lại tên: ");
                        }
                    }while (!Const.validateName(newName));
                    studentToUpdate.setName(newName);
                    break;
                case 2:
                    System.out.print("Nhập ngày sinh mới (yyyy-MM-dd): ");
                    String newDateOfBirth = sc.nextLine();
                    if (Const.validateBirth(newDateOfBirth)) {
                        try {
                            Date dateOfBirth = Const.DATE_FORMAT.parse(newDateOfBirth);
                            studentToUpdate.setDateOfBirth(dateOfBirth);
                        } catch (Exception e) {
                            System.out.println("Lỗi: Định dạng ngày không hợp lệ.");
                        }
                    } else {
                        System.out.println("Lỗi: Định dạng ngày không hợp lệ.");
                    }

                    break;
                case 3:
                    System.out.print("Nhập địa chỉ mới: ");
                    String newAddress;
                    do {
                        newAddress= sc.nextLine();
                        if (!Const.validateAddress(newAddress)) {
                            System.out.println("Địa chỉ phải ít hơn " + Const.MAX_ADDRESS_LENGTH + "  ký tự và kh đc trống" );
                            System.out.print("Nhập lại địa chỉ mới: ");
                        }
                    }while (!Const.validateAddress(newAddress));
                    studentToUpdate.setAddress(newAddress);
                    break;
                case 4:
                    System.out.print("Nhập chiều cao mới (cm): ");
                    double newHeight;
                    do {
                        newHeight = sc.nextDouble();
                        if (!Const.validateHeight(newHeight)) {
                            System.out.println("Chiều cao phải nằm trong khoảng " + Const.MIN_HEIGHT + " đến " + Const.MAX_HEIGHT + " cm.");
                            System.out.print("Nhập lại chiều cao mới: ");
                        }
                    }while (!Const.validateHeight(newHeight));
                    studentToUpdate.setHeight(newHeight);
                    break;
                case 5:
                    System.out.print("Nhập cân nặng mới (kg): ");
                    double newWeight;
                    do {
                        newWeight = sc.nextDouble();
                        if (!Const.validateWeight(newWeight)) {
                            System.out.println("Cân nặng phải nằm trong khoảng " + Const.MIN_WEIGHT + " đến " + Const.MAX_WEIGHT + " kg.");
                            System.out.print("Nhập lại cân nặng mới: ");
                        }
                    }while (!Const.validateWeight(newWeight));
                    studentToUpdate.setWeight(newWeight);
                    break;
                case 6:
                    System.out.print("Nhập mã sinh viên mới: ");
                    String newStudentId;
                    boolean isValid=false;
                    do {
                        newStudentId = sc.nextLine();
                        if(newStudentId.equals(studentToUpdate.getId())){
                            isValid=true;
                            break;
                        }
                        if (!Const.validateStudentId(newStudentId)) {
                            System.out.println("Mã sinh viên phải có độ dài " + Const.STUDENT_ID_MAX_LENGTH + " ký tự.");
                            System.out.print("Nhập lại mã sinh viên mới: ");
                        }
                        else {
                            for (Student i:listStudents){
                                if (newStudentId.equals(i.getStudentId())) {
                                    System.out.println("Mã sinh viên đã tồn tại vui lòng nhập mã sinh viên khác.");
                                    break;
                                }
                            }
                        }
                    }while (!Const.validateStudentId(newStudentId));
                    studentToUpdate.setStudentId(newStudentId);
                    break;
                case 7:
                    System.out.print("Nhập tên trường mới: ");
                    String newSchoolName;
                    do {
                        newSchoolName = sc.nextLine();
                        if (!Const.validateSchoolName(newSchoolName)) {
                            System.out.println("Tên trường không được trống và phải ít hơn " + Const.SCHOOL_NAME_MAX_LENGTH + " ký tự.");
                            System.out.println("Nhập lại tên trường khác: ");
                        }
                    }while (!Const.validateSchoolName(newSchoolName));
                    studentToUpdate.setSchoolName(newSchoolName);
                    break;
                case 8:
                    System.out.print("Nhập năm học mới: ");
                    int newStudentYear;
                    do {
                        newStudentYear = sc.nextInt();
                        if (!Const.validateSchoolYear(newStudentYear)) {
                            System.out.println("Năm nhập học phải từ năm " + Const.MIN_SCHOOL_YEAR + " đến năm " + Const.MAX_SCHOOL_YEAR + ".");
                            System.out.println("Nhập lại năm học mới: ");
                        }
                    }while (!Const.validateSchoolYear(newStudentYear));
                    studentToUpdate.setStudentYear(newStudentYear);
                    break;
                case 9:
                    System.out.print("Nhập điểm GPA mới: ");
                    double newGpa;
                    do {
                        newGpa = sc.nextDouble();
                        if (!Const.validateGPA(newGpa)) {
                            System.out.println("Điểm trung bình phải nằm trong khoảng " + Const.MIN_GPA + " đến " + Const.MAX_GPA + ".");
                            System.out.print("Nhập lại điểm GPA mới: ");
                        }
                    }while (!Const.validateGPA(newGpa));
                    studentToUpdate.setGpa(newGpa);
                    studentToUpdate.setAcademicPerformance();
                    break;
                case 10:
                    System.out.println("Hoàn thành cập nhật.");
                    System.out.println("Thông tin sinh viên sau khi cập nhật:");
                    System.out.println(studentToUpdate.toString());
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");

            }
        }
    }


    public static void deleteStudentByIds(int id) {
        int indexToRemove = -1;
        for (int i = 0; i < listStudents.size(); i++) {
            if (listStudents.get(i) != null && listStudents.get(i).getId() == id) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove == -1) {
            System.out.println("Không tìm thấy sinh viên cần xóa ");
            return;
        }

        listStudents.remove(indexToRemove);
        System.out.println("Xóa thành công sinh viên có id là " + id);
        printListStudents();
    }

    public void displayAcademicPerformancePercentages() {
        Map<AcademicPerformance, Integer> performanceCount = countAcademicPerformance();
        int totalStudents = listStudents.size();
        Map<AcademicPerformance, Double> performancePercentages = new EnumMap<>(AcademicPerformance.class);

        for (Map.Entry<AcademicPerformance, Integer> entry : performanceCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalStudents;
            performancePercentages.put(entry.getKey(), percentage);
        }

        List<Map.Entry<AcademicPerformance, Double>> sortedPerformancePercentages = performancePercentages.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());

        System.out.println("Tỷ lệ phần trăm học lực của các sinh viên:");
        for (Map.Entry<AcademicPerformance, Double> entry : sortedPerformancePercentages) {
            System.out.printf("%s: %.2f%%\n",entry.getKey(), entry.getValue());
        }
    }

    private Map<AcademicPerformance, Integer> countAcademicPerformance() {
        Map<AcademicPerformance, Integer> performanceCount = new EnumMap<>(AcademicPerformance.class);
        for (AcademicPerformance performance : AcademicPerformance.values()) {
            performanceCount.put(performance, 0);
        }
        for (Student student : listStudents) {
            AcademicPerformance performance = student.academicPerformance;
            performanceCount.put(performance, performanceCount.get(performance) + 1);
        }
        return performanceCount;
    }

    public void displayGpaPercentages() {
        Map<Double, Integer> gpaCount = new HashMap<>();
        for (Student student : listStudents) {
            gpaCount.put(student.gpa, gpaCount.getOrDefault(student.gpa, 0) + 1);
        }

        int totalStudents = listStudents.size();
        Map<Double, Double> gpaPercentages = new HashMap<>();

        for (Map.Entry<Double, Integer> entry : gpaCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalStudents;
            gpaPercentages.put(entry.getKey(), percentage);
        }

        List<Map.Entry<Double, Double>> sortedGpaPercentages = gpaPercentages.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toList());

        System.out.println("Tỷ lệ phần trăm điểm trung bình của các sinh viên:");
        for (Map.Entry<Double, Double> entry : sortedGpaPercentages) {
            System.out.printf("%.1f: %.2f%%\n", entry.getKey(), entry.getValue());
        }
    }

    public void displayStudentsByAcademicPerformance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào học lực sinh viên(KEM, YEU, TRUNG_BINH, KHA, GIOI, XUAT_SAC): ");
        String input = sc.nextLine().toUpperCase().replace(" ", "_");

        AcademicPerformance performance;
        try {
            performance = AcademicPerformance.valueOf(input);
        } catch (Exception e) {
            System.out.println("Học lực không hợp lệ.");
            return;
        }

        System.out.println("Danh sách sinh viên với học lực " + performance + " : ");
        for (int i = 0; i < listStudents.size(); i++) {
            if (listStudents.get(i) != null && listStudents.get(i).academicPerformance == performance) {
                System.out.println(listStudents.get(i));
            }
        }
    }

    public void storeStudents(){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("students.txt"));
            for (Student student : listStudents) {
                writer.write(student+ "\n" );
            }
            System.out.println("Lưu trữ danh sách sinh viên vào file student.txt thành công");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void storeStudentsOut(){
        try {
            BufferedWriter writer=new BufferedWriter(new FileWriter("students.txt"));
            for (Student student : listStudents) {
                writer.write(student+ "\n" );
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
