package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InfrastructureDepartment {
	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	private	  ArrayList<Billboard> billboards;

	public InfrastructureDepartment() throws ClassNotFoundException, IOException {
		billboards = new ArrayList<Billboard>();
		loadBillboards();
	}
	
	public void addBillboard(double w, double h, boolean iU, String b) throws FileNotFoundException, IOException {
		Billboard billboard = new Billboard(w,h,iU,b); 
		billboards.add(billboard);
		saveBillboards();
	}
	
	private void saveBillboards() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_FILE_NAME));
		oos.writeObject(billboards);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	private void loadBillboards() throws IOException, ClassNotFoundException {
		File f = new File(BILLBOARD_FILE_NAME);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			billboards = (ArrayList<Billboard>) ois.readObject();
			ois.close();
		}
	}
	
	public String exportDangerousBillboardReport(String fn) throws IOException {
		int count = 1;
		String message = "===========================\n"
						+ "DANGEROUS BILLBOARD REPORT\n"
						+ "===========================\n"
						+ "The dangerous billboard are:\n";
		FileWriter fw = new FileWriter(fn, true);
		for(int i=0; i<billboards.size();i++) {
			Billboard bd = billboards.get(i);
			if(bd.calculateArea(bd.getHeight(), bd.getWidth()) >= 160) {
				fw.write(bd.getWidth()+";"+bd.getHeight()+";"+bd.getInUse()+";"+bd.getBrand()+"\n");
				message += count +". Billboard "+bd.getBrand()+" with area "+bd.calculateArea(bd.getHeight(), bd.getWidth())+"\n";
				count++;
			}
		}
		fw.close();
		return message;
	}
	
	public void importData(String fn) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fn));
		String line = br.readLine();
		line = br.readLine();
		while(line != null) {
			String[] parts = line.split("\\|");
			double w = Double.parseDouble(parts[0]);
			double h = Double.parseDouble(parts[1]);
			boolean iU = Boolean.parseBoolean(parts[2]);
			Billboard billboard = new Billboard(w,h,iU,parts[3]); 
			billboards.add(billboard);
			line = br.readLine();
		}
		br.close();
	}
	
	public String toString() {
		String show = "";
		show = "W      H      InUse      Brand\n";
		for(int i=0; i<billboards.size(); i++) {
			show += billboards.get(i).getWidth()+"   "+billboards.get(i).getHeight()+"    "+billboards.get(i).getInUse()+"       "+billboards.get(i).getBrand()+"\n";
		}
		show += "\nTOTAL: " + billboards.size() + " billboards";
		return show;
	}
}