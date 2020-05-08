import java.util.*;
import sun.security.krb5.EncryptionKey;
import java.io.*;
import java.security.*; 
import java.awt.event.*;
import java.awt.*; 
import javax.swing.*;

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
        //System.out.println("Generated Hash-Value is: " + this.hashValue + "\n");
        Main.jTextArea1.append("Generated Hash-Value is: " + this.hashValue + "\n");
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
    private static LinkedList<Block> blockchain = new LinkedList<Block>();
    private static LinkedList<String> NewTransactionAddToHistory = new LinkedList<String>();
    private static Scanner scanner = new Scanner(System.in);

    class ProductionGradle {

        protected void accessGenesisChamber() {
            ProductionGradle accessGradle = new ProductionGradle();
            accessGradle.GenesisChamber();
        }

        private void GenesisBlock() {
            blockchain.add(new Block("10000", "0"));
        }

        private void GenesisChamber() {
            int counter = 1;
            //int NoOfBlock;
            int NoOfBlock = Integer.parseInt(String.valueOf(Main.BlockEntryUse.getText()));

            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            
            //System.out.println("Enter the no. of blocks to be produced:");
            //NoOfBlock = scanner.nextInt();
            
            //System.out.println("Blockchain is being produced at the rate of 1 block per 1.5 seconds:");
            Main.jTextArea1.append("Blockchain is being produced at the rate of 1 block per 1.5 seconds:\n");
            try {
                GenesisBlock();
                Thread.sleep(1500);
                while(counter != NoOfBlock) {
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));
                    accessIntegrations.Decentralized_Blockchain_Validation();
                    Thread.sleep(1500);
                    counter++;
                }
            }
            catch (InterruptedException ex) {
                System.out.println("Blockchain abruptly broken.");
            }

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

        private static IntegrationsHouse accessIntegrations = new IntegrationsHouse();

        protected void accessCurrentState() {
            accessIntegrations.accessCurrentState();
        }
        protected void accessTransaction() {
            accessIntegrations.Transaction();
        }
        protected void accessDecentralized_Blockchain_Validation() {
            accessIntegrations.Decentralized_Blockchain_Validation();
        }
        protected void accessMerkleTrees() {
            IntegrationsHouse.MerkleTrees Process = new MerkleTrees(NewTransactionAddToHistory);
            //System.out.println("Merkle Root: " + Process.GetMerkleRoot().get(0));
            Main.jTextArea1.append("\nMerkle Root: " + Process.GetMerkleRoot().get(0) + "\n");
        }

        class MerkleTrees {
            private LinkedList<String> TransactionsHistory;

            protected MerkleTrees(LinkedList<String> TransactionsHistory) {
                this.TransactionsHistory = TransactionsHistory;
            }
            
            protected LinkedList<String> GetMerkleRoot() {
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

            private String MergeHashValue(String hashValueLeft, String hashValueRight) {
                String mergedHash = hashValueLeft + hashValueRight;

                Blockchain accessBlockchain = new Blockchain();
                Blockchain.ProductionGradle accessEncryption = accessBlockchain.new ProductionGradle();
                return accessEncryption.EncryptionMechanism(mergedHash);
            }

            
        }
        private void CurrentState() {
            //System.out.println("\nYou have a total amount of " + blockchain.get(0).accessData + " Gas left.");
            //System.out.println("Your ID is: " + blockchain.get(0).accessHashValue);
            Main.jTextArea1.append("\nYou have a total amount of " + blockchain.get(0).accessData + " Gas left.\n");
            Main.jTextArea1.append("Your ID is: " + blockchain.get(0).accessHashValue + "\n");
        }

        private Boolean Transaction() {
            CurrentState();

            //System.out.print("\nClient ID : ");
            //String ClientID = scanner.next();
            String ClientID = String.valueOf(Main.ClientIDEntryUse.getText());
            //System.out.print("Amount to be transfered: ");
            //double amount = scanner.nextDouble();
            double amount = Double.parseDouble(String.valueOf(Main.AmountEntryUse.getText()));
            double TransactionFee = 0.01*Double.parseDouble(String.valueOf(amount));

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
                            
                            //System.out.println("\n" + blockchain.get(j).data + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                            //System.out.println("You are left with " + blockchain.get(0).data + " Gas\n");
                            Main.jTextArea1.append("\n" + amount + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                            Main.jTextArea1.append("\nYou are left with " + blockchain.get(0).data + " Gas\n");

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

            System.out.println("Decentralized Blockchain Validation successfully completed !!\n");

            return true; 
        }
    }
} 

public class Main extends javax.swing.JFrame {

    public static Blockchain newBlockchainProduction = new Blockchain();
    public static Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
    public static Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();

    public Main() {
        initComponents();
    }

    private void initComponents() {

        Welcome = new javax.swing.JLabel();
        BlockEntry = new javax.swing.JLabel();
        BlockEntryUse = new javax.swing.JLabel();
        NoOfBlock = new javax.swing.JTextField();
        ProduceBlockchain = new javax.swing.JButton();
        ClientIDEntry = new javax.swing.JLabel();
        ClientIDEntryUse = new javax.swing.JLabel();
        ClientID = new javax.swing.JTextField();
        AmountEntry = new javax.swing.JLabel();
        AmountEntryUse = new javax.swing.JLabel();
        Amount = new javax.swing.JTextField();
        ExecuteTransaction = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Welcome.setFont(new java.awt.Font("Segoe UI", 1, 24));  
        Welcome.setText("Welcome to Blockchain Simulator");

        BlockEntry.setFont(new java.awt.Font("Segoe UI", 0, 18));  
        BlockEntry.setText("Enter the no. of Blocks :");

        NoOfBlock.setFont(new java.awt.Font("Segoe UI", 0, 14));  
        NoOfBlock.setText("0");
        

        ProduceBlockchain.setFont(new java.awt.Font("Segoe UI", 0, 18));  
        ProduceBlockchain.setText("Produce Blockchain");
        ProduceBlockchain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        ClientIDEntry.setFont(new java.awt.Font("Segoe UI", 0, 18));  
        ClientIDEntry.setText("Client ID :");

        ClientID.setText("null");
        ClientID.setToolTipText("");

        AmountEntry.setFont(new java.awt.Font("Segoe UI", 0, 18));  
        AmountEntry.setText("Amount :");

        Amount.setText("0");

        ExecuteTransaction.setFont(new java.awt.Font("Segoe UI", 0, 18));  
        ExecuteTransaction.setText("Execute Transaction");
        ExecuteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));  
        jLabel3.setText("jTextArea1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(AmountEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ClientIDEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ClientID))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(BlockEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(NoOfBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(138, 138, 138)))
                                .addGap(66, 66, 66)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ProduceBlockchain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ExecuteTransaction, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BlockEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NoOfBlock)
                    .addComponent(ProduceBlockchain, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ClientIDEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AmountEntry)
                    .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExecuteTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void BlockchainActionPerformed(java.awt.event.ActionEvent evt) {
        String Activator = evt.getActionCommand();

        if (Activator.equals("Produce Blockchain")) {
            BlockEntryUse.setText(NoOfBlock.getText()); 

            GenesisGradle.accessGenesisChamber();

            NoOfBlock.setText("0");
        }
        else if (Activator.equals("Execute Transaction")) {
            AmountEntryUse.setText(Amount.getText()); 
            ClientIDEntryUse.setText(ClientID.getText()); 

            IntegrationGradle.accessTransaction();
            IntegrationGradle.accessDecentralized_Blockchain_Validation();
            IntegrationGradle.accessMerkleTrees();

            Amount.setText("0");
            ClientID.setText("null");
        }
            // Error Handling
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField Amount;
    private javax.swing.JLabel AmountEntry;
    public static javax.swing.JLabel AmountEntryUse;
    private javax.swing.JLabel BlockEntry;
    public static javax.swing.JLabel BlockEntryUse;
    private javax.swing.JTextField ClientID;
    private javax.swing.JLabel ClientIDEntry;
    public static javax.swing.JLabel ClientIDEntryUse;
    private javax.swing.JButton ExecuteTransaction;
    private javax.swing.JTextField NoOfBlock;
    private javax.swing.JButton ProduceBlockchain;
    private javax.swing.JLabel Welcome;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea1;

}
