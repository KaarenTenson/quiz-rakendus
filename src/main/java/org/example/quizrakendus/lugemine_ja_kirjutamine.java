package org.example.quizrakendus;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lugemine_ja_kirjutamine {
    public static quiz loe(String projekt) throws IOException {
        List<String[]> kaartidelist = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(projekt+".bin"))) {
            int arv = dis.readInt();
            for (int i = 0; i < arv; i++) {
                String Kusismus = dis.readUTF();
                String Vastus = dis.readUTF();
                kaartidelist.add(new String[]{Kusismus, Vastus});
            }
            return new quiz(projekt,kaartidelist,arv);
        }
    }
    private static List<String> leiaprojektid(){
        List<String> projektid = new ArrayList<>();
        try (InputStream baidid = new FileInputStream("Projektid.txt");
             InputStreamReader tekst = new InputStreamReader(baidid, Charset.defaultCharset());
             BufferedReader puhverdatud = new BufferedReader(tekst)) {
            String rida = puhverdatud.readLine();
            while (rida != null) {
                rida = puhverdatud.readLine(); // loeb järgmise rea. kui ei saa, tagastab nulli
                projektid.add(rida);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projektid;
    }
    public static void kirjutaprojektid(ArrayList<quiz> projektid){
        try (
                BufferedWriter puhverdatud = new BufferedWriter(new FileWriter("sisend.txt",Charset.defaultCharset()))) {
            for(int i=0;i<projektid.size();i++){
            puhverdatud.write(projektid.get(i).getNimi());}
            // loeb järgmise rea. kui ei saa, tagastab nulli
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void lisaprojektid(ArrayList<quiz> projektid){
        try (
                BufferedWriter puhverdatud = new BufferedWriter(new FileWriter("sisend.txt", Charset.defaultCharset(), true))) {
            for(int i=0;i<projektid.size();i++){
                puhverdatud.write(projektid.get(i).getNimi());}
            // loeb järgmise rea. kui ei saa, tagastab nulli
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        String[] flashcard = {"küsimus", "vastus"};
        List<String[]> flashcardid = new ArrayList<>();
        flashcardid.add(flashcard);
        quiz esimen=new quiz("test",flashcardid,1);
        esimen.kirjuta();
    }
}
