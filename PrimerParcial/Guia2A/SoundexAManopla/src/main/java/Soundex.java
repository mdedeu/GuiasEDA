import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Arrays;

public class Soundex {
    int[] abecedario= new int[26];

    public static void main(String[] args) {
        Soundex soundex = new Soundex();
        System.out.println(soundex.similarity("soltar","sotlar"));


    }
    Soundex(){
        for(int i=0; i<26;i++){
            // A,E,H,I,O,U,W,Y
            if(i==0 || i==4 || i==8 || i==7 || i==14 || i==20 || i== 24 || i==22)
                this.abecedario[i]=0;
            //b,f,p,v
            if(i==1 || i==5 || i== 15 || i==21)
                this.abecedario[i]=1;
            //c,g,j,k,q,s,x,z
            if(i==2 || i==6 || i==9 || i==10 || i==16|| i==18 || i==23 || i==25)
                this.abecedario[i]=2;
            //d,t
            if(i==3 || i==19)
                this.abecedario[i]=3;
            //l
            if(i==11)
                this.abecedario[i]=4;
            //m,n
            if(i==12 || i==13)
                this.abecedario[i]=5;
            //r
            if(i==17)
                this.abecedario[i]=6;

        }
    }

    public String encode(String input){
        input = input.toUpperCase();
        char[] in = input.toCharArray();
        char[] out = {'0','0','0','0'};
        out[0]=in[0];
        int cnt=1;
        char current;
        char last= getMapping(in[0]);
        for(int i=1; i< in.length && cnt < 4;i++,last=current){
            char iter = in[i];
            current=getMapping(iter);
            if(current!='0' && current!=last){
                out[cnt++]=current;
            }
        }
        return new String(out);
    }

    private char getMapping(char a){
        int b= a-'A';
        return (char)(abecedario[b] + '0');
    }
    public double similarity(String s1, String s2){

        String string1encoded= encode(s1);
        String string2encoded= encode(s2);
        char[] string1= string1encoded.toCharArray();
        char[] string2= string2encoded.toCharArray();
        int eql=0;
        double lng = 4.0;
        for(int i=0; i < string1.length; i++){
            if(string1[i]==string2[i])
                eql++;
        }
        return eql/lng;
    }
}
