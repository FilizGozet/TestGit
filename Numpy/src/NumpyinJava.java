/*
 * Filiz GOZET
 * 20.10.2018 tarihinde yapilmistir.
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.metadata.IIOMetadataFormat;


/**
 *
 * @author ersin
 */
public class NumpyinJava {
    
    
    // þu türlerde tanýmlanabilmeli
    // [1,2,3]
    // [[1,2],[2,3]]
    // [[1,[2,2],3]]
    
	public static void main(String args[]) {	
		
		
		Object[] a = { 10, 20, 30 };
		Object[] b = { new int[]{1,2}, new int[]{2,3} };
		Object[] c = { 1, new int[]{1,2}, 3 };
		Object[] d = { 5, 15, 20 };
		Object[] e = { new int[]{2,8,0,6}, new int[]{4,5,1,1},new int[]{8,9,3,6},new int[]{6,1,1,1} };
		
		

		
		System.out.print("create    ");  print(create(b));                System.out.println();
		System.out.print("zeros     ");  print(create(zeros(b)));         System.out.println();
		System.out.print("ones      ");  print(create(ones(b)));          System.out.println();
		System.out.print("arange    ");  print(create(arange(0,15,2)));   System.out.println();
		System.out.print("linspace  ");  print(create(linspace(0,10,2))); System.out.println();
		System.out.print("sub       ");  print(create(sub(a,d)));         System.out.println();
		System.out.print("add       ");  print(create(add(a,d)));         System.out.println();
		System.out.print("mul       ");  print(create(mul(a,d)));         System.out.println();
		System.out.print("div       ");  print(create(div(a,d)));         System.out.println();
		System.out.print("ravel     ");  print(create(e)); System.out.print("-->"); print(create(ravel(e)));     System.out.println();
		System.out.print("shape     ");  print(create(b)); System.out.print("-->"); System.out.println(shape(b));
		System.out.print("resize    ");  print(create(e)); System.out.print("-->"); print(create(resize(e,8))); System.out.println();
		
	}
	
	
	
	//olusturulcak matrisin boyutlarinin olabilecegi degerler //list kullandim affedin ^^
	public static List divisor(int a) {
		
		List list = new ArrayList();
		
		for(int x=1 ; x<=a ; x++) {
			if(a % x == 0) {
				list.add(x);
			}
		}
		return list;
	}
	
	
	// [[1,[2,2],3]] --> bu turlerde tanimmlanmali demissiniz ama o zaman diger fonksiyonlarin kullaniminda 
	//problem cikariyor o yuzden onu da engelledim
	//yarattigimiz arrayin kontrolunu yapar
	public static boolean checkArray(Object[] a) {
		
		int x=0, y=0, z=0 ;	
		boolean bol = false;
		for(Object t : a){
			if(t instanceof int[]) {
				x++;
			}else if(t instanceof Integer) {
				y++;
			}else if(t instanceof Double) {
				z++;
			}
		}
		//matrisle ifadenin uygun olup olmadigini kontrol ettim
		if(x!=0 && y!=0 && z!=0) {
			throw new RuntimeException("Matris yaratmaya uygun degil.");
		}else if(x!=0 && y!=0) {
			throw new RuntimeException("Matris yaratmaya uygun degil.");
		}else if(y!=0 && z!=0) {
			throw new RuntimeException("Matris yaratmaya uygun degil.");
		}else if(x!=0 && z!=0) {
			throw new RuntimeException("Matris yaratmaya uygun degil.");
		}else if(x!=0) {
			bol = true;
		}else if(y!=0){
			bol = true;
		}else if(z!=0){
			bol = true;
		}
					
		return bol;
	}

	
	public static Object[] create(Object[] array) {
		Object[] n = new Object[array.length];
		
		if(checkArray(array)) {
			for(int x=0 ; x<array.length ; x++){
				 if(array[x] instanceof int[]) {
						int[] a = (int[])array[x];
						n[x] = Arrays.toString(a);
					}else {
						n[x] = array[x];
					}
			 }
		}
		return n;
    }
    
    // [1,2] ->  [0,0]
    public static Object[] zeros(Object[] shape) {
    	
    	for(int k=0 ; k<shape.length ; k++) {
			if(shape[k] instanceof int[]) {
				int[] array = (int[]) shape[k];
				for(int j=0 ; j<array.length ; j++) {
					array[j] = 0;
				}
			}else {
				shape[k] = 0;
			}
		}
    	
    	return shape;
    }

     // [1,2] ->  [1,1]
    public static Object[] ones(Object[] shape) {
        
    	for(int k=0 ; k<shape.length ; k++) {
			if(shape[k] instanceof int[]) {
				int[] array = (int[]) shape[k];
				for(int j=0 ; j<array.length ; j++) {
					array[j] = 1;
				}
			}else {
				shape[k] = 1;
			}
		}
    	
    	return shape;
    }

    // 0,10,2 ->  [0,2,4,6,8,10]
    public static Object[] arange(double s, double e, double inc) {
        
    	double num = (e - s) / inc + 1; //arrayin eleman sayisi
    	int x = 0;
    	
    	Object[] array = new Object[(int) num];
    	
		for(double i=s ; i<=e ; i+=inc) {
			array[x] = i;
			x++;
		}

		return array;
    }

    // 0,10,2 ->  [0,  10]
    public static Object[] linspace(double s, double e, double inc) {
    	
    	double diff = e - s;
		double cm = diff / (double)(inc - 1);
		
		Object[] array = new Object[(int) inc];
		
		for(int i=0 ; i<inc ; i++) {
			String x = String.format("%.2f", s + cm*i).replace(",", ".");
			array[i] = Double.parseDouble(x);
		}
		
		return array;
    	
    }
    
    public static void print(Object[] array) {
    	System.out.print("[ ");
		for(Object ak : array) {
			System.out.print(ak.toString() + " ");
		}
		System.out.print("]");
    }
    
    /////////////////////////////////////////////////////////
    
    //[10,20,30] - [5, 15, 20] -> [5,5,10]
     public static Object[] sub(Object[] a, Object[] b) {
        
    	 Object[] sonuc = new Object[a.length];
    	 
     	if(a.length == b.length) { 		
     		for(int i=0 ; i<a.length ; i++) {
        		 sonuc[i] = (int)a[i] - (int)b[i];
        		 }
     		}else {
     			throw new RuntimeException("Illegal dimensions");
     		}
      	 
     	 return sonuc;
    }
     
     //[10,20,30] + [5, 15, 20] -> [15,35,50]
     public static Object[] add(Object[] a, Object[] b) {
        
    	 Object[] sonuc = new Object[a.length];
    	 
      	if(a.length == b.length) { 		
      		for(int i=0 ; i<a.length ; i++) {
         		 sonuc[i] = (int)a[i] + (int)b[i];
         		 }
      		}else {
      			throw new RuntimeException("Illegal dimensions");
      		}
       	 
      	 return sonuc;
    }
     
     //[2,4,6] * [2,2,2] -> [4,8,12]
     public static Object[] mul(Object[] a, Object[] b) {
        
    	 Object[] sonuc = new Object[a.length];
    	 
       	if(a.length == b.length) { 		
       		for(int i=0 ; i<a.length ; i++) {
          		 sonuc[i] = (int)a[i] * (int)b[i];
          		 }
       		}else {
       			throw new RuntimeException("Illegal dimensions");
       		}
        	 
       	 return sonuc; 
  
    }
        
      //  [4,8,12] /   [2,2,2] -> [2,4,6]
     public static Object[] div(Object[] a, Object[] b) {
        
    	 Object[] sonuc = new Object[a.length];
    	 
        	if(a.length == b.length) { 		
        		for(int i=0 ; i<a.length ; i++) {
           		 sonuc[i] = (int)a[i] / (int)b[i];
           		 }
        		}else {
        			throw new RuntimeException("Illegal dimensions");
        		}
         	 
        	 return sonuc; 
    	 
    }
     /////////////////////////////////////////////////////////
          
     public static int sizeofArray(Object[] a) {
    	 return create(ravel(a)).length;
     }
         
     public static <T> T[] append(T[] arr, T element) {
    	    final int N = arr.length;
    	    arr = Arrays.copyOf(arr, N + 1);
    	    arr[N] = element;
    	    return arr;
    	}
   
     // [[1,2],3,[4,5,6]] -> [1,2,3,4,5,6]
     public static Object[] ravel(Object[] a) {
    	 
    	 Object[] al = new Object[0];

 		for(int k=0 ; k<a.length ; k++) {
 			if(a[k] instanceof int[]) {
 				int[] array = (int[]) a[k];
 				for(int j=0 ; j<array.length ; j++) {
 					al = append(al,array[j]);
 				}
 			}else {
 				al = append(al,a[k]);
 			}
 		}
 		return al;
    }
     
     public static String shape(Object[] a){
    	 
    	int[] al = new int[2];
   		
   		int row = a.length;
   		int col = 0;
   		al[0] = row;
   		
   		for(int i=0 ; i<a.length ; i++) {
   			if(a[i] instanceof int[]) {
   				int[] array = (int[]) a[i];
   				col = array.length;
   			}
   		}		
   		al[1] = col;
   		return Arrays.toString(al).replace("[", "(").replace("]", ")");
     }
    
     public static Object[] resize(Object[] a, int b){
    	 
    	 int array = sizeofArray(a) / b;		 
    	 int[] integerarr = new int[sizeofArray(a)];
    	 Object[] j = new Object[array];
    	 
    	 //elimizdeki matrisi kaclik yazabiliriz?
    	 if(divisor(sizeofArray(a)).contains(b)) {
    		 
        	 //Object arrayi int arraye donusturdum
     		  for(int i=0 ; i<sizeofArray(a) ; i++) {
     			integerarr[i] = (int) ravel(a)[i];
     		  }
    	 		  
     		  for(int i=0 ; i<array ; i++) {
     			  j[i] = Arrays.copyOfRange(integerarr, i*b, i*b+b);
     		  }   		  
    	 }else {
    	  throw new RuntimeException("Elimizdeki matristen bu boyularda matrix olusturulamaz !");
      }
    	 return j;
     }
}
    	