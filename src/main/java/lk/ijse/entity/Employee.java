package lk.ijse.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String EmployeeId;
    private String Name;
    private String contactInformation;
    private String Role;
    private String Salary;
    private String UserId;
}
