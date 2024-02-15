package cartas;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Pantalla {
    private int hor;
    public int getHor() {
        return hor;
    }
    public int getVer() {
        return ver;
    }
    private int ver;
    private int cx;
    private int cy;
    private char[][] chars;
    private char[][] cols;
    private char ultcol;

    public Pantalla(int hor, int ver){
        this.hor = hor;
        this.ver = ver;
        this.chars = new char[hor][ver];
        this.cols = new char[hor][ver];
        this.ultcol='w';
        this.cx=0;this.cy=0;
        this.borra();
        
    }
    final private Map<Character,String> colors = Map.of(
                                'x',"\u001B[0m", // reset
                                'r',"\u001B[31m",// rojo
                                'v',"\u001B[32m",// verde
                                'a',"\u001B[33m",// amarillo
                                'b',"\u001B[34m",// azul
                                'l',"\u001B[35m",// lila
                                'c',"\u001B[36m",// cyan
                                'w',"\u001B[37m",// blanc 
                                'n',"\u001B[90m",// gris 
                                'm',"\u001B[40m" // negro fondo
                                );
    
    final private static Map<Character,char[]> quadre = new HashMap<>();
    static {
        quadre.put('b', new char[]{'█', '█', '█', '█', '█', '█'}); // bloc
        quadre.put('l', new char[]{'┌', '┐', '┘', '└','│', '─',}); // linea
        quadre.put('d', new char[]{'╔', '╗', '╝', '╚','║', '═',}); // doble
    }

    


    protected void clear() {
        try {
                String os = System.getProperty("os.name").toLowerCase();
                ProcessBuilder processBuilder;
                if (os.contains("win")) { // Windows
                    processBuilder = new ProcessBuilder("cmd", "/c", "cls");
                } else { // Unix/Linux/Mac
                    processBuilder = new ProcessBuilder("clear");
                }
                Process process = processBuilder.inheritIO().start();
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
        }
    }
    public void situa(int x, int y, String texte, char color) {
        int av=0;
        for (char c : texte.toCharArray()) {
            if (c=='\n'){
                av=0;y++;
            }else{
                this.posa(x+av,y,c,color);
                av++;
            }
            
        }
        cy=y+1;
        cx=x;

    }
    //sobrecàrrega sense color
    public void situa(int x, int y, String texte){
        this.situa(x, y, texte, this.ultcol);
    }
    //sobrecàrrega sense posició
    public void situa(String texte, char color){
        this.situa(this.cx,this.cy,texte, color);
    }
    //sobrecàrrega sense posició ni color
    public void situa(String texte){
        this.situa(texte, this.ultcol);
    }

    private void posa(int x, int y, char ch, char col){
        if (x>=0 && x<this.hor && y>=0 && y < this.ver){
            this.cols[x][y]=col;
            this.chars[x][y]=ch;
        }
        this.ultcol=col;
    }
    public void marc(int x, int y, int hor, int ver, char l,char col){
        hor=(hor<2)?2:hor-1;
        ver=(ver<2)?2:ver-1;
        this.posa(x,y,quadre.get(l)[0],col);
        this.posa(x+hor,y,quadre.get(l)[1],col);
        this.posa(x+hor,y+ver,quadre.get(l)[2],col);
        this.posa(x,y+ver,quadre.get(l)[3],col);
        for (int i = x+1; i < x+hor; i++) {
            this.posa(i, y, quadre.get(l)[5], col);
            this.posa(i, y+ver, quadre.get(l)[5], col);            
        }
        for (int i = y+1; i < y+ver; i++) {
            this.posa(x, i, quadre.get(l)[4], col);
            this.posa(x+hor, i, quadre.get(l)[4], col);            
        }
        
    }
    public void marc(){
        this.marc(0,0, this.hor, this.ver, 'l', this.ultcol );
    }
    public void mostra(){
        this.clear();
        for (int y = 0; y < this.ver; y++) {
            for (int x = 0; x < this.hor; x++) {
                if (cols[x][y]!=this.ultcol){
                    this.ultcol=cols[x][y];
                    System.out.print(colors.get('x')+colors.get(ultcol));
                }
                System.out.print(chars[x][y]);
            }
            System.out.println();
        }
    }
    public void borra(){
        for (int y = 0; y < this.ver; y++) {
            for (int x = 0; x < this.hor; x++) {
                this.chars[x][y]=' ';
                this.cols[x][y]='w';
            }
            System.out.println();
        }
    }


}