package model;

import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orders {
    private String id;
    private Date date;
    private String customerId;
   private ArrayList<OrderDetail> orderDetails;
}
