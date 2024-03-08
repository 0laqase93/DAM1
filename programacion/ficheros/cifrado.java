import java.io.*;

public class cifrado {
    public static void main(String[] args) {
        File encriptado;
        try {
            File archivo = new File(args[0]);
            encriptado = new File(args[0].replace(".txt", "") + ".cf" + args[1]);
            FileWriter in = new FileWriter(encriptado);
            FileReader fos = new FileReader(archivo);
            BufferedReader out = new BufferedReader(fos);

            String linea = out.readLine();
            while (linea != null) {
                in.write(encriptar(linea, Integer.parseInt(args[1])));
                linea = out.readLine();
            }

            in.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encriptar(String encriptado, int posiciones){
        int longitud = encriptado.length();
        String desencriptado = "";
        
        for(int i = 1; i < longitud; ++i){
            int letra = encriptado.charAt(i);

            if(letra < 'A'){
                desencriptado += (char)letra;
            }else if((letra >= 'A') && (letra <= 'Z')){
                if(letra + posiciones > 'Z'){
                    desencriptado += (char)(letra + posiciones - 'Z' + 64);
                }else if(letra + posiciones < 'A'){
                    desencriptado += (char)(letra + posiciones + 'Z' - 64);
                }else{
                    desencriptado += (char)(letra + posiciones);
                }  
            }else if((letra > 'Z' && letra < 'a')){
                desencriptado += (char)letra;
            }else if((letra >= 'a' && letra <= 'z')){
                if(letra + posiciones > 'z'){
                    desencriptado += (char)(letra + posiciones - 'z' + 96);
                }else if(letra + posiciones < 'a'){
                    desencriptado += (char)(letra + posiciones + 'z' - 96);
                }else{
                    desencriptado += (char)(letra + posiciones);
                }
            }else{
                desencriptado += (char)letra;
            }
        }   
        return desencriptado;
    }
}
