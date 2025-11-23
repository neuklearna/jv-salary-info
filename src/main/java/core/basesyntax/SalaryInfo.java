package core.basesyntax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOURLY_PAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salaries = new int[names.length];

        // PĘTLA 1: Przetwarzanie danych
        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            LocalDate workDate = LocalDate.parse(parts[DATE_INDEX], DATE_FORMATTER);

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                int hours = Integer.parseInt(parts[HOURS_INDEX]);
                int hourlyPay = Integer.parseInt(parts[HOURLY_PAY_INDEX]);
                int salary = hours * hourlyPay;
                String employeeName = parts[NAME_INDEX];

                int index = findNameIndex(names, employeeName);
                if (index != -1) {
                    salaries[index] += salary;
                }
            }
        }

        // PĘTLA 2: Budowanie raportu
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i])
                    .append(System.lineSeparator());
        }

        return result.toString().trim();
    }


    private int findNameIndex(String[] names, String name) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }
}
