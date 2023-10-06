import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.Test;
import structures.AssociativeArray;
import structures.KeyNotFoundException;

public class AssociativeArrayTests {
  /**
   * Testing set(), get(), and remove()
   */
  @Test
  public void julianKimTest1() {
    AssociativeArray<Integer, String> testArr = new AssociativeArray<Integer, String>();

    /**
     * set()-specific tests
     */
    // setting a key with a value
    testArr.set(0, "a");
    try {
      assertEquals("a", testArr.get(0), "Can set a key with a value");
    } catch (Exception e) {
      fail("Could not set key with value");
    }

    // having more than 1 key set
    testArr.set(1, "b");
    try {
      assertEquals("b", testArr.get(1), "More than 1 key can be set");
    } catch (Exception e) {
      fail("Could not set more than 1 key");
    }

    // overwriting previously set key
    testArr.set(0, "c");
    try {
      assertEquals("c", testArr.get(0), "Can overwrite exisiting key with a new value");
    } catch (Exception e) {
      fail("Could not overwrite existing key with new value");
    }

    /**
     * get()-specific tests
     */
    // getting non-existent key
    try {
      testArr.get(2);

      fail("Should not have gotten a value for a non-existent key");
    } catch (Exception e) {
      if (!(e instanceof KeyNotFoundException)) {
        fail("Wrong type of exception thrown");
      }
    }

    /**
     * remove()-specific tests
     */
    // removing existing key
    testArr.remove(0);
    try {
      testArr.get(0);

      fail("Should have thrown an exception for a removed k/v pair");
    } catch (Exception e) {
      if (!(e instanceof KeyNotFoundException)) {
        fail("Wrong type of exception thrown");
      }
    }

    // removing non-existent key
    testArr.remove(2);
  }

  /**
   * Testing hasKey() and size()
   */
  @Test
  public void julianKimTest2() {
    AssociativeArray<Integer, String> testArr = new AssociativeArray<Integer, String>();
    /**
     * hasKey()-specific tests
     */
    // array has set key
    testArr.set(0, "a");
    assertTrue(testArr.hasKey(0), "Array has set key");

    // array does not have not set key
    assertFalse(testArr.hasKey(1), "Array does not have not set key");

    // array does not have removed key
    testArr.remove(0);
    assertFalse(testArr.hasKey(0), "Array does not have removed key");

    /**
     * size()-specific tests
     */
    // array of size 1 has correct size
    testArr.set(0, "a");
    assertEquals(1, testArr.size(), "Array of size 1 has correct size");

    // array with more than 1 element has correct size
    testArr.set(1, "b");
    testArr.set(2, "c");
    assertEquals(3, testArr.size(), "Array with more than 1 element has correct size");

    // Removing element decreases size by 1
    testArr.remove(1);
    assertEquals(2, testArr.size(), "Removing element decreases size by 1");
  }

  @Test
  public void julianKimEdge1() {
    AssociativeArray<Integer, String> testArr = new AssociativeArray<Integer, String>();
    // hasKey works on empty array
    assertFalse(testArr.hasKey(0), "hasKey works on empty array");
    
    // empty array has 0 size
    assertEquals(0, testArr.size(), "Empty array has 0 size");

    // get on empty array returns proper exception
    try {
      testArr.get(0);

      fail("Should not have gotten a value for a non-existent key");
    } catch (Exception e) {
      if (!(e instanceof KeyNotFoundException)) {
        fail("Wrong type of exception thrown");
      }
    }

    // overwriting element should not change size
    testArr.set(0, "a");
    int originalSize = testArr.size();
    testArr.set(0, "b");
    assertEquals(originalSize, testArr.size(), "Overwriting element should not change size");

    // removing a k/v pair should not tamper with other k/v pairs
    testArr.set(1, "c");
    testArr.remove(0);
    try {
      assertEquals("c", testArr.get(1), "Removing a k/v pair should not tamper with other k/v pairs");
    } catch (Exception e) {
      fail("Should have gotten existing k/v pair");
    }

    // removing previously removed key
    testArr.remove(0);
    try {
      testArr.get(0);

      fail("Should have thrown an exception for a removed k/v pair");
    } catch (Exception e) {
      if (!(e instanceof KeyNotFoundException)) {
        fail("Wrong type of exception thrown");
      }
    }

    /**
     * using floats as keys
     */
    // setting
    AssociativeArray<Float, String> floatTest = new AssociativeArray<Float, String>();
    floatTest.set(0.1f, "a");

    // hasKey and size
    assertTrue(floatTest.hasKey(0.1f), "Float-keyed array has set key");
    assertEquals(1, floatTest.size(), "Float-keyed array has proper size");

    // getting
    try {
      assertEquals("a", floatTest.get(0.1f), "Getting from Float-keyed array works");
    } catch (Exception e) {
      fail("Should not have thrown an exception for getting from Float-keyed array");
    }

    // removing
    floatTest.remove(0.1f);
    try {
      floatTest.get(0.1f);

      fail("Should have thrown exception for removed k/v pair");
    } catch (Exception e) {
      if (!(e instanceof KeyNotFoundException)) {
        fail("Wrong type of exception thrown");
      }
    }
  }
}
