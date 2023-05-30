package com.spectrum.crudventas.model;

import lombok.Data;

@Data
public class Cliente {
        private int id;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private String ciudad;
        private int categoria;

        public Cliente(String nombre, String apellido1, String apellido2, String ciudad, int categoria) {
                this.nombre = nombre;
                this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.ciudad = ciudad;
                this.categoria = categoria;
        }
        public Cliente() {

        }
}