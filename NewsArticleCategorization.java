import java.util.*;
import java.io.*;
import java.lang.*;
//to make dictionary

class DirList 
{

String dirname = "F:/sem 5/java/challenging assignment/20news-19997/20_newsgroups";
boolean isAlpha(String name) {
    char[] chars = name.toCharArray();

    for (char c : chars) {
        if(!Character.isLetter(c)) {
            return false;
        }
    }

    return true;
}
void make(TreeSet ts,TreeSet ts1,ArrayList al) throws Exception
{

File f1 = new File(dirname);
BufferedReader br;
if(f1.isDirectory())
{
System.out.println("Directory of " + dirname);
String s[] = f1.list();
	for (int i=0; i < s.length; i++) 
	{
	
	File f = new File(dirname + "/" + s[i]);
		
		if (f.isDirectory())
		{
		String s1[] = f.list();
		for(int j=0;j<s1.length;j++)
		{
		String str;
		File f2=new File(dirname+"/"+s[i]+"/"+s1[j]);
		StringTokenizer tr;
		HashMap hm=new HashMap();
		br=new BufferedReader(new FileReader(f2));
			while((str=br.readLine())!=null)
			{
			tr=new StringTokenizer(str," ");
			while(tr.hasMoreTokens())
			{
			String word=tr.nextToken();
			boolean bo=isAlpha(word);
			if(bo==true)
			{ 
			boolean b=ts1.contains(word);
			if(b==false)
			{
			ts.add(word);
				boolean b1=hm.containsKey(word);
					if(b1==true)
					{
					hm.put(word,(Double)hm.get(word)+1d);
					}
					else
					hm.put(word,1d);	
			}
			}
			}
			}
		al.add(hm);
		System.out.println(s1[j] + " is a file" + j+" "+i);
		//Thread.sleep(100);
		}
		System.out.println(s.length+" "+s1.length);
		}
		/*else
		{
		String str;
		StringTokenizer tr;
		BufferedReader br=new BufferedReader(new FileReader(s[i]));
			while((str=br.readLine())!=null)
			{
			tr=new StringTokenizer(str," ");
			while(tr.hasMoreTokens())
			{
			String word=tr.nextToken();
			ts.add(word);			
			}
			//System.out.println(str);
			}
		System.out.println(s[i] + " is a file"+i);
		}*/
		
		
	}
} 
/*System.out.println(ts);
System.out.println(ts.size());
Object ia[]=al.toArray();
HashMap hm=(HashMap)ia[0];
System.out.println(hm);*/
}//end of make

}//end of class



class IDF
{
void calc(TreeSet ts,ArrayList al,ArrayList idf) 
{
int i=0;
//HashMap hm;
//System.out.println(al.size());
	Iterator itr = ts.iterator();
	int c=0;int c1=0;int count=0;
	Object ia[]=al.toArray();
//hm=(HashMap)ia[0];
System.out.println(ts.size());		
		while(itr.hasNext()) 
		{		
		Object element = itr.next();
		//System.out.println(element);	
		c++;
		c1=0;
		count=0;i=0;
		Iterator itr1 = al.iterator();
			while(i<=19599) 
			{
			HashMap hm=new HashMap();
			hm=(HashMap)ia[c1];
			//System.out.println(hm);
			c1++;
			i++;
			boolean b=hm.containsKey(element);
				if(b==true)
				{
				count++;
				}//c1++;
			}//}catch(Exception e){try{Thread.sleep(1);}catch(Exception e1){}System.out.println("exception");}
		//Thread.sleep(100);
		System.out.println("outer"+c+" "+count);
		//try{
		Double d=(Math.log(ts.size()/count))/(Math.log(2));
		
		idf.add(d);//}catch(Exception e){}
		//System.out.println(idf);
		}
System.out.println(idf);
/*Object ia1[]=idf.toArray();
Double db=(Double)ia1[0];
System.out.println(db);*/
}
}//end of IDF

class TFIDF
{

void TfIdf(TreeSet ts,ArrayList al,ArrayList idf) throws Exception
{
FileWriter f=new FileWriter("Output1_length");
int c1=0;
int c=0,i=0;
Iterator it=ts.iterator();
Object ia1[]=al.toArray();
Object ia[]=idf.toArray();
Iterator itr1 = al.iterator();
		while(i<=19599)
		{
		HashMap hm=(HashMap)ia1[c1];
		Set mapSet1 = (Set) hm.entrySet();
				Iterator mapIterator1 = mapSet1.iterator();
				Double max=0d;
        					while (mapIterator1.hasNext()) 
        					{
        					Map.Entry mapEntry1 = (Map.Entry) mapIterator1.next();
        					String keyValue1 = (String) mapEntry1.getKey();
        					Double value1 = (Double) mapEntry1.getValue();
					if(value1>max)
						max=value1;
        					
        					}
		c1++;
		Double sum=0d;
		Set mapSet = (Set) hm.entrySet();
		Iterator mapIterator = mapSet.iterator();
        			while (mapIterator.hasNext()) 
        			{
				
        			Map.Entry mapEntry = (Map.Entry) mapIterator.next();
        			String keyValue = (String) mapEntry.getKey();
        			Double value = (Double) mapEntry.getValue();
        			System.out.println(value);
			while(it.hasNext())
			{
				Object element=it.next();
				boolean bl=(keyValue).equals(element);
				if(bl==true)
				{
				break;
				}
				
			if(bl==true)
			break;
			c++;
			}
			Double db=(Double)ia[c];
			Double temp=(Double)(value/max)*db;
			//try{
			hm.put(keyValue,(Double)(value/max)*db);
			sum+=(temp*temp);
        			//}catch(Exception e){}
			}
			Double length=Math.sqrt(sum);
			f.write(String.valueOf(length)); 
			f.write(" ");
		i++;
		}
System.out.println("done");
f.close();
}//end of TfIdf
}//end of TFIDF

class Input extends DirList
{

StringTokenizer tr;
Double sum=0d;

void ipCal(TreeSet ts,TreeSet ts1,ArrayList idf,HashMap hmap) throws Exception
{
BufferedReader br=new BufferedReader(new FileReader("F:/sem 5/java/challenging assignment/testing/7/76901"));
FileWriter f1=new FileWriter("Input1_length");
Double max=0d;
String str;
int c=0;
Object ia[]=idf.toArray();
	while((str=br.readLine())!=null)
	{
	tr=new StringTokenizer(str," ");
		while(tr.hasMoreTokens())
		{
		
		String word=tr.nextToken();
		boolean bo=isAlpha(word);
				if(bo==true)
				{
				boolean b2=ts1.contains(word);
				if(b2==false)
				{
						boolean b1=hmap.containsKey(word);
						if(b1==true)
						{
						if(max<((Double)hmap.get(word)+1d))
							max=((Double)hmap.get(word)+1d);
						hmap.put(word,(Double)hmap.get(word)+1d);
						}
						else
						hmap.put(word,1d);
				}
				}
				
		}

	}
System.out.println(hmap);
Set mapSet = (Set) hmap.entrySet();
Iterator mapIterator = mapSet.iterator();
        	while (mapIterator.hasNext()) 
        	{		
        		Map.Entry mapEntry = (Map.Entry) mapIterator.next();
        		String keyValue = (String) mapEntry.getKey();
        		Double value = (Double) mapEntry.getValue();
        		System.out.println(value);
		Iterator it=ts.iterator();
		c=0;
		while(it.hasNext())
			{
			Object element=it.next();
			boolean bl=(keyValue).equals(element);
				if(bl==true)
				{
				break;
				}
				
				if(bl==true)
				break;
				c++;
			}  
		//System.out.println(max);
		if(c<ts.size())
		{
		Double db=(Double)ia[c];
		//try{
		hmap.put(keyValue,(Double)(value/max)*db);
		sum+=((Double)(value/max)*db)*((Double)(value/max)*db);//}catch(Exception e){}
		}
        	}
System.out.println("done"+hmap.size());
System.out.println(sum);
Thread.sleep(1000);
Double length=Math.sqrt(sum);
System.out.println(max+" "+length);

f1.write(String.valueOf(length)); 
f1.write(" ");
f1.close();
}//end of ipCal
}//end of input

class CosSim
{
void cosSim(ArrayList al,HashMap hmap) throws Exception
{
FileWriter f2=new FileWriter("Output1_CosSim");
BufferedReader br1=new BufferedReader(new FileReader("Input1_length"));
BufferedReader br=new BufferedReader(new FileReader("Output1_length"));
int c1=0;
String str;
StringTokenizer tr;
String str1=br1.readLine();
Object ia[]=al.toArray();
Iterator itr = al.iterator();			
		while((str=br.readLine())!=null)
		{		
		tr=new StringTokenizer(str," ");
		while(tr.hasMoreTokens())
		{
		String word=tr.nextToken();	
		HashMap hm=(HashMap)ia[c1];
		c1++;
		Double s=0d;
		Set mapSet = (Set) hm.entrySet();
		Iterator mapIterator = mapSet.iterator();
        		while (mapIterator.hasNext()) 
        		{		
        			Map.Entry mapEntry = (Map.Entry) mapIterator.next();
        			String keyValue = (String) mapEntry.getKey();
        			Double value = (Double) mapEntry.getValue();
			boolean b=hmap.containsKey(keyValue);
				if(b==true)
				{
					s+=((Double)hmap.get(keyValue))*(value);		
				}
        		}
		Double vt=s/((Double.parseDouble(word))*(Double.parseDouble(str1)));
		f2.write(String.valueOf(vt));
		f2.write(" ");
		}
		}
f2.close();
}//end of function cosSim
}//end of CosSim

class Result
{
void res() throws Exception
{
BufferedReader br=new BufferedReader(new FileReader("Output1_CosSim"));
Double max=0d;
int i=0,c=0;
String str;
StringTokenizer tr;		
		while((str=br.readLine())!=null)
		{		
		tr=new StringTokenizer(str," ");
		while(tr.hasMoreTokens())
		{
		String word=tr.nextToken();
		c++;
		Double a=Double.parseDouble(word);
			if(max<a&&c<19600)
			{
				max=a;
				i=c;
			}
		
		}
		}	
System.out.println(max+" "+i);
}
}
class Demo
{
public static void main(String args[]) //throws Exception
{
HashMap hmap=new HashMap();
TreeSet ts=new TreeSet();
ArrayList al=new ArrayList();
ArrayList idf=new ArrayList();
DirList dl=new DirList();

IDF id=new IDF();
TFIDF tid=new TFIDF();
Input in=new Input();
CosSim cs=new CosSim();
Result rt=new Result();
String st[]={"a", "a's","about", "above", "above", "across","accordingly", "after", "afterwards", "again", "against", "all","allow", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anybody","anything","anyway","anyways", "anywhere","appropriate", "are", "around", "as","aside",  "at","available", "back","be","became","because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between","beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "can't", "co", "con", "could", "certain","certainly","changes","clearly",	"co","com","come","comes","concerning","consequently",
"consider","considering","contain","containing","contains",
"corresponding","course","currently","couldn't", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during","did","didn't","doing","different","described", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves"};

TreeSet ts1=new TreeSet(Arrays.asList(st));	
try
{

dl.make(ts,ts1,al);
id.calc(ts,al,idf);
tid.TfIdf(ts,al,idf);
in.ipCal(ts,ts1,idf,hmap);
cs.cosSim(al,hmap);
rt.res();
}
catch(Exception e)
{}
//System.out.println(ts);
}
}