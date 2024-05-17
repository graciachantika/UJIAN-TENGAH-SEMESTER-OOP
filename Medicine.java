package apotekmanagementsystem;

public class Medicine {
    private String medicineName;
    private double price;
    private String expiryDate;
    private int stock;

    public Medicine(String medicineName, double price, String expiryDate, int stock) {
        this.medicineName = medicineName;
        this.price = price;
        this.expiryDate = expiryDate;
        this.stock = stock;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Nama Obat: " + medicineName + "\n" +
               "Harga: " + price + "\n" +
               "Tanggal Kadaluarsa: " + expiryDate + "\n" +
               "Stok: " + stock;
    }
}
