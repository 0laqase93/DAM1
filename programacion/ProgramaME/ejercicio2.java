import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ejercicio2 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n1 = Integer.parseInt(br.readLine());
            ArrayList<Integer> num = new ArrayList<>();
            ArrayList<String> nom = new ArrayList<>();
            ArrayList<Integer> no = new ArrayList<>();
            for (int i = 0; i < n1; i++) {
                String[] val = br.readLine().split(" ");

                boolean vale = true;
                for (int nume : num) {
                    if (nume == Integer.parseInt(val[0])){
                        vale = false;
                        break;
                    }
                }
                if (!vale) {
                    no.add(Integer.parseInt(val[0]));
                } else {
                    num.add(Integer.parseInt(val[0]));
                    nom.add(val[1]);
                }
            }

            int menor = 10001;
            int indexMenor = 0;
            int index = 0;
            for (int valor : num) {
                if ((valor < menor) && !(no.contains(valor))) {
                    menor = valor;
                    indexMenor = index;
                }
                index++;
            }
            System.out.println(nom.get(indexMenor));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
