package com.example.goodjob.classes;

public class ValidSession {

    public static User usuarioLogueado = null;
    private static final String LOCAL = "https://192.168.1.110";
    public static final String IP = LOCAL + "/Conexiones";
    public static final String IP_IMAGENES_ACTIVIDADES = LOCAL + "/imagenes-actividades";
    public static final String IMAGENES_ACTIVIDADES_GUARDADAS = IP_IMAGENES_ACTIVIDADES + "/imagenes-subidas";
}
