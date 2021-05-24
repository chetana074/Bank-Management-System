import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

abstract class Main extends JFrame {

	static int r=0,x=1,l=0,q=1;
	int accountDeleted = 0;

	JFrame fLogin,fRegister,warning,categoryF,emptyFieldF,delAccF,category1,category2,category3;
	JFrame category4,category5,category6,category7;

	JButton backB,delAccB1,delAccB2,depositCashB,moneyTransfB,updateDoneB,withdrawCashB,logoutB,emptyFieldB; 
	JButton loginSubmitB,radioOkButton,registerSubmitB,registerResetB,loginResetB,warningOKButton;

	JLabel delAccL,warningLabel,emptyFieldL,withdrawCashL,categoryList,depositCashL,totalCashL,userNameL;
	JLabel currentBalanceL,loginFirstNameL,loginMiddleNameL,loginLastNameL,loginPasswordL,loginAccL;
	JLabel moneyTransfL,registerFirstNameL,registerMiddleNameL,registerBalanceL,registerLastNameL,dispAccL;
	JLabel registerAccL,registerPasswordL,confirmPasswordL,registerPhoneL,registerEmailL,registerDOBL;
	JLabel dispFirstNameL,dispMiddleNameL,dispLastNameL,dispEmailL,dispPhoneL,dispDOBL,dispUserNameL;
	JLabel label[][] = new JLabel[10][3];	

	JTextField depositCashT,currentBalanceT,totalCashT,withdrawCashT,loginFirstNameT,loginMiddleNameT;
	JTextField loginLastNameT,loginAccT,moneyTransfT,registerFirstNameT,registerMiddleNameT,registerDOBT;
	JTextField registerBalanceT,registerLastNameT,registerAccT,registerPhoneT,registerEmailT,userNameT;	
	JTextField dispFirstNameT,dispMiddleNameT,dispLastNameT,dispAccT,dispEmailT,dispPhoneT,dispDOBT;
	JTextField dispUserNameT;

	JPasswordField loginPasswordT,registerPasswordT,confirmPasswordT;

	JRadioButton r1,r2,r3,r4,r5,r6,r7,r8;

	ButtonGroup bg;

	//actual value stored in these variables
	String firstName,middleName,lastName,currentBalance,accNo,emailID,phoneNo,dob,password,str1,str2,optxt;
	String depositCash,withdrawCash,moneyTransf,moneyTransfIFSC,moneyTransfAcc,userName;

	static BankManagementProject obj = new BankManagementProject();

	public void category() {

		//category frame
		categoryF = new JFrame("Categories");
		categoryF.setSize(800,600); 
		categoryF.setLayout(null); 
		categoryF.setVisible(true);

		//log out button
		logoutB = new JButton("Log Out");
		logoutB.setBounds(600,10,100,40);
		categoryF.add(logoutB);
		logoutB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				++r;
				categoryF.dispose();
				obj.FirstFrame();
			} 
		});

		//categories
		categoryList = new JLabel("List of available categories : "); 
		categoryList.setBounds(200,30,500,50);
		categoryF.add(categoryList);

		r1=new JRadioButton("1. Display Account Details");  
		r1.setBounds(250,80,500,50);

		r2=new JRadioButton("2. Update Account Details");    
		r2.setBounds(250,120,500,50);

		r3=new JRadioButton("3. Deposit cash in the account");  
		r3.setBounds(250,160,500,50);

		r4=new JRadioButton("4. Withdraw cash from the account");    
		r4.setBounds(250,200,500,50);

		r5=new JRadioButton("5. Money Transfer");  
		r5.setBounds(250,240,500,50);

		r6=new JRadioButton("6. Display History of Account");    
		r6.setBounds(250,280,500,50);

		r7=new JRadioButton("7. Delete Account");  
		r7.setBounds(250,320,500,50);

		r8=new JRadioButton("8. Exit the program");    
		r8.setBounds(250,360,500,50);

		bg=new ButtonGroup(); 

		bg.add(r1); categoryF.add(r1);
		bg.add(r2); categoryF.add(r2);
		bg.add(r3); categoryF.add(r3);
		bg.add(r4); categoryF.add(r4);
		bg.add(r5); categoryF.add(r5);
		bg.add(r6); categoryF.add(r6);
		bg.add(r7); categoryF.add(r7);
		bg.add(r8); categoryF.add(r8);

		radioOkButton = new JButton("Ok");
		radioOkButton.setBounds(275,450,100,40);
		categoryF.add(radioOkButton);

		radioOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(r1.isSelected()) {
					displayAccDetails();
				}
				else if(r2.isSelected()) {
					updateAccDetails();
				}
				else if(r3.isSelected()) {
					if(accountDeleted==0) {
						depositCash();
					}
					else {
						cannotPerformTheOperation();
					}
				}
				else if(r4.isSelected()) {
					if(accountDeleted==0) {
						withdrawCash();
					}
					else {
						cannotPerformTheOperation();
					}
				}
				else if(r5.isSelected()) {
					if(accountDeleted==0) {
						MoneyTransfer();
					}
					else {
						cannotPerformTheOperation();
					}
				}
				else if(r6.isSelected()) {
					historyOfAcc();
				}
				else if(r7.isSelected()) {
					delAccR();
				}
				else if(r8.isSelected()) {
					System.exit(0);
				}
			}
		});
	}
	public void cannotPerformTheOperation() {
		JFrame f = new JFrame("Invalid Field");
		f.setSize(700,400); 
		f.setLayout(null); 
		f.setVisible(true);
		
		JLabel l = new JLabel("Account is deleted!!!"); 
		l.setHorizontalAlignment(l.CENTER);
		l.setBounds(100,30,500,30);
		f.add(l);
		
		JLabel l1 = new JLabel("Cannot Perform the Operation"); 
		l1.setHorizontalAlignment(l1.CENTER);
		l1.setBounds(100,80,500,30);
		f.add(l1);
		
		JButton b = new JButton("Back");
		b.setBounds(300,300,100,40);
		f.add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
		});
	}
	public void displayAccDetails() {
		category1 = new JFrame("Account Details");
		category1.setSize(800,600); 
		category1.setLayout(null); 
		category1.setVisible(true);

		dispFirstNameL = new JLabel("First Name : "); 
		dispFirstNameL.setBounds(100,30,500,30);
		category1.add(dispFirstNameL);

		dispFirstNameT = new JTextField();
		dispFirstNameT.setBounds(250, 40, 200, 25);
		category1.add(dispFirstNameT);
		dispFirstNameT.setText(firstName);
		dispFirstNameT.setEditable(false);

		dispMiddleNameL = new JLabel("Middle Name : "); 
		dispMiddleNameL.setBounds(100,80,500,30);
		category1.add(dispMiddleNameL);

		dispMiddleNameT = new JTextField();
		dispMiddleNameT.setBounds(250, 90, 200, 25);
		category1.add(dispMiddleNameT);
		dispMiddleNameT.setText(middleName);
		dispMiddleNameT.setEditable(false);

		dispLastNameL = new JLabel("Last Name : "); 
		dispLastNameL.setBounds(100,130,500,30);
		category1.add(dispLastNameL);

		dispLastNameT = new JTextField();
		dispLastNameT.setBounds(250, 140, 200, 25);
		category1.add(dispLastNameT);
		dispLastNameT.setText(lastName);
		dispLastNameT.setEditable(false);

		dispUserNameL = new JLabel("User Name : "); 
		dispUserNameL.setBounds(100,180,500,30);
		category1.add(dispUserNameL);

		dispUserNameT = new JTextField();
		dispUserNameT.setBounds(250, 190, 200, 25);
		category1.add(dispUserNameT);
		dispUserNameT.setText(lastName);
		dispUserNameT.setEditable(false);

		dispAccL = new JLabel("Account Number : "); 
		dispAccL.setBounds(100,230,500,30);
		category1.add(dispAccL);

		dispAccT = new JTextField();
		dispAccT.setBounds(250, 240, 200, 25);
		category1.add(dispAccT);
		dispAccT.setText(accNo);
		dispAccT.setEditable(false);

		JLabel l = new JLabel("Current Balance : "); 
		l.setBounds(100,280,500,30);
		category1.add(l);

		JTextField t = new JTextField();
		t.setBounds(250, 290, 200, 25);
		category1.add(t);
		t.setText(currentBalance);
		t.setEditable(false);

		dispEmailL = new JLabel("Email ID : "); 
		dispEmailL.setBounds(100,330,500,30);
		category1.add(dispEmailL);

		dispEmailT = new JTextField();
		dispEmailT.setBounds(250, 340, 200, 25);
		category1.add(dispEmailT);
		dispEmailT.setText(emailID);
		dispEmailT.setEditable(false);

		dispPhoneL = new JLabel("Phone Number : "); 
		dispPhoneL.setBounds(100,380,500,30);
		category1.add(dispPhoneL);

		dispPhoneT = new JTextField();
		dispPhoneT.setBounds(250, 390, 200, 25);
		category1.add(dispPhoneT);
		dispPhoneT.setText(phoneNo);
		dispPhoneT.setEditable(false);

		dispDOBL = new JLabel("Date of Birth : "); 
		dispDOBL.setBounds(100,430,500,30);
		category1.add(dispDOBL);

		dispDOBT = new JTextField();
		dispDOBT.setBounds(250, 440, 200, 25);
		category1.add(dispDOBT);
		dispDOBT.setText(dob);
		dispDOBT.setEditable(false);

		backB = new JButton("Back");
		backB.setBounds(300,500,100,40);
		category1.add(backB);

		backB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category1.dispose();
			}
		});
	}
	public void updateAccDetails() {
		category2 = new JFrame("Update Account Details");
		category2.setSize(800,600); 
		category2.setLayout(null); 
		category2.setVisible(true);

		dispFirstNameL = new JLabel("First Name : "); 
		dispFirstNameL.setBounds(100,30,500,30);
		category2.add(dispFirstNameL);

		dispFirstNameT = new JTextField();
		dispFirstNameT.setBounds(250, 40, 200, 25);
		category2.add(dispFirstNameT);
		dispFirstNameT.setText(firstName);
		dispFirstNameT.setEditable(false);

		dispMiddleNameL = new JLabel("Middle Name : "); 
		dispMiddleNameL.setBounds(100,80,500,30);
		category2.add(dispMiddleNameL);

		dispMiddleNameT = new JTextField();
		dispMiddleNameT.setBounds(250, 90, 200, 25);
		category2.add(dispMiddleNameT);
		dispMiddleNameT.setText(middleName);
		dispMiddleNameT.setEditable(false);

		dispLastNameL = new JLabel("Last Name : "); 
		dispLastNameL.setBounds(100,130,500,30);
		category2.add(dispLastNameL);

		dispLastNameT = new JTextField();
		dispLastNameT.setBounds(250, 140, 200, 25);
		category2.add(dispLastNameT);
		dispLastNameT.setText(lastName);
		dispLastNameT.setEditable(false);

		dispUserNameL = new JLabel("User Name : "); 
		dispUserNameL.setBounds(100,180,500,30);
		category2.add(dispUserNameL);

		dispUserNameT = new JTextField();
		dispUserNameT.setBounds(250, 190, 200, 25);
		category2.add(dispUserNameT);
		dispUserNameT.setText(lastName);
		dispUserNameT.setEditable(false);

		dispAccL = new JLabel("Account Number : "); 
		dispAccL.setBounds(100,230,500,30);
		category2.add(dispAccL);

		dispAccT = new JTextField();
		dispAccT.setBounds(250, 240, 200, 25);
		category2.add(dispAccT);
		dispAccT.setText(accNo);
		dispAccT.setEditable(false);

		JLabel l = new JLabel("Current Balance : "); 
		l.setBounds(100,280,500,30);
		category2.add(l);

		JTextField t = new JTextField();
		t.setBounds(250, 290, 200, 25);
		category2.add(t);
		t.setText(currentBalance);
		t.setEditable(false);

		dispEmailL = new JLabel("Email ID : "); 
		dispEmailL.setBounds(100,330,500,30);
		category2.add(dispEmailL);

		dispEmailT = new JTextField();
		dispEmailT.setBounds(250, 340, 200, 25);
		category2.add(dispEmailT);
		dispEmailT.setText(emailID);

		dispPhoneL = new JLabel("Phone Number : "); 
		dispPhoneL.setBounds(100,380,500,30);
		category2.add(dispPhoneL);

		dispPhoneT = new JTextField();
		dispPhoneT.setBounds(250, 390, 200, 25);
		category2.add(dispPhoneT);
		dispPhoneT.setText(phoneNo);

		dispDOBL = new JLabel("Date of Birth : "); 
		dispDOBL.setBounds(100,430,500,30);
		category2.add(dispDOBL);

		dispDOBT = new JTextField();
		dispDOBT.setBounds(250, 440, 200, 25);
		category2.add(dispDOBT);
		dispDOBT.setText(dob);

		updateDoneB = new JButton("Done");
		updateDoneB.setBounds(190,500,100,40);
		category2.add(updateDoneB);

		updateDoneB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emailID = dispEmailT.getText();
				phoneNo = dispPhoneT.getText();
				dob = dispDOBT.getText();

				category2.dispose();
			}
		});
	}
	public void depositCash() {
		category3 = new JFrame("Deposit Cash");
		category3.setSize(800,700); 
		category3.setLayout(null); 
		category3.setVisible(true);

		depositCashL = new JLabel("Enter Amount to Deposit : "); 
		depositCashL.setBounds(100,80,500,30);
		category3.add(depositCashL);

		depositCashT = new JTextField();
		depositCashT.setBounds(300, 85, 200, 25);
		category3.add(depositCashT);


		depositCashB = new JButton("Deposit");  
		depositCashB.setBounds(300,200,100,40);
		category3.add(depositCashB);

		depositCashB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JLabel amountDeposited = new JLabel();
				amountDeposited.setBounds(200,300,500,30);
				category3.add(amountDeposited);
				amountDeposited.setText("Amount Deposited to the account successfully!");

				depositCash = depositCashT.getText();

				currentBalanceL = new JLabel(); 
				currentBalanceL.setBounds(250,350,500,30);
				category3.add(currentBalanceL);
				String data1 = "Previous Balance : " + currentBalance;
				currentBalanceL.setText(data1);

				depositCashL = new JLabel(); 
				depositCashL.setBounds(250,400,500,30);
				category3.add(depositCashL);
				String data2 = "Deposited Amount : " + depositCash;
				depositCashL.setText(data2);

				int total;
				int cash1 = Integer.parseInt(depositCash);
				int cash2 = Integer.parseInt(currentBalance);
				total = cash1 + cash2; 

				totalCashL = new JLabel(); 
				totalCashL.setBounds(250,450,500,30);
				category3.add(totalCashL);
				String data3 = "Current Balance : " + String.valueOf(total);
				totalCashL.setText(data3);

				currentBalance = String.valueOf(total);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				label[x][0] = new JLabel();
				label[x][0].setText(dtf.format(now));
				label[x][1] = new JLabel();
				label[x][1].setText(depositCash);
				String d = "Deposit";
				label[x][2] = new JLabel();
				label[x][2].setText(d);
				++x;

				backB = new JButton("Back");
				backB.setBounds(260,500,100,40);
				category3.add(backB);

				backB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						category3.dispose();
					}
				});
			}
		});
	}
	public void withdrawCash() {
		category4 = new JFrame("Withdraw Cash");
		category4.setSize(800,600); 
		category4.setLayout(null); 
		category4.setVisible(true);

		withdrawCashL = new JLabel("Enter Amount to Withdraw : "); 
		withdrawCashL.setBounds(100,80,500,30);
		category4.add(withdrawCashL);

		withdrawCashT = new JTextField();
		withdrawCashT.setBounds(300, 85, 200, 25);
		category4.add(withdrawCashT);

		withdrawCashB = new JButton("Withdraw");  
		withdrawCashB.setBounds(300,200,100,40);
		category4.add(withdrawCashB);

		withdrawCashB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				withdrawCash = withdrawCashT.getText();

				JLabel amountWithdrawn = new JLabel();
				amountWithdrawn.setBounds(200,300,500,30);
				category4.add(amountWithdrawn);
				amountWithdrawn.setText("Amount Withdrawn from the account successfully!");

				currentBalanceL = new JLabel(); 
				currentBalanceL.setBounds(200,350,500,30);
				category4.add(currentBalanceL);
				String data1 = "Previous Balance : " + currentBalance;
				currentBalanceL.setText(data1);

				withdrawCashL = new JLabel(); 
				withdrawCashL.setBounds(200,400,500,30);
				category4.add(withdrawCashL);
				String data2 = "Withdrawn Amount : " + withdrawCash;
				withdrawCashL.setText(data2);

				int total;
				int cash1 = Integer.parseInt(withdrawCash);
				int cash2 = Integer.parseInt(currentBalance);
				total = cash2 - cash1 ; 

				totalCashL = new JLabel(); 
				totalCashL.setBounds(200,450,500,30);
				category4.add(totalCashL);
				String data3 = "Current Balance : " + String.valueOf(total);
				totalCashL.setText(data3);

				currentBalance = String.valueOf(total);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				label[x][0] = new JLabel();
				label[x][0].setText(dtf.format(now));
				label[x][1] = new JLabel();
				label[x][1].setText(withdrawCashT.getText());
				String w = "Withdraw";
				label[x][2] = new JLabel();
				label[x][2].setText(w);
				++x;

				backB = new JButton("Back");
				backB.setBounds(260,500,100,40);
				category4.add(backB);

				backB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						category4.dispose();
					}
				});
			}
		});
	}
	public void MoneyTransfer() {
		category5 = new JFrame("Transfer Money");
		category5.setSize(800,800); 
		category5.setLayout(null); 
		category5.setVisible(true);

		final JLabel label1 = new JLabel("Select the way to transfer money : ");
		label1.setBounds(50,30,500,30);  
		label1.setSize(400,100);  
		category5.add(label1);

		String fields[]={"National Electronic Fund Transfer (NEFT)","Real Time Gross Settlement (RTGS)","Immediate Payment Service (IPS)"};        
		final JComboBox cb = new JComboBox(fields);    
		cb.setBounds(300,75,300,20);
		category5.add(cb);

		JLabel moneyTransfAccL = new JLabel("Enter account no of the destination account : "); 
		moneyTransfAccL.setBounds(50,120,500,30);
		category5.add(moneyTransfAccL);

		JTextField moneyTransfAccT = new JTextField();
		moneyTransfAccT.setBounds(300, 120, 200, 25);
		category5.add(moneyTransfAccT);

		JLabel moneyTransfIFSCL = new JLabel("Enter the IFSC code of destination account : "); 
		moneyTransfIFSCL.setBounds(50,170,500,30);
		category5.add(moneyTransfIFSCL);

		JTextField moneyTransfIFSCT = new JTextField();
		moneyTransfIFSCT.setBounds(300, 170, 200, 25);
		category5.add(moneyTransfIFSCT);

		moneyTransfL = new JLabel("Enter amount to be transferred : "); 
		moneyTransfL.setBounds(50,220,500,30);
		category5.add(moneyTransfL);

		moneyTransfT = new JTextField();
		moneyTransfT.setBounds(300, 220, 200, 25);
		category5.add(moneyTransfT);

		moneyTransfB = new JButton("Transfer");  
		moneyTransfB.setBounds(300,270,100,40);
		category5.add(moneyTransfB);

		moneyTransfB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JLabel paymentSucc = new JLabel();
				paymentSucc.setBounds(250,350,500,50);
				category5.add(paymentSucc);
				paymentSucc.setText("Transfer Successful!!!");

				moneyTransf = moneyTransfT.getText();
				moneyTransfAcc = moneyTransfAccT.getText();
				moneyTransfIFSC = moneyTransfIFSCT.getText();

				JLabel wayL = new JLabel();
				wayL.setBounds(50,400,500,30);
				category5.add(wayL);

				String data = "Money Transfer way selected : "   
						+ cb.getItemAt(cb.getSelectedIndex());  
				wayL.setText(data);

				JLabel AccL = new JLabel();
				AccL.setBounds(50,450,500,30);
				category5.add(AccL);
				String data1 = "Money Transferred to Account : "+moneyTransfAcc;
				AccL.setText(data1);

				JLabel IFSCL = new JLabel();
				IFSCL.setBounds(50,500,500,30);
				category5.add(IFSCL);
				String data2 = "IFSC Code : "+moneyTransfIFSC;
				IFSCL.setText(data2);

				JLabel prevBalanceL = new JLabel(); 
				prevBalanceL.setBounds(50,550,500,30);
				category5.add(prevBalanceL);
				String data3 = "Previous Balance : " + currentBalance;
				prevBalanceL.setText(data3);

				moneyTransfL = new JLabel(); 
				moneyTransfL.setBounds(50,600,500,30);
				category5.add(moneyTransfL);
				String data4 = "Transferred Amount : " + moneyTransf;
				moneyTransfL.setText(data4);

				int total;
				int cash1 = Integer.parseInt(moneyTransf);
				int cash2 = Integer.parseInt(currentBalance);
				total = cash2 - cash1; 

				totalCashL = new JLabel(); 
				totalCashL.setBounds(50,650,500,30);
				category5.add(totalCashL);
				String data5 = "Current Balance : " + String.valueOf(total);
				totalCashL.setText(data5);

				currentBalance = String.valueOf(total);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
				LocalDateTime now = LocalDateTime.now();
				label[x][0] = new JLabel();
				label[x][0].setText(dtf.format(now));
				label[x][1] = new JLabel();
				label[x][1].setText(moneyTransfT.getText());
				String m = "Money Transfer";
				label[x][2] = new JLabel();
				label[x][2].setText(m);
				++x;

				backB = new JButton("Back");
				backB.setBounds(300,700,100,40);
				category5.add(backB);

				backB.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						category5.dispose();
					}
				});
			}
		});
	}
	public void historyOfAcc() {
		category7 = new JFrame("Account Transaction History");
		category7.setSize(900,800);
		category7.setLayout(null);
		category7.setVisible(true);

		label[0][0] = new JLabel(); 
		label[0][0].setBounds(50,70,500,30);
		category7.add(label[0][0]);
		label[0][0].setText("Transaction Date and Time");

		label[0][1] = new JLabel(); 
		label[0][1].setBounds(350,70,500,30);
		category7.add(label[0][1]);
		label[0][1].setText("Amount");

		label[0][2] = new JLabel(); 
		label[0][2].setBounds(550,70,500,30);
		category7.add(label[0][2]);
		label[0][2].setText("Type");

		historyOfAcc1();
	}
	public void historyOfAcc1() {
		int j=120;
		try {
			for(int i=1;i<=x;i++) {
				label[i][0].setBounds(50,j,500,30);
				category7.add(label[i][0]);

				label[i][1].setBounds(350,j,500,30);
				category7.add(label[i][1]);

				label[i][2].setBounds(550,j,500,30);
				category7.add(label[i][2]);

				j=j+50;
			}
		}
		catch(Exception e) {

		}
		JButton b = new JButton("Back");
		b.setBounds(275,500,100,40);
		category7.add(b);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				category7.dispose();
			}
		});
	}
	public void delAccR() {

		delAccF = new JFrame("Delete Account");
		delAccF.setSize(700,500);
		delAccF.setLayout(null);
		delAccF.setVisible(true);

		delAccL = new JLabel("Delete the account permently?"); 
		delAccL.setBounds(50,70,500,30);
		delAccF.add(delAccL);

		delAccB1 = new JButton("Yes");  
		delAccB1.setBounds(400,70,100,40);
		delAccF.add(delAccB1);

		delAccB2 = new JButton("No");  
		delAccB2.setBounds(400,150 ,100,40);
		delAccF.add(delAccB2);

		delAccB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				accountDeleted = 1;
				
				int flag = 0;
				int k;
				for(k=0;k<=r;k++) {
					if((accNo).equals(obj.robj[k].accNo) ) {
						firstName = obj.robj[k].firstName;
						middleName = obj.robj[k].middleName;
						lastName = obj.robj[k].lastName;
						accNo = obj.robj[k].accNo;
						currentBalance = obj.robj[k].currentBalance;
						emailID = obj.robj[k].emailID;
						phoneNo = obj.robj[k].phoneNo;
						dob = obj.robj[k].dob;
						password = obj.robj[k].password;
						userName = obj.robj[k].userName;

						flag = 1;						
						break;
					}
				}
				if(flag==1) {
					obj.robj[k].firstName = "";
					obj.robj[k].middleName = "";
					obj.robj[k].lastName = "";
					obj.robj[k].accNo = "";
					obj.robj[k].currentBalance = "";
					obj.robj[k].emailID = "";
					obj.robj[k].phoneNo = "";
					obj.robj[k].dob = "";
					obj.robj[k].password = "";
					obj.robj[k].userName = "";

					for(int i=1;i<x;i++) {
						label[i][0].setText("");
						label[i][1].setText("");
						label[i][2].setText("");
					}
				}
				firstName = "";
				middleName = ""; 
				lastName = "";
				currentBalance = ""; 
				accNo = "";
				emailID = "";
				phoneNo = "";
				dob = "";
				userName = "";
				delAccF.dispose();
			}
		});
		delAccB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delAccF.dispose();
			}
		});
	}
	public void incorrectPassword() {
		//warning frame
		warning = new JFrame("Warning");
		warning.setSize(400,400); 
		warning.setLayout(null); 
		warning.setVisible(true);

		//warning label
		warningLabel = new JLabel("Passwords do not match! Enter Password Again"); 
		warningLabel.setBounds(50,70,500,30);
		warning.add(warningLabel);

		//ok button
		warningOKButton = new JButton("Okay");  
		warningOKButton.setBounds(100,100,100,40);
		warning.add(warningOKButton);

		warningOKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerPasswordT.setText("");
				confirmPasswordT.setText("");
				warning.dispose(); 
			}
		});

	}
	public void emptyField() {
		//empty field frame
		emptyFieldF = new JFrame("Empty Field Error");
		emptyFieldF.setSize(400,300);
		emptyFieldF.setLayout(null);
		emptyFieldF.setVisible(true);

		//empty field label
		emptyFieldL = new JLabel("Please fill all the fields"); 
		emptyFieldL.setBounds(100,65,500,30);
		emptyFieldF.add(emptyFieldL);

		//empty field button
		emptyFieldB = new JButton("Okay");  
		emptyFieldB.setBounds(120,130,100,40);
		emptyFieldF.add(emptyFieldB);
		emptyFieldB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyFieldF.dispose();
			}
		});
	}
}
class register extends Main {
	public void registerFields() {
		fRegister = new JFrame("Register");
		fRegister.setSize(800,800);
		fRegister.setLayout(null); 
		fRegister.setVisible(true);

		//enter first name label
		registerFirstNameL = new JLabel("Enter First Name : "); 
		registerFirstNameL.setBounds(100,50,500,30);
		fRegister.add(registerFirstNameL);

		//accept first name text field
		registerFirstNameT = new JTextField();
		registerFirstNameT.setBounds(350, 50, 200, 25);
		fRegister.add(registerFirstNameT);

		//enter middle name label
		registerMiddleNameL = new JLabel("Enter Middle Name : "); 
		registerMiddleNameL.setBounds(100,100,500,30);
		fRegister.add(registerMiddleNameL);

		//accept middle name text field
		registerMiddleNameT = new JTextField();
		registerMiddleNameT.setBounds(350, 100, 200, 25);
		fRegister.add(registerMiddleNameT);

		//enter last name label
		registerLastNameL = new JLabel("Enter Last Name : "); 
		registerLastNameL.setBounds(100,150,500,30);
		fRegister.add(registerLastNameL);

		//accept last name text field
		registerLastNameT = new JTextField();
		registerLastNameT.setBounds(350, 150, 200, 25);
		fRegister.add(registerLastNameT);

		//enter user name label
		userNameL = new JLabel("Enter User Name : "); 
		userNameL.setBounds(100,200,500,30);
		fRegister.add(userNameL);

		//accept user name text field
		userNameT = new JTextField();
		userNameT.setBounds(350, 200, 200, 25);
		fRegister.add(userNameT);

		//enter Account No label
		registerAccL = new JLabel("Enter Account No : "); 
		registerAccL.setBounds(100,250,500,30);
		fRegister.add(registerAccL);

		//accept Account No text field
		registerAccT = new JTextField();
		registerAccT.setBounds(350, 250, 200, 25);
		fRegister.add(registerAccT);

		//enter current balance
		currentBalanceL = new JLabel("Enter Current Balance : "); 
		currentBalanceL.setBounds(100,300,500,30);
		fRegister.add(currentBalanceL);

		//accept current balance
		currentBalanceT = new JTextField();
		currentBalanceT.setBounds(350, 300, 200, 25);
		fRegister.add(currentBalanceT);

		//enter email label
		registerEmailL = new JLabel("Enter Email ID : "); 
		registerEmailL.setBounds(100,350,500,30);
		fRegister.add(registerEmailL);

		//accept email text field
		registerEmailT = new JTextField();
		registerEmailT.setBounds(350, 350, 200, 25);
		fRegister.add(registerEmailT);

		//enter phone label
		registerPhoneL = new JLabel("Enter Phone Number : "); 
		registerPhoneL.setBounds(100,400,500,30);
		fRegister.add(registerPhoneL);

		//accept phone text field
		registerPhoneT = new JTextField();
		registerPhoneT.setBounds(350, 400, 200, 25);
		fRegister.add(registerPhoneT);

		//enter DOB label
		registerDOBL = new JLabel("Enter Date of Birth (dd/mm/yyyy) : "); 
		registerDOBL.setBounds(100,450,500,30);
		fRegister.add(registerDOBL);

		//accept DOB
		registerDOBT = new JTextField();
		registerDOBT.setBounds(350,450,200,25);
		fRegister.add(registerDOBT);

		//enter password label
		registerPasswordL = new JLabel("Enter a strong password : "); 
		registerPasswordL.setBounds(100,500,500,30);
		fRegister.add(registerPasswordL);

		//accept password text field
		registerPasswordT = new JPasswordField();
		registerPasswordT.setBounds(350, 500, 200, 25);
		fRegister.add(registerPasswordT);

		//confirm password label
		confirmPasswordL = new JLabel("Confirm password : "); 
		confirmPasswordL.setBounds(100,550,500,30);
		fRegister.add(confirmPasswordL);

		//confirm password text field
		confirmPasswordT = new JPasswordField();
		confirmPasswordT.setBounds(350, 550, 200, 25);
		fRegister.add(confirmPasswordT);

		//submit button
		registerSubmitB = new JButton("Submit");
		registerSubmitB.setBounds(150,650,100,40);
		fRegister.add(registerSubmitB);

		registerSubmitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerSubmit();
			}
		});

		//reset button
		registerResetB = new JButton("Reset");  
		registerResetB.setBounds(400,650,100,40);
		fRegister.add(registerResetB);

		registerResetB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerReset();
			}
		});
	}
	public void registerReset() {
		registerFirstNameT.setText("");
		registerMiddleNameT.setText("");
		registerLastNameT.setText("");
		registerAccT.setText("");
		registerPasswordT.setText("");
		confirmPasswordT.setText("");
		registerPhoneT.setText("");
		registerEmailT.setText("");
		userNameT.setText("");
		registerDOBT.setText("");
	}
	public void sameUserNameError() {
		JFrame newF = new JFrame();
		newF.setSize(800,500); 
		newF.setLayout(null); 
		newF.setVisible(true);

		JLabel newl = new JLabel(); 
		newl.setBounds(50,50,500,30);
		newl.setText("This user name is already taken! please enter another user name");
		newF.add(newl);

		JButton newb = new JButton("Okay");
		newb.setBounds(150,150,100,40);
		newF.add(newb);

		newb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newF.dispose();
			}
		});
	}
	public void notStrongPassword() {
		JFrame frame = new JFrame("Password Error");
		frame.setSize(500,500);
		frame.setLayout(null); 
		frame.setVisible(true);
		
		JLabel l1 = new JLabel("Please enter the strong password!!!");
		l1.setHorizontalAlignment(l1.CENTER);
		l1.setBounds(30,30,500,30);
		frame.add(l1);
		
		JLabel l2 = new JLabel("Strong password must contain....");
		l2.setHorizontalAlignment(l2.CENTER);
		l2.setBounds(30,80,500,30);
		frame.add(l2);
		
		JLabel l3 = new JLabel("1. Atleast one uppercase character");
		l3.setHorizontalAlignment(l3.CENTER);
		l3.setBounds(50,130,500,30);
		frame.add(l3);
		
		JLabel l4 = new JLabel("2. Atleast one lowercase character");
		l4.setHorizontalAlignment(l4.CENTER);
		l4.setBounds(50,180,500,30);
		frame.add(l4);
		
		JLabel l5 = new JLabel("3. Atleast one digit between 0 to 9");
		l5.setHorizontalAlignment(l5.CENTER);
		l5.setBounds(50,230,500,30);
		frame.add(l5);
		
		JLabel l6 = new JLabel("4. Atleast 8 characters in total");
		l6.setHorizontalAlignment(l6.CENTER);
		l6.setBounds(50,280,500,30);
		frame.add(l6);
		
		JButton b = new JButton("Okay");
		b.setBounds(200,400,100,40);
		frame.add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

	}
	public void registerSubmit() {

		int userNameError = 0;

		firstName = registerFirstNameT.getText();
		middleName = registerMiddleNameT.getText();
		lastName = registerLastNameT.getText();
		accNo = registerAccT.getText();
		currentBalance = currentBalanceT.getText();
		emailID = registerEmailT.getText();
		phoneNo = registerPhoneT.getText();
		dob = registerDOBT.getText();
		userName = userNameT.getText();
		str1 = new String(registerPasswordT.getPassword());
		str2 = new String(confirmPasswordT.getPassword());
		
		for(int b=0;b<=r-1;b++) {
			if((obj.robj[b].userName).compareTo(userName) == 0) {
				sameUserNameError();
				userNameError = 1;
			}
		}
		
		if (userNameError == 0) {
			
			boolean hasUppercase1 = !str1.equals(str1.toLowerCase());
			boolean hasLowercase1 = !str1.equals(str1.toUpperCase());
			boolean hasUppercase2 = !str2.equals(str2.toLowerCase());
			boolean hasLowercase2 = !str2.equals(str2.toUpperCase());
			
			if(firstName.isEmpty() || middleName.isEmpty() || lastName.isEmpty() || str1.isEmpty() || 
					str2.isEmpty() || accNo.isEmpty() || currentBalance.isEmpty() || emailID.isEmpty() ||
					phoneNo.isEmpty() || dob.isEmpty() || userName.isEmpty()) {
				emptyField();
			}
			else if(str1.length()<8 || str2.length()<8 || !hasUppercase1 || !hasLowercase1 || !hasLowercase2
					|| !hasUppercase2) {
				notStrongPassword();
			}
			else if(str1.compareTo(str2)!=0) { 
				incorrectPassword();
			}
			else {
				fRegister.dispose();
				password = str1;
				category();
			}
		}
		else {
			userNameT.setText("");
		}
	}
}
class login extends Main {
	public void loginFields() {

		fLogin = new JFrame("Login");
		fLogin.setSize(800,500); 
		fLogin.setLayout(null); 
		fLogin.setVisible(true);

		//enter first name label
		loginFirstNameL = new JLabel("Enter First Name : "); 
		loginFirstNameL.setBounds(100,65,500,30);
		fLogin.add(loginFirstNameL);

		//accept first name text field
		loginFirstNameT = new JTextField();
		loginFirstNameT.setBounds(350, 70, 200, 25);
		fLogin.add(loginFirstNameT);

		//enter middle name label
		loginMiddleNameL = new JLabel("Enter Middle Name : "); 
		loginMiddleNameL.setBounds(100,115,500,30);
		fLogin.add(loginMiddleNameL);

		//accept middle name text field
		loginMiddleNameT = new JTextField();
		loginMiddleNameT.setBounds(350, 120, 200, 25);
		fLogin.add(loginMiddleNameT);

		//enter last name label
		loginLastNameL = new JLabel("Enter Last Name : "); 
		loginLastNameL.setBounds(100,165,500,30);
		fLogin.add(loginLastNameL);

		//accept last name text field
		loginLastNameT = new JTextField();
		loginLastNameT.setBounds(350, 170, 200, 25);
		fLogin.add(loginLastNameT);

		//enter user name
		userNameL = new JLabel("Enter User Name : "); 
		userNameL.setBounds(100,215,500,30);
		fLogin.add(userNameL);

		//accept user name
		userNameT = new JTextField();
		userNameT.setBounds(350, 220, 200, 25);
		fLogin.add(userNameT);

		//enter Account No label
		loginAccL = new JLabel("Enter Account No : "); 
		loginAccL.setBounds(100,265,500,30);
		fLogin.add(loginAccL);

		//accept Account No text field
		loginAccT = new JTextField();
		loginAccT.setBounds(350, 270, 200, 25);
		fLogin.add(loginAccT);


		//enter password label
		loginPasswordL = new JLabel("Enter Password : "); 
		loginPasswordL.setBounds(100,315,500,30);
		fLogin.add(loginPasswordL);

		//accept password text field
		loginPasswordT = new JPasswordField();
		loginPasswordT.setBounds(350, 320, 200, 25);
		fLogin.add(loginPasswordT);

		//submit button
		loginSubmitB = new JButton("Submit");  
		loginSubmitB.setBounds(150,400,100,40);
		fLogin.add(loginSubmitB);

		loginSubmitB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginSubmit();
			}
		});

		//reset button
		loginResetB = new JButton("Reset");  
		loginResetB.setBounds(400,400,100,40);
		fLogin.add(loginResetB);

		loginResetB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginReset();
			}
		});

	}
	public void loginReset() {
		loginFirstNameT.setText("");
		loginMiddleNameT.setText("");
		loginLastNameT.setText("");
		loginAccT.setText("");
		loginPasswordT.setText("");
		userNameT.setText("");
	}
	public void accDoesNotExist() {
		JFrame f1 = new JFrame("Account Not Found");
		f1.setSize(700,400); 
		f1.setLayout(null); 
		f1.setVisible(true);

		JLabel l1 = new JLabel("This account is not registered!!!"); 
		l1.setBounds(100,70,500,30);
		f1.add(l1);

		JLabel l2 = new JLabel("Please do registration first"); 
		l2.setBounds(100,120,500,30);
		f1.add(l2);

		JButton b1 = new JButton("Ok");  
		b1.setBounds(275,200,100,40);
		f1.add(b1);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f1.dispose();
			}
		});
	}
	public void loginSubmit() {
		firstName = loginFirstNameT.getText();
		middleName = loginMiddleNameT.getText();
		lastName = loginLastNameT.getText();
		String passwordL = new String(loginPasswordT.getPassword());
		accNo = loginAccT.getText();
		userName = userNameT.getText();

		if(firstName.isEmpty() || middleName.isEmpty() || userName.isEmpty() ||lastName.isEmpty() ||
				passwordL.isEmpty()) {
			emptyField();
		}
		else {
			int flag = 0;
			int valueOfx = x;
			for(int k=0;k<=r;k++) {
				if((accNo).equals(obj.robj[k].accNo) ) {
					firstName = obj.robj[k].firstName;
					middleName = obj.robj[k].middleName;
					lastName = obj.robj[k].lastName;
					accNo = obj.robj[k].accNo;
					currentBalance = obj.robj[k].currentBalance;
					emailID = obj.robj[k].emailID;
					phoneNo = obj.robj[k].phoneNo;
					dob = obj.robj[k].dob;
					password = obj.robj[k].password;
					userName = obj.robj[k].userName;

					for(int i=1;i<=valueOfx;i++) {
						label[i][0] = obj.robj[k].label[i][0];
						label[i][1] = obj.robj[k].label[i][1];
						label[i][2] = obj.robj[k].label[i][2];
					}

					++l;
					flag = 1;
					break;
				}
			}
			if(flag==1) {
				fLogin.dispose();
				category();
			}
			else {
				accDoesNotExist();
			}
		}
	}
}
public class BankManagementProject extends register {

	register robj[] = new register[10];
	login lobj[] = new login[9];

	BankManagementProject() {

		for(int y=0;y<10;y++) {
			robj[y] = new register();
		}

		for(int y=0;y<9;y++) {
			lobj[y] = new login();
		}
	}

	public void FirstFrame() {
		//main frame
		JFrame fMain = new JFrame("Bank Management System");
		fMain.setSize(800,500); //800 width 500 height
		fMain.setLayout(null); //using no layout managers
		fMain.setVisible(true); //making the frame visible

		//login label
		JLabel loginLabel = new JLabel("Already have an account? Login Here"); 
		loginLabel.setBounds(50,70,500,30);
		fMain.add(loginLabel);

		//register label
		JLabel registerLabel = new JLabel("Don't have any account? Register Here");
		registerLabel.setBounds(50,150,500,40);
		fMain.add(registerLabel);

		//login button
		JButton loginButton = new JButton("Login");  
		loginButton.setBounds(400,70,100,40);
		fMain.add(loginButton);

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fMain.dispose();

				if(l==0) {
					lobj[0].loginFields();
				}
				if(l==1) {
					lobj[1].loginFields();
				}
				if(l==2) {
					lobj[2].loginFields();
				}
				if(l==3) {
					lobj[3].loginFields();
				}
				if(l==4) {
					lobj[4].loginFields();
				}
				if(l==5) {
					lobj[5].loginFields();
				}
				if(l==6) {
					lobj[6].loginFields();
				}
				if(l==7) {
					lobj[7].loginFields();
				}
				if(l==8) {
					lobj[8].loginFields();
				}


			}
		});

		//register button
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(400,150,100,40);
		fMain.add(registerButton);

		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				fMain.dispose();

				if(r==0) {
					robj[0].registerFields();
				}
				if(r==1) {
					robj[1].registerFields();
				}
				if(r==2) {
					robj[2].registerFields();
				}
				if(r==3) {
					robj[3].registerFields();
				}
				if(r==4) {
					robj[4].registerFields();
				}
				if(r==5) {
					robj[5].registerFields();
				}
				if(r==6) {
					robj[6].registerFields();
				}
				if(r==7) {
					robj[7].registerFields();
				}
				if(r==8) {
					robj[8].registerFields();
				}
				if(r==9) {
					robj[9].registerFields();
				}
			}
		});
		
		JButton b = new JButton("Exit");
		b.setHorizontalAlignment(b.CENTER);
		b.setBounds(200,300,100,40);
		fMain.add(b);

		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fMain.dispose();
			}
		});
	}
	public static void main(String[] args) {

		obj.FirstFrame();

	}
}