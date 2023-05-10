package com.kg.fss.testClass.BasicIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntiBody {

    //抗体类

    private static int dimension;//目标维数

    private double gene[];//抗体串

    private double affinityDegreeWithAntigen;//与抗原的亲和度

    private double expect;//期望值

    private double concentrations;//浓度

    public int getDimension() {
        return dimension;
    }

    public double[] getGene() {
        return gene;
    }

    public double getExpect() {
        return expect;
    }

    public double getConcentrations() {
        return concentrations;
    }

    public double getAffinityDegreeWithAntigen() {
        return affinityDegreeWithAntigen;
    }

    public AntiBody(int dimension){
        this.dimension = dimension;
        gene = new double[dimension];
    }

    //初始化抗体
    public void init(){
        for(int i = 0; i < dimension; i++){
            gene[i] = Math.random()*20 - 10;
        }
        calaffinityDegreeWithAntigen();
    }

    //计算与抗原的亲和度
    public void calaffinityDegreeWithAntigen(){

        double sum = 0;
        for(int i = 0; i < dimension; i++){
            sum += Math.pow(gene[i],2);
        }

        this.affinityDegreeWithAntigen = 1.0 / (1 + sum);
    }

    public double calExpect(){
        this.expect = affinityDegreeWithAntigen / concentrations;
        return this.expect;
    }

    public void mutation(int num){
        int size = gene.length;
        for (int i = 0; i < num; i++) {
            //寻找变异位置
            int at = ((int) (Math.random() * size)) % size;
            //变异后的值
            gene[at] = Math.random()*20 - 10;
        }
    }

    public static AntiBody clone(AntiBody c) {
        if (c == null || c.gene == null) {
            return null;
        }
        AntiBody copy = new AntiBody(dimension);
        copy.gene = new double[dimension];
        for (int i = 0; i < c.gene.length; i++) {
            copy.gene[i] = c.gene[i];
        }
        return copy;
    }

    //交叉
    public static List<AntiBody> cross(AntiBody p1, AntiBody p2) {
        if (p1 == null || p2 == null) { //染色体有一个为空，不产生下一代
            return null;
        }
        if (p1.gene == null || p2.gene == null) { //染色体有一个没有基因序列，不产生下一代
            return null;
        }
        if (p1.gene.length != p2.gene.length) { //染色体基因序列长度不同，不产生下一代
            return null;
        }
        AntiBody c1 = clone(p1);
        AntiBody c2 = clone(p2);
        //随机产生交叉互换位置
        int size = c1.gene.length;
        int a = ((int) (Math.random() * size)) % size;
        int b = ((int) (Math.random() * size)) % size;
        int min = a > b ? b : a;
        int max = a > b ? a : b;
        //对位置上的基因进行交叉互换
        double t;
        for (int i = min; i <= max; i++) {
            t = c1.gene[i];
            c1.gene[i] = c2.gene[i];
            c2.gene[i] = t;
        }
        List<AntiBody> list = new ArrayList<AntiBody>();
        c1.calaffinityDegreeWithAntigen();
        c2.calaffinityDegreeWithAntigen();
        list.add(c1);
        list.add(c2);
        return list;
    }

    public void calConcentration(double radius,AntiBody ab1, AntiBody ab2){
        if(ab1.equals(ab2))
            return;
        double sum = 0;
        this.concentrations = 0;
        for(int k = 0; k < dimension; k++){

            double z = ab1.getGene()[k] - ab2.getGene()[k];
            sum += Math.pow(z, 2);

        }
        if(Math.sqrt(sum) < radius){
            concentrations++;
        }
    }

}
