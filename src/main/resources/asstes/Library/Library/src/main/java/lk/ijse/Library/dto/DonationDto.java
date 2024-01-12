package lk.ijse.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DonationDto {

    private String d_id;
    private String d_name;
    private String address;
    private int tel;
    private int monetary_amount;
}
