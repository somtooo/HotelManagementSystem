package customerScreen;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer extends RecursiveTreeObject<Customer> {
    public StringProperty id;
    public StringProperty name;
    public StringProperty email;
    public StringProperty address;
    public StringProperty phone;
    public StringProperty numOfPeople;
    public StringProperty paymentType;
    public StringProperty duration;
    public StringProperty roomType;
    public StringProperty roomNumber;
    public StringProperty startDate;
    public StringProperty endDate;
    public StringProperty price;
    public StringProperty services;
    public StringProperty total;
    public Customer() {
        super();
    }

    public Customer(String id,
                    String name,
                    String email,
                    String address,
                    String phone,
                    String numOfPeople,
                    String paymentType,
                    String duration,
                    String roomType,
                    String roomNumber,
                    String startDate,
                    String endDate,
                    String price,
                    String services,
                    String total)
    {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.numOfPeople = new SimpleStringProperty(numOfPeople);
        this.paymentType = new SimpleStringProperty(paymentType);
        this.duration = new SimpleStringProperty(duration);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.startDate = new SimpleStringProperty(startDate);
        this.endDate = new SimpleStringProperty(endDate);
        this.price = new SimpleStringProperty(price);
        this.services = new SimpleStringProperty(services);
        this.total = new SimpleStringProperty(total);
    }
}
