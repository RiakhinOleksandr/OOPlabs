package org.task;

public class Main {
    public static void main(String[] args) {
        // Creating matrixes:
        Matrix m = new Matrix(3, 3);
        Matrix m1 = new Matrix(2, 3);
        Matrix m2 = new Matrix(3, 2);
        Matrix matrix_for_inverse1 = new Matrix(3, 3);
        Matrix matrix_for_inverse2 = new Matrix(3, 3);
        ImmutableMatrix M = new ImmutableMatrix(3, 3);
        ImmutableMatrix M1 = new ImmutableMatrix(2, 3);
        // Filling up matrixes:
        double[] numbers = { 0, 1, 2, 4, 0, 6, 7, 8, 0 };
        double[] numbers1 = { -1, 2, -3, 4, -5, 6 };
        double[] numbers2 = { 1, 2, 3, 5, 7, 9, 11, 13, 17 };
        double[] numbers3 = { 0, 1, 4, 3, 2, 0, 0, 1, -1 };
        m.fill_up_matrix(numbers);
        m1.fill_up_matrix(numbers1);
        m2.fill_up_matrix(numbers1);
        matrix_for_inverse1.fill_up_matrix(numbers2);
        matrix_for_inverse2.fill_up_matrix(numbers3);
        M.fill_up_matrix(numbers);
        M1.fill_up_matrix(numbers1);
        // Adding matrixes:
        // m1.add_matrixes(m);
        // printMatrix(m1);
        // ImmutableMatrix M2 = ImmutableMatrix.add_matrixes(M1, M);
        // printImmutableMatrix(M2);
        // Multiplying matrixes by number:
        // m1.multiply_Matrix_by_number(2);
        // printMatrix(m1);
        // ImmutableMatrix M3 = ImmutableMatrix.multiply_Matrix_by_number(M1, 2);
        // printImmutableMatrix(M3);
        // Multiplying marixes:
        // Transponate matrix:
        // m1.transponate();
        // printMatrix(m1);
        // ImmutableMatrix M4 = ImmutableMatrix.transponate(M);
        // printImmutableMatrix(M4);
        // Create diagonal matrix:
        // double[] vector = { 1, 2, 3, 4, 5 };
        // Matrix m3 = Matrix.create_diagonal_matrix(vector);
        // printMatrix(m3);
        // Create unit matrix:
        // Matrix m4 = Matrix.create_unit_matrix(6);
        // printMatrix(m4);
        // Create random matrix:
        // Matrix m5 = Matrix.random_column_vector(8, 100);
        // Matrix m5 = Matrix.random_row_vector(8, 100);
        // printMatrix(m5);
        // Inverse matrix:
        // printImmutableMatrix(M);
        /// ImmutableMatrix m6 = ImmutableMatrix.inverse_matrix(M);
        // printImmutableMatrix(m6);
        // printImmutableMatrix(M);
        // ImmutableMatrix M3 = ImmutableMatrix.inverse_matrix(M);
        // printImmutableMatrix(M3);
        // ImmutableMatrix M4 = ImmutableMatrix.multiply_matrixes(M3, M);
        // printImmutableMatrix(M4);
        // Matrix m7 = Matrix.inverse_matrix(matrix_for_inverse2);
        // printMatrix(m7);
    }

    static public void printMatrix(Matrix m) {
        int[] dimension = m.dimension_of_matrix();
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                System.out.printf("%.3f  ", m.get_element(i, j));
            }
            System.out.println("");
        }
        System.out.println("");
    }

    static public void printImmutableMatrix(ImmutableMatrix m) {
        int[] dimension = m.dimension_of_matrix();
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                System.out.printf("%.3f  ", m.get_element(i, j));
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
