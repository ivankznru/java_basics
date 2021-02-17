public class IndividualBusinessman extends Client {
    private final double AMOUNT_LOW_PERCENT = 1000;
    private final double TAX_AMOUNT_1_PERCENT = 0.99;
    private final double TAX_AMOUNT_0_5_PERCENT = 0.995;
    private double amount;
    private final String INFO_PUT = "- у индивидуальных предпренимателей происходит с комиссией 1%, если сумма меньше 1 000 рублей. " +
            "И пополнение с комиссией 0,5%,если сумма больше либо равна 1 000 рублей. Нельзя пополнить счет отрицательным значением.";
    private final String INFO_TAKE = "- у индивидуальных предпренимателей происходит без комиссии. Нельзя снять со счета сумму больщую чем " + amount;


    @Override
    public void put(double amountToPut) {
        if (amountToPut < AMOUNT_LOW_PERCENT) {
            super.put(amountToPut * TAX_AMOUNT_1_PERCENT);
        }
        if (amountToPut >= AMOUNT_LOW_PERCENT) {
            super.put(amountToPut * TAX_AMOUNT_0_5_PERCENT);
        }
    }

    @Override
    public void take(double amountToTake) {

        super.take(amountToTake);
    }


    @Override
    public double getAmount() {
        amount = super.getAmount();
        //TODO: реализуйте метод и удалите todo
        return Math.round(amount);
    }

    public void info() {
        super.info(INFO_PUT, INFO_TAKE);

    }

}


//У PhysicalPerson пополнение и списание происходит без комиссии.
// Если передать в метод пополнения отрицательное значение, сумма на счёте не должна измениться.
// При попытке снять сумму больше, чем есть на счете, сумма не списывается со счёта,
// сумма на счёте не изме

//У IndividualBusinessman — все условия PhysicalPerson и дополнительно,
// пополнение с комиссией 1%, если сумма меньше 1 000 рублей. И пополнение с комиссией 0,5%,
// если сумма больше либо равна 1 000 рублей.

