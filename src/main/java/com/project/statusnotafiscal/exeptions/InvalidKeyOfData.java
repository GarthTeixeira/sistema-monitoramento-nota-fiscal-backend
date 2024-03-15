package com.project.statusnotafiscal.exeptions;
import java.text.MessageFormat;

public class InvalidKeyOfData extends Exception{

    private String invalidKey;

    public InvalidKeyOfData(String invalidKey){
        this.invalidKey = invalidKey;
    }

    @Override
    public String toString(){
        return MessageFormat.format("A chave {0} não é válida", invalidKey);
    }
}
