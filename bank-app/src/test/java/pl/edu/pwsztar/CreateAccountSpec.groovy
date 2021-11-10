package pl.edu.pwsztar

import spock.lang.Specification
import spock.lang.Unroll

class CreateAccountSpec extends Specification {

    @Unroll
    def "should create account number #accountNumber for #user"() {

        given: "initial data"
            def bank = new Bank()
        when: "the account is created"
            def number = bank.createAccount()
        then: "check account number"
            number == accountNumber

        where:
            user   | accountNumber
            'John' | 1
            'Tom'  | 2
            'Mike' | 3
            'Todd' | 4
    }

    def "should create account number with balance equals 0"() {
        given: "initial data"
            def bank = new Bank()
        when: "the account is created"
            def balance = bank.createAccount()
        then: "account balance should equals 0"
            bank.accountBalance(balance) == 0
    }
}
