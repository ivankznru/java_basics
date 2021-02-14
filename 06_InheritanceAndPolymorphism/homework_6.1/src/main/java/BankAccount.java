public class BankAccount {

  private double amount;

  protected void setAmount(double amount) {
    this.amount = amount;
  }

  protected double getAmount() {
    amount =this.amount;

    return amount;
  }

  protected void put(double amountToPut) {
    if(amountToPut>0){ amount = amount +amountToPut;}

  }

  protected void take(double amountToTake) {
    if(amountToTake<= amount){amount = amount - amountToTake;}

  }


}
