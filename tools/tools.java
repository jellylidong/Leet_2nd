import java.util.*;
public class tools {
	public static <E> void printList(List<E> list){
		for(E node:list)
			if(node == null)
				System.out.println("[]");
			else
				System.out.print("[" + node + "]" + "");
	}
	
	
	
	public static <E> void print2dList(List<List<E>> lists){
		for(List<E> list: lists){
			printList(list);
			System.out.println("");
		}
	}
	
	
	public static void print2dArray(int[][] arr){
		for(int i = 0; i < arr[0].length; i++){
			for(int j = 0; j < arr.length; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static <E>  void printArray(E[] arr){
		for(int j = 0; j < arr.length; j++){
			System.out.print(arr[j]+" ");
		}
		System.out.println();
	}
	
	public static char[][] transStrToChar(String[] strArray){
		int width = strArray[0].length();
		int height = strArray.length;
		char[][] c = new char[height][width];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				c[i][j] = strArray[i].charAt(j);
			}
		}
		return c;
	}

	public  static List<List<Integer>> gen2dList(int[][] arr){
		List<List<Integer>> lists = new ArrayList<List<Integer>>();
		for(int i = 0; i < arr.length; i++){
			List<Integer> list = new ArrayList<Integer>();
			for(int j = 0; j < arr[i].length; j++)
				list.add(arr[i][j]);
			lists.add(list);
		}
		return lists;
	}
	
	public static void printTreeInorder(TreeNode root){
		if(root.left != null)
			printTreeInorder(root.left);
		System.out.print(root.val+" ");
		if(root.right != null)
			printTreeInorder(root.right);
	}
	
	public static void printTreePostorder(TreeNode root){
		if(root.left != null)
			printTreeInorder(root.left);
		
		if(root.right != null)
			printTreeInorder(root.right);
		
		System.out.print(root.val+" ");
	}
	
	public static void printTreePreorder(TreeNode root){
		System.out.print(root.val+" ");
		if(root.left != null)
			printTreeInorder(root.left);
		
		if(root.right != null)
			printTreeInorder(root.right);
		
		
	}
}

