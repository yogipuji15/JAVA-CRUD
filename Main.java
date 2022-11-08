package com.TugasPraktikum7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("=========================================");
            System.out.println("MENU: ");
            System.out.println("1) Registrasi");
            System.out.println("2) Login");
            System.out.println("3) Tampilkan Data");
            System.out.println("4) Ubah Data");
            System.out.println("5) Hapus Data");
            System.out.println("6) Exit");
            System.out.print("Pilih Menu: ");
            int menu = in.nextInt();
            if(menu == 1){
                System.out.println("--------------- REGISTRASI ---------------");
                System.out.print("Masukkan username: ");
                in.nextLine();
                String username = in.nextLine();
                System.out.print("Masukkan Password: ");
                String pass = in.nextLine();
                
                if(CheckRegistrasi(username, pass)){
                    System.out.println("=========================================");
                    System.out.println("        DATA BERHASIL DIDAFTARKAN");
                    System.out.println("=========================================");
                    Registrasi(username,pass);
                    FileWriter fw = new FileWriter("output.txt",true);
                    BufferedWriter writer = new BufferedWriter(fw);
                    String kode = String.format("%04d", userCode(username)); 
                    writer.write(username.substring(0, 3).toUpperCase()+"-"+kode+"\n");
                    writer.close();
                    System.out.println("Username    : "+username);
                    System.out.println("Password    : "+pass);
                    System.out.println("Kode User   : "+username.substring(0, 3).toUpperCase()+"-"+kode);
                    System.out.println("=========================================");

                }
                else{
                    System.out.println("=========================================");
                    System.out.println("           REGISTRASI GAGAL !!!");
                    System.out.println("=========================================");
                }
            }else if(menu == 2){
                System.out.println("--------------- LOGIN ---------------");
                System.out.print("Masukkan username : ");
                in.nextLine();
                String username = in.nextLine();
                System.out.print("Masukkan password : ");
                String pass = in.nextLine();

                FileReader fr = new FileReader("output.txt");
                BufferedReader reader = new BufferedReader(fr);
                String uname ;
                String password="";
                String userCode="";
                int count=0;
                while((uname = reader.readLine())!= null){
                    password = reader.readLine();
                    userCode = reader.readLine();
                    if(username.equals(uname)){
                        count++;
                        break;
                    }
                }
                if(count==0){
                    System.out.println("Login gagal, username tidak ditemukan");
                }else if(!pass.equals(password)){
                    System.out.println("Login gagal, password yang anda masukkan salah");
                }else{
                    System.out.println("=========================================");
                    System.out.println("SELAMAT DATANG "+userCode+" !!!");
                    System.out.println("Data Anda ");
                    System.out.println("Username  : "+username);
                    System.out.println("Kode User : "+userCode);
                    System.out.println("Password  : "+password);
                }
                reader.close();

            }else if(menu == 3){
                FileReader fr = new FileReader("output.txt");
                BufferedReader reader = new BufferedReader(fr);
                String username ;
                String pass;
                String userCode;
                int count = 1;
                System.out.println("  [[[[[[[[ LIST DATA ]]]]]]]]");
                while((username = reader.readLine())!= null){
                    pass = reader.readLine();
                    userCode = reader.readLine();
                    System.out.println("=========== Data "+count+" ===========");
                    System.out.println("Username  : "+username);
                    System.out.println("Kode User : "+ userCode+"\n");
                    count++;
                }
                reader.close();
            }else if(menu == 4){
                System.out.println("=========================================");
                System.out.println("Silahkan login ke akun yang ingin anda ubah");
                System.out.print("Masukkan username : ");
                in.nextLine();
                String username = in.nextLine();
                System.out.print("Masukkan password : ");
                String pass = in.nextLine();
                
                if(checkLogin(username, pass)){
                    System.out.println("=========================================");
                    System.out.println("1) Ubah Username");
                    System.out.println("2) Ubah Password");
                    System.out.print("Pilih nomor: ");
                    int option= in.nextInt();
                    String usernameUpdate=username;
                    String passUpdate=pass;
                    String usernameCheck = "                      ";

                    if(option==1){
                        System.out.println("=========================================");
                        System.out.print("Masukkan username baru: ");
                        in.nextLine();
                        usernameUpdate = in.nextLine();
                        usernameCheck = usernameUpdate;
                    }else if(option==2){
                        System.out.println("=========================================");
                        System.out.print("Masukkan password baru: ");
                        in.nextLine();
                        passUpdate = in.nextLine();
                    }

                    List<String> unameTemp = new LinkedList<String>();
                    List<String> passTemp = new LinkedList<String>();
                    List<String> userCodeTemp = new LinkedList<String>();
    
                    FileReader fr = new FileReader("output.txt");
                    BufferedReader reader = new BufferedReader(fr);
                    String uname ;
                    String password="";
                    String userCode="";
                    int count=0;
                    while((uname = reader.readLine())!= null){
                        password = reader.readLine();
                        userCode = reader.readLine();
                        if(username.equals(uname)){
                            unameTemp.add(usernameUpdate);
                            passTemp.add(passUpdate);
                            userCodeTemp.add(usernameUpdate.substring(0, 3).toUpperCase()+userCode.substring(3, 8));
                        }else{
                            unameTemp.add(uname);
                            passTemp.add(password);
                            userCodeTemp.add(userCode);
                        }
                    }
                    reader.close();
                    
                    if(CheckRegistrasi(usernameCheck, passUpdate)){
                        FileWriter fw = new FileWriter("output.txt");
                        BufferedWriter writer = new BufferedWriter(fw);
                        for(String list : unameTemp){
                            writer.write(list+"\n");
                            writer.write(passTemp.get(unameTemp.indexOf(list))+"\n");
                            writer.write(userCodeTemp.get(unameTemp.indexOf(list))+"\n");
                        }
                        writer.close();
                        System.out.println("=========================================");
                        System.out.println("========== DATA BERHASIL DIUBAH =========");
                        System.out.println("=========================================");
                    }else{
                        System.out.println("=========================================");
                        System.out.println("          GAGAL MENGUBAH DATA !!!");
                        System.out.println("=========================================");
                    }
                }

            }else if(menu == 5){
                System.out.println("=========================================");
                System.out.println("Silahkan login ke akun yang ingin anda hapus");
                System.out.print("Masukkan username : ");
                in.nextLine();
                String username = in.nextLine();
                System.out.print("Masukkan password : ");
                String pass = in.nextLine();

                List<String> unameTemp = new LinkedList<String>();
                List<String> passTemp = new LinkedList<String>();
                List<String> userCodeTemp = new LinkedList<String>();

                FileReader fr = new FileReader("output.txt");
                BufferedReader reader = new BufferedReader(fr);
                String uname ;
                String password="";
                String userCode="";
                int count=0;
                while((uname = reader.readLine())!= null){
                    password = reader.readLine();
                    userCode = reader.readLine();
                    if(username.equals(uname)){
                        count++;
                    }else{
                        unameTemp.add(uname);
                        passTemp.add(password);
                        userCodeTemp.add(userCode);
                    }
                    if(username.equals(uname) && pass.equals(password)){
                        count++;
                    }
                }
                reader.close();
                if(count==0){
                    System.out.println("Login gagal, username tidak ditemukan");
                }else if(count==1){
                    System.out.println("Login gagal, password yang anda masukkan salah");
                }else{
                    FileWriter fw = new FileWriter("output.txt");
                    BufferedWriter writer = new BufferedWriter(fw);
                    int kode =1;
                    for(String list : unameTemp){
                        String kodeString = String.format("%04d", kode);
                        writer.write(list+"\n");
                        writer.write(passTemp.get(unameTemp.indexOf(list))+"\n");
                        writer.write(userCodeTemp.get(unameTemp.indexOf(list)).substring(0, 4)+kodeString+"\n");
                        kode++;
                    }
                    writer.close();
                    System.out.println("=========================================");
                    System.out.println("           DATA BERHASIL DIHAPUS");
                    System.out.println("=========================================");
                }
            }else if(menu == 6){
                System.exit(0);
            }else{
                System.out.println("MENU TIDAK TERSEDIA, SILAHKAN MASUKKAN KEMBALI PILIHAN MENU");
            }
        }
    }

    public static void Registrasi(String username, String pass) throws IOException{
        FileWriter fw = new FileWriter("output.txt",true);
        BufferedWriter writer = new BufferedWriter(fw);
        writer.write(username+"\n");
        writer.write(pass+"\n");
        writer.close();
    }

    public static int userCode(String username) throws IOException{
        FileReader fr = new FileReader("output.txt");
        BufferedReader reader = new BufferedReader(fr);
        String uname ;
        String password;
        String userCode;
        int kode = 0;
        while((uname = reader.readLine())!= null){
            password = reader.readLine();
            userCode = reader.readLine();
            kode++;
        }
        reader.close();
        return kode;
    }

    public static boolean CheckRegistrasi(String username, String pass) throws IOException{
        // Check syarat Username
        if(username.length()<7){
            System.out.println("Username harus lebih dari 6 karakter");
            return false;
        }
        for(int i=0;i<username.length();i++){
            if(username.charAt(i) == '!' | username.charAt(i) == '@' | username.charAt(i) == '#' | username.charAt(i) == '$' | username.charAt(i) == '%' | username.charAt(i) == '^' | username.charAt(i) == '&' | username.charAt(i) == '*' | username.charAt(i) == '(' | username.charAt(i) == ')' | username.charAt(i) == '.' | username.charAt(i) == ',' ){
                System.out.println("Username tidak boleh mengandung spesial karakter");
                return false;
            }
        }

        // Check duplikasi Username
        FileReader fr = new FileReader("output.txt");
        BufferedReader reader = new BufferedReader(fr);
        String uname ;
        String password;
        String userCode;
        while((uname = reader.readLine())!= null){
            password = reader.readLine();
            userCode = reader.readLine();
            if(uname.equals(username)){
                System.out.println("Username sudah terpakai");
                return false;
            }
        }
        reader.close();
        
        // Check syarat Password
        if(pass.length()<9){
            System.out.println("Password harus lebih dari 8 karakter");
            return false;
        }else if(!pass.matches(".*[A-Z]+.*") ){
            System.out.println("Password tidak mengandung huruf kapital");
            return false;
        }else if(!pass.matches(".*[a-z]+.*")){
            System.out.println("Password tidak mengandung huruf kecil");
            return false;
        }
        int count =0;
        for(int i=0;i<pass.length();i++){
            if(pass.charAt(i) == '!' | pass.charAt(i) == '@' | pass.charAt(i) == '#' | pass.charAt(i) == '$' | pass.charAt(i) == '%' | pass.charAt(i) == '^' | pass.charAt(i) == '&' | pass.charAt(i) == '*' | pass.charAt(i) == '(' | pass.charAt(i) == ')' | pass.charAt(i) == '.' | pass.charAt(i) == ',' ){
                count++;
            }
        }
        if(count==0){
            System.out.println("Password tidak mengandung spesial karakter (!@#$%^&*().,)");
            return false;
        }
        // Check Palindrome
        StringBuilder sb=new StringBuilder(pass);  
        sb.reverse();  
        String rev=sb.toString();  
        if(pass.equals(rev)){  
            System.out.println("Password tidak boleh berbentuk Palindrome");
            return false;  
        }
        
        return true;
    }

    public static boolean checkLogin(String username, String pass) throws IOException{
        FileReader fr = new FileReader("output.txt");
        BufferedReader reader = new BufferedReader(fr);
        String uname ;
        String password="";
        String userCode="";
        int count=0;
        while((uname = reader.readLine())!= null){
            password = reader.readLine();
            userCode = reader.readLine();
            if(username.equals(uname)){
                count++;
                break;
            }
        }
        reader.close();
        if(count==0){
            System.out.println("Login gagal, username tidak ditemukan");
            return false;
        }else if(!pass.equals(password)){
            System.out.println("Login gagal, password yang anda masukkan salah");
            return false;
        }else{
            return true;
        }
    }
}
