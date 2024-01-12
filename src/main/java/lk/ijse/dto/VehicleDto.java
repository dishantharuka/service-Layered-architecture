package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class VehicleDto {

    private String id;
    private String model;
    private String year;
    private String licensePlate;
    private String CustomerID;
}