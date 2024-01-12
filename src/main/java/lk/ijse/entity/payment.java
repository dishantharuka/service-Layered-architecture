package lk.ijse.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class payment {



        private String id;
        private String amount;
        private String paymentMethod;
        private String itemID;
}
