
import java.util.Objects;


public class BlockChain {

	private Node top;
	private int prevHash;
	
	public BlockChain() {
		top = null;
	}
	
	public void addBlock(Block block) {
		Node newBlock = new Node(block, top);
		if(top != null) {
			prevHash = Objects.hash(top.getBlock().getpHash(), block.getHash());
		}else {
			prevHash = 0;
		}
		top = newBlock;
		top.getBlock().setpHash(prevHash);
	}
	
	public boolean verify() {
		boolean result = false;
		int check = 0;
		
		reverse(top);

		Node prev = null;
		Node curr = top;
		
		if(curr == null) {
			result = true;
		}else {
			while(curr.getNext() != null) {
				prev = curr;
				curr = curr.getNext();
				if(prev != null) {
					check = Objects.hash(prev.getBlock().getpHash(), curr.getBlock().getHash());
				}else {
					check = 0;
					System.out.println(curr.getBlock().getpHash());
				}
				
				if(check == curr.getBlock().getpHash()) {
					result = true;
				}
			}
		}
		return result;
	}
	
	public void reverse(Node node) { 
        Node prev = null; 
        Node current = node; 
        Node next = null; 
        while (current != null) { 
            next = current.getNext(); 
            current.setNext(prev); 
            prev = current; 
            current = next; 
        } 
        top = prev; 
        //return node; 
    } 
	
	public void print() {
		System.out.println();
		System.out.println("block chain: ");
		System.out.println();
		if(top != null) {
			top.printChain();
		}else {
			System.out.println("block chain is empty");
		}
	}
}
