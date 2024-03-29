package Negocio;

import java.util.ArrayList;

public class ArbolBinario<T> {
	private Nodo<T> raiz;

	public ArbolBinario() { this.raiz = null; }

	public Nodo<T> getRaiz() { return this.raiz; }

	public void setRaiz(Nodo<T> raiz) { this.raiz = raiz; }
	
	//Busca los elementos de un arbol binario general, a partir de un dato dado
	public boolean buscarElemento(T elemento) { return this.buscarElemento(this.raiz, elemento); }

	private boolean buscarElemento(Nodo<T> arbol,T elemento) {
		if (arbol != null) {
			if (arbol.getElemento().equals(elemento)) return true;
			else if (this.buscarElemento(arbol.getIzq(), elemento)) return true;
			else if (this.buscarElemento(arbol.getDer(), elemento)) return true;
		}
		return false;
	}
	
	//Muestra todo lo que contiene el arbol con sus respectivos niveles
	public void mostrarArbolSangrado() { this.mostrarArbolSangrado(this.raiz, 0); }

	private void mostrarArbolSangrado(Nodo<T> arbol, int nivel) {
		if (arbol != null) {
			this.mostrarArbolSangrado(arbol.getIzq(), nivel + 1);
			System.out.println(" " + nivel + " " + arbol.getElemento());
			this.mostrarArbolSangrado(arbol.getDer(), nivel + 1);
		}
	}
	
	//Retorna el nodo de un elemento dado, si se encuentra en el arbol, de lo contrario retorna nulo
	public Nodo<T> retornarNodo(T elemento) { return this.retornarNodo(this.raiz, elemento); }

	private Nodo<T> retornarNodo(Nodo<T> arbol, T elemento){
		if (arbol != null) {
			if (elemento.equals(arbol.getElemento())) return arbol;
			if (this.buscarElemento(arbol.getIzq(), elemento)) return this.retornarNodo(arbol.getIzq(), elemento);
			else if (this.buscarElemento(arbol.getDer(), elemento)) return this.retornarNodo(arbol.getDer(), elemento);
		}
		return null;
	}
	
	//Permite Ingresar el primer Elemento
	public void insertar(T elemento) { this.raiz = new Nodo<T>(elemento); }
	
	//Permite ingresar elementos cuando ya el arbol ya tiene una raiz
	public void insertar(T padre, T hijo, String tHijo){
		Nodo<T> temp = null;
		if (raiz != null) {
			temp = this.retornarNodo(padre);
			temp.insertarNodo(temp, hijo, tHijo);
		}
	}
	
	//Recorrido PreOrden
	private void preOrden() { this.preOrden(this.raiz); }

	private void preOrden(Nodo<T> aux) {
		if (aux != null) {
			System.out.println(" " + aux.getElemento());
			this.preOrden(aux.getIzq());
			this.preOrden(aux.getDer());
		}
	}
	
	//Recorrido InOrden
	private void inOrden() { this.inOrden(this.raiz); }

	private void inOrden(Nodo<T> aux) {
		if (aux != null) {
			this.inOrden(aux.getIzq());
			System.out.println(" " + aux.getElemento());
			this.inOrden(aux.getDer());
		}
	}
	
	//Recorrido PostOrden
	private void postOrden() { this.postOrden(this.raiz); }

	private void postOrden(Nodo<T> aux) {
		if (aux != null) {
			this.postOrden(aux.getIzq());
			this.postOrden(aux.getDer());
			System.out.println(" " + aux.getElemento());
		}
	}
	
	//Muestra los recorridos postOrden,InOrden,PreOrden
	public void mostrarRecorridos() {
		System.out.println("PostOrden");
		this.postOrden();
		System.out.println("InOrden");
		this.inOrden();
		System.out.println("PreOrden");
		this.preOrden();
	}
	
	//Saca el Nodo del hermano, de un elemento ingresado
	private Nodo<T> buscarHermano(T dato) {
		Nodo<T> padre = this.buscarPadre(dato);
		if(padre != null) {
			if(this.buscarElemento(padre.getIzq(), dato)) return padre.getDer();
			else if(this.buscarElemento(padre.getDer(),dato)) return padre.getIzq();
		}
		return padre;
	}
	
	//Saca el nodo padre de un elemento dado
	public Nodo<T> buscarPadre(T dato) { return this.buscarPadre(this.raiz, dato, this.raiz); }

	private Nodo<T> buscarPadre(Nodo<T> arbol, T dato, Nodo<T> padre) {
		if(arbol != null) {
			if (dato.equals(arbol.getElemento())) {
				if (!dato.equals(this.raiz.getElemento())) return padre;
				else return null;
			}else if (this.buscarElemento(arbol.getIzq(), dato)) return this.buscarPadre(arbol.getIzq(), dato, arbol);
			else if (this.buscarElemento(arbol.getDer(), dato)) return this.buscarPadre(arbol.getDer(), dato, arbol);
		}
		return arbol;
	}
	
	//Retorna un cadena donde especifica el padre y sus hermanos(si los tiene) de un elemento dado
	public String retornarPadreHermano(T elemento) {
		Nodo<T> padreDato = this.buscarPadre(elemento);
		Nodo<T> hermanoDato = this.buscarHermano(elemento);
		if (this.buscarElemento(elemento)) {
			if (padreDato != null) {
				if (hermanoDato != null) return elemento + " El padre es " + padreDato.getElemento() + " y su hermanos es " + hermanoDato.getElemento();
				else return elemento + " El padre es " + padreDato.getElemento() + " y no tiene hermanos";
			}else return elemento + " Es la raiz del arbol no tiene padres y hermanos";
		}
		return "El nodo no se encuentra en el arbol";
	}
	
	//Muestra los nodos que son hojas
	public ArrayList<T> sacarHojas() {
		ArrayList<T> hojas = new ArrayList<T>();
		this.sacarHojas(raiz, hojas);
		return hojas;
	}

	public void sacarHojas(Nodo<T> arbol, ArrayList<T> hojas) {
		if (arbol != null) {
			this.sacarHojas(arbol.getIzq(), hojas);
			if (this.esHoja(arbol)) hojas.add((T)arbol.getElemento());
			this.sacarHojas(arbol.getDer(), hojas);
		}
	}
	
	//Valida si un nodo es hoja retornando true o false
	private boolean esHoja(Nodo<T> arbol) {
		if (arbol.getIzq() == null && arbol.getDer() == null) return true;
		return false;
	}
}
