package com.gestion_persona.to;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Data
@Getter
@Setter
public class Response {
     private boolean exito;
     private String msj;

     public boolean isExito() {
          return exito;
     }

     public void setExito(boolean exito) {
          this.exito = exito;
     }

     public String getMsj() {
          return msj;
     }

     public void setMsj(String msj) {
          this.msj = msj;
     }
}
