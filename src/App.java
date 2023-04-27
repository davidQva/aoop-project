public class App {
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Jahaja har kan man ha kul!");
        System.out.println();
        
        int k = 10;
        
        for (int i = 0; i < k; i++) 
        {
        	if(i < k/2)
        		System.out.print(i + "\t");
        	else if(i == k/2) 
        	{
        		System.out.println();
        		System.out.print (i + "\t");
        	}
        	else if(i > k/2)
        		System.out.print(i + "\t");
		}
        System.out.println("\n");
        System.out.println("tada!~~");
        //Nu la jag till denna kommentar bara för att se om den hänger med pushhhhhen
    }
}
