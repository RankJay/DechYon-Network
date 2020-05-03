import java.util.*; 
import java.io.*;
import java.security.*; 

interface accessBlockchain {
    String hashValue = ""; 
    String previousHashValue = "";
    String accessHashValue();
}

class NegativeGasNotAllowed extends Exception {
    public NegativeGasNotAllowed() {
        System.out.println();
    }
}
class NotEnoughGasAvailable extends Exception {
    public NotEnoughGasAvailable() {
        System.out.println();
    }
}
class SelfLoopedTransaction extends Exception {
    public SelfLoopedTransaction() {
        System.out.println();
    }
}



class Block implements accessBlockchain { 
    protected String hashValue; 
    protected String previousHashValue; 
    protected String data; 
    public String accessData;
    private long timeFootPrint; 
    
    protected Block(String data, String previousHashValue) { 
        this.data = data; 
        this.accessData = this.data;
        this.previousHashValue = previousHashValue; 
        this.timeFootPrint = new Date().getTime(); 
        this.hashValue = generateHashValue();
        //System.out.println("Data: " + this.data);
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
    protected static String encryptionMechanism(String blockEncodedDetails) { 
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

    private static ArrayList<Block> blockchain = new ArrayList<Block>(); 
    static Scanner scanner = new Scanner(System.in);
    
    private static void Transaction() {
        System.out.println("\nYou have a total amount of " + blockchain.get(0).accessData + " Gas left.");
        System.out.println("Your ID is: " + blockchain.get(0).hashValue);
        
        System.out.print("\nClient ID : ");
        String ClientID = scanner.next();
        System.out.print("Amount to be transfered: ");
        int amount = scanner.nextInt();
        try {
            if(amount<=Integer.parseInt(blockchain.get(0).accessData)) {
                if(amount<0) {
                    throw new NegativeGasNotAllowed();
                }
                if(ClientID==blockchain.get(0).hashValue) {
                    throw new SelfLoopedTransaction();
                }
                for(int j=0;j<blockchain.size();j++) {
                    if (blockchain.get(j).hashValue.equals(ClientID)) {
                        blockchain.get(0).data = String.valueOf(Integer.parseInt(blockchain.get(0).accessData) - amount);
                        blockchain.get(0).accessData = blockchain.get(0).data;
                        blockchain.get(j).data = String.valueOf(Integer.parseInt(blockchain.get(j).accessData) + amount);
                        blockchain.get(j).accessData = blockchain.get(j).data;
                        
                        System.out.println("\n" + blockchain.get(j).data + " Gas sent to account, having Client ID: " + blockchain.get(j).hashValue);
                        System.out.println("You are left with " + blockchain.get(0).data + " Gas");
                        break;
                    }
                }
            }
            else {
                throw new NotEnoughGasAvailable();
            }
        }
        
        catch(SelfLoopedTransaction ex) {
            System.out.println("You cannot process a Transaction to your own ID, that will led to demolishing the transaction fees.");
        }
        catch(NotEnoughGasAvailable ex) {
            System.out.println("You don't have enough Gas to process the transaction.");
        }
        catch(NegativeGasNotAllowed ex) {
            System.out.println("You cannot process negatively signed Gas Amount in the transaction.");
        }
    }

    private static Boolean Decentralized_Blockchain_Validation() 
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
        
        System.out.println("Blockchain is being produced at the rate of 1 block per 1.5 seconds:");
        try {
            blockchain.add(new Block("10000", "0"));
            Thread.sleep(1500);
            while(incr != data) {
                blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).hashValue));
                Thread.sleep(1500);
                incr++;
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Blockchain abruptly broken.");
        }
        System.out.println("Blockchain is produced...");
        
        //Decentralized_Blockchain_Validation();
        
        Transaction();
    } 
} 
