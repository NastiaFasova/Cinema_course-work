package kpi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Double amountOfMoney;
    private Long userId;

    public Bill(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Bill(Long id, Double amountOfMoney) {
        this.id = id;
        this.amountOfMoney = amountOfMoney;
    }
}
