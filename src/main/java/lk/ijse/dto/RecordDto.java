package lk.ijse.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class RecordDto {

        private String id;
        private String vehicleID;
        private String serviceDate;
        private String serviceDescription;
        private String technicianName;

    }
