package com.kg.fss.util;

/**
 * @program: IAArg
 * @description: test
 * @author: plf
 * @created: 2023/06/20 15:56
 */

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws Exception {

        /*
         * 读取数据
         */
        BufferedReader br = new BufferedReader(new FileReader("C:\\javaProject\\fss\\src\\main\\java\\com\\kg\\fss\\util\\input_assgin01_02.dat"));
        String s = null;
        int size = Integer.parseInt(br.readLine());
        /*
         * 1 定义候选点坐标以及每个点对应的权重
         */
        double[] x = new double[size];
        double[] y = new double[size];
        double[] xweight = new double[size];
        double[] yweight = new double[size];
        int i = 0;
        while((s = br.readLine()) != null){
            String[] a = s.split(",");
            x[i] = Double.parseDouble(a[0]);
            y[i] = Double.parseDouble(a[1]);
            xweight[i] = Double.parseDouble(a[2]);
            yweight[i] = Double.parseDouble(a[2]);
            i++;
        }

        initarea(x, y, xweight, yweight);
        System.out.println();
    }




    /**
     * 快速排序(同时将每个点对应的权值依次做了调整)
     *
     * @param attr  待排序数组（轴上点坐标）
     * @param weight  权值
     * @param low  待排序数组最低点
     * @param height  待排序数组最高点
     */
    public static void pxres(double[] attr, double[] weight, int low, int height){
        double temp = 0;
        double temp1 =0;
        int i = low;
        int j = height;

        if(low < height){
            temp = attr[low];
            temp1 = weight[low];

            while(i != j){
                while(j > i && attr[j] >= temp){
                    --j;
                }
                if(i < j){
                    attr[i] = attr[j];
                    weight[i] = weight[j];
                    ++i;
                }
                while(i < j && attr[i] < temp){
                    ++i;
                }
                if(i < j){
                    attr[j] = attr[i];
                    weight[j] = weight[i];
                    --j;
                }
            }

            attr[i] = temp;
            weight[i] = temp1;
            pxres(attr, weight, low, i - 1);
            pxres(attr, weight, i + 1, height);
        }
    }

    /**
     * 对每个轴求带权中位数对应的坐标
     * @param addr  轴上坐标
     * @param Weights  权重
     * @param zhou  维度或经度标识
     * @return 带权中位数
     */
    public static double axis(double [] addr, double [] Weights, String zhou){

        /*
         * 对每个轴坐标进行快速排序，同时调整对应的权重
         */

        pxres(addr, Weights, 0, addr.length - 1);
        System.out.println("排序后的" + zhou + "坐标为：");
        for(int i = 0; i < addr.length; i++){
            System.out.print(addr[i] + " ");
        }
        System.out.println("\n排序后的" + zhou + "坐标对应的权值为：");
        for(int i = 0; i < Weights.length; i++){
            System.out.print(Weights[i] + " ");
        }

        /*
         * 3 所有候选点权值之和
         */

        double sumweight = 0;
        for(int i = 0; i < Weights.length; i++){
            sumweight += Weights[i];
        }
        System.out.println("\n所有候选点权值之和：" + sumweight);

        /*
         * 4 求纬度方向的带权中位数
         */

        double sum = 0;
        for(int i = 0; i < Weights.length; i++){
            sum += Weights[i];
            if(sum >= sumweight / 2){
                return addr[i];
            }
        }

        return 0;
    }

    /**
     * 求飞行服务站坐标方法
     * @param Xaxis  纬度坐标点集
     * @param Yaxis  经度坐标点集
     * @param XWeights  权重
     * @param YWeights  权重
     */
    public static void initarea(double[] Xaxis, double[] Yaxis, double [] XWeights, double[] YWeights){

        /*
         * 2 对纬度，经度分别处理 (注意：对应轴排完序相对应的权值随着做对应调整，使其保持一一对应)
         * 2.1 对纬度坐标点进行快速排序
         */
        double px = axis(Xaxis, XWeights, "纬度");
        /*
         * 2.2 对经度坐标点进行快速排序
         */
        double py = axis(Yaxis, YWeights, "经度");

        /*
         * 打印邮局位置
         */
        System.out.println("飞行服务站位置为：（" + px + "," + py + ")");
    }
}

