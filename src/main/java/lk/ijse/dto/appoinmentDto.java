package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    public class appoinmentDto {

        private String id;
        private String date;
        private String time;
        private String serviceType;
        private String vehicleID;

    }
