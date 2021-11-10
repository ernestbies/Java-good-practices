package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class DepositSpec extends Specification {

    def cleanup () {
        Bank.accountNumber = 0
    }

    @Unroll
    def "should deposit money on account number #accountNumber if account exist"() {

        given: "initial data"
            def bank = new Bank()
            bank.createAccount()
            bank.createAccount()
            bank.createAccount()
            bank.createAccount()
            bank.createAccount()
        when: "tries deposit money on account that exist"
            def status = bank.deposit(accountNumber, amount)
        then: "money is deposited and result should be true"
            status == result

        where:
            accountNumber | amount | result
            1             | 100    | true
            2             | 500    | true
            3             | 1000   | true
            4             | 300    | true
            5             | 200    | true
    }

    @Unroll
    def "should not deposit money on account number #accountNumber if account does not exist"() {

        given: "initial data"
            def bank = new Bank()
            bank.createAccount()
            bank.createAccount()
            bank.createAccount()
        when: "tries deposit money on account that does not exist"
            def status = bank.deposit(accountNumber, amount)
        then: "money is not deposited and result should be false"
            status == result

        where:
            accountNumber | amount | result
            7             | 1000   | false
            11            | 300    | false
            5             | 200    | false
    }

}
