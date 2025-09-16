package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.PayType;
import hospital.model.entity.enums.VisitPrice;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class Payment implements Transaction {
    private int id;
    private PayType payType;
    private LocalDate payDate;
    private int price;
    private Doctor doctor;



    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    @Override
    public void pay ( ) {
        try {
            switch (payType) {
                case CARD:
                    System.out.println("pay with card" + " " + "price :" + price + " payDate :" + payDate);
                    break;
                case CASH:
                    System.out.println("pay with cash" + " " + "price :" + price + " payDate :" + payDate);
                    break;
                case CHECK:
                    System.out.println("pay with check" + " " + "price :" + price + " payDate :" + payDate);
                    break;
                case ONLINE:
                    System.out.println("pay online" + " " + "price :" + price + " payDate :" + payDate);
                    break;
                case TRANSFER:
                    System.out.println("transfer pay" + " " + "price :" + price + " payDate :" + payDate);
                    break;
                default:
                    System.out.println("pay type not found");
            }
            this.payDate = LocalDate.now();
            receipt();

        }catch (Exception e){
            System.out.println("Payment error:" + e.getMessage());
        }
    }



    @Override
    public void receipt ( ) {
        System.out.println("----- payment receipt -----");
        System.out.println("Payment number: " + id);
        System.out.println("payment date: " + (payDate != null ? payDate : "not registered"));
        System.out.println("payment type: " + (payType != null ? payType : "uncertain"));
        System.out.println("amount paid: " + price + " toman");
        System.out.println("-----------------------");

    }
}
