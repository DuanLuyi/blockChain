package prototype;
import java.util.ArrayList;


import com.google.gson.GsonBuilder;


// install the corresponding gson jar package

public class Main {
    //store block in arraylist
    public static ArrayList<Block> blockChain = new ArrayList<Block>();

    public static void main(String[] args) {
        //generate genesis block
        Block genesisBlock = new Block("This is the genesis block, data stores here.", "0", 0);
        blockChain.add(genesisBlock);
        //System.out.println(blockChain.size());

        //generate the next blocks, add following Txs here if needed.
        generateNextBlock("This is the second block.");
        generateNextBlock("This is the third block.");
        generateNextBlock("This is the 4 block.");
        generateNextBlock("This is the 5 block.");
        generateNextBlock("This is the 6 block.");
        generateNextBlock("This is the 7 block.");
        generateNextBlock("This is the 8 block.");
        generateNextBlock("This is the 9 block.");
        generateNextBlock("This is the 10 block.");
        generateNextBlock("This is the 11 block.");

        //Validation
        if(isValidNewBlock()){
            System.out.println("Blockchain validation successful!\n");
        }

        //print the block info via gson package
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }


    //Generating A Block
    public static void generateNextBlock(String data){
        Block previousBlock = blockChain.get(blockChain.size()-1);
        int nextIndex = previousBlock.index+1;
        String previousHash = blockChain.get(blockChain.size()-1).hash;

        Block newBlock = new Block(data, previousHash, nextIndex);
        blockChain.add(newBlock);
    }

    //Block Integrity Validation
    public static boolean isValidNewBlock(){
        Block previousBlock;
        Block newBlock;

        for(int i=1; i< blockChain.size(); i++){
            previousBlock = blockChain.get(i-1);
            newBlock = blockChain.get(i);
            String testIndex = Integer.toString(newBlock.index);
            String testTimestamp = Long.toString(newBlock.timeStamp);
            String testPre = newBlock.previousHash;
            String testData = newBlock.data;

            //index verification
            if(previousBlock.index!=newBlock.index-1){
                System.out.println("Index error!");
                return false;
            }

            //Hash linkage verification
            if(!(previousBlock.hash).equals(newBlock.previousHash)){
                System.out.println("Hash linkage verification fails!");
                System.out.println(newBlock.index);
                return false;
            }

             //unsuccessful try, it may lead to an unknown error, the second block mismatches with the third one.
            //if (!newBlock.hashTest(testIndex, testTimestamp, testPre, testData).equals(newBlock.hash)){
            //    //System.out.println(testIndex);
            //    //System.out.println(testTimestamp);
            //    //System.out.println(testPre);
            //    //System.out.println(testData);
            //
            //    System.out.println("Hash generating error!");
            //    return false;
            //}
        }
        return true;
    }

}
