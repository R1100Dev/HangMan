import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Proccess {
    private int Error = -1;
    private String secrete = "";

    public ArrayList<String> wrong;
    public ArrayList<String> known ;
    private ArrayList<String> words ;

    public void load() {
        try {

            this.words.clear();
            FileReader fr = new FileReader("words.txt");
            Scanner s = new Scanner(fr);
            while (s.hasNext()) {
                this.words.add(s.nextLine());
            }
            s.close();

        } catch (IOException ee) {
            JOptionPane.showMessageDialog(null, "oops! there ws an error ! :(");
        }
    }
    public void init(){
        wrong=new ArrayList<String>();
        known  =new ArrayList<String>();
        words  =new ArrayList<String>();
        load();
    }
    private String random() {
        Random r = new Random();
        Date d = new Date();
        r.setSeed(d.getTime());
        System.out.println(this.words.size());

        int i = r.nextInt(words.size());
        return this.words.get(i);
    }

    public int getError(){
        return this.Error;
    }
    public void setError(int val){
        this.Error=val;
    }
    public void newWord(){
        this.secrete=random();
        setError(this.secrete.length());
        known.clear();

        for (int x = 0; x < secrete.length(); x++) {
            known.add(" _");
        }

        wrong.clear();
    }
    public String getSecrete(){
        return this.secrete;
    }
    public String display(){
        String in="";
        for (int x = 0; x < secrete.length(); x++) {
            in = in + known.get(x);
        }
        return in;
    }
    public void check(){

    }
    public void setSecrete(String s){
        this.secrete=s;
    }
}
