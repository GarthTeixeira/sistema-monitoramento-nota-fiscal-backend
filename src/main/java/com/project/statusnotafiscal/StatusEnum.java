package com.project.statusnotafiscal;

public enum StatusEnum {
    VERDE("Positivo"),
    AMARELO("Temporariamente Negativa"),
    VERMELHO("Negativa"),
    VAZIO("-"),
    ERRO("error");

    private String descricao;

    StatusEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusEnum passStatusString(String status){
        if (status.equals("-")){
            return StatusEnum.VAZIO;
        }
        return StatusEnum.valueOf(status);
    }
}
