//package cs445proj2;

public class Assig2B {
	
	public static void main(String[] args) {
		
		//TEST1
		
		StringBuilder class1 = new StringBuilder();
		MyStringBuilder class2 = new MyStringBuilder();
		String class3 = new String();
		
		System.out.println("Test 1: Append");
		
		long start = System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class1.append("a");
		}
		
		long stop = System.nanoTime();
		System.out.println("StringBuilder:");
		System.out.println("Total time: " + (stop - start) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop - start) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		long start2 = System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class2.append("a");
		}
		
		long stop2 = System.nanoTime();
		
		System.out.println("MyStringBuilder:");
		System.out.println("Total time: " + (stop2 - start2) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop2 - start2) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		long start3 = System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class3 += "a";
		}
		
		long stop3 = System.nanoTime();
		System.out.println("String:");
		System.out.println("Total time: " + (stop3 - start3) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop3 - start3) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		//TEST 2
		
		System.out.println("Test 2: Delete");
		
		long start4 = System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class1.delete(0, 1);
		}
		
		long stop4 = System.nanoTime();
		System.out.println("StringBuilder:");
		System.out.println("Total time: " + (stop4 - start4) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop4 - start4) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		long start5 = System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class2.delete(0, 1);
		}
		
		long stop5 = System.nanoTime();
		System.out.println("MyStringBuilder:");
		System.out.println("Total time: " + (stop5 - start5) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop5 - start5) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		long start6= System.nanoTime();
		
		for(int i = 0; i < Integer.parseInt(args[0]); i++) {
			class3.substring(1);
		}
		
		long stop6 = System.nanoTime();
		System.out.println("String:");
		System.out.println("Total time: " + (stop6 - start6) + " nanoseconds");
		System.out.println("Total time per operation: " + ((stop6 - start6) / Integer.parseInt(args[0])) + " nanoseconds\n");
		
		//TEST 3
		
				System.out.println("Test 3: Insert");
				
				long start7 = System.nanoTime();
				
				for(int i = 0; i < Integer.parseInt(args[0]); i++) {
					class1.insert((class1.length()/2), "aa");
					
				}
				
				long stop7 = System.nanoTime();
				System.out.println("StringBuilder:");
				System.out.println("Total time: " + (stop7 - start7) + " nanoseconds");
				System.out.println("Total time per operation: " + ((stop7 - start7) / Integer.parseInt(args[0])) + " nanoseconds\n");
				
				long start8 = System.nanoTime();
				
				for(int i = 0; i < Integer.parseInt(args[0]); i++) {
					class2.insert((class2.length()/2), "aa");
				}
				
				long stop8 = System.nanoTime();
				System.out.println("MyStringBuilder:");
				System.out.println("Total time: " + (stop8 - start8) + " nanoseconds");
				System.out.println("Total time per operation: " + ((stop8 - start8) / Integer.parseInt(args[0])) + " nanoseconds\n");
				
				long start9 = System.nanoTime();
				
				for(int i = 0; i < Integer.parseInt(args[0]); i++) {
					
					class3 = class3.substring(0, class3.length()/2) + "aa" + class3.substring(class3.length()/2, class3.length());
				}
				
				long stop9 = System.nanoTime();
				System.out.println("String:");
				System.out.println("Total time: " + (stop9 - start9) + " nanoseconds");
				System.out.println("Total time per operation: " + ((stop9 - start9) / Integer.parseInt(args[0])) + " nanoseconds\n");
	}
}
