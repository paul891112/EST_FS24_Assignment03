import org.mockito.internal.matchers.Or;

public class MockOrderBuilder {

    //default values
    private String orderId = "id542369";
    private double amount = 0;

    public MockOrderBuilder withOrderId(String id){
        this.orderId = id;
        return this;
    }

    public MockOrderBuilder withAmount(double a){
        this.amount = a;
        return this;
    }

    public Order build(){
        return new Order(this.orderId, this.amount);
    }
}
