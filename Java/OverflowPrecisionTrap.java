public class OverflowPrecisionTrap {

    public static void main(String[] args) {

        int salary = 1_500_000;
        int hike = 15;
        int years = 4;

        int projected = salary;
        for (int i = 1; i <= years; i++) {
            projected += projected * hike / 100;
        }

        int limit = Integer.MAX_VALUE;
        limit += projected;

        double tax = 0.1 + 0.2 + 0.3 + 0.4;
        boolean exact = tax == 1.0;

        System.out.println("Projected Salary = " + projected);
        System.out.println("Overflowed Value = " + limit);
        System.out.println("Tax = " + tax);
        System.out.println("Exact 1.0? " + exact);
    }
}
