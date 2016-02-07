 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author inba
 */
public class TSP {
        static double len = 0;
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
        @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String currentLine;
         
        String[] crimeRecords = new String[10000];
        int count = 0;
        if(args.length<1){
            System.out.println("Enter File Name");
            return;
        }
        BufferedReader br;
        try{
            FileReader f= new FileReader(args[0]); 
            br = new BufferedReader(f);
            while ((currentLine = br.readLine()) != null) {
                crimeRecords[count++]=currentLine;
            }
        }catch(FileNotFoundException fe){
            System.out.println("File was not found in specified location.");
        }
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter start index:");
        int sIndex=Integer.parseInt(br.readLine());
        System.out.println("Enter end index:");
        int eIndex= Integer.parseInt(br.readLine());
        System.out.println("Crime Records processed:");
        for(int i=sIndex;i<=eIndex;i++){
            System.out.println(crimeRecords[i]);
        }
        double[][] distance = distances(crimeRecords,sIndex,eIndex);
        
        Prim p = new Prim();
        int[] parents = p.mstPrim(distance, eIndex-sIndex+1);
        
        double length = 0;
        
        TreeNode[] msTree = new TreeNode[parents.length];
        for(int i=0;i<parents.length;i++){
            msTree[i] = new TreeNode(i);
        }
        
        TreeNode root = msTree[0];
        for(int i=1;i<parents.length;i++){
            
            msTree[i].addParents(msTree[parents[i]],distance[parents[i]][i]);
            
        }
        root.preOrder();
        TreeNode[] hcArray = TreeNode.hCycle.toArray(new TreeNode[0]);
        
        for(int i=0;i<hcArray.length-1;i++){
                System.out.print(hcArray[i].currentLocation+",");
                
                length += distance[hcArray[i].currentLocation][hcArray[i+1].currentLocation];
        }
        System.out.println(hcArray[hcArray.length-1].currentLocation);
        System.out.println("length is : "+length);
        
    }
    /**
     * Creates the weighted adjacency matrix
     * @param crimeRecords records to be processed
     * @param sIndex start index of the record position
     * @param eIndex position of final record to be processed 
     * @return returns the adjacency matrix
     * Theta(n-squared)
     */
    public static double[][] distances(String[] crimeRecords,int sIndex, int eIndex){
        double[][] distances= new double[eIndex-sIndex+1][eIndex-sIndex+1];
        
        String[] sDistancesI,sDistancesJ;
        for(int i=sIndex;i<=eIndex;i++){
            sDistancesI =  crimeRecords[i].split(",");
            for(int j=sIndex;j<=eIndex;j++){
                sDistancesJ=crimeRecords[j].split(",");
                distances[i-sIndex][j-sIndex] = distance(Double.parseDouble(sDistancesI[0]),Double.parseDouble(sDistancesI[1]),Double.parseDouble(sDistancesJ[0]),Double.parseDouble(sDistancesJ[1]));
                
            }
        }
        
        return distances;
    }
/**
 * returns the distance between two coordinates using pythogorean formula
 * @param x1 x coordinate of first position
 * @param y1 y coordinate of second position
 * @param x2 x coordinate of first position
 * @param y2 y coordinate of second position
 * @return distance between the two coordinates
 * Theta(1)
 */
    public static double distance (double x1, double y1, double x2, double y2){
       
    return Math.sqrt(Math.pow(x1-x2,2) + Math.pow(y1-y2,2))*0.00018939;
    }
    
    
}
