
public class Tree {
	Node root = new Node();
	Level [] Level;
	Node [] leaves;
	int height=0;
	final int m;
	final int degree;
	
	
	public Tree(){
		m = 5;
		degree = 6;
	}
	
	
	public Tree(int m, int degree,String [] inputData){
		this.m = m;
		this.degree = degree;
		constructLeavse(inputData);
		constructInodes(leaves);
		constructPointer();
		constructRoot(Level[Level.length-1]);
		constructRootPointer();
	}
	
	private void constructLeavse(String [] inputData){
		System.out.println("Construct leaves begin:");
		int length = inputData.length;	
		System.out.println("There are "+length+" values in total.");
		int numberOfLeaves = (int) Math.ceil(((double)length)/ ((double)degree));
		leaves = new Node[numberOfLeaves];
		int index = 0;
		int numberOFElementNotFull = degree - length % degree == degree ? 0 :degree - length % degree;
		for(int j = 0;j<(numberOfLeaves - numberOFElementNotFull);j++){
			leaves[j] = new Node(true);
			for(int i = 0;i<degree;i++){
				leaves[j].AddToList(inputData[index]);
				index++;
			}
			if(j>=1){
				leaves[j-1].setNext(leaves[j]);
			}
		}
		if(numberOFElementNotFull > 0){
			for(int j = numberOfLeaves - numberOFElementNotFull;j<numberOfLeaves;j++){
				leaves[j] = new Node(true);
				for(int i = 0;i<m;i++){
					leaves[j].AddToList(inputData[index]);
					index++;
				}
				leaves[j-1].setNext(leaves[j]);
			}
		}
		leaves[leaves.length-1].setNext(null);
		System.out.println("Leaf node number: "+leaves.length);
	}

	private void constructInodes(Node [] leaves){
		System.out.println("Construct internal nodes begin:");
		int[] levelNodeNumber = new int[100];
		int nodeNumber= leaves.length;
		while(nodeNumber>10){
			levelNodeNumber[height] = (int) Math.ceil(((double)nodeNumber)/ ((double)(degree+1)));
			nodeNumber=levelNodeNumber[height];
			this.height++;
		}
		System.out.println("internal level number is: "+height);
		Level = new Level[height];
		int index = 1;
		int totalChildren = leaves.length;
		System.out.println("Construct first internal level begin");
		Level[0] = new Level(levelNodeNumber[0]);
		int numberOFElementFull = 0 ;
		int numberOFElementNotFull = 0;
		numberOFElementFull = totalChildren - degree*levelNodeNumber[0];
		numberOFElementNotFull = levelNodeNumber[0] - numberOFElementFull;
		for(int k = 0;k<numberOFElementFull;k++){
			for(int j = 0;j<6;j++){
				Level[0].AddToList(k,leaves[index].getIndexAt(0)); //question of index
				index++;
			}
			index++;
		}
	
		if(numberOFElementNotFull >0){
			for(int k = numberOFElementFull;k<levelNodeNumber[0];k++){
				for(int j = 0;j<5;j++){
					Level[0].AddToList(k,leaves[index].getIndexAt(0));
					index++;
				}
				index++;
			}
		}
		int index_2 = 0;
		for(int i=0;i<Level[0].getNodeNumber();i++){
			for(int j=0;j<=Level[0].getNodeAt(i).getIndex();j++){
				Level[0].getNodeAt(i).setChildren(j, leaves[index_2]);
				index_2++;
			}
		}
		System.out.println("There are "+Level[0].getNodeNumber()+" nodes in the first internal level.");
		totalChildren = levelNodeNumber[0];
		for(int i = 1;i<Level.length;i++){
			System.out.println("Construct "+(i+1)+" internal level begin");
			index=1;
			Level[i] = new Level(levelNodeNumber[i]);
			//numberOFElementNotFull = degree - (totalChildren-levelNodeNumber[i]) % degree == degree ? 0 :degree - totalChildren % degree;
			numberOFElementFull = totalChildren - degree *levelNodeNumber[i];
			numberOFElementNotFull = levelNodeNumber[i] - numberOFElementFull;
			for(int k = 0;k<numberOFElementFull;k++){
				for(int j = 0;j<6;j++){
					Level[i].AddToList(k,Level[i-1].getNodeAt(index).getChildren(0).getIndexAt(0)); //question of index
					index++;
				}
				index++;
			}
			if(numberOFElementNotFull >0){
				for(int k = numberOFElementFull;k<levelNodeNumber[i];k++){
					for(int j = 0;j<5;j++){
						Level[i].AddToList(k,Level[i-1].getNodeAt(index).getChildren(0).getIndexAt(0));
						index++;
					}
					index++;
				}
			}
			totalChildren = levelNodeNumber[i];
			System.out.println("There are "+Level[i].getNodeNumber()+" nodes in the "+(i+1)+" internal level.");
		}
	}
	
	private void constructRoot(Level lastLevel){
		System.out.println("Construct root begin");
		int index=1;
		for(int i=0;i<lastLevel.getNodeNumber()-1;i++){
			//root.AddToList(lastLevel.getNodeAt(index).getIndexAt(0));
			root.AddToList(lastLevel.getNodeAt(index).getChildren(0).getChildren(0).getIndexAt(0));
			index++;
		}
		System.out.println("There are "+root.getIndex()+" values in the root.");
	}
	
	private void constructRootPointer(){
		for(int i=0;i<=root.getIndex();i++){
			root.setChildren(i, Level[Level.length-1].getNodeAt(i));
		}
	}
	
	private void constructPointer(){
		if(Level.length >=1){
//			for(int i=0;i<=root.getIndex();i++){
//				root.setChildren(i, Level[Level.length-1].getNodeAt(i));
//			}
			int index=0;
			if(Level.length > 1){
				for(int k=Level.length-1; k>0; k--){
					for(int i=0;i<Level[k].getNodeNumber();i++){
						for(int j=0;j<=Level[k].getNodeAt(i).getIndex();j++){
							//System.out.println("k is "+k+",j is "+j+",i is "+i+" "+index);
							Level[k].getNodeAt(i).setChildren(j, Level[k-1].getNodeAt(index));
							index++;
						}
					}
					System.out.println("level is "+k+", "+index);
					index=0;
				}
			}
//			for(int i=0;i<Level[0].getNodeNumber();i++){
//				for(int j=0;j<=Level[0].getNodeAt(i).getIndex();j++){
//					Level[0].getNodeAt(i).setChildren(j, leaves[index]);
//					index++;
//				}
//			}
//			System.out.println("level is 0"+", "+index);
		}
		else{
			for(int i=0;i<=root.getIndex();i++){
				root.setChildren(i, leaves[i]);
			}
			
		}
	}

	
	public void searchForName(String query,Node node){
		Node temp = new Node();
		if(node.isLeaf){
			System.out.println("Leave:");
			node.contain(query);
		}
		else{
			for(int i = 0;i<node.getIndex();i++){
				if(query.trim().compareToIgnoreCase(node.getIndexAt(i)) < 0){
					System.out.println(query+", Current node is :");
					node.print();
					System.out.println("Index is : "+i);
					System.out.println("query compare to node.getIndexAt("+i+") is :"+
							query.trim().compareToIgnoreCase(node.getIndexAt(i)));
					temp = node.getChildren(i);
					System.out.println();
					break;
				}
				else if(query.trim().compareToIgnoreCase(node.getIndexAt(i)) == 0){
					System.out.println(query+", Current node is :");
					node.print();
					System.out.println("Index is : "+i);
					System.out.println("query compare to node.getIndexAt("+i+") is :"+
							query.trim().compareToIgnoreCase(node.getIndexAt(i)));
					temp = node.getChildren(i+1);
					System.out.println();
					break;
				}
			}
			if(query.trim().compareToIgnoreCase(node.getIndexAt(node.getIndex()-1)) >= 0){
				System.out.println(query+", Current node is :");
				node.print();
				System.out.println("Index is : "+(node.getIndex()-1));
				System.out.println("query compare to node.getIndexAt("+(node.getIndex()-1)+") is :"+
						query.trim().compareToIgnoreCase(node.getIndexAt((node.getIndex()-1))));
				temp = node.getChildren(node.getIndex());
				System.out.println();
			}
			searchForName(query,temp);
		}
	}
	
	public void print(){
		root.print();
		for(int i = this.Level.length-1;i>=0;i--){
			System.out.println(i+"st level^^^^^^^^^^^^^^^^^^^^^^^");
			this.Level[i].print();
			System.out.println();
		}
		for(int j = 0;j<this.leaves.length;j++){
			//leaves[j].print();
		}
		
	}
	
}
