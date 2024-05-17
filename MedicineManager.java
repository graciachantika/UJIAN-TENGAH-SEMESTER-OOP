package apotekmanagementsystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MedicineManager {
    private Scanner scanner;
    private Medicine[] medicines;
    private int medicineCount;
    private final int MAX_MEDICINES = 100;

    public MedicineManager(Scanner scanner) {
        this.scanner = scanner;
        this.medicines = new Medicine[MAX_MEDICINES];
        this.medicineCount = 0;
    }

    public void addMedicine() {
        if (medicineCount >= MAX_MEDICINES) {
            System.out.println("Maaf, batas maksimum obat telah tercapai.");
            return;
        }

        System.out.print("Masukkan nama obat: ");
        String medicineName = scanner.nextLine();

        double price = getValidDoubleInput("Masukkan harga obat (IDR): ");
        if (price == -1.0) return;

        System.out.print("Masukkan tanggal kadaluarsa obat (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine();

        int stock = getValidIntInput("Masukkan stok obat: ");
        if (stock == -1) return;

        medicines[medicineCount++] = new Medicine(medicineName, price, expiryDate, stock);
        System.out.println("Obat berhasil ditambahkan!");
    }

    public void displayMedicines() {
        if (medicineCount == 0) {
            System.out.println("Belum ada obat yang tersedia.");
            return;
        }

        System.out.println("==========================================================");
        System.out.println("|                    Daftar Obat                          |");
        System.out.println("==========================================================");
        System.out.printf("| %-3s | %-20s | %-12s | %-15s | %-5s |\n", "No", "Nama Obat", "Harga", "Kadaluarsa", "Stok");
        System.out.println("==========================================================");

        for (int i = 0; i < medicineCount; i++) {
            System.out.printf("| %-3d | %-20s | %-12s | %-15s | %-5d |\n",
                (i + 1),
                medicines[i].getMedicineName(),
                formatRupiah(medicines[i].getPrice()),
                medicines[i].getExpiryDate(),
                medicines[i].getStock()
            );
            System.out.println("----------------------------------------------------------");
        }
    }

    public void updateMedicine() {
        if (medicineCount == 0) {
            System.out.println("Belum ada obat yang tersedia untuk diperbarui.");
            return;
        }

        displayMedicines();
        int index = getValidIndex("Masukkan nomor obat yang ingin diperbarui: ");
        if (index == -1) return;

        Medicine medicine = medicines[index - 1];
        System.out.println("Obat yang akan diperbarui:");
        System.out.println(medicine);

        System.out.print("Masukkan nama obat baru (kosongkan jika tidak ingin mengubah): ");
        String newMedicineName = scanner.nextLine();
        if (!newMedicineName.isEmpty()) {
            medicine.setMedicineName(newMedicineName);
        }

        double newPrice = getValidDoubleInput("Masukkan harga obat baru (IDR) (kosongkan jika tidak ingin mengubah): ");
        if (newPrice != -1.0) {
            medicine.setPrice(newPrice);
        }

        System.out.print("Masukkan tanggal kadaluarsa obat baru (YYYY-MM-DD) (kosongkan jika tidak ingin mengubah): ");
        String newExpiryDate = scanner.nextLine();
        if (!newExpiryDate.isEmpty()) {
            medicine.setExpiryDate(newExpiryDate);
        }

        int newStock = getValidIntInput("Masukkan stok obat baru (kosongkan jika tidak ingin mengubah): ");
        if (newStock != -1) {
            medicine.setStock(newStock);
        }

        System.out.println("Obat berhasil diperbarui!");
    }

    public void deleteMedicine() {
        if (medicineCount == 0) {
            System.out.println("Belum ada obat yang tersedia untuk dihapus.");
            return;
        }

        displayMedicines();
        int index = getValidIndex("Masukkan nomor obat yang ingin dihapus: ");
        if (index == -1) return;

        for (int i = index - 1; i < medicineCount - 1; i++) {
            medicines[i] = medicines[i + 1];
        }
        medicineCount--;
        System.out.println("Obat berhasil dihapus!");
    }

    private int getValidIndex(String message) {
        int index = -1;
        boolean validIndex = false;
        while (!validIndex) {
            try {
                System.out.print(message);
                index = scanner.nextInt();
                scanner.nextLine();
                if (index >= 1 && index <= medicineCount) {
                    validIndex = true;
                } else {
                    System.out.println("Nomor obat tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Mohon masukkan nomor obat dalam bentuk angka.");
                scanner.nextLine();
            }
        }
        return index;
    }

    private double getValidDoubleInput(String message) {
        double value = -1.0;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(message);
                value = scanner.nextDouble();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mohon masukkan harga obat dalam format yang benar.");
                scanner.nextLine();
            }
        }
        return value;
    }

    private int getValidIntInput(String message) {
        int value = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print(message);
                value = scanner.nextInt();
                scanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Mohon masukkan stok obat dalam angka.");
                scanner.nextLine();
            }
        }
        return value;
    }

    private String formatRupiah(double value) {
        return String.format("Rp%,.2f", value).replace(',', '.');
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (medicineCount == 0) {
            sb.append("Belum ada obat yang tersedia.");
        } else {
            sb.append("=== Daftar Obat ===\n");
            for (int i = 0; i < medicineCount; i++) {
                sb.append((i + 1)).append(". ").append(medicines[i]).append("\n");
            }
        }
        return sb.toString();
    }

    public void displayMedicines(int startIndex, int endIndex) {
        if (medicineCount == 0) {
            System.out.println("Belum ada obat yang tersedia.");
            return;
        }

        System.out.println("=== Daftar Obat ===");
        for (int i = startIndex - 1; i < endIndex && i < medicineCount; i++) {
            System.out.println((i + 1) + ". " + medicines[i]);
        }
    }
}
