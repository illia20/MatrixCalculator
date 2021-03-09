package com.example.matrixcalculator;

public class MatrixQ {
    private int m;
    private int n;
    double[][] a;

    public int getM() {
        return this.m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void set(int i, int j, double q) {
        this.a[i][j] = q;
    }

    public double get(int i, int j) {
        return this.a[i][j];
    }

    public MatrixQ(int m, int n) {
        this.m = m;
        this.n = n;
        this.a = new double[m][n];
    }

    public MatrixQ(int n) {
        this.m = this.n = n;
        this.a = new double[n][n];
    }
    public MatrixQ transponse(){
        MatrixQ result = new MatrixQ(n, m);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                result.set(j, i, a[i][j]);
            }
        }
        return result;
    }

    public MatrixQ findMinor(int _i, int _j){
        MatrixQ result = new MatrixQ(m - 1);
        boolean iq = false, jq = false;
        for(int i = 0; i < m; i++){
            if(i == _i) {
                iq = true;
                continue;
            }
            for(int j = 0; j < m; i++){
                if(j == _j) {
                    jq = true;
                    continue;
                }
                if(iq){
                    if(jq) result.set(i-1, j-1, this.a[i][j]);
                    else result.set(i-1, j, this.a[i][j]);
                    continue;
                }
                if(jq){
                    result.set(i, j-1, this.a[i][j]);
                    continue;
                }
                result.set(i, j, this.a[i][j]);
            }
        }
        return result;
    }

    public double det() throws Exception {
        if (this.m != this.n) {
            throw new Exception("Not square matrix!");
        } else if (this.m > 0 && this.m <= 4) {
            if (this.m == 1) {
                return this.a[0][0];
            } else if (this.m == 2) {
                return this.a[0][0] * this.a[1][1] - this.a[0][1] * this.a[1][0];
            } else if (this.m == 3) {
                return this.a[0][0] * this.a[1][1] * this.a[2][2] + this.a[0][1] * this.a[1][2] * this.a[2][0] + this.a[0][2] * this.a[1][0] * this.a[2][1] - this.a[0][2] * this.a[1][1] * this.a[2][0] - this.a[0][0] * this.a[1][2] * this.a[2][1] - this.a[0][1] * this.a[1][0] * this.a[2][2];
            } else if(this.m == 4){
                double r = 0;
                for(int j = 0; j < m; j++){
                    r += a[0][j] * findMinor(0, j).det();
                }
                return r;
            }
            else {
                throw new Exception("Error in det!");
            }
        } else {
            throw new Exception("Incorrect size!");
        }
    }

    public boolean sameSizes(MatrixQ a, MatrixQ b) {
        return a.getM() == b.getM() && a.getN() == b.getN();
    }

    public MatrixQ add(MatrixQ b) throws Exception {
        if (!this.sameSizes(this, b)) {
            throw new Exception("Matrixes have different sizes!");
        } else {
            MatrixQ result = new MatrixQ(this.m, this.n);

            for(int i = 0; i < this.m; ++i) {
                for(int j = 0; j < this.n; ++j) {
                    result.set(i, j, this.a[i][j] + b.get(i, j));
                }
            }

            return result;
        }
    }

    public MatrixQ sub(MatrixQ b) throws Exception {
        if (!this.sameSizes(this, b)) {
            throw new Exception("Matrixes have different sizes!");
        } else {
            MatrixQ result = new MatrixQ(this.m, this.n);

            for(int i = 0; i < this.m; ++i) {
                for(int j = 0; j < this.n; ++j) {
                    result.set(i, j, this.a[i][j] - b.get(i, j));
                }
            }

            return result;
        }
    }

    public MatrixQ inverse() throws Exception {
        if (this.det() == 0.0D) {
            throw new Exception("Singular matrix!");
        } else {
            MatrixQ result = new MatrixQ(this.n);
            for(int i = 0; i < m; i++){
                for(int j = 0; j < m; j++){
                    result.set(i, j, findMinor(i, j).det()*Math.pow(-1, i + j + 2));
                }
            }
            result = result.transponse();
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    result.set(i, j, result.get(i, j) / this.det());
                }
            }
            return result;
        }
    }

    public MatrixQ mult(MatrixQ b) throws Exception {
        if (this.n != b.getM()) {
            throw new Exception("Cannot multiply these matrixes!");
        } else if (this.m != this.n) {
            throw new Exception("Not square matrix!");
        } else if (this.m > 0 && this.m <= 4) {
            MatrixQ result = new MatrixQ(this.m, b.getN());

            for(int i = 0; i < this.m; ++i) {
                for(int j = 0; j < b.getN(); ++j) {
                    double r = 0.0D;

                    for(int k = 0; k < this.n; ++k) {
                        r += this.a[i][k] * b.get(k, j);
                    }

                    b.set(i, j, r);
                }
            }

            return result;
        } else {
            throw new Exception("Incorrect size!");
        }
    }
}
