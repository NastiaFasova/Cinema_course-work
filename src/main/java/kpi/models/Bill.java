package kpi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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
//    @OneToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
    private Long userId;

    public Bill(Double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public Bill(Long id, Double amountOfMoney) {
        this.id = id;
        this.amountOfMoney = amountOfMoney;
    }
    public Bill(Long id) {
        this.id = id;
    }
}
