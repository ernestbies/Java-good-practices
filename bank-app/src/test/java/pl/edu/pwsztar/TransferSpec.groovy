package pl.edu.pwsztar

import spock.lang.Specification

class TransferSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    def "should transfer money between accounts if there is sufficient amount of money on first of them"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumberOfFirstAccount = bank.createAccount()
            def accountNumberOfSecondAccount = bank.createAccount()
            bank.deposit(accountNumberOfFirstAccount, 1000)
        when: "wants to transfer money between accounts"
            def status = bank.transfer(accountNumberOfFirstAccount, accountNumberOfSecondAccount, 500)
        then: "money is transferred and result should be true"
            status
    }

    def "should not transfer money if there is not sufficient amount of money on first of them"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumberOfFirstAccount = bank.createAccount()
            def accountNumberOfSecondAccount = bank.createAccount()
            bank.deposit(accountNumberOfFirstAccount, 1000)
        when: "wants to transfer money between accounts"
            def status = bank.transfer(accountNumberOfFirstAccount, accountNumberOfSecondAccount, 1001)
        then: "money is not transferred and result should be false"
            !status
    }

    def "should not transfer money between accounts if second of them does not exist"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumber = bank.createAccount()
            bank.deposit(accountNumber, 1000)
        when: "wants to transfer money between accounts"
            def status = bank.transfer(accountNumber, 11, 500)
        then: "money is not transferred and result should be false"
            !status
    }

    def "should not transfer money between accounts if first of them does not exist"() {

        given: "initial data"
            def bank = new Bank()
            def accountNumber = bank.createAccount()
            bank.deposit(accountNumber, 555)
        when: "wants to transfer money between accounts"
            def status = bank.transfer(18, accountNumber, 500)
        then: "money is not transferred and result should be false"
            !status
    }

    def "should not transfer money between accounts if accounts do not exist"() {

        given: "initial data"
            def bank = new Bank()
        when: "wants to transfer money between accounts"
            def status = bank.transfer(10, 16, 500)
        then: "money is not transferred and result should be false"
            !status
    }
}
