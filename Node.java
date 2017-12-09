
public class Node {
	
	public int key = 10;;
	private String [] list =  new String[10];
	private int index = 0;
	private Node[] children = new Node[11];
	private Node next;
	public boolean isLeaf;
	public Node(){
		//next = new Node();
		this.isLeaf = false;
	}
	
	public Node(boolean isLeaf){
		this.isLeaf = isLeaf;
		if(isLeaf){
			next = new Node();
			children = null;
		}
		else
			next = null;
	}
	
	public void AddToList(String data){
		list[index] = data;
		index++;
		//this.print();
	}

	public void setChildren(int number, Node c){
		if(!this.isLeaf){
			this.children[number] = new Node();
			this.children[number]= c;
		}
	}
	 
	public void setNext(Node c){
		if(this.isLeaf){
			this.next = c;
		}
	}
	public String getIndexAt(int index) {
		
		return list[index];
	}
	
	public int getIndex(){
		return index;
	}
	
	public Node getChildren(int index){
		return this.children[index];
	}
	
	public void print(){
		for(int i= 0;i<this.list.length;i++){
			System.out.print(list[i] == null ? "N/A"+", ":list[i]+"/ ");
			//System.out.print(children[i] == null ? "N/A"+", ":children[i]+"/ ");
		}
		System.out.println("*************");
		for(int i= 0;i<this.children.length;i++){
			if(this.children[i] != null){
				for(int j = 0 ;j<this.children[i].getIndex();j++){
					System.out.print(this.children[i].getIndexAt(j)+"/ ");
				}
			}
			System.out.println("+++++++++++++++++++++");
		}
		System.out.println("-----");
	}
	
	public void printChild(){
		for(int i= 0;i<this.children.length;i++){
			System.out.print(children[i] == null ? "N/A"+", ":children[i]+"/ ");
		}
		System.out.println("-----");
	}
	public void contain(String a){
		for(int i = 0;i<index;i++){
			//System.out.println(list[i]);
			if(a.compareTo(list[i]) == 0){
				System.out.println("Found it");
			}
		}
	}
	
}
