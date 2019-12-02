package database;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Callback;


public class Room extends RecursiveTreeObject<Room> {
    public StringProperty id;
    public StringProperty roomType;
    public StringProperty roomNumber;
    public StringProperty numberOfPeople;
    public StringProperty floorNumber;
    public StringProperty roomPhone;
    public StringProperty roomPrice;
    public StringProperty roomStatus;
    public Room() {
        super();
    }

    public Room(String id,
                String roomType,
                String roomNumber,
                String numberOfPeople,
                String floorNumber,
                String roomPhone,
                String roomPrice,
                String roomStatus)
    {
        this.id = new SimpleStringProperty(id);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.numberOfPeople = new SimpleStringProperty(numberOfPeople);
        this.floorNumber = new SimpleStringProperty(floorNumber);
        this.roomPhone = new SimpleStringProperty(roomPhone);
        this.roomPrice = new SimpleStringProperty(roomPrice);
        this.roomStatus = new SimpleStringProperty(roomStatus);
    }
}
