package unidad2;

import java.util.ArrayList;
import java.util.List;

public class Tree {

	private TreeNode root;
	
	public Tree() {
		this.root = null;
	}
	
	
	//SE INSERTA DE FORMA ORDENADA
	public void add(Integer value) {
		if (this.root == null) //SI NO HAY RAIZ
			this.root = new TreeNode(value); //EL NUEVO NODO PASA A SER LA NUEVA RAIZ
		else
			this.add(this.root,value); //SI YA HAY RAIZ, EMPIEZO A LLAMAR RECURSIVAMENTE
	}
	
	private void add(TreeNode actual, Integer value) { //COMIENZA POR RAIZ, Y VA AVANZANDO CADA LLAMADO
		if (actual.getValue() > value) { //EL VALOR INGRESADO ES MENOR AL NODO DONDE ESTOY PARADO
			if (actual.getLeft() == null) {  //PREGUNTO SI EL LADO IZQUIERDO DEL NODO ESTA VACIO
				TreeNode temp = new TreeNode(value); //INSTANCIO EL NUEVO NODO CON NUEVO VALOR
				actual.setLeft(temp); //EL NODO ACTUAL SETEA A SU IZQUIERDA EL NUEVO NODO DE MENOR VALOR
			} else {  
				add(actual.getLeft(),value); //CASO CONTRARIO, SI EL LADO IZQUIERDO ESTA OCUPADO, RECORRO
			}
		} else if (actual.getValue() < value) { //SI EL VALOR INGRESADO ES MAYOR AL ACTUAL
			if (actual.getRight() == null) {  //PREGUNTO SI TIENE EL LADO DERECHO DISPONIBLE
				TreeNode temp = new TreeNode(value); //INSTANCIO 
				actual.setRight(temp); //UBICO EN EL LADO DERECHO DEL ACTUAL
			} else {
				add(actual.getRight(),value); //CASO CONTRRIO, SI EL DERECHO ESTA OCUPADO, RECORRO
			}
		}
	}
	
	
	//OBTENER RAIZ
	public Integer getRoot() {
		return this.root.getValue();
	}
	
	// ES UNA HOJA
	public boolean isItALeaf(TreeNode treeNode) {
		return treeNode.getLeft() == null && treeNode.getRight() == null;
	}
	
	//PREGUNTAR SI ESTA VACIO
	public boolean isEmpty() {
		return this.root == null;
	}
	
	
	//PREGUNTAR SI TIENE EL ELEMENTO
	public boolean hasElem(Integer value) {
		return hasElem(this.root, value);
	}
	
	private boolean hasElem(TreeNode nodoActual, Integer value) {
		
		if(nodoActual == null) {
			return false;
		}
		
		if(nodoActual.getValue() == value) {
			return true;
		}
		
		if(value>nodoActual.getValue()) {
			return hasElem(nodoActual.getRight(), value);
		}else {
			return hasElem(nodoActual.getLeft(), value);
		}
	}
	
	
	//METODO DE BORRADO DE UN NODO
	
	public boolean delete(Integer value) {
		TreeNode nodoActual = this.root; //EL NODO ACTUAL COMIENZA POR LA RAIZ
		TreeNode aux = null; // NODO AUXILIAR DE MEMORIA
		TreeNode parent = null; //AUXILIAR QUE ADOPTA EL ROL DEL PADRE
		return delete(nodoActual, value, aux, parent); // LLAMO METODO RECURSIVO
	}
	
	private boolean delete(TreeNode nodoActual, Integer value, TreeNode aux, TreeNode parent) {
		if(nodoActual == null) { //SI LA RAIZ NO EXISTE
			return false; //NO TENEMOS UN ARBOL
		}
		
		
		parent = nodoActual; //EN CASO QUE EXISTA EL ARBOL, COMIENZA PROCESO CON PADRE GUARDANDO INFO
		if(nodoActual.getLeft()!=null && value<nodoActual.getValue()) { //SI VALOR ES MENOR
			return delete(nodoActual.getLeft(), value, aux, parent); //RECORRO POR LA IZQUIERDA
		}
		if(nodoActual.getRight()!=null && value>nodoActual.getValue()) {//SI ES MAYOR
			return delete(nodoActual.getRight(), value, aux, parent);//RECORRO POR LA DERECHA
		}
		
		
		
		if(value == this.getRoot()) { //SI EL VALOR BUSCADO ES LA RAIZ Y QUIERO ELIMINARLA
			nodoActual = getMoreLeftNodeFromRightTree(nodoActual.getRight()); //BUSCO NMI ARBOL DERECHO
			this.root = nodoActual; //EL NODO BUSCADO REEMPLAZA LA RAIZ, SIENDO LA NUEVA RAIZ
			return true;
		}
		
		if(nodoActual.getValue() == value) { //AL RECORRER, SI SE ENCUENTRA EL NODO BUSCADO
			if(isItALeaf(nodoActual)) { //PRIMERO CONSULTO QUE SEA UNA HOJA
				nodoActual = null;	//SI ES HOJA, SIMPLEMENTE SE ELIMINA.
			}
			if(nodoActual.getRight()!=null && nodoActual.getLeft()!=null) { //SI TIENE 2 HIJOS
				aux = getMoreLeftNodeFromRightTree(nodoActual.getRight()); //BUSCO NMI DE LA DERECHA
				parent.setLeft(aux); //EL PADRE QUE PREVIAMENTE ESTA GUARDADO, CAMBIA HIJO IZQUIERDO POR EL NMI
				nodoActual = null; //UNA VEZ CAMBIADO EL HIJO IZQUIERDO DE PADRE, ELIMINO NODO ACTUAL, HIJO ANTERIOR
			}
			if(nodoActual.getLeft()!=null) { //SI SOLO TIENE UN HIJO, Y ES HIJO IZQUIERDO
				aux = nodoActual.getLeft(); //ALMACENO EN AUXILIAR EL HIJO IZQUIERDO DEL ACTUAL
				parent.setLeft(aux); //EL PADRE DEL ACTUAL READOPTA EL AUXILIAR
				nodoActual = null; //Y TERMINO DE ELIMINAR EL ACTUAL HABIENDO RESGUARDADO SU HIJO IZQUIERDO AL PADRE
			}else {
				aux = nodoActual.getRight(); //SI TIENE UN HIJO, PERO ES EL DERECHO, TOMO HIJO DERECHO DE ACTUAL
				parent.setRight(aux); //EL PADRE REEMPLAZA AL ACTUAL POR EL SUB HIJO DERECHO
				nodoActual=null; //ELIMINO EL ACTUAL, HABIENDO RESGUARDADO SU HIJO DERECHO AL PADRE
			}
			return true;
		}
		return false;
	}
	
	// OBTENER EL MÁS A LA IZQUIERDA DEL SUBÁRBOL DERECHO
	private TreeNode getMoreLeftNodeFromRightTree(TreeNode nodoActual) {
		while (nodoActual != null && nodoActual.getLeft() != null) {
			nodoActual = nodoActual.getLeft();
		}
		return nodoActual;
	}

	//OBTENER ALTURA
	public int getHeight() {
		TreeNode root = this.root;
		return getHeight(root, 0);
	}
	
	private int getHeight(TreeNode actual, int count) {
	    if (actual == null) {
	        return count - 1; 
	    }

	    int left = getHeight(actual.getLeft(), count + 1);
	    int right = getHeight(actual.getRight(), count + 1);

	    return Math.max(left, right);
	}
	
	//OBTENER LA RAMA MAS LARGA
	
	public List<Integer> getLongestBranch(){
		TreeNode root = this.root;
		List<TreeNode> lista = getLongestBranch(root);
		List<Integer> salida = new ArrayList<>();
		for (TreeNode treeNode : lista) {
			salida.add(treeNode.getValue());
		}
		return salida ;
	}
	
	private List<TreeNode> getLongestBranch(TreeNode nodoActual){
		List<TreeNode> salida = new ArrayList<>();
		List<TreeNode> salidaIzquierda = new ArrayList<>();
		List<TreeNode> salidaDerecha = new ArrayList<>();
		
		salida.add(nodoActual); //ATENCION CON AGREGAR PARA QUE NO SE PIERDA LO ENCONTRADO
		
		if(!isItALeaf(nodoActual)) {
			
			if(nodoActual.getLeft()!= null) {
				salidaIzquierda.addAll(getLongestBranch(nodoActual.getLeft())); //SIEMPRE PONER ADDALL
			}
			if(nodoActual.getRight()!= null) {
				salidaDerecha.addAll(getLongestBranch(nodoActual.getRight())); //SIEMPRE PONER ADDALL
			}
			
			
			if(salidaIzquierda.size()>=salidaDerecha.size()) {
				salida.addAll(salidaIzquierda);
			}else {
				salida.addAll(salidaDerecha);
			}
		}
		
		
		return salida;
		
	}
	
	//OBTENER LISTADO DE NODOS DE FRONTERA
	
	public List<Integer> getFrontera(){
		TreeNode root = this.root;
		List<TreeNode> lista = getFrontera(root);
		List<Integer> salida = new ArrayList<>();
		for (TreeNode treeNode : lista) {
			salida.add(treeNode.getValue());
		}
		return salida;
	}
	
	private List<TreeNode> getFrontera(TreeNode nodoActual){
		List<TreeNode> lista =  new ArrayList<>();
		
		if(isItALeaf(nodoActual)) {  //SI ES HOJA, ES FRONTERA
			lista.add(nodoActual);   //ATENCION CON AGREGAR PARA QUE NO SE PIERDA LO ENCONTRADO
		}
		
		if(nodoActual.getLeft()!=null) {
			lista.addAll(getFrontera(nodoActual.getLeft())); //SIEMPRE PONER ADDALL
		}
		if(nodoActual.getRight()!=null) {
			lista.addAll(getFrontera(nodoActual.getRight())); //SIEMPRE PONER ADDALL
		}
		
		return lista;
		
	}
	
}