package mk.ukim.finki.emt.lab.Model.enumerations;

public enum Currency {
    USD, EUR, YEN;

    public static double convert(Currency from, Currency to, double amount) {
        switch (from) {
            case USD:
                switch (to) {
                    case EUR:
                        return amount * 0.93;
                    case YEN:
                        return amount * 150.19;
                    default:
                        return amount;
                }
            case EUR:
                switch (to) {
                    case USD:
                        return amount * 1.08;
                    case YEN:
                        return amount * 162.04;
                    default:
                        return amount;
                }
            case YEN:
                switch (to) {
                    case USD:
                        return amount * 0.0067;
                    case EUR:
                        return amount * 0.0062;
                    default:
                        return amount;
                }
            default:
                return amount;
        }
    }
}
