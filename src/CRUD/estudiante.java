package CRUD;

public class estudiante {
    double b1;
    double b2;
    int cedula;

    public estudiante() {
    }

    public estudiante(double b1, double b2, int cedula) {
        this.b1 = b1;
        this.b2 = b2;
        this.cedula = cedula;
    }

    public double getB1() {
        return b1;
    }

    public void setB1(double b1) {
        this.b1 = b1;
    }

    public double getB2() {
        return b2;
    }

    public void setB2(double b2) {
        this.b2 = b2;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }
}

