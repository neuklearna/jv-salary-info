package core.basesyntax;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        // ========== KROK 1: PARSOWANIE DATY POCZĄTKOWEJ ==========
        // Mamy String "01.04.2019" i chcemy go rozłożyć na części

        String[] partsDateFrom = dateFrom.split("\\.");
        // split("\\.") dzieli String po kropkach
        // Dostajemy tablicę: ["01", "04", "2019"]

        int dayFrom = Integer.parseInt(partsDateFrom[0]);     // "01" → 1
        int monthFrom = Integer.parseInt(partsDateFrom[1]);   // "04" → 4
        int yearFrom = Integer.parseInt(partsDateFrom[2]);    // "2019" → 2019
        // parseInt() zamienia String na int (liczbę)


        // ========== KROK 2: PARSOWANIE DATY KOŃCOWEJ ==========
        // To samo co wyżej, ale dla daty końcowej "30.04.2019"

        String[] partsDateTo = dateTo.split("\\.");
        int dayTo = Integer.parseInt(partsDateTo[0]);         // "30" → 30
        int monthTo = Integer.parseInt(partsDateTo[1]);       // "04" → 4
        int yearTo = Integer.parseInt(partsDateTo[2]);        // "2019" → 2019


        // ========== KROK 3: PRZYGOTOWANIE TABLICY NA WYNAGRODZENIA ==========
        // Tworzymy tablicę int[] o takiej samej długości jak names[]
        // Na początku wszystkie wartości = 0
        // Przykład: jeśli names = ["John", "Andrew", "Kate"]
        // to salaries = [0, 0, 0]

        int[] salaries = new int[names.length];


        // ========== KROK 4: ZAMIANA DAT NA LICZBY (żeby łatwo porównywać) ==========
        // Zamiast porównywać "01.04.2019" jako String (trudne!)
        // zamieniamy na jedną liczbę: 20190401 (łatwe!)
        // Dlaczego? Bo 20190426 > 20190401 (łatwe porównanie operatorem >)

        int dateFromAsNumber = yearFrom * 10000 + monthFrom * 100 + dayFrom;
        // 2019 * 10000 = 20190000
        // 4 * 100 = 400
        // 1 = 1
        // Razem: 20190401

        int dateToAsNumber = yearTo * 10000 + monthTo * 100 + dayTo;
        // 2019 * 10000 = 20190000
        // 4 * 100 = 400
        // 30 = 30
        // Razem: 20190430


        // ========== KROK 5: GŁÓWNA PĘTLA - przetwarzamy każdą linię z data[] ==========
        // data[] to tablica np:
        // ["26.04.2019 John 4 50", "05.04.2019 Andrew 3 200", ...]
        // Musimy przejść przez KAŻDĄ linię i ją przeanalizować

        for (int i = 0; i < data.length; i++) {

            // Bierzemy jedną linię, np. "26.04.2019 John 4 50"
            String jednaLinia = data[i];

            // Dzielimy ją po spacjach
            String[] parts = jednaLinia.split(" ");
            // Dostajemy tablicę: ["26.04.2019", "John", "4", "50"]
            // parts[0] = "26.04.2019" (data pracy)
            // parts[1] = "John" (imię pracownika)
            // parts[2] = "4" (ile godzin pracował)
            // parts[3] = "50" (ile zarabia za godzinę)


            // ========== KROK 6: PARSOWANIE DATY Z LINII ==========
            // Bierzemy datę z parts[0], np. "26.04.2019"

            String dataZLinii = parts[0];

            // Dzielimy ją po kropkach
            String[] partsData = dataZLinii.split("\\.");
            // Dostajemy: ["26", "04", "2019"]

            int day = Integer.parseInt(partsData[0]);     // 26
            int month = Integer.parseInt(partsData[1]);   // 4
            int year = Integer.parseInt(partsData[2]);    // 2019

            // Zamieniamy na jedną liczbę (tak jak w kroku 4)
            int dataAsNumber = year * 10000 + month * 100 + day;
            // 2019 * 10000 + 4 * 100 + 26 = 20190426


            // ========== KROK 7: PARSOWANIE GODZIN I STAWKI ==========
            // Wyciągamy godziny i stawkę z parts[] i zamieniamy na int

            int hours = Integer.parseInt(parts[2]);      // "4" → 4 godziny
            int hourlyPay = Integer.parseInt(parts[3]);  // "50" → 50 zł/h


            // ========== KROK 8: SPRAWDZENIE CZY DATA JEST W ZAKRESIE ==========
            // Sprawdzamy: czy dataAsNumber jest między dateFromAsNumber a dateToAsNumber?
            // Przykład: czy 20190426 jest między 20190401 a 20190430? TAK!

            if (dataAsNumber >= dateFromAsNumber && dataAsNumber <= dateToAsNumber) {

                // ========== KROK 9: OBLICZENIE ZAROBKU ==========
                // Data jest w zakresie, więc liczymy ile osoba zarobiła tego dnia

                int salary = hours * hourlyPay;
                // 4 godziny * 50 zł = 200 zł zarobił tego dnia


                // ========== KROK 10: ZNALEZIENIE INDEKSU OSOBY W TABLICY names[] ==========
                // Musimy znaleźć GDZIE w tablicy names[] jest "John"
                // Czy to names[0], names[1], czy names[2]?
                // Przechodzimy przez całą tablicę names[] i szukamy

                for (int a = 0; a < names.length; a++) {

                    // Porównujemy: czy names[a] to ta sama osoba co parts[1]?
                    // .equals() służy do porównywania Stringów (NIE używamy ==!)

                    if (names[a].equals(parts[1])) {
                        // Znaleźliśmy! Np. names[0] = "John" i parts[1] = "John"

                        // Dodajemy zarobek do salaries[a]
                        // Jeśli John miał już 700 zł, a zarobił 200 zł
                        // to teraz będzie miał 900 zł
                        salaries[a] = salaries[a] + salary;
                        // można też krócej: salaries[a] += salary;
                    }
                }
            }
        }
        // Koniec głównej pętli - przetworzyliśmy wszystkie linie!


        // ========== KROK 11: BUDOWANIE WYNIKU (RAPORTU) ==========
        // StringBuilder to narzędzie do łączenia wielu Stringów
        // (lepsze niż zwykłe + dla wielu operacji)

        StringBuilder result = new StringBuilder();

        // Dodajemy pierwszą linię: "Report for period 01.04.2019 - 30.04.2019"
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");  // \n to nowa linia (Enter)


        // ========== KROK 12: DODAWANIE WYNAGRODZEŃ DO RAPORTU ==========
        // Przechodzimy przez wszystkie osoby i dodajemy ich wynagrodzenia

        for (int b = 0; b < salaries.length; b++) {
            // Dla każdej osoby dodajemy linię: "John - 900"
            result.append(names[b])        // Imię
                    .append(" - ")           // Myślnik
                    .append(salaries[b])     // Wynagrodzenie
                    .append("\n");           // Nowa linia
        }

        // ========== KROK 13: ZWRACANIE WYNIKU ==========
        // StringBuilder.toString() zamienia StringBuilder na zwykły String
        // i zwracamy go jako wynik metody

        return result.toString();
    }
}
