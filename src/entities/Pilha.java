package entities;

import java.util.Iterator;

public class Pilha<T> implements Iterable<T> {

    private Node<T> top; // o topo da pilha

    // construtor da pilha vazia
    public Pilha() {
        this.top = null;
    }

    // método para empilhar um elemento na pilha
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
    }

    // método para desempilhar um elemento da pilha
    public T pop() {
        if (top == null) {
            throw new IllegalStateException("A pilha está vazia");
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    // método para verificar qual o elemento no topo da pilha
    public T peek() {
        if (top == null) {
            throw new IllegalStateException("A pilha está vazia");
        }
        return top.getData();
    }

    // método para verificar se a pilha está vazia
    public boolean isEmpty() {
        return top == null;
    }

    // método para imprimir todos os elementos da pilha
    public void printStack() {
        Node<T> current = top;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    // classe interna para representar um nó da pilha
    private static class Node<T> {

        private T data; // informação armazenada no nó
        private Node<T> next; // próximo nó da pilha

        // construtor do nó
        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        // getters e setters
        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new PilhaIterator();
    }

    // classe interna que implementa o Iterator da Pilha
    private class PilhaIterator implements Iterator<T> {
        private Node<T> current;

        public PilhaIterator() {
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("Não há mais elementos");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}