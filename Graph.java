/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package csc401assign2prob6solution;



/**
 *
 * @author Hector Felix
 */
import java.util.*;


//Node structure of graph
class GraphNode{
   //data on the node
   private int data ;
   //this list contains all the connected nodes.
   private ArrayList<GraphNode> connectedTo;
   //visited for bfs
   boolean visited;
   //Constructor
   GraphNode(int data){
       this.data =data;
       this.connectedTo = new ArrayList<GraphNode>();
   }
   //getter and setters
   public int getData() {
       return data;
   }
   public void setData(int data) {
       this.data = data;
   }
   public ArrayList<GraphNode> getConnectedTo() {
       return connectedTo;
   }
   public void setConnectedTo(ArrayList<GraphNode> connectedTo) {
       this.connectedTo = connectedTo;
   }
}
public class Graph {

   private ArrayList<GraphNode> vertices;
  
   //Constructor
   Graph(){
      
       this.vertices=new ArrayList<GraphNode>();
   }


   public ArrayList<GraphNode> getVertices() {
       return vertices;
   }
   public void setVertices(ArrayList<GraphNode> vertices) {
       this.vertices = vertices;
   }
  
   public static void bfs(GraphNode node)
   {
       if(node==null) {
           return;
       }
       LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
       queue.add(node);
       queue.add(null);
       node.visited=true;
       GraphNode parent=null;
       GraphNode prev=null;
       while (!queue.isEmpty())
       {

           GraphNode element=queue.remove();
           if(element==null) {
               parent = prev;
               System.out.println();
               if(queue.isEmpty()) {
                   break;
               }
               element=queue.remove();
           }
          
          
           if(element!=null && parent!=null) {
               System.out.print(element.getData() + "("+parent.getData()+")");
               List<GraphNode> neighbours=element.getConnectedTo();
               for (int i = 0; i < neighbours.size(); i++) {
                   GraphNode n=neighbours.get(i);
                   if(n!=null && !n.visited)
                   {
                       queue.add(n);
                       n.visited=true;
     
                   }
               }
               prev=element;
               queue.add(null);
           }else {
               if(element!=null) {
                   System.out.print(element.getData() + "("+")");
                   List<GraphNode> neighbours=element.getConnectedTo();
                   for (int i = 0; i < neighbours.size(); i++) {
                       GraphNode n=neighbours.get(i);
                       if(n!=null && !n.visited)
                       {
                           queue.add(n);
                           n.visited=true;
         
                       }
                   }
                   prev=element;
                   queue.add(null);
               }
              
           }
          
          
          
          
          
          

       }
   }
   public static void main(String[] args) {
       //Lets first create the object of Graph Class;
       Graph g = new Graph();
      
       //Scanner is used for taking input from user
       Scanner sc = new Scanner(System.in);
       System.out.println("Please enter the Number of Nodes :");
       int n = sc.nextInt();
      
       //Lets declare probability array as mention in question probability has to be 0.25. we code achieve this by
       //taking three false and one true in the array.
       //if true comes we connect them otherwise not
       boolean prob[] = {true,false,false,false};
      
       //Lets create all the vertices first
       for(int i=1;i<=n;i++) {
           //create a graph node
           GraphNode gn = new GraphNode(i);
           //add the vertex in the graph.
           g.getVertices().add(gn);
       }
      
       //Now we have added all the vertices of the graph.
       //Its time to connect them with each other;
      
       ArrayList<GraphNode> al = g.getVertices();
       for(GraphNode gn : al) {
          
           for(GraphNode gn2 : al) {
              
               if(!gn.equals(gn2)) {
               //used to generate random number between 0 to 3
                   int rand = (int) (Math.random()*3);
               //if it is 0 then it will give true and we connect the node else not
               if(prob[rand]) {
                   gn.getConnectedTo().add(gn2);
                   gn2.getConnectedTo().add(gn);
               }
              
               }
           }
       }
      
       //Now all we need to do BFS
       System.out.println("Enter starting vertex");
       int start = sc.nextInt();
       //find this vertex in the graph
       GraphNode startVertex=null;
       for(GraphNode gn : al) {
           if(gn.getData()==start) {
               startVertex=gn;
               break;
           }
       }
       //call method bfs
       bfs(startVertex);
      
      

   }

}
