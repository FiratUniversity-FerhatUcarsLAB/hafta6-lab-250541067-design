/**
* Ad Soyad: [Avşin Pelin Bilgiç]
* Numara: [250541067]
* Proje: [Restoran Sipariş]
* Tarih: [25.11.2025]
*/
import java.util.Scanner;

public class siparisSistemi {

    // 1) Ana yemek fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;   // 0 → Alınmadı
        }
    }

    // 3) İçecek fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Taze Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo menü kontrolü
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy Hour kontrolü
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci,
                                          boolean happyHour, double icecekFiyati,
                                          int gun) {

        double toplamIndirim = 0;

        // Combo indirimi (%15)
        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        // Happy hour → sadece içecek %20 indirim
        if (happyHour && icecekFiyati > 0) {
            toplamIndirim += icecekFiyati * 0.20;
        }

        // Öğrenci indirimi (hafta içi %10)
        if (ogrenci) {
            if (gun >= 1 && gun <= 5) {
                toplamIndirim += (tutar - toplamIndirim) * 0.10;  
            }
        }

        // 200 TL üzeri ek %10
        if (tutar - toplamIndirim > 200) {
            toplamIndirim += (tutar - toplamIndirim) * 0.10;
        }

        return toplamIndirim;
    }

    // 8) Bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

    // -------------------------------------------------------------------
    // ANA PROGRAM
    // -------------------------------------------------------------------
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Ana Yemek (1-4, yoksa 0): ");
        int a = input.nextInt();

        System.out.print("Başlangıç (0-3): ");
        int b = input.nextInt();

        System.out.print("İçecek (0-4): ");
        int c = input.nextInt();

        System.out.print("Tatlı (0-3): ");
        int d = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Öğrenci misiniz? (E/H): ");
        boolean ogrenci = input.next().equalsIgnoreCase("E");

        System.out.print("Hangi gün? (1=Pzt … 7=Paz): ");
        int gun = input.nextInt();

        // Fiyat hesaplama
        double ana = getMainDishPrice(a);
        double bas = getAppetizerPrice(b);
        double icecek = getDrinkPrice(c);
        double tatli = getDessertPrice(d);

        double araToplam = ana + bas + icecek + tatli;

        boolean combo = isComboOrder(ana > 0, icecek > 0, tatli > 0);
        boolean happyHour = isHappyHour(saat);

        double indirim = calculateDiscount(araToplam, combo, ogrenci, happyHour, icecek, gun);
        double toplam = araToplam - indirim;

        double bahsis = calculateServiceTip(toplam);

        // ÇIKTI
        System.out.println("\n--- SİPARİŞ ÖZETİ ---");
        System.out.println("Ara Toplam: " + araToplam + " TL");
        System.out.println("Toplam İndirim: -" + String.format("%.2f", indirim) + " TL");
        System.out.println("Ödenecek Tutar: " + String.format("%.2f", toplam) + " TL");
        System.out.println("Bahşiş Önerisi (%10): " + String.format("%.2f", bahsis) + " TL");

        input.close();
    }
}
