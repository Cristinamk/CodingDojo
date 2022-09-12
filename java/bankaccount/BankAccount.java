class BankAccount {

    private double checkingBalance ;
    private double savingsBalance ;
    public static int numberOfaccounts = 0;
    public static double totalAmountofAccounts = 0;

    public BankAccount() {
        BankAccount.numberOfaccounts += 1;
        this.checkingBalance = 0;
        this.savingsBalance = 0;
    }


    public String transaction(String transaction,String type,double amount){
        if (transaction == "deposit"){
            if (type == "checkings"){
                this.checkingBalance += amount;
            } else if (type == "savings"){
                this.savingsBalance += amount;
            }
        } else if (transaction == "withdraw"){

            if (type == "checkings"){
                if (amount>this.checkingBalance){
                    System.out.println("Insufficient founds");
                }
                this.checkingBalance -= amount;
            } else if (type == "savings"){
                this.savingsBalance -= amount;
            }
        }
        return "transaction Succesful ";}
    public double getAccountTotal(){
        return this.checkingBalance + this.savingsBalance;
    }

// getters
    public double getcheckingBalance() {
        return this.checkingBalance;
    }
    public double getsavingsBalance() {
        return this.savingsBalance;
    }



}
