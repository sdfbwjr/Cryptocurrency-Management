

public class Node {
	//private Investors investor;
	//private Cryptocurrencies cryptocurrency;
	private Node next;
	private ListItem item;
	private Block block;
	
	public Node(ListItem data, Node link) {
		item = data;
		next = link;
	}
	
	public Node(Block data, Node link) {
		block = data;
		next = link;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public String getData() {
		return item.getSym();
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node x) {
		this.next = x;
	}
	
	public int getQuant() {
		return item.getQuant();
	}
	
	public void setQuant(int num) {
		item.setQuant(num);
	}
	

	public ListItem getPerson() {
		return this.item;
	}
	
	public String getCrypt() {
		String name;
		name = this.item.getName();
		return name;
	}
	
	public void print() {
		item.print();
		if(next != null) {
			next.print();
		}
	}
	
	public void printChain() {
		block.print();
		if(next != null) {
			next.printChain();
		}
	}
}
