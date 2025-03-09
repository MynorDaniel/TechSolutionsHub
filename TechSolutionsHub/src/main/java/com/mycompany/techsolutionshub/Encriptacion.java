/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.techsolutionshub;

import java.util.Base64;

/**
 *
 * @author mynordma
 */
public class Encriptacion {
    
    public String encriptar(String palabra){
        // Convertir la palabra en bytes
        byte[] bytes = palabra.getBytes();
        // Encriptar usando Base64
        String palabraEncriptada = Base64.getEncoder().encodeToString(bytes);
        return palabraEncriptada;
    }
}
