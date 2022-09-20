import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Commit {
	private String parent;
	private String other;
	private String pTree;
	private String author;
	private String date;
	private String summary;
	public Commit(String tree,String summ,String auth,String prent){
		pTree = tree;
		parent = prent;
		summary = summ;
		author = auth;
	}
	public String genSha(File f) throws IOException, NoSuchAlgorithmException {
		BufferedReader r = new BufferedReader(new FileReader(f));
		ArrayList<String> strs = new ArrayList<String>();
		while (r.ready()) {
			strs.add(r.readLine());

		}
		r.close();
		String s ="";
		for (int i =0 ; i<4;i++) {
			if (strs.size()>i) {
				s+=strs.get(strs.size()-i);
			}
			
		}
		date = strs.get(strs.size()-1);
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		 
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        byte[] messageDigest = md.digest(s.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value 
        String hashtext = no.toString(16);
        return hashtext;
	}
	public String getDate() {
		return date;
	}
	public void writeNew() throws NoSuchAlgorithmException {
		String s = pTree;
		s+="\n";
		s+=parent;
		s+="\n";
		s+=other;
		s+="\n";
		s+=author;
		s+="\n";
		s+=date;
		s+="\n";
		s+=summary;
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		 
        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        byte[] messageDigest = md.digest(s.getBytes());

        // Convert byte array into signum representation
        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value 
        String hashtext = no.toString(16);
        File f = new File("objects\\"+hashtext);
	}
	
	
}
