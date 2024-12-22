public class NasabahPremium extends Nasabah {
    private final double cashback; // Cashback yang didapatkan oleh nasabah premium

    //Konstruktor untuk inisialisasi nasabah premium.
    public NasabahPremium(String id, String nama, String noRekening, double saldo) {
        super(id, nama, noRekening, saldo);
        this.cashback = saldo * 0.02; // Contoh perhitungan matematis untuk cashback 2% dari saldo
    }

    // Method untuk menampilkan data nasabah premium.
     
    public void displayNasabahPremium() {
        super.displayNasabah(); // Memanggil method displayNasabah dari kelas super
        System.out.println("Cashback: " + cashback); // Menampilkan jumlah cashback yang diterima nasabah premium
    }
}
