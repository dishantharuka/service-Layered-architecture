package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SalaryDto {

        private String salaryId;
        private String EmployeeID;
        private String month;
        private String year;
        private String amount;

    }

