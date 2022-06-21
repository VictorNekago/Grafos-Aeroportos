package aaaa;

import java.io.*;
import java.util.*;
import java.util.Random;

public class Grafos {

	public static void main(String[] args) throws IOException {
		
		Scanner input;
		input=new Scanner(System.in);
		
		int Matriz[][]=new int[500][500];
		int linhaM=0;
		int colunaM=0;
		int pesoM=0;
		int vert1=0,vert2=0,Peso2=0;
		String[] VetString=new String[3];
		
        InputStream is = new FileInputStream("D:\\trabalho de grafos\\Base.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

       try {
        String linha = br.readLine(); // primeira linha

        while (linha != null) {
        	
          //  System.out.println(linha);
        	VetString=linha.split(" ");
        	
            linha = br.readLine();
          //  linha.split(" ");
            Integer.parseInt(VetString[0]);
            vert1= Integer.parseInt(VetString[0]);
            vert2= Integer.parseInt(VetString[1]);
            Peso2= Integer.parseInt(VetString[2]);
            InsereMatriz(Matriz,vert1,vert2,Peso2);
            
           // System.out.println("col1 "+VetString[0]+" col2 "+VetString[1]+" col3 "+VetString[2]);
            
            
        }
        br.close();
        //metodo para imprimir a matriz, funcionando certinho 100%!!!
       // ImprimeMatriz(Matriz);
    }
       catch (Exception ex){
    	   
       }
      
       
       System.out.println("Deseja Realizar o Teste de forma Manual ou Automatica ?");
       System.out.println("Digite (1) para Manual e 2 para Automatica");
       int escolha=input.nextInt();
       
       if (escolha==1) {
    	   Manual(Matriz);
       }
       
       else if(escolha==2) {
    	   Auto(Matriz);
       }
       
       else {
    	   System.out.println("valor incorreto, verifique novamente");
       }
    	   
       
       
  
	}
	
	
	public static void InsereMatriz (int matriz[][],int col,int lin, int peso) {
		matriz[lin][col]=peso;
		matriz[col][lin]=peso;
	
	}
	
	public static void ImprimeMatriz(int matriz[][]) {
		
		System.out.println("MATRIZ---------------------------------------------");
		for (int i=0;i<matriz.length;i++) {
			
			for (int j=0;j<matriz[0].length;j++) {
				System.out.print(" "+matriz[i][j]);
			}
			System.out.print("\n");
			}
		
		
	}
	
	public static void Manual(int matriz[][]) {
		Scanner input;
		input=new Scanner(System.in);
		int continuar=0;
		int escolha =0;
		int MatrizImpossivel[][]=new int[500][500];
		int MatrizPossivel[][]=new int[500][500];
			while(continuar!=1) {
		   System.out.println("onde você esta?");
	       int um =input.nextInt();
	       System.out.println("pra onde você vai?");
	       int dois =input.nextInt();
	       
	       if (matriz[um][dois]!=0) {
	           System.out.println("há voo disponivel");
	           System.out.println("Lugares disponiveis = "+matriz[um][dois]);
	           System.out.println("deseja comprar? (1) para sim, (2) para não");
	           int compra =input.nextInt();
	           
	           if (compra == 1) {
	        	   System.out.println("compra realizada com sucesso");
	        	   matriz[um][dois]=matriz[um][dois]-1;
	        	   matriz[dois][um]=matriz[um][dois]-1;
	        	   System.out.println("vagas disponiveis agora "+matriz[um][dois]);
	        	   if (matriz[um][dois]==0) {
	        		   //Relatorio de compras de passagens QUE NAO FORAM POSSIVEIS
	        		 MatrizImpossivel[um][dois]=MatrizImpossivel[um][dois]+1; 		   
	        		   
	        	   }
	        	   else if (matriz[um][dois]!=0) {
	        		   //Relatorio de compras de passagens QUE FORAM POSSIVEIS
	        		   MatrizPossivel[um][dois]=MatrizPossivel[um][dois]+1;
	        	   }
	        	   
	           }
	           else {
	        	   
	           }
	       }
	       else {
	           System.out.println("não ha voo disponivel para esta localidade");
	       }
	       
	       System.out.println("deseja comprar mais passagens? (1) para sim (2) para não ");
	      escolha = input.nextInt();
	       
	      
	      
	      
	       if (escolha==2){
	    	  continuar=1; 
	       }
	       else {
	    	   
	       }
	       
			} 
	}

	public static void Auto(int matriz[][]) throws IOException {

		
		Random random = new Random();
		
		int numero1 = 0;
		int numero2 = 0;
		int numero3 = 0;
		int MatrizImpossivel[][]=new int[500][500];
		int MatrizPossivel[][]=new int[500][500];
		
		int numeroPassagens= random.nextInt(10000);//quantas passagens ele vai comprar no total
		
		
		for (int contador=0;contador<=numeroPassagens;contador++) {
			
			numero1 = random.nextInt(500);
			numero2 = random.nextInt(500);
			numero3 = random.nextInt(500);
			
		if (matriz[numero1][numero2]==0) {
 		   //Relatorio de compras de passagens QUE NAO FORAM POSSIVEIS
 		 MatrizImpossivel[numero1][numero2]=numero3; 		   
 		   
 	   }
		 else if (matriz[numero1][numero2]!=0 && numero3<=matriz[numero1][numero2]) {
			 //realizando a compra na matriz original
			 matriz[numero1][numero2]= matriz[numero1][numero2]-numero3;
			 matriz[numero2][numero1]=matriz[numero2][numero1]-numero3;
			 
  		   //Relatorio de compras de passagens QUE FORAM POSSIVEIS
  		   MatrizPossivel[numero1][numero2]=numero3;
  	   }
		}
		
		//ImprimeMatriz(MatrizPossivel);
		System.out.println("passagens compradas = "+numeroPassagens);
		CriaArquivo(MatrizImpossivel,"Matriz Impossivel");
		CriaArquivo(MatrizPossivel,"Matriz Possivel");
		CriaArquivo(matriz,"Matriz Final");
		
		//dps cria uma visualição da matriz aqui so de queques
		
	}
	
	public static void CriaArquivo(int matriz[][],String NomeArquivo) throws IOException{
		
        BufferedWriter br = new BufferedWriter(new FileWriter(NomeArquivo));
        int contador=1;
        for (int i=0;i<matriz.length;i++) {
        	
        	for (int j=0;j<matriz.length;j++) {
        		
        		if(i<j && matriz[i][j]!=0) {
        			//escrita do arquivo
        			
        			 br.write(Integer.toString(i));
        			 br.write(" ");
        			 br.write(Integer.toString(j));
        			 br.write(" "); 
        			 br.write(Integer.toString(matriz[i][j]));
        			 br.newLine();
        			 br.flush();
        			
        		}
        	}
        	
        	
        	
        	
        }
      
	}
	
	
}
