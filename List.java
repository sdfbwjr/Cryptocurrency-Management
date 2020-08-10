

public class List {
	private Node top;
	
	public List() {
		top = null;
	}
	
	public void addInvestor(Investors data) {
		if(legalID(data.getSym())) {
		 if(!exist(data.getSym())) {
			Node newNode = new Node(data, top);
			this.top = newNode;
			System.out.println("create investor " + data.getSym() + " successfully");
		 }else {
			System.out.println(data.getSym() + " duplicate");
		 }
		}else {
			System.out.println("illegal ID");
		}
	}
	
	public boolean legalID(String str) {
		boolean answer = true;
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)<97 || str.charAt(i)>122 || str.length()>8) {
				answer = false;
				break;
			}
		}
		
		return answer;
	}
	
	//add to the system linked list
	public void addCryp(Cryptocurrencies data) {
		if(legalSyb(data.getSym())){
			if(!exist(data.getSym())) {
				Node newNode = new Node(data, top);
				this.top = newNode;
				System.out.println("create cryptocurrency " + data.getSym() + " successfully");
				
			}else {
				System.out.println(data.getSym() + " duplicate");
			}
		}else {
			System.out.println("illegal symbol");
		}
	}
	
	//add to each investors crypto list
	public void investorsCrypt(Cryptocurrencies data) {
		if(!exist(data.getSym())) {
			Node newNode = new Node(data, top);
			this.top = newNode;
		}
	}
	
	public boolean legalSyb(String str) {
		boolean answer = false;
		if(str.length()<=4) {
			answer = true;
		}
		return answer;
	}
	
	public boolean exist(String symbol) {
		Node curr = top;
		boolean result = false;
		
		while(curr != null) {
			if(curr.getData().equals(symbol)) {
				result = true;
				curr = curr.getNext();
			}else {
			curr = curr.getNext();
			}
		}
		
		return result;
	}
	
	public boolean enough(String symb, int quant) {
		Node curr = top;
		boolean result = false;
		
		while(curr != null) {
			if(curr.getData().equals(symb) && curr.getQuant()>=quant) {
				//curr.setQuant(quant);
				result = true;
				curr = curr.getNext();
			}
			else {
				curr = curr.getNext();
			}
		}
		
		return result;
	}
	
	public ListItem find(String id) {
		ListItem result = null;
		Node curr = top;

		while(curr != null) {
			if(curr.getData().equals(id)) {
				result = curr.getPerson();
				curr = curr.getNext();
			}else {
				curr = curr.getNext();
			}
		}
		return result;
	}
	
	public String mine(String id, String symb, int quant) {
		String result = "";
		Investors person = (Investors)find(id);
		
		String name = findName(symb);
		if(!exist(id)) {
			result = "investor " + id + " not found";
		}else if(!this.exist(symb)) {
			result = symb + " not found";
		}else if(!enough(symb, quant)) {
			result = symb + " not sufficient";
		}else {
			//log in block chain

		    Block block;
			Cryptocurrencies curren;
			
			block = new Block(id, quant);
			curren = (Cryptocurrencies)find(symb);
			curren.getChain().addBlock(block);
			
			person.addCrypt(symb, name, quant);
			find(symb).setQuant(quant);
			result = id + " mine " + quant + " " + symb + " successfully";
		}
		
		return result;
	}
	
	public String findName(String sym) {
		String result = "";
		Node curr = top;

		while(curr != null) {
			if(curr.getData().equals(sym)) {
				result = curr.getCrypt();
				curr = curr.getNext();
			}else {
				curr = curr.getNext();
			}
		}
		return result;
	}
	
	public String trade(String tOne, String tTwo, String currencyOne, String currencyTwo, int quantOne, int quantTwo) {
		//log in blockchain
		String result = "";
		Investors traderOne;
		Investors traderTwo;
		Cryptocurrencies currenOne;
		Cryptocurrencies currenTwo;
		Block blockO;
		Block blockT;
		Block blockR;
		Block blockF;
		traderOne = (Investors)find(tOne);
		traderTwo = (Investors)find(tTwo);
		currenOne = (Cryptocurrencies)find(currencyOne);
		currenTwo = (Cryptocurrencies)find(currencyTwo);
		
		if(!exist(tOne) || !exist(tTwo)) {//if trader doesn't exist
			result = "trader not found";
		}else if(tOne.equals(tTwo)){
			result = "same trader";
		}else {
			if(currencyOne.equals("CAD") && currencyTwo.equals("CAD")) {
				int cashOne = traderOne.getQuant();
				int cashTwo = traderTwo.getQuant();
				
				if(cashOne<quantOne || cashTwo <quantTwo) {
					result = "not enough cash";
				}else {
					traderOne.tradeCash(quantOne, quantTwo);
					traderTwo.tradeCash(quantTwo, quantOne);
					result = "trade successfully";
				}
			}else if(currencyOne.equals("CAD") && !currencyTwo.equals("CAD")) {
				int cashOne = traderOne.getQuant();
				
				if(!traderTwo.findCryptList(currencyTwo)) {//trader two doesn't has c2
					result = currencyTwo + " not found";
				}else if(cashOne<quantOne) {
					result = tOne + " does not has enough cash";
				}else if(!traderTwo.enoughcryptList(currencyTwo, quantTwo)) {
					result = tTwo + " does not has enough" + currencyTwo;
				}else {
					blockT = new Block(tOne, quantTwo);//trader1 get quant2
					currenTwo.getChain().addBlock(blockT);
					//blockR = new Block(tTwo, -quantTwo);//trader2 give quant2
					//currenTwo.getChain().addBlock(blockR);
					
					traderOne.tradeCash(quantOne, 0);
					traderOne.addCrypt(currencyTwo, findName(currencyTwo), quantTwo);
					traderTwo.tradeCash(0, quantOne);
					traderTwo.outCrypt(currencyTwo, quantTwo);
					result = "trade successfully";
				}
			}else if(!currencyOne.equals("CAD") && currencyTwo.equals("CAD")) {
				int cashTwo = traderTwo.getQuant();
				
				if(!traderOne.findCryptList(currencyOne)) {//trader one doesn't has c1
					result = currencyOne + "not found";
				}else if(cashTwo < quantTwo) {
					result = tTwo + " doesn't has enough cash";
				}else if(!traderOne.enoughcryptList(currencyOne, quantOne)) {
					result = tOne + " doesn't has enough " + currencyOne;
				}else {
					//blockO = new Block(tOne, -quantOne);//trader1 give quant1
					//currenOne.getChain().addBlock(blockO);
					blockF = new Block(tTwo, quantOne);//trader2 get quant1
					currenOne.getChain().addBlock(blockF);
					
					traderOne.tradeCash(0, quantTwo);
					traderOne.outCrypt(currencyOne, quantOne);
					traderTwo.tradeCash(quantTwo, 0);
					traderTwo.addCrypt(currencyOne, findName(currencyOne), quantOne);
					result = "trade successfully";
				}
			}else {
				if(!traderOne.findCryptList(currencyOne)) {
					result = "currency " + currencyOne + " not found";
				}else if(!traderTwo.findCryptList(currencyTwo)) {
					result = "currency " + currencyTwo + " not found";
				}else if(!traderTwo.enoughcryptList(currencyTwo, quantTwo)) {
					result = tTwo + " doesn't has enough " + currencyTwo;
				}else if(!traderOne.enoughcryptList(currencyOne, quantOne)) {
					result = tOne + " doesn't has enough " + currencyOne;
				}else {

					//blockO = new Block(tOne, -quantOne);//trader1 give quant1
					//currenOne.getChain().addBlock(blockO);
					blockT = new Block(tOne, quantTwo);//trader1 get quant2
					currenTwo.getChain().addBlock(blockT);
					//blockR = new Block(tTwo, -quantTwo);//trader2 give quant2
					//currenTwo.getChain().addBlock(blockR);
					blockF = new Block(tTwo, quantOne);//trader2 get quant1
					currenOne.getChain().addBlock(blockF);
					
					traderOne.outCrypt(currencyOne, quantOne);
					traderOne.addCrypt(currencyTwo, findName(currencyTwo), quantTwo);
					traderTwo.addCrypt(currencyOne, findName(currencyOne), quantOne);
					traderTwo.outCrypt(currencyTwo, quantTwo);
					result = "trade successfully";
				}
			}
		}
		return result;
	}
	
	public void portfolioReport(String id) {
		Node curr = top;
		boolean printed = false;
		Investors person;
		
		while(curr != null) {
			if(curr.getData().equals(id)) {
				person = (Investors)curr.getPerson();
				person.printCrypt();
				System.out.println();
				person.print();
				printed = true;
				curr = curr.getNext();
			}else {
				curr = curr.getNext();
			}
		}
		
		if(!printed) {
			System.out.println("investor not found");
		}
	}
	
	public void currencyReport(String sym) {
		Cryptocurrencies currency = (Cryptocurrencies)find(sym);
		
		if(currency != null) {
			System.out.println();
			System.out.println();
			currency.print();
			currency.getChain().print();
			//currency.getChain().verify();
			if(currency.getChain().verify()) {
			//if(!result) {
				System.out.println("verified successful");
			}else {
				System.out.println("block chain has been altered");
			}
		}else{
			System.out.println("cryptocurrency is not in the system");
		}
	}
	
	public void print() {
		System.out.print("[");
		if(top != null) {
			top.print();
		}
		System.out.print("]");
	}
}
