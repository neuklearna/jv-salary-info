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
        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < names.length; i++) {
            nameToIndex.put(names[i], i);
        }

        int dateFromAsNumber = yearFrom * YEAR_MULTIPLIER
                + monthFrom * MONTH_MULTIPLIER + dayFrom;
        int dateToAsNumber = yearTo * YEAR_MULTIPLIER
                + monthTo * MONTH_MULTIPLIER + dayTo;

        for (int i = 0; i < data.length; i++) {
            String jednaLinia = data[i];
            String[] parts = jednaLinia.split(" ");

            String dataZLinii = parts[0];
            String[] partsData = dataZLinii.split("\\.");
            int day = Integer.parseInt(partsData[0]);
            int month = Integer.parseInt(partsData[1]);
            int year = Integer.parseInt(partsData[2]);
            int dataAsNumber = year * YEAR_MULTIPLIER + month * MONTH_MULTIPLIER + day;

            int hours = Integer.parseInt(parts[2]);
            int hourlyPay = Integer.parseInt(parts[3]);

            if (dataAsNumber >= dateFromAsNumber && dataAsNumber <= dateToAsNumber) {
                int salary = hours * hourlyPay;
                String name = parts[1];
                if (nameToIndex.containsKey(name)) {
                    int index = nameToIndex.get(name);
                    salaries[index] += salary;
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
                    .append(salaries[b]);
            if (b < salaries.length - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
