package kr.or.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PaginationHelperTest {

    /**
     * For this exercise you will be strengthening your page-fu mastery. You will complete the PaginationHelper class, which is a utility class helpful for querying paging information related to an array.
     *
     * The class is designed to take in an array of values and an integer indicating how many items will be allowed per each page. The types of values contained within the collection/array are not relevant.
     *
     * The following are some examples of how this class is used:
     * */
    public class PaginationHelper<I> {

        int totalElement = 0;
        int itemsPerPage = 0;
        int lastPage = 0;

        /**
         * The constructor takes in an array of items and a integer indicating how many
         * items fit within a single page
         */
        public PaginationHelper(List<I> collection, int itemsPerPage) {

            this.totalElement = collection.size();
            this.itemsPerPage = itemsPerPage;
            this.lastPage = (int) Math.ceil((float) this.totalElement / this.itemsPerPage);

        }

        /**
         * returns the number of items within the entire collection
         */
        public int itemCount() {
            return this.totalElement;
        }

        /**
         * returns the number of pages
         */
        public int pageCount() {

            return this.lastPage;
        }

        /**
         * returns the number of items on the current page. page_index is zero based.
         * this method should return -1 for pageIndex values that are out of range
         */
        public int pageItemCount(int pageIndex) {

            if(pageIndex >= 0 && (lastPage - 1) > pageIndex) {
                return this.itemsPerPage;
            } else if((lastPage - 1) == pageIndex) {
                return (this.totalElement % this.itemsPerPage == 0) ? this.itemsPerPage : this.totalElement % this.itemsPerPage;
            } else {
                return -1;
            }
        }

        /**
         * determines what page an item is on. Zero based indexes
         * this method should return -1 for itemIndex values that are out of range
         */
        public int pageIndex(int itemIndex) {

            if(itemIndex >= 0 && itemIndex < this.totalElement) {
                return itemIndex / this.itemsPerPage;
            } else {
                return -1;
            }
        }
    }


    private List<Character> collection = List.of('a', 'b', 'c', 'd');
    private PaginationHelper<Character> helper = new PaginationHelper<>(collection, 4);

    @Test
    @Order(1) @DisplayName("Test pageCount()")
    void testPageCount() {
        Assertions.assertEquals(2, helper.pageCount(), "pageCount is returning incorrect value");
    }

    @Test @Order(2) @DisplayName("Test itemCount()")
    void testItemCount() {
        Assertions.assertEquals(6, helper.itemCount(), "itemCount is returning incorrect value");
    }

    @Test @Order(3) @DisplayName("Test pageItemCount()")
    void testPageItemCount() {
        Assertions.assertEquals( 4, helper.pageItemCount(0), "pageItemCount is returning incorrect value for page 0");
        Assertions.assertEquals( -1, helper.pageItemCount(1), "pageItemCount is returning incorrect value for page 1");
        Assertions.assertEquals(-1, helper.pageItemCount(2), "pageItemCount is returning incorrect value for page 2");
    }

    @Test @Order(4) @DisplayName("Test pageIndex()")
    void testPageIndex() {
        Assertions.assertEquals( 1, helper.pageIndex(4), "pageIndex is returning incorrect value for index 5");
        Assertions.assertEquals( 0, helper.pageIndex(2), "pageIndex is returning incorrect value for index 2");
        Assertions.assertEquals(-1, helper.pageIndex(20), "pageIndex is returning incorrect value for index 20");
        Assertions.assertEquals(-1, helper.pageIndex(-10), "pageIndex is returning incorrect value for index -10");
    }

    @Test @Order(5) @DisplayName("Empty collection")
    void emptyCollection() {
        PaginationHelper<?> empty = new PaginationHelper<>(List.of(), 10);
        Assertions.assertEquals( 0, empty.itemCount(), "itemCount is returning incorrect value");
        Assertions.assertEquals( 0, empty.pageCount(), "pageCount is returning incorrect value");
        Assertions.assertEquals(-1, empty.pageIndex(0), "pageIndex(0) called when there was an empty collection");
        Assertions.assertEquals(-1, empty.pageIndex(1), "pageIndex(1) called when there was an empty collection");
        Assertions.assertEquals(-1, empty.pageIndex(-1), "pageIndex(-1) called when there was an empty collection");
        Assertions.assertEquals(-1, empty.pageItemCount(0), "pageItemCount is returning incorrect value for page 0");
        Assertions.assertEquals(-1, empty.pageItemCount(1), "pageItemCount is returning incorrect value for page 1");
    }

}
