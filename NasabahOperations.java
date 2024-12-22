interface NasabahOperations {
    // Interface untuk operasi nasabah bank
    
    /**
     * Buat nasabah baru.
     * @param id ID nasabah.
     * @param nama Nama nasabah.
     * @param noRekening Nomor rekening nasabah.
     * @param saldo Saldo awal nasabah.
     */
    void createNasabah(String id, String nama, String noRekening, double saldo);

    /**
     * Tampilkan daftar nasabah.
     */
    void readNasabah();

    /**
     * Perbarui data nasabah.
     * @param id ID nasabah yang ingin diedit.
     * @param namaBaru Nama baru untuk nasabah.
     */
    void updateNasabah(String id, String namaBaru);

    /**
     * Hapus nasabah berdasarkan ID.
     * @param id ID nasabah yang ingin dihapus.
     */
    void deleteNasabah(String id);
}
