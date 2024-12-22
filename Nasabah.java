public class Nasabah {
    private final String id;
    private String nama;
    private final String noRekening;
    private final double saldo; // Pastikan ini adalah double, bukan String

    // Konstruktor
    public Nasabah(String id, String nama, String noRekening, double saldo) {
        this.id = id;
        this.nama = nama;
        this.noRekening = noRekening;
        this.saldo = saldo;
    }

    // Getter untuk ID
    public String getId() {
        return id;
    }

    // Getter untuk Nama
    public String getNama() {
        return nama;
    }

    // Getter untuk Nomor Rekening
    public String getNoRekening() { 
        return noRekening;
    }

    // Getter untuk Saldo
    public double getSaldo() {
        return saldo;
    }

    // Setter untuk Nama
    public void setNama(String nama) {
        this.nama = nama;
    }

    // Metode untuk menampilkan informasi nasabah
    public void displayNasabah() {
        System.out.println("ID           : " + id);
        System.out.println("Nama         : " + nama);
        System.out.println("No Rekening  : " + noRekening);
        System.out.println("Saldo        : " + saldo);
        System.out.println("Status       : " + (saldo > 100000 ? "Premium" : "Reguler"));
        System.out.println("---------------------------");
    }
}
