public class PhysicalPerson extends Client {
    private double amount;
    private final String INFO_PUT = "- у физических лиц происходит без комиссии. Нельзя пополнить счет отрицательным значением.";
    private final String INFO_TAKE = "- у физических лиц происходит без комиссии. Нельзя снять со счета сумму больщую чем " + amount;


    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);

    }

    @Override
    public void take(double amountToTake) {

        super.take(amountToTake);
    }


    @Override
    public double getAmount() {
        amount = super.getAmount();
        //TODO: реализуйте метод и удалите todo
        return amount;
    }

    public void info() {
        super.info(INFO_PUT, INFO_TAKE);
    }
}
// У PhysicalPerson пополнение и списание происходит без комиссии.
// Если передать в метод пополнения отрицательное значение, сумма на счёте не должна измениться.
// При попытке снять сумму больше, чем есть на счете, сумма не списывается со счёта,
// сумма на счёте не изменяется.
