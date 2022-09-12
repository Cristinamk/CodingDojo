class TestBankAccount {
    public static void main (String[] args) {
        BankAccount cristinBankAccount = new BankAccount();
        cristinBankAccount.transaction("deposit","checkings",1100.33);
        System.out.println(cristinBankAccount.getcheckingBalance());
        System.out.println(BankAccount.numberOfaccounts);
        cristinBankAccount.transaction("withdraw","checkings",100.33);
        cristinBankAccount.transaction("deposit","savings",301.33);
        System.out.println(cristinBankAccount.getAccountTotal());
        // System.out.println(cristinBankAccount.getcheckingBalance());

        BankAccount ionBankAccount = new BankAccount();
        ionBankAccount.transaction("deposit","savings",300.33);
        System.out.println(BankAccount.numberOfaccounts);
        cristinBankAccount.transaction("deposit","savings",100.33);
        ionBankAccount.transaction("withdraw","savings",307.33);
        System.out.println(ionBankAccount.getcheckingBalance());
    }
}
