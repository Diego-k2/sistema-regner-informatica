package br.com.regnerinformatica.CRUDinicial.model.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_placas_de_video")
public class PlacaVideoModel extends PecaSuperClass{

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
