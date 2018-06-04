package ctr;

import java.io.Serializable;

public class Fila<E> implements Serializable {

    private static final long serialVersionUID = 7022754954868471708L;
    private NoFila<E> inicio = new NoFila<E>();
    private NoFila<E> fim = new NoFila<E>();
    private int tamanho;

    @SuppressWarnings("hiding")
    class NoFila<E> implements Serializable {

        private static final long serialVersionUID = 1892641490503505230L;
        E obj;
        NoFila<E> prox;
    }

    public Fila() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    public void insira(E obj) {
        NoFila<E> novoNo = new NoFila<E>();
        novoNo.obj = obj;
        novoNo.prox = null;
        if (tamanho == 0) {
            inicio = novoNo;
            fim = novoNo;
            tamanho++;
        } else {
            fim.prox = novoNo;
            fim = novoNo;
            tamanho++;
        }
    }

    public E remova() {
        if (tamanho > 0) {
            E obj = null;

            //pega rato
            if (inicio == null) {
                //System.out.println("Inicio é null");
            }
            if (inicio != null) {
                //if (obj != null) {
                    obj = inicio.obj;
                    inicio = inicio.prox;
                    tamanho--;
               //}
                return obj;
            } else {
                return null;
            }
        }
        return null;
    }

    public boolean estaVazia() {
        if (tamanho == 0) {
            //System.out.println("Fila Vazia");
            return true;
        } else {
            return false;
        }
    }

    public int tamanho() {
        return tamanho;
    }

    public void limpe() {
        while (!estaVazia()) {
            remova();
        }

    }
}
