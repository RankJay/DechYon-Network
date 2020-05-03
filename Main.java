import java.util.*; 
import java.io.*;
import java.security.*; 

interface accessBlockchain {
    String hashValue = ""; 
    String previousHashValue = "";
    String accessHashValue();
}

class Block implements accessBlockchain { 
    String hashValue; 
    String previousHashValue; 
    private String data; 
    private long timeFootPrint; 
    
    protected Block(String data, String previousHashValue) { 
        this.data = data; 
        this.previousHashValue = previousHashValue; 
        this.timeFootPrint = new Date().getTime(); 
        this.hashValue = generateHashValue();
        System.out.println("Data: " + this.data);
        System.out.println("Generated Hash-Value is: " + this.hashValue);
    } 

    private String generateHashValue() { 
        String HashValue = "";
        HashValue = EncryptionOfHash.encryptionMechanism(previousHashValue + Long.toString(timeFootPrint) + data); 
        return HashValue; 
    } 
    
    public String accessHashValue() { 
        String Value = "";
        Value = this.hashValue; 
        return Value; 
    } 
} 

class EncryptionOfHash { 
    public static String encryptionMechanism(String blockEncodedDetails) { 
        try { 
            int pointer = 0;
            StringBuffer hexadecimalHashValue = new StringBuffer();

            MessageDigest encryptionAlgorithm = MessageDigest.getInstance("MD5"); 
            byte[] hash = encryptionAlgorithm.digest(blockEncodedDetails.getBytes("UTF-8")); 

            while (pointer < hash.length) { 
                String hex = Integer.toHexString(0xff & hash[pointer]); 
                if (hex.length() == 1) {
                    hexadecimalHashValue.append('0'); 
                }
                hexadecimalHashValue.append(hex); 
                pointer++; 
            } 

            return hexadecimalHashValue.toString(); 
        } 
        catch (Exception e) { 
            throw new RuntimeException(e); 
        } 
    } 
} 

public class Main extends Exception { 

    public static ArrayList<Block> blockchain = new ArrayList<Block>(); 

    public static Boolean Decentralized_Blockchain_Validation() 
    { 
        Block currentBlock; 
        Block previousBlock; 

        System.out.println("Analyzing Blockchain Validation...");
        for (int i=1;i<blockchain.size();i++) { 
            currentBlock = blockchain.get(i); 
            previousBlock = blockchain.get(i-1); 

            if (!currentBlock.hashValue.equals(currentBlock.accessHashValue())) { 
                System.out.println("Hashes are not equal"); 
                return false; 
            } 
            else {
                System.out.println("Hash Value is validated"); 
            }

            if (!previousBlock.hashValue.equals(currentBlock.previousHashValue)) { 
                System.out.println("Previous Hashes are not equal"); 
                return false; 
            } 
            else {
                System.out.println("Previous Hash Value is validated"); 
            }
        }

        System.out.println("Decentralized Blockchain Validation successfully completed !!");
        return true; 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int incr = 1;
        int data;
        System.out.println("Enter the no. of blocks to be produced:");
        data = scanner.nextInt();
        try {
            blockchain.add(new Block("Block no. 0", "0"));
            Thread.sleep(1500);
            while(incr != data) {
                blockchain.add(new Block("Block no. " + incr, blockchain.get(blockchain.size() - 1).hashValue));
                Thread.sleep(1500);
                incr++;
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Blockchain abruptly broken.");
        }
        
        Decentralized_Blockchain_Validation();
    } 
} 