
public class Level {
	private Node [] InternalNode;
	
	public Level(){
		
	}
	
	public Level(int number){
		InternalNode = new Node[number];
		for(int i = 0;i<number;i++){
			InternalNode[i] = new Node();
		}
	}
	
	public void AddToList(int number,String data){
		InternalNode[number].AddToList(data);
	}
	
	public int getNodeNumber(){
		return InternalNode.length;
	}
	
	public Node getNodeAt(int index){
		return InternalNode[index];
	}
	public void print(){
		for(int i = 0;i<this.InternalNode.length;i++){
			System.out.println(i+"st node^^^^^^^^^^^^^^^^^^^^^^^");
			if(this.InternalNode[i] != null){
				this.InternalNode[i].print();
			}
		}
	}
	
	public void setChildAtIndex(int number, Node c){
		this.InternalNode[number].setChildren(0, c);;
	}
}
