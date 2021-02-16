public class CardAccount extends BankAccount {
    private final double TAX_AMOUNT = 1.01;
    private double amount;


    @Override
    protected void put(double amountToPut) {
        super.put(amountToPut);

    }

    @Override
    protected void take  (double amountToTake) {

        super.take(amountToTake * TAX_AMOUNT);}


    @Override
    protected double getAmount() {
        amount =super.getAmount();

        return amount;
    }

}

