package employeeScreen;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee extends RecursiveTreeObject<Employee> {
    public StringProperty id;
    public StringProperty username;
    public StringProperty password;
    public StringProperty fullName;
    public StringProperty address;
    public StringProperty phone;
    public StringProperty startDate;
    public StringProperty salary;
    public StringProperty userType;
    public Employee() {
        super();
    }

    public Employee(String id,
                    String username,
                    String password,
                    String fullName,
                    String address,
                    String phone,
                    String startDate,
                    String salary,
                    String userType)
    {
        this.id = new SimpleStringProperty(id);
        this.username= new SimpleStringProperty(username);
        this.password= new SimpleStringProperty(password);
        this.fullName= new SimpleStringProperty(fullName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.startDate = new SimpleStringProperty(startDate);
        this.salary = new SimpleStringProperty(salary);
        this.userType = new SimpleStringProperty(userType);

    }
}
