package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class WithdrawSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    @Unroll
    def "should withdraw #money from account number #accountNumber if there is sufficient amount of money"() {

        given: "initial data"
            def bank = new Bank()
            bank.createAccount()
            bank.deposit(1, 100)
            bank.createAccount()
            bank.deposit(2, 500)
            bank.createAccount()
            bank.deposit(3, 1000)
            bank.createAccount()
            bank.deposit(4, 2000)
            bank.createAccount()
            bank.deposit(5, 2000)
        when: "wants to withdrawn money from account"
            def number = bank.withdraw(accountNumber, money)
        then: "money is withdrawn and result should be true"
            number == result

        where:
            accountNumber | money | result
            1             | 100   | true
            2             | 500   | true
            3             | 800   | true
            4             | 1600  | true
    }

    @Unroll
    def "should not withdraw #money from account number #accountNumber due to insufficient amount of money"() {

        given: "initial data"
            def bank = new Bank()
            bank.createAccount()
            bank.deposit(1, 100)
            bank.createAccount()
            bank.deposit(2, 500)
            bank.createAccount()
            bank.deposit(3, 1000)
        when: "wants to withdrawn money from account"
            def status = bank.withdraw(accountNumber, money)
        then: "money is not withdrawn and result should be false"
            status == result

        where:
            accountNumber | money | result
            1             | 101   | false
            2             | 501   | false
            3             | 1200  | false
    }
}
