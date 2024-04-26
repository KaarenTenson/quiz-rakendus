package org.example.quizrakendus;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class quiz {
    private String nimi;
    private List<String[]> flashcards;
    private int arv;

    public quiz(String nimi, List<String[]>  flashcards, int arv) {
        this.nimi = nimi;
        this.flashcards = flashcards;
        this.arv = arv;
    }
    public void kustuta(int indeks){
        flashcards.remove(indeks);
    }
    public void lisa(String[] kaart){
        flashcards.add(kaart);
        arv+=1;
    }
    public void kirjuta() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(nimi+".bin"))) {
            dos.writeInt(arv);
            for (String[] el:flashcards) {
                dos.writeUTF(el[0]);
                dos.writeUTF(el[1]);
            }
        }

    }
    public void muuda(int indeks, String uusKus, String uusVas){
        this.flashcards.get(indeks)[0]=uusKus;
        this.flashcards.get(indeks)[1]=uusVas;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public List<String[]> getFlashcards() {
        return flashcards;
    }

    @Override
    public String toString() {
        return "quiz{" +
                "nimi='" + nimi + '\'' +
                ", flashcards=" + flashcards +
                ", arv=" + arv +
                '}';
    }
    public void valjasta(){
        for (String[] el:flashcards){
            System.out.println(Arrays.toString(el));
        }
    }

    public void setFlashcards(List<String[]> flashcards) {
        this.flashcards = flashcards;
    }
}
