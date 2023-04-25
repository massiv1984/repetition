import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        Scanner skannare = new Scanner(System.in);
        System.out.print("skriv namnet på filen du vill skanna: ");
        String fil = skannare.nextLine();
        skannare.close();

        try {
            ArrayList<String> logginfo = readLoggfil(fil);
            ArrayList<String> antalgithub = hittaAntalGithub(logginfo);
            System.out.println("githubrelaterade grejer i loggen: " + antalgithub);
            System.out.println("totalt antal grejer i loggen: " + logginfo.size());
            int antalfel = hittaFel(logginfo);
            System.out.println("antal error i loggen: " + antalfel);
        } catch (FileNotFoundException e) {
            System.out.println("ojdå något gick fel: " + e.getMessage());
        }
    }

    public static ArrayList<String> readLoggfil(String fil) throws FileNotFoundException {
        ArrayList<String> logginfo = new ArrayList<>();
        Scanner skannare = new Scanner(new File(fil));
        while (skannare.hasNextLine()){
            String rad = skannare.nextLine();
            logginfo.add(rad);
        }
        skannare.close();
        return logginfo;
    }

    public static int hittaFel(ArrayList<String> logginfo){
        int antalfel = 0;
        for (String rad : logginfo) {
            if (rad.contains("ERROR")){
                antalfel++;
            }
        }
        return antalfel;
    }

    public static ArrayList<String> hittaAntalGithub(ArrayList<String> logginfo){
        ArrayList<String> antalgithub = new ArrayList<>();
        for (String rad : logginfo){
            if (rad.contains("github")){
                antalgithub.add(rad + "\n");
            }
        }
        return antalgithub;
    }
}