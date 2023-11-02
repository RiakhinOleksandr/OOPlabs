package org.task;

public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(4, 3);
        double[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15 };
        int[] dimension = m.dimension_of_matrix();
        m.fill_up_matrix(numbers);
        for (int i = 0; i < dimension[0]; i++) {
            for (int j = 0; j < dimension[1]; j++) {
                System.out.printf("%.3f  ", m.get_element(i, j));
            }
            System.out.println("");
        }
        System.out.printf("Dimension of this matrix is: %d x %d", dimension[0], dimension[1]);
    }
}
