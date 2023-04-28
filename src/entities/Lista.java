package entities;

public class Lista {

	private Node first; // início da lista encadeada
	private Node current; // nó atual da lista encadeada

	// construtor da lista vazia
	public Lista() {
		this.first = null;
		this.current = null;
	}

	public Lista(Node first, Node current) {
		super();
		this.first = first;
		this.current = current;
	}

	// método para inserir um elemento na lista
	public void insert(Object data) {
		Node newNode = new Node(data);
		newNode.setNext(first);
		first = newNode;
		current = newNode;
	}

	// método para adicionar um elemento no final da lista
	public void add(Object data) {
		Node newNode = new Node(data);
		if (first == null) {
			first = newNode;
		} else {
			Node last = first;
			while (last.getNext() != null) {
				last = last.getNext();
			}
			last.setNext(newNode);
		}
		current = newNode;
	}

	// método para remover um elemento da lista
	public void delete() {
		if (current == null) {
			throw new IllegalStateException("Não há elemento para remover");
		}
		if (first == current) {
			first = current.getNext();
		} else {
			Node previous = first;
			while (previous.getNext() != current) {
				previous = previous.getNext();
			}
			previous.setNext(current.getNext());
		}
		current = current.getNext();
	}

	// método para obter os dados do elemento atual
	public Object info() {
		if (current == null) {
			throw new IllegalStateException("Não há elemento atual");
		}
		return current.getData();
	}

	// método para avançar para o próximo elemento da lista
	public void next() {
		if (current == null) {
			throw new IllegalStateException("Não há elemento atual");
		}
		current = current.getNext();
	}

	// método para ir para o último elemento da lista
	public void last() {
		if (first == null) {
			throw new IllegalStateException("A lista está vazia");
		}
		current = first;
		while (current.getNext() != null) {
			current = current.getNext();
		}
	}

	// getters e setters do nó
	public Node getFirst() {
		return first;
	}

	public void setFirst(Node first) {
		this.first = first;
	}

	public Node getCurrent() {
		return current;
	}

	public void setCurrent(Node current) {
		this.current = current;
	}

	// classe interna para representar um nó da lista encadeada
	private static class Node {

		private Object data; // informação armazenada no nó
		private Node next; // nó atual da lista encadeada

		// construtor do nó
		public Node(Object data) {
			this.data = data;
			this.next = null;
		}

		// getters e setters
		public Object getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
}