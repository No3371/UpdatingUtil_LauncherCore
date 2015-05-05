package Main;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class Frameset{
	static JTextArea Text_message;
	static JScrollPane Tscroll;
	static String fileName;
	static JButton Btn_start;
	static JFrame mainFrame;
	static JCheckBox Cbox_renew;
	static JProgressBar Pbar;
	
	public Frameset(){
	mainFrame = new JFrame();
	mainFrame.setSize(640, 270);
	mainFrame.setLayout(new GridBagLayout());
	mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
	mainFrame.setLocationRelativeTo(null);
	
	Text_message = new JTextArea("");
	GridBagConstraints GBC_Text_message = new GridBagConstraints();
	GBC_Text_message.gridx = 0; GBC_Text_message.gridy = 1;
	GBC_Text_message.weightx = 1; GBC_Text_message.weighty = 1;
	GBC_Text_message.gridwidth = 6; GBC_Text_message.gridheight = 8;
	GBC_Text_message.fill = GridBagConstraints.BOTH;
	GBC_Text_message.anchor = GridBagConstraints.CENTER;
	Text_message.setEditable(false);
	DefaultCaret caret = (DefaultCaret)Text_message.getCaret();
	caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	Font f = new Font(null, 0, 12);
	Text_message.setFont(f);	
	
	Tscroll = new JScrollPane(Text_message);
	mainFrame.add(Tscroll, GBC_Text_message);
	/*
	Btn_start = new JButton("執行更新");
	GridBagConstraints GBC_Btn_start = new GridBagConstraints();
	GBC_Btn_start.gridx = 1; GBC_Btn_start.gridy = 8;
	GBC_Btn_start.weightx = 1; GBC_Btn_start.weighty = 0;
	GBC_Btn_start.gridwidth = 4; GBC_Btn_start.gridheight = 1;
	GBC_Btn_start.fill = GridBagConstraints.BOTH;
	GBC_Btn_start.anchor = GridBagConstraints.CENTER;
	mainFrame.add(Btn_start, GBC_Btn_start);
	Btn_start.setEnabled(false);
	Btn_start.setActionCommand("start");
	Btn_start.addActionListener(this);
	
	JCheckBox Cbox_renew = new JCheckBox("全新安裝");
	GridBagConstraints GBC_Cbox_renew = new GridBagConstraints();
	GBC_Cbox_renew.gridx = 5; GBC_Cbox_renew.gridy = 0;
	GBC_Cbox_renew.weightx = 0; GBC_Cbox_renew.weighty = 0;
	GBC_Cbox_renew.gridwidth = 1; GBC_Cbox_renew.gridheight = 1;
	GBC_Cbox_renew.fill = GridBagConstraints.BOTH;
	GBC_Cbox_renew.anchor = GridBagConstraints.EAST;
	mainFrame.add(Cbox_renew, GBC_Cbox_renew);
	*/
	JLabel L_Name = new JLabel("通用更新程式");
	GridBagConstraints GBC_L_Name = new GridBagConstraints();
	GBC_L_Name.gridx = 0; GBC_L_Name.gridy = 0;
	GBC_L_Name.gridwidth = 6; GBC_L_Name.gridheight = 1;
	GBC_L_Name.fill = GridBagConstraints.BOTH;
	GBC_L_Name.anchor = GridBagConstraints.CENTER;
	mainFrame.add(L_Name, GBC_L_Name);
	
	Pbar = new JProgressBar();
	Pbar.setStringPainted(true);
	Pbar.setMinimum(0);
	Pbar.setBorderPainted(true);
	GridBagConstraints GBC_Pbar = new GridBagConstraints();
	GBC_Pbar.gridx = 0; GBC_Pbar.gridy = 9;
	GBC_Pbar.gridwidth = 6; GBC_Pbar.gridheight = 1;
	GBC_Pbar.fill = GridBagConstraints.BOTH;
	GBC_Pbar.anchor = GridBagConstraints.CENTER;
	mainFrame.add(Pbar, GBC_Pbar);
	
	mainFrame.setVisible(true);
	}
/*
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "start"){
			if(Cbox_renew.isSelected() == false){
			Text_message.append("\n\n開始進行更新，請等候直到更新完成。");
			try {
				Downloader.fetchfiles();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else if(Cbox_renew.isSelected() == true){
				new ConfirmFullUpdate();
			}
		}
		
	}
	*/
}
/*
class ConfirmFullUpdate implements ActionListener{
	JDialog conf;
	JButton Yes = new JButton("確定");
	JButton No = new JButton("取消");
	
	public ConfirmFullUpdate() {
		conf = new JDialog(Frameset.mainFrame, "");
		conf.setSize(320, 180);
		conf.add(new JLabel("選擇全新安裝將會下載完整的遊戲程式，需要較多時間進行下載，確定嗎？"));
		conf.add(Yes);
		conf.setModal(true);
		conf.setResizable(false);
		Yes.setActionCommand("y");
		Yes.addActionListener(this);
		conf.add(No);
		No.setActionCommand("n");
		No.addActionListener(this);
		conf.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd == "y"){
			try {
				Downloader.fullupdate();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conf.dispose();
		}else if(cmd =="n"){
			Frameset.Text_message.append("\n更新取消。");
			conf.dispose();
		}
	}
}
*/
