package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class paymentDto {



        private String id;
        private String amount;
        private String paymentMethod;
        private String itemID;
}
