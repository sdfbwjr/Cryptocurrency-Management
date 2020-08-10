

public abstract class ListItem {

	public abstract void print();
	public abstract String getSym();
	public abstract int getQuant();
	public abstract void setQuant(int num);
	public abstract void addToPerson(int num);
	public abstract String getName();
}

class Investors extends ListItem{
	
	private String userID;
	private String firstName;
	private String lastName;
	private int cash;
	private List crypList = new List();
	
	public Investors(String usrId, String fname, String lname, int ca ) {
		userID = usrId;
		firstName = fname;
		lastName = lname;
		cash = ca;
	}
	
	public String getSym() {
		return this.userID;
	}
	
	public String getName() {
		String result;
		result = firstName + " " + lastName;
		return result;
	}
	
	public int getQuant() {
		return this.cash;
	}
	
	//trade two investors' cash
	public void tradeCash(int numOut, int numIn) {
		cash -= numOut;
		cash += numIn;
	}
	
	public void setQuant(int num) {
		System.out.println("setQuant erro");
	}
	
	public void addToPerson(int num) {
		System.out.println("add to person erro");
	}
	
	public void addCrypt(String sym, String na, int num) {
		if(!crypList.exist(sym)) {

			Cryptocurrencies newCryp = new Cryptocurrencies(sym, na, num);
			//crypList.addCryp(newCryp);
			crypList.investorsCrypt(newCryp);
		}else {

			ListItem crypt = crypList.find(sym);
			crypt.addToPerson(num);
		}
	}
	
	public void outCrypt(String sym, int num) {
		ListItem crypt = crypList.find(sym);
		crypt.setQuant(num);
	}
	
	public boolean findCryptList(String sym) {
		boolean result = false;
		
		result = crypList.exist(sym);
		
		return result;
	}
	
	public boolean enoughcryptList(String sym, int quant) {
		boolean result = false;

		result = crypList.enough(sym, quant);
		
		return result;
	}
	
	public void printCrypt() {
		System.out.println(this.userID + "'s cryptocurrencies: ");
		crypList.print();
	}
	
	public void print() {
		
		System.out.println(this.firstName + " " + this.lastName + " " + this.userID + " " + this.cash);
	}
}

class Cryptocurrencies extends ListItem{
	private String symbol;
	private String name;
	private int quant;
	private BlockChain chain;
	
	public Cryptocurrencies(String symb, String na, int quat) {
		symbol = symb;
		name = na;
		quant = quat;
		chain = new BlockChain();
	}
	
	public String getSym() {
		return this.symbol;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BlockChain getChain() {
		return this.chain;
	}
	
	public void setQuant(int num) {
		quant -= num;
	}
	
	public void addToPerson(int num) {
		quant += num;
	}
	
	public int getQuant() {
		return this.quant;
	}
	
	public void print() {
		System.out.println(this.name + " " + this.symbol + " " + this.quant);
	}
	
}

