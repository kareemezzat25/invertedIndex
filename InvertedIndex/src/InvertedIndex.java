import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class InvertedIndex {
    HashMap<String,DictEntry>termIndex;

    InvertedIndex()
    {
        termIndex=new HashMap<>();
    }
   public void createIndex(String []files) throws IOException {
        int documentId=1;
        try {
        for(String filename:files)
        {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
           String []words=line.split("\\W+");//(//W+)
            for(String word:words)
            {
                word=word.toLowerCase();
                if(!termIndex.containsKey(word))//search for term in inverted index
                {
                    termIndex.put(word, new DictEntry(documentId));
                    termIndex.get(word).term_freq++;
                    termIndex.get(word).doc_freq++;
                }
                else
                {
                    termIndex.get(word).term_freq++;
                    Poisting poistinglist=termIndex.get(word).plist;


                    while(poistinglist!=null)
                    {
                        if(poistinglist.docId==documentId)
                        {
                            break;
                        }
                        if(poistinglist.next==null) {
                            poistinglist.next = new Poisting(documentId);
                            termIndex.get(word).plist.documentfrequency++;
                            break;
                        }
                        poistinglist=poistinglist.next;
                    }
                }
            }
        }
        documentId++;
    }
    }
    catch(FileNotFoundException e) {
        e.printStackTrace();
    }
   }

   public void printPoistinglist() {
       TreeMap<String,DictEntry>sortedDict=new TreeMap<>();
       sortedDict.putAll(termIndex);
       for(Map.Entry<String, DictEntry> term : sortedDict.entrySet())
        {
            Poisting poistlist = term.getValue().plist;
            System.out.print(term.getKey());
            System.out.print("-------->{");
            while(poistlist!=null)
            { if(poistlist.next==null)
                {
                    System.out.print(poistlist.docId);
                    break;
                }
                System.out.print(poistlist.docId+",");
                poistlist=poistlist.next;
            }
            System.out.println("}---------->DocumentFrequncey : "+term.getValue().plist.documentfrequency);
       }
   }
    public void print()
    {
        TreeMap<String,DictEntry>sortedDict=new TreeMap<>();
        sortedDict.putAll(termIndex);
        for(Map.Entry<String, DictEntry> dictonary : sortedDict.entrySet())
        {
            System.out.println(dictonary.getKey() +"    DocumentFrequency : "+dictonary.getValue().plist.documentfrequency+"   TermFrequency : "+dictonary.getValue().term_freq);
        }
    }
    public void SearchTerm(String dictonary)
    {
        for(Map.Entry<String,DictEntry>term :termIndex.entrySet())
        {
            Poisting poistlist = term.getValue().plist;
            if(term.getKey().equals(dictonary))
            {
                System.out.print("The term : "+term.getKey()+" is exist in this documents");
                System.out.print(" -------> {");
                while(poistlist!=null)
                { if(poistlist.next==null)
                {
                    System.out.print(poistlist.docId);
                    break;
                }
                    System.out.print(poistlist.docId+",");
                    poistlist=poistlist.next;
                }
                System.out.println("}");
            }
        }
    }
}
