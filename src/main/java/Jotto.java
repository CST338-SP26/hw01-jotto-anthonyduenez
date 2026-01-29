import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Anthony Duenez Ramirez
 * @version 0.1.0
 * @Since 1/29/26
 **/
public class Jotto {
    private static final int WORD_SIZE = 5;
    private static final boolean DEBUG = true;

    private final ArrayList<String> wordList;
    private final ArrayList<String> playerGuesses;
    private final ArrayList<String> playedWords;
    private String currentWord;
    private String filename;
    private int score;

    public Jotto(String filename) {
        this.filename = filename;
        readWords();
    }

    public ArrayList<String> getWordList() {
        return wordList;
    }

    public ArrayList<String> getPlayerGuesses() {
        return playerGuesses;
    }

    public ArrayList<String> getPlayedWords() {
        return playedWords;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<String> readWords(){

    }

    public void play(){

    }

    public String showPlayedWords(){

    }

    public String showWordList(){

    }

    public ArrayList<String> showPlayerGuesses(){

    }

    protected int guess(){

    }

    public ArrayList<String> getPlayedWords(){

    }

    public String getCurrentWord(){

    }

    public int getLetterCount(String wordGuess){

    }

    protected void updateWordList(){

    }

    public boolean pickWord(){

    }

    public int score(){
        return getScore();
    }

    public boolean addPlayerGuess(String wordGuess){

    }

    protected void playerGuessScores(ArrayList<String> guesses){

    }
}
