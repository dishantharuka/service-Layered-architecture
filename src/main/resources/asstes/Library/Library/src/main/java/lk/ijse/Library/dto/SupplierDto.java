package lk.ijse.Library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SupplierDto {
    private String s_id;
    private String name;
    private String address;
    private int tel;

}
