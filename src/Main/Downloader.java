/*
 Downloader���O
 ���t�D�n�U����kdownload()
 �N�ϥΪ̪������ɮײM��P�u�W�ɮײM��i����
 �o�X�ݧR���λݤU���M��
 �ݧR���M��ǹFFileManager��delete��k�i��R��
 �ݤU���M��i��U��
 */

package Main;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

/* �@��Dropbox��Ƨ����]�tVersion�BFileList�BPatchList
 * 
 **/

public class Downloader {
	static URL versionUrl;
	static String dropboxUrlStr;
	static URL CurrentDL;
	static URL FileListUrl;
	static URL PatchLogUrl;
	static String CurrentDLFN;
	static String verFN;
	static int debugmode = 0;
	
	static ArrayList<String> queue = new ArrayList<String>();
	
	public static void debug(String strrr){
		if(debugmode == 1) Frameset.Text_message.append("\n" + strrr);
	}

	public static void updatingQueue() throws IOException{
		
		int[] Ver = {0, 0}, Date = {0, 0}, No = {0, 0};
		Ver[0] = Integer.parseInt(Main.MVersion + Main.SVersion);
		Date[0] = Integer.parseInt(Main.patchNoS.substring(0, 6));
		No[0] = Integer.parseInt(Main.patchNoS.substring(6, 9));
		Scanner scanner;
		debug("" + Ver[0] + Date[0] + No[0]);
		
		String str;
		BufferedReader FLO = new BufferedReader(new InputStreamReader(PatchLogUrl.openStream()));
		while((str = FLO.readLine()) != null){
			if(str.startsWith("No.")) str = str.substring(3);
			scanner = new Scanner(str);
			debug("read: " + str);
			
			String PatchNo = scanner.next();
			Ver[1] = Integer.parseInt(PatchNo.substring(0, 3));
			Date[1] = Integer.parseInt(PatchNo.substring(3, 9));
			No[1] = Integer.parseInt(PatchNo.substring(9, 12));
			
			if(Ver[1] <= Ver[0]){
				if(Date[1] <= Date[0]){
					if(No[1] <= No[0]){
						break;
					}
				}
			}
			
			String Type = scanner.next();
			scanner.useDelimiter("\\z");
			String FN = scanner.next();
			while(FN.startsWith(" ")){
				System.out.println("Space catched.");
				FN = FN.substring(1);
			}
			System.out.println(FN);
			if(Type.contains("�ɮקR���G") || Type.contains("�ɮק�s�G")){
				FileManager.delete(FN);
				debug("Delete: " + FN);
			}
			if(Type.contains("�ɮק�s�G") || Type.contains("�ɮ׷s�W�G")){
				queue.add(FN);
				debug("Queue: " + FN);
			}
			scanner.close();
			
		}

		fetchfiles();
		return;
	}
	
	public static void fetchfiles() throws IOException{	
		Frameset.Pbar.setMaximum(queue.size());
		Frameset.Text_message.append("\n�Y�N�}�l�U��" + queue.size() + "���ɮסC");
		for(int i = 0; i < queue.size(); i++){
			String CurrentDLFN = queue.get(i);
			//Replace URL seperators with file seperators.
			String CurrentDLUrl = CurrentDLFN.replace("\\", "/");
			CurrentDL = new URL(dropboxUrlStr + "/" + urlEncoder(CurrentDLUrl));
			download(CurrentDLFN, CurrentDL);
			Frameset.Pbar.setValue(Frameset.Pbar.getValue() + 1);
		}

		FileManager.delete("Version");
		download("Version", versionUrl); //��s���a�����ɮ�	
		
		Frameset.Text_message.append("\n��s�����C");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
}
	
	
	public static void fullupdate() throws IOException{
		String str;
		BufferedReader FLO = new BufferedReader(new InputStreamReader(FileListUrl.openStream()));
		while((str = FLO.readLine()) != null){
			if(str.startsWith("@u@")) str = str.substring(3);
			queue.add(str);
		}
		
		fetchfiles();
		
		return;
	}

	public static String urlEncoder(String in){		
		try {
			in = URLEncoder.encode(in, "UTF-8").replace("+", "%20");
			in = in.replace("%5C", "/");
			in = in.replace("%2F", "/");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
		
	}

	public static void download(String fn, URL url, int msg) throws IOException{
		if(fn.contains("\\")){
			new File(fn).getParentFile().mkdirs();
		}
		//
		if(msg != 0) Frameset.Text_message.append("\n�U��" + fn + "...");
		InputStream in;
		try {
			in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[4096];
		int n = 0;
		while((n = in.read(buf)) != -1){
			out.write(buf, 0, n);
		}
			out.close();
		in.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos;
			fos = new FileOutputStream(fn);
		fos.write(response);
		fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
	}
	
	public static void download(String fn, URL url) throws IOException{
		if(fn.contains("\\")){
			new File(fn).getParentFile().mkdirs();
		}
		//
		Frameset.Text_message.append("\n�U��" + fn + "...");
		InputStream in;
		try {
			in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[4096];
		int n = 0;
		while((n = in.read(buf)) != -1){
			out.write(buf, 0, n);
		}
			out.close();
		in.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos;
			fos = new FileOutputStream(fn);
		fos.write(response);
		fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
	}
}
