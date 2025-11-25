/**
* Ad Soyad: [Avşin Pelin Bilgiç]
* Numara: [250541067]
* Proje: [Not sistemi]
* Tarih: [25.11.2025]
*/


import java.util.Scanner;

public class notsistemi {

    // 1) Ortalama Hesaplama
    public static double calculateAverage(double vize, double finall, double odev) {
        return vize * 0.30 + finall * 0.40 + odev * 0.30;
    }

    // 2) Geçme-Kalma
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // 3) Harf Notu
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) return "AA";
        else if (ortalama >= 85) return "BA";
        else if (ortalama >= 80) return "BB";
        else if (ortalama >= 75) return "CB";
        else if (ortalama >= 65) return "CC";
        else if (ortalama >= 55) return "DC";
        else if (ortalama >= 50) return "DD";
        else if (ortalama >= 40) return "FD";
        else return "FF";
    }

    // 4) Onur Listesi: ort ≥85 ve tüm notlar ≥70
    public static boolean isHonorList(double ortalama, double vize, double finall, double odev) {
        return ortalama >= 85 && vize >= 70 && finall >= 70 && odev >= 70;
    }

    // 5) Bütünleme hakkı: 40 ≤ ort < 50
    public static boolean hasRetakeRight(double ortalama) {
        return ortalama >= 40 && ortalama < 50;
    }

    // ---------------------------------------------------------
    // ANA PROGRAM
    // ---------------------------------------------------------
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Vize Notu: ");
        double vize = scanner.nextDouble();

        System.out.print("Final Notu: ");
        double finall = scanner.nextDouble();

        System.out.print("Ödev Notu: ");
        double odev = scanner.nextDouble();

        double ortalama = calculateAverage(vize, finall, odev);

        System.out.println("\n--- NOT RAPORU ---");
        System.out.printf("Ortalama: %.2f\n", ortalama);
        System.out.println("Harf Notu: " + getLetterGrade(ortalama));
        System.out.println("Durum: " + (isPassingGrade(ortalama) ? "GEÇTİ" : "KALDI"));

        if (isHonorList(ortalama, vize, finall, odev)) {
            System.out.println("Öğrenci ONUR listesinde!");
        }

        if (hasRetakeRight(ortalama)) {
            System.out.println(" Öğrenci BÜTÜNLEME hakkına sahiptir.");
        }

        scanner.close();
    }
}

