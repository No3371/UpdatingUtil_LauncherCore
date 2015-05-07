/*
 FileManager類別
 由Downlaoder獲取線上檔案清單
 多餘檔案先行刪除
 再由Downloader直接下載缺失檔案
 檔案校驗功能（？
 */

package Main;

import java.io.*;
/*
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
*/
public class FileManager {
/*
	public static void deCompress(ZipFile zIn) {
		try {
			final int BUFFER = 2048;
			BufferedOutputStream dest = null;
			// FileInputStream fis = new FileInputStream(file);
			// CheckedInputStream checksum = new CheckedInputStream(fis,
			// new Adler32());
			// ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
			// checksum));
			File file = new File(Downloader.fullFN);
			ZipFile zipFile = new ZipFile(file);
			zipFile.getEntries();
			java.util.Enumeration e = zipFile.getEntries();

			ZipEntry entry;

			InputStream in = null;
			while (e.hasMoreElements()) {
				entry = (ZipEntry) e.nextElement();
				log("Extracting: " + entry);
				int count;
				byte[] data = new byte[BUFFER];
				log("unzip to " + getFileName(file.getPath()));

				FileOutputStream fos = new FileOutputStream(
						getFileName(file.getPath()) + File.separator
								+ newDir(file, entry.getName()));

				in = new BufferedInputStream(zipFile.getInputStream(entry));
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = in.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
			}
			in.close();
			// System.out
			// .println("Checksum: " + checksum.getChecksum().getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	public static void delete(String dfile) {
		File d = new File(dfile);
		if(d.exists()){
			Frameset.Text_message.append("\n刪除 " + dfile);
			d.delete();
		}
	}
	
	public static void resetter(String main, String sub, String no) throws IOException{
		FileWriter fw = new FileWriter("Version");
		fw.write(String.format("%s %s.%s.%s", Main.Vstr, main, sub, no));
		fw.close();
	}
	
	public static void log(String str){
		
	}
	/*
	public static void checkSum(String fn) throws NoSuchAlgorithmException, IOException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		try (InputStream is = Files.newInputStream(Paths.get(fn))) {
			  DigestInputStream dis = new DigestInputStream(is, md);
			  // Read stream to EOF as normal...
			}
			byte[] digest = md.digest();
			
	}
	*/
}
