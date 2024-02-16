import java.util.Scanner;
import Armas.*;
import Heroe.*;
import Monstruo.*;
import Sprites.Sprite;

public class dungeon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sprite sprites = new Sprite();
        System.out.print(sprites.getFondo());
        sc.nextLine();
    }
}