package DTO;

public class VeiculoDTO extends FuncionarioDTO {

    private String modelo, tipo, ano, cor, placa, entrada, id, horaentrada;
    private int ultimoid;
    private float valor;

    public String getHoraentrada() {
        return horaentrada;
    }

    public void setHoraentrada(String horaentrada) {
        this.horaentrada = horaentrada;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getUltimoid() {
        return ultimoid;
    }

    public void setUltimoid(int ultimoid) {
        this.ultimoid = ultimoid;
    }

    public String getId() {
        return id;
    }

    //private int id;
    public void setId(String id) {
        this.id = id;
    }

    /* public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }*/
    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
