package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class AccountBalanceSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    @Unroll
    def "should get account balance of an existing account" () {

        given: "initial data"
            def bank = new Bank()
            def accountNumber = bank.createAccount()
            bank.deposit(accountNumber, balance)
        when: "wants to get balance of account"
            def accountBalance = bank.accountBalance(accountNumber)
        then: "gets balance of account"
            accountBalance == result
        where:

            balance        |  result
            500            |  500
            1000           |  1000
            2000           |  2000
            300            |  300
    }

    @Unroll
    def "should not get account balance of account that does not exist" () {
        given: "initial data"
            def bank = new Bank()
        when: "wants to get balance of account"
            def accountBalance = bank.accountBalance(15)
        then: "returned value is -1 because account does not exist"
            accountBalance == BankOperation.ACCOUNT_NOT_EXISTS
    }
}
