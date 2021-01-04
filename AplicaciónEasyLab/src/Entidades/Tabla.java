package Entidades;

import Datos.DATPracticas;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Tabla {

    DATPracticas dao = null;

    public void visualizar_Pdf(JTable tabla) {
        tabla.setDefaultRenderer(Object.class, new imgTabla());
        DefaultTableModel dt = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dt.addColumn("Nombre_Practicas");
        dt.addColumn("Archivo_PDF");

        ImageIcon icono = null;
        if (get_Image("/IMG/32pdf.png") != null) {
            icono = new ImageIcon(get_Image("/IMG/32pdf.png"));
        }

        dao = new DATPracticas();
        PDF vo = new PDF();
        ArrayList<PDF> list = dao.Listar_PDFs();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Object fila[] = new Object[2];
                vo = list.get(i);
                fila[0] = vo.getNombrepdf();
                if (vo.getArchivopdf() != null) {
                    fila[1] = new JButton(icono);
                } else {
                    fila[1] = new JButton("Vacio");
                }

                dt.addRow(fila);
            }
            tabla.setModel(dt);
            tabla.setRowHeight(32);
        }
    }

    public Image get_Image(String ruta) {
        try {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(ruta));
            Image mainIcon = imageIcon.getImage();
            return mainIcon;
        } catch (Exception e) {
        }
        return null;
    }
}
