import java.time.LocalDate;


public class DepositAccount extends BankAccount {
    private double amount;
    private final int NOT_TO_TAKE_PERIOD= 1;// ONE MONTH;
    private LocalDate lastIncome;

    public void setLastIncome(LocalDate lastIncome) {
        this.lastIncome = lastIncome;
    }
    protected LocalDate getLastIncome()
    {
        return lastIncome;
    }

    @Override
    protected void put(double amountToPut) {
        if (amountToPut > 0) {
            super.put(amountToPut);
            setLastIncome(LocalDate.now());
        }

    }
    @Override
    protected void take(double amountToTake) {
        if (amountToTake > amount & getLastIncome() != null & LocalDate.now().isAfter(getLastIncome().plusMonths(NOT_TO_TAKE_PERIOD)))
        {
            super.take(amountToTake);
        }
    }

    @Override
    protected double getAmount() {
        amount =super.getAmount();
        return amount;
    }



}


