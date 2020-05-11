import java.util.*;
//import sun.security.krb5.EncryptionKey;
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
        //System.out.println("\nYou cannot process negatively signed Gas Amount in the transaction.");
        Dashboard.Console.append("\nYou cannot process negatively signed Gas Amount in the transaction.");
    }
}
class NotEnoughGasAvailable extends Exception {
    private static final long serialVersionUID = 1L;

    public NotEnoughGasAvailable() {
        //System.out.println("\nYou don't have enough Gas to process the transaction.");
        Dashboard.Console.append("\nYou don't have enough Gas to process the transaction.");
    }
}
class SelfLoopedTransaction extends Exception {
    private static final long serialVersionUID = 1L;

    public SelfLoopedTransaction() {
        //System.out.println("\nYou cannot process a Transaction to your own ID, that will led to demolishing the transaction fees.");
        Dashboard.Console.append("\nYou cannot process a Transaction to your own ID, that will led to demolishing the transaction fees.");
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
        Dashboard.Console.append("Generated Hash-Value is: " + this.hashValue + "\n");
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
            int NoOfBlock = Integer.parseInt(String.valueOf(Dashboard.BlockEntryUse.getText()));

            IntegrationsHouse accessIntegrations = new IntegrationsHouse();
            
            //System.out.println("Enter the no. of blocks to be produced:");
            //NoOfBlock = scanner.nextInt();
            
            //System.out.println("Blockchain is being produced at the rate of 1 block per 1.5 seconds:");
            Dashboard.Console.append("Blockchain is being produced at the rate of 1 block per 1.5 seconds:\n");
            try {
                GenesisBlock();
                //Thread.sleep(1500);
                while(counter != NoOfBlock) {
                    blockchain.add(new Block("0", blockchain.get(blockchain.size() - 1).accessHashValue));
                    accessIntegrations.Decentralized_Blockchain_Validation();
                    //Thread.sleep(1500);
                    counter++;
                }
                Dashboard.UserID.setText(blockchain.get(0).accessHashValue);
            }
            finally {

            }
            //catch (InterruptedException ex) {
            //    System.out.println("Blockchain abruptly broken.");
            //}

            //System.out.println("Blockchain is produced...\n");
            Dashboard.Console.append("Blockchain is produced...\n");
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
            Dashboard.Console.append("\nMerkle Root: " + Process.GetMerkleRoot().get(0) + "\n\n\n\n");
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
            Dashboard.Console.append("\nYou have a total amount of " + blockchain.get(0).accessData + " Gas left.\n");
            Dashboard.Console.append("Your ID is: " + blockchain.get(0).accessHashValue + "\n");
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
                if(amount<Double.parseDouble(blockchain.get(0).accessData)) {
                    if(amount<0) {
                        throw new NegativeGasNotAllowed();
                    }
                    else if(ClientID.equals(blockchain.get(0).accessHashValue)) {
                        throw new SelfLoopedTransaction();
                    }
                    else {
                        for(int j=0;j<blockchain.size();j++) {
                            if (blockchain.get(j).accessHashValue.equals(ClientID)) {
                                long timeFootPrint = new Date().getTime();

                                if((Double.parseDouble(blockchain.get(0).accessData) - amount - TransactionFee)>0) {
                                    blockchain.get(0).data = String.valueOf(Double.parseDouble(blockchain.get(0).accessData) - amount - TransactionFee);
                                    blockchain.get(0).accessData = blockchain.get(0).data;
                                    blockchain.get(j).data = String.valueOf(Double.parseDouble(blockchain.get(j).accessData) + amount);
                                    blockchain.get(j).accessData = blockchain.get(j).data;
                                    
                                    //System.out.println("\n" + blockchain.get(j).data + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                                    //System.out.println("You are left with " + blockchain.get(0).data + " Gas\n");
                                    Dashboard.Console.append("\n" + amount + " Gas sent to account, having Client ID: " + blockchain.get(j).accessHashValue);
                                    Dashboard.Console.append("\nYou are left with " + blockchain.get(0).data + " Gas\n");
                                    Dashboard.CurrentBalance.setText(blockchain.get(0).data);

                                    NewTransactionAddToHistory.add(amount + blockchain.get(0).accessHashValue + ClientID + timeFootPrint);
                                    Dashboard.NOOfTransactions.setText(String.valueOf(Dashboard.counter+=1));

                                    return true;
                                }
                                else {
                                    throw new NotEnoughGasAvailable();
                                }
                            }
                        }
                    }
                    blockchain.get(0).data = String.valueOf(Double.parseDouble(blockchain.get(0).accessData) - TransactionFee);
                    blockchain.get(0).accessData = blockchain.get(0).data;
                    Dashboard.CurrentBalance.setText(blockchain.get(0).data);

                    long selfTimeFootPrint = new Date().getTime();
                    NewTransactionAddToHistory.add(TransactionFee + blockchain.get(0).accessHashValue + selfTimeFootPrint);

                    return true;
                }
                else {
                    throw new NotEnoughGasAvailable();
                }
            }
    
            catch(SelfLoopedTransaction ex) {
                //System.out.print("");
                Dashboard.Console.append("\n");
            }
            catch(NumberFormatException e) {
                System.out.print("\nNumber format Exception.\n");
            }
            catch(NotEnoughGasAvailable ex) {
                //System.out.println("You need " + (amount-Integer.parseInt(blockchain.get(0).accessData)) + " Gas more to process out Transaction.");
                Dashboard.Console.append("\nYou need " + (amount-Double.parseDouble(blockchain.get(0).accessData)) + " Gas more to process out Transaction.\n\n");
            }
            catch(NegativeGasNotAllowed ex) {
                //System.out.println(amount + " cannot be processed.");
                Dashboard.Console.append("\n" + amount + " cannot be processed.\n\n");
            }
            return false;
        }
        private Boolean Decentralized_Blockchain_Validation() { 
            Block currentBlock; 
            Block previousBlock; 
    
            //System.out.println("Analyzing Blockchain Validation...");
            //Dashboard.Console.append("Analyzing Blockchain Validation...");
            for (int i=1;i<blockchain.size();i++) { 
                try {
                    currentBlock = blockchain.get(i); 
                    previousBlock = blockchain.get(i-1); 
        
                    if (!currentBlock.accessHashValue.equals(currentBlock.accessHashValue())) { 
                        //System.out.println("Hash value of Block no." + i + " has not matched with Blockchain"); 
                        return false; 
                    } 
                    else {
                        //System.out.println("Hash Value of Block no." + i + " is validated"); 
                        //Thread.sleep(1000);
                    }
        
                    if (!previousBlock.accessHashValue.equals(currentBlock.accessPreviousHashValue)) { 
                        //System.out.println("Hash value of Block no." + i + " with Previous Block no." + (i-1) + " has not matched in the Blockchain"); 
                        return false; 
                    } 
                    else {
                        //System.out.println("Hash Value of Block no." + i + " with Previous Block no." + (i-1) + " is validated"); 
                        //Thread.sleep(1000);
                    }
                }
                catch(ArrayIndexOutOfBoundsException e) {
                    //System.out.println("Blocks were overproduced as a result of which Blockchain is desynthesized.\n");
                    Dashboard.Console.append("Blocks were overproduced as a result of which Blockchain is desynthesized.\n");
                }
                //catch(InterruptedException e) {
                //    System.out.println("Invalid Block was found.\n");
                //}
            }

            //System.out.println("Decentralized Blockchain Validation successfully completed !!\n");
            //Dashboard.Console.append("Decentralized Blockchain Validation successfully completed !!\n");
            return true; 
        }
    }
} 


public class Dashboard extends javax.swing.JFrame {

    private static Blockchain newBlockchainProduction = new Blockchain();
    private static Blockchain.ProductionGradle GenesisGradle = newBlockchainProduction.new ProductionGradle();
    private static Blockchain.IntegrationsHouse IntegrationGradle = new Blockchain.IntegrationsHouse();
    protected static int counter = 0;


    public Dashboard() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        AccessPanel = new javax.swing.JPanel();
        Dashboard = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        BlockchainProduction = new javax.swing.JPanel();
        NOOfTransactions = new javax.swing.JLabel();
        BlockEntryUse = new javax.swing.JLabel();
        WalletCard = new javax.swing.JPanel();
        NoOfWallet = new javax.swing.JLabel();
        Wallet = new javax.swing.JLabel();
        Transactions = new javax.swing.JLabel();
        CurrentBalanceWrite = new javax.swing.JLabel();
        CurrentBalance = new javax.swing.JLabel();
        USD = new javax.swing.JLabel();
        ProduceBlockchain = new javax.swing.JButton();
        UserPanel = new javax.swing.JPanel();
        YourID = new javax.swing.JLabel();
        UserID = new javax.swing.JTextField();
        TransactionExecution = new javax.swing.JPanel();
        Transaction01 = new javax.swing.JLabel();
        WALLET = new javax.swing.JLabel();
        AMOUNT = new javax.swing.JLabel();
        CLIENTID = new javax.swing.JLabel();
        WalletNumber = new javax.swing.JTextField();
        Amount = new javax.swing.JTextField();
        AmountEntryUse = new javax.swing.JLabel();
        ClientID = new javax.swing.JTextField();
        ClientIDEntryUse = new javax.swing.JLabel();
        ExecuteTransaction = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Console = new javax.swing.JTextArea();
        Processes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);

        Background.setBackground(new java.awt.Color(24, 25, 29));

        AccessPanel.setBackground(new java.awt.Color(29, 32, 36));

        javax.swing.GroupLayout AccessPanelLayout = new javax.swing.GroupLayout(AccessPanel);
        AccessPanel.setLayout(AccessPanelLayout);
        AccessPanelLayout.setHorizontalGroup(
            AccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );
        AccessPanelLayout.setVerticalGroup(
            AccessPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 744, Short.MAX_VALUE)
        );

        Dashboard.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));
        Dashboard.setText("Dashboard");

        Search.setBackground(new java.awt.Color(42, 43, 49));
        Search.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Search.setForeground(new java.awt.Color(150, 150, 150));
        Search.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        Search.setText("search");
        Search.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        Search.setMargin(new java.awt.Insets(10, 10, 10, 10));
        /*
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });
        */

        BlockchainProduction.setBackground(new java.awt.Color(42, 43, 49));

        NOOfTransactions.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        NOOfTransactions.setForeground(new java.awt.Color(255, 255, 255));
        NOOfTransactions.setText("0");

        WalletCard.setBackground(new java.awt.Color(37, 37, 41));

        NoOfWallet.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        NoOfWallet.setForeground(new java.awt.Color(255, 255, 255));
        NoOfWallet.setText("1");

        Wallet.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Wallet.setForeground(new java.awt.Color(255, 255, 255));
        Wallet.setText(" Wallet");
        Wallet.setToolTipText("");

        javax.swing.GroupLayout WalletCardLayout = new javax.swing.GroupLayout(WalletCard);
        WalletCard.setLayout(WalletCardLayout);
        WalletCardLayout.setHorizontalGroup(
            WalletCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WalletCardLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(NoOfWallet)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WalletCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Wallet, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );
        WalletCardLayout.setVerticalGroup(
            WalletCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WalletCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NoOfWallet)
                .addGap(18, 18, 18)
                .addComponent(Wallet)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        Transactions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Transactions.setForeground(new java.awt.Color(200, 200, 200));
        Transactions.setText("Transactions");

        CurrentBalanceWrite.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        CurrentBalanceWrite.setForeground(new java.awt.Color(200, 200, 200));
        CurrentBalanceWrite.setText("Current Balance");

        CurrentBalance.setFont(new java.awt.Font("Consolas", 0, 36)); // NOI18N
        CurrentBalance.setForeground(new java.awt.Color(255, 255, 255));
        CurrentBalance.setText("10000.00");

        USD.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        USD.setForeground(new java.awt.Color(150, 150, 150));
        USD.setText("USD");

        ProduceBlockchain.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
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
                        .addComponent(CurrentBalanceWrite)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addComponent(CurrentBalance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(USD)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BlockchainProductionLayout.createSequentialGroup()
                        .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ProduceBlockchain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(BlockchainProductionLayout.createSequentialGroup()
                                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NOOfTransactions)
                                    .addComponent(Transactions))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addComponent(WalletCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(37, 37, 37))))
        );
        BlockchainProductionLayout.setVerticalGroup(
            BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BlockchainProductionLayout.createSequentialGroup()
                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(WalletCard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(NOOfTransactions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Transactions)))
                .addGap(24, 24, 24)
                .addComponent(CurrentBalanceWrite)
                .addGroup(BlockchainProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CurrentBalance))
                    .addGroup(BlockchainProductionLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(USD)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProduceBlockchain, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        UserPanel.setBackground(new java.awt.Color(29, 32, 36));

        YourID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        YourID.setForeground(new java.awt.Color(255, 255, 255));
        YourID.setText("Your ID");

        UserID.setBackground(new java.awt.Color(29, 32, 36));
        UserID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        UserID.setForeground(new java.awt.Color(200, 200, 200));
        UserID.setText("null");
        UserID.setToolTipText("");
        UserID.setAlignmentX(2.0F);
        UserID.setAlignmentY(2.0F);
        UserID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 3, true));
        UserID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        UserID.setMinimumSize(new java.awt.Dimension(6, 23));

        javax.swing.GroupLayout UserPanelLayout = new javax.swing.GroupLayout(UserPanel);
        UserPanel.setLayout(UserPanelLayout);
        UserPanelLayout.setHorizontalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(YourID)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        UserPanelLayout.setVerticalGroup(
            UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(UserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(YourID)
                    .addComponent(UserID, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TransactionExecution.setBackground(new java.awt.Color(29, 32, 36));

        Transaction01.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Transaction01.setForeground(new java.awt.Color(255, 255, 255));
        Transaction01.setText("Transactions");

        WALLET.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        WALLET.setForeground(new java.awt.Color(150, 150, 150));
        WALLET.setText("WALLET");

        AMOUNT.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        AMOUNT.setForeground(new java.awt.Color(150, 150, 150));
        AMOUNT.setText("AMOUNT");

        CLIENTID.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        CLIENTID.setForeground(new java.awt.Color(150, 150, 150));
        CLIENTID.setText("CLIENT ID");

        WalletNumber.setBackground(new java.awt.Color(42, 43, 49));
        WalletNumber.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        WalletNumber.setForeground(new java.awt.Color(255, 255, 255));
        WalletNumber.setText("1");
        WalletNumber.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));

        Amount.setBackground(new java.awt.Color(42, 43, 49));
        Amount.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        Amount.setForeground(new java.awt.Color(255, 255, 255));
        Amount.setText("0");
        Amount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        /*
        Amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });
        */

        ClientID.setBackground(new java.awt.Color(42, 43, 49));
        ClientID.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        ClientID.setForeground(new java.awt.Color(255, 255, 255));
        ClientID.setText("0");
        ClientID.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(42, 43, 49), 5, true));
        /*
        ClientID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });
        */

        ExecuteTransaction.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ExecuteTransaction.setForeground(new java.awt.Color(255, 255, 255));
        ExecuteTransaction.setText("send");
        ExecuteTransaction.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(122, 194, 49), 3, true));
        ExecuteTransaction.setContentAreaFilled(false);
        ExecuteTransaction.setFocusPainted(false);
        ExecuteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BlockchainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TransactionExecutionLayout = new javax.swing.GroupLayout(TransactionExecution);
        TransactionExecution.setLayout(TransactionExecutionLayout);
        TransactionExecutionLayout.setHorizontalGroup(
            TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionExecutionLayout.createSequentialGroup()
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TransactionExecutionLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(Transaction01))
                    .addGroup(TransactionExecutionLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
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
                                        .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(ClientID))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TransactionExecutionLayout.setVerticalGroup(
            TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TransactionExecutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Transaction01)
                .addGap(26, 26, 26)
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WALLET)
                    .addComponent(AMOUNT)
                    .addComponent(WalletNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Amount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(TransactionExecutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLIENTID)
                    .addComponent(ClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ExecuteTransaction)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Console.setBackground(new java.awt.Color(29, 32, 36));
        Console.setColumns(20);
        Console.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        Console.setForeground(new java.awt.Color(122, 194, 49));
        Console.setRows(5);
        Console.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(29, 32, 36), 1, true));
        jScrollPane1.setViewportView(Console);

        Processes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Processes.setForeground(new java.awt.Color(150, 150, 150));
        Processes.setText("Processes");

        javax.swing.GroupLayout BackgroundLayout = new javax.swing.GroupLayout(Background);
        Background.setLayout(BackgroundLayout);
        BackgroundLayout.setHorizontalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addComponent(AccessPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(Dashboard)
                        .addGap(125, 125, 125)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BackgroundLayout.createSequentialGroup()
                                .addComponent(BlockchainProduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TransactionExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(UserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(Processes)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(320, Short.MAX_VALUE))))
        );
        BackgroundLayout.setVerticalGroup(
            BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AccessPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BackgroundLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Dashboard)
                    .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(BackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BlockchainProduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BackgroundLayout.createSequentialGroup()
                        .addComponent(UserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(TransactionExecution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(Processes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BlockchainActionPerformed(java.awt.event.ActionEvent evt) {
        String Activator = evt.getActionCommand();

        if (Activator.equals("Produce Blockchain")) {
            BlockEntryUse.setText("5"); 

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
            // Error Handling
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AMOUNT;
    private javax.swing.JPanel AccessPanel;
    private javax.swing.JTextField Amount;
    protected static javax.swing.JLabel AmountEntryUse;
    private javax.swing.JPanel Background;
    private javax.swing.JPanel BlockchainProduction;
    private javax.swing.JLabel CLIENTID;
    private javax.swing.JTextField ClientID;
    protected static javax.swing.JLabel ClientIDEntryUse;
    protected static javax.swing.JTextArea Console;
    protected static javax.swing.JLabel BlockEntryUse;
    protected static javax.swing.JLabel CurrentBalance;
    private javax.swing.JLabel CurrentBalanceWrite;
    private javax.swing.JLabel Dashboard;
    private javax.swing.JButton ExecuteTransaction;
    protected static javax.swing.JLabel NOOfTransactions;
    private javax.swing.JLabel NoOfWallet;
    private javax.swing.JLabel Processes;
    private javax.swing.JButton ProduceBlockchain;
    private javax.swing.JTextField Search;
    private javax.swing.JLabel Transaction01;
    private javax.swing.JPanel TransactionExecution;
    private javax.swing.JLabel Transactions;
    private javax.swing.JLabel USD;
    private javax.swing.JPanel UserPanel;
    private javax.swing.JLabel WALLET;
    private javax.swing.JLabel Wallet;
    private javax.swing.JPanel WalletCard;
    private javax.swing.JTextField WalletNumber;
    private javax.swing.JLabel YourID;
    private javax.swing.JScrollPane jScrollPane1;
    protected static javax.swing.JTextField UserID;
    // End of variables declaration//GEN-END:variables
}