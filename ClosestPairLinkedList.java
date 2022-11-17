/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package closest.pair.linked.list;

import java.util.Random;

/* Materia: Algoritmo y Complejidad
 * Curso: 3265
 * Nombre: Esteban Quintero Gonzalez
 * Codigo Estudiantil: 200158282
 * Workshop Closest Pair
 * 07-09-2022
 * Realizar un algoritmo que mida la menor distancia de un grupo de puntos
 * usando LinkedList
 */
public class ClosestPairLinkedList {

    public static void main(String[] args) {
        Random rand = new Random();
        int n; //Numero de puntos
        int[] inputs = new int[1]; //Numero de comparaciones
        int cont = 1; // Numero de input 
        long time; // Tiempo demorado
        for (n = 5000; n < 100000; n = n*3/2) {
            LinkedList lista = new LinkedList();
            // Crea una nueva lista enlazada y le da valores aleatorios
            for (int i = 0; i < n; ++i) {
                lista.insert(rand.nextInt(n) + rand.nextInt(n), rand.nextInt(4));
            }
            lista = sort(lista); // Organiza la lista enlazada
            time = System.nanoTime();
            DaC(lista , inputs); // Realiza Divide and Conquer
            time = System.nanoTime()-time;
            // Imprime el input, numero de comparaciones y tiempo 
            System.out.println(cont + " " + inputs[0] + " " + time); 
            cont++;
        }
    }
    //Algoritmo para organizar las listas enlazadas 
    public static LinkedList sort(LinkedList l) {
        Nodo ne = l.PTR;
        int[][] matriz = new int[l.size][2];
        int tam = 0;
        while (ne != null) {
            matriz[tam][0] = ne.x;
            matriz[tam][1] = ne.y;
            tam++;
            ne = ne.link;
        }
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam - 1; j++) {
                if (matriz[j][0] > matriz[j + 1][0]) {
                    Integer tempx = matriz[j][0];
                    Integer tempy = matriz[j][1];
                    matriz[j][0] = matriz[j + 1][0];
                    matriz[j][1] = matriz[j + 1][1];
                    matriz[j + 1][0] = tempx;
                    matriz[j + 1][1] = tempy;
                }
            }
        }
        LinkedList newlist = new LinkedList();
        for (int i = 0; i < tam; i++) {
            newlist.insert(matriz[i][0], matriz[i][1]);
        }
        return newlist; // Entrega una nueva lista organizada
    }
    // Algoritmo de fuerza bruta para hallar la distancia mas cercana
    public static double BruteForce(LinkedList l, int[] in) {
        double mindist = Double.POSITIVE_INFINITY;
        Nodo ne = l.PTR;
        Nodo save;
        while (ne.link != null) {
            in[0]++;
            int x1 = ne.x;
            int y1 = ne.y;
            ne = ne.link;
            save = ne.link;
            while (save != null) {
                in[0]++;
                int x2 = ne.x;
                int y2 = ne.y;
                double dist = ((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1));
                if (mindist > dist) {
                    in[0]++;
                    mindist = dist;
                }
                save = save.link;
            }
        }
        mindist = Math.sqrt(mindist);
        return mindist;
    }

    public static double DaC(LinkedList l, int[] in) {
        Nodo ne = l.PTR;
        if (l.size <= 3) {
            // Si hay menos de 3 puntos realiza bruteforce
            return BruteForce(l, in);
        }
        int mid = l.size / 2; // Halla la mitad de los puntos
        LinkedList lizq = new LinkedList();
        // Realiza una nueva lista para la mitad de los puntos de la parte izquierda
        for (int i = 0; i < mid; i++) {
            lizq.insert(ne.x, ne.y);
            ne = ne.link;
        }
        LinkedList lder = new LinkedList();
        // Realiza una nueva lista para la otra mitad de puntos
        while (ne.link != null){
            lder.insert(ne.x, ne.y);
            ne = ne.link;
        }
        
        Double idist = DaC(lizq, in); //Llamado recursivo con la primera mitad
        Double ddist = DaC(lder, in); //Llamado recursivo con la segunda mitad
        
        Double dist = Math.min(idist, ddist);
        return dist;
    }
}
