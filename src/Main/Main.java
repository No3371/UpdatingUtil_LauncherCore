/*
 
 */

package Main;

import java.io.*;
import java.net.*;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Main{
	static float[] verCmp = { 0, 0 };
	static String patchNoS;

	private static void init() throws MalformedURLException {
		Downloader.versionUrl = new URL(
				"https://dl.dropboxusercontent.com/u/132679455/Client/Version");
		Downloader.verFN = "Version";
		Downloader.FileListUrl = new URL(
				"https://dl.dropboxusercontent.com/u/132679455/Client/FileList");
		Downloader.PatchLogUrl = new URL(
				"https://dl.dropboxusercontent.com/u/132679455/Client/patchlog.txt");
		Downloader.dropboxUrlStr = "https://dl.dropboxusercontent.com/u/132679455/Client";

	}
	
	public static void userSet() throws IOException{		
		
		if(new File("Launch.vbe").exists())	return;
		
		String Pname = JOptionPane.showInputDialog("�п�J�A������W�١G", "�^��μƦr");
		
		int Ram;
		switch(System.getProperty("sun.arch.data.model")){
		case "32":
			Ram = 1024;	break;
		case "64":
			Ram = 2048; break;
		default:
			Ram = 1024; break;
		}
		
		if(Pname != null && Pname.length() > 0){
			 while(!Pname.matches("\\w+")){
					Pname = JOptionPane.showInputDialog("�п�J�A������W�١G", "�^��μƦr");
			 }
		FileWriter fw = new FileWriter("Launch.vbe");
		fw.write(String.format("set a=wscript.createobject(\"wscript.shell\")\na.run(\"MCLauncherBN.exe 1.7.10-Forge10.13.2.1307-1.7.10*%s*%d*-Dfml.ignoreInvalidMinecraftCertificates=true -Dfml.ignorePatchDiscrepancies=true\")", Pname, Ram));
		fw.close();
		Runtime.getRuntime().exec("wscript.exe Launch.vbe");
		}
		
	}

	public static void main(String[] args) throws IOException {
		init();
		new Frameset();
		try {
			if(checkconnection() == 0) System.exit(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			versioncheck();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userSet();
		
		return;
	}

	public static void versioncheck() throws IOException, InterruptedException {
		File lver = new File("Version");
		if (lver.exists() == true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					Downloader.versionUrl.openStream()));
			String str = null;
			String[] tstr = new String[5];

			if ((str = br.readLine()) != null) {
				str = str.replace(".", " ");
				tstr = str.split("\\s");
			}

			String Vstr = tstr[0];
			String MVersion = tstr[1], SVersion = tstr[2];
			Frameset.Text_message.append("\n��s�N���G" + Vstr + "\n�u�W�����G"
					+ MVersion + "." + SVersion);
			verCmp[0] = Float.parseFloat((MVersion + SVersion));

			br.close();

			FileReader localVer = new FileReader("Version");
			br = new BufferedReader(localVer);

			if ((str = br.readLine()) != null) {
				str = str.replace(".", " ");
				tstr = str.split("\\s");
			}

			Vstr = tstr[0];
			MVersion = tstr[1];
			SVersion = tstr[2];
			patchNoS = tstr[3];
			Frameset.Text_message.append("\n�w�˪����G" + MVersion + "." + SVersion);
			verCmp[1] = Float.parseFloat((MVersion + SVersion));

			br.close();

			if (verCmp[0] > verCmp[1]) {
				Frameset.Text_message.append("\n�˴����s�ɮסA�����s�C");
				// Frameset.Btn_start.setEnabled(true);
				Thread.sleep(1000);
				Downloader.updatingQueue();
				return;
			} else if (verCmp[0] == verCmp[1]) {
				Frameset.Text_message.append("\n���a�������ݭn�i���s�A�Y�N�������{���C");
				Thread.sleep(5000);
			} else if (verCmp[0] < verCmp[1]) {
				Frameset.Text_message.append("\n������T���~�A�Ϊ̬O�u�W�����w���]�A�вM�Ÿ�Ƨ��᭫�}��s�{���C");
				FileManager.delete("Version");
				Thread.sleep(1000);
				Downloader.fullupdate();
				return;
			}
		} else if (lver.exists() == false) {
			Frameset.Text_message.append("\n���o�{������T�A�P�w���C�����w�ˡA�Y�N�۰ʦw�ˡC");
			Thread.sleep(1000);
			Downloader.fullupdate();
			return;
		}

	}

	public static int checkconnection() throws IOException,
			InterruptedException {
		Process p1 = java.lang.Runtime.getRuntime().exec(
				"ping -n 1 www.dropbox.com");
		int returnVal = p1.waitFor();
		boolean reachable = (returnVal == 0);
		if (reachable == false) {
			Frameset.Text_message.setText("�L�k�s��Dropbox���A���C");
			Frameset.Text_message.append("\n�нT�{�����s�u�A�{���Y�N�۰������C");
			Thread.sleep(5000);

			return 0;

		} else if (reachable == true) {
			Frameset.Text_message.setText("�w�s�WDropbox���A���C");
			return 1;
		} else {
			Frameset.Text_message.setText("�s�u���p�����C");
			Frameset.Text_message.append("\n�нT�{�����s�u�A�{���Y�N�۰������C");
			Thread.sleep(5000);
			return 0;
		}
	}

}
