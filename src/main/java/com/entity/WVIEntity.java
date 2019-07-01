package com.entity;


import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "woman_indicators")
@Entity
@Component
public class WVIEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;
    @Column(name ="ano")
    private String ano;
    @Column(name ="mes")
    private String mes;
    @Column(name ="num_Ameacas")
    private String numAmeacas;
    @Column(name ="num_Lesoes_Corporais")
    private String numLesoesCorporais;
    @Column(name ="num_Estupros")
    private String numEstupros;
    @Column(name ="num_Feminicidio_Consumado")
    private String numFeminicidioConsumado;
    @Column(name ="num_FeminicidioTentado")
    private String numFeminicidioTentado;

    public WVIEntity(){ }

    public WVIEntity(String ano, String mes, String numAmeacas, String numLesoesCorporais, String numEstupros, String numFeminicidioConsumado, String numFeminicidioTentado) {
        this.ano = ano;
        this.mes = mes;
        this.numAmeacas = numAmeacas;
        this.numLesoesCorporais = numLesoesCorporais;
        this.numEstupros = numEstupros;
        this.numFeminicidioConsumado = numFeminicidioConsumado;
        this.numFeminicidioTentado = numFeminicidioTentado;
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

    public String getNumAmeacas() {
        return numAmeacas;
    }

    public void setNumAmeacas(String numAmeacas) {
        this.numAmeacas = numAmeacas;
    }

    public String getNumLesoesCorporais() {
        return numLesoesCorporais;
    }

    public void setNumLesoesCorporais(String numLesoesCorporais) {
        this.numLesoesCorporais = numLesoesCorporais;
    }

    public String getNumEstupros() {
        return numEstupros;
    }

    public void setNumEstupros(String numEstupros) {
        this.numEstupros = numEstupros;
    }

    public String getNumFeminicidioConsumado() {
        return numFeminicidioConsumado;
    }

    public void setNumFeminicidioConsumado(String numFeminicidioConsumado) {
        this.numFeminicidioConsumado = numFeminicidioConsumado;
    }

    public String getNumFeminicidioTentado() {
        return numFeminicidioTentado;
    }

    public void setNumFeminicidioTentado(String numFeminicidioTentado) {
        this.numFeminicidioTentado = numFeminicidioTentado;
    }
}
