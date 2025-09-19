package hospital.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter

public class CashDesk {

        private int id;
        private float cashBalance;
        private float bankBalance;


        public void processPayment(Payment payment) {
            if (payment.getPayType().name().equalsIgnoreCase("CASH")) {
                cashBalance += payment.getPrice();
            } else if (payment.getPayType().name().equalsIgnoreCase("CARD")) {
                bankBalance += payment.getPrice();
            }
        }


        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

    }


