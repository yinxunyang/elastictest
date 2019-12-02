package com.bestvike.ess.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "cmc_code")
public class CmcCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String codeId;

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }
}