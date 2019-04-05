package lambda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Main {
	
	public static class SektorData {


		public Sektor sektor = null;
		public Enums.DurumSet durum;
		public Sektor constantSektor  = null;

		public SektorData(Sektor sektorler, Enums.DurumSet durum, Sektor constantSektorler) {
				this.sektor = sektorler;
				this.durum = durum;
				this.constantSektor = constantSektorler;
			}
			
	}
	
	public static void main(String[] args) {

		Main m = new Main();

		SektorData s1 = new SektorData(new Sektor(0,10),Enums.DurumSet.VARSAYILAN,new Sektor(0,10));
		SektorData s2 = new SektorData(new Sektor(1,20),Enums.DurumSet.VARSAYILAN,new Sektor(1,20));
		SektorData s3 = new SektorData(new Sektor(2,30),Enums.DurumSet.VARSAYILAN,new Sektor(2,30));
		
		List<SektorData> list = Arrays.asList(s1,s2,s3);
		
		List<String> str = Arrays.asList("as", "as", "ds", "separ");
		str.stream().filter(x -> x.equals("asa") || x.contains("aa")).collect(Collectors.toList())
				.forEach(System.out::println);

		JFrame fr = new JFrame("TEST");
		fr.setLayout(new BorderLayout());
		fr.setSize(350, 350);
		
		JPanel pn = new JPanel(new BorderLayout());
		pn.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JTable tb = new JTable(0,3);
		tb.setEnabled(false);

		setTable(list,tb);
		
		pn.add(tb);
		fr.add(pn,BorderLayout.CENTER);
		fr.setVisible(true);

	}
	
	public static void setTable(List<SektorData> list, JTable tb) {
		list.stream().forEach( x-> {
			((DefaultTableModel)tb.getModel()).addRow( (new Object[] {x.sektor.id,x.durum.toString(),x.constantSektor.id}) );
		});
		
	}

}
