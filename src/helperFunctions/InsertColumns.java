package helperFunctions;
import availableRoomsScreen.Room;
import com.jfoenix.controls.JFXTreeTableColumn;
import customerScreen.Customer;

public class InsertColumns {


    public static JFXTreeTableColumn<Room, String> insertColumnForRoom(String text){
        switch (text) {
            case "Room id":
                JFXTreeTableColumn<Room, String> room_id = new JFXTreeTableColumn<>(text);
                room_id.setPrefWidth(100);
                room_id.setCellValueFactory(param -> param.getValue().getValue().id);
                return room_id;
            case "Room Type":
                JFXTreeTableColumn<Room, String> roomType = new JFXTreeTableColumn<>(text);
                roomType.setPrefWidth(100);
                roomType.setCellValueFactory(param -> param.getValue().getValue().roomType);
                return roomType;
            case "Room Num":
                JFXTreeTableColumn<Room, String> room_number = new JFXTreeTableColumn<>(text);
                room_number.setPrefWidth(100);
                room_number.setCellValueFactory(param -> param.getValue().getValue().roomNumber);
                return room_number;
            case "People":
                JFXTreeTableColumn<Room, String> num_of_people = new JFXTreeTableColumn<>(text);
                num_of_people.setPrefWidth(70);
                num_of_people.setCellValueFactory(param -> param.getValue().getValue().numberOfPeople);
                return num_of_people;
            case "Floor":
                JFXTreeTableColumn<Room, String> floor_number = new JFXTreeTableColumn<>(text);
                floor_number.setPrefWidth(100);
                floor_number.setCellValueFactory(param -> param.getValue().getValue().floorNumber);
                return floor_number;
            case "Room Phone":
                JFXTreeTableColumn<Room, String> room_phone = new JFXTreeTableColumn<>(text);
                room_phone.setPrefWidth(100);
                room_phone.setCellValueFactory(param -> param.getValue().getValue().roomPhone);
                return room_phone;
            case "Room Price":
                JFXTreeTableColumn<Room, String> room_price = new JFXTreeTableColumn<>(text);
                room_price.setPrefWidth(100);
                room_price.setCellValueFactory(param -> param.getValue().getValue().roomPrice);
                return room_price;
            case "Room Status":
                JFXTreeTableColumn<Room, String> room_status = new JFXTreeTableColumn<>(text);
                room_status.setPrefWidth(100);
                room_status.setCellValueFactory(param -> param.getValue().getValue().roomStatus);
                return room_status;
            default:
                throw new IllegalArgumentException("Use matching text");
        }
    }

    public static JFXTreeTableColumn<Customer, String> insertColumnForCustomers(String text){
        switch (text) {
            case "id":
                JFXTreeTableColumn<Customer, String> id = new JFXTreeTableColumn<>(text);
                id.setPrefWidth(65);
                id.setCellValueFactory(param -> param.getValue().getValue().id);
                return id;
            case "name":
                JFXTreeTableColumn<Customer, String> name = new JFXTreeTableColumn<>(text);
                name.setPrefWidth(65);
                name.setCellValueFactory(param -> param.getValue().getValue().name);
                return name;
            case "email":
                JFXTreeTableColumn<Customer, String> email = new JFXTreeTableColumn<>(text);
                email.setPrefWidth(60);
                email.setCellValueFactory(param -> param.getValue().getValue().email);
                return email;
            case "address":
                JFXTreeTableColumn<Customer, String> address = new JFXTreeTableColumn<>(text);
                address.setPrefWidth(70);
                address.setCellValueFactory(param -> param.getValue().getValue().address);
                return address;
            case "phone":
                JFXTreeTableColumn<Customer, String> phone = new JFXTreeTableColumn<>(text);
                phone.setPrefWidth(60);
                phone.setCellValueFactory(param -> param.getValue().getValue().phone);
                return phone;
            case "numOfPeople":
                JFXTreeTableColumn<Customer, String> numOfPeople = new JFXTreeTableColumn<>(text);
                numOfPeople.setPrefWidth(110);
                numOfPeople.setCellValueFactory(param -> param.getValue().getValue().numOfPeople);
                return numOfPeople;
            case "paymentType":
                JFXTreeTableColumn<Customer, String> paymentType = new JFXTreeTableColumn<>(text);
                paymentType.setPrefWidth(110);
                paymentType.setCellValueFactory(param -> param.getValue().getValue().paymentType);
                return paymentType;
            case "duration":
                JFXTreeTableColumn<Customer, String> duration = new JFXTreeTableColumn<>(text);
                duration.setPrefWidth(75);
                duration.setCellValueFactory(param -> param.getValue().getValue().duration);
                return duration;
            case "roomType":
                JFXTreeTableColumn<Customer, String> roomType = new JFXTreeTableColumn<>(text);
                roomType.setPrefWidth(100);
                roomType.setCellValueFactory(param -> param.getValue().getValue().roomType);
                return roomType;
            case "roomNumber":
                JFXTreeTableColumn<Customer, String> roomNumber = new JFXTreeTableColumn<>(text);
                roomNumber.setPrefWidth(110);
                roomNumber.setCellValueFactory(param -> param.getValue().getValue().roomNumber);
                return roomNumber;
            case "startDate":
                JFXTreeTableColumn<Customer, String> startDate = new JFXTreeTableColumn<>(text);
                startDate.setPrefWidth(80);
                startDate.setCellValueFactory(param -> param.getValue().getValue().startDate);
                return startDate;
            case "endDate":
                JFXTreeTableColumn<Customer, String> endDate = new JFXTreeTableColumn<>(text);
                endDate.setPrefWidth(75);
                endDate.setCellValueFactory(param -> param.getValue().getValue().endDate);
                return endDate;
            case "price":
                JFXTreeTableColumn<Customer, String> price = new JFXTreeTableColumn<>(text);
                price.setPrefWidth(65);
                price.setCellValueFactory(param -> param.getValue().getValue().price);
                return price;
            case "services":
                JFXTreeTableColumn<Customer, String> services = new JFXTreeTableColumn<>(text);
                services.setPrefWidth(70);
                services.setCellValueFactory(param -> param.getValue().getValue().services);
                return services;
            case "total":
                JFXTreeTableColumn<Customer, String> total = new JFXTreeTableColumn<>(text);
                total.setPrefWidth(60);
                total.setCellValueFactory(param -> param.getValue().getValue().total);
                return total;

            default:
                throw new IllegalArgumentException("Use matching text");
        }
    }




}
