package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        String[] partsDateFrom = dateFrom.split("\\.");
        int dayFrom = Integer.parseInt(partsDateFrom[0]);
        int monthFrom = Integer.parseInt(partsDateFrom[1]);
        int yearFrom = Integer.parseInt(partsDateFrom[2]);

        String[] partsDateTo = dateTo.split("\\.");
        int dayTo = Integer.parseInt(partsDateTo[0]);
        int monthTo = Integer.parseInt(partsDateTo[1]);
        int yearTo = Integer.parseInt(partsDateTo[2]);

        int[] salaries = new int[names.length];

        int dateFromAsNumber = yearFrom * 10000 + monthFrom * 100 + dayFrom;
        int dateToAsNumber = yearTo * 10000 + monthTo * 100 + dayTo;

        for (int i = 0; i < data.length; i++) {
            String jednaLinia = data[i];
            String[] parts = jednaLinia.split(" ");

            String dataZLinii = parts[0];
            String[] partsData = dataZLinii.split("\\.");
            int day = Integer.parseInt(partsData[0]);
            int month = Integer.parseInt(partsData[1]);
            int year = Integer.parseInt(partsData[2]);
            int dataAsNumber = year * 10000 + month * 100 + day;

            int hours = Integer.parseInt(parts[2]);
            int hourlyPay = Integer.parseInt(parts[3]);

            if (dataAsNumber >= dateFromAsNumber && dataAsNumber <= dateToAsNumber) {
                int salary = hours * hourlyPay;

                for (int a = 0; a < names.length; a++) {
                    if (names[a].equals(parts[1])) {
                        salaries[a] = salaries[a] + salary;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        for (int b = 0; b < salaries.length; b++) {
            result.append(names[b])
                    .append(" - ")
                    .append(salaries[b])
                    .append("\n");
        }

        return result.toString();
    }
}
