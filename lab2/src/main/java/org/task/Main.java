package org.task;

public class Main {
    public static void main(String[] args) {
        // Creating matrixes:
        Matrix m = new Matrix(4, 3);
        Matrix m1 = new Matrix(3, 3);
        ImmutableMatrix M = new ImmutableMatrix(4, 3);
        ImmutableMatrix M1 = new ImmutableMatrix(3, 3);
        // Filling up matrixes:
        double[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15 };
        double[] numbers1 = { -1, 2, -3, 4, -5, 6, -7, 8, -9 };
        m.fill_up_matrix(numbers);
        m1.fill_up_matrix(numbers1);
        M.fill_up_matrix(numbers);
        M1.fill_up_matrix(numbers1);
        // Printing matrixes:
        // printMatrix(m);
        // printMatrix(m1);
        // Adding matrixes:
        // m1.add_matrixes(m);
        // printMatrix(m1);
        // ImmutableMatrix m2 = ImmutableMatrix.add_matrixes(M1, M);
        // printImmutableMatrix(m2);
        // Multiplying marixes:
        // Matrix m3 = Matrix.multiply_matrixes(m, m1);
        // printMatrix(m3);
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
