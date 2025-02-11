# Final Proyek Pemrograman Berorientasi Obyek 1
- Mata Kuliah: Pemrograman Berorientasi Obyek 1
 - Dosen Pengampu: Muhammad Ikhwan Fathulloh
## Kelompok
- Kelompok: 9
- Proyek: Aplikasi Booking Gor
- Anggota:
  - Ketua    : Luthfy Arief
  - Anggota 1: Fauzi Rikhshana
  - Anggota 2: Wildam Pramudiya Alif 
## Judul Studi Kasus
Studi Kasus Pembuatan Aplikasi Booking GOR untuk Meningkatkan Efisiensi Pengelolaan GOR

## Penjelasan Studi Kasus
Dalam studi kasus ini, kita akan membuat aplikasi Booking GOR untuk mempermudah pengelolaan gor dan proses pembookingan yang didalamnya ada 2 user, yaitu admin dan user(si pembooking), pada menu admin akan ada proses login terlebih dahulu, dimana dimenu login, admin akan mengisi username dan pasword yang telah kita buat dan disimpan di table Users pada database coba_gor. Setellah admin memasukkan username dan pasword dengan benar maka akan beralih ke menu selanjutnya, dimenu selanjutnya akan ada 4 menu, yaitu:
1. Kelola Jadwal
   Pada menu ini, memungkinkan admin untuk melihat jadwal pembooking yangstatusnya booking dalam artian tahap pemesanan yang sedang berlangsung.Di sini admin bisa 
   menyelesaikan booking jika pembooking sudah melawati/sudah selesai waktu bookingnya, yang kemudian data tersebut akan hilang dan berpindah ke riwayat booking.
2. Riwayat Booking
   Pada menu ini hanya akan ada data para pembooking sebelumnya yang telah selesai melakukan booking, yang artinya statusnya selesai.
3. Laporan Transaksi
   Pada menu ini, admin dapat melihat laporan transaksi semua pembooking, baik yang statusnya booking maupun yang statusnya selesai.
4. Logout
   Pada menu ini, setelah admin sudah selesai melakukan peninjauan bookingan gor, maka menu ini akan mengembalikan ke tampilan awal yaitu tampilan pilih admin atau user.
   
Pada menu User(si pembooking) tidak ada perintah untuk memasukkan username dan pasword, di tampilan awal menu ini hanya akan memerintahkan si pembooking untuk memasukkan 
nama, alamat dan nomor hp untuk syarat loginnya, setelah datanya telah terisi semua, pembooking akan menekan tombol login dan menuju ke menu selanjutnya. Pada menu ini 
akan ada 5 opsi pemilihan booking dan 1 tombol untuk melihat jadwal booking yang sudah ada.
   
1. Tanggal
   Pada opsi ini, pembooking akan diminta untuk memilih tanggal booking yang sesuai setelah melihat jadwal booking yang sedang berlangsung.
2. Jam Mulai
   Pada opsi ini, pembooking diminta untuk memilih jam mulai bookingnya kapan.
3. Durasi
   Setelah memilih jam mulainya, pembooking memilih durasi yang maksimalnya 4 jam. setelah memilih durasinya berapa, maka sistem akan otomatis melakukan
   penambahan antara jam mulai dengan durasinya yang akan menjadikan jam selesai melakukan pembookingannya.
4. Harga
   Sebenarnya ini bukan merupakan sebuah opsi, melainkan hanya informasi harga booking per satu jamnya, yang nantinya sistem akan melakukan penghitungan sesuai dengan durasi 
   yang dipilih.
5. Pembayaran
   Pada opsi ini, pembooking akan memilih proses pembayarannya melalui apa, apakah transfer bank, gopay, ovo, ataupun cash, yang disitu sudah ada keterangan nomor rekening, 
   nomor ovo dan gopay.

Setelah semua opsi sudah dipilih, maka si pembooking tinggal menekan tombol booking yang nantinya akan muncul notifikasi mengenai data bookingnya beserta jumlah harga bookingnya. di situ juga akan muncul pesan bahwa jika melakukan pembayaran maka bukti pembayarannya dikirim melalui WA yang tertera, dan jika melakukan pembayaran secara cash maka disitu juga muncul pesan untuk menyimpan data bookingnya yang bertujuan untuk bukti sudah melakukan booking.

## Penjelasan 4 Pilar OOP dalam Studi Kasus
#### 1. Inheritance
- ##### Definisi
  Inheritance adalah konsep di mana sebuah kelas (subclass) dapat mewarisi atribut dan method dari kelas lain (superclass). Dengan cara ini, kita bisa menulis satu bagian kode yang bisa dipakai berulang kali (reusable) di banyak tempat, dan juga membuat hubungan antar kelas yang jelas, misalnya mana kelas yang jadi 'induk' dan mana yang jadi 'turunan'. Sehingga, kode kita jadi lebih rapi dan mudah dipahami.
- ##### Penerapan pada Studi Kasus:
  - ##### Pewarisan dari JFrame:
    Kelas LoginFrame, LoginFrameUser, dan MenuAdmin semuanya me-extend dari JFrame. Ini berarti mereka mewarisi properti dan method dasar dari JFrame (seperti setSize(), 
    setVisible(), dll.) tanpa perlu mendefinisikannya ulang.
  - ##### Kelas Dasar untuk Login (BaseLoginFrame):
    BaseLoginFrame menjadi kelas dasar abstrak yang menyediakan pengaturan umum untuk tampilan login. Kemudian, LoginFrame dan LoginFrameUser mewarisi dan 
    mengimplementasikan detail spesifiknya sendiri.
- ##### Fungsi
  ###### Pengurangan Duplikasi Kode:
  Dengan menggunakan inheritance, kode-kode pengaturan frame (seperti pengaturan ukuran, lokasi, layout) tidak perlu dituliskan ulang di setiap kelas, sehingga membuat kode 
  lebih ringkas dan mudah dirawat.

#### 2. Encapsulation
- ##### Definisi
  Enkapsulasi adalah proses menyembunyikan detail implementasi internal sebuah kelas dan hanya menyediakan antarmuka (interface) yang terbatas kepada pengguna kelas 
  tersebut. Hal ini membantu menjaga integritas data dan mencegah akses langsung ke variabel internal.
- ##### Penerapan pada Studi Kasus:
  - ##### Privatisasi Data:
 
    Sebagian besar atribut di kelas seperti MenuAdmin, LoginFrame, dan LoginFrameUser dideklarasikan dengan access modifier private. Contohnya, variabel-variabel seperti 
    panel, tabel, dan field input (misalnya KolomUsername, KolomPassword, dsb.) disembunyikan dari kelas lain.
  - ##### Metode Pengakses (Getter/Setter) dan Metode Khusus:
    Logika untuk memanipulasi data (seperti memuat data dari database) ditempatkan dalam method khusus (misalnya, loadDataKelola(), completeBooking()) sehingga pengguna 
    kelas tidak perlu mengetahui detail query SQL atau manipulasi data internal.
- ##### Fungsi
  ###### Keamanan dan Konsistensi:
  Dengan enkapsulasi, perubahan pada cara data disimpan atau diolah hanya perlu dilakukan di dalam kelas tersebut tanpa mempengaruhi bagian lain dari program.

#### 3. Polymorphism
- ##### Definisi

  Polimorfisme memungkinkan objek-objek dari kelas yang berbeda, namun memiliki hubungan pewarisan, untuk diperlakukan secara seragam sebagai objek dari kelas induk. Dengan 
  cara ini, pemanggilan method pada objek tersebut akan mengeksekusi implementasi yang sesuai dengan tipe objek aktual (runtime).
- ##### Penerapan pada Studi Kasus:
  - ##### Penggunaan Referensi Tipe Dasar:
  
    Karena LoginFrame dan LoginFrameUser merupakan subclass dari BaseLoginFrame, maka dapat mendeklarasikan variabel dengan tipe JFrame atau BaseLoginFrame dan 
    menginisialisasinya dengan objek dari salah satu kelas tersebut. Misalnya:
   JFrame loginFrame;
   if (userIsAdmin) {
    loginFrame = new LoginFrame();
   } else {
    loginFrame = new LoginFrameUser();
   }
   loginFrame.setVisible(true);
   Di sini, variabel loginFrame dapat menunjuk ke objek dari kelas yang berbeda, dan pemanggilan method seperti setVisible(true) tetap valid.

  - ##### Method Overriding:
    Jika terdapat method yang di-override di masing-masing subclass (misalnya, jika BaseLoginFrame memiliki method abstrak initComponents()), maka ketika method tersebut 
    dipanggil melalui referensi tipe dasar, implementasi yang dieksekusi adalah yang ada di kelas aktual (LoginFrame atau LoginFrameUser).
- ##### Fungsi
  ###### Fleksibilitas dalam Ekstensi:
  Polimorfisme membuat sistem lebih fleksibel, karena dapat menambahkan kelas login baru di masa depan (misalnya, LoginFrameCustomer) dan memperlakukannya secara seragam 
  melalui referensi ke tipe dasar, tanpa mengubah kode logika yang sudah ada.


#### 4. Abstract
- ##### Definisi

  Abstraksi berarti menyederhanakan kompleksitas dengan hanya menampilkan detail yang relevan kepada pengguna, dan menyembunyikan detail implementasi yang rumit.
- ##### Penerapan pada Studi Kasus:
  - ##### Kelas Dasar Abstrak (BaseLoginFrame):
 
    BaseLoginFrame dibuat sebagai kelas abstrak yang menyediakan pengaturan dasar (ukuran, layout, dll.) dan mendefinisikan method abstrak seperti initComponents(). Kelas 
    ini menyembunyikan detail inisialisasi frame sehingga subclass (seperti LoginFrame dan LoginFrameUser) hanya perlu mengimplementasikan komponen spesifik mereka.
  - ##### Method Abstraksi:
    Metode seperti loadDataKelola(), loadDataTransaksi(), dan loadDataRiwayat() dalam kelas MenuAdmin mengabstraksikan detail pengambilan data dari database. Pengguna 
    method tersebut tidak perlu tahu bagaimana query SQL bekerja; cukup memanggil method untuk mendapatkan data yang diinginkan.
- ##### Fungsi
  ###### Pemisahan Tanggung Jawab:
  Dengan mengelompokkan fungsi-fungsi tertentu ke dalam method yang terpisah, program menjadi lebih mudah dipahami dan dirawat, karena setiap method menangani satu tugas 
  spesifik.

## Struktur Tabel Aplikasi
##### 1.Tabel Users (Menyimpan Data Admin dan User)
Digunakan untuk menyimpan data username dan password yang dibutuhkan saat melakukan login pada admin.

| Field  | Type  |  Null  |   Key  | Default | Extra |
| ------ |------ | ------ | ------ | ------  | ------ |
| username | varchar(50) | YES  |     | NULL    |     |
| password | varchar(50) | YES  |     | NULL    |     |

##### 2. Tabel pbooking (Menyimpan Data Booking User)
Digunakan untuk menyimpan informasi pemesanan GOR yang dilakukan oleh user(pembooking).

| Field  | Type          | Null | Key | Default | Extra|         
| ------ | ------ | ------ | ------ | ------ | ------ |
| id_booking       | int(11)       | NO   | PRI | NULL    | auto_increment |
| Nama             | varchar(50)   | YES  |     | NULL    |                |
| Alamat           | varchar(50)   | YES  |     | NULL    |                |
| No_HP            | char(50)      | YES  |     | NULL    |                |
| tanggal          | varchar(50)   | YES  |     | NULL    |                |
| durasi           | varchar(50)   | YES  |     | NULL    |                |
| jam_mulai        | varchar(50)   | YES  |     | NULL    |                |
| jam_selesai      | varchar(50)   | YES  |     | NULL    |                |
| totalbiaya       | decimal(10,2) | YES  |     | NULL    |                |
| metodePembayaran | varchar(50)   | YES  |     | NULL    |                |
| status           | varchar(20)   | YES  |     | booking |                |


## Tampilan Aplikasi

![Deskripsi Gambar](https://i.imgur.com/dIozxrE.png)


## Demo Proyek
- Github: Github
- Youtube: Youtube
