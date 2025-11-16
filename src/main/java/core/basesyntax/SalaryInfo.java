package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMATTER);

        int[] salaries = new int[names.length];

        for (int i = 0; i < data.length; i++) {
            String[] parts = data[i].split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], DATE_FORMATTER);

            if ((workDate.isAfter(startDate) || workDate.isEqual(startDate))
                    && (workDate.isBefore(endDate) || workDate.isEqual(endDate))) {
                int hours = Integer.parseInt(parts[2]);
                int hourlyPay = Integer.parseInt(parts[3]);
                int salary = hours * hourlyPay;
                String employeeName = parts[1];

                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(employeeName)) {
                        salaries[j] += salary;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            result.append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }

        return result.toString();
    }
}
