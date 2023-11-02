package org.task;

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
}