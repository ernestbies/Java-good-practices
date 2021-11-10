package pl.edu.pwsztar;

interface BankOperation {

    int ACCOUNT_NOT_EXISTS = -1;

    /** Zaklada konto i zwraca jego numer. Poczatkowy stan konta to 0 (numer konta powinien być inkrementowany) */
    int createAccount();

    /** Likwiduje konto o podanym numerze. Zwraca stan konta w momencie zamkniecia lub
        ACCOUNT_NOT_EXISTS (-1) gdy konta nie ma */
    int deleteAccount(int accountNumber);

    /** Realizuje wplate na konto o podanym numerze. Jesli konto istnieje, to zwraca true, jesli konto nie istnieje,
        to zwraca false */
    boolean deposit(int accountNumber, int amount);

    /** Realizuje wyplate srodkow z konta o podanym numerze. Jesli konto istnieje i stan konta jest nie mniejszy niz
        kwota wyplaty to zwraca true, jesli wyplaty nie mozna zrealizowac, to zwraca false */
    boolean withdraw(int accountNumber, int amount);

    /** Wykonuje przelew pomiedzy dwoma kontami. Jesli konta istnieja i na koncie zrodlowym jest wystarczajaca
        ilosc srodkow to przelew jest realizowany a metoda zwraca true. W przeciwnym wypadku zwraca false. */
    boolean transfer(int fromAccount, int toAccount, int amount);

    /** Zwraca stan konta, gdy konto istnieje. Zwraca ACCOUNT_NOT_EXISTS (-1) gdy konta o podanym numerze nie ma */
    int accountBalance(int accountNumber);

    /** Zwraca stan skarbca czyli sume stanow wszystkich kont */
    int sumAccountsBalance();
}
