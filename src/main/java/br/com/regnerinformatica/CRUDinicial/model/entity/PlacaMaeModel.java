package br.com.regnerinformatica.CRUDinicial.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_placas_maes")
public class PlacaMaeModel extends PecaSuperClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
