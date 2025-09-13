package modle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String ItemCode ;
    private String Description;
    private String PackSize ;
    private double UnitPrice;
    private int QtyOnHand;


}
