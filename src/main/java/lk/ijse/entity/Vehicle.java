package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class Vehicle {

    private String id;
    private String model;
    private String year;
    private String licensePlate;
    private String CustomerID;
}
