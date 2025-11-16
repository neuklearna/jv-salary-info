package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        String[] names = {"John", "Andrew", "Kate"};
        String[] data = {
                "26.04.2019 John 4 50",
                "05.04.2019 Andrew 3 200",
                "10.04.2019 John 7 100",
                "22.04.2019 Kate 9 100"
        };
        String dateFrom = "01.04.2019";
        String dateTo = "30.04.2019";
        String wynik = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(wynik);
    }
}
