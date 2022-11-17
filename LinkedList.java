/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package closest.pair.linked.list;

/**
 *
 * @author esteb
 */
public class LinkedList {

    Nodo PTR;
    int size;

    public LinkedList() {
        this.PTR = null;
        size = 0;
    }

    public void insert(int x, int y) {
        size++;
        if (PTR == null) {
            PTR = new Nodo(x, y);
        } else {
            Nodo p = PTR;
            while (p.link != null) {
                p = p.link;
            }
            p.link = new Nodo(x, y);
        }
    }
}
