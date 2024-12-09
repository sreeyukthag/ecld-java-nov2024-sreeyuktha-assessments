package exam;

import java.time.LocalDateTime;
import java.util.*;

abstract class Order {
    String orderId;
    String customerName;
    LocalDateTime orderDate;
    List<OrderItem> items;

    public Order(String orderId, String customerName, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.items = new LinkedList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public abstract double calculateTotal();
    public abstract double calculateDiscount();

    public String getCustomerName() {
        return customerName;
    }

    public double getOrderValue() {
        return calculateTotal() - calculateDiscount();
    }
}

class OrderItem {
    String productId;
    int quantity;
    double pricePerUnit;

    public OrderItem(String productId, int quantity, double pricePerUnit) {
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public double getTotalPrice() {
        return quantity * pricePerUnit;
    }
}

class RegularOrder extends Order {
    private static final double MINIMUM_FOR_DISCOUNT = 100.0;
    private static final double DISCOUNT_PERCENT = 0.10;

    public RegularOrder(String orderId, String customerName, LocalDateTime orderDate) {
        super(orderId, customerName, orderDate);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public double calculateDiscount() {
        double total = calculateTotal();
        if (total >= MINIMUM_FOR_DISCOUNT) {
            return total * DISCOUNT_PERCENT;
        }
        return 0;
    }
}

class PriorityOrder extends Order {
    private boolean expressShipping;
    private double additionalFee;

    public PriorityOrder(String orderId, String customerName, LocalDateTime orderDate, boolean expressShipping, double additionalFee) {
        super(orderId, customerName, orderDate);
        this.expressShipping = expressShipping;
        this.additionalFee = additionalFee;
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotalPrice();
        }
        if (expressShipping) {
            total += additionalFee;
        }
        return total;
    }

    public double calculateDiscount() {
        return 0;
    }
}

class OrderProcessor {
    private List<Order> orders;

    public OrderProcessor() {
        this.orders = new LinkedList<>();
    }

    public void processOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrdersByCustomer(String customerName) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    public double getTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue += order.getOrderValue();
        }
        return totalRevenue;
    }

    public double getAverageOrderValue() {
        if (orders.isEmpty()) {
            return 0;
        }
        return getTotalRevenue() / orders.size();
    }

    public List<String> getTopCustomers(int n) {
        return null;
    }

    public Iterator<Order> iterator() {
        return new OrderIterator();
    }

    private class OrderIterator implements Iterator<Order> {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < orders.size();
        }

        public Order next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return orders.get(currentIndex++);
        }
    }
}

public class HotelManagement {
    public static void main(String[] args) {
        OrderProcessor orderProcessor = new OrderProcessor();

        RegularOrder order1 = new RegularOrder("ORD001", "Sreeyuktha", LocalDateTime.now());
        order1.addItem(new OrderItem("P001", 2, 50.0));
        order1.addItem(new OrderItem("P002", 1, 30.0));
        orderProcessor.processOrder(order1);

        RegularOrder order2 = new RegularOrder("ORD002", "Abhinav", LocalDateTime.now());
        order2.addItem(new OrderItem("P003", 1, 120.0));
        orderProcessor.processOrder(order2);

        PriorityOrder order3 = new PriorityOrder("ORD003", "Praseeda", LocalDateTime.now(), true, 15.0);
        order3.addItem(new OrderItem("P004", 3, 40.0));
        orderProcessor.processOrder(order3);

        System.out.println("Total Revenue: $" + orderProcessor.getTotalRevenue());

        System.out.println("Average Order Value: $" + orderProcessor.getAverageOrderValue());

        List<Order> Orders = orderProcessor.getOrdersByCustomer("Praseeda");
        System.out.println("Orders for Praseeda :");
        for (Order order : Orders) {
            System.out.println("Order ID: " + order.orderId + ", Total Value: $" + order.getOrderValue());
        }

    }
}
