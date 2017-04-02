/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class Translate {

    //FILES
    
    private File TRANSLATE = new File("C:\\Users\\alumno\\Documents\\NetBeansProjects\\Translate\\src\\translate\\translate.txt");    
        
    private String wordSpanish, wordIngles, wordFrances, wordAleman;
    private int decition;
    
    private String wordToSearch;
    private int lenguaje;
    
    private int otherSee;
    
    //admin variables
    
    private Scanner scanner = new Scanner(System.in);   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Translate translate = new Translate();
            translate.run();
            
        } catch (Exception ex) {
            System.out.println("Error");
        }
    }    
    
    private void run() {
        
        System.out.println("Choose which module you want to use");
        System.out.println("1............... Admin");
        System.out.println("2............... User");
        
        decition = scanner.nextInt();
        
        switch(decition) {
            case 1:
                adminModule();
                break;
            case 2:
                userModule();
                break;
        }
                
    }
    
    private void adminModule() {
        System.out.println("Welcome to the admin module");        
        
        setInfo();
    }
    
    private void setInfo() {
        
        System.out.println("Ingrese la palabra en Español");
        wordSpanish = scanner.next();
                
        System.out.println("Ingrese la palabra en Ingles");
        wordIngles = scanner.next();
        
        System.out.println("Ingrese la palabra en Frances");
        wordFrances = scanner.next();
        
        System.out.println("Ingrese la palabra en Aleman");
        wordAleman = scanner.next();
        
        saveTranslate(wordSpanish,wordIngles,wordFrances,wordAleman);
        
        viewMore();
    }

    private void saveTranslate(String spanish, String ingles, String frances, String aleman) {
        try {
                   
            BufferedWriter translate =new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TRANSLATE, true), "utf-8"));            
            
            translate.write(spanish + "," + ingles + "," + frances + "," + aleman + "\r\n");                    
            translate.close();          
            
            System.out.println("Se han guardado correctamente las palabras");
        } catch (Exception ex) {
          //Captura un posible error le imprime en pantalla 
            System.out.println(ex.getMessage());
        } 
    }
    
    private void userModule() {
        
        System.out.println("Enter the lenguaje you want to search");       
        lenguajeMenu();
        lenguaje = scanner.nextInt();                     
        comprobateLenguaje(lenguaje);
                
        System.out.println("Enter the word you want to search");
        wordToSearch = scanner.next();                
             
        searchFile(wordToSearch, lenguaje);   
        
        viewMore();
    }
    
    private void comprobateLenguaje(int lenguaje) {
        if(lenguaje > 3 ) {
            System.out.println("This language does not exist");
            run();
        }
    }
    
    private void lenguajeMenu() {
        System.out.println("0.................... Spanish");
        System.out.println("1.................... Ingles");
        System.out.println("2.................... Frances");
        System.out.println("3.................... Aleman");
    }
    
    private void searchFile(String word, int lenguaje) {
        try {                                       
            
            BufferedReader buffer = new BufferedReader(new FileReader(TRANSLATE));                
            String lines;

            while((lines = buffer.readLine())!= null) {
                if(lines.indexOf(",")!= -lenguaje){
                    if (lines.split(",")[lenguaje].equalsIgnoreCase(word)) {                        
                        System.out.println("Español: " + lines.split(",")[0]);
                        System.out.println("Ingles: " + lines.split(",")[1]);
                        System.out.println("Frances: " + lines.split(",")[2]);
                        System.out.println("Aleman: " + lines.split(",")[3]);
                    } 
                }
            }

            buffer.close();
            
        } catch(Exception ex) {
            System.out.println("Error in the lecture");
        }               
    }
    
    private void viewMore() {
        System.out.println("Desea seguir regresar al menu principal");
        System.out.println("1... SI");
        System.out.println("2... NO");
        otherSee = scanner.nextInt();
                
        if(otherSee == 1 ) {
            run();
        }          
    }
}
