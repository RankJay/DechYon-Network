import java.util.*;
import java.io.*;
import java.security.*; 
//import sun.security.krb5.EncryptionKey;
import java.awt.event.*;
import java.awt.*; 
import javax.swing.*;

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
        //System.out.println("\nYou cannot process negatively signed Gas Amount in the transaction.");
        Dashboard.Console1.append("\nYou cannot process negatively signed Gas Amount in the transaction.");
    }
}
class NotEnoughGasAvailable extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughGasAvailable() {
        //System.out.println("\nYou don't have enough Gas to process the transaction.");
        Dashboard.Console1.append("\nYou don't have enough Gas to process the transaction.");
    }
}
class SelfLoopedTransaction extends Exception {
    private static final long serialVersionUID = 1L;

    public SelfLoopedTransaction() {
        Dashboard.Console1.append("\nYou cannot process a Transaction to your own ID, that will led to demolishing the transaction fees.");
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
        //System.out.println("Generated Hash-Value is: " + this.hashValue + "\n");
        Dashboard.Console1.append("Generated Hash-Value is: " + this.hashValue + "\n");
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
            //String PrivateKey = scanner.next();                 
            int member = 0;
            //while(member!=blockchain.size()) {
            //    if(PrivateKey.equals(blockchain.get(member).accessTransactionHistory())) {
                    BlockchainMining accessMining = new BlockchainMining();
                    accessMining.Mine(member);  
            //        break;              
            //    }
            //    member++;
            //}
        }
        private void Mine(int ID) {
            Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
            String MinerID = blockchain.get(ID).accessHashValue;
            
            try {
                if(IntegrationGradle.Decentralized_Blockchain_Validation() && Consensus(MinerID)) {
                    //System.out.println("Mining..");
                    Dashboard.MiningConsole.append("Mining..\n");

                    Thread.sleep(10000);
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));

                    //double randomNum = ThreadLocalRandom.current().nextDouble(0, 1);

                    double Bounty = Double.parseDouble(String.valueOf(blockchain.get(ID).data.get(blockchain.get(ID).data.size()-1))) + 0.001;
                    
                    blockchain.get(blockchain.size() - 1).data.put(0, MinerID + "founded this block.");
                    blockchain.get(ID).data.put(blockchain.get(ID).data.size(), String.valueOf(Bounty));
                    
                    //System.out.println("New Block is mined.");
                    Dashboard.MiningConsole.append("New Block is mined with Hash Value: " + blockchain.get(blockchain.size()-1).accessHashValue + "\n\n");
                    Dashboard.CurrentBalance.setText(blockchain.get(0).data.get(blockchain.get(0).data.size()-1));
                    //System.out.println(blockchain.get(blockchain.size()-1).accessHashValue);
                }
            }
            catch(InterruptedException e) {
                //System.out.println("Force Interruption was detected while mining the new Block");
                Dashboard.MiningConsole.append("\n\nForce Interruption was detected while mining the new Block\n\n");
            }
            catch(NullPointerException e) {
                //System.out.println("Latest Block mined was clustered with the Blockchain, hence discarded.");
                Dashboard.MiningConsole.append("\n\nLatest Block mined was clustered with the Blockchain, hence discarded.\n\n");
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
            int NoOfBlock = 3;
            //int NoOfBlock = Integer.parseInt(String.valueOf(Dashboard.BlockEntryUse.getText()));;

            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            
            //System.out.println("Enter the no. of blocks to be produced:");
            //NoOfBlock = scanner.nextInt();
            
            Dashboard.Console1.append("Blockchain is being produced at the rate of 1 block per 1.5 seconds:\n");
            try {
                blockchain.add(new Block("10000", "0"));
                NewTransactionAddToHistory.add("0");
                //Thread.sleep(1500);
                while(counter != NoOfBlock) {
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));
                    accessIntegrations.Decentralized_Blockchain_Validation();
                    //Thread.sleep(1500);
                    counter++;
                }
                Dashboard.UserID.setText(blockchain.get(0).accessHashValue);
            }
            
            catch(StackOverflowError e) {
                //System.out.print("");
                Dashboard.Console1.append("");
                
            }
            catch(NullPointerException e) {
                //System.out.print("");
                Dashboard.Console1.append("");
            }

            //catch (InterruptedException ex) {
            //    System.out.println("Blockchain abruptly broken.");
            //}

            //scanner.close();
            //System.out.println("Blockchain is produced...\n");
            Dashboard.Console1.append("\n\nBlockchain is produced...\n");
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
            //System.out.print("Enter Public-key in order to access transaction: \n");
            Dashboard.Console1.append("Enter Public-key in order to access transaction: \n");
            //String PublicKey = scanner.next();
            String PublicKey = ConfirmationKey.PublicKey.getText();

            IntegrationsHouse.MerkleTrees Process = accessIntegrations.new MerkleTrees(NewTransactionAddToHistory);

            ConfirmationKey.Execute.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    ConfirmationKey.ExecuteActionPerformed(evt);

                    if(PublicKey.equals(Process.GetMerkleRoot().get(0)) && ConfirmationKey.Flag==1) {
                        accessIntegrations.Transaction();
                    }
                }
            });
            
        }
        protected void accessDecentralized_Blockchain_Validation() {
            accessIntegrations.Decentralized_Blockchain_Validation();
        }
        protected void accessMerkleTrees() {
            IntegrationsHouse.MerkleTrees Process = new MerkleTrees(NewTransactionAddToHistory);
            //System.out.println("Merkle Root: " + Process.GetMerkleRoot().get(0));
            //Dashboard.Console1.append("\nMerkle Root: " + Process.GetMerkleRoot().get(0) + "\n\n\n\n");
            Dashboard.Console1.append("\nPublic-Key: " + Process.GetMerkleRoot().get(0) + "\n\n\n\n");
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
            //System.out.println("\nYou have a total amount of " + blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1) + " Gas left.");
            //System.out.println("Your ID is: " + blockchain.get(0).accessHashValue);
            Dashboard.Console1.append("\n\nYou have a total amount of " + blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1) + " Gas left.\n");
            Dashboard.Console1.append("Your ID is: " + blockchain.get(0).accessHashValue + "\n");
        }

        private Boolean Transaction() {
            CurrentState();
            
            //System.out.print("\nClient ID : ");
            //String ClientID = scanner.next();
            String ClientID = String.valueOf(Dashboard.ClientIDEntryUse.getText());
            //System.out.print("Amount to be transfered: ");
            //double amount = scanner.nextDouble();
            double amount = Double.parseDouble(String.valueOf(Dashboard.AmountEntryUse.getText()));
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
                                
                                //System.out.print("Enter your Private Key to execute transactions: ");
                                //String PrivateKey = scanner.next();
                                String PrivateKey = ConfirmationKey.PrivateKey.getText();
                                
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

                                    //System.out.println("\n" + amount + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                                    //System.out.println("You are left with " + blockchain.get(0).data.get(blockchain.get(0).data.size()-1) + " Gas");

                                    Dashboard.Console1.append("\n" + amount + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue + "\n");
                                    Dashboard.Console1.append("You are left with " + blockchain.get(0).data.get(blockchain.get(0).data.size()-1) + " Gas\n\n");
                                    Dashboard.CurrentBalance.setText(blockchain.get(0).data.get(blockchain.get(0).data.size()-1));

                                    IntegrationsHouse.MerkleTrees Process = accessIntegrations.new MerkleTrees(NewTransactionAddToHistory);
                                    //System.out.println("Merkle Root: " + Process.GetMerkleRoot().get(0));
                                    Dashboard.Console1.append("\nNew Public-Key: " + Process.GetMerkleRoot().get(0) + "\n");
                                    Dashboard.NOOfTransactions.setText(String.valueOf(Dashboard.counter+=1));

                                    return true;
                                }
                                else {
                                    //System.out.println("Sender Authentication failed.");
                                    Dashboard.Console1.append("\n\nSender Authentication failed.\n\n");
                                }
                            }
                            else {
                                throw new NotEnoughGasAvailable();
                            }
                        }
                    }
                    blockchain.get(0).data.put(blockchain.get(0).data.size(), String.valueOf(Double.parseDouble((String)blockchain.get(0).accessData.get(0)) - TransactionFee));
                    blockchain.get(0).accessData = blockchain.get(0).data;
                    Dashboard.CurrentBalance.setText(blockchain.get(0).data.get(blockchain.get(0).data.size()-1));

                    long selfTimeFootPrint = new Date().getTime();
                    NewTransactionAddToHistory.add(TransactionFee + blockchain.get(0).accessHashValue + selfTimeFootPrint);

                    return true;
                }
                else {
                    throw new NotEnoughGasAvailable();
                }
            }

            catch(StackOverflowError e) {
                //System.out.print("");
                Dashboard.Console1.append("\n");
            }
            catch(NullPointerException e) {
                //System.out.print("");
                Dashboard.Console1.append("\n");
            }
            catch(NumberFormatException e) {
                //System.out.print("\nNumber format Exception.\n");
                Dashboard.Console1.append("\nNumber format Exception.\n");
            }

            catch(SelfLoopedTransaction ex) {
                //System.out.print("");
                Dashboard.Console1.append("\n");
            }
            catch(NotEnoughGasAvailable ex) {
                //System.out.println("You need " + (amount-Double.parseDouble(blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1))) + " Gas more to process out Transaction.");
                Dashboard.Console1.append("\nYou need " + (amount-Double.parseDouble(blockchain.get(0).accessData.get(blockchain.get(0).accessData.size()-1))) + " Gas more to process out Transaction.\n\n");
            }
            catch(NegativeGasNotAllowed ex) {
                //System.out.println(amount + " cannot be processed.");
                Dashboard.Console1.append("\n" + amount + " cannot be processed.\n\n");
            }
            return false;
        }
        private Boolean Decentralized_Blockchain_Validation() { 
            Block currentBlock; 
            Block previousBlock; 
    
            //System.out.println("Analyzing Blockchain Validation...");
            Dashboard.Console1.append("\nAnalyzing Blockchain Validation...");
            for (int i=1;i<blockchain.size();i++) { 
                try {
                    currentBlock = blockchain.get(i); 
                    previousBlock = blockchain.get(i-1); 
        
                    if (!currentBlock.accessHashValue.equals(currentBlock.accessHashValue())) { 
                        System.out.println("Hash value of Block no." + i + " has not matched with Blockchain"); 
                        return false; 
                    } 
                    else {
                        //System.out.println("Hash Value of Block no." + i + " is validated"); 
                        //Thread.sleep(1000);
                    }
        
                    if (!previousBlock.accessHashValue.equals(currentBlock.accessPreviousHashValue)) { 
                        System.out.println("Hash value of Block no." + i + " with Previous Block no." + (i-1) + " has not matched in the Blockchain"); 
                        return false; 
                    } 
                    else {
                        //System.out.println("Hash Value of Block no." + i + " with Previous Block no." + (i-1) + " is validated"); 
                        //Thread.sleep(1000);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    System.out.println("Blocks were overproduced as a result of which Blockchain is desynthesized.\n");
                    Dashboard.Console1.append("\n\nBlocks were overproduced as a result of which Blockchain is desynthesized.\n\n");
                }
                //catch(InterruptedException e) {
                //    System.out.println("Invalid Block was found.\n");
                //}
            }
            //System.out.println("Decentralized Blockchain Validation successfully completed !!\n");
            Dashboard.Console1.append("\nDecentralized Blockchain Validation successfully completed !!\n");

            //IntegrationsHouse accessIntegrations = new IntegrationsHouse();

            //Evaluation();
            return true; 
        }
    }
} 

class ConfirmationKey extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    //private static Blockchain newBlockchainProduction = new Blockchain();
    //private static Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
    private static Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
    //private static Blockchain.BlockchainMining MiningGradle = newBlockchainProduction.new BlockchainMining();
    //protected static int counter = 0;

    public ConfirmationKey() {
        initComponents();
    }

    public void initComponents() {

        Background2 = new javax.swing.JPanel();
        PublicKey = new javax.swing.JTextField();
        PrivateKey = new javax.swing.JTextField();
        Access = new javax.swing.JButton();
        ValidatingConfirmation = new javax.swing.JLabel();
        EnterYourPrivateKeyWrite = new javax.swing.JLabel();
        Execute = new javax.swing.JButton();
        EnterPublicKeyWrite = new javax.swing.JLabel();
        PublicKeyWrite = new javax.swing.JLabel();
        PrivateKeyWrite = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Background2.setBackground(new java.awt.Color(24, 25, 29));

        PublicKey.setBackground(new java.awt.Color(42, 43, 49));
        PublicKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        PublicKey.setForeground(new java.awt.Color(100, 100, 100));
        PublicKey.setText("Enter your Public-Key");
        PublicKey.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 194, 49), 3));

        PrivateKey.setBackground(new java.awt.Color(42, 43, 49));
        PrivateKey.setFont(new java.awt.Font("Segoe UI", 1, 14));
        PrivateKey.setForeground(new java.awt.Color(100, 100, 100));
        PrivateKey.setText("Enter your Private-Key");
        PrivateKey.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 3, true));
        

        Access.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 18));
        Access.setForeground(new java.awt.Color(255, 255, 255));
        Access.setText("Access");
        Access.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 194, 49), 3));
        Access.setContentAreaFilled(false);
        Access.setFocusPainted(false);
        Access.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessActionPerformed(evt);
            }
        });

        ValidatingConfirmation.setFont(new java.awt.Font("Segoe UI", 1, 24));
        ValidatingConfirmation.setForeground(new java.awt.Color(255, 255, 255));
        ValidatingConfirmation.setText("Validating Confirmation...");

        EnterYourPrivateKeyWrite.setFont(new java.awt.Font("Segoe UI", 1, 18));
        EnterYourPrivateKeyWrite.setForeground(new java.awt.Color(150, 150, 150));
        EnterYourPrivateKeyWrite.setText("Enter your Private-Key for executing Transaction :");

        Execute.setBackground(new java.awt.Color(138, 255, 19));
        Execute.setFont(new java.awt.Font("Segoe UI", 1, 18));
        Execute.setForeground(new java.awt.Color(72, 136, 32));
        Execute.setText("Execute");
        Execute.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(138, 255, 19), 4, true));
        Execute.setContentAreaFilled(false);
        Execute.setEnabled(false);
        Execute.setFocusPainted(false);
        Execute.setOpaque(true);
        Execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteActionPerformed(evt);
            }
        });

        EnterPublicKeyWrite.setFont(new java.awt.Font("Segoe UI", 1, 18));
        EnterPublicKeyWrite.setForeground(new java.awt.Color(122, 194, 49));
        EnterPublicKeyWrite.setText("Enter your Public-Key for accessing Mainframe :");

        PublicKeyWrite.setFont(new java.awt.Font("Segoe UI", 0, 13));
        PublicKeyWrite.setForeground(new java.awt.Color(100, 100, 100));
        PublicKeyWrite.setText("PublicKey");

        PrivateKeyWrite.setFont(new java.awt.Font("Segoe UI", 0, 13));
        PrivateKeyWrite.setForeground(new java.awt.Color(100, 100, 100));
        PrivateKeyWrite.setText("PrivateKey");

        jLabel1.setIcon(new javax.swing.ImageIcon("F:\\Group3.png"));

        javax.swing.GroupLayout Background2Layout = new javax.swing.GroupLayout(Background2);
        Background2.setLayout(Background2Layout);
        Background2Layout.setHorizontalGroup(
            Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Background2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Background2Layout.createSequentialGroup()
                        .addComponent(EnterPublicKeyWrite)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Background2Layout.createSequentialGroup()
                        .addComponent(EnterYourPrivateKeyWrite)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Background2Layout.createSequentialGroup()
                        .addGroup(Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PublicKeyWrite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(Background2Layout.createSequentialGroup()
                                .addComponent(PrivateKey, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Background2Layout.createSequentialGroup()
                                .addComponent(PublicKey, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Access, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PrivateKeyWrite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44))
                    .addGroup(Background2Layout.createSequentialGroup()
                        .addComponent(ValidatingConfirmation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66))))
        );
        Background2Layout.setVerticalGroup(
            Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Background2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ValidatingConfirmation)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addComponent(EnterPublicKeyWrite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PublicKey, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Access, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PublicKeyWrite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EnterYourPrivateKeyWrite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Execute, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PrivateKey, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrivateKeyWrite)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 592, 396);
    }

    public static void AccessActionPerformed(java.awt.event.ActionEvent evt) {
        EnterPublicKeyWrite.setForeground(new java.awt.Color(100, 100, 100));
        PrivateKey.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 3, true));
        EnterYourPrivateKeyWrite.setForeground(new java.awt.Color(122, 194, 49));   
        PublicKey.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 3, true));
        Execute.setEnabled(true);
        Access.setEnabled(false);
        IntegrationGradle.accessTransaction();
        //IntegrationGradle.accessDecentralized_Blockchain_Validation();

    }

    public static void ExecuteActionPerformed(java.awt.event.ActionEvent evt) {
        Execute.setEnabled(false);
        Flag = 1;
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConfirmationKey.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmationKey.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmationKey.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmationKey.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmationKey().setVisible(true);
            }
        });
    }
    public static javax.swing.JButton Access;
    public static javax.swing.JPanel Background2;
    public static javax.swing.JLabel EnterPublicKeyWrite;
    public static javax.swing.JLabel EnterYourPrivateKeyWrite;
    public static javax.swing.JButton Execute;
    public static javax.swing.JTextField PrivateKey;
    public static javax.swing.JLabel PrivateKeyWrite;
    public static javax.swing.JTextField PublicKey;
    public static javax.swing.JLabel PublicKeyWrite;
    public static javax.swing.JLabel ValidatingConfirmation;
    public static javax.swing.JLabel jLabel1;
    public static int Flag = 0;
}


public class Dashboard extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    //private static LinkedList<Block> blockchain = new LinkedList<Block>();
    //static Scanner scanner = new Scanner(System.in);
    private static Blockchain newBlockchainProduction = new Blockchain();
    private static Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
    private static Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
    private static Blockchain.BlockchainMining MiningGradle = newBlockchainProduction.new BlockchainMining();
    protected static int counter = 0;

    public Dashboard() {
        initComponents();
    }

    private void initComponents() {

        Background = new javax.swing.JPanel();
        BlockMiningTab = new javax.swing.JPanel();
        BlockMining = new javax.swing.JLabel();
        NewBlock = new javax.swing.JLabel();
        Bounty = new javax.swing.JLabel();
        USDBounty = new javax.swing.JLabel();
        NewBlockCount = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MiningConsole = new javax.swing.JTextArea();
        ExecuteTransaction2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        TabBar = new javax.swing.JPanel();
        CurrentTab = new javax.swing.JPanel();
        DashboardImage = new javax.swing.JLabel();
        Dashboard = new javax.swing.JLabel();
        UserID = new javax.swing.JTextField();
        YourID = new javax.swing.JLabel();
        BlockchainProduction = new javax.swing.JPanel();
        NOOfTransactions = new javax.swing.JLabel();
        WalletCard = new javax.swing.JPanel();
        Wallet = new javax.swing.JLabel();
        NoOfWallet = new javax.swing.JLabel();
        Transactions = new javax.swing.JLabel();
        CurrentBalanceWrite = new javax.swing.JLabel();
        CurrentBalance = new javax.swing.JLabel();
        USD = new javax.swing.JLabel();
        ProduceBlockchain = new javax.swing.JButton();
        TransactionExecution = new javax.swing.JPanel();
        WALLET = new javax.swing.JLabel();
        AMOUNT = new javax.swing.JLabel();
        CLIENTID = new javax.swing.JLabel();
        WalletNumber = new javax.swing.JTextField();
        Amount = new javax.swing.JTextField();
        AmountEntryUse = new javax.swing.JLabel();
        ClientID = new javax.swing.JTextField();
        ClientIDEntryUse = new javax.swing.JLabel();
        ExecuteTransaction = new javax.swing.JButton();
        ConsensusTab = new javax.swing.JPanel();
        Dashboard1 = new javax.swing.JLabel();
        NextBlockWrite = new javax.swing.JLabel();
        USDBountyWrite = new javax.swing.JLabel();
        HashOfMinedBlock = new javax.swing.JTextField();
        ValidateMinedBlock = new javax.swing.JButton();
        NewBlockCountWrite = new javax.swing.JLabel();
        BountyCountWrite = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Console = new javax.swing.JScrollPane();
        Console1 = new javax.swing.JTextArea();
        BitcoinPanel = new javax.swing.JPanel();
        BTC = new javax.swing.JLabel();
        Bitcoin = new javax.swing.JLabel();
        BitcoinImage = new javax.swing.JLabel();
        EthereumPanel = new javax.swing.JPanel();
        ETH = new javax.swing.JLabel();
        Ethereum = new javax.swing.JLabel();
        EthereumImage = new javax.swing.JLabel();
        LitecoinPanel = new javax.swing.JPanel();
        LTC = new javax.swing.JLabel();
        Litecoin = new javax.swing.JLabel();
        LitcoinImage = new javax.swing.JLabel();
        DashPanel = new javax.swing.JPanel();
        DASH = new javax.swing.JLabel();
        Dash = new javax.swing.JLabel();
        MyWallets = new javax.swing.JLabel();
        Overview = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Background.setBackground(new java.awt.Color(24, 25, 29));

        BlockMiningTab.setBackground(new java.awt.Color(29, 32, 36));

        BlockMining.setFont(new java.awt.Font("Gilroy-Bold", 0, 21));
        BlockMining.setForeground(new java.awt.Color(125, 125, 125));
        BlockMining.setText("Block Mining");

        NewBlock.setFont(new java.awt.Font("Gilroy-Bold", 0, 14));
        NewBlock.setForeground(new java.awt.Color(125, 125, 125));
        NewBlock.setText("New Block");

        Bounty.setBackground(new java.awt.Color(255, 255, 255));
        Bounty.setFont(new java.awt.Font("Gilroy-Medium", 0, 36));
        Bounty.setForeground(new java.awt.Color(138, 255, 19));
        Bounty.setText("2099");

        USDBounty.setFont(new java.awt.Font("Segoe UI", 1, 14));
        USDBounty.setForeground(new java.awt.Color(125, 125, 125));
        USDBounty.setText("USD");

        NewBlockCount.setFont(new java.awt.Font("Gilroy-Light", 0, 36));
        NewBlockCount.setForeground(new java.awt.Color(255, 255, 255));
        NewBlockCount.setText("2543678");

        MiningConsole.setBackground(new java.awt.Color(29, 32, 36));
        MiningConsole.setColumns(20);
        MiningConsole.setFont(new java.awt.Font("Consolas", 1, 14));
        MiningConsole.setForeground(new java.awt.Color(122, 194, 49));
        MiningConsole.setRows(5);
        MiningConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(138, 255, 19), 3));
        MiningConsole.setHighlighter(null);
        jScrollPane1.setViewportView(MiningConsole);

        ExecuteTransaction2.setBackground(new java.awt.Color(138, 255, 19));
        ExecuteTransaction2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        ExecuteTransaction2.setForeground(new java.awt.Color(72, 136, 32));
        ExecuteTransaction2.setText("Mine");
        ExecuteTransaction2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(138, 255, 19), 4, true));
        ExecuteTransaction2.setContentAreaFilled(false);
        ExecuteTransaction2.setFocusPainted(false);
        ExecuteTransaction2.setOpaque(true);
        ExecuteTransaction2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Buffer.png"));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Graph.png"));

        javax.swing.GroupLayout BlockMiningTabLayout = new javax.swing.GroupLayout(BlockMiningTab);
        BlockMiningTab.setLayout(BlockMiningTabLayout);
        BlockMiningTabLayout.setHorizontalGroup(
            BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockMiningTabLayout.createSequentialGroup()
                        .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                                .addComponent(Bounty)
                                .addGap(423, 423, 423)
                                .addComponent(ExecuteTransaction2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1)
                            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                                .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NewBlockCount)
                                    .addComponent(USDBounty))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(53, 53, 53)))
                        .addGap(33, 33, 33))
                    .addGroup(BlockMiningTabLayout.createSequentialGroup()
                        .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NewBlock)
                            .addComponent(BlockMining))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addContainerGap())))
        );
        BlockMiningTabLayout.setVerticalGroup(
            BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(BlockMiningTabLayout.createSequentialGroup()
                        .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(BlockMining)
                                .addGap(18, 18, 18)
                                .addComponent(NewBlock))
                            .addGroup(BlockMiningTabLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewBlockCount)
                        .addGap(1, 1, 1)
                        .addComponent(USDBounty))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BlockMiningTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExecuteTransaction2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Bounty))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );

        Search.setBackground(new java.awt.Color(42, 43, 49));
        Search.setFont(new java.awt.Font("Segoe UI", 1, 13));
        Search.setForeground(new java.awt.Color(150, 150, 150));
        Search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Search.setText("search");
        Search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        Search.setMargin(new java.awt.Insets(10, 10, 10, 10));
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        TabBar.setBackground(new java.awt.Color(29, 32, 36));

        CurrentTab.setBackground(new java.awt.Color(42, 43, 49));

        DashboardImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Dashboard.png"));

        javax.swing.GroupLayout CurrentTabLayout = new javax.swing.GroupLayout(CurrentTab);
        CurrentTab.setLayout(CurrentTabLayout);
        CurrentTabLayout.setHorizontalGroup(
            CurrentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurrentTabLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(DashboardImage)
                .addContainerGap(59, Short.MAX_VALUE))
        );
        CurrentTabLayout.setVerticalGroup(
            CurrentTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CurrentTabLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(DashboardImage)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout TabBarLayout = new javax.swing.GroupLayout(TabBar);
        TabBar.setLayout(TabBarLayout);
        TabBarLayout.setHorizontalGroup(
            TabBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CurrentTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TabBarLayout.setVerticalGroup(
            TabBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabBarLayout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(CurrentTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Dashboard.setFont(new java.awt.Font("Gilroy-Light", 1, 36));
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard.setText("Dashboard");

        UserID.setBackground(new java.awt.Color(29, 32, 36));
        UserID.setFont(new java.awt.Font("Segoe UI", 1, 14));
        UserID.setForeground(new java.awt.Color(200, 200, 200));
        UserID.setText("null");
        UserID.setToolTipText("");
        UserID.setAlignmentX(2.0F);
        UserID.setAlignmentY(2.0F);
        UserID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 2, true));
        UserID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        UserID.setMinimumSize(new java.awt.Dimension(6, 23));
        UserID.setName("");
        UserID.setOpaque(false);

        YourID.setFont(new java.awt.Font("Segoe UI", 1, 12));
        YourID.setForeground(new java.awt.Color(255, 255, 255));
        YourID.setText("Your ID");

        BlockchainProduction.setBackground(new java.awt.Color(42, 43, 49));

        NOOfTransactions.setFont(new java.awt.Font("Gilroy-Light", 0, 36));
        NOOfTransactions.setForeground(new java.awt.Color(255, 255, 255));
        NOOfTransactions.setText("0");

        WalletCard.setBackground(new java.awt.Color(37, 37, 41));

        Wallet.setFont(new java.awt.Font("Gilroy-Light", 0, 18));
        Wallet.setForeground(new java.awt.Color(255, 255, 255));
        Wallet.setText(" Wallet");
        Wallet.setToolTipText("");

        NoOfWallet.setFont(new java.awt.Font("Gilroy-Light", 1, 48));
        NoOfWallet.setForeground(new java.awt.Color(255, 255, 255));
        NoOfWallet.setText("1");

        javax.swing.GroupLayout WalletCardLayout = new javax.swing.GroupLayout(WalletCard);
        WalletCard.setLayout(WalletCardLayout);
        WalletCardLayout.setHorizontalGroup(
            WalletCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WalletCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NoOfWallet)
                .addGap(44, 44, 44))
            .addGroup(WalletCardLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Wallet)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        WalletCardLayout.setVerticalGroup(
            WalletCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WalletCardLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(NoOfWallet)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Wallet)
                .addContainerGap())
        );

        Transactions.setFont(new java.awt.Font("Gilroy-Bold", 0, 14));
        Transactions.setForeground(new java.awt.Color(150, 150, 150));
        Transactions.setText("Transactions");

        CurrentBalanceWrite.setFont(new java.awt.Font("Gilroy-Bold", 0, 14));
        CurrentBalanceWrite.setForeground(new java.awt.Color(150, 150, 150));
        CurrentBalanceWrite.setText("Current Balance");

        CurrentBalance.setFont(new java.awt.Font("Gilroy-Light", 0, 36));
        CurrentBalance.setForeground(new java.awt.Color(255, 255, 255));
        CurrentBalance.setText("10000.00");

        USD.setFont(new java.awt.Font("Gilroy-Bold", 0, 14));
        USD.setForeground(new java.awt.Color(150, 150, 150));
        USD.setText("USD");

        ProduceBlockchain.setFont(new java.awt.Font("Gilroy-SemiBold", 0, 18));
        ProduceBlockchain.setForeground(new java.awt.Color(255, 255, 255));
        ProduceBlockchain.setText("Produce Blockchain");
        ProduceBlockchain.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 3, true));
        ProduceBlockchain.setContentAreaFilled(false);
        ProduceBlockchain.setFocusPainted(false);
        ProduceBlockchain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BlockchainProductionLayout = new javax.swing.GroupLayout(BlockchainProduction);
        BlockchainProduction.setLayout(BlockchainProductionLayout);
        BlockchainProductionLayout.setHorizontalGroup(
            BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockchainProductionLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CurrentBalance)
                            .addComponent(CurrentBalanceWrite))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(USD)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlockchainProductionLayout.createSequentialGroup()
                        .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ProduceBlockchain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(BlockchainProductionLayout.createSequentialGroup()
                                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NOOfTransactions)
                                    .addComponent(Transactions))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                                .addComponent(WalletCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))))
        );
        BlockchainProductionLayout.setVerticalGroup(
            BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockchainProductionLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addComponent(NOOfTransactions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Transactions))
                    .addComponent(WalletCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(CurrentBalanceWrite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CurrentBalance))
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(USD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(ProduceBlockchain, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        TransactionExecution.setBackground(new java.awt.Color(29, 32, 36));

        WALLET.setFont(new java.awt.Font("Gilroy-Bold", 0, 13));
        WALLET.setForeground(new java.awt.Color(150, 150, 150));
        WALLET.setText("WALLET");

        AMOUNT.setFont(new java.awt.Font("Gilroy-Bold", 0, 13));
        AMOUNT.setForeground(new java.awt.Color(150, 150, 150));
        AMOUNT.setText("AMOUNT");

        CLIENTID.setFont(new java.awt.Font("Gilroy-Bold", 0, 13));
        CLIENTID.setForeground(new java.awt.Color(150, 150, 150));
        CLIENTID.setText("CLIENT ID");

        WalletNumber.setBackground(new java.awt.Color(42, 43, 49));
        WalletNumber.setFont(new java.awt.Font("Tahoma", 1, 13));
        WalletNumber.setForeground(new java.awt.Color(255, 255, 255));
        WalletNumber.setText("1");
        WalletNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));

        Amount.setBackground(new java.awt.Color(42, 43, 49));
        Amount.setFont(new java.awt.Font("Segoe UI", 1, 13));
        Amount.setForeground(new java.awt.Color(255, 255, 255));
        Amount.setText("0");
        Amount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        ClientID.setBackground(new java.awt.Color(42, 43, 49));
        ClientID.setFont(new java.awt.Font("Segoe UI", 1, 13));
        ClientID.setForeground(new java.awt.Color(255, 255, 255));
        ClientID.setText("0");
        ClientID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        ClientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        ExecuteTransaction.setFont(new java.awt.Font("Microsoft YaHei", 1, 18));
        ExecuteTransaction.setForeground(new java.awt.Color(255, 255, 255));
        ExecuteTransaction.setText("send");
        ExecuteTransaction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(122, 194, 49), 3));
        ExecuteTransaction.setContentAreaFilled(false);
        ExecuteTransaction.setFocusPainted(false);
        ExecuteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExecuteTransactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransactionExecutionLayout = new javax.swing.GroupLayout(TransactionExecution);
        TransactionExecution.setLayout(TransactionExecutionLayout);
        TransactionExecutionLayout.setHorizontalGroup(
            TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TransactionExecutionLayout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ExecuteTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TransactionExecutionLayout.createSequentialGroup()
                        .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CLIENTID)
                            .addComponent(WALLET))
                        .addGap(18, 18, 18)
                        .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TransactionExecutionLayout.createSequentialGroup()
                                .addComponent(WalletNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(AMOUNT)
                                .addGap(18, 18, 18)
                                .addComponent(Amount))
                            .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(45, 45, 45))
        );
        TransactionExecutionLayout.setVerticalGroup(
            TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionExecutionLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WALLET)
                    .addComponent(AMOUNT)
                    .addComponent(WalletNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLIENTID)
                    .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ExecuteTransaction)
                .addGap(31, 31, 31))
        );

        ConsensusTab.setBackground(new java.awt.Color(29, 32, 36));

        Dashboard1.setFont(new java.awt.Font("Gilroy-Light", 1, 18));
        Dashboard1.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard1.setText("Consensus");

        NextBlockWrite.setFont(new java.awt.Font("Gilroy-Bold", 1, 14));
        NextBlockWrite.setForeground(new java.awt.Color(125, 125, 125));
        NextBlockWrite.setText("Next Block");

        USDBountyWrite.setFont(new java.awt.Font("Gilroy-Bold", 1, 14));
        USDBountyWrite.setForeground(new java.awt.Color(125, 125, 125));
        USDBountyWrite.setText("USD");

        HashOfMinedBlock.setBackground(new java.awt.Color(29, 32, 36));
        HashOfMinedBlock.setFont(new java.awt.Font("Segoe UI", 1, 14));
        HashOfMinedBlock.setForeground(new java.awt.Color(200, 200, 200));
        HashOfMinedBlock.setText("null");
        HashOfMinedBlock.setToolTipText("");
        HashOfMinedBlock.setAlignmentX(2.0F);
        HashOfMinedBlock.setAlignmentY(2.0F);
        HashOfMinedBlock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 2, true));
        HashOfMinedBlock.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        HashOfMinedBlock.setMinimumSize(new java.awt.Dimension(6, 23));
        HashOfMinedBlock.setOpaque(false);

        ValidateMinedBlock.setBackground(new java.awt.Color(230, 230, 230));
        ValidateMinedBlock.setFont(new java.awt.Font("Segoe UI", 1, 14));
        ValidateMinedBlock.setForeground(new java.awt.Color(100, 100, 100));
        ValidateMinedBlock.setText("Validate");
        ValidateMinedBlock.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(230, 230, 230), 4, true));
        ValidateMinedBlock.setContentAreaFilled(false);
        ValidateMinedBlock.setFocusPainted(false);
        ValidateMinedBlock.setOpaque(true);
        ValidateMinedBlock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        NewBlockCountWrite.setFont(new java.awt.Font("Gilroy-Light", 0, 36));
        NewBlockCountWrite.setForeground(new java.awt.Color(255, 255, 255));
        NewBlockCountWrite.setText("2543679");

        BountyCountWrite.setBackground(new java.awt.Color(255, 255, 255));
        BountyCountWrite.setFont(new java.awt.Font("Gilroy-Medium", 0, 36));
        BountyCountWrite.setForeground(new java.awt.Color(138, 255, 19));
        BountyCountWrite.setText("2099");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Buffer.png"));

        javax.swing.GroupLayout ConsensusTabLayout = new javax.swing.GroupLayout(ConsensusTab);
        ConsensusTab.setLayout(ConsensusTabLayout);
        ConsensusTabLayout.setHorizontalGroup(
            ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ConsensusTabLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ConsensusTabLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Dashboard1)
                            .addComponent(NextBlockWrite))
                        .addGap(157, 157, 157)
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ConsensusTabLayout.createSequentialGroup()
                        .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ConsensusTabLayout.createSequentialGroup()
                                .addComponent(NewBlockCountWrite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(USDBountyWrite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BountyCountWrite))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ConsensusTabLayout.createSequentialGroup()
                                .addComponent(HashOfMinedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ValidateMinedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ConsensusTabLayout.setVerticalGroup(
            ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConsensusTabLayout.createSequentialGroup()
                .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ConsensusTabLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(Dashboard1)
                        .addGap(18, 18, 18)
                        .addComponent(NextBlockWrite))
                    .addGroup(ConsensusTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NewBlockCountWrite, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BountyCountWrite)
                        .addComponent(USDBountyWrite)))
                .addGap(14, 14, 14)
                .addGroup(ConsensusTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HashOfMinedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValidateMinedBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        Console1.setBackground(new java.awt.Color(29, 32, 36));
        Console1.setColumns(20);
        Console1.setFont(new java.awt.Font("Consolas", 1, 14));
        Console1.setForeground(new java.awt.Color(122, 194, 49));
        Console1.setRows(5);
        Console1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(29, 32, 36), 1, true));
        Console.setViewportView(Console1);

        BitcoinPanel.setBackground(new java.awt.Color(122, 194, 49));
        BitcoinPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BitcoinPanelMouseClicked(evt);
            }
        });

        BTC.setFont(new java.awt.Font("Segoe UI", 1, 18));
        BTC.setForeground(new java.awt.Color(72, 136, 32));
        BTC.setText("BTC");

        Bitcoin.setFont(new java.awt.Font("Gilroy-Bold", 1, 30));
        Bitcoin.setForeground(new java.awt.Color(255, 255, 255));
        Bitcoin.setText("Bitcoin");

        BitcoinImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Bitcoin.png"));

        javax.swing.GroupLayout BitcoinPanelLayout = new javax.swing.GroupLayout(BitcoinPanel);
        BitcoinPanel.setLayout(BitcoinPanelLayout);
        BitcoinPanelLayout.setHorizontalGroup(
            BitcoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BitcoinPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(BitcoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Bitcoin)
                    .addComponent(BTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(BitcoinImage)
                .addContainerGap())
        );
        BitcoinPanelLayout.setVerticalGroup(
            BitcoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BitcoinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BitcoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BitcoinPanelLayout.createSequentialGroup()
                        .addComponent(BitcoinImage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BitcoinPanelLayout.createSequentialGroup()
                        .addComponent(BTC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(Bitcoin)))
                .addContainerGap())
        );

        EthereumPanel.setBackground(new java.awt.Color(29, 32, 36));
        EthereumPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EthereumPanelMouseClicked(evt);
            }
        });

        ETH.setFont(new java.awt.Font("Segoe UI", 1, 18));
        ETH.setForeground(new java.awt.Color(100, 100, 100));
        ETH.setText("ETH");

        Ethereum.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Ethereum.setForeground(new java.awt.Color(255, 255, 255));
        Ethereum.setText("Ethereum");

        EthereumImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Ethereum.png"));

        javax.swing.GroupLayout EthereumPanelLayout = new javax.swing.GroupLayout(EthereumPanel);
        EthereumPanel.setLayout(EthereumPanelLayout);
        EthereumPanelLayout.setHorizontalGroup(
            EthereumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EthereumPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EthereumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EthereumPanelLayout.createSequentialGroup()
                        .addComponent(ETH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(EthereumImage))
                    .addGroup(EthereumPanelLayout.createSequentialGroup()
                        .addComponent(Ethereum)
                        .addGap(0, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );
        EthereumPanelLayout.setVerticalGroup(
            EthereumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EthereumPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EthereumPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ETH)
                    .addComponent(EthereumImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Ethereum))
        );

        LitecoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        LitecoinPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LitecoinPanelMouseClicked(evt);
            }
        });

        LTC.setFont(new java.awt.Font("Segoe UI", 1, 18));
        LTC.setForeground(new java.awt.Color(100, 100, 100));
        LTC.setText("LTC");

        Litecoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Litecoin.setForeground(new java.awt.Color(255, 255, 255));
        Litecoin.setText("Litecoin");

        LitcoinImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\Hp\\Desktop\\folder\\FrontEnd\\src\\frontend\\Litecoin.png"));

        javax.swing.GroupLayout LitecoinPanelLayout = new javax.swing.GroupLayout(LitecoinPanel);
        LitecoinPanel.setLayout(LitecoinPanelLayout);
        LitecoinPanelLayout.setHorizontalGroup(
            LitecoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LitecoinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LitecoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LitecoinPanelLayout.createSequentialGroup()
                        .addComponent(Litecoin)
                        .addGap(0, 70, Short.MAX_VALUE))
                    .addGroup(LitecoinPanelLayout.createSequentialGroup()
                        .addComponent(LTC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LitcoinImage)))
                .addContainerGap())
        );
        LitecoinPanelLayout.setVerticalGroup(
            LitecoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LitecoinPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LitecoinPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LTC)
                    .addComponent(LitcoinImage))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Litecoin))
        );

        DashPanel.setBackground(new java.awt.Color(29, 32, 36));
        DashPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DashPanelMouseClicked(evt);
            }
        });

        DASH.setFont(new java.awt.Font("Segoe UI", 1, 18));
        DASH.setForeground(new java.awt.Color(100, 100, 100));
        DASH.setText("Dash");

        Dash.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Dash.setForeground(new java.awt.Color(255, 255, 255));
        Dash.setText("Dash");

        javax.swing.GroupLayout DashPanelLayout = new javax.swing.GroupLayout(DashPanel);
        DashPanel.setLayout(DashPanelLayout);
        DashPanelLayout.setHorizontalGroup(
            DashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DASH)
                    .addComponent(Dash))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        DashPanelLayout.setVerticalGroup(
            DashPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DashPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DASH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Dash))
        );

        MyWallets.setFont(new java.awt.Font("Gilroy-Bold", 1, 14));
        MyWallets.setForeground(new java.awt.Color(150, 150, 150));
        MyWallets.setText("Overview");

        Overview.setFont(new java.awt.Font("Gilroy-Bold", 1, 14));
        Overview.setForeground(new java.awt.Color(230, 230, 230));
        Overview.setText("My Wallets");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(TabBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(BlockchainProduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(ConsensusTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(TransactionExecution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(BitcoinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(EthereumPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(LitecoinPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(DashPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(Dashboard)
                                .addGap(124, 124, 124)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(YourID)
                                .addGap(18, 18, 18)
                                .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(BlockMiningTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(Console, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(MyWallets)
                                .addGap(341, 341, 341)
                                .addComponent(Overview)))
                        .addContainerGap(67, Short.MAX_VALUE))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(YourID)
                            .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(Dashboard)
                        .addGap(32, 32, 32)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MyWallets)
                            .addComponent(Overview))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BitcoinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(EthereumPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LitecoinPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DashPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TransactionExecution, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ConsensusTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(BlockchainProduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BlockMiningTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Console, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }                       
    private void BlockchainActionPerformed(java.awt.event.ActionEvent evt) {
        String Activator = evt.getActionCommand();

        if (Activator.equals("Produce Blockchain")) {
            //BlockEntryUse.setText("5"); 

            GenesisGradle.accessGenesisChamber();

            //NoOfBlock.setText("0");
        }
        else if (Activator.equals("send")) {
            AmountEntryUse.setText(Amount.getText()); 
            ClientIDEntryUse.setText(ClientID.getText()); 

            IntegrationGradle.accessTransaction();
            IntegrationGradle.accessDecentralized_Blockchain_Validation();

            //IntegrationGradle.accessMerkleTrees();
            Amount.setText("0");
            ClientID.setText("null");
        }
        else if (Activator.equals("Mine")) {
            MiningGradle.accessBlockchainMining();
        }
    }

    private void ExecuteTransactionActionPerformed(java.awt.event.ActionEvent evt) {  
        AmountEntryUse.setText(Amount.getText()); 
        ClientIDEntryUse.setText(ClientID.getText()); 

        ConfirmationKey FinalFrame = new ConfirmationKey();
        FinalFrame.setVisible(true);

        IntegrationGradle.accessTransaction();
        IntegrationGradle.accessDecentralized_Blockchain_Validation();

        //IntegrationGradle.accessMerkleTrees();
        Amount.setText("0");
        ClientID.setText("null");
    }                                                                                                   

    private void EthereumPanelMouseClicked(java.awt.event.MouseEvent evt) {                                           
        EthereumPanel.setBackground(new java.awt.Color(122, 194, 49));  
        ETH.setForeground(new java.awt.Color(72, 136, 32));
        Ethereum.setFont(new java.awt.Font("Gilroy-Bold", 1, 30));
        Bitcoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Litecoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Dash.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        BitcoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        BTC.setForeground(new java.awt.Color(100, 100, 100));
        LitecoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        LTC.setForeground(new java.awt.Color(100, 100, 100));
        DashPanel.setBackground(new java.awt.Color(29, 32, 36));
        DASH.setForeground(new java.awt.Color(100, 100, 100));
    }                                          

    private void LitecoinPanelMouseClicked(java.awt.event.MouseEvent evt) {                                           
        EthereumPanel.setBackground(new java.awt.Color(29, 32, 36));
        ETH.setForeground(new java.awt.Color(100, 100, 100));
        BitcoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        BTC.setForeground(new java.awt.Color(100, 100, 100));
        LitecoinPanel.setBackground(new java.awt.Color(122, 194, 49));
        LTC.setForeground(new java.awt.Color(72, 136, 32));
        Litecoin.setFont(new java.awt.Font("Gilroy-Bold", 1, 30));
        Ethereum.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Bitcoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Dash.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        DashPanel.setBackground(new java.awt.Color(29, 32, 36));
        DASH.setForeground(new java.awt.Color(100, 100, 100));        
    }                                          

    private void BitcoinPanelMouseClicked(java.awt.event.MouseEvent evt) {                                          
        EthereumPanel.setBackground(new java.awt.Color(29, 32, 36));
        ETH.setForeground(new java.awt.Color(100, 100, 100));
        BitcoinPanel.setBackground(new java.awt.Color(122, 194, 49));
        Bitcoin.setFont(new java.awt.Font("Gilroy-Bold", 1, 30));
        Ethereum.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Litecoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Dash.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        BTC.setForeground(new java.awt.Color(72, 136, 32));
        LitecoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        LTC.setForeground(new java.awt.Color(100, 100, 100));
        DashPanel.setBackground(new java.awt.Color(29, 32, 36));
        DASH.setForeground(new java.awt.Color(100, 100, 100));        
    }                                         

    private void DashPanelMouseClicked(java.awt.event.MouseEvent evt) {                                       
         EthereumPanel.setBackground(new java.awt.Color(29, 32, 36));
        ETH.setForeground(new java.awt.Color(100, 100, 100));
        BitcoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        BTC.setForeground(new java.awt.Color(100, 100, 100));
        LitecoinPanel.setBackground(new java.awt.Color(29, 32, 36));
        LTC.setForeground(new java.awt.Color(100, 100, 100));
        DashPanel.setBackground(new java.awt.Color(122, 194, 49));
        DASH.setForeground(new java.awt.Color(72, 136, 32));        
        Ethereum.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Bitcoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Litecoin.setFont(new java.awt.Font("Gilroy-Light", 0, 30));
        Dash.setFont(new java.awt.Font("Gilroy-Bold", 1, 30));
    }
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }
                   
    private javax.swing.JLabel AMOUNT;
    private javax.swing.JTextField Amount;
    protected static javax.swing.JLabel AmountEntryUse;
    private javax.swing.JLabel BTC;
    private javax.swing.JPanel Background;
    private javax.swing.JLabel Bitcoin;
    private javax.swing.JLabel BitcoinImage;
    private javax.swing.JPanel BitcoinPanel;
    private javax.swing.JLabel BlockMining;
    private javax.swing.JPanel BlockMiningTab;
    private javax.swing.JPanel BlockchainProduction;
    private javax.swing.JLabel Bounty;
    private javax.swing.JLabel BountyCountWrite;
    private javax.swing.JLabel CLIENTID;
    private javax.swing.JTextField ClientID;
    private javax.swing.JPanel ConsensusTab;
    private javax.swing.JScrollPane Console;
    protected static javax.swing.JLabel ClientIDEntryUse;
    protected static javax.swing.JTextArea Console1;
    protected static javax.swing.JLabel BlockEntryUse;
    protected static javax.swing.JLabel CurrentBalance;
    private javax.swing.JLabel CurrentBalanceWrite;
    private javax.swing.JPanel CurrentTab;
    private javax.swing.JLabel DASH;
    private javax.swing.JLabel Dash;
    private javax.swing.JPanel DashPanel;
    private javax.swing.JLabel Dashboard;
    private javax.swing.JLabel Dashboard1;
    private javax.swing.JLabel DashboardImage;
    private javax.swing.JLabel ETH;
    private javax.swing.JLabel Ethereum;
    private javax.swing.JLabel EthereumImage;
    private javax.swing.JPanel EthereumPanel;
    private javax.swing.JButton ExecuteTransaction;
    private javax.swing.JButton ExecuteTransaction2;
    private javax.swing.JTextField HashOfMinedBlock;
    private javax.swing.JLabel LTC;
    private javax.swing.JLabel LitcoinImage;
    private javax.swing.JLabel Litecoin;
    private javax.swing.JPanel LitecoinPanel;
    protected static javax.swing.JTextArea MiningConsole;
    private javax.swing.JLabel MyWallets;
    protected static javax.swing.JLabel NOOfTransactions;
    private javax.swing.JLabel NewBlock;
    private javax.swing.JLabel NewBlockCount;
    private javax.swing.JLabel NewBlockCountWrite;
    private javax.swing.JLabel NextBlockWrite;
    private javax.swing.JLabel NoOfWallet;
    private javax.swing.JLabel Overview;
    private javax.swing.JButton ProduceBlockchain;
    private javax.swing.JTextField Search;
    private javax.swing.JPanel TabBar;
    private javax.swing.JPanel TransactionExecution;
    private javax.swing.JLabel Transactions;
    private javax.swing.JLabel USD;
    private javax.swing.JLabel USDBounty;
    private javax.swing.JLabel USDBountyWrite;
    protected static javax.swing.JTextField UserID;
    private javax.swing.JButton ValidateMinedBlock;
    private javax.swing.JLabel WALLET;
    private javax.swing.JLabel Wallet;
    private javax.swing.JPanel WalletCard;
    private javax.swing.JTextField WalletNumber;
    private javax.swing.JLabel YourID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;                   
}
