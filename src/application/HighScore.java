package application;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class HighScore {
	private String filePath;
	
	public HighScore(String filePath) {
		this.filePath = filePath;
	}
	
	public Integer getHighScore() {
		Integer hs = -1;
		try {
			FileReader fr = new FileReader(this.filePath);
			hs = (Integer) fr.read();
			fr.close();
		} catch(Exception ex) {
			ex.getStackTrace();
		}
		return hs;
	}
	
	public void setHighScore(Integer hs) {
		try {
			FileWriter fw = new FileWriter(this.filePath);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(hs);
			out.close();
			fw.close();
		} catch(Exception ex) {
			ex.getStackTrace();
		}
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

