package entity;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "WOMAN_INDICATORS")
@Entity
public class WomanViolenceIndicatorsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private int id;
    @Column(name ="ano")
    private String ano;
    @Column(name ="mes")
    private String mes;
    @Column(name ="num_Ameacas")
    private int numAmeacas;
    @Column(name ="num_Lesoes_Corporais")
    private int numLesoesCorporais;
    @Column(name ="num_Estupros")
    private int numEstupros;
    @Column(name ="num_Feminicidio_Consumado")
    private int numFeminicidioConsumado;
    @Column(name ="num_FeminicidioTentado")
    private int numFeminicidioTentado;

    public WomanViolenceIndicatorsEntity(){ }

    public WomanViolenceIndicatorsEntity(String ano, String mes, int numAmeacas, int numLesoesCorporais, int numEstupros, int numFeminicidioConsumado, int numFeminicidioTentado) {
        super();
        this.ano = ano;
        this.mes = mes;
        this.numAmeacas = numAmeacas;
        this.numLesoesCorporais = numLesoesCorporais;
        this.numEstupros = numEstupros;
        this.numFeminicidioConsumado = numFeminicidioConsumado;
        this.numFeminicidioTentado = numFeminicidioTentado;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getNumAmeacas() {
        return numAmeacas;
    }

    public void setNumAmeacas(int numAmeacas) {
        this.numAmeacas = numAmeacas;
    }

    public int getNumLesoesCorporais() {
        return numLesoesCorporais;
    }

    public void setNumLesoesCorporais(int numLesoesCorporais) {
        this.numLesoesCorporais = numLesoesCorporais;
    }

    public int getNumEstupros() {
        return numEstupros;
    }

    public void setNumEstupros(int numEstupros) {
        this.numEstupros = numEstupros;
    }

    public int getNumFeminicidioConsumado() {
        return numFeminicidioConsumado;
    }

    public void setNumFeminicidioConsumado(int numFeminicidioConsumado) {
        this.numFeminicidioConsumado = numFeminicidioConsumado;
    }

    public int getNumFeminicidioTentado() {
        return numFeminicidioTentado;
    }

    public void setNumFeminicidioTentado(int numFeminicidioTentado) {
        this.numFeminicidioTentado = numFeminicidioTentado;
    }
}
