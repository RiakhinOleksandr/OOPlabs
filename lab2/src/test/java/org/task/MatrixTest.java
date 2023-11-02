package org.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class MatrixTest {
    private Matrix matrix_for_tests;

    @BeforeEach
    public void setUp() {
        matrix_for_tests = new Matrix(4, 3);
        double[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        matrix_for_tests.fill_up_matrix(numbers);
    }

    @Test
    public void check_constructors() {
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix(4, 4);
        Matrix m3 = new Matrix(matrix_for_tests);
        int[] d1 = { 0, 0 };
        int[] d2 = { 4, 4 };
        double[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
        Assertions.assertArrayEquals(m1.dimension_of_matrix(), d1);
        Assertions.assertArrayEquals(m2.dimension_of_matrix(), d2);
        Assertions.assertArrayEquals(m3.return_matrix(), matrix);
    }

    @Test
    public void check_filling_up() {
        Matrix m = new Matrix(3, 3);
        double[] matrix = { 1, 3, -1, 0, -2.5, -4.67, 10, 102, -69 };
        double[][] expected = { { 1, 3, -1 }, { 0, -2.5, -4.67 }, { 10, 102, -69 } };
        m.fill_up_matrix(matrix);
        Assertions.assertArrayEquals(m.return_matrix(), expected);
    }

    @Test
    public void check_getting_elements() {
        double[] expected1 = { 4, 5, 6 };
        double[] expected2 = { 2, 5, 8, 11 };
        Assertions.assertEquals(matrix_for_tests.get_element(3, 1), 11);
        Assertions.assertArrayEquals(matrix_for_tests.get_row(1), expected1);
        Assertions.assertArrayEquals(matrix_for_tests.get_column(1), expected2);
    }

    @Test
    public void check_dimensions() {
        int[] d = { 4, 3 };
        Assertions.assertArrayEquals(matrix_for_tests.dimension_of_matrix(), d);
    }
}
