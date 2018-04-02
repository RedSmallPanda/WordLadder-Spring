package com.example.demo;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;
import java.util.*;
import java.io.*;

@RestController
@EnableAutoConfiguration
public class Wordladder
{
    //Stack<String>a1;
    //Queue<Stack<String>>a2;

    public static Queue<Stack<String>> QueueStackOp(Queue<Stack<String>>q1,String ss){
        Stack<String> teS=q1.peek();
        Stack<String>CloneS=(Stack<String>)teS.clone();
        CloneS.push(ss);
        q1.offer(CloneS);
        return q1;
    }
    public static String Ladder(Queue<Stack<String>>a1,Set<String>a2,String w2,Vector<String>alpha,int mark) {
        String result=new String();
        String w1=a1.peek().peek();
        //a2.remove(a1.peek().peek());
        while (a1.size() > 0 && mark != 1)//control the push and pop operation of queue
        {

            String s1 = a1.peek().peek();
            //String temp = new String();
            for (int j = 0; j < s1.length(); j++)//Loop by each letter
            {
                String temp = s1;
                for (int k = 0; k < 26; k++)//replace letter j with 26 letters
                {
                    StringBuilder ttemp=new StringBuilder(temp);
                    ttemp.replace(j, j + 1, alpha.elementAt(k));
                    temp=ttemp.toString();
                    //System.out.println(temp.toUpperCase());
                    //Iterator<String> iter=a2.iterator();
                    //iter = a2.find(temp);
                    if (a2.contains(temp)) {
                        if (temp.equals(w2)) {
                            a2.remove(temp);
                            Stack<String> wola=a1.peek();
                            //wola = a1.peek();
                            Stack<String>tewola=(Stack<String>)wola.clone();
                            tewola.push(temp);
                            a1.offer(tewola);
                            //System.out.println("ladder from "+ w2 +" back to "+w1+":");
                            while (!tewola.empty()) {
                                result=result+tewola.peek() + " ";
                                tewola.pop();
                            }
                            //System.out.print("\n");
                            mark = 1;
                            return result;
                        } else {
                            a2.remove(temp);
                            Stack<String> wola=a1.peek();
                            //wola = a1.peek();
                            Stack<String>tewola=(Stack<String>)wola.clone();
                            tewola.push(temp);

                            a1.offer(tewola);
                        }

                    }

                }


            }
            a1.poll();

        }
        return result;
    }

    @RequestMapping("/")
    public String Homepage() {
        return "Please set the URL like \"http://localhost:8080/bag/set\"  \"bag\" is the first word, \"set\" is the second word.";
    }

    @RequestMapping(value="/{w1}/{w2}",method= RequestMethod.GET)
    public static String createString(@PathVariable String w1, @PathVariable String w2)throws IOException {
        Set<String>dictionary=new HashSet<String>();
        String tename=new String();

        String result=new String();
        //String w1=new String();
        //String w2=new String();

        String dict=new String();
        Vector<String>wordlist=new Vector<String>();

        String name=new String();
        //while (true) {
            //System.out.println("Dictionary file name?");
            //Scanner sc = new Scanner(System.in);
            tename = "dictionary.txt";
            File dic = new File(tename);
            //dic.open(tename, ios::in);
            //if (dic.exists()) {
             //   break;
           // }
            //dic.clear();
            //System.out.println("Unable to open that file.Try again.");

       // }
        name = tename;

        File fr = new File(name);
        BufferedReader diction = new BufferedReader(new FileReader(fr));
        //if (true) {
        dict=diction.readLine();
        while (dict!=null)
        {
            wordlist.add(dict);
            dict=diction.readLine();
        }
        Vector<String>alpha=new Vector<String>();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";
        String f = "f";
        String g = "g";
        String h = "h";
        String i = "i";
        String j = "j";
        String k = "k";
        String l = "l";
        String m = "m";
        String n = "n";
        String o = "o";
        String p = "p";
        String q = "q";
        String r = "r";
        String s = "s";
        String t = "t";
        String u = "u";
        String v = "v";
        String w = "w";
        String x = "x";
        String y = "y";
        String z = "z";
        alpha.add(a); alpha.add(b); alpha.add(c); alpha.add(d); alpha.add(e);
        alpha.add(f); alpha.add(g); alpha.add(h); alpha.add(i); alpha.add(j);
        alpha.add(k); alpha.add(l); alpha.add(m); alpha.add(n); alpha.add(o);
        alpha.add(p); alpha.add(q); alpha.add(r); alpha.add(s); alpha.add(t);
        alpha.add(u); alpha.add(v); alpha.add(w); alpha.add(x); alpha.add(y);
        alpha.add(z);
        while (true) {
            //Scanner sc1 = new Scanner(System.in);
           // System.out.println("Word #1 (or Enter to quit):");
            //Scanner sc1 = new Scanner(System.in);
            //w1 = sc1.nextLine();
            w1=w1.toLowerCase();

            if (!w1.equals("")) {
                //Scanner sc2 = new Scanner(System.in);
                //System.out.println("Word #2 (or Enter to quit):");
                //w2 = sc2.nextLine();
                w2=w2.toLowerCase();
                if (w2.equals("")) {
                    result="have a nice day.";
                    return result;
                }
            }
            else {
                result="have a nice day.";
                return result;

            }
            if(!w1.equals(w2)&&w1.length()==w2.length()) {
                int length = w1.length();
                for (int cont = 0; cont < wordlist.size(); cont++)
                {
                    if (wordlist.elementAt(cont).length() == length) {
                        dictionary.add(wordlist.elementAt(cont));
                    }
                }


                if (dictionary.contains(w1) && dictionary.contains(w2))
                {
                    int mark=0;
                    String res = new String();
                    Stack<String>word=new Stack<String>();
                    word.push(w1);
                    Queue<Stack<String>>woladder=new LinkedList<Stack<String>>();
                    woladder.offer(word);
                    int size = woladder.size();
                    res=Ladder(woladder, dictionary,  w2, alpha, mark);
                    if (res.equals("")) {
                        result="No word ladder found from" + " "+w2 + " "+"back to"+" "+ w1+".";
                        return result;
                    }
                    else{return res;}

                }
                else {
                    result="The two words must be found in the dictionary.";
                    return result;

                }
                //cout<<endl;

            }
            else if(w1.equals(w2)) {
                result="The two words must be different.";
                return result;

            }
            else if (w1.length() != w2.length()) {
                result="The two words must be the same length.";
                return result;
            }


        }

        //}

        //return 0;
    }


    public static void main( String[] args )
	{
		SpringApplication.run(Wordladder.class, args);
		//SpringApplication.run(UserController.class, args);
	}
}


