package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    public class appoinment {

        private String id;
        private String date;
        private String time;
        private String serviceType;
        private String vehicleID;

    }
