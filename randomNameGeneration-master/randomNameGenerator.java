import java.util.*;
import java.io.*;

//ALL HAIL LELOUCH
//ALL HAIL BRITTANIA
//not the biscuit guys


/*
the class randomNameGenerator
this class does the feature extraction and classification of the words it recieves
and stores it into an instance of the nameData class 
*/
public class randomNameGenerator
{
	public static nameData Storage = new nameData(); 
	static generatorUI frontend = new generatorUI();
	static NeuralNetwork n = new NeuralNetwork();

	public static void main(String[] args) 
	{
		frontend.renderUI();	
	}

	public void giveData(String str)
	{
		StringBuffer data = new StringBuffer(str);
		data = setLowerCase(data);

		convertAndStore(data);
	}

	public static StringBuffer setLowerCase(StringBuffer data)
	{

		for(int i = 0;i<data.length();i++)
		{
			if(Character.isUpperCase(data.charAt(i)))
			{
				data.replace(i,i+1,""+(char)((int)data.charAt(i) +32));
			}
		}

		return data;
	}

	public static void convertAndStore(StringBuffer data)
	{
		try
		{
		for(int i=0;i<data.length();i++)
		{
			if(Character.isLetter(data.charAt(i)))
			{
				int convert = ((int)data.charAt(i)) - 97;

				Storage.alphabetOccurrence[convert]++;

				getTheNextCharData(data,i,convert);
			}
		}

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void getTheNextCharData(StringBuffer data,int location,int initialConvertValue)
	{
		try
		{
			location++;
			int convert  = ((int)data.charAt(location)) - 97;

			Storage.alphabetSuccessor[initialConvertValue][convert]++;
			Storage.alphabetSuccessorSampleSpace[initialConvertValue]++;
		}catch(Exception ex){}
	}

	public void consent(boolean consentGiven)
	{
		if(consentGiven)
		{
			n.commenceComputing(Storage);
		}
	}
}
/**
class neuralNetwork
This is the main proccessing class it does all the heavy lifting now it has a lot of
Depreciated methods due to the fact that I am dumb and did not commit to github because
I wanted suspense that how the hell did he print words like amamamit,areelopa etc because 
if you look at the old code it only adds characters with the highes conditional probabiblity
so I did not commit but I tried to improve the efficiency of the algorithm ( well it was
something like O(N^4)) so I [guess what] messed up the code so much that even undo could 
not bring the code back and I had lost my theory sheets (I know everything looks too good to
be true but even I was left shocked when I saw these "Series of Unfortunate events" Unfold
[did I get the name right ?])

 Now to the point 
 the main agenda of the class is to get the priori and conditional probability and use it
 to generate the name now the methods are described but the program has many problems such
 as :-

 >The data only has one dimension "OCCURANCE" now if I generate a name based on that on dimension 
  I can only print words such as bananana or danajala or asanaoba and all these because a occurs 
  the MOST in the words and my awesome random charecter selector died so I will have to reform 
  the algorithm
 >Next, as I said I only have one dimension for arranging so I will need to find another dimension
  I am thinking of getting an average of Conditional and Priori but then a will invariably get 
  chosen again so I have to solve the 'a' problem first
 >Next I need help and ideas so please if u want to contribute mail me at 
 		argonsodiumvanadium@gmail.com 
*/

class NeuralNetwork{
	static nameData Storage;
	static int median;
	static double[] PrioriProbability = new double[26];
	static double[][] ConditionalProbability = new double[26][26]; 

	/*
	the y-axis will be the alphabets which are taken while
	the x -axis will be their probability to succeed alphabets on y-axis
	*/

	public void commenceComputing(nameData object)
	{
		Storage = object;

		createProbabilityMap();
		commenceGeneration();
	}
	//_________________________________________________________________________________________________________________________




	//PROBABILITY ALGORITHMS
	//the ProbabilityMap creating algorithms
	public static void createProbabilityMap()
	{
		getSampleSpace();
		getPrioriProbability();
		getConditionalProbability();
	}

	public static void getSampleSpace()
	{
		//gets total sample space to calculate priori probability
		for(int i=0;i<26;i++)
		{
			Storage.sampleSpace += Storage.alphabetOccurrence[i];
		}
		//System.out.println("sampleSpace : "+Storage.sampleSpace);
	}

	public static void getPrioriProbability()
	{
		//System.out.print("PrioriProbability : ");
		for (int i=0;i<26;i++)
		{
			PrioriProbability[i] = ((double)Storage.alphabetOccurrence[i])/((double)Storage.sampleSpace);
			//System.out.print(PrioriProbability[i]+",");
		}
		//System.out.println("");
	}

	public static void getConditionalProbability()
	{
		/*
		now i am gonna use some crazy wierd alphabet algebra
		yes I know now regret opening this code

		so as priori was already in initialized in previous function
		we will get the union of sets in this
		lets take a mystery alphabet "theta" and another mystery alphabet "phi"

		so P(theta) = PrioriProbability[theta's ASCII transformation - 97]
		now P(phi|theta) = P("theta"_INTERSECTION_"phi") / P(theta)

		and so in this algorithm i will find the 
		n("theta"_INTERSECTION_"phi") which is equal to the occurances of phi AFTER theta [I think AND hope so XD]
		after that its all simple
		*/
		//System.out.println("ConditionalProbability:-");
		for (int alphabetNum=0;alphabetNum<26;alphabetNum++) 
		{
			double P_THETA = PrioriProbability[alphabetNum];
			int N_TOTAL_SUCCESSORS = Storage.alphabetSuccessorSampleSpace[alphabetNum];
			for (int successorNum=0;successorNum<26;successorNum++) 
			{
				int N_INTERSECTION = Storage.alphabetSuccessor[alphabetNum][successorNum];

				double P_INTERSECTION = ((double)N_INTERSECTION)/((double)N_TOTAL_SUCCESSORS);

				ConditionalProbability[alphabetNum][successorNum] = P_INTERSECTION/P_THETA;
				//System.out.print(ConditionalProbability[alphabetNum][successorNum]+",");
			}
			//System.out.println("");
		}
	}

	/*

	DEPRCIATED
	#####################################################################
	
	ver-1

	public static int getNextCharIndex(char lastChar)
	{
		HashSet<Integer> checkRecursion = new HashSet<Integer>();
		int max=0,prevLength,removableIndex=0;
		int []possibleSuccessors= new int[5];
		int value = (int)lastChar - 97;

		var temp2D = ConditionalProbability[value];

		for (int h=0;h<5;h++)
		{
			for (int i=0;i<25;i++) 
			{
				if(max<temp2D[i])
				{
					prevLength = checkRecursion.size();
					checkRecursion.add(max);

					if(checkRecursion.size() == prevLength+1)
					{
						System.out.println("in the if block "+i );
						possibleSuccessors[h] =  i;
						removableIndex = i;
					}
				}
			}

			temp2D[removableIndex] = 0;
		}

		for(int elements:possibleSuccessors)
		{
			System.out.print(elements);
		}
		var index = Math.round((int)Math.random()*4);
		System.out.println(possibleSuccessors[index]);

		return (possibleSuccessors[index]);
	}

	ver - 2

	public static int getNextCharIndex(char lastChar,StringBuffer data)
	{
		var value = (int)lastChar -97;
		var tempArray = new double[26];
		double max = 0;
		int index=0;
		var holder = new int[5];
		int check;

		tempArray = ConditionalProbability[value];

		for (int i=0;i<5;i++) 
		{
			for2:
			for(int j=0;j<25;j++)
			{
				if(max<tempArray[j])
				{
					for(int k=0;k<data.length();k++)
					{
						var elements = data.charAt(k);
						if(elements == ((char)(index + 97)))
						{
							tempArray[index] = 0;
							continue for2;
						}
					}
					index =j;max=tempArray[j];
				}
			}

			tempArray[index] = 0;
			holder[i] = index;
		}

		return holder[(int)Math.round((Math.random() * 4))];
	}
		*/

	//_________________________________________________________________________________________________________________________





	//NAME GENERATION
	//these are the name generation algorithms
	public static void commenceGeneration()
	{
		generateWord(generateFirstLetter(),7);
	}

	public static StringBuffer generateFirstLetter()
	{
		char firstLetter = (char)(97+Math.round(Math.random()*25));

		while(check(firstLetter))
		{
			firstLetter = (char)(97+Math.round(Math.random()*25));
		}

		StringBuffer Name = new StringBuffer(""+firstLetter);
		
		return Name;
	}

	public static void generateWord(StringBuffer Name,int wordLength)
	{
		char lastLetter = getLastLetter(Name);
		if(Name.length()>wordLength)
		{
			generateWord(Name);
		}

		else
		{
			int value = (int)lastLetter - 97,index=0;double max =0.0;
			/*
			DEPRECIATED
			###########################################################

			for (int i=0;i<25;i++) 
			{
				if(max<ConditionalProbability[value][i])
				{
					max = ConditionalProbability[value][i];
					index = i;
				}
			}
			*/
			
			/*
			Right now you will be wondering why would anyone remove such a good code block
			well the reason is mentioned on the class description but to be concise
			'A' OCCURS TOO MANY TIMES AND I DON'T KNOW WHO(talking about a english parent)
			IN HIS/HER RIGHT MIND WOULD NAME THEIR KID BANANANA so to remove the a problem I
			removed the choice of A, yea this will be my future quote
				66
								if u have a problem..
							in life
						dont solve it..
					remove it
							-argonsodiumvanadium    99 
			*/
			for (int i=1;i<25;i++) 
			{
				if(max<ConditionalProbability[value][i])
				{
					max = ConditionalProbability[value][i];
					index = i;
				}
			}

			char toBeAdded = (char)(index+97);
			Name.append(toBeAdded);
			generateWord(Name,wordLength);
		}
	}

	public static void generateWord(StringBuffer Name)
	{
		System.out.println(Name);
	}

	public static char getLastLetter(StringBuffer data)
	{
		return data.charAt(data.length()-1);
	}

	public static boolean check(char firstLetter)
	{
		int convert = (int)firstLetter - 97;

		if(Storage.alphabetSuccessorSampleSpace[convert] != 0)
		{
			return false;
		}

		return true;
	}
}

//this is the class whose instance is used for feature extraction
class nameData
{
	int sampleSpace;
	int[] alphabetSuccessorSampleSpace;
	int[] wordLength;
	int[] alphabetOccurrence;
	int[][] alphabetSuccessor; 
	/*
	the y-axis will be the alphabets which are taken while
	the x -axis will be the successors
	*/

	nameData()
	{
		wordLength = new int[30];
		alphabetSuccessorSampleSpace = new int[26];
		alphabetOccurrence = new int[26];
		alphabetSuccessor = new int[26][26];
	}
}





/**
this is the class that generates user interface
i am seperating ui for easy upgrades in the far future
now i am just writing to make you think this is a proper
documentary comment

######*   ALL HAIL LELOUCH   *######

*/
class generatorUI
{
	static Scanner sc = new Scanner(System.in);
	static randomNameGenerator transfer = new randomNameGenerator();

	public static void readAndFeedFile(String fileName)
	{
		try{
			File file_Name = new File(fileName);
			FileReader fRead = new FileReader(file_Name);
			BufferedReader reader = new BufferedReader(fRead);

			var Line ="";

			while((Line = reader.readLine()) != null)
			{
				transfer.giveData(Line);
			}
		}catch(Exception ex){System.out.println("hey man did you delete the file or something cant find it");}
	}

	public void renderUI()
	{
		System.out.println("Taking data from textFile ...");

		var textfile = "NAMES_&#$^&@.txt";

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

