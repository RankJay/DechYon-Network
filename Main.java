import java.util.*;
import sun.security.krb5.EncryptionKey;
import java.io.*;
import java.security.*; 

interface accessBlockchain {
    String accessData = "";
    String accessHashValue = ""; 
    String accessPreviousHashValue = "";
    String accessHashValue();
    String accessPreviousHashValue();
    double TransactionFee = 0;
}

class NegativeGasNotAllowed extends Exception {
    private static final long serialVersionUID = 1L;

    public NegativeGasNotAllowed() {
        System.out.println("\nYou cannot process negatively signed Gas Amount in the transaction.");
    }
}
class NotEnoughGasAvailable extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughGasAvailable() {
        System.out.println("\nYou don't have enough Gas to process the transaction.");
    }
}
class SelfLoopedTransaction extends Exception {
    private static final long serialVersionUID = 1L;

    public SelfLoopedTransaction() {
        System.out.println("\nYou cannot process a Transaction to your own ID, that will led to demolishing the transaction fees.");
    }
}



class Block implements accessBlockchain { 
    private String hashValue; 
    public String accessHashValue = ""; 
    private String previousHashValue; 
    public String accessPreviousHashValue = "";
    protected String data; 
    public String accessData;
    private long timeFootPrint; 
    double TransactionFee;
    
    protected Block(String data, String previousHashValue) { 
        this.data = data; 
        this.accessData = this.data;
        this.previousHashValue = previousHashValue;  
        this.accessPreviousHashValue = this.previousHashValue;
        this.timeFootPrint = new Date().getTime(); 
        this.hashValue = generateHashValue();
        this.accessHashValue = this.hashValue;
        this.TransactionFee = 0.01*Integer.parseInt(this.data);
        //System.out.println("Data: " + this.data);
        System.out.println("Generated Hash-Value is: " + this.hashValue + "\n");
    } 

    private String generateHashValue() { 
        String HashValue = "";
        Blockchain accessBlockchain = new Blockchain();
        Blockchain.ProductionGradle GenerateHashValue = accessBlockchain.new ProductionGradle();
        HashValue = GenerateHashValue.EncryptionMechanism(previousHashValue + Long.toString(timeFootPrint) + data); 
        return HashValue; 
    } 
    
    public String accessHashValue() { 
        return this.accessHashValue;
    } 

    public String accessPreviousHashValue() { 
        return this.accessPreviousHashValue;
    } 
} 

class Blockchain { 
    private static ArrayList<Block> blockchain = new ArrayList<Block>();
    private static List<String> NewTransactionAddToHistory = new LinkedList<String>();
    private static Scanner scanner = new Scanner(System.in);

    /*
    public static void BlockchainEvaluation() {
        for(int i=0;i<blockchain.size();i++) {
            System.out.println(blockchain.get(i).data);
        }
    }
    */

    class ProductionGradle {

        protected void accessGenesisChamber() {
            ProductionGradle accessGradle = new ProductionGradle();
            accessGradle.GenesisChamber();
        }

        private void GenesisChamber() {
            Scanner scanner = new Scanner(System.in);
            int counter = 1;
            int NoOfBlock;

            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            
            System.out.println("Enter the no. of blocks to be produced:");
            NoOfBlock = scanner.nextInt();
            
            System.out.println("Blockchain is being produced at the rate of 1 block per 1.5 seconds:");
            try {
                blockchain.add(new Block("10000", "0"));
                Thread.sleep(1500);
                while(counter != NoOfBlock) {
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));
                    //accessIntegrations.Decentralized_Blockchain_Validation();
                    Thread.sleep(1500);
                    counter++;
                }
            }
            catch (InterruptedException ex) {
                System.out.println("Blockchain abruptly broken.");
            }

            //scanner.close();
            System.out.println("Blockchain is produced...\n");
        }
        protected String EncryptionMechanism(String blockEncodedDetails) { 
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
    
    static class IntegrationsHouse {

        //private List<String> NewTransactionAddToHistory = new LinkedList<String>();
        static IntegrationsHouse accessHistory = new IntegrationsHouse();

        protected void accessTransaction() {
            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            accessIntegrations.Transaction();
        }
        protected void accessDecentralized_Blockchain_Validation() {
            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            accessIntegrations.Decentralized_Blockchain_Validation();
        }

        private static void Evaluation() {
            for (int i=0;i<NewTransactionAddToHistory.size();i++) {
                System.out.println((i+1) + ":" + NewTransactionAddToHistory.get(i));
            }
        }

        class MerkleTrees {
            private List<String> TransactionsHistory;

            public MerkleTrees(List<String> TransactionsHistory) {
                this.TransactionsHistory = TransactionsHistory;
            }
            
            public List<String> GetMerkleRoot() {
                return construct(this.TransactionsHistory);
            }

            private List<String> construct(List<String> TransactionsHistory) {
                if(TransactionsHistory.size()==1) {
                    return TransactionsHistory;
                }

                List<String> updatedList = new ArrayList<>();

                for(int i=0;i<TransactionsHistory.size()-1;i+=2) {
                    updatedList.add(MergeHashValue(TransactionsHistory.get(i), TransactionsHistory.get(i+1)));
                }

                if(TransactionsHistory.size()%2==1) {
                    updatedList.add(MergeHashValue(TransactionsHistory.get(TransactionsHistory.size()-1), TransactionsHistory.get(TransactionsHistory.size()-1)));
                }
                
                return construct(updatedList);
            }

            private String MergeHashValue(String hash1, String hash2) {
                String mergedHash = hash1 + hash2;

                Blockchain accessBlockchain = new Blockchain();
                Blockchain.ProductionGradle accessEncryption = accessBlockchain.new ProductionGradle();
                return accessEncryption.EncryptionMechanism(mergedHash);
            }

            
        }

        private Boolean Transaction() {
            System.out.println("\nYou have a total amount of " + blockchain.get(0).accessData + " Gas left.");
            System.out.println("Your ID is: " + blockchain.get(0).accessHashValue);
            
            System.out.print("\nClient ID : ");
            String ClientID = scanner.next();
            System.out.print("Amount to be transfered: ");
            double amount = scanner.nextDouble();
            double TransactionFee = 0.01*Double.parseDouble(String.valueOf(amount));

            //List<String> NewTransactionAddToHistory = new LinkedList<>();

            try {
                if(amount<=Double.parseDouble(blockchain.get(0).accessData)) {
                    if(amount<0) {
                        throw new NegativeGasNotAllowed();
                    }
                    if(ClientID==blockchain.get(0).accessHashValue) {
                        throw new SelfLoopedTransaction();
                    }
                    for(int j=0;j<blockchain.size();j++) {
                        if (blockchain.get(j).accessHashValue.equals(ClientID)) {
                            long timeFootPrint = new Date().getTime();

                            blockchain.get(0).data = String.valueOf(Double.parseDouble(blockchain.get(0).accessData) - amount - TransactionFee);
                            blockchain.get(0).accessData = blockchain.get(0).data;
                            blockchain.get(j).data = String.valueOf(Double.parseDouble(blockchain.get(j).accessData) + amount);
                            blockchain.get(j).accessData = blockchain.get(j).data;
                            
                            System.out.println("\n" + blockchain.get(j).data + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                            System.out.println("You are left with " + blockchain.get(0).data + " Gas");

                            NewTransactionAddToHistory.add(amount + blockchain.get(0).accessHashValue + ClientID + timeFootPrint);

                            return true;
                        }
                    }
                    blockchain.get(0).data = String.valueOf(Double.parseDouble(blockchain.get(0).accessData) - TransactionFee);
                    blockchain.get(0).accessData = blockchain.get(0).data;

                    long selfTimeFootPrint = new Date().getTime();
                    NewTransactionAddToHistory.add(TransactionFee + blockchain.get(0).accessHashValue + selfTimeFootPrint);

                    return true;
                }
                else {
                    throw new NotEnoughGasAvailable();
                }
            }
    
            catch(SelfLoopedTransaction ex) {
                System.out.print("");
            }
            catch(NotEnoughGasAvailable ex) {
                System.out.println("You need " + (amount-Integer.parseInt(blockchain.get(0).accessData)) + " Gas more to process out Transaction.");
            }
            catch(NegativeGasNotAllowed ex) {
                System.out.println(amount + " cannot be processed.");
            }
            return false;
        }
        private Boolean Decentralized_Blockchain_Validation() { 
            Block currentBlock; 
            Block previousBlock; 
    
            System.out.println("Analyzing Blockchain Validation...");
            for (int i=1;i<blockchain.size();i++) { 
                try {
                    currentBlock = blockchain.get(i); 
                    previousBlock = blockchain.get(i-1); 
        
                    if (!currentBlock.accessHashValue.equals(currentBlock.accessHashValue())) { 
                        System.out.println("Hash value of Block no." + i + " has not matched with Blockchain"); 
                        return false; 
                    } 
                    else {
                        System.out.println("Hash Value of Block no." + i + " is validated"); 
                        Thread.sleep(1000);
                    }
        
                    if (!previousBlock.accessHashValue.equals(currentBlock.accessPreviousHashValue)) { 
                        System.out.println("Hash value of Block no." + i + " with Previous Block no." + (i-1) + " has not matched in the Blockchain"); 
                        return false; 
                    } 
                    else {
                        System.out.println("Hash Value of Block no." + i + " with Previous Block no." + (i-1) + " is validated"); 
                        Thread.sleep(1000);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("Blocks were overproduced as a result of which Blockchain is desynthesized.\n");
                }
                catch(InterruptedException e) {
                    System.out.println("Invalid Block was found.\n");
                }
            }

            //IntegrationsHouse accessHistory = new IntegrationsHouse();
            IntegrationsHouse.MerkleTrees Process = accessHistory.new MerkleTrees(NewTransactionAddToHistory);
            System.out.println("Merkle Root: " + Process.GetMerkleRoot());
            System.out.println("Decentralized Blockchain Validation successfully completed !!\n");

            Evaluation();
            return true; 
        }
    }
} 

public class Main extends Exception { 
    private static final long serialVersionUID = 1L;
    //private static ArrayList<Block> blockchain = new ArrayList<Block>();
    //static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Blockchain newBlockchainProduction = new Blockchain();
        
        Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
        GenesisGradle.accessGenesisChamber();

        //Blockchain.IntegrationsHouse IntegrationGradle = newBlockchainProduction.new IntegrationsHouse();
        Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
        //IntegrationGradle.accessDecentralized_Blockchain_Validation();

        int i = 0;
        while(i!=5) {
            IntegrationGradle.accessTransaction();
            i++;
        }

        //Blockchain.BlockchainEvaluation();
        //Blockchain.Evaluation();
        //IntegrationGradle.Evaluation();
        IntegrationGradle.accessDecentralized_Blockchain_Validation();
    } 
} 
