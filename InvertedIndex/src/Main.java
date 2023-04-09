
import java.io.*;
import java.io.IOException;
import java.util.*;


    public class Main {

        public static void main(String args[])  throws IOException{
            Scanner input=new Scanner(System.in);
            InvertedIndex invertedIndex=new InvertedIndex();
            String []documents=new String[10];
            documents=new String[]
                    //path of Documents you can change it
                    {"D:\\Documents/Document1.txt","D:\\Documents/Document2.txt","D:\\Documents/Document3.txt",
                    "D:\\Documents/Document4.txt","D:\\Documents/Document5.txt", "D:\\Documents/Document6.txt",
                    "D:\\Documents/Document7.txt","D:\\Documents/Document8.txt","D:\\Documents/Document9.txt",
                    "D:\\Documents/Document10.txt"};
            invertedIndex.createIndex(documents);//create index
            invertedIndex.printPoistinglist();
            System.out.println("Enter The word you want : ");
            String term=input.next();
            //search for This Term and return with documents that have This Term
            invertedIndex.SearchTerm(term);

        }
    }


