package org.task;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

public class MatrixTest {
    private Matrix matrix_for_tests, matrix_for_tests2;
    private ImmutableMatrix Matrix_for_tests, Matrix_for_tests2;

    @BeforeEach
    public void setUp() {
        matrix_for_tests = new Matrix(4, 3);
        matrix_for_tests2 = new Matrix(4, 4);
        Matrix_for_tests = new ImmutableMatrix(4, 3);
        Matrix_for_tests2 = new ImmutableMatrix(4, 4);
        double[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        double[] numbers2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
        matrix_for_tests.fill_up_matrix(numbers);
        matrix_for_tests2.fill_up_matrix(numbers2);
        Matrix_for_tests.fill_up_matrix(numbers);
        Matrix_for_tests2.fill_up_matrix(numbers2);
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

    @Test
    public void check_equals() {
        Matrix expected = new Matrix(matrix_for_tests);
        Assertions.assertEquals(matrix_for_tests.equals(expected), true);
    }

    @Test
    public void check_hashCode() {
        Matrix expected = new Matrix(matrix_for_tests);
        Assertions.assertEquals(matrix_for_tests.hashCode(), expected.hashCode());
        Matrix expected2 = new Matrix(matrix_for_tests2);
        Assertions.assertEquals(matrix_for_tests2.hashCode(), expected2.hashCode());
    }

    @Test
    public void check_adding_matrixes() {
        Matrix temp = new Matrix(4, 3);
        ImmutableMatrix Temp = new ImmutableMatrix(4, 3);
        double[] numbers = { -1, -2, -3, -4, -5, 6, -7, -8, 9, -10, -11, -12 };
        temp.fill_up_matrix(numbers);
        Temp.fill_up_matrix(numbers);
        temp.add_matrixes(matrix_for_tests);
        ImmutableMatrix matrix1 = ImmutableMatrix.add_matrixes(Temp, Matrix_for_tests);
        double[] expected = { 0, 12, 18, 0 };
        double[] expected1 = { 0, 0, 12 };
        Assertions.assertArrayEquals(expected, temp.get_column(2));
        Assertions.assertArrayEquals(expected1, temp.get_row(1));
        Assertions.assertArrayEquals(expected, matrix1.get_column(2));
        Assertions.assertArrayEquals(expected1, matrix1.get_row(1));
    }

    @Test
    public void check_multiplying_by_number() {
        Matrix temp = new Matrix(matrix_for_tests);
        ImmutableMatrix Temp = new ImmutableMatrix(Matrix_for_tests);
        temp.multiply_Matrix_by_number(-1);
        ImmutableMatrix matrix1 = ImmutableMatrix.multiply_Matrix_by_number(Temp, -1);
        Matrix expected = new Matrix(4, 3);
        ImmutableMatrix Expected = new ImmutableMatrix(4, 3);
        double[] numbers = { -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12 };
        expected.fill_up_matrix(numbers);
        Expected.fill_up_matrix(numbers);
        Assertions.assertEquals(temp.equals(expected), true);
        Assertions.assertEquals(matrix1.equals(Expected), true);
    }

    @Test
    public void check_multiplying_matrices() {
        Matrix m1 = new Matrix(3, 3);
        Matrix m2 = new Matrix(3, 1);
        double[] numbers1 = { 3, 0, 0, 0, 2, 0, 0, 0, 1 };
        double[] numbers2 = { 1, 0, 2 };
        m1.fill_up_matrix(numbers1);
        m2.fill_up_matrix(numbers2);
        Matrix matrix1 = Matrix.multiply_matrixes(m1, m2);
        double[] expected = { 3, 0, 2 };
        Assertions.assertArrayEquals(matrix1.get_column(0), expected);
    }
}