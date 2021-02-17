public abstract class Client {
    private double amount;

    public double getAmount() {
        amount = this.amount;

        return amount;

    }


    public void put(double amountToPut) {
        if (amountToPut > 0) {
            amount = amount + amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (amountToTake <= amount) {
            amount = amount - amountToTake;
        }
    }

    public void info(String putCondition, String takeCondition) {
        System.out.println("Ваш баланс:" + amount);
        System.out.println("Условие пополнения:" + putCondition);
        System.out.println("Условие снятия:" + takeCondition);
    }
}


//В проекте найдите абстрактный класс Client и его наследников IndividualBusinessman,
// LegalPerson и PhysicalPerson.
//Реализуйте методы классов, при необходимости переопределите методы в наследниках так,
// чтобы выполнялись условия пополнения и снятия:
//У каждого клиента есть сумма денег на счету (число).
// Деньги можно положить на счёт, снять и вернуть остаток на счёте.
// Каждый класс должен содержать метод, который выводит информацию в консоль о счёте:
// условие пополнения, условие снятия и баланс.
//
//У PhysicalPerson пополнение и списание происходит без комиссии.
// Если передать в метод пополнения отрицательное значение, сумма на счёте не должна измениться.
// При попытке снять сумму больше, чем есть на счете, сумма не списывается со счёта, сумма на счёте не изменяется.
//У LegalPerson — все условия PhysicalPerson и дополнительно снятие с комиссией 1%.

//У IndividualBusinessman — все условия PhysicalPerson и дополнительно,
// пополнение с комиссией 1%, если сумма меньше 1 000 рублей. И пополнение с комиссией 0,5%,
// если сумма больше либо равна 1 000 рублей.
