package Controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Model.AtClient;
import Model.AtServer;
import Model.Danh_sach;
import Model.Nhac;
import Model.Tac_gia;
import Model.The_loai;
import Model.User;
import Services.AtClientImpl;
import Services.AtServerImpl;
import View.AddView;
import View.EditView;
import View.ListView;
import View.Login;
import View.MainView;
import View.addDSView;

public class Client {
	
	//=======
	Login login = new Login();
	MainView mv = new MainView();
	AddView av = new AddView();
	EditView ev = new EditView();
	ListView lv = new ListView();
	addDSView adsv = new addDSView();
	//=======
	AtClient client = new AtClientImpl();
	AtServer server = new AtServerImpl();
	//=======
	ArrayList<Nhac> ListNhac = new ArrayList<>();
	ArrayList<Danh_sach> ListDanh_sach = new ArrayList<>();
	ArrayList<The_loai> ListThe_Loai = new ArrayList<>();
	ArrayList<Tac_gia> ListTac_gia = new ArrayList<>();
	User user  = new User();
	Danh_sach danh_sach = new Danh_sach();
	//=======
	//boolean delModButton = true;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Client() throws Exception {
		// TODO Auto-generated method stub
		UnicastRemoteObject.exportObject(client, 3101);
		server = (AtServer) Naming.lookup("//localhost/srvobj");
		server.registerClient(client);
		server.callServerMethod("Client contactServer");
		
		
//login frame 
	// button login
		login.logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User();
				user.setName(login.getUser());
				user.setPassword(login.getPass());
				
				if (login.modLogin) {
					try {
						if (server.checkLogin(user)) {
							login.setVisible(false);
							Login(user);
							login.PassWordField.setText(null);
							login.ConfirmPassField.setText(null);
							
						} else {
							JOptionPane.showMessageDialog(login, "Tài khoản hoặc mật khâu không hợp lệ!!!");
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					if (user.isName()) {
						if (user.isPassword()) {
							if (user.getPassword().equals(login.getConfirmPass())) {
								try {
									if (server.checkRegister(user)) {
										login.setVisible(false);
										login.PassWordField.setText(null);
										login.ConfirmPassField.setText(null);
										login.ChangeMod();
										Login(user);
									} else {
										JOptionPane.showMessageDialog(login, "Tài khoản đã tồn tại");
									}
								} catch (RemoteException e2) {
									// TODO Auto-generated catch block
									e2.printStackTrace();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								
							} else {
								JOptionPane.showMessageDialog(login, "Mật khẩu không khớp");
							}
						} else {
							JOptionPane.showMessageDialog(login, "Mật khẩu phải từ 3-20 kí tự");
						}
					} else {
						JOptionPane.showMessageDialog(login, "Tên tài khoản phải có từ 3-12 kí tự, không chứa kí tự đặc biệt ngoại trừ '-', '_'");
					}
				}
			}
		});
	// button SignUp
		login.SignUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login.ChangeMod();
			}
			
		});
	// button Show/Hide pass
		login.ShowHideButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (login.modShowHide) {
					login.PassWordField.setEchoChar((char) 0);
					login.modShowHide = false;
					login.ShowHideButton.setText("Hide");
				} else {
					login.PassWordField.setEchoChar('•');
					login.modShowHide = true;
					login.ShowHideButton.setText("Show");
				}
				
			}
			
		});

	// enter -> login
		login.PassWordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ActionEvent s = null;
					login.logInButton.getActionListeners()[0].actionPerformed(s);
				}
			}
		});
		
		
//
		
// addview 
		ListThe_Loai = server.getListThe_loai();
		av.The_loai.setModel(new DefaultComboBoxModel(getTheLoai()));
		
	// button add
		av.AddN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Nhac n =  new Nhac();
				
				n.setMa(av.Ma.getText());
				n.setTen(av.Ten.getText());
				n.setTac_gia(av.Tac_gia.getText());
				n.setLoi(av.Loi_bai_hat.getText());
				n.setHinh_thuc((String) av.Hinh_thuc.getSelectedItem());
				n.setThe_loai((String) av.The_loai.getSelectedItem());
				
				if (n.isNhac()) {
					try {
						if (server.addNhac(n)) {
							av.Refresh();
							try {
								ListNhac = server.getListNhac();
								ShowList();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(av, "Mã bài hát đã tồn tại");
						}
	
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(av, "Mã bài hất phải là dãy các số, tối đa 9 số");
				}
			}
			
		});
		
// editview
		ev.EditThe_loai.setModel(new DefaultComboBoxModel(getTheLoai()));
		
	// button save
		
		ev.SaveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (ev.modnumber) {
					Nhac n = new Nhac();
					n.setMa(ev.EditMa.getText());
					n.setTen(ev.EditTen.getText());
					n.setLoi(ev.EditLoi_mo_dau.getText());
					n.setTac_gia(ev.EditTac_Gia.getText());
					n.setThe_loai((String) ev.EditThe_loai.getSelectedItem());
					n.setHinh_thuc((String) ev.EditHinh_Thuc.getSelectedItem());
					n.isNhac();
					
					try {
						if (server.editNhac(n)) {
							ListNhac = server.getListNhac();
							ShowList();
							ev.setVisible(false);
							JOptionPane.showMessageDialog(ev, "Chỉnh sửa thành công!");
							
						} else {
							JOptionPane.showMessageDialog(ev, "Chỉnh sửa thất bại");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else {
					danh_sach.setName(ev.EditTen.getText());
					if (danh_sach.isNameDS()) {
						try {
							if (server.editDSYT(danh_sach)) {
								ShowListDS();
								ev.setVisible(false);
								JOptionPane.showMessageDialog(adsv, "Chỉnh sửa thành công");
							} else {
								JOptionPane.showMessageDialog(adsv, "Lỗi");
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(adsv, "Tên danh sách phải từ 1 -50 kí tự và không chứa kí tự đặc biệt");
					}
				}
			}
			
		});
		
//
// listView
	// button ok
		lv.OkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Danh_sach addDS = new Danh_sach();
				
				if (!lv.ok) {
					Nhac addN = new Nhac();
					addN = ListNhac.get(mv.table.getSelectedRow());
					addDS = ListDanh_sach.get(lv.tableDS.getSelectedRow());
					try {
						if (server.addNhacToDS(addDS, addN)) {
							lv.setVisible(false);
							JOptionPane.showMessageDialog(av, "Đã thêm bài hát vào danh sach");
						} else {
							JOptionPane.showMessageDialog(av, "Lỗi");
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
					} else {
						try {
							if (lv.tableDS.getSelectedRow() >= 0) {
								addDS = ListDanh_sach.get(lv.tableDS.getSelectedRow());
								showNhacInDS(addDS);
								danh_sach = addDS;
							}
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						mv.buttonDropMod = false;
						mv.DropButton.setVisible(true);
						
				}
				lv.setVisible(false);
				lv.tableDS.clearSelection();
			}
			
		});
	
	// button del
		lv.DeleteDSButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lv.tableDS.getSelectedRow() >= 0) {
					Danh_sach ds = new Danh_sach();
					ds = ListDanh_sach.get(lv.tableDS.getSelectedRow());
					try {
						if (server.deleteDSYT(ds)) {
							ShowListDS();
							if (danh_sach.getId() == ds.getId()) {
								ListNhac = server.getListNhac();
								ShowList();
							}
							JOptionPane.showMessageDialog(av, "Đã xóa dnah sách");
						} else {
							JOptionPane.showMessageDialog(av, "Lỗi");
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
	
	// button them danh sach
		
		lv.NewDSButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				adsv.setVisible(true);
			}
			
		});

	// button sua danh sach
		
		lv.EditDSButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (lv.tableDS.getSelectedRow() >= 0) {
					ev.changeModEditDS();
					danh_sach = ListDanh_sach.get(lv.tableDS.getSelectedRow());
					ev.setVisible(true);
					ev.EditTen.setText(ListDanh_sach.get(lv.tableDS.getSelectedRow()).getName());
				}
			}
			
		});

// addDSView
	// button ok
		adsv.OKtenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Danh_sach d = new Danh_sach();
				d.setName(adsv.NameDS.getText()); 
				if (d.isNameDS()) {
					try {
						if (server.addDSYT(d.getName(), user)) {
							if (!lv.ok) {
								Nhac nadd = new Nhac();
								nadd = ListNhac.get(mv.table.getSelectedRow());
								d = server.getDanh_sach(d, user);
								server.addNhacToDS(d, nadd);
								JOptionPane.showMessageDialog(adsv, "Đã thêm bài hét vào danh sách");
							} else {
								JOptionPane.showMessageDialog(adsv, "Đã tạo danh sách");
							}
							adsv.NameDS.setText(null);
							adsv.setVisible(false);
							ShowListDS();
							lv.setVisible(false);
							
						} else {
							JOptionPane.showMessageDialog(adsv, "Lỗi");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(adsv, "Tên danh sách từ 1-50 ký tự và không chưa kí tự đặt biệt");
				}
			}
			
		});
		
// main View 
//		ListTac_gia = server.getListTac_gia();
		
		mv.FindTheLoai.setModel(new DefaultComboBoxModel(getTheLoai()));
		
	// button logout
		mv.LogOutButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mv.Logout();
				login.setVisible(true);
				
			}
			
		});
	// button add
		mv.AddButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				av.setVisible(true);
				
			}
			
		});
		
	// button refresh
		mv.RefreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ListNhac = server.getListNhac();
					ShowList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mv.FindMa.setText(null);
				mv.FindTen.setText(null);
				mv.FindLoiMoDau.setText(null);
				mv.FindTacGia.setText(null);
				mv.FindTheLoai.setSelectedIndex(0);
				mv.FindHinhThuc.setSelectedIndex(0);
				mv.buttonDropMod = true;
				danh_sach = new Danh_sach();
				ev.setVisible(false);
				av.setVisible(false);
				lv.setVisible(false);
				mv.changeMod();
			}
			
		});
		
	// button edit
		mv.EditButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mv.table.getSelectedRow() >= 0) {
					ev.changeMod();
					ev.EditMa.setText(ListNhac.get(mv.table.getSelectedRow()).getMa());
					ev.EditTen.setText(ListNhac.get(mv.table.getSelectedRow()).getTen());
					ev.EditLoi_mo_dau.setText(ListNhac.get(mv.table.getSelectedRow()).getLoi());
					ev.EditTac_Gia.setText(ListNhac.get(mv.table.getSelectedRow()).getTac_gia());
					ev.EditThe_loai.setSelectedItem(ListNhac.get(mv.table.getSelectedRow()).getThe_loai());
					ev.EditHinh_Thuc.setSelectedItem(ListNhac.get(mv.table.getSelectedRow()).getHinh_thuc());
					
					ev.setVisible(true);
				}
			}
			
		});
		
	// button del
		mv.DropButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mv.table.getSelectedRow() >= 0) {
					if (mv.buttonDropMod) {
						try {
							if (server.deleteNhac(ListNhac.get(mv.table.getSelectedRow()))) {
								ListNhac = server.getListNhac();
								ShowList();
								JOptionPane.showMessageDialog(ev, "Đã xóa bài hát thành công");
							} else {
								JOptionPane.showMessageDialog(av, "Lõi");
							}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							if (server.deleteNhacFromDS(ListNhac.get(mv.table.getSelectedRow()), danh_sach)) {
								showNhacInDS(danh_sach);
								JOptionPane.showMessageDialog(av, "Đã xóa bài hát khỏi danh sách");
							} else {
								JOptionPane.showMessageDialog(av, "Lỗi");
							}
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
			
						
						
					}
				}
			}
			
		});
		
		
	// button find
		mv.FindButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Nhac n = new Nhac();
				n.setMa(mv.FindMa.getText());
				n.setTen(mv.FindTen.getText());
				n.setLoi(mv.FindLoiMoDau.getText());
				n.setTac_gia(mv.FindTacGia.getText());
				n.setThe_loai((String) mv.FindTheLoai.getSelectedItem());
				n.setHinh_thuc((String) mv.FindHinhThuc.getSelectedItem());
				n.beforeFind();
				
				try {
					ListNhac = server.findNhac(n);
					ShowList();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
	// double click table
		mv.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = mv.table.getSelectedRow();
					mv.DetailsNPane.setText("  Ma: " + ListNhac.get(row).getMa() + " \t\tTen: "
											+ ListNhac.get(row).getTen() + " \r\n  Tac gia: "
											+ ListNhac.get(row).getTac_gia() + " \r\n  The loai: "
											+ ListNhac.get(row).getThe_loai() + " \t\tHinh thuc: "
											+ ListNhac.get(row).getHinh_thuc() + " \r\n  Loi: "
											+ ListNhac.get(row).getLoi());
				}
			}
		});
		
	// button danh sach yeu thich
		mv.ShowDSButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ShowListDS();
					lv.setVisible(true);
					lv.ok = true;
					lv.DeleteDSButton.setVisible(true);
					lv.EditDSButton.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
	// button add danh sach 
		mv.addDSYTButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (mv.table.getSelectedRow() >= 0) {
					try {
						ShowListDS();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					lv.setVisible(true);
					lv.ok = false;
				}
				lv.DeleteDSButton.setVisible(false);
				lv.EditDSButton.setVisible(false);
			}
			
		});
		
		
		//
		
		
		login.setVisible(true);
		
	}
	
	public void Login(User user) throws Exception {
		mv.frame.setVisible(true);
		this.user =  mv.user = server.Login(user);
		mv.changeMod();
		ListNhac = server.getListNhac();
		ShowList();
	}
	
	public void ShowList() throws Exception {
//		mv.ListNhac = server.getListNhac();
//		ListNhac = server.getListNhac();
		Refresh();
		
		for (Nhac nhac : ListNhac) {
			mv.model.addRow(new Object[] {
				nhac.getMa(), nhac.getTen(), nhac.getLoi(), nhac.getTac_gia(), nhac.getThe_loai(), nhac.getHinh_thuc()
			});
		}
		
	}
	
	public void ShowListDS() throws Exception {
		ListDanh_sach = server.readDS(this.user);
		RefreshDS();
		for (Danh_sach ds : ListDanh_sach) {
			lv.modelDS.addRow(new Object[] {ds.getName()} );
		}
	}
	
	public void showNhacInDS(Danh_sach ds) throws Exception {
		Refresh();
		ListNhac = server.readNhacInDS(ds);
		for (Nhac n : ListNhac) {
			mv.model.addRow(new Object[] {
					n.getMa(), n.getTen(), n.getLoi(), n.getTac_gia(), n.getThe_loai(), n.getHinh_thuc()
			});
		}
	}
	
	public void RefreshDS() {
		lv.tableDS.setModel(new DefaultTableModel(
				new Object[][] {}, 
				new String[] {
						"Tên danh sách"
				}
				) {
			boolean[] columnEditables = new boolean[] {
					false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		lv.modelDS = (DefaultTableModel) lv.tableDS.getModel();
	}
	
	public void Refresh() { 
		mv.table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {
				"Mã", "Tên bài hát", "Lời", "Tác giả", "Thể loại", "Hình thức"
		})
				{
			private static final long serialVersionUID = 1L;
			
			boolean[] columnEdittables = new boolean[] {
					false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEdittables[column];
			}
				});
		
		mv.model = (DefaultTableModel) mv.table.getModel();
		mv.DetailsNPane.setText(null);
	}
	
	public String[] getTheLoai() {
		String [] tl = new String[ListThe_Loai.size() + 1];
		tl[0] = "";
		for (int i = 0; i < ListThe_Loai.size(); i++) {
			tl[i+1] = ListThe_Loai.get(i).getThe_loai();
		}
		return tl;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Client run = new Client();
	}

}
