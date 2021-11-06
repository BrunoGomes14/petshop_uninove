/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author Bruno
 */
public class ServicoHistorico{
    int id;
    int idServico;
    int idUsuarioResponsavel;
    int idPet;
    Date dtPrestacaoServico;
    String nomeServico;
    Pet pet;

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    public int getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public void setIdUsuarioResponsavel(int idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    public int getIdPet() {
        return idPet;
    }

    public void setIdPet(int idPet) {
        this.idPet = idPet;
    }

    public Date getDtPrestacaoServico() {
        return dtPrestacaoServico;
    }

    public void setDtPrestacaoServico(Date dtPrestacaoServico) {
        this.dtPrestacaoServico = dtPrestacaoServico;
    }
}
