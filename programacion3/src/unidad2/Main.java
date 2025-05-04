package unidad2;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		Tree tree = new Tree();
		
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(3);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(7);
		TreeNode node5 = new TreeNode(11);
		TreeNode node6 = new TreeNode(12);
		TreeNode node7 = new TreeNode(16);
		TreeNode node8 = new TreeNode(42);
		TreeNode node9 = new TreeNode(12);
		TreeNode node10 = new TreeNode(43);
		TreeNode node11 = new TreeNode(51);
		
		tree.add(node4.getValue());
		tree.add(node8.getValue());
		tree.add(node11.getValue());
		tree.add(node10.getValue());
		tree.add(node7.getValue());
		tree.add(node1.getValue());
		tree.add(node2.getValue());
		tree.add(node5.getValue());
		tree.add(node6.getValue());
		tree.add(node9.getValue());
		tree.add(node3.getValue());
		
		boolean res = false;
		
		res = tree.hasElem(51);
		
		tree.delete(51);
		
		int altura = tree.getHeight();
		
		List<Integer> salidaMasLarga = tree.getLongestBranch();
		
		List<Integer> listaDeFronteras = tree.getFrontera();
		
		System.out.println(listaDeFronteras);
		
		System.out.println(altura);
		
	}

}
