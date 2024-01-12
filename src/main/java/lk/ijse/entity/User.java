package lk.ijse.entity;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private String UserId;
    private String Name;
    private String Email;
    private String Password;
}

