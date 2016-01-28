/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author luthfifadil
 */
public class B {
    
        ArrayList<A> individu = new ArrayList<>();
        int pops ;
        int gens ;
        int kl;
        ArrayList<A> parent;
        ArrayList<A> newIndividu = new ArrayList<>();
        double LearningRate = 0.5;

    public B( int pops, int gens,int kl) {
        this.pops = pops;
        this.gens = gens;
        this.kl = kl;
        initPopulasi();
    }
       
    public void initPopulasi(){
        double minimum = -10;
        for (int j=0; j<pops; j++){
            ArrayList<Double> kr = new ArrayList<>();
            for (int i=0; i<kl; i++){
                kr.add(minimum + Math.random()*10);
            }
            individu.add(new A(kr));
        }
    }
    
    public void seleksiOrangTua(){
        parent = new ArrayList<>();
        for (int j=0; j<pops*2; j++){
            for (int i=0; i<2; i++){
                int terpilih = (int) Math.random()*10;
                parent.add(individu.get(terpilih));
            }
        }
    }
    
    public void rekombinasi(){
        newIndividu = new ArrayList<>();
        for (int i=0; i<parent.size(); i=i+2){
            ArrayList<Double> kr = new ArrayList<>();
            for (int j=0; j<kl; j++){
                kr.add((parent.get(i).kr.get(j)+parent.get(i+1).kr.get(j))/2);
            }
            newIndividu.add(new A(kr));
        }
    }
    
    
    public void mutasi(){
        Random a = new Random();
        
        for (int i=0 ; i<newIndividu.size(); i++){
            ArrayList<Double> kr = new ArrayList<>();
            double b = a.nextGaussian();
            double tau = newIndividu.get(0).kr.get(2)*Math.exp(LearningRate*b);
            for (int j=0; j<1; j++){
                double c = newIndividu.get(0).kr.get(j)+(tau*b);
//                newIndividu.get(i).kr.set(j,c+(tau*b));
                kr.add(c);
            }
            kr.add(tau);
            newIndividu.remove(0);
            newIndividu.add(new A(kr));
        }
    }
    
    public void seleksiSurvivor(){
        A elitism;
        int max = 0;
        for(int j=0; j<newIndividu.size(); j++){
            if (individu.get(max).fitness < individu.get(j).fitness){
                max = j ;
            }
        }
        elitism = individu.get(max);
        individu = new ArrayList<>();
        individu.add(elitism);
        for (int i=0; i<pops-1; i++){
            max = 0;
            for(int j=0; j<newIndividu.size(); j++){
                if (newIndividu.get(max).fitness < newIndividu.get(j).fitness){
                    max = j ;
                }
            }
            individu.add(newIndividu.remove(max));
        }
    }
    
    public void run(){
        for (int i=0; i<gens; i++){
            seleksiOrangTua();
            rekombinasi();
            mutasi();
            seleksiSurvivor();
            System.out.println(""+individu.get(0).x+" "+individu.get(0).y+" "+individu.get(0).fitness);
        }
    }
}

