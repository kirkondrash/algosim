import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="order_triggers")
public class OrderTrigger {

    enum Type {STOPLOSS, MAKEPROFIT, OPEN}

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_triggers_sequence"
    )
    @SequenceGenerator(
            name = "order_triggers_sequence",
            sequenceName = "order_triggers_id_seq",
            allocationSize = 40
    )
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;
    @Column(name = "trigger")
    private BigDecimal trigger;

    public OrderTrigger() {
    }

    public OrderTrigger(Type type, BigDecimal trigger) {
        this.type = type;
        this.trigger = trigger;
    }

    public int getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getTrigger() {
        return trigger;
    }

    public void setTrigger(BigDecimal trigger) {
        this.trigger = trigger;
    }

    @Override
    public String toString() {
        return "OrderTrigger{" +
                "type=" + type +
                ", trigger=" + trigger +
                '}';
    }
}
