package Negocio;

public class Nodo <T>{
	private Nodo<T> izq; // hijo izquierdo
	private Nodo<T> der; // hijo derecho
	private int factorEquilibrio; // factor de equilibrio
	private T elemento; // los datos como elementos del arbol avl
						
	// Constructores
	public Nodo(T elemento) { this(elemento, null, null); }

	public Nodo(T elemento, Nodo<T> izq, Nodo<T> der) { this.elemento = elemento; this.izq = izq; this.der = der; this.factorEquilibrio = 0; }

	public Nodo<T> getIzq() { return this.izq; }

	public void setIzq(Nodo<T> izq) { this.izq = izq; }

	public Nodo<T> getDer() { return this.der; }

	public void setDer(Nodo<T> der) { this.der = der; }

	public int getFactorEquilibrio() { return this.factorEquilibrio; }

	public void setFactorEquilibrio(int factorEquilibrio) { this.factorEquilibrio = factorEquilibrio; }

	public T getElemento() { return this.elemento; }

	public void setElemento(T elemento) { this.elemento = elemento; }
}
