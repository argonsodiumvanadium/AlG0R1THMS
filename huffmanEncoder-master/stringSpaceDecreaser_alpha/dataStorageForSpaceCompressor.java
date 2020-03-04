import java.util.*;

public class dataStorageForSpaceCompressor implements Runnable
{
	static StringLambdaExpression dataStoringInstance;
	
	Thread storageThread;
	int[][] TRUE_STORAGE_ARRAY;

	static int TO_BE_STORED;

	dataStorageForSpaceCompressor()
	{
		storageThread = new Thread();
		TRUE_STORAGE_ARRAY = new int[10][];
		for(int i=0;i<10;i++)
		{
			TRUE_STORAGE_ARRAY[i] = new int[Math.pow(128,i)];
		}
	}

	public void giveTheValue(int index)
	{
		TO_BE_STORED = index;
		this.notifyAll();
	}

	public void storeValueInList()
	{

	}

	/*public static void initializeInstance()
	{
		dataStoringInstance = storeValue ->{}
	}*/

	public void run()
	{
		//initializeInstance();
		while(true)
		{
			wait();
			storeValueInList();
		}
	}
}

interface StringLambdaExpression
{
	String storeData(int storeValue);
}
