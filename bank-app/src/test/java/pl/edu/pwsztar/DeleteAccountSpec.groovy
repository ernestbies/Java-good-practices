package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class DeleteAccountSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    @Unroll
    def "should delete account without any balance"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumber = bank.createAccount()
        when: "wants to delete account"
            def accountBalance = bank.deleteAccount(accountNumber)
        then: "account is deleted and result should be zero"
            accountBalance == 0
    }

    def "should delete account with balance"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumber = bank.createAccount()
            def amount = 1500
            bank.deposit(accountNumber, amount)
        when: "wants to delete account"
            def accountBalance = bank.deleteAccount(accountNumber)
        then: "account is deleted and result should be balance of deleted account"
            accountBalance == 1500
    }

    @Unroll
    def "should not delete account number #accountNumber if it does not exist"() {

        given: "initial data"
            def bank = new Bank()
            bank.createAccount()
            bank.createAccount()
            bank.createAccount()
        when: "wants to delete account that does not exist"
            def status = bank.deleteAccount(accountNumber)
        then: "account is deleted and result should be -1 because account does not exist"
            status == result

        where:
            accountNumber | result
            5             | BankOperation.ACCOUNT_NOT_EXISTS
            7             | BankOperation.ACCOUNT_NOT_EXISTS
            6             | BankOperation.ACCOUNT_NOT_EXISTS
    }

}
