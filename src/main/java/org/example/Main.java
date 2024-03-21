package org.example;

public class Main {
    public static void main(String[] args) {

//        int a=20,b=50;
//        int largetNumber = a>b?a:b;
//        System.out.println("Largest Number among two : "+ largetNumber);
//
//        String s="Welcome to AuTOmatioN";
//        int lowerCount=0;
//        int upperCount=0;
//        for(int i=0;i<s.length(); i++){
//            char ch = s.charAt(i);
//            if(ch>=65 && ch<=90){
//                upperCount++;
//            } else{
//                lowerCount++;
//            }
//        }
//        System.out.println("LowerCount : "+ lowerCount);
//        System.out.println("UpperCount : "+ upperCount);
        System.out.println(isVowelPresent("STR NMY"));

        String str = "Welcome";
//        if(str.contains("a"||'e'||'i'||'o'||'u')){
//
//        }
    }
    public static boolean isVowelPresent(String s){
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch=='e' || ch=='i' || ch=='o' || ch=='u' || ch=='a'){
                break;
            } else{
//                return false;
                System.out.println(ch);
            }

        }
        return true;
    }
}