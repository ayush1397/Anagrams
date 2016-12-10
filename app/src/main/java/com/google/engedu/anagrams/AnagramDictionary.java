package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

private ArrayList<String> wordList = new ArrayList<String>();
private HashMap<String, String> lettersToWord = new HashMap<String, String>();
private HashSet<String> wordSet = new HashSet<String>();

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);

            wordSet.add(word);
            String key = sortLetters(word);
            if(lettersToWord.containsKey(key)){
                tempList = lettersToWord.get(key);
            }
            else{
                tempList = new ArrayList<String> ();
            }
        }
    }

    public boolean isGoodWord(String word, String base) {

        if(wordSet.contains(base)){
            if(!word.contains(base)){
                return true;
            }
        }
        return false;
    }

    String sortLetters(String s){
        int l = s.length();
        char[] sCharArray = s.toCharArray();
        char ch;
        //selection sort
        for(int i=0;i<l-1;i++){
            small=i;
            for(int j=i+1;j<l;j++){
                if((int)sCharArray[small]>(int)sCharArray[j]){
                    small=j;
                }
            }

            ch = sCharArray[i];
            sCharArray[i] = sCharArray[small];
            sCharArray[small] = ch;
        }

        s = String.valueOf(sCharArray);

        return s;
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        ArrayList<String> result = new ArrayList<String>();
        String word,wordSorted,targetWordSorted;

        targetWordSorted = sortLetters(targetWord);

        tempList = lettersToWord.get(targetWordSorted);

        if(tempList!=null){
            for(int i=0;i<tempList.size()){

            }
        }

        for(int i=0;i<wordList.size();i++){
            word = wordList.get(i);
            wordSorted = sortLetters(word);
            if(targetWordSorted.length()==wordSorted.length()){
                if(targetWordSorted.equals(wordSorted) && isGoodWord(word,targetWord)){
                    result.add(word);
                }
            }
        }
        return result;
    }

    public ArrayList<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        result = getAnagrams(word);
        String targetWordWEA = "";
        for(char ch='a';ch<='z';ch++){
            targetWordWEA = word + Character.toString(ch);
            temp = getAnagrams(targetWordWEA);
            for(int i=0;i<temp.size();i++){
                if(isGoodWord(temp.get(i),word)){
                    result.add(temp.get(i));
                }
            }
        }

        return result;
    }

    public String pickGoodStarterWord() {

        wordList = sizeToWords.get(wordLength);

        if(wordList!=null){
            int t = random.nextInt(wordList.size());
            for(int i=t;i<wordList.size();i++){
                tempList = getAnagramsWithOneMoreLetter(wordList.get(i));
                if(tempList.size()>=MIN_NUM_ANAGRAMS){
                    return wordList.get(i);
                }
            }

            if(wordLength!=MAX_WORD_LENGTH){

            }
        }

        return "stop";
    }
}
