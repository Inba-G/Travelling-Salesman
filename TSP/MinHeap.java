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
public class MinHeap {
    public double[] heap;
    int[] vertex;
    int size;
    boolean isEmpty;
    int maxsize;
    static double len = 0;
    static double test;
    /**
     * Constructor with size of heap as parameter
     * @param size size of the heap
     * Theta(1)
     */
    public MinHeap(int size){
        heap = new double[size+1];
        vertex = new int[size+1];
        this.size = 1;
        this.maxsize = size;
        
    }
    /**
     * Extracts the minimum node from the heap(root Node).
     * @return returns the minimum node
     * Theta(n)
     */
    public int ExtractMin(){
        int minimum = vertex[1];
        if(heap[1]!=Double.MAX_VALUE)
            len += heap[1];
        test = heap[1];
        
        vertex[1]=vertex[size];
        
        heap[1]=heap[size];
        --size;
        minHeap();
        return minimum;
    }
    /**
     * Returns the location of the left child given the parent location
     * @param pLocation location of parent node
     * @return index of the left child
     * Theta(1)
     */
    public int lC(int pLocation){
        return 2*pLocation;
    }
    /**
     * Returns the location of the right child given the parent location
     * @param pLocation location of the parent node
     * @return index of the right child
     * Theta(1)
     */
    public int rC(int pLocation){
        return 2*pLocation+1;
    }
    /**
     * Checks if the given node is a leaf node
     * @param location location of the node
     * @return boolean if the node is leaf node or not
     * Theta(1)
     */
    public boolean isLeaf(int location){
        if(size/2==1)
            return location >  (size / 2)  &&  location <= size;
        return location >=  (size / 2)  &&  location <= size;
    }
    /**
     * Returns the parent location
     * @param cLocation location of the child node
     * @return index of the parent location in the heap
     * Theta(1)
     */
    public int pi(int cLocation){
        return (cLocation / 2);
    }
    /**
     * 
     * Inserts the element into the heap
     * @param element distance to the node
     * @param ver node value
     * Theta(n)
     */
    public void add(double element, int ver)
    {
        heap[size] = element;
        vertex[size]=ver;
        if(size<maxsize)size++;
        int current = size-1;
 
        while (heap[current] < heap[pi(current)])
        {
            swap(current,pi(current));
            current = pi(current);
        }
        minHeap();
    }
    /**
     * Swaps the position of the two given values
     * @param a first value
     * @param b second value
     * Theta(1)
     */
    public void swap(int a, int b)
    {
        double tmp;
        int tmp2;
        tmp = heap[a];
        tmp2 = vertex[a];
        heap[a] = heap[b];
        vertex[a] = vertex[b];
        heap[b] = tmp;
        vertex[b] = tmp2;
    }
    /**
     * Maintains the integrity of the heap
     * @param pos position where the heap property is to be checked
     * Theta(log(n))
     */
    private void minHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            
            if ( (heap[pos] > heap[lC(pos)])  || (heap[pos] > heap[rC(pos)]))
            {
                if (heap[lC(pos)] < heap[rC(pos)])
                {
                    swap(pos, lC(pos));
                    minHeapify(lC(pos));
                }else
                {
                    swap(pos, rC(pos));
                    minHeapify(rC(pos));
                }
            }
        }
        
    }
    /**
     * Verifies the integrity of each node in the minheap
     * by iteratively traversing across all root nodes
     * Theta(n)
     */
    public void minHeap()
    {
       
          
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
           
            minHeapify(pos);
        }
    }
    /**
     * changes value of a node in heap
     * @param location the node in which the change must me made
     * @param value value which must be updated in the node
     * @param u start node
     * Theta(n) 
     */
    public void setValue(int location, double value, int u){
        
        int position = 0;
        for(int i=1;i<vertex.length;i++){
            if(vertex[i]==location){
                position = i;
                break;
            }
        }
        heap[position] = value;
        minHeap(u,location);
    }
    /**
     * Checks if given value is present in the heap or not.
     * @param value value to check for in minheap
     * @return boolean if node is present or not.
     * Theta(n)
     */
    boolean contains(int value) {
        
        for(int i =1;i<=size;i++){
            if(vertex[i]==value){
                return true;
            }
        }
        
        return false;
    }
    /**
     * Returns the node to travel to given distance
     * @param value the distance to travel to a node
     * @return returns the node location
     * Theta(n)
     */
    int getVertex(double value) {
        for(int i=1;i<size;i++){
            if(heap[i]==value){
                
                return vertex[i];
            }
        }
        return -1; 
    }
    
    /**
     * Override method of toString method
     * @return string representation of heap
     * Theta(n)
     */
    public String toString(){
    String s="";
    for(int i=1; i< size; i++){
        s+=""+heap[i]+"-->";
    }
    return s;
}
    /**
     * Checks if heap is empty
     * @return boolean if heap is empty or not
     * Theta(1)
     */
    boolean isEmpty() {
       return size ==0;
    }
    
    /**
     * Iteratively checks if minheap property is maintained across all nodes in the heap
     * @param u start node
     * @param location end node
     * Theta(n)
     */
    private void minHeap(int u,int location) {
        for (int pos = (size / 2); pos >= 1 ; pos--)
        {
            
            minHeapify(pos,u,location);
        }
    }
    /**
     * Maintains the minheap property
     * @param pos location of node from which heapify operation must start
     * @param u start node
     * @param location end node
     * Theta(log(n))
     */
    private void minHeapify(int pos,int u ,int location)
    {
        if (!isLeaf(pos,u,location))
        { 
           
            if ( (heap[pos] > heap[lC(pos)])  || (heap[pos] > heap[rC(pos)]))
            {   
                if (heap[lC(pos)] < heap[rC(pos)])
                {
                    swap(pos, lC(pos));
                    minHeapify(lC(pos));
                }else
                {
                    swap(pos, rC(pos));
                    minHeapify(rC(pos));
                }
            }
        }
        
    }
    /**
     * Checks if node is root node or leaf node
     * @param location location of the node
     * @param u start node
     * @param i end node
     * @return boolean if node is leaf or not
     * Theta(1)
     */
    public boolean isLeaf(int location,int u, int i){
        
        if(size/2==1)
            return location >  (size / 2)  &&  location <= size;
        return location >=  (size / 2)  &&  location <= size;
    }
}
