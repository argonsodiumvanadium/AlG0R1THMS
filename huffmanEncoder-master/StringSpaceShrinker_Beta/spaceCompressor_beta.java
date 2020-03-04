import java.util.*;
import java.io.*;

public class spaceCompressor_beta
{
	static UserInterface frontend = new UserInterface();

	public static void main(String[] args) {
		frontend.renderUI();
	}
}
class letterExtractor
{
	public static letterStorage Storage = new letterStorage();

	public void giveData(String str)
	{
		Storage.sentenceHolder = new StringBuffer(str);

		convertAndStoreLetters(Storage.sentenceHolder);

	}

	public static void convertAndStoreLetters(StringBuffer sentenceHolder)
	{
		try
		{
		for(int i=0;i<sentenceHolder.length();i++)
		{
			char storable = sentenceHolder.charAt(i);
			int index = (int)storable;

			Storage.dataArray[index]++;
		}

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void consent(boolean consentGiven)
	{
		if(consentGiven)
		{ 
			Skynet t_800 = new Skynet(Storage);
			t_800.terminateJohnConnor(t_800);
		}
	}
}

//this is the storage object
class letterStorage
{
	int[] dataArray;
	int totalLetters;
	int rowsRequired;
	StringBuffer sentenceHolder;

	letterStorage(){
		dataArray = new int[65_536];
	}

	public int getTotalLetters()
	{
		this.totalLetters = 0;
		for(int element:this.dataArray)
		{
			this.totalLetters +=element;
		}
		return this.totalLetters;
	}

	public int getRows()
	{
		HashSet<Integer> set = new HashSet<Integer>();
		this.rowsRequired = 0;
		for(int element:this.dataArray)
		{
			set.add(element);
		}
		this.rowsRequired = set.size() -1;
		return this.rowsRequired;
	}
}

class Skynet
{
	letterStorage localData;
	int[][] occuranceArray;
	char[] hierarchy;
	static String[] LEGEND;
	static BitSet[] BIT_LEGEND;

	private static Skynet t_1000 = new Skynet();

	Skynet(){} 

	Skynet(letterStorage obj)
	{
		localData = obj;
		occuranceArray = new int[localData.getRows()][65_536];
		hierarchy = new char[65_536];
	}

	//starts the execution of John Conno- *ahem* the program
	public void terminateJohnConnor(Skynet t_800)
	{
		t_1000 = t_800; //they kinda spoiled the opness of Arnold with this upgrade
		t_1000.setUpTheGraph();
		t_1000.getHierarchy();
		LEGEND = t_1000.getLegend();
		//BIT_LEGEND=t_1000.turnStringToBits();
		t_1000.shrinkAndCompare();
	}

	public void setUpTheGraph()
	{
		int max=0,index=-1,shiftingMax,maxIndex;

		shiftingMax = localData.getTotalLetters() + 1;
		maxIndex = -1;

		for(int[] childrenArray : occuranceArray)
		{
			for(int i=0;i<childrenArray.length;i++)
			{
				if(this.localData.dataArray[i]<shiftingMax & maxIndex != i)
				{
					childrenArray[i] = this.localData.dataArray[i];

					if(max<childrenArray[i] & index != i)
					{
						max = childrenArray[i];
						index = i;
					}
				}
			}
			shiftingMax = max;
			maxIndex = index;
			max =0;
			index = -1;
		}
	}

	public void getHierarchy()
	{
		int pos =0;
		for(int i=0;i<this.occuranceArray.length;i++)
		{
			int max = this.getMax(i);
			for(int k=0;k<this.occuranceArray[i].length;k++)
			{
				if(max == this.occuranceArray[i][k])
				{
					this.hierarchy[pos] = (char)(k);
					pos+=1;
				}
			}
		}
	}

	public int getMax(int index)
	{
		var max= 0;
		for(int element :this.occuranceArray[index])
		{
			if(max<element)
			{
				max = element;
			}
		}
		return max;
	}

	public String[] getLegend()
	{
		var returnable = new String[this.localData.dataArray.length];
		var tempArray = new String[this.localData.dataArray.length];
		var prevVal = 0;

		//this is de-nullifying the array
		for(String element:returnable)
		{
			element = "";
		}


		for (int i=1;i<6;i++)
			for(int j=0;j<Math.pow(2,i);j++)
			{
				String temp = checkExistence(Integer.toBinaryString(j),tempArray);
				tempArray[prevVal] = temp;

				prevVal++;
			}

		for(int k=0;k<this.localData.dataArray.length;k++)
		{
			int index = (int)hierarchy[k];
			try{
			returnable[index] = tempArray[k];
			}catch (Exception e) {}
		}
		return returnable;
	}

	public String checkExistence(String binary,String[] array)
	{
		for (String element:array) 
		{	try{
			if(element.equals(binary))
				binary="0"+binary;
		}catch (Exception e) {
			
		}
		}

		return binary;
	}

	/*
	public BitSet[] turnStringToBits()
	{
		BitSet[] temp = new BitSet[this.localData.dataArray.length];

		temp = initializeBitSet(temp);

		int index;
		try
		{
			for(int h=0;h<LEGEND.length;h++)
			{
				String element = LEGEND[h];
				index =h;
				System.out.println(h);

				if(element.length() != 0)
				{
					System.out.print("in the if");

					BitSet bit = new BitSet(element.length());

					for(int i=1;i<element.length();i++)
					{
						System.out.println(i);
						if(element.charAt(i) == '1')
						{
							bit.set(i,true);
						}
					}

					temp[index] = bit;
				}
			}
		}catch(Exception ex){
			System.out.println(ex);
		}
		System.out.println(temp[97].toString());

		return temp;
	}

	public BitSet[] initializeBitSet(BitSet[] bits)
	{
		for(BitSet element : bits)
		{
			System.out.print("initializeBitSet");
			element = new BitSet(0);
			System.out.println(element.toString());
		}

		return bits;
	}
	*/
	public void shrinkAndCompare()
	{
		int space =0;
		StringBuffer shrinked = new StringBuffer("");
		//try{
			for(int i=0;i<localData.sentenceHolder.length();i++)
			{
				char chr = localData.sentenceHolder.charAt(i);
				int index = (int)chr;

				try{space += LEGEND[index].length();}catch(Exception ex){}

				shrinked.append(LEGEND[index]+".");
			}
			
			System.out.println("Previous String :- "+localData.sentenceHolder);
			System.out.println("Space taken :- "+(localData.sentenceHolder.length()*16)+" bits \n\n");
			try{Thread.sleep(2000);}catch(Exception ex){}
			System.out.println("Shrinked String :- "+shrinked);
			System.out.println("Space taken :-"+space+" bits");
			try{Thread.sleep(1500);}catch(Exception ex){}
			//System.out.println("This calulation was not done by the computer but was coded reason being\njava is not a low level language like C or C++ or even RUST it is type Specific ");
			//System.out.println("Now due to java not being a low level language and strictly typed I \ndoubt you can actually create a new data type in java with a different space requirement\n");
			//System.out.println("Now as the program creates a new legend each time its space requirement\nmust be seen too another important point each Character must be sent at intervals");
			//System.out.println("The reason for that is , this binary code does not have deliters nor \ndoes it have arguments of the same length, to maximise space conservation so the number of bits and LEGEND");
			//System.out.println("is very important while using this algorithm without that the interpreter\n will not understand what are you saying\n\n");
		/*}catch(ArrayOutOfBoundsException ex){
			System.out.println("ERR0R");
		}*/
		//3324176

	}
}


class UserInterface
{
	static Scanner sc = new Scanner(System.in);
	static letterExtractor transfer = new letterExtractor();

	public static void readAndFeedFile(String fileName)
	{
		try{
			File file_Name = new File(fileName);
			FileReader fRead = new FileReader(file_Name);
			BufferedReader reader = new BufferedReader(fRead);

			var Line ="";
			String concated = "";

			while((Line = reader.readLine()) != null)
			{
				concated += Line;
			}

			transfer.giveData(concated);
		}catch(Exception ex){System.out.println("hey man did you delete the file or something cant find it");}
	}

	public void renderUI()
	{
		System.out.println("Taking data from textFile ...");

		var textfile = "Shrinkable.txt";

		readAndFeedFile(textfile);

		//try{Thread.sleep(2500);}catch(Exception ex){}

		/*
		DEPRECIATED

		boolean NOT_QUIT = true;
		while(NOT_QUIT)
		{
			String input = sc.next();
			if(input.equals("//quit"))
			{
				NOT_QUIT = false;
			}
			else
			{
				transfer.giveData(input);
			}
		}
		*/
		transfer.consent(true);
	}
}
