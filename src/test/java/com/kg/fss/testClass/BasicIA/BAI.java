package com.kg.fss.testClass.BasicIA;

import java.util.ArrayList;
import java.util.List;

public class BAI {

    //抗体群
    public List<AntiBody> antiBodies;

    //记忆细胞群
    public List<AntiBody>  memoryCells;

    //抗体数量
    public int abNum;

    //记忆细胞数量
    public int mcNum;

    //抗体间半径
    public double radius;//0.02

    public int dimension;

    /**最大变异步长*/
    private int maxMutationNum = 10;

    public double mutationRate;//变异概率

    //初始化抗体群
    public void init(int num, int dimension, int mNum, double radius, double mutationRate){

        this.abNum = num;
        this.mcNum = mNum;
        this.dimension = dimension;
        this.radius = radius;
        this.mutationRate = mutationRate;
        antiBodies = new ArrayList<AntiBody>(num+mNum);
        memoryCells = new ArrayList<AntiBody>(mNum);

        for(int i = 0 ; i < num+mNum; i++){
            AntiBody ab = new AntiBody(dimension);
            ab.init();
            antiBodies.add(ab);
        }

        calConcentrations();
    }

    public void memoryCellSelection(){
        sortByAffinityDegree();
        boolean isFirst = false;
        if(memoryCells.size() == 0){
            isFirst = true;
        }
        List<AntiBody> list = new ArrayList<AntiBody>();
        for(int i = 0; i < antiBodies.size(); i++){
            list.add(antiBodies.get(i));
        }


        for(int i = 0; i < mcNum; i++){//选择加入记忆细胞
            if(isFirst)
                memoryCells.add(antiBodies.get(i));
            else {
                if(memoryCells.get(mcNum-1).getAffinityDegreeWithAntigen()>=list.get(0).getAffinityDegreeWithAntigen()){
                    break;
                }
                if (list.get(0).getAffinityDegreeWithAntigen() > memoryCells.get(i).getAffinityDegreeWithAntigen()) {
                    //判断是否有重复的
                    boolean isExist = false;
                    for(int j = 0; j < i; j++){
                        if(list.get(0).getAffinityDegreeWithAntigen() == memoryCells.get(j).getAffinityDegreeWithAntigen()){
                            isExist = true;
                            break;
                        }
                    }
                    if(isExist){
                        list.remove(0);
                        continue;
                    }
                    //将亲和度高的插入到记忆细胞中
                    for (int j = mcNum - 1; j > i; j--) {
                        memoryCells.set(j, memoryCells.get(j - 1));
                    }
                    memoryCells.set(i, list.get(0));
                    list.remove(0);
                }
                else
                    continue;


            }
        }

    }

    //计算抗体与抗体间的亲和度
    public void calConcentrations(){

        for(int i = 0; i < antiBodies.size(); i++){

            for(int j = 0; j < antiBodies.size(); j++){

                if(i == j){
                    continue;
                }
                antiBodies.get(i).calConcentration(radius, antiBodies.get(i), antiBodies.get(j));

            }
            antiBodies.get(i).calExpect();
        }

    }

    //根据与抗原的亲和度对抗体群从大到小排序
    public void sortByAffinityDegree(){

        for(int i = 0; i < antiBodies.size() - 1; i++){

            for(int j = i+1; j < antiBodies.size(); j++){

                if(antiBodies.get(i).getAffinityDegreeWithAntigen() < antiBodies.get(j).getAffinityDegreeWithAntigen()){
                    AntiBody ab = new AntiBody(dimension);
                    ab = antiBodies.get(i);
                    antiBodies.set(i, antiBodies.get(j));
                    antiBodies.set(j, ab);
                }

            }

        }

    }

    public void sortByExpect(){

        for(int i = 0; i < antiBodies.size() - 1; i++){

            for(int j = i+1; j < antiBodies.size(); j++){

                if(antiBodies.get(i).getExpect() < antiBodies.get(j).getExpect()){
                    AntiBody ab = new AntiBody(dimension);
                    ab = antiBodies.get(i);
                    antiBodies.set(i, antiBodies.get(j));
                    antiBodies.set(j, ab);
                }

            }

        }

    }

    //促进和抑制
    public void promotionAndInhibition(){

        sortByExpect();
        for(int i = antiBodies.size() - 1; i > abNum; i--){
            antiBodies.remove(i);//清除低期望值的抗体
        }

    }

    //轮盘赌选择抗体
    public void selection(){
        double[] p = new double[antiBodies.size()]; ;//每个抗体被选中的概率
        double sum=0;
        //计算公式中的分母部分
        for(int i=0;i < antiBodies.size(); i++){
            sum += antiBodies.get(i) .getAffinityDegreeWithAntigen();
        }
        //计算每个抗体被选中的概率
        for(int i=0;i < antiBodies.size(); i++){

            p[i]=antiBodies.get(i) .getAffinityDegreeWithAntigen()/sum;

        }
        List<AntiBody> childantiBodies = new ArrayList<AntiBody>();
        //生成下一代种群
        while (childantiBodies.size() < abNum) {
            double selectp = Math.random();
            double selectp2 = Math.random();
            // 轮盘赌选择两个抗体
            double sumselect = 0;
            int selectAb = -1;
            int selectAb2 = -1;
            boolean label1 = false, label2 = false;
            for (int i = 0; i < antiBodies.size(); i++) {
                sumselect += p[i];
                if (sumselect >= selectp && label1 == false) {
                    selectAb = i;
                    label1 = true;
                }
                if (sumselect >= selectp2 && label2 == false) {
                    selectAb2 = i;
                    label2 = true;
                }
                if (label1 == true && label2 == true) {
                    break;
                }
            }
            if (selectAb == -1 || selectAb2 == -1)
                System.out.println("error");


            // 变异交叉
            if (Math.random() < mutationRate) {
                int mutationNum1 = (int) (Math.random() * maxMutationNum);
                int mutationNum2 = (int) (Math.random() * maxMutationNum);
                antiBodies.get(selectAb).mutation(mutationNum1);
                antiBodies.get(selectAb2).mutation(mutationNum2);
            }

            List<AntiBody> children = AntiBody.cross(antiBodies.get(selectAb), antiBodies.get(selectAb2));
            if (children != null) {
                for (AntiBody ab : children) {
                    childantiBodies.add(ab);
                }
            }


            // 新种群替换旧种群
            antiBodies.clear();
            antiBodies = childantiBodies;
            for(int i = 0; i < memoryCells.size(); i++){//选择记忆细胞加入种群
                boolean isExist = false;
                for(int j = 0; j < antiBodies.size(); j++){
                    if(memoryCells.get(i).getAffinityDegreeWithAntigen() == antiBodies.get(j).getAffinityDegreeWithAntigen()){
                        isExist = true;
                    }
                }
                if(!isExist)
                    antiBodies.add(memoryCells.get(i));
            }

        }
    }

    public void print(){
        sortByAffinityDegree();
        System.out.println("最优解集合：");
        for(int j = 0; j < antiBodies.size(); j++){
            for(int i = 0; i < dimension; i++){
                System.out.print(antiBodies.get(j).getGene()[i]+"\t");
            }
            System.out.println("\n适应度："+antiBodies.get(j).getAffinityDegreeWithAntigen()+"\n");
        }
    }

    public static void main(String args[]){

        BAI ab = new BAI();
        ab.init(50, 30, 10, 50, 0.2);
        int generation = 0;
        for(; generation < 1000; generation++){
            ab.calConcentrations();
            ab.memoryCellSelection();
            ab.promotionAndInhibition();
            ab.selection();
        }
        ab.print();
    }

}
