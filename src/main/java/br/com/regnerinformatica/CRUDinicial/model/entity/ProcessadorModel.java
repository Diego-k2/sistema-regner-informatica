package br.com.regnerinformatica.CRUDinicial.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_processadores")
public class ProcessadorModel extends PecaSuperClass{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    private String frequencia;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
}
