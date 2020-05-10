import java.util.*;
import sun.security.krb5.EncryptionKey;
import java.io.*;
import java.security.*; 

interface accessBlockchain {
    Hashtable <Integer, String> accessData = new Hashtable <Integer, String>();
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
    protected Hashtable <Integer, String> data = new Hashtable <Integer, String>(); 
    protected Hashtable <Integer, String> accessData = new Hashtable <Integer, String>();
    private long timeFootPrint; 
    protected LinkedList<String> TransactionHistory = new LinkedList<String>(); 
    private String PrivateMerkleRoot = "";
    double TransactionFee;
    
    protected Block(String data, String previousHashValue) { 
        this.data.put(0, data); 
        this.accessData = this.data;
        this.previousHashValue = previousHashValue;  
        this.accessPreviousHashValue = this.previousHashValue;
        this.timeFootPrint = new Date().getTime(); 
        this.hashValue = generateHashValue();
        this.accessHashValue = this.hashValue;
        this.TransactionFee = 0.01*Integer.parseInt(this.data.get(this.data.size()-1));
        this.PrivateMerkleRoot = this.hashValue;
        //System.out.println("Data: " + this.data);
        System.out.println("Generated Hash-Value is: " + this.hashValue + "\n");
    } 

    private String generateHashValue() { 
        String HashValue = "";
        Blockchain accessBlockchain = new Blockchain();
        Blockchain.ProductionGradle GenerateHashValue = accessBlockchain.new ProductionGradle();
        HashValue = GenerateHashValue.EncryptionMechanism(previousHashValue + Long.toString(timeFootPrint) + data.get(0)); 
        return HashValue; 
    } 
    private String Wallet() {
        return this.data.get(this.data.size()-1);
    }

    public String accessWallet() {
        if (this.accessData.get(this.accessData.size()-1)==this.data.get(this.data.size()-1)) {
            return Wallet();
        }
        return "0";
    }
    
    public String accessHashValue() { 
        return this.accessHashValue;
    } 

    public String accessPreviousHashValue() { 
        return this.accessPreviousHashValue;
    } 

    public void accessTransactionHistory(String Root) {
        this.PrivateMerkleRoot = Root;
        System.out.println("Root address: " + Root);
    }

    public String accessTransactionHistory() {
        String Root = this.PrivateMerkleRoot;
        return Root;
    }
} 

class Blockchain { 
    private static LinkedList<Block> blockchain = new LinkedList<Block>();
    private static LinkedList<String> NewTransactionAddToHistory = new LinkedList<String>();
    private static Scanner scanner = new Scanner(System.in);

    /*
    public static void BlockchainEvaluation() {
        for(int i=0;i<blockchain.size();i++) {
            System.out.println(blockchain.get(i).data.get(blockchain.get(i).data.size()-1));
            System.out.println();
        }
    }
    */
    class BlockchainMining {
        protected void accessBlockchainMining() {
            System.out.print("Enter your Private Key to fetch your Miner account: ");
            String PrivateKey = scanner.next();                 
            int member = 0;
            while(member!=blockchain.size()) {
                if(PrivateKey.equals(blockchain.get(member).accessTransactionHistory())) {
                    BlockchainMining accessMining = new BlockchainMining();
                    accessMining.Mine(member);  
                    break;              
                }
                member++;
            }
        }
        private void Mine(int ID) {
            Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
            String MinerID = blockchain.get(ID).accessHashValue;
            
            try {
                if(IntegrationGradle.Decentralized_Blockchain_Validation() && Consensus(MinerID)) {
                    System.out.println("Mining..");
                    Thread.sleep(10000);
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));

                    //double randomNum = ThreadLocalRandom.current().nextDouble(0, 1);

                    double Bounty = Double.parseDouble(String.valueOf(blockchain.get(ID).data.get(blockchain.get(ID).data.size()-1))) + 0.001;
                    
                    blockchain.get(blockchain.size() - 1).data.put(0, MinerID + "founded this block.");
                    blockchain.get(ID).data.put(blockchain.get(ID).data.size(), String.valueOf(Bounty));
                    
                    System.out.println("New Block is mined.");
                    //System.out.println(blockchain.get(blockchain.size()-1).accessHashValue);
                }
            }
            catch(InterruptedException e) {
                System.out.println("Force Interruption was detected while mining the new Block");
            }
            catch(NullPointerException e) {
                System.out.println("Latest Block mined was clustered with the Blockchain, hence discarded.");
            }
        }

        private Boolean Consensus(String MinerID) {
            Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();

            if(IntegrationGradle.Decentralized_Blockchain_Validation()) {
                for(int i=0;i<blockchain.size();i++) {
                    if(MinerID.equals(blockchain.get(i).accessHashValue)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    class ProductionGradle {

        protected void accessGenesisChamber() {
            ProductionGradle accessGradle = new ProductionGradle();
            accessGradle.GenesisChamber();
        }

        private void GenesisChamber() {
            int counter = 1;
            int NoOfBlock;

            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            
            System.out.println("Enter the no. of blocks to be produced:");
            NoOfBlock = scanner.nextInt();
            
            System.out.println("Blockchain is being produced at the rate of 1 block per 1.5 seconds:");
            try {
                blockchain.add(new Block("10000", "0"));
                NewTransactionAddToHistory.add("0");
                Thread.sleep(1500);
                while(counter != NoOfBlock) {
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));
                    accessIntegrations.Decentralized_Blockchain_Validation();
                    Thread.sleep(1500);
                    counter++;
                }
            }
            
            catch(StackOverflowError e) {
                System.out.print("");
            }
            catch(NullPointerException e) {
                System.out.print("");
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

        //private LinkedList<String> NewTransactionAddToHistory = new LinkedList<String>();
        static IntegrationsHouse accessIntegrations = new IntegrationsHouse();

        protected void accessTransaction() {
            System.out.print("Enter Public-key in order to access transaction: ");
            String PublicKey = scanner.next();

            IntegrationsHouse.MerkleTrees Process = accessIntegrations.new MerkleTrees(NewTransactionAddToHistory);
            if(PublicKey.equals(Process.GetMerkleRoot().get(0))) {
                accessIntegrations.Transaction();
            }
        }
        protected void accessDecentralized_Blockchain_Validation() {
            accessIntegrations.Decentralized_Blockchain_Validation();
        }
        protected void accessMerkleTrees() {
            IntegrationsHouse.MerkleTrees Process = new MerkleTrees(NewTransactionAddToHistory);
            System.out.println("Merkle Root: " + Process.GetMerkleRoot().get(0));
            //Dashboard.Console.append("\nMerkle Root: " + Process.GetMerkleRoot().get(0) + "\n\n\n\n");
        }

        class MerkleTrees {
            private LinkedList<String> TransactionsHistory;

            protected MerkleTrees(LinkedList<String> TransactionsHistory) {
                this.TransactionsHistory = TransactionsHistory;
            }
            
            public LinkedList<String> GetMerkleRoot() {
                return MerkleTreeConstruction(this.TransactionsHistory);
            }

            private LinkedList<String> MerkleTreeConstruction(LinkedList<String> TransactionsHistory) {
                if(TransactionsHistory.size()==1) {
                    return TransactionsHistory;
                }

                LinkedList<String> updatedList = new LinkedList<>();

                for(int i=0;i<TransactionsHistory.size()-1;i+=2) {
                    updatedList.add(MergeHashValue(TransactionsHistory.get(i), TransactionsHistory.get(i+1)));
                }

                if(TransactionsHistory.size()%2==1) {
                    updatedList.add(MergeHashValue(TransactionsHistory.get(TransactionsHistory.size()-1), TransactionsHistory.get(TransactionsHistory.size()-1)));
                }
                
                return MerkleTreeConstruction(updatedList);
            }

            private String MergeHashValue(String hash1, String hash2) {
                String mergedHash = hash1 + hash2;

                Blockchain accessBlockchain = new Blockchain();
                Blockchain.ProductionGradle accessEncryption = accessBlockchain.new ProductionGradle();
                return accessEncryption.EncryptionMechanism(mergedHash);
            }

            
        }
        private void CurrentState() {
            System.out.println("\nYou have a total amount of " + blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1) + " Gas left.");
            System.out.println("Your ID is: " + blockchain.get(0).accessHashValue);
            //Dashboard.Console.append("\nYou have a total amount of " + blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1) + " Gas left.\n");
            //Dashboard.Console.append("Your ID is: " + blockchain.get(0).accessHashValue + "\n");
        }

        private Boolean Transaction() {
            CurrentState();
            
            System.out.print("\nClient ID : ");
            String ClientID = scanner.next();
            System.out.print("Amount to be transfered: ");
            double amount = scanner.nextDouble();
            double TransactionFee = 0.01*Double.parseDouble(String.valueOf(amount));

            try {
                if(amount<Double.parseDouble(blockchain.get(0).accessData.get(blockchain.get(0).data.size()-1))) {
                    if(amount<0) {
                        throw new NegativeGasNotAllowed();
                    }
                    if(ClientID==blockchain.get(0).accessHashValue) {
                        throw new SelfLoopedTransaction();
                    }
                    for(int j=0;j<blockchain.size();j++) {
                        if (blockchain.get(j).accessHashValue.equals(ClientID)) {
                            long timeFootPrint = new Date().getTime();

                            if(0<=(Double.parseDouble(blockchain.get(0).accessData.get(blockchain.get(0).data.size()-1)) - amount - TransactionFee)) {
                                
                                System.out.print("Enter your Private Key to execute transactions: ");
                                String PrivateKey = scanner.next();
                                
                                if(PrivateKey.equals(blockchain.get(0).accessTransactionHistory())) {

                                    blockchain.get(0).data.put(blockchain.get(0).data.size(), String.valueOf(Double.parseDouble((String)blockchain.get(0).accessData.get(blockchain.get(0).data.size()-1)) - amount - TransactionFee));
                                    blockchain.get(0).accessData = blockchain.get(0).data;
                                    blockchain.get(j).data.put(blockchain.get(j).data.size(), String.valueOf(Double.parseDouble((String)blockchain.get(j).accessData.get(blockchain.get(j).data.size()-1)) + amount));
                                    blockchain.get(j).accessData = blockchain.get(j).data;

                                    blockchain.get(0).TransactionHistory.add(amount + blockchain.get(0).accessHashValue + ClientID + timeFootPrint);
                                    blockchain.get(j).TransactionHistory.add(amount + ClientID + blockchain.get(0).accessHashValue + timeFootPrint);

                                    IntegrationsHouse.MerkleTrees Sender = accessIntegrations.new MerkleTrees(blockchain.get(0).TransactionHistory);
                                    IntegrationsHouse.MerkleTrees Receiver = accessIntegrations.new MerkleTrees(blockchain.get(j).TransactionHistory);

                                    NewTransactionAddToHistory.add(amount + blockchain.get(0).accessHashValue + ClientID + timeFootPrint);
                                    
                                    blockchain.get(0).accessTransactionHistory(Sender.GetMerkleRoot().get(0));
                                    blockchain.get(j).accessTransactionHistory(Receiver.GetMerkleRoot().get(0));

                                    System.out.println("\n" + amount + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                                    System.out.println("You are left with " + blockchain.get(0).data.get(blockchain.get(0).data.size()-1) + " Gas");

                                    IntegrationsHouse.MerkleTrees Process = accessIntegrations.new MerkleTrees(NewTransactionAddToHistory);
                                    System.out.println("Merkle Root: " + Process.GetMerkleRoot().get(0));

                                    return true;
                                }
                                else {
                                    System.out.println("Sender Authentication failed.");
                                }
                            }
                            else {
                                throw new NotEnoughGasAvailable();
                            }
                        }
                    }
                    blockchain.get(0).data.put(blockchain.get(0).data.size(), String.valueOf(Double.parseDouble((String)blockchain.get(0).accessData.get(0)) - TransactionFee));
                    blockchain.get(0).accessData = blockchain.get(0).data;

                    long selfTimeFootPrint = new Date().getTime();
                    NewTransactionAddToHistory.add(TransactionFee + blockchain.get(0).accessHashValue + selfTimeFootPrint);

                    return true;
                }
                else {
                    throw new NotEnoughGasAvailable();
                }
            }

            catch(StackOverflowError e) {
                System.out.print("");
            }
            catch(NullPointerException e) {
                System.out.print("");
            }
            catch(NumberFormatException e) {
                System.out.print("\nNumber format Exception.\n");
            }

            catch(SelfLoopedTransaction ex) {
                System.out.print("");
            }
            catch(NotEnoughGasAvailable ex) {
                System.out.println("You need " + (amount-Double.parseDouble(blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1))) + " Gas more to process out Transaction.");
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
            System.out.println("Decentralized Blockchain Validation successfully completed !!\n");

            //IntegrationsHouse accessIntegrations = new IntegrationsHouse();

            //Evaluation();
            return true; 
        }
    }
} 

public class Access extends Exception { 
    private static final long serialVersionUID = 1L;
    //private static LinkedList<Block> blockchain = new LinkedList<Block>();
    //static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Blockchain newBlockchainProduction = new Blockchain();
        
        Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
        GenesisGradle.accessGenesisChamber();

        //Blockchain.IntegrationsHouse IntegrationGradle = newBlockchainProduction.new IntegrationsHouse();
        Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
        //IntegrationGradle.accessDecentralized_Blockchain_Validation();

        sint i = 0;
        while(i!=5) {
            IntegrationGradle.accessTransaction();
            i++;
        }

        //Blockchain.BlockchainEvaluation();
        //Blockchain.Evaluation();
        //IntegrationGradle.Evaluation();
        IntegrationGradle.accessDecentralized_Blockchain_Validation();
        //BlockchainEvaluation();

        Blockchain.BlockchainMining access = newBlockchainProduction.new BlockchainMining();
        access.accessBlockchainMining();
    } 
}
