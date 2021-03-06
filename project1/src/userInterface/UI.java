package userInterface;
/**
 *
 * @author Yukang Li
 * 
 */
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JFileChooser;

//import org.omg.CORBA.PRIVATE_MEMBER;

import Components.CC;
import Components.IR;
import Components.IX;
import Components.MAR;
import Components.MBR;
import Components.MFR;
import Components.PC;
import Components.Register;
import Components.Cache;
import Components.Devid;
import function.*;

import javax.imageio.stream.MemoryCacheImageInputStream;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class UI {
	
	public InitialData iData;
	public static int p1Result;
	public static int keyboardFlag;
	public static int memory[] = new int[4096];
	public static String[] memory2 = new String[4096];
	//0 MAR / 1 PC / 2 MB / 3 MFR / 4 IR / 5 CC / 6 R0
	//7 R1 / 8 R2 / 9 R3 / 10 IX1 / 11 IX2 / 12 IX3
	public static int NewValue[] = new int[20];
	public static int OldValue[] = new int[20];
	public static Devid Devids[] = new Devid[32];
	public static int MAR_index=0;
	public static int PC_index=1;
	public static int MBR_index=2;
	public static int MFR_index=3;
	public static int IR_index=4;
	public static int CC_index=5;
	public static int R0_index=6;
	public static int R1_index=7;
	public static int R2_index=8;
	public static int R3_index=9;
	public static int IX1_index=10;
	public static int IX2_index=11;
	public static int IX3_index=12;
	public static int fr0_index=13;
	public static int fr1_index=14;
	private Tools tools = new Tools();

	public JFrame frame;
	public static JTextField MAR_textField;
	public static JTextField PC_textField;
	public static JTextField MBR_textField;
	public static JTextField MFR_textField;
	public static JTextField IR_textField;
	public static JTextField CC_textField;
	public static JTextField R0_textField;
	public static JTextField R1_textField;
	public static JTextField R2_textField;
	public static JTextField R3_textField;
	public static JTextField IX1_textField;
	public static JTextField IX2_textField;
	public static JTextField IX3_textField;
	public static JTextField Addr_textField;
	public static JTextField Value_textField;
	
	private JButton PCStore;
	private JButton MARStore;
	private JButton MBRStore;
	private JButton MFRStore;
	private JButton IRStore;
	private JButton CCStore;
	private JButton R0Store;
	private JButton R1Store;
	private JButton R2Store;
	private JButton R3Store;
	private JButton IX1Store;
	private JButton IX2Store;
	private JButton IX3Store;
	private JButton AddrQuery;
	private JButton AddrStore;
	private JButton RunInstr;
	private JButton IPL;
	private JButton Save;
	private JButton P1;
	private JButton btnChooseFile;
	private JButton P4;
	private JButton FR0Store;
	private JButton FR1Store;
	private static JButton KeyboardInput;
	
	public static PC pc = new PC();
	public static MAR mar = new MAR();
	public static MBR mbr = new MBR();
	public static MFR mfr = new MFR();
	public static IR ir = new IR(); 
	public static CC cc = new CC();
	public static Register[] r = new Register[4];
	public static IX[] ix = new IX[4];
	public static JTextArea Instr_textArea;
	public static JTextArea LogtextArea;
	public static Cache cache = new Cache();
	private JButton Singlestep;
	public static JTextField keyboardTextField;
	public static JTextArea printerTextArea;
	public static Register[] fr = new Register[2];
	private JTextField FR0_textField;
	private JTextField FR1_textField;
	private JTextArea pipelineTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Instr.FalseFloat2TureFloat(65);
		Instr.TrueFloat2FalseFloat(1.2539062);
		initialData();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI window = new UI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UI() {
		initialize();
	}
	
	/**
	 * Initial the data
	 */
	public static void initialData() {
		for (int i = 0; i < Devids.length; i++) {
			Devids[i] = new Devid();
		}
		for(int i=0;i<4;i++){
		       ix[i]=new IX();
		 }
		for(int j=0;j<4;j++) {
			r[j] = new Register();
		}
		fr[0] = new Register();
		fr[1] = new Register();
		pc.setValue(2001, PC_index);
		ix[1].setValue(100, IX1_index);
	    ix[2].setValue(1000, IX2_index);
	    ix[3].setValue(200, IX3_index);
	    //String[1000]='LDR ';
	    //program 2
	    memory[100] = 10;
	    memory[101] = 1000; // pointer sentences start from 1000
	    memory[102] = 0; // letter of S
	    memory[103]=1000; // pointer of the first letter in s
	    memory[104]=1000; // pointer of the first letter in s+1
	    memory[105] =0; // pointer of the letter in W

	    memory[126] = -1;
	    memory[127] = 0;
	    memory[128] = 1;// sentences number
	    memory[129] = 1; //word number
	    memory[130] =-2;//space
	    memory[131] =-3;//period
	    
	    memory[200] = 2029;
	    memory[201] = 2016;
	    memory[202] = 2029; 
	    memory[203] = 2029;
	    memory[204] = 2020;
	    memory[205] = 2029;
	    memory[206] = 2029;
	    memory[207] = 2005;
	    memory[208] = 2005;
	    memory[209] = 2029;
	    memory[210] = 2038;
	    memory[211] = 2061;
	    memory[212] = 2045;
	    memory[213] = 2038;
	    memory[214] = 2057;
	    memory[215] = 2034;
	    memory[216] = 2001;

	}
	
	
	public int f1(int address) {

		MAR_textField.setText(Integer.toString(address));
    	mar.setValue(address, MAR_index);
    	//value is the instr.
    	//int value = memory[address];
    	int value = cache.returnValue(address);
    	
    	//put the instr. to the mbr & ir
    	MBR_textField.setText(Integer.toString(value));
    	mbr.setValue(value, MBR_index);
    	IR_textField.setText(Integer.toString(value));
    	ir.setValue(value, IR_index);
    	pipelineTextArea.append(memory2[address] + " is decoding\n");
    	System.out.println(memory2[address] + " is decoding");
    	//UI.LogtextArea.append("Value:" + value);
    	return value;
	}
	public int f2(int instr, int address) {
		int rlt=0;
		rlt = analysisInstr(instr);
		pipelineTextArea.append(memory2[address] + " is executing\n");
		return rlt;
	}
	public void f3(int instr, int rlt, int address) {
		writeback(instr, rlt);
		pipelineTextArea.append(memory2[address] + " is writing back\n");
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100,1300, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MAR_textField = new JTextField();
		MAR_textField.setBounds(91, 80, 130, 26);
		frame.getContentPane().add(MAR_textField);
		MAR_textField.setColumns(10);
		MAR_textField.setText(Integer.toString(mar.getValue()));
		
		PC_textField = new JTextField();
		PC_textField.setColumns(10);
		PC_textField.setBounds(91, 118, 130, 26);
		frame.getContentPane().add(PC_textField);
		PC_textField.setText(Integer.toString(pc.getValue()));
		
		MBR_textField = new JTextField();
		MBR_textField.setColumns(10);
		MBR_textField.setBounds(91, 156, 130, 26);
		frame.getContentPane().add(MBR_textField);
		MBR_textField.setText(Integer.toString(mbr.getValue()));
		
		MFR_textField = new JTextField();
		MFR_textField.setColumns(10);
		MFR_textField.setBounds(91, 194, 130, 26);
		frame.getContentPane().add(MFR_textField);
		MFR_textField.setText(Integer.toString(mfr.getValue()));
		
		IR_textField = new JTextField();
		IR_textField.setColumns(10);
		IR_textField.setBounds(91, 232, 130, 26);
		frame.getContentPane().add(IR_textField);
		IR_textField.setText(Integer.toString(ir.getValue()));
		
		CC_textField = new JTextField();
		CC_textField.setText(" ");
		CC_textField.setColumns(10);
		CC_textField.setBounds(91, 270, 130, 26);
		frame.getContentPane().add(CC_textField);
		CC_textField.setText(Integer.toString(cc.getValue()));
		
		R0_textField = new JTextField();
		R0_textField.setColumns(10);
		R0_textField.setBounds(364, 80, 130, 26);
		frame.getContentPane().add(R0_textField);
		R0_textField.setText(Integer.toString(r[0].getValue()));
		
		R1_textField = new JTextField();
		R1_textField.setColumns(10);
		R1_textField.setBounds(364, 118, 130, 26);
		frame.getContentPane().add(R1_textField);
		R1_textField.setText(Integer.toString(r[1].getValue()));
		
		R2_textField = new JTextField();
		R2_textField.setColumns(10);
		R2_textField.setBounds(364, 156, 130, 26);
		frame.getContentPane().add(R2_textField);
		R2_textField.setText(Integer.toString(r[2].getValue()));
		
		R3_textField = new JTextField();
		R3_textField.setColumns(10);
		R3_textField.setBounds(364, 194, 130, 26);
		frame.getContentPane().add(R3_textField);
		R3_textField.setText(Integer.toString(r[3].getValue()));
		
		IX1_textField = new JTextField();
		IX1_textField.setColumns(10);
		IX1_textField.setBounds(364, 232, 130, 26);
		frame.getContentPane().add(IX1_textField);
		IX1_textField.setText(Integer.toString(ix[1].getValue()));
		
		IX2_textField = new JTextField();
		IX2_textField.setColumns(10);
		IX2_textField.setBounds(364, 270, 130, 26);
		frame.getContentPane().add(IX2_textField);
		IX2_textField.setText(Integer.toString(ix[2].getValue()));
		
		IX3_textField = new JTextField();
		IX3_textField.setColumns(10);
		IX3_textField.setBounds(364, 308, 130, 26);
		frame.getContentPane().add(IX3_textField);
		IX3_textField.setText(Integer.toString(ix[3].getValue()));
		
		JLabel lblMar = new JLabel("MAR");
		lblMar.setHorizontalAlignment(SwingConstants.CENTER);
		lblMar.setBounds(27, 85, 61, 16);
		frame.getContentPane().add(lblMar);
		
		JLabel lblNewLabel = new JLabel("PC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 123, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMbr = new JLabel("MBR");
		lblMbr.setHorizontalAlignment(SwingConstants.CENTER);
		lblMbr.setBounds(27, 161, 61, 16);
		frame.getContentPane().add(lblMbr);
		
		JLabel lblMfr = new JLabel("MFR");
		lblMfr.setHorizontalAlignment(SwingConstants.CENTER);
		lblMfr.setBounds(27, 199, 61, 16);
		frame.getContentPane().add(lblMfr);
		
		JLabel lblIr = new JLabel("IR");
		lblIr.setHorizontalAlignment(SwingConstants.CENTER);
		lblIr.setBounds(27, 237, 61, 16);
		frame.getContentPane().add(lblIr);
		
		JLabel lblCc = new JLabel("CC");
		lblCc.setHorizontalAlignment(SwingConstants.CENTER);
		lblCc.setBounds(27, 275, 61, 16);
		frame.getContentPane().add(lblCc);
		
		JLabel lblR = new JLabel("R0");
		lblR.setHorizontalAlignment(SwingConstants.CENTER);
		lblR.setBounds(313, 85, 61, 16);
		frame.getContentPane().add(lblR);
		
		JLabel lblR_1 = new JLabel("R1");
		lblR_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_1.setBounds(313, 123, 61, 16);
		frame.getContentPane().add(lblR_1);
		
		JLabel lblR_2 = new JLabel("R2");
		lblR_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_2.setBounds(313, 161, 61, 16);
		frame.getContentPane().add(lblR_2);
		
		JLabel lblR_3 = new JLabel("R3");
		lblR_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblR_3.setBounds(313, 199, 61, 16);
		frame.getContentPane().add(lblR_3);
		
		JLabel lblIxr = new JLabel("IX1");
		lblIxr.setHorizontalAlignment(SwingConstants.CENTER);
		lblIxr.setBounds(313, 237, 61, 16);
		frame.getContentPane().add(lblIxr);
		
		JLabel lblIxr_1 = new JLabel("IX2");
		lblIxr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblIxr_1.setBounds(313, 275, 61, 16);
		frame.getContentPane().add(lblIxr_1);
		
		JLabel lblIxr_2 = new JLabel("IX3");
		lblIxr_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblIxr_2.setBounds(313, 313, 61, 16);
		frame.getContentPane().add(lblIxr_2);
		
		
		
		RunInstr = new JButton("Run");
		RunInstr.setBounds(125, 435, 70, 29);
		frame.getContentPane().add(RunInstr);
		RunInstr.setEnabled(false);
		
		Addr_textField = new JTextField();
		Addr_textField.setColumns(10);
		Addr_textField.setBounds(91, 37, 130, 26);
		frame.getContentPane().add(Addr_textField);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setBounds(125, 20, 61, 16);
		frame.getContentPane().add(lblAddress);
		
		Value_textField = new JTextField();
		Value_textField.setColumns(10);
		Value_textField.setBounds(233, 37, 130, 26);
		frame.getContentPane().add(Value_textField);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblValue.setBounds(260, 20, 61, 16);
		frame.getContentPane().add(lblValue);
		
		AddrStore = new JButton("Store");
		AddrStore.setBounds(364, 37, 70, 29);
		frame.getContentPane().add(AddrStore);
		
		AddrQuery = new JButton("Query");
		AddrQuery.setBounds(441, 37, 70, 29);
		frame.getContentPane().add(AddrQuery);
		
		MARStore = new JButton("Store");
		MARStore.setBounds(214, 80, 70, 29);
		frame.getContentPane().add(MARStore);
		
		PCStore = new JButton("Store");
		PCStore.setBounds(214, 118, 70, 29);
		frame.getContentPane().add(PCStore);
		
		MBRStore = new JButton("Store");
		MBRStore.setBounds(214, 156, 70, 29);
		frame.getContentPane().add(MBRStore);
		
		MFRStore = new JButton("Store");
		MFRStore.setBounds(214, 194, 70, 29);
		frame.getContentPane().add(MFRStore);
		
		IRStore = new JButton("Store");
		IRStore.setBounds(214, 232, 70, 29);
		frame.getContentPane().add(IRStore);
		
		CCStore = new JButton("Store");
		CCStore.setBounds(214, 270, 70, 29);
		frame.getContentPane().add(CCStore);
		
		R0Store = new JButton("Store");
		R0Store.setBounds(490, 80, 70, 29);
		frame.getContentPane().add(R0Store);
		
		R1Store = new JButton("Store");
		R1Store.setBounds(490, 118, 70, 29);
		frame.getContentPane().add(R1Store);
		
		R2Store = new JButton("Store");
		R2Store.setBounds(490, 156, 70, 29);
		frame.getContentPane().add(R2Store);
		
		R3Store = new JButton("Store");
		R3Store.setBounds(490, 194, 70, 29);
		frame.getContentPane().add(R3Store);
		
		IX1Store = new JButton("Store");
		IX1Store.setBounds(490, 232, 70, 29);
		frame.getContentPane().add(IX1Store);
		
		IX2Store = new JButton("Store");
		IX2Store.setBounds(490, 270, 70, 29);
		frame.getContentPane().add(IX2Store);
		
		IX3Store = new JButton("Store");
		IX3Store.setBounds(490, 308, 70, 29);
		frame.getContentPane().add(IX3Store);
		
		JLabel lblInstruction = new JLabel("CardReader");
		lblInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstruction.setBounds(408, 354, 86, 16);
		frame.getContentPane().add(lblInstruction);
		
		
		LogtextArea = new JTextArea();
		LogtextArea.setText("");
		LogtextArea.setBounds(623, 85, 260, 251);
		frame.getContentPane().add(LogtextArea);
		
		JScrollPane logJScrollPane = new JScrollPane(LogtextArea);
		logJScrollPane.setBounds(623, 85, 260, 251);
		frame.getContentPane().add(logJScrollPane);
		
		
		JLabel lblLog = new JLabel("Log");
		lblLog.setHorizontalAlignment(SwingConstants.CENTER);
		lblLog.setBounds(720, 60, 61, 16);
		frame.getContentPane().add(lblLog);
		
		Instr_textArea = new JTextArea();
//		Instr_textArea.setText("LDR r0,00,0,11000\n"+"LDR r0,01,0,11000");
		Instr_textArea.setText("");
		Instr_textArea.setBounds(330, 372, 264, 170);
		frame.getContentPane().add(Instr_textArea);
		//set the textArea could not be edited before click IPL button
		Instr_textArea.setEditable(false);
		
		JScrollPane instrScrollPane = new JScrollPane(Instr_textArea);
		instrScrollPane.setBounds(330, 372, 264, 170);
		frame.getContentPane().add(instrScrollPane);
		
		IPL = new JButton("IPL");
		IPL.setBounds(125, 394, 70, 29);
		frame.getContentPane().add(IPL);
		
		Singlestep = new JButton("SingleStep");
		Singlestep.setBounds(201, 435, 99, 29);
		frame.getContentPane().add(Singlestep);
		Singlestep.setEnabled(false);
		
		Save = new JButton("Save");
		Save.setBounds(207, 394, 70, 29);
		frame.getContentPane().add(Save);
		Save.setEnabled(false);
		
		P1 = new JButton("P1");
		P1.setBounds(53, 394, 70, 29);
		frame.getContentPane().add(P1);
		P1.setEnabled(false);
		
		keyboardTextField = new JTextField();
		keyboardTextField.setBounds(623, 369, 256, 68);
		frame.getContentPane().add(keyboardTextField);
		keyboardTextField.setColumns(10);
		
		KeyboardInput = new JButton("input");
		KeyboardInput.setBounds(720, 449, 61, 29);
		frame.getContentPane().add(KeyboardInput);
		
		JLabel lblKeyboard = new JLabel("keyboard");
		lblKeyboard.setBounds(720, 354, 61, 16);
		frame.getContentPane().add(lblKeyboard);
		
		printerTextArea = new JTextArea();
		printerTextArea.setColumns(10);
		printerTextArea.setBounds(950, 85, 260, 251);
		frame.getContentPane().add(printerTextArea);
		
		JScrollPane printJScrollPane = new JScrollPane(printerTextArea);
		printJScrollPane.setBounds(950, 85, 260, 251);
		frame.getContentPane().add(printJScrollPane);
		
		JLabel lblPrinter = new JLabel("Printer");
		lblPrinter.setBounds(1054, 60, 61, 16);
		frame.getContentPane().add(lblPrinter);
		
		btnChooseFile = new JButton("P3");
		btnChooseFile.setBounds(53, 476, 117, 29);
		frame.getContentPane().add(btnChooseFile);
		btnChooseFile.setEnabled(false);
		
		JButton btnP2 = new JButton("P2");
		btnP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadFileP2 p2 = new ReadFileP2();
				p2.ReadP2();
				run();
				r[0].setValue(p1Result, R0_index);
				Instr.Refresh(NewValue, OldValue);
				printerTextArea.setText(Integer.toString(p1Result));
			}
		});
		
		btnP2.setBounds(53, 435, 73, 29);
		frame.getContentPane().add(btnP2);
		btnP2.setEnabled(false);
		
		P4 = new JButton("P4");
		P4.setBounds(186, 476, 70, 29);
		frame.getContentPane().add(P4);
		P4.setEnabled(false);
		
		FR0_textField = new JTextField();
		FR0_textField.setText("");
		FR0_textField.setColumns(10);
		FR0_textField.setBounds(91, 308, 130, 26);
		frame.getContentPane().add(FR0_textField);
		
		FR1_textField = new JTextField();
		FR1_textField.setText("");
		FR1_textField.setColumns(10);
		FR1_textField.setBounds(91, 349, 130, 26);
		frame.getContentPane().add(FR1_textField);
		
		FR0Store = new JButton("Store");
		FR0Store.setBounds(214, 308, 70, 29);
		frame.getContentPane().add(FR0Store);
		
		FR1Store = new JButton("Store");
		FR1Store.setBounds(214, 349, 70, 29);
		frame.getContentPane().add(FR1Store);
		
		JLabel lblFr = new JLabel("FR0");
		lblFr.setHorizontalAlignment(SwingConstants.CENTER);
		lblFr.setBounds(27, 313, 61, 16);
		frame.getContentPane().add(lblFr);
		
		JLabel lblFr_1 = new JLabel("FR1");
		lblFr_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblFr_1.setBounds(27, 354, 61, 16);
		frame.getContentPane().add(lblFr_1);
		
		pipelineTextArea = new JTextArea();
		pipelineTextArea.setText("");
		pipelineTextArea.setBounds(950, 368, 260, 185);
		frame.getContentPane().add(pipelineTextArea);
		
		JScrollPane pipelineJScrollPane = new JScrollPane(pipelineTextArea);
		pipelineJScrollPane.setBounds(950, 368, 260, 185);
		frame.getContentPane().add(pipelineJScrollPane);
		
		JLabel lblPipeline = new JLabel("pipeline");
		lblPipeline.setHorizontalAlignment(SwingConstants.CENTER);
		lblPipeline.setBounds(1054, 348, 61, 16);
		frame.getContentPane().add(lblPipeline);
		
		btnChooseFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				OpenFile of = new OpenFile();
				UI.Instr_textArea.setText("LDR r0,01,0,00000\n" + 
						"STR r0,01,0,00101\n" + 
						"LDR r0,01,0,00100\n" + 
						"STR r0,01,0,00011\n" + 
						"LDR r0,01,1,00101\n" + 
						"JGE r0,11,1,00001\n" + 
						"LDR r1,01,1,00011\n" + 
						"STR r1,01,0,00010\n" + 
						"LDR r0,01,0,00010\n" + 
						"JGE r0,11,1,00010\n" + 
						"LDR r1,01,0,11100\n" + 
						"OUT r1,000,00001\n" + 
						"LDR r1,01,0,11101\n" + 
						"OUT r1,000,00001\n" + 
						"JMA r0,11,1,00011\n" + 
						"LDR r1,01,1,00011\n" + 
						"STR r1,01,0,00010\n" + 
						"JGE r0,11,1,00100\n" + 
						"JMA r0,11,1,00101\n" + 
						"SMR r0,01,0,00010\n" + 
						"JNE r0,11,1,00110\n" + 
						"LDR r0,01,0,00101\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,00101\n" + 
						"LDR r0,01,0,00011\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,00011\n" + 
						"JMA r0,11,1,01000\n" + 
						"LDR r0,01,0,00100\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,00100\n" + 
						"LDR r0,01,1,00100\n" + 
						"JGE r0,11,1,01001\n" + 
						"LDR r0,01,1,00100\n" + 
						"SMR r0,01,0,11010\n" + 
						"JNE r0,11,1,01010\n" + 
						"JMA r0,11,1,01011\n" + 
						"LDR r0,01,1,00100\n" + 
						"SMR r0,01,0,11110\n" + 
						"JNE r0,11,1,01100\n" + 
						"LDR r0,01,0,00100\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,00100\n" + 
						"JMA r0,11,1,01101\n" + 
						"LDR r0,01,1,00100\n" + 
						"SMR r0,01,0,11111\n" + 
						"JNE r0,11,1,01110\n" + 
						"LDR r0,01,0,11100\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,11100\n" + 
						"LDR r0,01,0,11011\n" + 
						"STR r0,01,0,11101\n" + 
						"LDR r0,01,0,00100\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,00100\n" + 
						"JMA r0,11,1,01111\n" + 
						"LDR r0,01,0,11101\n" + 
						"AIR r0,00000001\n" + 
						"STR r0,01,0,11101\n" + 
						"JMA r0,11,1,10000\n" + 
						"HLT r0,00,0,00000\n");
				// store instructions into memory
				//get the address to store the first instruction 
		    	String PCAddress = PC_textField.getText();
		    	//get the instr.s in the textarea
		    	String instrs = Instr_textArea.getText();
		    	
		    	//split the instr.s and store them into the memory
		    	StoreInstr(instrs, PCAddress);
				try {
					of.PickMe();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		P1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.Instr_textArea.setText("LDR r0,00,0,11000\n" + 
						"LDR r0,01,0,11000\n" + 
						"LDR r1,00,0,11000\n" + 
						"LDR r2,10,0,11000\n" + 
						"STR r1,00,0,11000\n" + 
						"STR r2,01,0,11000\n" + 
						"STR r3,00,1,11000\n" + 
						"STR r3,11,1,11001 \n" + 
						"LDA r1,00,0,11010\n" + 
						"LDA r2,01,0,11001 \n" + 
						"LDA r3,00,1,11001\n" + 
						"LDA r3,11,1,11001 \n" + 
						"LDX r1,10,0,11010\n" + 
						"LDX r1,11,1,11010 \n" + 
						"STX r1,10,0,11011\n" + 
						"STX r2,11,1,11100 \n" + 
						"HLT r0,00,0,00000\n" + 
						"");
				String PCAddress = PC_textField.getText();
		    	//get the instr.s in the textarea
		    	String instrs = Instr_textArea.getText();
		    	
		    	//split the instr.s and store them into the memory
		    	StoreInstr(instrs, PCAddress);
			}
		});
		
		
		P4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UI.Instr_textArea.setText("FADD r1,00,0,00000\n" + 
						"FADD r0,00,1,00001\n" + 
						"FSUB r0,00,0,00010\n" + 
						"FSUB r1,00,1,00001\n" + 
						"LDFR r0,00,0,00110\n" + 
						"VADD r0,00,0,00011\n" + 
						"VSUB r0,00,0,00011\n" + 
						"LDFR r0,00,0,00010\n" + 
						"CNVRT r0,00,0,00000\n" + 
						"LDFR r0,00,0,00001\n");
				
				// store instructions into memory
				//get the address to store the first instruction 
		    	String PCAddress = PC_textField.getText();
		    	//get the instr.s in the textarea
		    	String instrs = Instr_textArea.getText();
		    	
		    	//split the instr.s and store them into the memory
		    	StoreInstr(instrs, PCAddress);
				//set initial data
				InitialData p4 = new InitialData();
				p4.InitialP4();
				
		    	
				//start p4 running process
				int head=pc.getValue();
				int tail = head+9;
				
				int p1;
				
				int p2;
				
				int p3;
				
				int instr = 0;
				int rlt = 0;
				for (int i=head;i<=tail;i++){
					p1 = i;
					p2 = i-1;
					p3 = i-2;
					
					if(p3>=head && p3<=tail) {
						f3(instr, rlt, p3);
					}
					if(p2>=head) {
						if(p3 > 2000) {
							String i2 = memory2[p2];
							String[] array2 = i2.split(" |,");
							String i3 = memory2[p3];
							String[] array3 = i3.split(" |,");
							if(array2[1].equals(array3[1])) {
								pipelineTextArea.append(memory2[p2] + " is bubbled\n" );
								pipelineTextArea.append(memory2[p1] + " is bubbled\n" );
								pipelineTextArea.append("------------------------------\n");
								rlt = f2(instr, p2);
								//i += 1;
							}else {
								rlt = f2(instr, p2);
							}
						}else {
							rlt = f2(instr, p2);
						}
						
					}
					if(p1>=head && p1<=tail) {
						instr = f1(p1);
					}
					pipelineTextArea.append("------------------------------\n");
				}
			}
		});

		
		PCStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//get the value from the TextField
				String PCContent = PC_textField.getText();
				//determine if the textField is blank;
				if(PCContent != null) {
					int value = Integer.parseInt(PCContent);
					//if it's not blank, store data to the PC
					pc.setValue(value,PC_index);
					//PC_textField.setText(Integer.toString(pc.getValue()));
				}
			}
		});
		
		MARStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String marString = MAR_textField.getText();
				if(marString != null) {
					int value = Integer.parseInt(marString);
					mar.setValue(value, MBR_index);
					MAR_textField.setText(Integer.toString(mar.getValue()));
				}
			}
		});
		
		MBRStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mbrString = MBR_textField.getText();
				if(mbrString != null) {
					int value = Integer.parseInt(mbrString);
					mbr.setValue(value, MBR_index);
					MBR_textField.setText(Integer.toString(mbr.getValue()));
				}
			}
		});
		
		MFRStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String mfrString = MFR_textField.getText();
				if(mfrString != null) {
					int value = Integer.parseInt(mfrString);
					mfr.setValue(value,MFR_index);
					MFR_textField.setText(Integer.toString(mfr.getValue()));
				}
			}
		});
		
		IRStore.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String irString = IR_textField.getText();
				if(irString != null) {
					int value = Integer.parseInt(irString);
					ir.setValue(value, IR_index);
					IR_textField.setText(Integer.toString(ir.getValue()));
				}
			}
		});
		
		CCStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ccString = CC_textField.getText();
				if(ccString != null) {
					int value = Integer.parseInt(ccString);
					cc.setValue(value,CC_index);
					CC_textField.setText(Integer.toString(cc.getValue()));
				}
			}
		});
		
		FR0Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fr0String = FR0_textField.getText();
				if(fr0String != null) {
					int value = Integer.parseInt(fr0String);
					fr[0].setValue(value, fr0_index);
					FR0_textField.setText(Integer.toString(fr[0].getValue()));
				}
				
			}
		});
		
		FR1Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fr1String = FR1_textField.getText();
				if(fr1String != null) {
					int value = Integer.parseInt(fr1String);
					fr[1].setValue(value, fr1_index);
					FR1_textField.setText(Integer.toString(fr[1].getValue()));
				}
				
			}
		});
		
		R0Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String r0String = R0_textField.getText();
				if(r0String != null) {
					int value = Integer.parseInt(r0String);
					r[0].setValue(value, R0_index);
					R0_textField.setText(Integer.toString(r[0].getValue()));
				}
			}
		});
		
		R1Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String r1String = R1_textField.getText();
				if(r1String != null) {
					int value = Integer.parseInt(r1String);
					r[1].setValue(value, R1_index);
					R1_textField.setText(Integer.toString(r[1].getValue()));
				}
			}
		});
		
		R2Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String r2String = R2_textField.getText();
				if(r2String != null) {
					int value = Integer.parseInt(r2String);
					r[2].setValue(value, R2_index);
					R2_textField.setText(Integer.toString(r[2].getValue()));
				}
			}
		});
		
		R3Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String r3String = R3_textField.getText();
				if(r3String != null) {
					int value = Integer.parseInt(r3String);
					r[3].setValue(value, R3_index);
					R3_textField.setText(Integer.toString(r[3].getValue()));
				}
			}
		});
		
		IX1Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ix1String = IX1_textField.getText();
				if(ix1String != null) {
					int value = Integer.parseInt(ix1String);
					ix[1].setValue(value, IX1_index);
					IX1_textField.setText(Integer.toString(ix[1].getValue()));
				}
			}
		});
		
		IX2Store.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String ix2String = IX2_textField.getText();
						if(ix2String != null) {
							int value = Integer.parseInt(ix2String);
							ix[2].setValue(value, IX2_index);
							IX2_textField.setText(Integer.toString(ix[2].getValue()));
						}
					}
				});
		
		IX3Store.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ix3String = IX3_textField.getText();
				if(ix3String != null) {
					int value = Integer.parseInt(ix3String);
					ix[3].setValue(value, IX3_index);
					IX3_textField.setText(Integer.toString(ix[3].getValue()));
				}
			}
		});
		
		AddrQuery.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String addrString = Addr_textField.getText();
				if(addrString != null) {
					int addr = Integer.parseInt(addrString);
					//int value = memory[addr];
					int value = cache.returnValue(addr);
					String valueString = Integer.toString(value);
					Value_textField.setText(valueString);
				}
			}
		});
		
		/**
		 * @author Yukang Li
		 * this store button mainly use to test the function
		 */
		AddrStore.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String addrString = Addr_textField.getText();
				String valueString  = Value_textField.getText();
				if(addrString != null && valueString != null) {
					int add = Integer.parseInt(addrString);
					int value = Integer.parseInt(valueString);
					//memory[add] = value;
					cache.setValue(add, value);
				}
			}
		});
		
		/**
		 * save the instruction in the instrTextArea
		 * then can implement the run and singleStep function
		 */
		Save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		    	//get the address to store the first instruction 
		    	String PCAddress = PC_textField.getText();
		    	//get the instr.s in the textarea
		    	String instrs = Instr_textArea.getText();
		    	
		    	//split the instr.s and store them into the memory
		    	StoreInstr(instrs, PCAddress);
			}
		});
		
		/**
		 * execute only one instruction shown in the pc -- sep 19
		 * 
		 * actually, the function of singleStep should can be clicked many times
		 * execute the instr.s one by one
		 */
		Singlestep.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				1. Get all instr. shown in the textArea and the address in [PC]
//				2. Store all instr. 
//				3. execute the first instr.
//				4. the value of PC should be plus one - [PC]+1
				String addString = PC_textField.getText();
				int add = Integer.parseInt(addString);
				singleExecute(add);
			}
		});
		
		/**
		 * when user click the IPL button, user would be allowed to type
		 * in the instruction area. 
		 */
		IPL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				P1.setEnabled(true);
				btnP2.setEnabled(true);
				btnChooseFile.setEnabled(true);
				P4.setEnabled(true);
				Save.setEnabled(true);
				RunInstr.setEnabled(true);
				Instr_textArea.setEditable(true);
				Singlestep.setEnabled(true);
			}
		});
		
		/**
		 * Run instr.s
		 */
		RunInstr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				run();
			}
		});
		
		KeyboardInput.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// get keyboard value, put it to keyboard(devid[0])
				// Devids[0].setValue(Integer.parseInt(keyboardTextField.getText()));
				String input = keyboardTextField.getText();
				String[] lines = input.split(" ");

				// if the length smaller than 2, means input the word
				if (lines.length < 2) {
					//store users input at index 10
					int i = 10;
					String value = lines[0];
					char[] chars = value.toCharArray();
			        //store each char into the memory
			        for (int j = 0; j < chars.length; j++) {
			        	if ((int)chars[j] <= 90 && (int)chars[j] >= 65) {
				        	System.out.println(i+" "+chars[j]);
				        	UI.memory[i++] = (int)chars[j]+32;	
			        	}
			        	else {
			        		System.out.println(i+" "+chars[j]);
				        	UI.memory[i++] = (int)chars[j];
			        	}
			        	
					}
			        UI.memory[i]= -1; 
				} else {
					for (int i = 0, add = 10; i < lines.length; i++, add++) {
						System.out.println("add " + add + " value " + lines[i]);
						String string = lines[i];
						UI.cache.setValue(add, Integer.parseInt(string));
					}
					// calculate the minimum number in the array
					int index = 0;
					int temp = Integer.parseInt(lines[20]) - Integer.parseInt(lines[0]);
					for (int i = 1; i < lines.length - 1; i++) {
						int diff = Integer.parseInt(lines[20]) - Integer.parseInt(lines[i]);
						if (diff < 0) {
							diff = diff * -1;
						}
						if (diff <= temp) {
							temp = diff;
							index = i;
						}
					}
					System.out.println(lines[index]);
					p1Result = Integer.parseInt(lines[index]);
					UI.r[0].setValue(Integer.parseInt(lines[20]), UI.R0_index);
//			    Instr.Refresh(UI.NewValue,UI.OldValue);
//				Instr.IN(0, 0);
//				Instr.OUT(0, 1);
					// change the keyboard flag
					// clear the keyboard Textfield

					//keyboardTextField.setText("");
				}
			}
		});
	}
	
	/**
	 * 2. get the address in the [PC] 
	 * 3. Put it to the [MAR] 
	 * 4. Get the instruction from memory 
	 * 5. Put the decimal instruction to [MBR] 
	 * 6. Put the instruction to [IR] 
	 * 	1. [IR] decode the instruction from Decimal to String 
	 * 	2. Get OpCode, R, IX, I, Address
	 * @author Yukang Li 
	 */
    public boolean singleExecute(int address) {

    	//get address from pc (Hexadecimal Format String)
    	//transfer hexadecimal to decimal in the next step
    	
    	//get the instr. from memory
    	//transfer hexadecimal to decimal
    	//iAddress means address in int format
    	//int iAddress = tools.strHexa2decimal(address);
    	//put it to mar
    	MAR_textField.setText(Integer.toString(address));
    	mar.setValue(address, MAR_index);
    	//value is the instr.
    	//int value = memory[address];
    	int value = cache.returnValue(address);
    	
    	//put the instr. to the mbr & ir
    	MBR_textField.setText(Integer.toString(value));
    	mbr.setValue(value, MBR_index);
    	IR_textField.setText(Integer.toString(value));
    	ir.setValue(value, IR_index);
    	//decode & execute the instr. 
    	int  i = analysisInstr(value);
    	if(i == 1) {
    		return false;
    	}
    	pc.setValue(pc.getValue()+1,PC_index);
    	PC_textField.setText(Integer.toString(pc.getValue()));
    	return true;
    }
    
    /**
     * Batch execution instr.s
     * @author Yukang Li
     */
    public void run() {
    	//get the address to store the first instruction 
    	String address = PC_textField.getText();
    	//convert the address to decimal, so that could be calculated
    	int iAddress = Integer.parseInt(address);
    	//execute the instr.s
    	Boolean status = true;
    	while (status) {
    		iAddress = pc.getValue();
			status = singleExecute(iAddress);
			//iAddress++;
		}
//    	System.out.println(printerTextArea.getText());
//    	if(printerTextArea.getText() == "") {
//    		printerTextArea.append(Integer.toString(p1Result));
//    	}
    }
    
    /**
     * analysis the instr. and use different instr. func.
     * @param instr
     * @author Yukang Li
     */
    public int analysisInstr(int instr) {
    	//once face the HLT instr. change the status to 1;
    	int status = 0;
    	//array contain opCode, register, indexRegister, indirect, address
    	int[] array = tools.decodeInstr(instr);
    	int opCode = array[0];
    	int r;
    	int ix;
    	int i;
    	int address;
    	int Rx;
    	int Ry;
    	int AL;
    	int LR;
    	int Count;
    	int DevID;
    	int cc;
    	int immed;
    	int trapCode;
    	switch (opCode) {
		case 1:
			//01 -- LDR
			//System.out.println(array[1]);
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDR(r, ix, i, address);
			break;
		case 2:
			//02 -- STR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STR(r, ix, i, address);
			break;
		case 3:
			//03 -- LDA
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDA(r, ix, i, address);
			break;
		case 41:
			//41 -- LDX
			ix = array[2];
			i = array[3];
			address = array[4];
			System.out.println("41 ix "+ix);
			System.out.println("41 i "+i);
			System.out.println("41 address "+address);
			Instr.LDX(ix, i, address);
			break;
		case 42:
			//42 -- STX
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STX(ix, i, address);
			break;
		case 10:
			//10 -- JZ
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JZ(r, ix, i, address);
			break;
		case 11:
			//11 -- JNE
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JNE(r, ix, i, address);
			break;
		case 12:
			//12 -- JCC
			cc = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JCC(cc, ix, i, address);
			break;
		case 13:
			//13 -- JMA
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JMA(ix, i, address);
			break;
		case 14:
			//14 --  JSR
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JSR(ix, i, address);
			break;
		case 15:
			//15 -- RFS
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.RFS();
			break;
		case 16:
			//16 -- SOB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.SOB(r, ix, i, address);
			break;
		case 17:
			//17 -- JGE
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JGE(r, ix, i, address);
			break;
		case 04:
			//04 -- AMR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.AMR(r, ix, i, address);
			break;
		case 05:
			//05 -- SMR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.SMR(r, ix, i, address);
			break;
		case 06:
			//06 -- AIR
			r = array[1];
			immed = array[2];
			Instr.AIR(r, immed);
			break;
		case 07:
			//07 -- SIR
			r = array[1];
			immed = array[4];
			Instr.SIR(r, immed);
			break;
		case 20:
			//20 -- MLT
			Rx = array[1];
			Ry = array[2];
			Instr.MLT(Rx, Ry);
			break;
		case 21:
			//21 -- DVD
			Rx = array[1];
			Ry = array[2];
			Instr.DVD(Rx, Ry);
			break;
		case 22:
			//22 -- TRR
			Rx = array[1];
			Ry = array[2];
			Instr.TRR(Rx, Ry);
			break;
		case 23:
			//23 -- AND
			Rx = array[1];
			Ry = array[2];
			Instr.AND(Rx, Ry);
			break;
		case 24:
			//24 -- ORR
			Rx = array[1];
			Ry = array[2];
			Instr.ORR(Rx, Ry);
			break;
		case 25:
			//25 --NOT
			Rx = array[1];
			Instr.NOT(Rx);
			break;
		case 31:
			//31 -- SRC
			r = array[1];
			AL = array[2];
			LR = array[3];
			Count = array[4];
			Instr.SRC(r, Count, LR, AL);
			break;
		case 32:
			//32 -- RRC
			r = array[1];
			AL = array[2];
			LR = array[3];
			Count = array[4];
			Instr.RRC(r, Count, LR, AL);
			break;
		case 61:
			//61 -- IN
			r = array[1];
			DevID = array[2];
			Instr.IN(r, DevID);
			break;
		case 62:
			//62 -- OUT
			r = array[1];
			DevID = array[2];
			Instr.OUT(r, DevID);
			break;
		case 63:
			//63 -- CHK
			r = array[1];
			DevID = array[2];
			break;
//		case 36:
//			//36 -- TRAP
//			trapCode = array[1];
//			break;
		case 00:
			//00 -- HLT
			status = 1;
			break;
		case 33:
			//34 -- FADD
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			int rlt = Instr.FADD(r, ix, i, address);
			//Instr.FADD2(r, rlt);
			break;
		case 34:
			//34 -- FSUB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.FSUB(r, ix, i, address);
			break;
		case 35:
			//35 -- VADD
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.VADD(r, ix, i, address);
			break;
		case 36:
			//36 -- VSUB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.VSUB(r, ix, i, address);
			break;
		case 37:
			//37 -- CNVRT
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.CNVRT(r, ix, i, address);
			break;
		case 50:
			//50 -- LDFR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDFR(r, ix, i, address);
			break;
		case 51:
			//51 -- STFR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STFR(r, ix, i, address);
			break;
		default:
			break;
		}
    	return status;
    }
    
    public int writeback(int instr, int rlt) {
    	//once face the HLT instr. change the status to 1;
    	int status = 0;
    	//array contain opCode, register, indexRegister, indirect, address
    	int[] array = tools.decodeInstr(instr);
    	int opCode = array[0];
    	int r;
    	int ix;
    	int i;
    	int address;
    	int Rx;
    	int Ry;
    	int AL;
    	int LR;
    	int Count;
    	int DevID;
    	int cc;
    	int immed;
    	int trapCode;
    	switch (opCode) {
		case 1:
			//01 -- LDR
			//System.out.println(array[1]);
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDR(r, ix, i, address);
			break;
		case 2:
			//02 -- STR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STR(r, ix, i, address);
			break;
		case 3:
			//03 -- LDA
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDA(r, ix, i, address);
			break;
		case 41:
			//41 -- LDX
			ix = array[2];
			i = array[3];
			address = array[4];
			System.out.println("41 ix "+ix);
			System.out.println("41 i "+i);
			System.out.println("41 address "+address);
			Instr.LDX(ix, i, address);
			break;
		case 42:
			//42 -- STX
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STX(ix, i, address);
			break;
		case 10:
			//10 -- JZ
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JZ(r, ix, i, address);
			break;
		case 11:
			//11 -- JNE
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JNE(r, ix, i, address);
			break;
		case 12:
			//12 -- JCC
			cc = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JCC(cc, ix, i, address);
			break;
		case 13:
			//13 -- JMA
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JMA(ix, i, address);
			break;
		case 14:
			//14 --  JSR
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JSR(ix, i, address);
			break;
		case 15:
			//15 -- RFS
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.RFS();
			break;
		case 16:
			//16 -- SOB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.SOB(r, ix, i, address);
			break;
		case 17:
			//17 -- JGE
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.JGE(r, ix, i, address);
			break;
		case 04:
			//04 -- AMR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.AMR(r, ix, i, address);
			break;
		case 05:
			//05 -- SMR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.SMR(r, ix, i, address);
			break;
		case 06:
			//06 -- AIR
			r = array[1];
			immed = array[2];
			Instr.AIR(r, immed);
			break;
		case 07:
			//07 -- SIR
			r = array[1];
			immed = array[4];
			Instr.SIR(r, immed);
			break;
		case 20:
			//20 -- MLT
			Rx = array[1];
			Ry = array[2];
			Instr.MLT(Rx, Ry);
			break;
		case 21:
			//21 -- DVD
			Rx = array[1];
			Ry = array[2];
			Instr.DVD(Rx, Ry);
			break;
		case 22:
			//22 -- TRR
			Rx = array[1];
			Ry = array[2];
			Instr.TRR(Rx, Ry);
			break;
		case 23:
			//23 -- AND
			Rx = array[1];
			Ry = array[2];
			Instr.AND(Rx, Ry);
			break;
		case 24:
			//24 -- ORR
			Rx = array[1];
			Ry = array[2];
			Instr.ORR(Rx, Ry);
			break;
		case 25:
			//25 --NOT
			Rx = array[1];
			Instr.NOT(Rx);
			break;
		case 31:
			//31 -- SRC
			r = array[1];
			AL = array[2];
			LR = array[3];
			Count = array[4];
			Instr.SRC(r, Count, LR, AL);
			break;
		case 32:
			//32 -- RRC
			r = array[1];
			AL = array[2];
			LR = array[3];
			Count = array[4];
			Instr.RRC(r, Count, LR, AL);
			break;
		case 61:
			//61 -- IN
			r = array[1];
			DevID = array[2];
			Instr.IN(r, DevID);
			break;
		case 62:
			//62 -- OUT
			r = array[1];
			DevID = array[2];
			Instr.OUT(r, DevID);
			break;
		case 63:
			//63 -- CHK
			r = array[1];
			DevID = array[2];
			break;
//		case 36:
//			//36 -- TRAP
//			trapCode = array[1];
//			break;
		case 00:
			//00 -- HLT
			status = 1;
			break;
		case 33:
			//34 -- FADD
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			//int rlt = Instr.FADD(r, ix, i, address);
			Instr.FADD2(r, rlt);
			break;
		case 34:
			//34 -- FSUB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.FSUB2(r, rlt);
			break;
		case 35:
			//35 -- VADD
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.VADD2(r, rlt);
			break;
		case 36:
			//36 -- VSUB
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.VSUB2(r,rlt);
			break;
		case 37:
			//37 -- CNVRT
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.CNVRT2(r,rlt);
			break;
		case 50:
			//50 -- LDFR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.LDFR2(r,rlt);
			break;
		case 51:
			//51 -- STFR
			r = array[1];
			ix = array[2];
			i = array[3];
			address = array[4];
			Instr.STFR2(r,rlt);
			break;
		default:
			break;
		}
    	return status;
    }
    

	/**
	 * this function mainly get the instr.s and the address,
	 * then store those instr.s to the memory
	 * @author Chinmay
	 * @param a
	 * @param pc1
	 */
	public void StoreInstr(String a, String pc1) {
		int pc = Integer.parseInt(pc1);
		String[] lines = a.split("\n");
		for (String line : lines) {
			//System.out.println("contents in the line " + line);
			ReadInputBox(line, pc);
			
			pc= pc+1;
		}

	}
	
	/**
	 * @author Chinmay
	 * @param r
	 * @param pc
	 */
	public void ReadInputBox(String r, int pc) {
			//String r = "";
			// This string variable is used for scanning the values of each line from text
			// area.
		memory2[pc]= r; 
		String str = "";
		String ins, ins1, reg, freg, ireg, mem, indirectAdd,reg2,ins4,ins5;
		ins = r.substring(0, 3);
		ins1 = r.substring(0, 2);
		ins4 = r.substring(0, 4);
		ins5 = r.substring(0, 5);
		if (ins.equals("LDR") || ins.equals("STR") || ins.equals("LDX") || ins.equals("STX") || ins.equals("LDA")
				|| ins.equals("HLT")) {
			//ins = r.substring(0, 3);
			reg = r.substring(4, 6);
			ireg = r.substring(7, 9);
			indirectAdd = r.substring(10,11);
			mem = r.substring(12,17);

			if (ins.equals("LDR")) {
				ins = "000001";
			} else if (ins.equals("STR")) {
				ins = "000010";
			} else if (ins.equals("LDA")) {
				ins = "000011";
			} else if (ins.equals("LDX")) {
				ins = "101001";
			} else if (ins.equals("STX")) {
				ins = "101010";

			} else if (ins.equals("HLT")) {
				ins = "000000";

			} else {
				ins = "000000";
			}

			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}

//			if (ireg.equals("x1")) {
//				ireg = "01";
//			} else if (ireg.equals("x2")) {
//				ireg = "10";
//			} else if (ireg.equals("x3")) {
//				ireg = "11";
//			}
			
			str = ins + reg + ireg +  indirectAdd +mem ;
			
		} else if(ins1.equals("JZ")) {
			
			//Transfer instructions
			reg = r.substring(3, 5);
			ireg = r.substring(6, 8);
			mem = r.substring(10, 15);
			indirectAdd = r.substring(9,10);
			
			if(ins1.equals("JZ")) {
				ins1 = "001010";
			}
			
			
			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}

			if (ireg.equals("x1")) {
				ireg = "01";
			} else if (ireg.equals("x2")) {
				ireg = "10";
			} else if (ireg.equals("x3")) {
				ireg = "11";
			}
			
			str = ins1 + reg + ireg +  indirectAdd +mem ;

		}
		
		else if ( ins.equals("JNE") || ins.equals("JCC") || ins.equals("JMA")
				|| ins.equals("JSR") || ins.equals("RFS") || ins.equals("SOB") || ins.equals("JGE")) {

			reg = r.substring(4, 6);
			ireg = r.substring(7, 9);
			mem = r.substring(12, 17);
			indirectAdd = r.substring(10,11);



			if (ins.equals("JNE")) {
				ins = "001011";
			} else if (ins.equals("JCC")) {
				ins = "001100";
			} else if (ins.equals("JMA")) {
				ins = "001101";
			} else if (ins.equals("JSR")) {
				ins = "001110";
			} else if (ins.equals("RFS")) {
				ins = "001111";
			} else if (ins.equals("SOB")) {
				ins = "010000";

			} else if(ins.equals("JGE")){
				ins = "010001";

			} else if (ins.equals("HLT")){
				ins = "00000";
			}else {
				ins = "00000";

			}
			

			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}

			if (ireg.equals("x1")) {
				ireg = "01";
			} else if (ireg.equals("x2")) {
				ireg = "10";
			} else if (ireg.equals("x3")) {
				ireg = "11";
			}
			
			str = ins + reg + ireg +  indirectAdd +mem ;
			
		}
		
		else if (ins.equals("AMR") || ins.equals("SMR") ) {

			//Arithmetic and logical instructions
			reg = r.substring(4, 6);
			ireg = r.substring(7, 9);
			mem = r.substring(12, 17);
			indirectAdd = r.substring(10,11);



			if (ins.equals("AMR")) {
				ins = "000100";
			} else if (ins.equals("SMR")) {
				ins = "000101";
			}

			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}

			if (ireg.equals("x1")) {
				ireg = "01";
			} else if (ireg.equals("x2")) {
				ireg = "10";
			} else if (ireg.equals("x3")) {
				ireg = "11";
			}
			
			
			str = ins + reg + ireg +  indirectAdd +mem ;

			
			
		}
		else if (ins.equals("AIR") || ins.equals("SIR")) {

			//Arithmetic and logical instructions
			reg = r.substring(4, 6);
			//ireg = r.substring(7, 9);
			
			String immed = r.substring(7);
			//String blacked = "000";
			
			if(ins.equals("AIR")) {
				ins = "000110";
			}else if(ins.equals("SIR")) {
				ins = "000111";
			}
			
			if (reg.equals("r0")) {
				reg = "00";
			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}
			str = ins + reg + immed;
			
		}
		
		else if (ins.equals("MLT") || ins.equals("DVD") || ins.equals("TRR") || ins.equals("AND") || ins.equals("ORR")) {
			
			
			//Arithmetic part 2
			//Instructions of the type MLT Rx,Ry
			
			reg = r.substring(4, 6);
			reg2 = r.substring(7,9);
			String blacked = "000000";
			if(ins.equals("MLT")) {
				ins = "010100";
			}else if(ins.equals("DVD")) {
				ins = "010101";
			}else if(ins.equals("TRR")) {
				ins = "010110";
			}else if(ins.equals("AND")) {
				ins = "010111";
			}else if(ins.equals("ORR")) {
				ins = "011000";
			}
			
			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}
			
			if (reg2.equals("r0")) {
				reg2 = "00";

			} else if (reg2.equals("r1")) {
				reg2 = "01";
			} else if (reg2.equals("r2")) {
				reg2 = "10";
			} else if (reg2.equals("r3")) {
				reg2 = "11";
			}
			
			str = ins + reg + reg2+ blacked;
		}
		
		else if ( ins.equals("NOT")) {
			
			
			//Arithmetic part 2
			//Instructions of the type MLT Rx,Ry
			
			reg = r.substring(4, 6);
			String blacked = "00000000";
			ins = "011001";
			
			
			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}

			
			str = ins + reg + blacked;
		}
		
		else if (ins.equals("SRC") || ins.equals("RRC")) {
			// logical rotate
			String count,R,LR,AL;
			R = r.substring(4,6);
			count = r.substring(7,11);
			AL = r.substring(12,13);
			LR = r.substring(14);
			
			String blacked= "00";
			if(ins.equals("SRC")) {
				ins = "011111";
			}else if(ins.equals("RRC")) {
				ins = "100000";
			}
			
			if(R.equals("r0")) {
				R = "00";
			}else if(R.equals("r1")) {
				R = "01";
			}else if(R.equals("r2")) {
				R = "10";
			}else if(R.equals("r3")) {
				R = "11";
			} 
			

			
			str = ins + R + LR + AL + blacked + count;
		}
		
		
		else if(ins.equals("OUT") || ins.equals("CHK")){
			// I/O operation.
			reg = r.substring(4, 6);
			String DevId = r.substring(11,16);
			if(ins.equals("OUT")) {
				ins = "111110";
			}else if(ins.equals("CHK")) {
				ins = "111111";
			}
			
			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}
			//int a = Integer.parseInt(DevId);
			//DevId = Integer.toBinaryString(a);
			String blacked = "000";
			str = ins + reg + DevId + blacked;
			
		}
		else if(ins1.equals("IN")) {
			ins1 = "111101";
			reg = r.substring(3, 5);
			String DevId = r.substring(6,11);

			if (reg.equals("r0")) {
				reg = "00";

			} else if (reg.equals("r1")) {
				reg = "01";
			} else if (reg.equals("r2")) {
				reg = "10";
			} else if (reg.equals("r3")) {
				reg = "11";
			}
			//int a = Integer.parseInt(DevId);
			//DevId = Integer.toBinaryString(a);
			String blacked = "000";
			str = ins1 + reg + blacked + DevId ;
			//commiting the code
		}
		
		//decode instr. for part 4
		if(ins4.equals("FADD") || ins4.equals("FSUB") || ins4.equals("VADD") || ins4.equals("VSUB") || 
				ins4.equals("LDFR") || ins4.equals("STFR")) {
			//initialize parameters
			freg = r.substring(5, 7);
			ireg = r.substring(8, 10);
			indirectAdd = r.substring(11,12);
			mem = r.substring(13,18);
			//decode opcode
			if(ins4.equals("FADD")) 
				ins4 = "100001";
			else if(ins4.equals("FSUB"))
				ins4 = "100010";
			else if(ins4.equals("VADD"))
				ins4 = "100011";
			else if(ins4.equals("VSUB"))
				ins4 = "100100";
			else if(ins4.equals("LDFR"))
				ins4 = "110010";
			else if(ins4.equals("STFR"))
				ins4 = "110011";
			//decode floatRegister number
			if (freg.equals("r0"))
				freg = "00";
			else if (freg.equals("r1"))
				freg = "01";
			//combine all parameters 
			str = ins4 + freg + ireg +  indirectAdd +mem ;
		}
		
		if(ins5.equals("CNVRT")) {
			//initialize parameters
			reg = r.substring(6, 8);
			ireg = r.substring(9, 11);
			indirectAdd = r.substring(12, 13);
			mem = r.substring(14,19);
			//decode opcode
			ins5 = "100101";
			//decode floatRegister number
			if (reg.equals("r0"))
				reg = "00";
			else if (reg.equals("r1"))
				reg = "01";
			//combine all parameters 
			str = ins5 + reg + ireg +  indirectAdd +mem ;
		}
			//System.out.println(r.substring(0,3)+str);
			int result = Integer.parseInt(str, 2);
			//System.out.println("ok"+result);
			//memory[pc] = result;
			cache.setValue(pc, result);
			
	}
}
