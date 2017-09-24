package com.designer;

import java.text.DecimalFormat;
import javax.swing.JFrame;
import com.impinj.octane.ImpinjReader;
import com.impinj.octane.OctaneSdkException;
import com.impinj.octane.ReaderMode;
import com.impinj.octane.ReportMode;
import com.impinj.octane.SearchMode;
import com.impinj.octane.Settings;
import com.impinj.octane.Tag;
import com.impinj.octane.TagReport;
import com.impinj.octane.TagReportListener;
import javax.swing.JButton;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.JRadioButton;


public class RGfid {
	static final double pi = Math.PI;
	static final double frequency = 920.625;
	static final double wavelength = (3*Math.pow(10, 8))/(frequency*Math.pow(10,6));
	static final int N = 6;
	static ImpinjReader reader = new ImpinjReader();
	
	static double[] RealTimeReport = new double[N];
	static ArrayList<Double> ReportJD = new ArrayList<Double>();
	static ArrayList<Double> ReportQT= new ArrayList<Double>();
	static ArrayList<Double> ReportB = new ArrayList<Double>();
	
	static DecimalFormat s = new DecimalFormat("0.00");
	
	String hostname = "192.168.1.27";
	String temp_EPC = " ";
	int flag1 = 0,flag2 = 0,flag3 = 0;
    double Phase_t1_a1 = 0,Phase_t2_a1 = 0,Phase_t3_a1 = 0,Phase_t4_a1 = 0,Phase_t5_a1 = 0;
    double Phase_t1_a2 = 0,Phase_t2_a2 = 0,Phase_t2_a3 = 0,Phase_t4_a2 = 0,Phase_t5_a2 = 0;
    double Phase_t1_a3 = 0,Phase_t3_a2 = 0,Phase_t3_a3 = 0,Phase_t4_a3 = 0,Phase_t5_a3 = 0;
    double delta_t23_a1 = 0,delta_t12_a1 = 0,delta_t14_a1 = 0,delta_t23_a2 = 0, delta_t15_a3 = 0, delta_t34_a3 = 0;
   
    private JFrame frame;
    private JLabel lblIp,label,lblNewLabel_1,tagDetect,label_train,label_6,label_7,label_8,label_9,save_flag;
	private JTextField IP_text,State_text,realtime_1,realtime_2,realtime_3,realtime_4,realtime_5,realtime_6,JD_1,JD_2,JD_3,JD_4,JD_5,JD_6,QT_1,QT_2,QT_3,QT_4,QT_5,QT_6,B_1,B_2,B_3,B_4,B_5,B_6;
	private JButton btnConnect,btn_refresh,btnRun,InputBT;
	@SuppressWarnings("rawtypes")
	private	JComboBox comboBox ;
	private  JRadioButton radioButton1,radioButton2;
	
	
	public RGfid() {
		
		initialize();	
		
	}

	public static void main(String[] args) {
 
		RGfid window = new RGfid();
		window.frame.setVisible(true);
				
	}
	
	// 读写器报告监听
    TagReportListener listener = new TagReportListener() {
		
		public void onTagReported(ImpinjReader reader, TagReport report) {
    		//DecimalFormat s = new DecimalFormat("0.000");	
            List<Tag> tags = report.getTags();                 
            for (Tag t : tags) {
               temp_EPC = t.getEpc().toString();
               if (t.getAntennaPortNumber() == 1) {           	
            	   switch (temp_EPC) {
				   case "3008 33B2 DDD9 0140 0000 0001":
			           Phase_t1_a1 = 2*pi-t.getPhaseAngleInRadians();   			                          	                                    
				       break;				       
				   case "3008 33B2 DDD9 0140 0000 0002":
	                   Phase_t2_a1 = 2*pi-t.getPhaseAngleInRadians();	                   
	                   break;
				   case "3008 33B2 DDD9 0140 0000 0003":
	                   Phase_t3_a1 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;	
				   case "3008 33B2 DDD9 0140 0000 0004":
	                   Phase_t4_a1 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;				
				   case "3008 33B2 DDD9 0140 0000 0005":
	                   Phase_t5_a1 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;				
				   default:
					   break;
				 }
            	   
            	   if (Phase_t1_a1*Phase_t2_a1*Phase_t3_a1*Phase_t4_a1*Phase_t5_a1!=0) flag1=1;    
            	   
              }
               
               if (t.getAntennaPortNumber() == 2) {
            	     switch (temp_EPC) {
				   case "3008 33B2 DDD9 0140 0000 0001":
			           Phase_t1_a2 = 2*pi-t.getPhaseAngleInRadians();  
				       break;
				   case "3008 33B2 DDD9 0140 0000 0002":
	                   Phase_t2_a2 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;
				   case "3008 33B2 DDD9 0140 0000 0003":
	                   Phase_t3_a2 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;
				   case "3008 33B2 DDD9 0140 0000 0004":
	                   Phase_t4_a2 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;				
				   case "3008 33B2 DDD9 0140 0000 0005":
	                   Phase_t5_a2 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;				
				   default:
					   break;
					           		             	     
				}
            	   
            	   if (Phase_t1_a2*Phase_t2_a2*Phase_t3_a2*Phase_t4_a2*Phase_t5_a2!=0) flag2=1; 
            	   
               }
               
               if (t.getAntennaPortNumber() == 3) {
            	   switch (temp_EPC) {
				   case "3008 33B2 DDD9 0140 0000 0001":
			           Phase_t1_a3 = 2*pi-t.getPhaseAngleInRadians(); 
				       break;
				   case "3008 33B2 DDD9 0140 0000 0002":
	                   Phase_t2_a3 = 2*pi-t.getPhaseAngleInRadians();	 
	                   break;
				   case "3008 33B2 DDD9 0140 0000 0003":
	                   Phase_t3_a3 = 2*pi-t.getPhaseAngleInRadians();	  
	                   break;
				   case "3008 33B2 DDD9 0140 0000 0004":
	                   Phase_t4_a3 = 2*pi-t.getPhaseAngleInRadians();	 
	                   break;				
				   case "3008 33B2 DDD9 0140 0000 0005":
	                   Phase_t5_a3 = 2*pi-t.getPhaseAngleInRadians();	                  
	                   break;				
				   default:
					   break;
				}
            	   
            	   if (Phase_t1_a3*Phase_t2_a3*Phase_t3_a3*Phase_t4_a3*Phase_t5_a3!=0) flag3=1;  
            	   
               }
               
              if (flag1*flag2*flag3 != 0){           	               	 
            	    tagDetect.setText("检测结果正常！");   
            	    State_text.setText("目标标签已就绪！");
            	    delta_t23_a1 = Math.abs(Phase_t3_a1 - Phase_t2_a1); 
                if (delta_t23_a1 > pi & delta_t23_a1 < 2*pi) delta_t23_a1 = 2*pi - delta_t23_a1; 
                RealTimeReport[0] = 100*(delta_t23_a1*wavelength)/(4*pi); 
                realtime_1.setText(s.format(RealTimeReport[0]));
                
                delta_t12_a1 = Math.abs(Phase_t1_a1 - Phase_t2_a1);
                if (delta_t12_a1 > pi & delta_t12_a1 < 2*pi) delta_t12_a1 = 2*pi - delta_t12_a1;            	                           
                RealTimeReport[1] = 100*(delta_t12_a1*wavelength)/(4*pi); 
                realtime_2.setText(s.format(RealTimeReport[1]));
                                
                delta_t14_a1 = Math.abs(Phase_t1_a1 - Phase_t4_a1);
                if (delta_t14_a1 > pi & delta_t14_a1 < 2*pi) delta_t14_a1 = 2*pi - delta_t14_a1;            	                           
                RealTimeReport[2]=100*(delta_t14_a1*wavelength)/(4*pi); 
                realtime_3.setText(s.format(RealTimeReport[2])); 
                  
                delta_t23_a2 = Math.abs(Phase_t3_a2 - Phase_t2_a2);
                if (delta_t23_a2 > pi & delta_t23_a2 < 2*pi) delta_t23_a2 = 2*pi - delta_t23_a2;                	                              
                RealTimeReport[3]=100*(delta_t23_a2*wavelength)/(4*pi); 
                realtime_4.setText(s.format(RealTimeReport[3]));
                
                delta_t15_a3 = Math.abs(Phase_t1_a3 - Phase_t5_a3);
                if (delta_t15_a3 > pi & delta_t15_a3 < 2*pi) delta_t15_a3 = 2*pi - delta_t15_a3;                	                              
                RealTimeReport[4]=100*(delta_t15_a3*wavelength)/(4*pi); 
                realtime_5.setText(s.format(RealTimeReport[4]));
                
                delta_t34_a3 = Math.abs(Phase_t3_a3 - Phase_t4_a3);
                if (delta_t34_a3 > pi & delta_t34_a3 < 2*pi) delta_t34_a3 = 2*pi - delta_t34_a3;                	                              
                RealTimeReport[5]=100*(delta_t34_a3*wavelength)/(4*pi);  
                realtime_6.setText(s.format(RealTimeReport[5]));
                
                if(!ReportJD.isEmpty()) {
					  JD_1.setText(s.format(ReportJD.get(0)));
				      JD_2.setText(s.format(ReportJD.get(1)));
				      JD_3.setText(s.format(ReportJD.get(2)));
				      JD_4.setText(s.format(ReportJD.get(3)));
				      JD_5.setText(s.format(ReportJD.get(4)));
				      JD_6.setText(s.format(ReportJD.get(5)));
					 }
				else {
				    	  JD_1.setText("");JD_2.setText("");JD_3.setText("");
				    	  JD_4.setText("");JD_5.setText("");JD_6.setText("");
					  }
				   
			    if(!ReportQT.isEmpty()){
				      QT_1.setText(s.format(ReportQT.get(0)));
				      QT_2.setText(s.format(ReportQT.get(1)));
				      QT_3.setText(s.format(ReportQT.get(2)));
				      QT_4.setText(s.format(ReportQT.get(3)));
				      QT_5.setText(s.format(ReportQT.get(4)));
				      QT_6.setText(s.format(ReportQT.get(5)));
				      }
			    else {
				  	  QT_1.setText("");QT_2.setText("");QT_3.setText("");
				  	  QT_4.setText("");QT_5.setText("");QT_6.setText("");
					  }
			    
			    if(!ReportQT.isEmpty()){
				       B_1.setText(s.format(ReportB.get(0)));
					   B_2.setText(s.format(ReportB.get(1)));
					   B_3.setText(s.format(ReportB.get(2)));
					   B_4.setText(s.format(ReportB.get(3)));
					   B_5.setText(s.format(ReportB.get(4)));
					   B_6.setText(s.format(ReportB.get(5)));
				       }
		        else {
				    	  B_1.setText("");B_2.setText("");B_3.setText("");
				    	  B_4.setText("");B_5.setText("");B_6.setText("");
				      }
    				if (!ReportJD.isEmpty()&& !ReportQT.isEmpty() && !ReportB.isEmpty()) {
    					
    					radioButton1.setForeground(Color.BLACK);
    			      	InputBT.setForeground(Color.BLACK);
    			      	btn_refresh.setForeground(Color.BLACK);
    			      	
    					if (Match() == ReportJD) {    						
    						lblNewLabel_1.setIcon(new ImageIcon("/Users/chengkang/Documents/图片/JD.png")); 
    						State_text.setText("识别手势为剪刀");  
    						
    					} 
    					else if (Match() == ReportB) {
    						lblNewLabel_1.setIcon(new ImageIcon("/Users/chengkang/Documents/图片/B.png"));
    						State_text.setText("识别手势为布"); 
    					}
    					else {
    						lblNewLabel_1.setIcon(new ImageIcon("/Users/chengkang/Documents/图片/QT.png"));
    					    State_text.setText("识别手势为石头"); }
    				    }
    				else {
    				    btn_refresh.setForeground(Color.GRAY);
    				    radioButton1.setForeground(Color.GRAY);
    			      	InputBT.setForeground(Color.GRAY);
    				}
              }
              
              else State_text.setText("尚未检测出目标标签就绪");  
              
            }
        }
    };  
    
    //保存训练数据
    static public void TrainSave(ArrayList<Double> arr){   	
    	  for(int i = 0; i < RealTimeReport.length; i++) {
    		  arr.add(RealTimeReport[i]);
    	  }   	  
    }
    
    //欧氏距离
    static public double EuclidDistance(ArrayList<Double> arr) {
    	  double distance = 0;
  	  if(arr.size() != RealTimeReport.length) return -1;  	  		          
  	  else{  
           for (int i = 0; i < RealTimeReport.length; i++) {  
               double temp = Math.pow((RealTimeReport.length - arr.get(i)), 2);  
               distance += temp;  
           }  
           distance = Math.sqrt(distance);  
      }  
  	  return distance;  	
   }
    
    //手势类别匹配
    static public ArrayList<Double> Match( ) {   	
    	  double temp = Compare(EuclidDistance(ReportB), EuclidDistance(ReportJD), EuclidDistance(ReportQT));
    	  if(temp == EuclidDistance(ReportB)) return ReportB;
    	  else if(temp == EuclidDistance(ReportJD)) return ReportJD;	 
    	  else return ReportQT;
    }
    
    //计算最小值
    static public double Compare(double A,double B,double C) {
		return ((A<B?A:B)<C?(A<B?A:B):C);   	      	
    }
    
    //鼠标事件，保存训练集
    MouseAdapter trainSave = new MouseAdapter() {
      	public void mouseClicked(MouseEvent e) {
      		switch(comboBox.getSelectedItem().toString()) {
      		   case "剪刀": 
      		       TrainSave(ReportJD);
      		       save_flag.setForeground(Color.green);
      		       save_flag.setText("success!");
      		       File file1 = new File("/Users/chengkang/Documents/JD.txt");			    			
     			   try {
     				     BufferedWriter out=new BufferedWriter(new FileWriter(file1));
     				     out.newLine();
     				     for(int i = 0 ;i < ReportJD.size();++i){
     					     out.write(s.format(ReportJD.get(i))+"\n");
     					     out.newLine();
     				}
     			   out.close(); 
     				  
     			} catch (IOException e1) {  				
     				e1.printStackTrace();
     			}	
      		       break;
      		   case "石头":
      		       TrainSave(ReportQT);
      		       save_flag.setForeground(Color.blue);
      		       save_flag.setText("success!");
      		       File file2 = new File("/Users/chengkang/Documents/QT.txt");			    			
   			       try {
   				         BufferedWriter out=new BufferedWriter(new FileWriter(file2));
   				         out.newLine();
   				         for(int i = 0 ;i < ReportQT.size();++i) {
   					         out.write(s.format(ReportQT.get(i))+"\n");
   					         out.newLine();
   				         }
   			       out.close(); 
   				  
   			       } catch (IOException e1) {  				
   				        e1.printStackTrace();
   			         }	
      		       break;
      		   case "布":
      			   TrainSave(ReportB);
      			   save_flag.setText("success!");
      			   save_flag.setForeground(Color.red);
      			   File file3 = new File("/Users/chengkang/Documents/B.txt");			    			
    			       try {
    				         BufferedWriter out=new BufferedWriter(new FileWriter(file3));
    				         out.newLine();
    				        for(int i = 0 ;i < ReportB.size();++i) {
    					     out.write(s.format(ReportB.get(i))+"\n");
    					     out.newLine();
    				        }
    			       out.close();    				  
    			       } catch (IOException e1) {  				
    				         e1.printStackTrace();
    			         }	
      			   break;
      		   default:
      			   break;
      		}  
      		//save_flag.setText("");
		}    	
	};
	
	//鼠标事件，连接读写器
	MouseAdapter connect = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(btnConnect.getText() == "连接"){
				try {
					
		            if (hostname == null) {
		                throw new Exception("Must specify the '"
		                        + hostname + "' property");
		            }
		            		            
		            // Connect to the reader
		            State_text.setText("开始连接: "+hostname+" ...");
		            reader.connect(hostname);
		            IP_text.setText(hostname);
		            // Get the default settings
		            Settings settings = reader.queryDefaultSettings();
		            settings.getReport().setMode(ReportMode.Individual);
		            settings.setReaderMode(ReaderMode.MaxThroughput);
		            settings.setSearchMode(SearchMode.DualTarget);
		            settings.getReport().setIncludeFirstSeenTime(true);
		            settings.getReport().setIncludePhaseAngle(true);
		            settings.getReport().setIncludePeakRssi(true);
                    settings.getReport().setIncludeAntennaPortNumber(true); 
		            settings.getAntennas().disableAll();
		            settings.getAntennas().getAntenna((short) 1 ).setEnabled(true);
		            settings.getAntennas().getAntenna((short) 2 ).setEnabled(true);
		            settings.getAntennas().getAntenna((short) 3 ).setEnabled(true);
		            
		            // Apply the new settings
		            reader.applySettings(settings);
		            reader.setTagReportListener(listener);
		            btnConnect.setText("断开");

				} catch (OctaneSdkException ex) {
			        System.out.println(ex.getMessage());
			    } catch (Exception ex) {
			        System.out.println(ex.getMessage());
			        ex.printStackTrace(System.out);
			    }
				State_text.setText("已连接到: "+hostname+" ...");
				
			}
			else{
				try {
					reader.stop();
				} catch (OctaneSdkException e1) {					
					e1.printStackTrace();
				}
				reader.disconnect();
				IP_text.setText("");
				btnConnect.setText("连接");
				State_text.setText("断开与 "+hostname+" 的通信...");
				
			}
		

		}			
				
    };
   
    //鼠标事件，初始化数据为0
    MouseAdapter refresh = new MouseAdapter() {
    	public void mouseClicked(MouseEvent e) {
    		    State_text.setText("初始化训练集...");
			ReportB.clear();
			ReportJD.clear();
			ReportQT.clear();
			
			radioButton1.setForeground(Color.GRAY);
			InputBT.setForeground(Color.GRAY);
			State_text.setText("初始化完毕！");
		}
	};
	
	MouseAdapter input = new MouseAdapter() {
    	public void mouseClicked(MouseEvent e) {
    		try {
    		     FileReader fileReader = new FileReader("/Users/chengkang/Documents/JD.txt");
             @SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(fileReader);
             int i = 0;           
             double readLine = 0.0;              
             while((readLine = Double.valueOf(buf.readLine()) ) != 0){
                 ReportJD.set(i, readLine);
                 i++;
             }
        }
         catch (Exception e1) {
             e1.printStackTrace();          
         }
    		try {
   		     FileReader fileReader = new FileReader("/Users/chengkang/Documents/QT.txt");
            @SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(fileReader);
            int i = 0;           
            double readLine = 0.0;              
            while((readLine = Double.valueOf(buf.readLine()) ) != 0){
                ReportQT.set(i, readLine);
                i++;
            }
       }
        catch (Exception e1) {
            e1.printStackTrace();          
        }
    		try {
   		    FileReader fileReader = new FileReader("/Users/chengkang/Documents/B.txt");
            @SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(fileReader);
            int i = 0;           
            double readLine = 0.0;              
            while((readLine = Double.valueOf(buf.readLine()) ) != 0){
                ReportB.set(i, readLine);
                i++;
            }
       }
        catch (Exception e1) {
            e1.printStackTrace();          
        }
	}
};
	
	//鼠标事件，读写器开启
	MouseAdapter run = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			if(btnRun.getText() == "开始"){				
            	btnRun.setText("暂停");
            	try {
					reader.start();
				} catch (OctaneSdkException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
                
            	State_text.setText("阅读器开始工作...");
            	
			}
			else {
				btnRun.setText("开始");
				try {
					reader.stop();
				} catch (OctaneSdkException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				State_text.setText("阅读器暂停工作...");
			}
		}
	};

	
	
	//程序初始化
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
	  frame = new JFrame("基于RFID标签运动轨迹的手势识别系统演示");
	  frame.getContentPane().setForeground(SystemColor.textHighlight);
	  frame.setBounds(100, 100, 624, 414);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.getContentPane().setLayout(null);
	  
	  btnConnect = new JButton("连接");
	  btnConnect.setForeground(Color.BLACK);
	  btnConnect.setBounds(188, 50, 56, 27);
	  btnConnect.addMouseListener(connect);
	  frame.getContentPane().add(btnConnect);
		
	  btnRun = new JButton("开始");
	  btnRun.setForeground(Color.BLACK);
	  btnRun.setBackground(new Color(51, 153, 204));
	  btnRun.setBounds(188, 77, 56, 29);
	  btnRun.addMouseListener(run); 
	  frame.getContentPane().add(btnRun);
	
      lblIp = new JLabel("读写器IP:");
      lblIp.setForeground(Color.BLACK);
	  lblIp.setBounds(6, 54, 73, 16);
	  frame.getContentPane().add(lblIp);
	  
	  IP_text = new JTextField();
	  IP_text.setBounds(76, 49, 102, 26);
	  IP_text.setColumns(10);
	  frame.getContentPane().add(IP_text);
	
	
	
	  label = new JLabel("执行结果显示");
	  label.setBounds(378, 17, 114, 20);
	  label.setFont(new Font("Lucida Grande", Font.BOLD, 16));
	  frame.getContentPane().add(label);
	  
	 btn_refresh = new JButton("一键初始化");
	 btn_refresh.setForeground(Color.GRAY);
	 btn_refresh.setBounds(76, 110, 95, 29);
	 btn_refresh.setToolTipText("初始化训练数据");
	 btn_refresh.addMouseListener(refresh);
	 frame.getContentPane().add(btn_refresh);
	  
	JSeparator separator = new JSeparator();
	separator.setBounds(114, 461, 137, 0);
	separator.setOrientation(SwingConstants.VERTICAL);
	frame.getContentPane().add(separator);
	
	JSeparator separator_2 = new JSeparator();
	separator_2.setBounds(461, 216, 1, 0);
	separator_2.setOrientation(SwingConstants.VERTICAL);
	frame.getContentPane().add(separator_2);
	
	JSeparator separator_1 = new JSeparator();
	separator_1.setBackground(Color.WHITE);
	separator_1.setBounds(0, 136, 270, 12);
	frame.getContentPane().add(separator_1);
	
	JSeparator separator_5 = new JSeparator();
	separator_5.setBackground(Color.WHITE);
	separator_5.setOrientation(SwingConstants.VERTICAL);
	separator_5.setBounds(263, 0, 11, 481);
	frame.getContentPane().add(separator_5);
	
	lblNewLabel_1 = new JLabel("");
	lblNewLabel_1.setIcon(new ImageIcon("/Users/chengkang/Documents/图片/blank.png"));
	lblNewLabel_1.setBounds(321, 50, 264, 187);
	frame.getContentPane().add(lblNewLabel_1);
	
	JSeparator separator_3 = new JSeparator();
	separator_3.setBackground(Color.WHITE);
	separator_3.setBounds(0, 286, 765, 12);
	frame.getContentPane().add(separator_3);
	
	State_text = new JTextField();
	State_text.setBounds(26, 323, 225, 63);
	frame.getContentPane().add(State_text);
	State_text.setColumns(10);
	
	JLabel label_2 = new JLabel("系统控制模块");
	label_2.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	label_2.setBounds(76, 17, 102, 16);
	frame.getContentPane().add(label_2);
	
	JLabel label_3 = new JLabel("系统训练模块");
	label_3.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	label_3.setBounds(76, 151, 102, 16);
	frame.getContentPane().add(label_3);
	
	JSeparator separator_4 = new JSeparator();
	separator_4.setBackground(Color.WHITE);
	separator_4.setBounds(0, 0, 753, 12);
	frame.getContentPane().add(separator_4);
	
	JSeparator separator_6 = new JSeparator();
	separator_6.setBackground(Color.WHITE);
	separator_6.setBounds(0, 459, 788, 12);
	frame.getContentPane().add(separator_6);
	
	JSeparator separator_7 = new JSeparator();
	separator_7.setOrientation(SwingConstants.VERTICAL);
	separator_7.setBounds(736, 0, 11, 481);
	frame.getContentPane().add(separator_7);
	
	JLabel label_4 = new JLabel("当前训练类型：");
	label_4.setForeground(Color.BLACK);
	label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
	label_4.setBounds(63, 234, 96, 29);
	frame.getContentPane().add(label_4);
	
	comboBox = new JComboBox();
	comboBox.setForeground(Color.BLACK);
	comboBox.setBackground(new Color(255, 255, 255));
	comboBox.setModel(new DefaultComboBoxModel(new String[] {"无", "剪刀", "石头", "布"}));
	comboBox.setBounds(159, 227, 96, 47);
	frame.getContentPane().add(comboBox);
	comboBox.getSelectedItem();
	
	JLabel label_5 = new JLabel("系统状态");
	label_5.setFont(new Font("Lucida Grande", Font.BOLD, 15));
	label_5.setBounds(94, 307, 65, 16);
	frame.getContentPane().add(label_5);
	
	JButton btnSave = new JButton("保存");
	btnSave.setForeground(Color.BLACK);	
	btnSave.setToolTipText("保持训练数据集");
	btnSave.setBounds(83, 264, 95, 27);
	btnSave.addMouseListener(trainSave);
	frame.getContentPane().add(btnSave);
	
	JLabel label_1 = new JLabel("环境检测：");
	label_1.setForeground(Color.BLACK);
	label_1.setBounds(6, 82, 67, 16);
	frame.getContentPane().add(label_1);
	
    tagDetect = new JLabel("");
    tagDetect.setForeground(Color.RED);
	tagDetect.setBounds(86, 82, 107, 16);
	frame.getContentPane().add(tagDetect);
	
    InputBT = new JButton("一键导入");
	InputBT.addMouseListener(input);
	InputBT.setForeground(Color.GRAY);
	InputBT.setBounds(149, 179, 95, 27);
	frame.getContentPane().add(InputBT);
	
	radioButton1 = new JRadioButton("本地已存在训练集：");
	radioButton1.setFont(new Font("Heiti TC", Font.BOLD, 13));
	radioButton1.setForeground(Color.GRAY);
	radioButton1.setBounds(6, 172, 153, 35);
	frame.getContentPane().add(radioButton1);
	
	radioButton2 = new JRadioButton("初次使用：");
	radioButton2.setFont(new Font("Heiti TC", Font.BOLD, 13));
	radioButton2.setForeground(Color.BLACK);
	radioButton2.setBounds(6, 214, 153, 23);
	frame.getContentPane().add(radioButton2);
	
	label_train = new JLabel("");
	label_train.setForeground(Color.RED);
	label_train.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
	label_train.setBounds(97, 216, 96, 16);
	frame.getContentPane().add(label_train);
	
	label_6 = new JLabel("实时数据集：");
	label_6.setFont(new Font("Lucida Grande", Font.BOLD, 12));
	label_6.setBounds(279, 302, 102, 16);
	frame.getContentPane().add(label_6);
	
	label_7 = new JLabel("剪刀训练集：");
	label_7.setFont(new Font("Lucida Grande", Font.BOLD, 12));
	label_7.setBounds(279, 324, 84, 16);
	frame.getContentPane().add(label_7);
	
	label_8 = new JLabel("拳头训练集：");
	label_8.setFont(new Font("Lucida Grande", Font.BOLD, 12));
	label_8.setBounds(279, 346, 84, 16);
	frame.getContentPane().add(label_8);
	
	label_9 = new JLabel("布训练集：");
	label_9.setFont(new Font("Lucida Grande", Font.BOLD, 12));
	label_9.setBounds(290, 368, 84, 16);
	frame.getContentPane().add(label_9);
	
	realtime_1 = new JTextField();
	realtime_1.setColumns(10);
	realtime_1.setBounds(348, 303, 45, 16);
	frame.getContentPane().add(realtime_1);
	
	realtime_2 = new JTextField();
	realtime_2.setColumns(10);
	realtime_2.setBounds(393, 303, 45, 16);
	frame.getContentPane().add(realtime_2);
	
	realtime_3 = new JTextField();
	realtime_3.setColumns(10);
	realtime_3.setBounds(438, 303, 45, 16);
	frame.getContentPane().add(realtime_3);
	
	realtime_4 = new JTextField();
	realtime_4.setColumns(10);
	realtime_4.setBounds(483, 303, 45, 16);
	frame.getContentPane().add(realtime_4);
	
	realtime_5 = new JTextField();
	realtime_5.setColumns(10);
	realtime_5.setBounds(528, 303, 45, 16);
	frame.getContentPane().add(realtime_5);
	
	realtime_6 = new JTextField();
	realtime_6.setColumns(10);
	realtime_6.setBounds(573, 303, 45, 16);
	frame.getContentPane().add(realtime_6);
	
	JD_1 = new JTextField();
	JD_1.setColumns(10);
	JD_1.setBounds(348, 324, 45, 16);
	frame.getContentPane().add(JD_1);
	
	QT_1 = new JTextField();
	QT_1.setColumns(10);
	QT_1.setBounds(348, 346, 45, 16);
	frame.getContentPane().add(QT_1);
	
	B_1 = new JTextField();
	B_1.setColumns(10);
	B_1.setBounds(348, 370, 45, 16);
	frame.getContentPane().add(B_1);
	
	JD_2 = new JTextField();
	JD_2.setColumns(10);
	JD_2.setBounds(393, 324, 45, 16);
	frame.getContentPane().add(JD_2);
	
	QT_2 = new JTextField();
	QT_2.setColumns(10);
	QT_2.setBounds(393, 346, 45, 16);
	frame.getContentPane().add(QT_2);
	
	B_2 = new JTextField();
	B_2.setColumns(10);
	B_2.setBounds(393, 370, 45, 16);
	frame.getContentPane().add(B_2);
	
	JD_3 = new JTextField();
	JD_3.setColumns(10);
	JD_3.setBounds(438, 324, 45, 16);
	frame.getContentPane().add(JD_3);
	
	QT_3 = new JTextField();
	QT_3.setColumns(10);
	QT_3.setBounds(438, 346, 45, 16);
	frame.getContentPane().add(QT_3);
	
	B_3 = new JTextField();
	B_3.setColumns(10);
	B_3.setBounds(438, 370, 45, 16);
	frame.getContentPane().add(B_3);
	
	JD_4 = new JTextField();
	JD_4.setColumns(10);
	JD_4.setBounds(483, 324, 45, 16);
	frame.getContentPane().add(JD_4);
	
	QT_4 = new JTextField();
	QT_4.setColumns(10);
	QT_4.setBounds(483, 346, 45, 16);
	frame.getContentPane().add(QT_4);
	
	B_4 = new JTextField();
	B_4.setColumns(10);
	B_4.setBounds(483, 370, 45, 16);
	frame.getContentPane().add(B_4);
	
	JD_5 = new JTextField();
	JD_5.setColumns(10);
	JD_5.setBounds(528, 324, 45, 16);
	frame.getContentPane().add(JD_5);
	
	QT_5 = new JTextField();
	QT_5.setColumns(10);
	QT_5.setBounds(528, 346, 45, 16);
	frame.getContentPane().add(QT_5);
	
	B_5 = new JTextField();
	B_5.setColumns(10);
	B_5.setBounds(528, 370, 45, 16);
	frame.getContentPane().add(B_5);
	
	JD_6 = new JTextField();
	JD_6.setColumns(10);
	JD_6.setBounds(573, 324, 45, 16);
	frame.getContentPane().add(JD_6);
	
	QT_6 = new JTextField();
	QT_6.setColumns(10);
	QT_6.setBounds(573, 346, 45, 16);
	frame.getContentPane().add(QT_6);
	
	B_6 = new JTextField();
	B_6.setColumns(10);
	B_6.setBounds(573, 370, 45, 16);
	frame.getContentPane().add(B_6);
	
	save_flag = new JLabel("");
	save_flag.setBounds(176, 268, 75, 16);
	frame.getContentPane().add(save_flag);
	
  }
}

		
