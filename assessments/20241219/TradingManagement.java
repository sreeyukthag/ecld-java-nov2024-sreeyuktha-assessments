package exam;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

enum TransactionType {BUY, SELL}

// Class for representing a financial transaction
class Transaction {
    private Long transactionId;
    private String symbol;
    private double amount;
    private TransactionType type;
    private LocalDateTime timestamp;

    // Constructor of class Transaction
    public Transaction(Long transactionId, String symbol, double amount, TransactionType type) {
        this.transactionId = transactionId;
        this.symbol = symbol;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    // Getter methods
    public Long getTransactionId() {
        return transactionId;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}

// Interface for the trading system
interface TradingSystem {
    void processTransaction(Transaction transaction);
    void rollbackTransaction(Long transactionId);
    Iterator<Transaction> getTransactionsByTimeWindow(Duration window);
    double getAggregateAmount(String symbol, Duration window);
}

// Class that implements trading system
class TradingProcessor implements TradingSystem {
    private List<Transaction> activeTransactions; // List of active transactions
    private List<Transaction> historicalData; // List of historical transactions

    // Constructor of class TradingProcessor
    public TradingProcessor() {
        this.activeTransactions = new ArrayList<>();
        this.historicalData = new ArrayList<>();
    }

    // Process a new transaction
    public void processTransaction(Transaction transaction) {
        activeTransactions.add(transaction);
        historicalData.add(transaction);
    }

    // Rolling back a transaction using ID
    public void rollbackTransaction(Long transactionId) {
        activeTransactions.removeIf(transaction -> transaction.getTransactionId().equals(transactionId));
    }

    // Get transactions within a specified time window
    public Iterator<Transaction> getTransactionsByTimeWindow(Duration window) {
        LocalDateTime now = LocalDateTime.now();
        List<Transaction> filteredTransactions = activeTransactions.stream()
                .filter(transaction -> Duration.between(transaction.getTimestamp(), now).compareTo(window) <= 0)
                .collect(Collectors.toList());
        return filteredTransactions.iterator();
    }

    // Get the aggregate amount of transactions for a specific symbol within a time window
    public double getAggregateAmount(String symbol, Duration window) {
        LocalDateTime now = LocalDateTime.now();
        return activeTransactions.stream()
                .filter(transaction -> transaction.getSymbol().equals(symbol) &&
                        Duration.between(transaction.getTimestamp(), now).compareTo(window) <= 0)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
}

// Main class
public class TradingManagement {
    public static void main(String[] args) {
        TradingProcessor tradingProcessor = new TradingProcessor();

        // Demo transactions
        tradingProcessor.processTransaction(new Transaction(1L, "ABCD", 150.0, TransactionType.BUY));
        tradingProcessor.processTransaction(new Transaction(2L, "ABCD", 200.0, TransactionType.SELL));
        tradingProcessor.processTransaction(new Transaction(3L, "WXYZ", 300.0, TransactionType.BUY));
        tradingProcessor.processTransaction(new Transaction(4L, "ABCD", 300.0, TransactionType.BUY));
        tradingProcessor.processTransaction(new Transaction(5L, "ABCD", 300.0, TransactionType.SELL));
        tradingProcessor.processTransaction(new Transaction(6L, "WXYZ", 300.0, TransactionType.BUY));

        // Rolling back a transaction
        tradingProcessor.rollbackTransaction(2L);

        // Showing Transactions in last 1 Hr
        Duration oneHour = Duration.ofHours(1);
        Iterator<Transaction> transactions = tradingProcessor.getTransactionsByTimeWindow(oneHour);
        System.out.println("Transactions in the last hour:");
        while (transactions.hasNext()) {
            Transaction transaction = transactions.next();
            System.out.println("ID: " + transaction.getTransactionId() + ", Symbol: " + transaction.getSymbol() + ", Amount: " + transaction.getAmount());
        }

        // Get aggregate amount for "ABCD" in the last 1 hour
        double totalAmount = tradingProcessor.getAggregateAmount("ABCD", oneHour);
        System.out.println("Total amount for ABCD in the last hour: " + totalAmount);
    }
}
