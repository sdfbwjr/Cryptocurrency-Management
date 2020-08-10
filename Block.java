
import java.util.*;

public class Block {

	//hashprev
	//hash
	private String investor;
	private int quant;
	private int hash;
	private int prevHash;
	
	public Block(String inv, int qua) {
		investor = inv;
		quant = qua;
		hash = Objects.hash(investor, quant);
	}
	
	public int getHash() {
		return hash;
	}
	
	public int getpHash() {
		return prevHash;
	}
	
	public int setpHash(int num) {
		prevHash = num;
		
		return prevHash;
	}
	
	public void print() {
		System.out.println(investor + " get " + quant);
	}
	
}
