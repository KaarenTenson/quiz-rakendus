package org.example.quizrakendus;

import java.io.*;
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
    public static List<String> leiaprojektid(){
        List<String> projektid = new ArrayList<>();
        try (InputStream baidid = new FileInputStream("Projektid.txt");
             InputStreamReader tekst = new InputStreamReader(baidid, Charset.defaultCharset());
             BufferedReader puhverdatud = new BufferedReader(tekst)) {
            String rida = puhverdatud.readLine();
            while (rida != null) {
                projektid.add(rida);
                rida = puhverdatud.readLine(); // loeb järgmise rea. kui ei saa, tagastab nulli
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return projektid;
    }
    public static void kirjutaUle(String[] projektid){
        try (
                BufferedWriter puhverdatud = new BufferedWriter(new FileWriter("Projektid.txt",Charset.defaultCharset()))) {
            for(int i=0;i<projektid.length;i++){
            puhverdatud.write(projektid[i]);}
            // loeb järgmise rea. kui ei saa, tagastab nulli
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void lisaprojektid(String projekt){
        try (
                BufferedWriter puhverdatud = new BufferedWriter(new FileWriter("Projektid.txt", Charset.defaultCharset(), true))) {

                puhverdatud.write(projekt+"\n");
            // loeb järgmise rea. kui ei saa, tagastab nulli
        }   catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException {
        String[] uus={"mis see on?", "Kana"};
        String[] teine1={"mis see on?", "kaur"};
        ArrayList<String[]> uus1=new ArrayList<>();
        uus1.add(uus);
        uus1.add(teine1);
        quiz esimen=new quiz("test",uus1,uus1.size());
        esimen.kirjuta();
        quiz teine=loe("test");
        teine.valjasta();
    }
}
