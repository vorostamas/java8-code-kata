package stream.api;

import common.test.tool.annotation.Easy;
import common.test.tool.dataset.ClassicOnlineStore;
import common.test.tool.entity.Customer;

import common.test.tool.entity.Item;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Exercise4Test extends ClassicOnlineStore {

    @Easy @Test
    public void firstRegistrant() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Find the first customer who registered this online store by using {@link Stream#findFirst}
         * The customerList are ascending ordered by registered timing.
         */
        Optional<Customer> firstCustomer = customerList.stream().findFirst();

        assertThat(firstCustomer.get(), is(customerList.get(0)));
    }

    @Easy @Test
    public void isThereAnyoneOlderThanLimit() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Check whether any customer older than 40 exists or not, by using {@link Stream#anyMatch}
         */
        final int LIMIT = 40;
        Predicate<Integer> isOlderThanLimit = age -> age > LIMIT;
        boolean olderThan40Exists = customerList.stream().map(Customer::getAge).anyMatch(isOlderThanLimit);

        assertThat(olderThan40Exists, is(false));
    }

    @Easy @Test
    public void isEverybodyOlderThanLimit() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Check whether all customer are older than 20 or not, by using {@link Stream#allMatch}
         */
        final int LIMIT = 20;
        Predicate<Integer> isOlderThanLimit = age -> age > LIMIT;
        boolean allOlderThan20 = customerList.stream().map(Customer::getAge).allMatch(isOlderThanLimit);

        assertThat(allOlderThan20, is(true));
    }

    @Easy @Test
    public void everyoneWantsSomething() {
        List<Customer> customerList = this.mall.getCustomerList();

        /**
         * Confirm that none of the customer has empty list for their {@link Customer.wantToBuy}
         * by using {@link Stream#noneMatch}
         */
        Predicate<List<Item>> hasEmptyList = List::isEmpty;
        boolean everyoneWantsSomething = customerList.stream().map(Customer::getWantToBuy).noneMatch(hasEmptyList);

        assertThat(everyoneWantsSomething, is(true));
    }
}
