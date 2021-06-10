package afaqy;

import java.sql.Date;
import java.time.LocalDate;

public class NewCRUDMain {

    public static void main(String[] args) {

        NewCRUDOperations crud = new NewCRUDOperations();
        System.out.println(crud.addEmployee(202,"Mahmoud","Muahmmad", 'M', Date.valueOf(LocalDate.of(1994,5,10)), Date.valueOf(LocalDate.now())));
        System.out.println(crud.deleteEmployee(202));
        System.out.println(crud.addDepartment("d030","BLA"));
        System.out.println(crud.getDepartmentByID("d030"));
        System.out.println(crud.updateDepartmentName("d030","BLA BLA"));
        System.out.println(crud.deleteDepartment("d030"));

    }

}
