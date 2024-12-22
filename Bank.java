import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Bank implements NasabahOperations {
    private Connection connection;
    private List<Nasabah> nasabahList; // Menggunakan ArrayList untuk menyimpan data nasabah sementara

    // Konstruktor untuk menginisialisasi koneksi ke database dan ArrayList
    public Bank() {
        connectToDatabase();
        nasabahList = new ArrayList<>(); // Inisialisasi ArrayList
        loadNasabahFromDatabase(); // Muat data nasabah dari database ke ArrayList
    }

    // Method untuk menghubungkan ke database MySQL
    private void connectToDatabase() {//Menggunakan blok try-catch
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "");
            System.out.println("Koneksi database berhasil.");
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e.getMessage());
            System.exit(1); // Keluar jika koneksi gagal
        }
    }

    // Method untuk memuat data nasabah dari database ke ArrayList
    private void loadNasabahFromDatabase() { //menggunakan try-catch
        String sql = "SELECT * FROM nasabah";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nama = resultSet.getString("nama");
                String noRekening = resultSet.getString("no_rekening");
                double saldo = resultSet.getDouble("saldo");

                Nasabah nasabah = new Nasabah(id, nama, noRekening, saldo);
                nasabahList.add(nasabah); // Tambahkan ke ArrayList
            }
            System.out.println("Data nasabah berhasil dimuat ke memori.");
        } catch (SQLException e) {
            System.out.println("Gagal memuat data nasabah: " + e.getMessage());
        }
    }

    // Fungsi CREATE - Menambahkan data nasabah baru ke database dan ArrayList
    @Override
    public void createNasabah(String id, String nama, String noRekening, double saldo) {
        LocalDate dateCreated = LocalDate.now();
        String formattedDate = dateCreated.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //manipulasiDate
        String sql = "INSERT INTO nasabah (id, nama, no_rekening, saldo, tanggal_pembuatan) VALUES (?, ?, ?, ?, ?)";

        //menggunakan try-catch
        try (PreparedStatement statement = connection.prepareStatement(sql)) { //Manipulasi String dengan setString pada PreparedStatement:
            statement.setString(1, id);
            statement.setString(2, nama);
            statement.setString(3, noRekening);
            statement.setDouble(4, saldo);
            statement.setString(5, formattedDate);//manipulasidate
            statement.executeUpdate();

            // Tambahkan juga ke ArrayList
            nasabahList.add(new Nasabah(id, nama, noRekening, saldo));
            System.out.println("Nasabah dengan ID " + id + " berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Gagal menambahkan nasabah: " + e.getMessage());
        }
    }

    // Fungsi READ - Membaca data dari ArrayList
    @Override
    public void readNasabah() {
        if (nasabahList.isEmpty()) {
            System.out.println("Tidak ada nasabah yang terdaftar.");
            return;
        }

        System.out.println("\n=== Daftar Nasabah ===");
        for (Nasabah nasabah : nasabahList) {
            nasabah.displayNasabah();
        }
    }

    // Fungsi UPDATE - Memperbarui data nasabah di database dan ArrayList
    @Override
    public void updateNasabah(String id, String namaBaru) {
        String sql = "UPDATE nasabah SET nama = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, namaBaru);
            statement.setString(2, id);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // Update juga di ArrayList
                for (Nasabah nasabah : nasabahList) {
                    if (nasabah.getId().equals(id)) {
                        nasabah.setNama(namaBaru);
                        break;
                    }
                }
                System.out.println("Data nasabah dengan ID " + id + " berhasil diperbarui.");
            } else {
                System.out.println("Nasabah dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data nasabah: " + e.getMessage());
        }
    }

    // Fungsi DELETE - Menghapus nasabah dari database dan ArrayList
    @Override
    public void deleteNasabah(String id) {
        String sql = "DELETE FROM nasabah WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                // Hapus juga dari ArrayList
                nasabahList.removeIf(nasabah -> nasabah.getId().equals(id));
                System.out.println("Nasabah dengan ID " + id + " berhasil dihapus.");
            } else {
                System.out.println("Nasabah dengan ID " + id + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data nasabah: " + e.getMessage());
        }
    }

    // Menutup koneksi database
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.out.println("Gagal menutup koneksi database: " + e.getMessage());
        }
    }
}
