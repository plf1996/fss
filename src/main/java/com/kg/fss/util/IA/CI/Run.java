package com.kg.fss.util.IA.CI;

import com.kg.fss.util.IA.Graph.Graph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Run {
	public static int randomInt(int start, int end) {//生成[start, end]之间的随机数
		Random random = new Random();
		int s = random.nextInt(end)%(end-start+1) + start;
		return s;
	}

	public static double randomDouble(double start, double end) {
		//生成随机浮点数
		double result = start + Math.random() * (end - start);
		return result;
	}

	public int random_node(double capacity, Graph physic, ArrayList<Integer> result){
		//随机选取物理节点，其满足物理节点的cpu能力满足虚拟节点的cpu需求，且该物理节点没有被选取映射过
		int physic_node = randomInt(0, physic.nodes.length - 1);

		//当虚拟节点的需求大于物理节点的资源或者该物理节点已经被选中过,则不断循环，这是约束。
		while(capacity > physic.nodes[physic_node].getCapacity()
				|| result.contains(physic_node)){
			physic_node = randomInt(0, physic.nodes.length - 1);
		}
		return physic_node;
	}

	public ArrayList<Integer> random_solution(Graph virtual, Graph physic){//生成映射关系
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < virtual.nodes.length; i++){
			//对应关系,索引为0，代表虚拟节点为0，其值代表映射到哪个物理节点上
			result.add(random_node(virtual.nodes[i].getCapacity(), physic, result));
		}
		return result;//返回映射结果
	}

	public int object_function(int hop, int virtual_link_bandwidth){//映射到的物理链路成本
		return hop * virtual_link_bandwidth;
	}

	public void evaluate(ArrayList<Po> pop, Graph physic, Graph virtual){//计算解空间中所有解的值。
		//根据虚拟网络的节点与物理网络上的节点的映射关系，依次遍历虚拟网络链路，根据虚拟网络链路得到该虚拟链路的两端虚拟节点
		//根据映射关系得到映射到物理网络上的两个物理节点，用迪杰斯特拉算法得到两个物理节点的最短路径，
		//该虚拟路径的成本为跳数*虚拟链路带宽，该解的值为所有虚拟路径的成本
		//如果物理最短路径上带宽不够，则这种映射方案(解)不合理,置该解的值为最大值

		for(int i = 0; i < pop.size(); i++){
			ArrayList<Integer> result = pop.get(i).getSolution();//节点映射关系
			int cost = 0;
			int panduan = 0;
			for(int j = 0; j < virtual.links.length; j++){
				int left = virtual.links[j].getLeftID();
				int right = virtual.links[j].getRightID();
				int physic_left = result.get(left);
				int physic_right = result.get(right);
				int[] path = physic.dijkstra(physic_left, physic_right,
						virtual.links[j].getBandWidth());
				if(path == null){
					pop.get(i).setCost(Integer.MAX_VALUE);//这种方案就不要了
					panduan = 1;
					break;
				}
				//成本函数，跳数乘以带宽
				cost += object_function(path.length - 1, virtual.links[j].getBandWidth());
			}
			if(panduan == 0){
				pop.get(i).setCost(cost);
			}
			physic.refreshMatrix();//重置

		}
	}

	public double num_clones(int size, double clone_factor){//计算每个解需要克隆几个解
		return Math.floor(size * clone_factor);//例子Math.floor(1.4)=1.0
	}

	public void calculate_affinity(ArrayList<Po> pop){//计算亲和度
		//本题计算亲和度是根据文档中的公式。
		//首先计算解空间中最大值-最小值=range。如果range==0,则置亲和度为0，否则该解的亲和度=1-(解的值/range)
		Collections.sort(pop, new SortByCost());
		int range = pop.get(pop.size() - 1).getCost() - pop.get(0).getCost();
		//System.out.println("last+" + pop.get(pop.size() - 1).getCost() + "begin" + pop.get(0).getCost());
		//System.out.println("range+" + range + "\n");
		if(range == 0){
			for(int i = 0; i < pop.size(); i++) {
				pop.get(i).setAffinity(1.0);
			}
		}else{
			for(int i = 0; i < pop.size(); i++) {
				pop.get(i).setAffinity(1.0 - (double)(pop.get(i).getCost() / range));
			}
		}
	}

	public double calculate_mutation_rate(Po po, double mutate_factor) {//计算变异值
		//变异值为e^(亲和度*变异因子)
		return Math.exp(po.getAffinity() * mutate_factor);
	}

	public ArrayList<Integer> point_mutation(ArrayList<Integer> solution, double m_rate,
											 Graph virtual, Graph physic){//对解进行克隆变异
		ArrayList<Integer> content = new ArrayList<Integer>();
		for(int i = 0; i < solution.size(); i++){
			content.add(solution.get(i));
		}
		for(int i = 0; i < content.size(); i++){
			if(randomDouble(0.0, 1.0) < m_rate){//对解中的每个值，如果[0.0,1.0]中随机生成一个数小于变异值
				//则对该值进行变异，即重新随机选取物理节点进行映射
				content.set(i, random_node(virtual.nodes[i].getCapacity(), physic, content));
			}
		}
		return content;
	}

	public ArrayList<Po> clone_and_hypermutate(ArrayList<Po> pop, double clone_factor,
											   double mutate_factor, Graph virtual, Graph physic){//对解空间pop中的解进行克隆变异
		ArrayList<Po> clones = new ArrayList<Po>();
		double number_clones = num_clones(pop.size(), clone_factor);//计算每一个解要生成多少个克隆的解
		calculate_affinity(pop);//计算每个解的亲和度
		for(int i = 0; i < pop.size(); i++){
			double m_rate = calculate_mutation_rate(pop.get(i), mutate_factor);//计算每个解的变异值
			for(double j = 0; j < number_clones; j++){
				Po clone = new Po();
				clone.setSolution(point_mutation(pop.get(i).getSolution(), m_rate, virtual,
						physic));//对解进行克隆变异
				clones.add(clone);
			}
		}

		evaluate(clones, physic, virtual);
		return clones;
	}

	public ArrayList<Po> combi(ArrayList<Po> pop, ArrayList<Po> clones){//解空间的结合
		for(int i = 0; i < pop.size(); i++) {
			clones.add(pop.get(i));
		}
		return clones;
	}

	public ArrayList<Po> first_pop_size(ArrayList<Po> combination, int pop_size){//选取出前pop_size个解
		ArrayList<Po> child = new ArrayList<Po>();
		for(int i = 0; i < pop_size; i++) {
			child.add(combination.get(i));
		}
		return child;
	}

	public ArrayList<Po> random_insertion(ArrayList<Po> pop, int num_rand, int pop_size
			, Graph virtual, Graph physic){//生成随机解，合并原始解空间pop，选取出前pop_size最优的解
		if(num_rand == 0){
			return pop;
		}
		ArrayList<Po> child = new ArrayList<Po>();
		for(int i = 0; i < num_rand; i++){
			Po po = new Po();
			po.setSolution(random_solution(virtual, physic));//生成随机解
			child.add(po);
		}
		evaluate(child, physic, virtual);//计算开销。
		physic.refreshMatrix();
		ArrayList<Po> combination = combi(pop, child);
		Collections.sort(combination, new SortByCost());//验证过，没错
		pop = first_pop_size(combination, pop_size);
		//delete_null(pop);
		return pop;

	}

	public Po search(Graph physic, Graph virtual, int max_gens, int pop_size,
					 double clone_factor, int num_rand, double mutate_factor){
		ArrayList<Po> pop = new ArrayList<Po>();
		for(int i = 0; i < pop_size; i++){//生成初始解
			Po po = new Po();
			po.setSolution(random_solution(virtual, physic));//生成随机解
			pop.add(po);
		}
		evaluate(pop, physic, virtual);//计算开销。
		physic.refreshMatrix();
		Collections.sort(pop, new SortByCost());
		//delete_null(pop);//去除掉不合格的方案。
		Po best = pop.get(0);
		System.out.println("best" + pop.get(0).getCost());
		for(int i = 0; i < max_gens; i++){
			ArrayList<Po> clones = clone_and_hypermutate(pop, clone_factor,
					mutate_factor, virtual, physic);//对解进行克隆并变异，生成解空间clones
			evaluate(clones, physic, virtual);
			calculate_affinity(clones);//计算开销
			physic.refreshMatrix();
			ArrayList<Po> combination = combi(pop, clones);//原始解与变异解结合
			Collections.sort(combination, new SortByCost());//排序
			pop = first_pop_size(combination, pop_size);//选取前pop_size最优的解
			//delete_null(pop);
			//生成num_rand个随机解，和原先的解合并选取出前pop_size最优的解
			pop = random_insertion(pop, num_rand, pop_size, virtual,physic);
			pop.add(best);//结合上一次循环的最优解
			Collections.sort(pop, new SortByCost());//排序
			pop = first_pop_size(combination, pop_size);//选取前pop_size最优的解
			//delete_null(pop);
			best = pop.get(0);
			System.out.println("best-Affinity...........................");
			System.out.println(best.getAffinity());
			System.out.println("best-cost...........................");
			System.out.println(best.getCost());
		}
		Collections.sort(pop, new SortByCost());
		return pop.get(0);
	}

	public void delete_null(ArrayList<Po> pop){
		while(pop.size() > 0){
			if(pop.get(pop.size() - 1).getCost() == Integer.MAX_VALUE){
				pop.remove(pop.size() - 1);
			}else{
				break;
			}
		}
	}


	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir").toString());
		Run test = new Run();
		Graph physic=new Graph("\\src\\main\\java\\com\\kg\\fss\\util\\IA\\P1.txt",true);//55-103
		Graph virtual=new Graph("\\src\\main\\java\\com\\kg\\fss\\util\\IA\\V1.txt",false);//14-21
        /*for(int i = 0; i < physic.links.length; i++){
			System.out.println(physic.links[i].getBandWidth());
		}
		System.out.println("............");
		for(int i = 0; i < virtual.links.length; i++){
			System.out.println(virtual.links[i].getBandWidth());
		}*/
		//物理节点0容量，取值0-54
		physic.nodes[0].getCapacity();
		//虚拟节点0容量，取值0-13
		virtual.nodes[0].getCapacity();

		//链路映射，最短路径，无路是为NULL，会更新邻接矩阵
		int[] path=physic.dijkstra(1, 30, 2000);
		//邻接矩阵复原
		physic.refreshMatrix();

		//虚拟链路节点
		virtual.links[0].getLeftID();
		virtual.links[0].getRightID();

		//虚拟链路带宽
		virtual.links[0].getBandWidth();

		int max_gens = 100;//迭代20次
		int pop_size = 100;//初始抗体数量
		double clone_factor = 0.2;//克隆因子
		int num_rand = 2;//随机生成抗体的数量
		double mutate_factor = -2.5;//变异因子
		Po result = test.search(physic, virtual, max_gens, pop_size, clone_factor, num_rand, mutate_factor);
		System.out.println("final: " + result.getCost());
	}
}
class SortByCost implements Comparator{
	public int compare(Object o1, Object o2) {
		Po s1 = (Po) o1;
		Po s2 = (Po) o2;
		if(s1.getCost() > s2.getCost()) {
			return 1;
		}else if(s1.getCost() < s2.getCost()) {
			return -1;
		}
		return 0;//相等时要返回0，因为值非常精确，当差值非常小时就认为是0，需要返回0，否则会报错
	}
}
