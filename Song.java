//Ling Zheng
//10/05/2015
//CSE143X 
//HW #1
//print a cumulative song by only using static methods and println
   
public class Song {

    public static void main(String[] args){
       // print fly verse
       System.out.println("There was an old woman who swallowed a fly.");  
       flyend();  
       // print spider verse
       System.out.println("There was an old woman who swallowed a spider,");
       System.out.println("That wriggled and iggled and jiggled insider her.");
       spiderend();
       // print bird verse
       System.out.println("There was an old woman who swallowed a bird,");
       System.out.println("How absurd to swallow a bird.");
       birdend();
       // print cat verse
       System.out.println("There was an old woman who swallowed a cat,");
       System.out.println("Imagine that to swallow a cat.");
       catend();
       // print dog verse
       System.out.println("There was an old woman who swallowed a dog,");
       System.out.println("What a hog to swallow a dog.");
       dogend();
       // print wolf verse
       System.out.println("There was an old woman who swallowed a wolf,");
       System.out.println("A wolf inside must be tough.");
       wolfend();
       // print the ending verse
       System.out.println("There was an old woman who swallowed a horse,");
       System.out.println("She died of course.");    
    
    }
    // the end of fly verse
    public static void flyend() {
       System.out.println("I don't know why she swallowed that fly,");  
       System.out.println("Perhaps she'll die.");
       System.out.println();
    }
    // the end of spider verse
    public static void spiderend(){
       System.out.println("She swallowed the spider to catch the fly,");
       flyend();  
    }
    // the end of bird verse
    public static void birdend() {
       System.out.println("She swallowed the bird to catch the spider,");
       spiderend();
    }
    // the end of cat verse 
    public static void catend() {
       System.out.println("She swallowed the cat to catch the bird,");
       birdend();
    }
    // the end of dog verse
    public static void dogend() {
       System.out.println("She swallowed the dog to catch the cat,");
       catend();
    }
    // the end of wolf verse
    public static void wolfend(){
       System.out.println("She swallowed the wolf to catch the dog,");
       dogend();
    }
    
    
}