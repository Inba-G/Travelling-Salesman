/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

/**
 *
 * @author inba
 */

public class Prim {
    int w[][];
    int parents[];
    static double length;
    /**
     * Implementation of Prim Algorithm
     * Calculates the least distance to reach a particular node from another node
     * @param w adjacency matrix representing the weighted graph
     * @param max the vertices of the graph
     * @return parent nodes array
     * Theta(n-squared)
     */
    public int[] mstPrim(double [][] w,int max){
        double[] key;
        double infinity = Double.MAX_VALUE;
        MinHeap q = new MinHeap(max);
        parents = new int[max];
        q.add(0,0);
        for(int i=1;i<max;i++){
            q.add(Double.MAX_VALUE,i);
        }
        key = new double[max];
        for(int i=0;i<max;i++){
            key[i]=infinity;
        }
        key[0]=0;
        int u=0;
        
        while(!q.isEmpty()&&u<max){
            
            u = q.ExtractMin();
           for(int i=1;i<=max;i++){
                if(q.contains(i) && w[u][i] !=0 &&w[u][i]<key[i]){
                    key[i] = w[u][i];
                    q.setValue(i,key[i],u);
                    parents[i] =u;
                 }
            }
            
        }
        
        return parents;
    }

    
}
