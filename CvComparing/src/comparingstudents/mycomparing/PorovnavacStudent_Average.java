package comparingstudents.mycomparing;

import comparingstudents.Student;

public class PorovnavacStudent_Average implements ComparatorInterface{

    @Override
    public boolean bigger(Object o1, Object o2) {
        return ((Student)o1).getAverageGrade() > ((Student)o2).getAverageGrade();
    }

}