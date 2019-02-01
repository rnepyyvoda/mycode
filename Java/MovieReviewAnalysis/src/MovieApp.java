        import java.io.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Scanner;
        import java.util.Set;
        import java.text.DecimalFormat;

public class MovieApp {
    private static HashMap<String, ArrayList<Integer>> sortedWordsWithRatings;
    private static HashMap<String, Double> wordRatings;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    public static void main(String[] args) {
        ArrayList<String> reviewFile;
        String newReview, fileName, choice;
        double score;
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter a name of the sample review file:");
        fileName=in.nextLine();
        reviewFile=readTxtFile(fileName);

        wordRatings=getAvgWordRatings(reviewFile);
        do {
            System.out.println("Please enter your review:");
            newReview = in.nextLine();

            score = produceTheScore(newReview);

            System.out.println(newReview + ". Automatic review score: " + df2.format(score));
            System.out.println();
            System.out.println("Do you want to rate another review? Y/N");
            choice=in.nextLine();

        }while(choice.equalsIgnoreCase("y"));
    }


    /**
     * Method reads .txt file with sample reviews and ratings into Array List line by line
     * @param fileName
     * @return
     */
    public static ArrayList<String> readTxtFile(String fileName) {
        Scanner ratingsScanner;


        try {
            File ratings = new File(fileName);
            ratingsScanner = new Scanner(ratings);

        } catch (Exception e) {
            throw new IllegalArgumentException("No such file file: " + fileName);
        }

        ArrayList<String> sampleRatings=new ArrayList<>();
        while (ratingsScanner.hasNext()){
            sampleRatings.add(ratingsScanner.nextLine());
        }
        ratingsScanner.close();
        System.out.println();
        return sampleRatings;
    }

    /**
     * Method receives reviews and ratings in ArrayList, sorts them and places each new word into HasMap with word's average rating.
     * @param sampleRatings
     * @return
     */
    public static HashMap<String, Double> getAvgWordRatings (ArrayList<String> sampleRatings){

        // Writing Array List into HashMap with KEY Word and VALUE ArrayList of cumulative ratings
        sortedWordsWithRatings=new HashMap<>();

        for(int i=0;i<sampleRatings.size();i++){
            String currentLine=sampleRatings.get(i);
            newRatingRecord(currentLine);
        }

        HashMap<String, Double> avgWordRatings=new HashMap<>();
        avgWordRatings=getAvgWordRatingsIntermediate(sortedWordsWithRatings);
        return avgWordRatings;
    }

    /**
     * This is supplementary method that serves getAvgWordRatings.
     * Method writes each word from current string into HashMap sortedWordsWithRatings.
     * @param currentLine
     */
    public static void newRatingRecord (String currentLine){

        Scanner stringScanner=new Scanner(currentLine);
        int currentRating=stringScanner.nextInt();

        while (stringScanner.hasNext()){
            String currentWord=stringScanner.next().toLowerCase();

            ArrayList<Integer> currentWordRatings=new ArrayList<>();
            if(sortedWordsWithRatings.containsKey(currentWord)){
                currentWordRatings=sortedWordsWithRatings.get(currentWord);
                currentWordRatings.add(currentRating);
                sortedWordsWithRatings.replace(currentWord, currentWordRatings);
            }
            else {
                currentWordRatings.add(currentRating);
                sortedWordsWithRatings.put(currentWord, currentWordRatings);
            }
        }
    }

    /**
     * This is supplementary method that serves getAvgWordRatings.
     * Method calculates average rating for each word and writes result into new HashMap avgWordRatings.
     * @param sortedWordsWithRatings
     * @return
     */
    public static HashMap<String, Double> getAvgWordRatingsIntermediate(HashMap<String,ArrayList<Integer>> sortedWordsWithRatings){

        HashMap<String,Double> avgWordRatings=new HashMap<>();
        Set<String> words = sortedWordsWithRatings.keySet();

        for (String word:words){
            ArrayList<Integer> cumulativeRatings=sortedWordsWithRatings.get(word);
            int sum=0;
            for(int rating:cumulativeRatings){
                sum+=rating;
            }
            double avgRating=sum/cumulativeRatings.size();

            avgWordRatings.put(word,avgRating);
        }
        return avgWordRatings;
    }

    /**
     * This method predicts score for the new review input by customer.
     * @param newReview
     * @return
     */
    public static double produceTheScore(String newReview){
        String currentWord;
        int count=0;
        double sum=0, score;
        if(!newReview.isEmpty()){
            Scanner stringScanner=new Scanner(newReview);
            while(stringScanner.hasNext()){
                currentWord=stringScanner.next().toLowerCase();
                if(wordRatings.containsKey(currentWord)) {
                    sum += wordRatings.get(currentWord);
                    count++;
                }
                else{
                    sum+=2;//2 is default word rating, when word is not present in reference HashMap.
                    count++;
                }
            }
            score=sum/count;
            return score;
        }
        else{
            return -1;
        }
    }
}
