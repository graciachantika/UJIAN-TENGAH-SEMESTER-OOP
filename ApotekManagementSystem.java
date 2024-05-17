package apotekmanagementsystem;

import java.util.Scanner;

public class ApotekManagementSystem {
    private MedicineManager medicineManager;

    public ApotekManagementSystem() {
        Scanner scanner = new Scanner(System.in);
        medicineManager = new MedicineManager(scanner);
    }

    public void run() {
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    medicineManager.addMedicine();
                    break;
                case 2:
                    medicineManager.displayMedicines();
                    break;
                case 3:
                    medicineManager.updateMedicine();
                    break;
                case 4:
                    medicineManager.deleteMedicine();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private void printMenu() {
        System.out.println("=================================");
        System.out.println("|   Sistem Manajemen Apotek     |");
        System.out.println("=================================");
        System.out.println("| 1. Tambah Obat                |");
        System.out.println("| 2. Tampilkan Daftar Obat      |");
        System.out.println("| 3. Perbarui Obat              |");
        System.out.println("| 4. Hapus Obat                 |");
        System.out.println("| 5. Keluar                     |");
        System.out.println("=================================");
        System.out.print("Pilih opsi: ");
    }

    public static void main(String[] args) {
        ApotekManagementSystem system = new ApotekManagementSystem();
        system.run();
    }
}

