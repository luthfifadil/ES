/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;

/**
 *
 * @author luthfifadil
 */
public class A {

    double fitness;
    double fungsi;
    ArrayList<Double> kr;
    double x;
    double y;
    
    
    //fungsi pembentukan kromosom awal 
    //individu dirandom langsung dimasukkin ke kromosom

    public A(ArrayList<Double> kr) {
        this.kr = kr;
        x = kr.get(0);
        y = kr.get(1);
        setFungsi();
        setFitness();
    }

    public void setFungsi(){
        fungsi = 1/(-1*Math.abs(Math.sin(x)*Math.cos(y)*Math.exp(Math.abs(1-(Math.sqrt(Math.pow(x,2)+Math.pow(y,2))/Math.PI)))));
    }
    public void setFitness(){
        fitness = fungsi * -1;
    }
    
}
