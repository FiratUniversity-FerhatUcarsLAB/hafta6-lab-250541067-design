/**
* Ad Soyad: [Avşin Pelin Bilgiç]
* Numara: [250541067]
* Proje: [Sinema Bileti]
* Tarih: [25.11.2025]
*/
import java.util.Scanner;

public class SinemaBiletSistemi {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7; // Cumartesi (6), Pazar (7)
    }

    // 2) Matine mi?
    public static boolean isMatinee(int saat) {
        return saat < 12; // 12:00 öncesi matine
    }

    // 3) Temel fiyat hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend) { // Hafta içi
            if (matinee) return 45;
            else return 65;
        } else { // Hafta sonu
            if (matinee) return 55;
            else return 85;
        }
    }

    // 4) İndirim hesaplama
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirimOrani = 0;

        // Yaşa bağlı indirimler
        if (yas >= 65) {
            indirimOrani = 0.30;
        } else if (yas < 12) {
            indirimOrani = 0.25;
        }

        // Meslek indirimleri
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) indirimOrani = Math.max(indirimOrani, 0.20);
                else indirimOrani = Math.max(indirimOrani, 0.15);
                break;

            case 2: // Öğretmen
                if (gun == 3) indirimOrani = Math.max(indirimOrani, 0.35);
                break;

            case 3:
                // Diğer → indirim yok
                break;
        }

        return indirimOrani;
    }

    // 5) Ekstra format ücreti
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;   // 2D
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;
        }
    }

    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double discountRate = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);

        double indirimli = base - (base * discountRate);
        return indirimli + extra;
    }

    // 7) Bilet bilgisi oluşturma
    public static String generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double indirim = calculateDiscount(yas, meslek, gun) * base;
        double extra = getFormatExtra(filmTuru);
        double toplam = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        return  "\n--- BİLET BİLGİSİ ---\n" +
                "Temel fiyat: " + base + " TL\n" +
                "İndirim: -" + indirim + " TL\n" +
                "Format ekstra: +" + extra + " TL\n" +
                "Toplam fiyat: " + toplam + " TL\n";
    }

    // ------------------------------------------------------
    // ANA PROGRAM
    // ------------------------------------------------------
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Gün (1=Pzt ... 7=Paz): ");
        int gun = input.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = input.nextInt();

        System.out.print("Yaş: ");
        int yas = input.nextInt();

        System.out.print("Meslek (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = input.nextInt();

        System.out.print("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = input.nextInt();

        String bilet = generateTicketInfo(gun, saat, yas, meslek, filmTuru);
        System.out.println(bilet);

        input.close();
    }
}
