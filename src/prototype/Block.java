package prototype;

import java.util.Date;

//Block Structure
public class Block {
    //The height of block, start from 0
    public int index;
    //UnixTime
    public long timeStamp;
    public String hash;
    //Encrypted via Algo SHA-256
    public String previousHash;
    //Any data, including Txs stored here
    public String data;

    //Block constructor
    public Block(String data, String previousHash, int index){
        this.index = index;
        this.timeStamp = new Date().getTime()/1000;
        this.hash = getHash();
        this.previousHash = previousHash;
        this.data = data;
    }

    //Block Hash
    public String getHash(){
        String value = Sha256Util.applySha256(
                Integer.toString(index)+ Long.toString(timeStamp)
                + previousHash + data
        );
        return value;
    }

    //For validation test
    public String hashTest(String index, String timeStamp, String previousHash, String data){
        return hash = Sha256Util.applySha256(index+ timeStamp+ previousHash+ data);
    }
}
