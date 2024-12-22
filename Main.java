import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Percabangan - Validasi login dengan if-else
        if (!login(scanner)) {
            System.out.println("Akses ditolak. Program dihentikan.");
            return;
        }

        Bank bank = new Bank(); //Objek - Membuat objek dari class Bank

        while (true) { //Perulangan - Menggunakan while untuk menu utama
            System.out.println("\n=== Sistem Manajemen Nasabah ===");
            System.out.println("1. Tambah Nasabah");
            System.out.println("2. Lihat Daftar Nasabah");
            System.out.println("3. Edit Data Nasabah");
            System.out.println("4. Hapus Nasabah");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {//Percabangan - Switch-case untuk menangani pilihan menu
                case 1 -> {
                    System.out.print("Masukkan ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan No Rekening: ");
                    String noRekening = scanner.nextLine();
                    System.out.print("Masukkan Saldo: ");
                    double saldo = scanner.nextDouble();
                    bank.createNasabah(id, nama, noRekening, saldo);
                }
                case 2 -> bank.readNasabah();
                case 3 -> {
                    System.out.print("Masukkan ID Nasabah yang ingin diedit: ");
                    String idEdit = scanner.nextLine();
                    System.out.print("Masukkan Nama Baru: ");
                    String namaBaru = scanner.nextLine();
                    bank.updateNasabah(idEdit, namaBaru);
                }
                case 4 -> {
                    System.out.print("Masukkan ID Nasabah yang ingin dihapus: ");
                    String idHapus = scanner.nextLine();
                    bank.deleteNasabah(idHapus);
                }
                case 5 -> {
                    bank.closeConnection();
                    System.out.println("Keluar dari program.");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
            //Percabangan - Switch-case untuk menangani pilihan menu
                    }
    }

    //Percabangan - Validasi login dengan if-else
    private static boolean login(Scanner scanner) {
        String username = "admin1";
        String password = "5678";

        for (int i = 0; i < 3; i++) { //Perulangan - Menggunakan for-loop untuk batas login
            System.out.println("\n=== Log In ===");
            System.out.print("Username: ");
            String inputUser = scanner.nextLine().trim(); //Manipulasi String - Menggunakan trim()
            System.out.print("Password: ");
            String inputPass = scanner.nextLine().trim();

            // Validasi Captcha
            String captchaGenerated = generateCaptcha();
            System.out.print("Captcha  : " + captchaGenerated + "\nMasukkan Captcha : ");
            String captchaInput = scanner.nextLine().trim();

            if (inputUser.equalsIgnoreCase(username) && inputPass.equals(password) && captchaInput.equals(captchaGenerated)) {
                System.out.println("Login berhasil!");
                return true;
            } else {
                System.out.println("Login gagal. Silakan coba lagi.");
            }
        }
        return false;
    }

    //Manipulasi String - Membuat captcha dengan StringBuilder
    private static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {//  Perulangan - Menggunakan for-loop untuk karakter captcha
            int index = (int) (Math.random() * characters.length()); 
            captcha.append(characters.charAt(index));
        }
        return captcha.toString();
    }
}
