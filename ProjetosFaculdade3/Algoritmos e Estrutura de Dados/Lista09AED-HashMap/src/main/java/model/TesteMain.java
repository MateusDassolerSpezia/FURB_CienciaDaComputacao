/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author mdspezia
 */
public class TesteMain {
    public static void main(String[] args) {
        MapaDispersao<Aluno> mapa = new MapaDispersao(53);
        
        Aluno ob1 = new Aluno(12000, "Jean", LocalDate.of(2000, Month.JANUARY, 1));
        Aluno ob2 = new Aluno(12000, "Mateus", LocalDate.of(2000, 1, 1));
        
        System.out.println("Igual = " + ob1.equals(ob2));
        
        NoMapa<Aluno> no1 = new NoMapa<>();
        no1.setChave(100);
        no1.setInfo(ob1);
        
        NoMapa<Aluno> no2 = new NoMapa<>();
        no2.setChave(100);
        no2.setInfo(ob2);

        mapa.inserir(100, ob1);
        mapa.inserir(100, ob2);
        
        System.out.println("Igual = " + no1.equals(no2));
        System.out.println(mapa.buscar(100).getNome());
        
        MapaDispersao teste = new MapaDispersao(20);
        teste.inserir(112, ob1);
        teste.inserir(115, ob2);
        teste.realocarMapa(10);
    }
}
