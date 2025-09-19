package hospital.model.entity;

import com.google.gson.Gson;
import hospital.model.entity.enums.PayType;
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
        private Bank bank;
        private PayType payType;





        @Override
        public String toString() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

    }


