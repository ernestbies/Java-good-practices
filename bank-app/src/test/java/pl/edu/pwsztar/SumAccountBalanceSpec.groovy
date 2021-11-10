package pl.edu.pwsztar

import spock.lang.Specification

class SumAccountBalanceSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    def "should get balance of all existing accounts"() {

        given: "initial data"
            def bank = new Bank()
            def firstAccountNumber = bank.createAccount()
            def secondAccountNumber = bank.createAccount()
            def thirdAccountNumber = bank.createAccount()
            def firstAccountBalance = 500
            def secondAccountBalance = 1000
            def thirdAccountBalance = 1000
            bank.deposit(firstAccountNumber, firstAccountBalance)
            bank.deposit(secondAccountNumber, secondAccountBalance)
            bank.deposit(thirdAccountNumber, thirdAccountBalance)
        when: "wants to get sum of account balances"
            def balance = bank.sumAccountsBalance()
        then: "get sum of account balances"
            balance == 2500
    }
}
