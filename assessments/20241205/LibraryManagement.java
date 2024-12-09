package exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//created a abstract class Library Item with Id,Item and availability
abstract class LibraryItem {
    String id, title;
    boolean available;
  
//created a constructor of the class
    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }
//within the class a function is created to calculate fee - will be inherited.
    public abstract double calculateLateFee(int daysLate);
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// class Book created - child class of LibraryItem
class Book extends LibraryItem {
    private double lateFeePerDay = 2.0;
    public Book(String id, String title) {
        super(id, title);
    }
    public double calculateLateFee(int daysLate) {
        return daysLate * lateFeePerDay;
    }
}
// class Magazine created - child class of LibraryItem
class Magazine extends LibraryItem {
    private double lateFeePerDay = 1.0;

    public Magazine(String id, String title) {
        super(id, title);
    }
    public double calculateLateFee(int daysLate) {
        return daysLate * lateFeePerDay;
    }
}
//Class Library is created
class Library {
    private List<LibraryItem> items = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
    }

    public void removeItem(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                break;
            }
        }
    }

    public List<LibraryItem> searchByTitle(String title) {
        List<LibraryItem> foundItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                foundItems.add(item);
            }
        }
        return foundItems;
    }

    public Optional<LibraryItem> borrowItem(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                if (item.isAvailable()) {
                    item.setAvailable(false);
                    return Optional.of(item);
                } else {
                    System.out.println("Item is currently not available.");
                    return Optional.empty();
                }
            }
        }
        System.out.println("Item not found.");
        return Optional.empty();
    }

    public void returnItem(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equals(id)) {
                item.setAvailable(true);
                return;
            }
        }
        throw new IllegalArgumentException("Item not found.");
    }

    public List<LibraryItem> getOverdueItems() {
        List<LibraryItem> overdueItems = new ArrayList<>();
        for (LibraryItem item : items) {
            if (!item.isAvailable()) {
                overdueItems.add(item);
            }
        }
        return overdueItems;
    }
}
