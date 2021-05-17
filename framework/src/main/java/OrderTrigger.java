import java.math.BigDecimal;

public class OrderTrigger {

    enum Type {STOPLOSS, TAKEPROFIT, OPEN}

    private int id;
    private Type type;
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
