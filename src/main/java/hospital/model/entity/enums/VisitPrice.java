package hospital.model.entity.enums;

import lombok.Getter;


@Getter
public enum VisitPrice {
    VISIT1(180_000),
    VISIT2(200_000);

    private final int price;

    VisitPrice(int price) {
        this.price = price;
    }

}
