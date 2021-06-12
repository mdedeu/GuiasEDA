package junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {

  @Test
  void firstTest() {
    assertEquals(2, 1 + 1);
    assertTrue(5 > 2);
    assertFalse(5 < 2);
    assertNotEquals(5, 1);
    assertNull(null);
    assertNotNull("hola mundo");
    assertThrows(RuntimeException.class, () -> {
      throw new RuntimeException("Error");
    });
  }

  class Calculator {
    int add(int x, int y) {
      return x + y;
    }

    int multiply(int x, int y) {
      return x * y;
    }
  }

  @Test
  void addingNumberWorks() {
    // Arrange
    Calculator calculator = new Calculator();

    // Act
    int result = calculator.add(2, 3);

    // Assert
    assertEquals(5, result);
  }

  Calculator calculator;

  @BeforeEach
  void before() {
    calculator = new Calculator();
  }

  @Test
  void addNumbers() {
    assertEquals(5, calculator.add(2, 3));
  }

  @Test
  void multiplyNumbers() {
    assertEquals(6, calculator.multiply(2, 3));
  }

}
