/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shiftmanagement.Business.UC;

import java.util.Objects;

/**
 *
 * @author Tiago
 */
public class Registo {
    
    private int id;
    private String idAluno;
    private String idUC;
    private String idTurno;
    
    public Registo(){
        this.id=0;
        this.idAluno = null;
        this.idUC = null;
        this.idTurno = null;
    }
    
    public Registo(int id, String aluno, String uc, String turno){
        this.id = id;
        this.idAluno = aluno;
        this.idUC = uc;
        this.idTurno = turno;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getIdAluno() {
        return idAluno;
    }

    public String getIdUC() {
        return idUC;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public void setIdUC(String idUC) {
        this.idUC = idUC;
    }

    public void setIdTurno(String idTurno) {
        this.idTurno = idTurno;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registo other = (Registo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.idAluno, other.idAluno)) {
            return false;
        }
        if (!Objects.equals(this.idUC, other.idUC)) {
            return false;
        }
        if (!Objects.equals(this.idTurno, other.idTurno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registo{" + "id=" + id + ", idAluno=" + idAluno + ", idUC=" + idUC + ", idTurno=" + idTurno + '}';
    }
    
    
    
    
}
