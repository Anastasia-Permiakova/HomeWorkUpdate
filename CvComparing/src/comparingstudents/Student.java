package comparingstudents;

import comparingstudents.mycomparing.CompareInterface;

import java.util.ArrayList;
import java.util.Objects;

public class Student implements CompareInterface, Comparable<Student>{
    private String firstName;
    private String lastName;
    private int studentNumber;
    private ArrayList<Integer> gardes;
    private double averageGrade;

    //TODO pole znamek

    public Student(String firstName, String lastName, int studentNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
    }
    public Student(String firstName, String lastName, int studentNumber, ArrayList grades) {
        this.gardes = grades;
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentNumber = studentNumber;
        countAverage();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public double getAverageGrade() {
        return averageGrade;
    }


    //TODO calculateAverage
    
    @Override
    public String toString() {
        return String.format("%10s%10s%10d",firstName, lastName, studentNumber);
    }

    public boolean isBigger(Student student) {
        return this.studentNumber > student.studentNumber;
    }

    //potrebne pro MyComparing
    @Override
    public boolean isBigger(CompareInterface o) {
        return this.studentNumber > ((Student)o).studentNumber;
    }

   /* @Override
    public int compareTo(Object o) {
        return this.studentNumber - ((Student)o).studentNumber;
    }*/

    //potrebne pro Comparing
    @Override
    public int compareTo(Student o) {
        return this.studentNumber - o.studentNumber;
    }
    
    //pri zmene equals zmenit i hashCode
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + this.studentNumber;
        return hash;
    }
    private void countAverage(){
        double sumGrade = 0;
        double averageGrade;
        for (int grade : this.gardes) {
            sumGrade += grade;
        }
        this.averageGrade = sumGrade/gardes.size();
    }

    private void addGrade(int grade){
        this.gardes.add(grade);
        countAverage();
    }

    private void changeGrade(int grade, int position){
        this.gardes.add(position, grade);
        countAverage();
    }

    private String printGrades(){
        String gradesString = "{";
        for (int i = 0; i < this.gardes.size()-1; i++){
            gradesString += gardes.get(i);
            gradesString += ", ";
        }
        gradesString += gardes.get(gardes.size()-1);
        gradesString += "}";
        return gradesString;
    }
    //default in Object 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        return true;
    }
}
