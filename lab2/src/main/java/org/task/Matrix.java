package org.task;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    private int rows, columns;

    public Matrix(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new double[rows][columns];
    }

    public Matrix() {
        this.columns = 0;
        this.rows = 0;
        this.matrix = new double[0][0];
    }

    public Matrix(Matrix m) {
        this.columns = m.columns;
        this.rows = m.rows;
        this.matrix = m.matrix;
    }

    public double[][] return_matrix() {
        return this.matrix;
    }

    public void fill_up_matrix(double[] numbers) {
        if (numbers.length == this.columns * this.rows) {
            int k = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    this.matrix[i][j] = numbers[k];
                    k++;
                }
            }
        } else {
            System.out.println("Wrong number of numbers!");
        }
    }

    public double get_element(int row, int columns) {
        return this.matrix[row][columns];
    }

    public double[] get_row(int row) {
        return this.matrix[row];
    }

    public double[] get_column(int num_of_column) {
        double[] column = new double[this.rows];
        for (int i = 0; i < this.rows; i++) {
            column[i] = this.matrix[i][num_of_column];
        }
        return column;
    }

    public int[] dimension_of_matrix() {
        return new int[] { rows, columns };
    }

    public boolean equals(Matrix m) {
        if (m.columns == this.columns & m.rows == this.rows) {
            for (int i = 0; i < m.rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    if (m.matrix[i][j] != this.matrix[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int p = 63;
        int result = p * (p * (p + this.rows) + this.columns) + Arrays.hashCode(this.matrix);
        return result;
    }

    public void add_matrixes(Matrix m) {
        if (this.rows == m.rows && this.columns == m.columns) {
            for (int i = 0; i < m.rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    this.matrix[i][j] = this.matrix[i][j] + m.matrix[i][j];
                }
            }
        } else {
            System.out.println("Matrixes must be same size!");
        }
    }

    public void multiply_Matrix_by_number(double number) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                this.matrix[i][j] *= number;
            }
        }
    }

    public static Matrix multiply_matrixes(Matrix m1, Matrix m2) {
        if (m1.columns == m2.rows) {
            Matrix matrix1 = new Matrix(m1.rows, m2.columns);
            for (int i = 0; i < m1.rows; i++) {
                for (int j = 0; j < m2.columns; j++) {
                    for (int k = 0; k < m1.columns; k++) {
                        matrix1.matrix[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                    }
                }
            }
            return matrix1;
        } else {
            System.out.println("Those matrixes can`t be multiplyed!");
            return new Matrix();
        }
    }

    public void transponate() {
        double[] numbers = new double[this.rows * this.columns];
        double[] column;
        int k = 0;
        for (int i = 0; i < this.columns; i++) {
            column = this.get_column(i);
            for (int j = 0; j < this.rows; j++) {
                numbers[k] = column[j];
                k++;
            }
        }
        int temp = this.rows;
        this.rows = this.columns;
        this.columns = temp;
        this.matrix = new double[rows][columns];
        this.fill_up_matrix(numbers);
    }

    public static Matrix create_diagonal_matrix(double[] vector) {
        int len = vector.length;
        int k = 0;
        Matrix m = new Matrix(len, len);
        double[] numbers = new double[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    numbers[k] = vector[i];
                } else {
                    numbers[k] = 0;
                }
                k++;
            }
        }
        m.fill_up_matrix(numbers);
        return m;
    }

    public static Matrix create_unit_matrix(int n) {
        double[] numbers = new double[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = 1;
        }
        Matrix m = Matrix.create_diagonal_matrix(numbers);
        return m;
    }
}

final class ImmutableMatrix {
    private double[][] matrix;
    private int rows, columns;
    private boolean changed = false;

    public ImmutableMatrix(int rows, int columns) {
        this.columns = columns;
        this.rows = rows;
        this.matrix = new double[rows][columns];
    }

    public ImmutableMatrix() {
        this.columns = 0;
        this.rows = 0;
        this.matrix = new double[0][0];
    }

    public ImmutableMatrix(ImmutableMatrix m) {
        this.columns = m.columns;
        this.rows = m.rows;
        this.matrix = m.matrix;
    }

    public double[][] return_matrix() {
        return this.matrix;
    }

    public void fill_up_matrix(double[] numbers) {
        if (!changed) {
            if (numbers.length == this.columns * this.rows) {
                int k = 0;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        this.matrix[i][j] = numbers[k];
                        k++;
                    }
                }
            } else {
                System.out.println("Wrong number of numbers!");
            }
        } else {
            System.out.println("You can`t change immutable matrix");
        }
    }

    public double get_element(int row, int columns) {
        return this.matrix[row][columns];
    }

    public double[] get_row(int row) {
        return this.matrix[row];
    }

    public double[] get_column(int num_of_column) {
        double[] column = new double[this.rows];
        for (int i = 0; i < this.rows; i++) {
            column[i] = this.matrix[i][num_of_column];
        }
        return column;
    }

    public int[] dimension_of_matrix() {
        return new int[] { rows, columns };
    }

    public boolean equals(ImmutableMatrix m) {
        if (m.columns == this.columns & m.rows == this.rows) {
            for (int i = 0; i < m.rows; i++) {
                for (int j = 0; j < m.columns; j++) {
                    if (m.matrix[i][j] != this.matrix[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int p = 63;
        int result = p * (p * (p + this.rows) + this.columns) + Arrays.hashCode(this.matrix);
        return result;
    }

    public static ImmutableMatrix add_matrixes(ImmutableMatrix m1, ImmutableMatrix m2) {
        if (m1.rows == m2.rows && m1.columns == m2.columns) {
            ImmutableMatrix m = new ImmutableMatrix(m1.rows, m1.columns);
            for (int i = 0; i < m1.rows; i++) {
                for (int j = 0; j < m1.columns; j++) {
                    m.matrix[i][j] = m1.matrix[i][j] + m2.matrix[i][j];
                }
            }
            return m;
        } else {
            System.out.println("Matrixes must be same size!");
            return new ImmutableMatrix();
        }
    }

    public static ImmutableMatrix multiply_Matrix_by_number(ImmutableMatrix m, double number) {
        ImmutableMatrix matrix1 = new ImmutableMatrix(m.rows, m.columns);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.columns; j++) {
                matrix1.matrix[i][j] = number * m.matrix[i][j];
            }
        }
        return matrix1;
    }

    public static ImmutableMatrix multiply_matrixes(ImmutableMatrix m1, ImmutableMatrix m2) {
        if (m1.columns == m2.rows) {
            ImmutableMatrix matrix1 = new ImmutableMatrix(m1.rows, m2.columns);
            for (int i = 0; i < m1.rows; i++) {
                for (int j = 0; j < m2.columns; j++) {
                    for (int k = 0; k < m1.columns; k++) {
                        matrix1.matrix[i][j] += m1.matrix[i][k] * m2.matrix[k][j];
                    }
                }
            }
            return matrix1;
        } else {
            System.out.println("Those matrixes can`t be multiplyed!");
            return new ImmutableMatrix();
        }
    }

    public static ImmutableMatrix transponate(ImmutableMatrix m) {
        ImmutableMatrix matrix1 = new ImmutableMatrix(m.columns, m.rows);
        double[] numbers = new double[m.rows * m.columns];
        double[] column;
        int k = 0;
        for (int i = 0; i < m.columns; i++) {
            column = m.get_column(i);
            for (int j = 0; j < m.rows; j++) {
                numbers[k] = column[j];
                k++;
            }
        }
        matrix1.fill_up_matrix(numbers);
        return matrix1;
    }

    public static ImmutableMatrix create_diagonal_matrix(double[] vector) {
        int len = vector.length;
        int k = 0;
        ImmutableMatrix m = new ImmutableMatrix(len, len);
        double[] numbers = new double[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == j) {
                    numbers[k] = vector[i];
                } else {
                    numbers[k] = 0;
                }
                k++;
            }
        }
        m.fill_up_matrix(numbers);
        return m;
    }

    public static ImmutableMatrix create_unit_matrix(int n) {
        double[] numbers = new double[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = 1;
        }
        ImmutableMatrix m = ImmutableMatrix.create_diagonal_matrix(numbers);
        return m;
    }
}